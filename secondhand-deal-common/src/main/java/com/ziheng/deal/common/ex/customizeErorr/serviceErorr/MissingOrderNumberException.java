package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 订单号不能为空
 */

public class MissingOrderNumberException extends BaseException {
    public MissingOrderNumberException() {
    }

    public MissingOrderNumberException(String message) {
        super(message);
    }

    public MissingOrderNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingOrderNumberException(Throwable cause) {
        super(cause);
    }

    public MissingOrderNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
