package com.ziheng.deal.common.config;

import com.ziheng.deal.common.util.TokenUtil;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * websocket 握手请求信息获取
 */

@Configuration
public class GetHttpRequestHeadersConfig extends ServerEndpointConfig.Configurator {
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        Map<String, List<String>> parameterMap = request.getParameterMap();
        List<String> list = parameterMap.get("token");
        sec.getUserProperties().put("token", list.get(0));

    }
}
