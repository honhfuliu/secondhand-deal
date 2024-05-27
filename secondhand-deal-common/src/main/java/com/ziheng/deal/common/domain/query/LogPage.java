package com.ziheng.deal.common.domain.query;

import lombok.Data;

/**
 * 日志分页查询 条件
 */

public class LogPage extends PageQueryBase{
    private String exception; // 根据字段查找不为空的数据

    public LogPage(String exception) {
        this.exception = exception;
    }

    public LogPage() {
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
