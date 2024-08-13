package com.ziheng.deal.common.domain.VO;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息接收VO对象
 */
@Data
public class UserInfoV0 {
    private String name;
    private Integer sex;
    private Date birthday;
}
