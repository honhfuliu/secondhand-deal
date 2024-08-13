package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 收藏商品id不能为空异常处理
 */

public class CollectCommodityIdNotNull extends BaseException {
    public CollectCommodityIdNotNull() {
    }

    public CollectCommodityIdNotNull(String message) {
        super(message);
    }

    public CollectCommodityIdNotNull(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectCommodityIdNotNull(Throwable cause) {
        super(cause);
    }

    public CollectCommodityIdNotNull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
