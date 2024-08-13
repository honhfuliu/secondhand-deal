package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 收货地址id为空异常处理
 */

public class ShippingAddressIDCannotBeEmptyException extends BaseException {
    public ShippingAddressIDCannotBeEmptyException() {
    }

    public ShippingAddressIDCannotBeEmptyException(String message) {
        super(message);
    }

    public ShippingAddressIDCannotBeEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShippingAddressIDCannotBeEmptyException(Throwable cause) {
        super(cause);
    }

    public ShippingAddressIDCannotBeEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
