package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 商品不存在购物车中异常处理
 */

public class ProductNotInCartException extends BaseException {
    public ProductNotInCartException() {
    }

    public ProductNotInCartException(String message) {
        super(message);
    }

    public ProductNotInCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotInCartException(Throwable cause) {
        super(cause);
    }

    public ProductNotInCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
