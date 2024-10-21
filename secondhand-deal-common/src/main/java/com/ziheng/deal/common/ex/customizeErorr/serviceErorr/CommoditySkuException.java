package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 商品sku错误异常处理
 */

public class CommoditySkuException extends BaseException {
    public CommoditySkuException() {
    }

    public CommoditySkuException(String message) {
        super(message);
    }

    public CommoditySkuException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommoditySkuException(Throwable cause) {
        super(cause);
    }

    public CommoditySkuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
