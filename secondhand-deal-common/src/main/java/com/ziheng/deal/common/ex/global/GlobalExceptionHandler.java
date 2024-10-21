package com.ziheng.deal.common.ex.global;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ziheng.deal.common.ex.customizeErorr.EmailMessagingException;
import com.ziheng.deal.common.ex.customizeErorr.TokenIllegalException;
import com.ziheng.deal.common.ex.customizeErorr.controllerErorr.FileEmptyException;
import com.ziheng.deal.common.ex.customizeErorr.controllerErorr.FileSizeException;
import com.ziheng.deal.common.ex.customizeErorr.controllerErorr.FileTypeException;
import com.ziheng.deal.common.ex.customizeErorr.controllerErorr.FileUploadException;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.resp.ReturnCodeEnum;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    // 积分不足异常处理
    @ExceptionHandler(IntegralNotSufficientException.class)
    public ResultJsonData<Void> integralNotSufficientException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5023.getCode(), ReturnCodeEnum.RC5023.getMessage());
    }

    // 商品sku不存在异常处理
    @ExceptionHandler(CommoditySkuException.class)
    public ResultJsonData<Void> commoditySkuException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5022.getCode(), ReturnCodeEnum.RC5022.getMessage());
    }

    // 订单不存在异常处理
    @ExceptionHandler(OrderNotFoundException.class)
    public ResultJsonData<Void> rderNotFoundException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC1005.getCode(), ReturnCodeEnum.RC1005.getMessage());
    }

    // 订单号不能为空异常处理
    @ExceptionHandler(MissingOrderNumberException.class)
    public ResultJsonData<Void> issingOrderNumberException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC1004.getCode(), ReturnCodeEnum.RC1004.getMessage());
    }

    // 购买自己的商品异常处理
    @ExceptionHandler(SelfPurchaseProhibitedException.class)
    public ResultJsonData<Void> selfPurchaseProhibitedException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC1003.getCode(), ReturnCodeEnum.RC1003.getMessage());
    }

    // 商品库存不足异常处理
    @ExceptionHandler(ProductNotInCartException.class)
    public ResultJsonData<Void> productNotInCartException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC1002.getCode(), ReturnCodeEnum.RC1002.getMessage());
    }

    // 商品库存不足异常处理
    @ExceptionHandler(InventoryShortageException.class)
    public ResultJsonData<Void> inventoryShortageException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC1001.getCode(), ReturnCodeEnum.RC1001.getMessage());
    }

    // 聊天用户id字段不能为空异常处理
    @ExceptionHandler(ChatUserFieldCannotEmptyException.class)
    public ResultJsonData<Void> chatUserFieldCannotEmptyException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5021.getCode(), ReturnCodeEnum.RC5021.getMessage());
    }


    // 用户已签到异常处理
    @ExceptionHandler(SignedInException.class)
    public ResultJsonData<Void> signedInException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5020.getCode(), ReturnCodeEnum.RC5020.getMessage());
    }

    // 收货地址信息不存在异常处理
    @ExceptionHandler(DeliveryAddressDoesNotExistException.class)
    public ResultJsonData<Void> deliveryAddressDoesNotExistException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5019.getCode(), ReturnCodeEnum.RC5019.getMessage());
    }


    // 收货地址id不能为空异常处理
    @ExceptionHandler(ShippingAddressIDCannotBeEmptyException.class)
    public ResultJsonData<Void> shippingAddressIDCannotBeEmptyException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5018.getCode(), ReturnCodeEnum.RC5018.getMessage());
    }


    // 密码不能为空异常处理
    @ExceptionHandler(PasswordCannotEmptyException.class)
    public ResultJsonData<Void> passwordCannotEmptyException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC2006.getCode(), ReturnCodeEnum.RC2006.getMessage());
    }


    // 购物车信息不存在常处理
    @ExceptionHandler(ShoppingCartInfoNull.class)
    public ResultJsonData<Void> shoppingCartInfoNull(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5017.getCode(), ReturnCodeEnum.RC5017.getMessage());
    }

    // 购物车id为空异常处理
    @ExceptionHandler(ShoppingCartIdNotNull.class)
    public ResultJsonData<Void> shoppingCartIdNotNull(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5016.getCode(), ReturnCodeEnum.RC5016.getMessage());
    }

    // 收藏商品已存在异常处理
    @ExceptionHandler(CollectCommodityExisted.class)
    public ResultJsonData<Void> ollectCommodityExisted(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5014.getCode(), ReturnCodeEnum.RC5014.getMessage());
    }

    // 收藏商品id不能为空异常处理
    @ExceptionHandler(CollectCommodityIdNotNull.class)
    public ResultJsonData<Void> ollectCommodityIdNotNull(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5015.getCode(), ReturnCodeEnum.RC5015.getMessage());
    }


    // 商品状态错误异常
    @ExceptionHandler(StatusException.class)
    public ResultJsonData<Void> statusException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5013.getCode(), ReturnCodeEnum.RC5013.getMessage());
    }


    // 商品不存在异常处理
    @ExceptionHandler(CommodityDoesNotExistException.class)
    public ResultJsonData<Void> commodityDoesNotExistException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC5012.getCode(), ReturnCodeEnum.RC5012.getMessage());
    }

    // 字段验证不能为空异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJsonData<Void> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(fieldName).append(": ").append(errorMessage).append("; ");
        });

        return ResultJsonData.fali(4500, errors.toString());
    }



    // 文件上传失败异常处理
    @ExceptionHandler(FileUploadException.class)
    public ResultJsonData<Void> FileUploadException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC3004.getCode(), ReturnCodeEnum.RC3004.getMessage());
    }



    // 文件类型错误异常处理
    @ExceptionHandler(FileTypeException.class)
    public ResultJsonData<Void> fileTypeException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC3003.getCode(), ReturnCodeEnum.RC3003.getMessage());
    }

    // 文件超过指定大小异常处理
    @ExceptionHandler(FileSizeException .class)
    public ResultJsonData<Void> fileSizeException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC3002.getCode(), ReturnCodeEnum.RC3002.getMessage());
    }

    // 文件为空异常处理
    @ExceptionHandler(FileEmptyException.class)
    public ResultJsonData<Void> FileEmptyException(){
        return ResultJsonData.fali(ReturnCodeEnum.RC3001.getCode(), ReturnCodeEnum.RC3001.getMessage());
    }

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
