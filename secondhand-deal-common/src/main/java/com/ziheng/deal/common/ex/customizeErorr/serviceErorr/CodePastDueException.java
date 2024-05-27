package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

// 验证码过期异常
public class CodePastDueException extends BaseException {
    public CodePastDueException() {
    }

    public CodePastDueException(String message) {
        super(message);
    }

    public CodePastDueException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodePastDueException(Throwable cause) {
        super(cause);
    }

    public CodePastDueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
