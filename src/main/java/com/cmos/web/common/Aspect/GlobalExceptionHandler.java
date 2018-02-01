package com.cmos.web.common.Aspect;

/**
 * Created by Administrator on 2018/1/18.
 */

import com.cmos.web.common.result.Result;
import org.apache.shiro.authc.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @create 2017-12-02 15:34
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> handleMyException(HttpServletRequest request, Exception ex){
        Result<Object> result = new Result<>("-1","未知错误","");
        if((ex instanceof UnknownAccountException) || (ex instanceof IncorrectCredentialsException)){
            result.setReturnMessage("用户名或者密码错误！");
        }else if((ex instanceof LockedAccountException) || (ex instanceof DisabledAccountException)){
            result.setReturnMessage("账号被锁定！");
        }else if(ex instanceof ExpiredCredentialsException){
            result.setReturnMessage("用户登录超时，请重新登录！");
        }else if(ex instanceof ExcessiveAttemptsException){
            result.setReturnMessage("失败次数过多！");
        }else{
        }
        ex.printStackTrace();
        return result;
    }
}