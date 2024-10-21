package com.ziheng.deal.common.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MyOrderDTO {
    private String orderNumber; // 订单号
    List<MyOrderItem> myOrderItemList; //商品信息
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime; //创建时间
}
