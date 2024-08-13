package com.ziheng.deal.common.domain.VO;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommodityVerifyVO {
    @NotNull(message = "id不能为空")
    private Integer cId; // 商品的id

    @NotNull(message = "状态不能为空")
    private Integer status; // 状态

    private String rejectReason; //未通过原因
}
