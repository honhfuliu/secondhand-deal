package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 邮箱不存在异常
 */
public class EmailDoesNotExistException extends BaseException {
    public EmailDoesNotExistException() {
    }

    public EmailDoesNotExistException(String message) {
        super(message);
    }

    public EmailDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public EmailDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
