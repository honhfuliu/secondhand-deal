package com.ziheng.deal;

import com.ziheng.deal.common.logService.RequestLogService;
import com.ziheng.deal.db.service.impl.RequestLogServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.ziheng.deal.admin", "com.ziheng.deal.db", "com.ziheng.deal.common", "com.ziheng.deal"})
@MapperScan("com.ziheng.deal.db.mapper")
public class DealUserApiMain {
    public static void main(String[] args) {
        SpringApplication.run(DealUserApiMain.class, args);
    }

//    @Bean
//    public RequestLogService requestLogService() {
//        return new RequestLogServiceImpl();
//    }
}