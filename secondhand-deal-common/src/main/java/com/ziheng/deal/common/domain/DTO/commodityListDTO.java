package com.ziheng.deal.common.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息返回
 */

@Data
public class commodityListDTO {
    private Integer id; // 商品id
    private String commodityTitle; // 商品名称
    private BigDecimal price; // 价格
    private Integer status; // 状态
    private Integer cNumber; // 数量
    private Integer buyNumber; //销量
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createData; // 创建时间
    private String imgPath; // 展示图地址
    private String rejectReason; // 商品违规原因


}
