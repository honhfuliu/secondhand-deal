package com.ziheng.deal.common.domain.VO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 商品结算数据接收
 */
@Data
public class CommoditySettlementVO {
    // 商品id
    @NotNull(message = "商品id不能为空")
    private Integer commodityId;
    // 单价
    @NotNull(message = "单价不能为空")
    private BigDecimal unitPrice;
    // 总价
    @NotNull(message = "总价不能为空")
    private BigDecimal totalPrice;
    // 购买数量
    @NotNull(message = "购买数量不能为空")
    private Integer buyQuantity;
    //商品sku规格
    private HashMap<String, Object> commoditySku;
}
