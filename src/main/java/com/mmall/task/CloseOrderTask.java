package com.mmall.task;

import com.mmall.common.Const;
import com.mmall.common.RedissonManager;
import com.mmall.service.IOrderService;
import com.mmall.util.PropertiesUtil;
import com.mmall.util.RedisSharedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务 订单超时未付款
 *
 * @author Shusheng Shi
 */
@Component
@Slf4j
public class CloseOrderTask {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private RedissonManager redissonManager;

    @PreDestroy
    public void delLock() {
        RedisSharedPoolUtil.del( Const.RedisLock.CLOSE_ORDER_TASK_LOCK );

    }

    /**
     * 每1分钟(每个1分钟的整数倍)
     */
    @Scheduled(cron="0 */1 * * * ?")
    public void closeOrderTaskV3() {
        log.info( "关闭订单定时任务启动" );
        long lockTimeout=Long.parseLong( PropertiesUtil.getProperty( "lock.timeout", "5000" ) );
        //此时有效期无限,通过setnx保证了同步
        Long setnxResult=RedisSharedPoolUtil.setnx( Const.RedisLock.CLOSE_ORDER_TASK_LOCK, String.valueOf( System.currentTimeMillis() + lockTimeout ) );
        //尝试获取锁,相当于Redission中的tryLock()
        if (setnxResult != null && setnxResult.intValue() == 1) {
            closeOrder( Const.RedisLock.CLOSE_ORDER_TASK_LOCK );
        } else {
            //未获取到锁，继续判断，判断时间戳，看是否可以重置并获取到锁
            String lockValueStr=RedisSharedPoolUtil.get( Const.RedisLock.CLOSE_ORDER_TASK_LOCK );
            //尝试获取锁,相当于Redission中的tryLock()
            if (lockValueStr != null && System.currentTimeMillis() > Long.parseLong( lockValueStr )) {
                String getSetResult=RedisSharedPoolUtil.getSet( Const.RedisLock.CLOSE_ORDER_TASK_LOCK, String.valueOf( System.currentTimeMillis() + lockTimeout ) );
                /**
                 *  再次用当前时间戳getset
                 *  返回给定的key的旧值，->旧值判断，是否可以获取锁
                 *  当key没有旧值时，即key不存在时，返回null ->获取锁
                 *  这里我们set了一个新的value值，获取旧的值。
                 */
                //尝试获取锁,相当于Redission中的tryLock()
                if (getSetResult == null || (getSetResult != null && StringUtils.equals( lockValueStr, getSetResult ))) {
                    //真正获取到锁       //如果返回值是1，代表设置成功，获取锁
                    closeOrder( Const.RedisLock.CLOSE_ORDER_TASK_LOCK );
                } else {
                    log.info( "没有获取到分布式锁:{}", Const.RedisLock.CLOSE_ORDER_TASK_LOCK );
                }
            } else {
                log.info( "没有获取到分布式锁:{}", Const.RedisLock.CLOSE_ORDER_TASK_LOCK );
            }
        }
        log.info( "关闭订单定时任务结束" );
    }



    @Scheduled(cron="0 */1 * * * ?")
    public void closeOrderTask() {
        RLock lock=redissonManager.getRedisson().getLock( Const.RedisLock.CLOSE_ORDER_TASK_LOCK );
        boolean getLock=false;
        try {
            //如果难以预估时间,waittime设为0,否则会出现两个tomcat都获得分布式锁,因为定时任务可能执行的很快
            if (getLock=lock.tryLock( 0, 50, TimeUnit.SECONDS )) {
                log.info( "Redisson获取到分布式锁:{},ThreadName:{}", Const.RedisLock.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName() );
                int hour=Integer.parseInt( PropertiesUtil.getProperty( "close.order.task.time.hour", "2" ) );
                iOrderService.closeOrder(hour);
            } else {
                log.info( "Redisson没有获取到分布式锁:{},ThreadName:{}", Const.RedisLock.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName() );
            }
        } catch (InterruptedException e) {
            log.error( "Redisson分布式锁获取异常", e );
        } finally {
            if (!getLock) {
                return;
            }
            lock.unlock();
            log.info( "Redisson分布式锁释放锁" );
        }
    }

    //设置锁的有效期,防止setnx失效
    private void closeOrder(String lockName) {
        //有效期5秒，防止死锁
        RedisSharedPoolUtil.expire( lockName, 5 );
        log.info( "获取{},ThreadName:{}", Const.RedisLock.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName() );
        int hour=Integer.parseInt( PropertiesUtil.getProperty( "close.order.task.time.hour", "2" ) );
        iOrderService.closeOrder( hour );
        RedisSharedPoolUtil.del( Const.RedisLock.CLOSE_ORDER_TASK_LOCK );
        log.info( "释放{},ThreadName:{}", Const.RedisLock.CLOSE_ORDER_TASK_LOCK, Thread.currentThread().getName() );
        log.info( "===============================" );
    }
}
