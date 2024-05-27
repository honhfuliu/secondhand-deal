package com.ziheng.deal.common.util;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.ziheng.deal.common.domain.IpInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日志中需要使用到的工具类
 */
@Component
public class LogUtil {

    /**
     * 客户端ip地址获取
     * @param request 请求数据
     * @return ip地址
     */
    public String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    /**
     * 客户端使用浏览器获取
     * @param request
     * @return
     */
    public String browser(HttpServletRequest request) {
        String browser = request.getHeader("User-Agent");
        if (browser == null || browser.isEmpty() || "unknown".equalsIgnoreCase(browser)) {
            browser = request.getHeader("sec-ch-ua");
        }
        return browser;
    }

    /**
     * 客户端地理位置获取
     */
    @Resource
    private Gson gson;
    public IpInfo LocationAcquisition(String ip){
        //1. 创建客户端
        WebClient client = WebClient.builder()
                .baseUrl("https://whois.pconline.com.cn")
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer
                            .defaultCodecs()
                            .maxInMemorySize(265 * 1024 * 1024);
                }).build();
        // 构建完整的URI并添加查询参数
        String uri = "/ipJson.jsp?ip=" + ip +"&json=true";
        String stringData = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
//        Mono<String> stringMono = client.get()
//                .uri(uri)
//                .retrieve()
//                .bodyToMono(String.class);

        /*
        TimeUnit.SECONDS.sleep(2);
        // 使用 CountDownLatch 来等待异步操作完成
        CountDownLatch latch = new CountDownLatch(2);

        // 原子操作获取lambda中处理的值
        AtomicReference<String> processedJsonRef = new AtomicReference<>("");

        String[] RedultJson = new String[1];

        stringMono.subscribe(
                json -> {
                    RedultJson[0] = json;
                    System.out.println("util:" + json);
                    // Process the received JSON string asynchronously
                    processedJsonRef.set(json);
                    // Perform any further processing or operations
                },
                error -> {
                    // 异常处理
                    if (error instanceof Exception) {
                        throw new RuntimeException();
                    }
                }
        );*/
//        System.out.println("获取字符串：" + processedJsonRef);
//        String json = extractString(stringMono.block());
//        IpInfo ipInfo = gson.fromJson(processedJsonRef.get(), IpInfo.class);

        IpInfo ipInfo = gson.fromJson(stringData, IpInfo.class);
        return ipInfo;
    }



    public String extractString(String jsonData) {
        // 正则表达式匹配 IPCallBack(...) 中的 JSON 字符串
        String regex = "IPCallBack\\(([^)]*)\\)"; // 匹配从 IPCallBack( 开始到第一个 ) 结束的内容

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jsonData);

        String stringData = null;
        if (matcher.find()) {
            // 获取匹配到的 JSON 字符串，包括大括号
            stringData = matcher.group(1);

        } else {
            stringData = "No match found.";
        }
        return stringData;
    }


    /**
     * 判断请求是否是ajax请求
     * @param request
     * @return
     */
    public boolean isAjax(HttpServletRequest request){
        String accept = request.getHeader("accept");
        return accept != null && accept.contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }
}
