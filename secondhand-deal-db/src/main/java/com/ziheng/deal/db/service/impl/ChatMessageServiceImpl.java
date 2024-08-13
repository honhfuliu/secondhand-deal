package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ziheng.deal.common.domain.DTO.ChatUserMessageDTO;
import com.ziheng.deal.common.domain.VO.ChatVO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.ChatUserFieldCannotEmptyException;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.InsertException;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.db.entity.ChatMessage;
import com.ziheng.deal.db.service.ChatMessageService;
import com.ziheng.deal.db.mapper.ChatMessageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【chat_message(用户聊天信息存储表)】的数据库操作Service实现
* @createDate 2024-07-30 23:39:09
*/
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage>
    implements ChatMessageService{

    @Resource
    private ChatMessageMapper chatMessageMapper;

    /**
     * 用户消息存储
     * @param userId 发送消息者的id
     * @param chatVO 消息信息与接收消息人信息
     */
    @Override
    public void userChatMessageSave(Integer userId, ChatVO chatVO) {
        // 判断各个参数不能为空

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId(userId);
        chatMessage.setReceiverId(chatVO.getId());
        chatMessage.setStatus('0');
        chatMessage.setType(chatMessage.getType());
        chatMessage.setMessage(chatVO.getMessage());
        chatMessage.setCreateTime(new Date());
        int insert = chatMessageMapper.insert(chatMessage);
        if (insert != 1) {
            throw new InsertException();
        }


    }

    /**
     * 根据我的id和聊天用户ID获取到聊天信息
     * @param myId 我的ID
     * @param friendId 聊天用户ID
     * @return 用户聊天信息
     */
    @Override
    public List<ChatUserMessageDTO> getUserChatInformation(Integer myId, Integer friendId) {
        if (friendId == null){
           // 参数为空异常处理
            throw new ChatUserFieldCannotEmptyException("聊天用户字段不能为空");
        }

        List<ChatUserMessageDTO> chatUserMessageDTOList = chatMessageMapper.getUserChatInformation(myId, friendId);

        return chatUserMessageDTOList;
    }
}




