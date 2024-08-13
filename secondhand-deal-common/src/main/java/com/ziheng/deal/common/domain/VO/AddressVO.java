package com.ziheng.deal.common.domain.VO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 收货地址添加VO对象
 */
@Data
public class AddressVO {
    private Integer addressId;

    /**
     * 收货人名称
     */
    @NotBlank(message = "收货人名称不能为空")
    private String addressName;

    /**
     * 收货人手机号
     */
    @NotBlank(message = "收货人手机号不能为空")
    private String contact;

    /**
     * 省
     */
    @NotNull(message = "地址编码不能为空")
    private Long province;

    /**
     * 市
     */
    @NotNull(message = "地址编码不能为空")
    private Long city;

    /**
     * 区
     */
    @NotNull(message = "地址编码不能为空")
    private Long area;

    /**
     * 街道
     */
    @NotNull(message = "地址编码不能为空")
    private Long street;

    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    private String fullAddress;

}
