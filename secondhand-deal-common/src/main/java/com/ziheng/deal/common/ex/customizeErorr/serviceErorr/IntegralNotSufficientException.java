package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 积分不足异常处理
 */
public class IntegralNotSufficientException extends BaseException {
    public IntegralNotSufficientException() {
    }

    public IntegralNotSufficientException(String message) {
        super(message);
    }

    public IntegralNotSufficientException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegralNotSufficientException(Throwable cause) {
        super(cause);
    }

    public IntegralNotSufficientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
