package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 收藏商品不存在异常
 */

public class CollectCommodityNotExisted extends BaseException {
    public CollectCommodityNotExisted() {
    }

    public CollectCommodityNotExisted(String message) {
        super(message);
    }

    public CollectCommodityNotExisted(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectCommodityNotExisted(Throwable cause) {
        super(cause);
    }

    public CollectCommodityNotExisted(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
