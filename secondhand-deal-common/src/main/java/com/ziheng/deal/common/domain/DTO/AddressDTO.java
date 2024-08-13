package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

/**
 * 收货地址信息返回对象
 */

@Data
public class AddressDTO {
    private Integer id; // id
    private String name; // 收货人名称
    private Integer status; // 是否是默认地址
    private String contact; //手机号
    private String fullAddress; // 详细地址
    private String provinceName; // 省
    private String cityName; // 市
    private String areaName; // 区
    private String streetName; // 街道
    private Long provinceCode;
    private Long cityCode;
    private Long areaCode;
    private Long streetCode;
}
