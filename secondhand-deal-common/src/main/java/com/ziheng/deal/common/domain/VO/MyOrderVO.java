package com.ziheng.deal.common.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import lombok.Data;

import java.util.Date;

@Data
public class MyOrderVO extends PageQueryBase {
    private String orderNumber; // 订单号
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date startTime; // 订单开始时间
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date endTime; // 订单结束时间
    private String orderStatus; // 订单状态

    @Override
    public String toString() {
        return "MyOrderVO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
