package com.ziheng.deal.common.config;

import com.ziheng.deal.common.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * 拦截器白名单配置类
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> WhitelistPaths = Arrays.asList(
                "/reg/**/**",
                "/upload/**",
                "/commodity/show", // 首页商品展示
                "/reg/recover/code/v",
                "/admin/**",
                "/error",
                "/favicon.ico",
                "/user/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/v3/**",
                "/swagger-ui.html/**",
                "/api",
                "/api-docs",
                "/api-docs/**",
                "/doc.html/**",
                "/doc.html#/**"
        );
        /*
        *在Spring MVC中，通过.addInterceptor(loginInterceptor)添加拦截器时，应该注入的是已经由Spring管理的拦截器对象，
        * 而不是通过new关键字创建的新对象。这是因为Spring负责管理Bean的生命周期和依赖注入，如果手动使用new关键字创建拦截器对象，
        * Spring无法对其进行管理和注入，导致拦截器中的依赖无法正确注入，从而出现空指针异常或其他问题。
        * */
        registry.addInterceptor(loginInterceptor).excludePathPatterns(WhitelistPaths).addPathPatterns("/**");
    }
}
