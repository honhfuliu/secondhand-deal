package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 结算商品数据返回
 */
@Data
public class SettlementCommodityDTO {
    private Integer commodityId; // 商品id
    private Integer shoppingId; // 购物车id
    private String path; // 主图
    private String deliveryMethod; // 配送方式
    private BigDecimal deliveryFee; // 配送费用
    private String title; // 商品标题
    private BigDecimal unitPrice; // 单价
    private Integer buyQuantity; // 购买数量
    //  商品sku规格
    private HashMap<String,Object> commoditySku;


}
