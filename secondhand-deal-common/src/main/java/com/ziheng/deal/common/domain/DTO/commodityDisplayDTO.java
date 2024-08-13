package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品首页展示数据返回DTO
 */
@Data
public class commodityDisplayDTO {
    private Integer id; // 商品id
    private String commodityTitle; // 商品名称
    private BigDecimal cPrice; // 价格
    private String imgPath; // 展示图地址
    private Integer buyNumber; //销量
}
