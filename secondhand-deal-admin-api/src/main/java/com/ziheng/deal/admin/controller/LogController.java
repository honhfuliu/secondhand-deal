package com.ziheng.deal.admin.controller;

import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.query.LogPage;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.db.entity.TLog;
import com.ziheng.deal.db.service.TLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
@Tag(name = "日志管理模块")
public class LogController {

    @Resource
    private TLogService logService;

    /**
     * 分页条件日志查询
     * @param pageQueryBase 页数  每页数量  排序字段
     * @return
     */
    @GetMapping()
    public ResultJsonData<PageDOT<TLog>> PageLogWhereQuery(LogPage pageQueryBase){
        PageDOT<TLog> dot = logService.PageLogWhereQuery(pageQueryBase);
        return ResultJsonData.success(dot);
    }

}
