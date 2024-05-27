package com.ziheng.deal.common.ex.customizeErorr;

// 发送电子邮件异常处理
public class EmailMessagingException extends BaseException{
    public EmailMessagingException() {
    }

    public EmailMessagingException(String message) {
        super(message);
    }

    public EmailMessagingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailMessagingException(Throwable cause) {
        super(cause);
    }

    public EmailMessagingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
