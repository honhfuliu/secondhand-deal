package com.ziheng.deal.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 手动获取 Spring 管理的 Bean 对象(自动注入不了使用)
 */
@Component
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }
}
