package com.ziheng.deal.sms;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import com.ziheng.deal.common.domain.IpInfo;
import com.ziheng.deal.common.util.LogUtil;
import com.ziheng.deal.common.util.SendEmailUtil;
import com.ziheng.deal.common.util.SmsVerificationUtil;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

//import javax.net.ssl.KeyManager;
//import javax.net.ssl.X509TrustManager;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class smsTest {
    @Test
    void aliyunSmdTest() throws ExecutionException, InterruptedException {
        // HttpClient Configuration
        /*HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                .maxConnections(128) // Set the connection pool size
                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                // Configure the proxy
                .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
                        .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
                // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
                .x509TrustManagers(new X509TrustManager[]{})
                .keyManagers(new KeyManager[]{})
                .ignoreSSL(false)
                .build();*/

        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
//                .accessKeyId(System.getenv("LTAI5tNgW6wvNViEJVBmHEeM"))
//                .accessKeySecret(System.getenv("aScZFGsSjJOPT7Bq4Y6uIaWR3FWXaA"))
                .accessKeyId("LTAI5tNgW6wvNViEJVBmHEeM")
                .accessKeySecret("aScZFGsSjJOPT7Bq4Y6uIaWR3FWXaA")
                //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("阿里云短信测试")
                .templateCode("SMS_154950909")
                .phoneNumbers("18787629716")
                .templateParam("{\"code\":\"6666\"}")
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/

        // Finally, close the client
        client.close();

    }



    @Autowired
    private SmsVerificationUtil smsVerificationUtil;

    @Value("${sms.register.signName}")
    String signName;

    @Value("${sms.register.templateCode}")
    String templateCode;

    @Test
    void test02() throws ExecutionException, InterruptedException {
        System.out.println(signName);
        System.out.println(templateCode);
        String s = smsVerificationUtil.SendSms("18869700309", signName, templateCode);
        System.out.println(s);

    }


    @Test
    void test03(){
        /*
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            builder.append(random.nextInt(10));
        }
        System.out.println(builder);*/

        String builder = "6584";
        String code = "{\"code\":\"6666\"}";
        code = code.replace("6666", builder);
        System.out.println(code);
    }

    @Test
    void test04(){
        int a = 10/0;
    }

    @Resource
    SendEmailUtil sendEmailUtil;
    @Test
    void test05() throws MessagingException {
        String s = sendEmailUtil.SendEmail("1577272812@qq.com");
        System.out.println(s);
    }

    @Test
    void test06() {
        byte[] key = "zxcvbnmasdf".getBytes();
        String token = JWT.create()
                .setPayload("username", "ziheng")
                .setPayload("age", 18)
                .setPayload("sex","n")
                .setKey(key)
                .sign();

        System.out.println(token);
    }

    @Test
    void test07(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InppaGVuZyIsImFnZSI6MTgsInNleCI6Im4ifQ.eDi_wKqy-5Ex-lBNuuh4YYnS-r0kSYHNfJ2UJxxkUZU";
        byte[] key = "zxcvbnmasdf".getBytes();
        JWT jwt = JWT.of(token);
        System.out.println(jwt.getHeader(JWTHeader.TYPE));
        System.out.println(jwt.getHeader(JWTHeader.ALGORITHM));
        System.out.println(jwt.getPayload("username"));


    }


    @Resource
    private LogUtil logUtil;
    @Test
    void test08(){
        IpInfo ipInfo = logUtil.LocationAcquisition("180.137.111.52");
        System.out.println(ipInfo);
    }

    @Test
    void test09(){
        String input = "if(window.IPCallBack) {IPCallBack({\"ip\":\"180.137.111.52\",\"pro\":\"广西\",\"proCode\":\"450000\",\"city\":\"北海市\",\"cityCode\":\"450500\",\"region\":\"\",\"regionCode\":\"0\",\"addr\":\"广西北海市 电信\",\"regionNames\":\"\",\"err\":\"\"});}";

        // 正则表达式匹配 IPCallBack(...) 中的 JSON 字符串
        String regex = "IPCallBack\\(([^)]*)\\)"; // 匹配从 IPCallBack( 开始到第一个 ) 结束的内容

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            // 获取匹配到的 JSON 字符串，包括大括号
            String jsonWithBrackets = matcher.group(1);

            // 去除大括号，得到纯净的 JSON 字符串
//            String json = jsonWithBrackets.substring(1, jsonWithBrackets.length() - 1);

            System.out.println(jsonWithBrackets); // 输出纯净的 JSON 字符串
        } else {
            System.out.println("No match found.");
        }
    }
}
