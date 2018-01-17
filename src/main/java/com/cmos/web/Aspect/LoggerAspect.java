package com.cmos.web.Aspect;

import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.base.result.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.lang.model.element.Element;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 编写切面
 * @author hy
 */
@Aspect // 注解声明一个切面
@Component // 受spring管理的容器
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    private static final ThreadLocal<Long> timeTreadLocal = new ThreadLocal<>();

    //定义一个切点方法
    @Pointcut(" @annotation(com.cmos.web.annotation.LoggerManager)")
    public void loggerManagerCut() {}

    @Before("loggerManagerCut()")
    public void beforeLogger(JoinPoint joinPoint) {
        logger.info("******Before advice ******");
        // 接收到请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("******请求地址 : " + request.getRequestURL().toString());
        logger.info("******类型: " + request.getMethod());
        logger.info("******IP : " + request.getRemoteAddr());
        logger.info("******请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("******参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 环绕通知方法前必须throws 这样出现异常才能捕捉
     * @param pjp
     * @throws Throwable
     */
    @Around("loggerManagerCut()")
    public Result<Object>  aroundLogger(ProceedingJoinPoint pjp) throws Throwable{
        logger.info("******Around advice ******");
        Result<Object> result = new Result<>(); //返回对象
        Map<String, Object> map = getControllerMethodDescription(pjp);
        Map<String, Object> logForm = new HashMap<>();
        try{
            logForm.put("module", map.get("module"));
            logForm.put("methods", map.get("methods"));
            logForm.put("description", "执行成功！");
            long startTime = System.currentTimeMillis();
            result  = (Result<Object>)pjp.proceed(); //调用方法
            long endTime = System.currentTimeMillis();
            //记录操作日志
            logForm.put("actionTime", endTime-startTime);
         }catch (Throwable throwable){
            //异常 表示方法调用失败 保存操作日志
            logForm.put("description", "执行失败！");
         }
        return result;
    }
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint){
        Map<String, Object> map = new HashMap<>();
        String targetName = joinPoint.getTarget().getClass().getName(); //调用的controller类
        String methodName = joinPoint.getSignature().getName(); //方法名称
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
           logger.error("",e.getMessage());
        }
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("module", method.getAnnotation(LoggerManager.class).module());
                    map.put("methods", joinPoint.getSignature().getDeclaringTypeName() + "."
                            + joinPoint.getSignature().getName());
                    String de = method.getAnnotation(LoggerManager.class).description();
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(de)){
                        de="执行成功!";
                    };
                    map.put("description", de);
                    break;
                }
            }
        }
        return map;
    }
}
