package com.cmos.web.annotation;

import com.cmos.web.common.enums.LogType;

import java.lang.annotation.*;

/**
 * 自定义注解日志类
 * Created by Administrator on 2018/1/11.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManager {
     LogType type() default LogType.SPACE;
     String module()  default "";  //模块
     String description() default ""; //执行是否成功
}
