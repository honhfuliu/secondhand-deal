package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

// 父级id不存在异常
public class ParentIdExistentException extends BaseException {
    public ParentIdExistentException() {
    }

    public ParentIdExistentException(String message) {
        super(message);
    }

    public ParentIdExistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParentIdExistentException(Throwable cause) {
        super(cause);
    }

    public ParentIdExistentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
