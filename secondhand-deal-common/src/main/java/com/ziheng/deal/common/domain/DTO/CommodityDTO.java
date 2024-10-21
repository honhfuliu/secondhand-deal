package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class CommodityDTO {
    private Integer cId; // 商品的id

    private String classificationName; // 商品分类名称


    private String commodityTitle; // 商品标题


    private String brand; // 品牌

    private Integer buyNumber; // 销售数量


    private String locality; // 产地


    private String isinteger; // 是否整装

    private String style; // 风格
    private String design; // 图案
    private String material; // 材质


    private String suitableObject; // 适用对象


    private String commodityType; // 商品类型（成色）


    private BigDecimal cPrice; // 价格

    private Map<String, List<Object>> commoditySkuHeader; // sku规格

    private List<Map<String,Object>> commoditySkus; // sku信息

    private Integer cNumber; // 数量


    private String deliveryMethod; // 配送方式

    private Float shippingFees; // 配送费用

    private ArrayList<String> commodityPicturePaths; // 商品主图的存储地址


    private String commodityDetails; // 商品详情信息

}
