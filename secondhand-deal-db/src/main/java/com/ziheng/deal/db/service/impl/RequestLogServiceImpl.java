package com.ziheng.deal.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ziheng.deal.common.domain.IpInfo;
import com.ziheng.deal.common.logService.RequestLogService;
import com.ziheng.deal.common.util.LogUtil;
import com.ziheng.deal.db.entity.TLog;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RequestLogServiceImpl implements RequestLogService {
    @Resource
    private TLogServiceImpl logServiceimpl;

    @Resource
    private LogUtil logUtil;

    /**
     * 日志信息获取存储
     * @param requestUrl 请求url
     * @param requestMethod 请求方法
     * @param ipAddress 请求地址
     * @param exInfo 异常信息
     * @param classMethod 改请求处理方法
     * @param isaxios 是否是axios请求
     * @param browser 客户端使用的浏览器
     */
    @Override
    @Async
    public void saveRequestLog(String requestUrl, String requestMethod, String browser, String ipAddress, String exInfo, String classMethod, Boolean isaxios) {
        // 获取IP地址信息

        IpInfo ipInfo = new IpInfo();
        if (!ipAddress.equals("127.0.0.1")) {
            ipInfo = logUtil.LocationAcquisition(ipAddress);

        }

        TLog tLog = new TLog();
        if (ipInfo != null) {
            BeanUtil.copyProperties(ipInfo, tLog);
            tLog.setIsp(ipInfo.getAddr());
        }
        tLog.setRemoteAddr(ipAddress);
        tLog.setRequestUrl(requestUrl);
        tLog.setHttpMethod(requestMethod);
        tLog.setClassMethod(classMethod);
        tLog.setBrowser(browser);
        if (isaxios) {
            tLog.setType("Axios请求");
        } else{
            tLog.setType("普通请求");
        }
        tLog.setException(exInfo);

        // 补全日志
        tLog.setCreateTime(new Date());
        tLog.setUpdateTime(new Date());

        logServiceimpl.insertLog(tLog);
    }
}
