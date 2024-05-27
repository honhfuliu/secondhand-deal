package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

// 分类名称存在异常
public class ClassificationNameExistsException extends BaseException {
    public ClassificationNameExistsException() {
    }

    public ClassificationNameExistsException(String message) {
        super(message);
    }

    public ClassificationNameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassificationNameExistsException(Throwable cause) {
        super(cause);
    }

    public ClassificationNameExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
