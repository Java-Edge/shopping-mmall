package com.mmall.dao;

import com.mmall.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> getByOrderNoUserId(@Param("orderNo")Long orderNo, @Param("userId")Integer userId);

    /**
     * 根据订单ID查询订单
     *
     * @param orderNo
     * @return
     */
    List<OrderItem> getByOrderNo(@Param("orderNo")Long orderNo);



    void batchInsert(@Param("orderItemList") List<OrderItem> orderItemList);


}