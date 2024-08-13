package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 收藏商品已存在异常处理类
 */

public class CollectCommodityExisted extends BaseException {
    public CollectCommodityExisted() {
    }

    public CollectCommodityExisted(String message) {
        super(message);
    }

    public CollectCommodityExisted(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectCommodityExisted(Throwable cause) {
        super(cause);
    }

    public CollectCommodityExisted(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
