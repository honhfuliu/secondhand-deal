package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户聊天信息存储表
 * @TableName chat_message
 */
@TableName(value ="chat_message")
@Data
public class ChatMessage implements Serializable {
    /**
     * ID 主键 自增
     */
    @TableId(type = IdType.AUTO)
    private Integer messageId;

    /**
     * 发送消息着的id
     */
    private Integer senderId;

    /**
     * 信息接收者ID
     */
    private Integer receiverId;

    /**
     * 消息类型 文本-text 图片-image 视频-video
     */
    private Object type;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消息状态 0-已读 1-未读
     */
    private Object status;

    /**
     * 判断消息是对方的还是我的 我的-0  对方-1
     */
    private Object isSender;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ChatMessage other = (ChatMessage) that;
        return (this.getMessageId() == null ? other.getMessageId() == null : this.getMessageId().equals(other.getMessageId()))
                && (this.getIsSender() == null ? other.getIsSender() == null : this.getIsSender().equals(other.getIsSender()))
            && (this.getReceiverId() == null ? other.getReceiverId() == null : this.getReceiverId().equals(other.getReceiverId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMessageId() == null) ? 0 : getMessageId().hashCode());
        result = prime * result + ((getIsSender() == null) ? 0 : getIsSender().hashCode());
        result = prime * result + ((getReceiverId() == null) ? 0 : getReceiverId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageId=").append(messageId);
        sb.append(", isMy=").append(isSender);
        sb.append(", myId=").append(receiverId);
        sb.append(", type=").append(type);
        sb.append(", message=").append(message);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}