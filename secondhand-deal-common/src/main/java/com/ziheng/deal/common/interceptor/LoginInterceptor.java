package com.ziheng.deal.common.interceptor;

import com.ziheng.deal.common.domain.IpInfo;
import com.ziheng.deal.common.ex.customizeErorr.TokenIllegalException;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.NotLoginException;
import com.ziheng.deal.common.logService.RequestLogService;
import com.ziheng.deal.common.util.LogUtil;
import com.ziheng.deal.common.util.TokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器配置
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RequestLogService requestLogService; // 注入RequestLogService接口

    @Resource
    private LogUtil logUtil;

    @Resource
    private TokenUtil tokenUtil;

    // 方法处理前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("拦截路径是：" + request.getRequestURI());
        // 通过所有OPTION请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//            System.out.println("OPTIONS请求，放行");
            return true;
        }

        String token = request.getHeader("token");
        if (token == null) {
            throw new NotLoginException("用户未登录");
        }
        boolean verifyToken = tokenUtil.verifyToken(token);
        if (!verifyToken) {
            throw new TokenIllegalException("令牌不合法");
        }
        return true;

    }

    /// 数据返回前拦截
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request.getMethod().equals("OPTIONS")){
            return;
        }
        // 获取请求信息
        String requestUrl = request.getRequestURI();
        String requestMethod = request.getMethod();
        String ip = logUtil.getClientIpAddress(request);
        if("0.0.0.0".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "localhost".equals(ip) || "127.0.0.1".equals(ip)){
            ip = "127.0.0.1";
        }

        // 获取浏览器
        String browser = logUtil.browser(request);

        String exInfo = null;
        // 获取异常信息
        if(ex != null) {
            exInfo = ex.getMessage();
        }


        String fullMethodName = null;
        // 获取请求路径的全包名
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String packageName = handlerMethod.getBeanType().getName(); // 获取处理方法所在类的全包名
            String methodName = handlerMethod.getMethod().getName(); // 获取处理方法的方法名

            fullMethodName = packageName + "." + methodName;

        }

        // 是否是axios请求判断
        boolean ajax = logUtil.isAjax(request);

//        System.out.println("是否是axios请求：" + ajax);
//
//        System.out.println("请求路径：" +requestUrl);
//        System.out.println("请求方法：" + requestMethod);
//        System.out.println("host:" + ip);
//        System.out.println("异常信息：" + exInfo);
//        System.out.println("请求浏览器：" + browser);


        // 使用RequestLogService接口记录请求日志
        requestLogService.saveRequestLog(requestUrl, requestMethod, browser, ip, exInfo, fullMethodName, ajax);
    }



}
