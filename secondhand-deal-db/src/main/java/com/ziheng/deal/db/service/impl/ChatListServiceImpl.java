package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.DTO.ChatListDTO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.InsertException;
import com.ziheng.deal.db.entity.ChatList;
import com.ziheng.deal.db.entity.TCommodity;
import com.ziheng.deal.db.mapper.ChatMessageMapper;
import com.ziheng.deal.db.mapper.TCommodityMapper;
import com.ziheng.deal.db.service.ChatListService;
import com.ziheng.deal.db.mapper.ChatListMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【chat_list】的数据库操作Service实现
* @createDate 2024-07-30 20:49:13
*/
@Service
public class ChatListServiceImpl extends ServiceImpl<ChatListMapper, ChatList>
    implements ChatListService{

    @Resource
    private TCommodityMapper commodityMapper;

    @Resource
    private ChatListMapper chatListMapper;

    @Resource
    private ChatMessageMapper chatMessageMapper;

    /**
     * 根据用户ID和商品ID查询我是否与该用户聊过天
     * @param userId 用户ID
     * @param commodityId 商品ID
     */
    @Override
    public void addFriend(Integer userId, Integer commodityId) {
        // 根据商品ID 查询出用户ID
        TCommodity commodity = commodityMapper.selectById(commodityId);

        if (userId.equals(commodity.getUserId())){
            return;
        }

        //查询该用户是否与我聊过天
        LambdaQueryWrapper<ChatList> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatList::getMyId, userId);
        wrapper.eq(ChatList::getFriendId, commodity.getUserId());

        ChatList chatList = chatListMapper.selectOne(wrapper);

        // 没有则添加到数据库中
        if (chatList == null) {
            // 插入一条记录，我和对方
            ChatList list = new ChatList();
            list.setMyId(userId);
            list.setFriendId(commodity.getUserId());
            list.setIsDelete(0);
            list.setCreatedTime(new Date());
            list.setUpdatedTime(new Date());
            int insert = chatListMapper.insert(list);
            if (insert != 1) {
                throw  new InsertException();
            }

            // 插入另一条记录，对方和我
            ChatList reverseList = new ChatList();
            reverseList.setMyId(commodity.getUserId());
            reverseList.setFriendId(userId);
            reverseList.setIsDelete(0);
            reverseList.setCreatedTime(new Date());
            reverseList.setUpdatedTime(new Date());
            int insert2 = chatListMapper.insert(reverseList);
            if (insert2 != 1) {
                throw new InsertException();
            }
        }

    }

    /**
     * 根据用户ID获取到所有聊天列表
     * @param userId 用户ID
     * @return 聊天列表
     */
    @Override
    public List<ChatListDTO> getByUserIdChatLists(Integer userId) {
        return  chatListMapper.getByUserIdChatLists(userId);

    }
}




