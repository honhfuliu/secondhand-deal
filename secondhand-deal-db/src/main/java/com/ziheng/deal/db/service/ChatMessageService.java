package com.ziheng.deal.db.service;


import com.ziheng.deal.common.domain.DTO.ChatUserMessageDTO;
import com.ziheng.deal.common.domain.VO.ChatVO;
import com.ziheng.deal.db.entity.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【chat_message(用户聊天信息存储表)】的数据库操作Service
* @createDate 2024-07-30 23:39:09
*/
public interface ChatMessageService extends IService<ChatMessage> {

    // 用户发送消息信息存储
    void  userChatMessageSave(Integer userId, ChatVO chatVO);

    // 根据我的ID和聊天用户的ID获取到聊天信息
    List<ChatUserMessageDTO> getUserChatInformation(Integer myId, Integer friendId);

}
