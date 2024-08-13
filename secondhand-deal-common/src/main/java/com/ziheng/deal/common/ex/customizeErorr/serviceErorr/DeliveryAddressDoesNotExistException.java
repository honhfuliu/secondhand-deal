package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 收货地址不存在异常处理对象
 */
public class DeliveryAddressDoesNotExistException extends BaseException {
    public DeliveryAddressDoesNotExistException() {
    }

    public DeliveryAddressDoesNotExistException(String message) {
        super(message);
    }

    public DeliveryAddressDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryAddressDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public DeliveryAddressDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
