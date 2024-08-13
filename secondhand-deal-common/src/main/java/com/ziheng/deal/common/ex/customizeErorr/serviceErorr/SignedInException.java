package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 *  用于已签到异常处理
 */

public class SignedInException extends BaseException {
    public SignedInException() {
    }

    public SignedInException(String message) {
        super(message);
    }

    public SignedInException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignedInException(Throwable cause) {
        super(cause);
    }

    public SignedInException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
