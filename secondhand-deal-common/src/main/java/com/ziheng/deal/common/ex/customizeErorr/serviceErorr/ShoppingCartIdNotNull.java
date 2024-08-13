package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 购物车id为空异常处理
 */
public class ShoppingCartIdNotNull extends BaseException {
    public ShoppingCartIdNotNull() {
    }

    public ShoppingCartIdNotNull(String message) {
        super(message);
    }

    public ShoppingCartIdNotNull(String message, Throwable cause) {
        super(message, cause);
    }

    public ShoppingCartIdNotNull(Throwable cause) {
        super(cause);
    }

    public ShoppingCartIdNotNull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
