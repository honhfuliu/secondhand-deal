package com.ziheng.deal.common.ex.global;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ziheng.deal.common.ex.customizeErorr.EmailMessagingException;
import com.ziheng.deal.common.ex.customizeErorr.TokenIllegalException;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.resp.ReturnCodeEnum;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

//    @ExceptionHandler(ArithmeticException.class)
//    public ResultJsonData bbb() {
//        return ResultJsonData.fali(600,"傻逼por");
//    }
    // 删除数据时异常处理
    @ExceptionHandler(DeleteException.class)
    public ResultJsonData<Void> deleteException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC4003.getCode(), ReturnCodeEnum.RC4003.getMessage());
    }

    // 分类不存在异常
    @ExceptionHandler(ClassificationInexistenceException.class)
    public ResultJsonData<Void> classificationInexistenceException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5011.getCode(), ReturnCodeEnum.RC5011.getMessage());
    }


    // 父级不存在异常
    @ExceptionHandler(ParentIdExistentException.class)
    public ResultJsonData<Void> parentIdExistentException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5010.getCode(), ReturnCodeEnum.RC5010.getMessage());
    }

    // 分类名称存在异常
    @ExceptionHandler(ClassificationNameExistsException.class)
    public ResultJsonData<Void> classificationNameExistsException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5009.getCode(), ReturnCodeEnum.RC5009.getMessage());
    }


    // 字段不能为空异常
    @ExceptionHandler(TooManyResultsException.class)
    public ResultJsonData<Void> TooManyResultsException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC2005.getCode(), ReturnCodeEnum.RC2005.getMessage());
    }


    // 修改数据异常处理
    @ExceptionHandler(UpdateException.class)
    public ResultJsonData<Void> updateException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC4002.getCode(), ReturnCodeEnum.RC4002.getMessage());
    }


    // 用户未登录异常处理
    @ExceptionHandler(NotLoginException.class)
    public ResultJsonData notLoginException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5008.getCode(), ReturnCodeEnum.RC5008.getMessage());
    }

    // 邮箱不存在异常
    @ExceptionHandler(EmailDoesNotExistException.class)
    public ResultJsonData emailDoesNotExistException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5007.getCode(), ReturnCodeEnum.RC5007.getMessage());
    }

    // 密码错误异常
    @ExceptionHandler(PasswordNotMatchException.class)
    public ResultJsonData passwordNotMatchException(){
        return ResultJsonData.fali(ReturnCodeEnum.USERNAME_OR_PASSWORD_ERROR.getCode(), ReturnCodeEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
    }


    // 用户数据不存在异常
    @ExceptionHandler(UserDataDoesNotExistException.class)
    public ResultJsonData userDataDoesNotExistException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5006.getCode(), ReturnCodeEnum.RC5006.getMessage());
    }

    // 令牌不合法异常处理
    @ExceptionHandler(TokenIllegalException.class)
    public ResultJsonData tokenIllegalException(){
        return ResultJsonData.fali(ReturnCodeEnum.INVALID_TOKEN.getCode(), ReturnCodeEnum.INVALID_TOKEN.getMessage());
    }


    //签名不一致异常
    @ExceptionHandler(SignatureVerificationException.class)
    public ResultJsonData signatureVerificationException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC2004.getCode(), ReturnCodeEnum.RC2004.getMessage());
    }

    // token 令牌过期异常
    @ExceptionHandler(TokenExpiredException.class)
    public ResultJsonData okenExpiredException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC2002.getCode(), ReturnCodeEnum.RC2002.getMessage());
    }

    // token 错误异常处理
    @ExceptionHandler(JWTDecodeException.class)
    public ResultJsonData jWTDecodeException(){
        return ResultJsonData.fali(ReturnCodeEnum.INVALID_TOKEN.getCode(), ReturnCodeEnum.INVALID_TOKEN.getMessage());
    }

    // 验证码过期异常
    @ExceptionHandler(CodePastDueException.class)
    public ResultJsonData codePastDueException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5005.getCode(), ReturnCodeEnum.RC5005.getMessage());
    }

    // 插入数据异常
    @ExceptionHandler(InsertException.class)
    public ResultJsonData insertException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC4001.getCode(), ReturnCodeEnum.RC4001.getMessage());
    }

    // 用户名已存在异常
    @ExceptionHandler(UsernameDuplicateException.class)
    public ResultJsonData usernameMessagingException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5004.getCode(), ReturnCodeEnum.RC5004.getMessage());
    }

    // 验证码错误
    @ExceptionHandler(CodeException.class)
    public ResultJsonData codeException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5003.getCode(), ReturnCodeEnum.RC5003.getMessage());
    }

    // 发送电子邮件异常
    @ExceptionHandler(EmailMessagingException.class)
    public ResultJsonData emailMessageException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5002.getCode(), ReturnCodeEnum.RC5002.getMessage());
    }

    // 邮箱已被注册异常返回
    @ExceptionHandler(EmailDuplicateException.class)
    public ResultJsonData emailException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5001.getCode(), ReturnCodeEnum.RC5001.getMessage());
    }

}
