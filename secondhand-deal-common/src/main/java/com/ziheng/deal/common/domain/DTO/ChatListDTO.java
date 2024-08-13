package com.ziheng.deal.common.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 聊天用户列表返回
 */
@Data
public class ChatListDTO {
    private Integer id; // 用户ID
    private String img; // 头像
    private String name; //账号名称
    private String nickName;// 昵称
    private String endMessage; //最后一条消息
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime; //最后一条消息的发送时间
    private Integer unreadCount; // 未读消息数量

    @Override
    public String toString() {
        return "ChatListDTO{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", endMessage='" + endMessage + '\'' +
                ", endTime=" + endTime +
                ", unreadCount=" + unreadCount +
                '}';
    }
}
