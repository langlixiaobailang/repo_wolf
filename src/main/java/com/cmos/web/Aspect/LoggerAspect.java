package com.cmos.web.Aspect;

import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.beans.sys.SysLog;
import com.cmos.web.beans.sys.User;
import com.cmos.web.common.result.Result;
import com.cmos.web.common.utils.ToolUtils;
import com.cmos.web.controller.sys.IController;
import com.cmos.web.iservice.sys.ISysLogSV;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
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

    @Autowired
    private ISysLogSV sysLogSV;

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
     * @throws Throwable
     */
//    @Around("loggerManagerCut()")
//    public void aroundLogger(ProceedingJoinPoint pjp) throws Throwable{
//        logger.info("******Around advice ******");
//        // 接收到请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        User user = ToolUtils.getLoginUser(request);
//        String ip = ToolUtils.getRemoteAddr(request);
//        //Result<Object> result = new Result<>(); //返回对象
//        Map<String, Object> map = getControllerMethodDescription(pjp);
//        Long executeTime = 0L;
//        try{
//            long startTime = System.currentTimeMillis();
//            pjp.proceed(); //调用方法
//            long endTime = System.currentTimeMillis();
//            executeTime = endTime-startTime;
//
//            SysLog sysLog = new SysLog();
//            sysLog.setLogTypeName((String)map.get("type"));
//            sysLog.setSysLogModule((String)map.get("module"));
//            sysLog.setSysLogMethods((String)map.get("methods"));
//            sysLog.setSysLogResult("执行成功");
//            sysLog.setSysLogIp(ip);
//            sysLog.setLogHandleTimes(executeTime);
//            sysLog.setLogCreateDate(new Date());
//            sysLog.setSysLogDesc((String)map.get("description"));
//            if(user != null){
//                sysLog.setLogCreateUser(user.getUserName());
//            }
//            sysLogSV.insert(sysLog);
//            logger.error("保存日志成功！");
//         }catch (Throwable throwable){
//            //异常 表示方法调用失败 保存操作日志
//            SysLog errSysLog = new SysLog();
//            errSysLog.setLogTypeName((String)map.get("type"));
//            errSysLog.setSysLogModule((String)map.get("module"));
//            errSysLog.setSysLogMethods((String)map.get("methods"));
//            errSysLog.setSysLogResult("执行失败");
//            errSysLog.setSysLogIp(ip);
//            errSysLog.setLogHandleTimes(executeTime);
//            errSysLog.setLogCreateDate(new Date());
//            errSysLog.setSysLogDesc((String)map.get("description"));
//            errSysLog.setLogErrorMsg(throwable.getMessage());
//            if(user != null){
//                errSysLog.setLogCreateUser(user.getUserName());
//            }
//            sysLogSV.insert(errSysLog);
//            logger.error("保存异常日志成功！异常信息为：" + throwable.getMessage());
//            throw throwable;
//         }
//    }

    @AfterReturning(pointcut = "loggerManagerCut()",returning = "object")//打印输出结果
    public void AfterReturning(JoinPoint joinPoint,Object object) throws Exception{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        User user = ToolUtils.getLoginUser(request);
        String ip = ToolUtils.getRemoteAddr(request);
        Map<String, Object> map = getControllerMethodDescription(joinPoint);
        SysLog sysLog = new SysLog();
        sysLog.setLogTypeName((String)map.get("type"));
        sysLog.setSysLogModule((String)map.get("module"));
        sysLog.setSysLogMethods((String)map.get("methods"));
        sysLog.setSysLogResult("执行成功");
        sysLog.setSysLogIp(ip);
        //sysLog.setLogHandleTimes(executeTime);
        sysLog.setLogCreateDate(new Date());
        sysLog.setSysLogDesc((String)map.get("description"));
        if(user != null){
            sysLog.setLogCreateUser(user.getUserName());
        }
        sysLogSV.insert(sysLog);
        logger.info("response={}",object.toString());
    }

    @AfterThrowing(pointcut = "loggerManagerCut()",throwing="ex")
    public void AfterThrowing(JoinPoint joinPoint,Throwable ex) throws Exception{
        System.out.println("目标方法中抛出的异常:"+ex);
        System.out.println("模拟抛出异常后的增强处理...");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        User user = ToolUtils.getLoginUser(request);
        String ip = ToolUtils.getRemoteAddr(request);
        Map<String, Object> map = getControllerMethodDescription(joinPoint);
        //异常 表示方法调用失败 保存操作日志
            SysLog errSysLog = new SysLog();
            errSysLog.setLogTypeName((String)map.get("type"));
            errSysLog.setSysLogModule((String)map.get("module"));
            errSysLog.setSysLogMethods((String)map.get("methods"));
            errSysLog.setSysLogResult("执行失败");
            errSysLog.setSysLogIp(ip);
            //errSysLog.setLogHandleTimes(executeTime);
            errSysLog.setLogCreateDate(new Date());
            errSysLog.setSysLogDesc((String)map.get("description"));
            errSysLog.setLogErrorMsg(ex.getMessage());
            if(user != null){
                errSysLog.setLogCreateUser(user.getUserName());
            }
            sysLogSV.insert(errSysLog);
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
