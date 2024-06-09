package com.ziheng.deal.common.resp;

import lombok.Getter;

/**
 * 枚举类，用于 由后端统一定义各种返回结果的状态码和本次接口调用的结果描述
 */

@Getter
public enum ReturnCodeEnum {
    /**操作成功**/
    RC200(200,"success"),
    /**邮箱已被注册**/
    RC5001(5001,"该邮箱已被注册"),
    RC5002(5002,"发送电子邮件异常请于管理员联系"),
    RC5003(5003,"验证码错误"),
    RC5004(5004,"账号名称已存在"),
    RC5005(5005,"验证码已过期"),
    RC5006(5006,"用户数据不存在或该账号已被冻结，请与管理员进行联系"),
    RC5007(5007,"邮箱数据不存在或该账号已被冻结，请与管理员进行联系"),
    RC5008(5008,"用户未登录"),
    RC5009(5009,"分类名称已存在"),
    RC5010(5010,"父级id不存在"),
    RC5011(5011,"分类不存在"),
    RC5012(5012,"商品不存在"),


    RC3001(3001,"文件不能为空"),
    RC3002(3002,"文件超过指定大小范围"),
    RC3003(3003,"文件类型错误"),



    RC4001(4001,"插入数据时产生未知的异常，请与管理员联系"),
    RC4002(4002,"修改数据时产生未知的异常，请与管理员联系"),
    RC4003(4003,"删除数据时产生未知的异常，请与管理员联系"),

    INVALID_TOKEN(2001,"访问令牌不合法"),
    RC2002(2002, "令牌已过期"),
    RC2004(2004, "签名不一致异常"),
    RC2005(2005, "字段不能为空"),
    ACCESS_DENIED(2003,"没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED(1001,"客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误");

    /**自定义状态码**/
    private final Integer code;
    /**自定义描述**/
    private final String message;

    ReturnCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    //遍历枚举V1
    public static ReturnCodeEnum getReturnCodeEnum(Integer code)
    {
        for (ReturnCodeEnum element : ReturnCodeEnum.values()) {
            if(element.getCode().equals(code))
            {
                return element;
            }
        }
        return null;
    }



}
