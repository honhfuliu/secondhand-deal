package com.ziheng.deal.common.domain.VO;

import lombok.Data;

/**
 * 用户注册数据接收 VO对象
 */
@Data
public class UserRegisterVO {
    public String email;
    public String code;
    public String username;
    public String password;
}
