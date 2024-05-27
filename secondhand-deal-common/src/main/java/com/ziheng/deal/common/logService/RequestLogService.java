package com.ziheng.deal.common.logService;


//在公共模块中定义RequestLogService接口，包含抽象方法用于操作请求接口信息，例如存储请求日志到数据库
public interface RequestLogService {
    void saveRequestLog(String requestUrl, String requestMethod, String browser,String ipAddress, String exInfo, String classMethod, Boolean isaxios) throws InterruptedException;
}
