package com.ziheng.deal.db.redis;


import com.ziheng.deal.db.service.TOrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * redis 过期 key 监听， 处理过期未支付的订单
 */

@Component
@Slf4j
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    @Autowired
    private TOrderService orderService;
    final static Logger logger = LoggerFactory.getLogger(KeyExpiredListener.class);
//    @Resource
//    private ExecutorService executorService; // 异步执行线程池


    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void doHandleMessage(Message message) {

        // 过期的 key
        byte[] body = message.getBody();

        // 消息通道
        byte[] channel = message.getChannel();
        logger.info("message = {}, channel = {}", new String(body), new String(channel));

        // 处理过期的订单
        Pattern pattern = Pattern.compile("\\d{6}-\\d+");
        Matcher matcher = pattern.matcher(new String(body));

        if (matcher.matches()) {
            System.out.println("运行订单取消");
            orderService.ExpiredCommodityOrderSolve(new String(body));
            // 处理过期订单逻辑
        } else {
            System.out.println("未运行订单取消");
        }


    }

//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        String expiredKey = message.toString();
//
//        log.info("过期消息key:{}", expiredKey);
//    }





}
