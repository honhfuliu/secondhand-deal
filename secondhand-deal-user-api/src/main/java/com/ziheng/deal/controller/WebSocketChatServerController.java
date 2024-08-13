package com.ziheng.deal.controller;

import cn.hutool.json.JSONUtil;
import com.ziheng.deal.common.config.GetHttpRequestHeadersConfig;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.ChatVO;
import com.ziheng.deal.common.util.SpringContext;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.ChatMessageService;
import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户聊天服务类
 */


@ServerEndpoint(value = "/chatServer", configurator = GetHttpRequestHeadersConfig.class)
@Controller
public class WebSocketChatServerController {


    private static final Logger logger = LoggerFactory.getLogger(WebSocketChatServerController.class);


    // 这里为什么使用的是常量，是因为Endpoint是多例的，
    // 每一个客户端连接，都会创建一个新的，那么就会出现每一个实例都会有一个map集合，
    // 这里想要的是所有的实例使用的都是同一个map集合，那么就只能使用常量
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();


    private String token;

    // 获取tokenUtil工具类
    private TokenUtil tokenUtil = SpringContext.getBean(TokenUtil.class);



    /**
     * 建立webSocket连接后被调用
     * @param session
     */
    @OnOpen
    public void OnOpen(Session session, EndpointConfig endpointConfig){

        // 将session保存
        // 获取到用户token中的信息
        this.token = (String) endpointConfig.getUserProperties().get("token");

        TokenInfo tokenTuser = tokenUtil.getTokenTuser(this.token);
        System.out.println(tokenTuser.getUsername());

        onlineUsers.put(tokenTuser.getUsername(), session);


        System.out.println(tokenTuser.getUsername() + "连接成功");

    }

    /**
     * 浏览器发送消息到服务端，该方法会调用
     * @param message 信息
     * @throws IOException
     */
    @OnMessage
    public void OnMessage(String message) throws IOException {
        System.out.println("收到消息" + message);

        // 将消息推送给指定的用户
        ChatVO vo = JSONUtil.toBean(message, ChatVO.class);

        // 获取到用户token中的信息
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(this.token);
        // 将消息存储
        ChatMessageService chatMessageService = SpringContext.getBean(ChatMessageService.class);

        chatMessageService.userChatMessageSave(tokenTuser.getUId(),vo);

        System.out.println(vo.toString());
        // 获取消息接收方的用户的session
        String name = vo.getName();
        Session session1 = onlineUsers.get(name);
        session1.getBasicRemote().sendText(vo.getMessage());
    }

    // 关闭链接
    @OnClose
    public void OnClose(Session session) {
        // 从onlineUsers集合中删除当前用户的session信息
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);
        onlineUsers.remove(tokenTuser.getUsername());
        System.out.println(tokenTuser.getUsername() + "关闭连接");

    }

//    // 连击异常处理
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        // 处理错误
//        System.out.println("Error: " + session.getId() + ", " + throwable.getMessage());
//        throwable.printStackTrace();
//    }


}
