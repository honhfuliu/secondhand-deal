package com.ziheng.deal.common.domain.VO;

import lombok.Data;

/**
 * 用户消息接收对象
 */

@Data
public class ChatVO {
    private String name; // 用户名称
    private String message; // 用户消息
    private Integer id; // 用户ID
    private Integer type; // 消息类型

}
