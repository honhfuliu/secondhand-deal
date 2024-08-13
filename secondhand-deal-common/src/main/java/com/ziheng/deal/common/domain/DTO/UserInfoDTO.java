package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

/**
 * 个人信息查询返回对象
 */
@Data
public class UserInfoDTO {
    private String name; // 昵称
    private Integer score; // 积分
    private String path; // 图片地址


}
