package com.ziheng.deal.common.ex.customizeErorr.controllerErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

//文件状态异常

public class FileStateException extends BaseException {
    public FileStateException() {
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
