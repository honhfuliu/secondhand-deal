package com.ziheng.deal.db.mapper;

import com.ziheng.deal.common.domain.DTO.ChatUserMessageDTO;
import com.ziheng.deal.db.entity.ChatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【chat_message(用户聊天信息存储表)】的数据库操作Mapper
* @createDate 2024-07-30 23:39:09
* @Entity com.ziheng.deal.entity.ChatMessage
*/
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
    // 根据我的ID和聊天用户的ID获取到聊天信息
    List<ChatUserMessageDTO> getUserChatInformation(@Param("myId") Integer myId, @Param("friendId") Integer friendId);

}




