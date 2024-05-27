package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.util.List;
// 分页查询结果返回

@Data
public class PageDOT<T> {
    private Integer total;
    private List<T> list;
}
