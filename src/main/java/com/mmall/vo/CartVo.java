package com.mmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JavaEdge
 */
@Data
public class CartVo {

    private List<CartProductVo> cartProductVoList;

    /**
     * 商品总价
     */
    private BigDecimal cartTotalPrice;

    /**
     * 是否已经都勾选
     */
    private Boolean allChecked;

    private String imageHost;

}
