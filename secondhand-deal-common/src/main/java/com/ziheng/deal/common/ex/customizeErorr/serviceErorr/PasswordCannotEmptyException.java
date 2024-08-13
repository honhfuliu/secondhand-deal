package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 密码不能为空异常处理
 */

public class PasswordCannotEmptyException extends BaseException {
    public PasswordCannotEmptyException() {
    }

    public PasswordCannotEmptyException(String message) {
        super(message);
    }

    public PasswordCannotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordCannotEmptyException(Throwable cause) {
        super(cause);
    }

    public PasswordCannotEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
