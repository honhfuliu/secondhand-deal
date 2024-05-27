package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 用户数据不存在异常处理
 */
public class UserDataDoesNotExistException extends BaseException {
    public UserDataDoesNotExistException() {
    }

    public UserDataDoesNotExistException(String message) {
        super(message);
    }

    public UserDataDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDataDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public UserDataDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
