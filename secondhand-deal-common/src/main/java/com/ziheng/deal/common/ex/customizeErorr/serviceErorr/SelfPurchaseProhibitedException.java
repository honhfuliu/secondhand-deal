package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 禁止购买自己的商品异常处理类
 */
public class SelfPurchaseProhibitedException extends BaseException {
    public SelfPurchaseProhibitedException() {
    }

    public SelfPurchaseProhibitedException(String message) {
        super(message);
    }

    public SelfPurchaseProhibitedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelfPurchaseProhibitedException(Throwable cause) {
        super(cause);
    }

    public SelfPurchaseProhibitedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
