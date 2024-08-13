package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

/**
 * 聊天用户
 */
@Data
public class ChatUserMessageDTO {
    private String message; //消息
    private Integer sort; //分辨我的消息还是对方的消息
    private String type; //消息类型
}
