package com.ziheng.deal.common.ex.customizeErorr;

/**
 * 令牌不合法异常
 */
public class TokenIllegalException extends BaseException{
    public TokenIllegalException() {
    }

    public TokenIllegalException(String message) {
        super(message);
    }

    public TokenIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenIllegalException(Throwable cause) {
        super(cause);
    }

    public TokenIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
