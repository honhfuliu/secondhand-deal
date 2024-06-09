package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 商品不存在异常处理
 */
public class CommodityDoesNotExistException extends BaseException {
    public CommodityDoesNotExistException() {
    }

    public CommodityDoesNotExistException(String message) {
        super(message);
    }

    public CommodityDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommodityDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public CommodityDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
