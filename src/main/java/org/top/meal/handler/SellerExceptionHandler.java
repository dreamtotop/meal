package org.top.meal.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.top.meal.config.ProjectUrlConfig;
import org.top.meal.exception.SellException;
import org.top.meal.exception.SellerAuthorizeException;
import org.top.meal.utils.ResultVOUtil;
import org.top.meal.vo.ResultVO;

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){

        return new ModelAndView("redirect:".concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
        .concat(projectUrlConfig.getSell()).concat("sell/seller/login"));
    }

    @ExceptionHandler(value=SellException.class)
    @ResponseBody
    //指定返回http状态码
    //@ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
