package com.ziheng.deal.common.domain.VO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

@Data
public class CommodityVO {
    private Integer cId; // 商品的id

    private Integer classifyId; // 商品分类

    @NotBlank(message = "商品标题不能为空")
    private String commodityTitle; // 商品标题

    @NotBlank(message = "品牌不能为空")
    private String brand; // 品牌

    @NotBlank(message = "产地不能为空")
    private String locality; // 产地


    private String isinteger; // 是否整装

    private String style; // 风格
    private String design; // 图案
    private String material; // 材质

    @NotBlank(message = "适用对象不能为空")
    private String suitableObject; // 适用对象

    @NotBlank(message = "商品类型不能为空")
    private String commodityType; // 商品类型（成色）

//    @NotNull(message = "商品价格不能为空")
//    private BigDecimal cPrice; // 价格
//
//    @NotNull(message = "商品数量不能为空")
//    private Integer cNumber; // 数量

    @NotBlank(message = "配送方式不能为空")
    private String deliveryMethod; // 配送方式

    private Float shippingFees; // 配送费用

    private ArrayList<String> commodityPicturePaths; // 商品主图的存储地址

    @NotBlank(message = "商品详情不能为空")
    private String commodityDetails; // 商品详情信息

    private ArrayList<HashMap<String,Object>> commoditySku; // 商品sku存储
    private ArrayList<HashMap<String,Object>> commodityHeader; // 商品sku规格

}
