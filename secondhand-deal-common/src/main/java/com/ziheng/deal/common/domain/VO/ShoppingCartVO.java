package com.ziheng.deal.common.domain.VO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashMap;

/**
 * 购物车商品添加对象
 */

@Data
public class ShoppingCartVO {
    @NotNull(message = "商品id不能为空")
    private Integer commodityId;

    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量不能小于1")
    private Integer shoppingNumber;

    private HashMap<String, Object> commoditySku;

}
