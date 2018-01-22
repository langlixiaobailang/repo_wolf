package com.cmos.web.Aspect;

/**
 * Created by Administrator on 2018/1/18.
 */

import com.cmos.web.common.result.Result;
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
    public Result<Object> handleMyException(HttpServletRequest request, Exception e){
        Result<Object> result = new Result<>();
        result.setObject("");
        result.setReturnCode("-1");
        result.setReturnMessage("内部错误！");
        e.printStackTrace();
        return result;
    }
}