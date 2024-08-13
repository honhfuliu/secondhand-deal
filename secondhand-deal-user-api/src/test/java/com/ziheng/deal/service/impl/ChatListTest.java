package com.ziheng.deal.service.impl;

import com.ziheng.deal.common.domain.DTO.ChatListDTO;
import com.ziheng.deal.db.mapper.ChatListMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ChatListTest {
    @Resource
    private ChatListMapper chatListMapper;


    @Test
    void test01(){
        List<ChatListDTO> lists = chatListMapper.getByUserIdChatLists(2);
        for (ChatListDTO list : lists) {
            System.out.println(list);
        }
    }
}
