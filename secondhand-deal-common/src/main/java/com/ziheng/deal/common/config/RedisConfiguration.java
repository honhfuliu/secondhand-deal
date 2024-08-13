package com.ziheng.deal.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 *  Redis 消息监听容器配置
 */

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(factory);

        //  container.setTaskExecutor(null);            // 设置用于执行监听器方法的 Executor
        //  container.setErrorHandler(null);            // 设置监听器方法执行过程中出现异常的处理器
        //  container.addMessageListener(null, null);   // 手动设置监听器 & 监听的 topic 表达式

        return container;
    }
}
