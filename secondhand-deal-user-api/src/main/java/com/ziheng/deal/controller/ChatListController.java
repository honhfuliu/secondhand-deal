package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.ChatListDTO;
import com.ziheng.deal.common.domain.DTO.ChatUserMessageDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.ChatListService;
import com.ziheng.deal.db.service.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("chat")
@Tag(name = "用户聊天列表")
public class ChatListController {
    @Resource
    private ChatListService chatListService;

    @Resource
    private ChatMessageService chatMessageService;

    @Resource
    private TokenUtil tokenUtil;

    @Resource
    private HttpServletRequest httpServletRequest;

    @PostMapping()
    @Operation(summary = "添加用户聊天信息")
    public ResultJsonData<Void> addChat(@RequestBody HashMap<String,Integer> map){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);

        chatListService.addFriend(tokenInfo.getUId(), map.get("userId"));

        return ResultJsonData.success();
    }


    @GetMapping()
    @Operation(summary = "根据用户ID获取到所有的聊天列表信息")
    public ResultJsonData<List<ChatListDTO>> getAllChatLists(){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        List<ChatListDTO> lists = chatListService.getByUserIdChatLists(tokenInfo.getUId());
        return ResultJsonData.success(lists);
    }

    @GetMapping("info")
    @Operation(summary = "根据用户id和聊天用户ID获取到聊天信息")
    public ResultJsonData<List<ChatUserMessageDTO>> getUserChatInformation( @RequestParam Integer friendId){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        List<ChatUserMessageDTO> chatUserMessageDTOList = chatMessageService.getUserChatInformation(tokenInfo.getUId(), friendId);
        return ResultJsonData.success(chatUserMessageDTOList);
    }



}
