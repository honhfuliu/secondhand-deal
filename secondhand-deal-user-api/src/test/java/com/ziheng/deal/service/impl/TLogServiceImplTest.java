package com.ziheng.deal.service.impl;

import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.query.LogPage;
import com.ziheng.deal.db.entity.TLog;
import com.ziheng.deal.db.service.TLogService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TLogServiceImplTest {
    @Resource
    TLogService logService;
    @Test
    void test01(){
        LogPage logPage = new LogPage();
        logPage.setException(null);
        logPage.setPageNo(1);
        logPage.setPageSize(5);
        PageDOT<TLog> dot = logService.PageLogWhereQuery(logPage);
        System.out.println(dot);

    }

    @Test
    void test02(){
        LogPage logPage = new LogPage();
        logPage.setException("exception");
        logPage.setPageNo(1);
        logPage.setPageSize(5);
        PageDOT<TLog> dot = logService.PageLogWhereQuery(logPage);
        System.out.println(dot);

    }
}
