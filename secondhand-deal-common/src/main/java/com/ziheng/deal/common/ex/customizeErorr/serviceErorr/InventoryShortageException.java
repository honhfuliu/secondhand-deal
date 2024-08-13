package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 商品库存不足异常处理
 */
public class InventoryShortageException extends BaseException {
    public InventoryShortageException() {
    }

    public InventoryShortageException(String message) {
        super(message);
    }

    public InventoryShortageException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryShortageException(Throwable cause) {
        super(cause);
    }

    public InventoryShortageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
