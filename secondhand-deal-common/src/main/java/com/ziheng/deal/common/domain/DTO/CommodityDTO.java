package com.ziheng.deal.common.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class CommodityDTO {

    private String classificationName; // 商品分类名称


    private String commodityTitle; // 商品标题


    private String brand; // 品牌


    private String locality; // 产地


    private String isinteger; // 是否整装

    private String style; // 风格
    private String design; // 图案
    private String material; // 材质


    private String suitableObject; // 适用对象


    private String commodityType; // 商品类型（成色）


    private BigDecimal cPrice; // 价格


    private Integer cNumber; // 数量


    private String deliveryMethod; // 配送方式

    private Float shippingFees; // 配送费用

    private ArrayList<String> commodityPicturePaths; // 商品主图的存储地址


    private String commodityDetails; // 商品详情信息

}
