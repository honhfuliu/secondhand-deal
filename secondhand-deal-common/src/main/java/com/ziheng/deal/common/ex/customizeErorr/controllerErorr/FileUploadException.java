package com.ziheng.deal.common.ex.customizeErorr.controllerErorr;

import com.ziheng.deal.common.ex.customizeErorr.BaseException;

/**
 * 上传文件错误异常处理
 */

public class FileUploadException extends BaseException {
    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
