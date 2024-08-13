package com.ziheng.deal.common.ex.customizeErorr.serviceErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 聊天用户id字段不能为空异常处理
 */
public class ChatUserFieldCannotEmptyException extends BaseException {
    public ChatUserFieldCannotEmptyException() {
    }

    public ChatUserFieldCannotEmptyException(String message) {
        super(message);
    }

    public ChatUserFieldCannotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatUserFieldCannotEmptyException(Throwable cause) {
        super(cause);
    }

    public ChatUserFieldCannotEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
