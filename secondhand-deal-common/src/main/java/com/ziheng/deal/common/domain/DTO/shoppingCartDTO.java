package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车数据返回
 */
@Data
public class shoppingCartDTO {
    private Integer id;
    private String title;
    private BigDecimal unitPrice;
    private Integer status;
    private Integer number;
    private BigDecimal totalPrice;
    private String imgPath;

}
