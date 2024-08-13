package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 购物车信息不存在异常
 */
public class ShoppingCartInfoNull extends BaseException {
    public ShoppingCartInfoNull() {
    }

    public ShoppingCartInfoNull(String message) {
        super(message);
    }

    public ShoppingCartInfoNull(String message, Throwable cause) {
        super(message, cause);
    }

    public ShoppingCartInfoNull(Throwable cause) {
        super(cause);
    }

    public ShoppingCartInfoNull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
