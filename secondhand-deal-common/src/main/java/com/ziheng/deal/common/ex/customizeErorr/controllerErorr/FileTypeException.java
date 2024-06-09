package com.ziheng.deal.common.ex.customizeErorr.controllerErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

// 文件类型错误异常处理
public class FileTypeException extends BaseException {
    public FileTypeException() {
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
