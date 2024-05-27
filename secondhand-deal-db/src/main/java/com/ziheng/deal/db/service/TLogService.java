package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.query.LogPage;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import com.ziheng.deal.db.entity.TLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_log】的数据库操作Service
* @createDate 2024-04-20 17:28:13
*/
public interface TLogService extends IService<TLog> {
    //日志的写入
    void insertLog(TLog log);

    // 分页查询日志
    PageDOT<TLog> PageLogWhereQuery(LogPage pageQueryBase);
}
