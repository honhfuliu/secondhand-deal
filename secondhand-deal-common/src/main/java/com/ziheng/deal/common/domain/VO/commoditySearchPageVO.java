package com.ziheng.deal.common.domain.VO;

import com.ziheng.deal.common.domain.query.PageQueryBase;
import lombok.Data;

@Data
public class commoditySearchPageVO extends PageQueryBase {
    private String searchName;

}
