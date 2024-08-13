package com.ziheng.deal.db.mapper;

import com.ziheng.deal.common.domain.DTO.ChatListDTO;
import com.ziheng.deal.db.entity.ChatList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【chat_list】的数据库操作Mapper
* @createDate 2024-07-30 20:49:13
* @Entity com.ziheng.deal.entity.ChatList
*/
public interface ChatListMapper extends BaseMapper<ChatList> {
    // 根据用户ID展示出聊天用户列表
    List<ChatListDTO> getByUserIdChatLists(@Param("userId") Integer userId);

}




