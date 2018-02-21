package com.mmall.service.test;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;
import com.mmall.service.impl.ShippingServiceImpl;
import com.mmall.test.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by geely
 */
@Slf4j
public class ShippingServiceTest extends TestBase {


    @Autowired
    private ShippingServiceImpl iShippingService;

    @Test
    public void testSelect(){
        ServerResponse sr = iShippingService.select(1, 3);
        Shipping shipping = (Shipping)sr.getData();
        log.info(ToStringBuilder.reflectionToString(shipping));

    }

    @Test
    public void testList(){
        ServerResponse sr = iShippingService.list(1,1,2);
        PageInfo<Shipping> p=new PageInfo<Shipping>((List<Shipping>)sr.getData());
        log.info(ToStringBuilder.reflectionToString(sr.getData()));
        log.info("======");

        ServerResponse x = ServerResponse.createBySuccess(p);
        log.info(ToStringBuilder.reflectionToString(x));

//        {
//            "status": 0,
//                "date": {
//            "pageNum": 1,
//                    "pageSize": 2,
//                    "size": 2,
//                    "startRow": 1,
//                    "endRow": 2,
//                    "total": 8,
//                    "pages": 4,
//                    "list": [
//            {
//                "id": 3,
//                    "userId": 1,
//                    "receiverName": "a",
//                    "receiverPhone": "0100",
//                    "receiverMobile": "186",
//                    "receiverProvince": "北京",
//                    "receiverCity": "北京",
//                    "receiverDistrict": "昌平区",
//                    "receiverAddress": "矩阵小区",
//                    "receiverZip": "100000",
//                    "createTime": 1480515824000,
//                    "updateTime": 1480515824000
//            },
//            {
//                "id": 4,
//                    "userId": 1,
//                    "receiverName": "b",
//                    "receiverPhone": "0100",
//                    "receiverMobile": "186",
//                    "receiverProvince": "北京",
//                    "receiverCity": "北京",
//                    "receiverDistrict": "昌平区",
//                    "receiverAddress": "矩阵小区",
//                    "receiverZip": "100000",
//                    "createTime": 1480515824000,
//                    "updateTime": 1480515824000
//            }
//            ],
//            "firstPage": 1,
//                    "prePage": 0,
//                    "nextPage": 2,
//                    "lastPage": 4,
//                    "isFirstPage": true,
//                    "isLastPage": false,
//                    "hasPreviousPage": false,
//                    "hasNextPage": true,
//                    "navigatePages": 8,
//                    "navigatepageNums": [
//            1,
//                    2,
//                    3,
//                    4
//            ]
//        }
//        }
    }
}
