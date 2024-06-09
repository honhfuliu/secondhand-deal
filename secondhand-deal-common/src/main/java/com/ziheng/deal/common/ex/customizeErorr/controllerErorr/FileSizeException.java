package com.ziheng.deal.common.ex.customizeErorr.controllerErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

// 文件大小错误异常处理
public class FileSizeException extends BaseException {
    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
