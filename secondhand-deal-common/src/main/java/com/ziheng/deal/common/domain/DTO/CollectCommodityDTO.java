package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户收藏商品返回对象
 */
@Data
public class CollectCommodityDTO {
    private Integer id;
    private String imgPath;
    private BigDecimal cPrice;
    private String title;
    private Integer status;
}
