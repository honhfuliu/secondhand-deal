package com.ziheng.deal.common.domain.VO;

import com.ziheng.deal.common.domain.query.PageQueryBase;
import lombok.Data;

/**
 * 商品分页查询类
 */
@Data
public class commodityPageQueryVO  extends PageQueryBase {
    private String commodityTitle; // 名称
    private Integer status; // 状态
}
