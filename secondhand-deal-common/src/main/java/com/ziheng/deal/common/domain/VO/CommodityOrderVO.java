package com.ziheng.deal.common.domain.VO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 商品订单确认提交数据接收
 */
@Data
public class CommodityOrderVO {
    private List<CommoditySettlementVO> commodityInfos; // 商品信息
    private Integer integral; // 积分

    private Integer useIntegralId; // 使用积分的商品id

    @NotNull(message = "是否使用积分不能为空")
    private boolean whetherIntegral; // 是否使用积分

    @NotNull(message = "收货地址不能为空")
    private Integer addressId; // 收货地址id

    private String remark; // 备注


}
