package com.ziheng.deal.common.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一格式返回
 */
@Data
@Accessors(chain = true)
public class ResultJsonData<T> {
    private Integer code;
    private String message;
    private T data;
    private Long timestmap;

    public ResultJsonData() {
        this.timestmap = System.currentTimeMillis();
    }

    //成功默认返回
    public static <T> ResultJsonData<T> success(T data) {
        ResultJsonData resultData = new ResultJsonData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultJsonData<T> success() {
        ResultJsonData resultData = new ResultJsonData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        return resultData;
    }

    //成功自定义返回message
    public static <T> ResultJsonData<T> CustomizeSuccess(Integer code, String massage, T data){
        ResultJsonData resultData = new ResultJsonData<>();
        resultData.setCode(code);
        resultData.setMessage(massage);
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultJsonData<T> CustomizeSuccess(Integer code, String massage){
        ResultJsonData resultData = new ResultJsonData<>();
        resultData.setCode(code);
        resultData.setMessage(massage);
        return resultData;
    }

    //失败自定义返回
    public static <T> ResultJsonData<T> fali(Integer code, String massge) {
        ResultJsonData resultJsonData = new ResultJsonData<>();
        resultJsonData.setCode(code);
        resultJsonData.setMessage(massge);
        return resultJsonData;
    }



}
