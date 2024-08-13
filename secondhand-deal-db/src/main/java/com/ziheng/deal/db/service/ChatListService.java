package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.ChatListDTO;
import com.ziheng.deal.db.entity.ChatList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【chat_list】的数据库操作Service
* @createDate 2024-07-30 20:49:13
*/
public interface ChatListService extends IService<ChatList> {
    // 根据用户id 和商品id 查询该用户是否与我聊过天，没有则添加到数据库中
    void addFriend(Integer userId, Integer commodityId);

    // 根据用户ID展示出聊天用户列表
    List<ChatListDTO> getByUserIdChatLists(Integer userId);

}
