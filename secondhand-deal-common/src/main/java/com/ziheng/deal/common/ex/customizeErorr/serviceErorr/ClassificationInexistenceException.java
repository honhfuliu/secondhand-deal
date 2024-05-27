package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

// 分类不存在异常
public class ClassificationInexistenceException extends BaseException {
    public ClassificationInexistenceException() {
    }

    public ClassificationInexistenceException(String message) {
        super(message);
    }

    public ClassificationInexistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassificationInexistenceException(Throwable cause) {
        super(cause);
    }

    public ClassificationInexistenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
