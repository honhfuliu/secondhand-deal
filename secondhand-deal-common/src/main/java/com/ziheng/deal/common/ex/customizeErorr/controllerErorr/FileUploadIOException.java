package com.ziheng.deal.common.ex.customizeErorr.controllerErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

//文件读写异常

public class FileUploadIOException extends BaseException {
    public FileUploadIOException() {
    }

    public FileUploadIOException(String message) {
        super(message);
    }

    public FileUploadIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIOException(Throwable cause) {
        super(cause);
    }

    public FileUploadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
