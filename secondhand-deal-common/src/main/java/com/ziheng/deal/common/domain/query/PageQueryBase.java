package com.ziheng.deal.common.domain.query;

import lombok.Data;

/**
 * 分页查询基类
 */
@Data
public class PageQueryBase {
    private Integer PageNo; //页码
    private Integer PageSize; //每页数据条数
    private String sortBy; // 排序字段(如果排序字段为null默认按照创建时间来排序)
    private boolean isAsc; //是否升序
}
