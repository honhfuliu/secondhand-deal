package com.ziheng.deal.common.domain;

import lombok.Data;

/**
 * ip地址位置实体类
 */

@Data
public class IpInfo {
    // IP 地址
    private String ip;

    // 省份名称
    private String pro;

    // 省份代码
    private String proCode;

    // 城市名称
    private String city;

    // 城市代码
    private String cityCode;

    // 地区名称
    private String region;

    // 地区代码
    private String regionCode;

    // 详细地址
    private String addr;

    // 地区名称列表（逗号分隔）
    private String regionNames;

    // 错误信息（如果有）
    private String err;

}
