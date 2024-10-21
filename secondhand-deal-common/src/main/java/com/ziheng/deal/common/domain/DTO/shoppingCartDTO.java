package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 购物车数据返回
 */
@Data
public class shoppingCartDTO {
    private Integer id; // 购物车id
    private String title; // 商品标题
    private BigDecimal unitPrice; // 商品单击
    private Integer status; // 商品id
    private Integer number; // 购买数量
    private BigDecimal totalPrice; // 总价
    private String imgPath; // 商品展示图
    private String stingSku;
    private HashMap<String, Object> commoditySku; // 商品规格

}
