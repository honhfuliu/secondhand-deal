package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 我的订单信息返回
 */
@Data
public class MyOrderItem {
    private Integer commodityId; // 商品id
    private Integer commodityNumber; // 商品数量
    private BigDecimal commodityUnitPrice; // 商品单价
    private BigDecimal discountedPrice; // 积分减免金额
    private BigDecimal orderPrice; // 订单金额
    private String commodityTitle; // 商品标题
    private String commodityPath; // 商品主图
    private Map<String, Object> commoditySku;// 商品sku
    private BigDecimal deliveryFee; //配送费用
    private String status;// 状态
}
