package com.cmos.web.common.Aspect;

import com.cmos.web.beans.sys.SysLog;
import com.cmos.web.beans.sys.SysUser;
import com.cmos.web.common.annotation.LoggerManager;
import com.cmos.web.common.enums.LogType;
import com.cmos.web.common.utils.ToolUtils;
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
    private Long startTimes = 0L;
    private Long endTimes = 0L;
    private Long executeTime = 0L;

    @Autowired
    private ISysLogSV sysLogSV;

    @Pointcut(" @annotation(com.cmos.web.common.annotation.LoggerManager)")
    public void loggerManagerCut() {}

    @Before("loggerManagerCut()")
    public void beforeLogger(JoinPoint joinPoint) {
        logger.info("****** Before advice ******");
        // 接收到请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("******request url : " + request.getRequestURL().toString());
        logger.info("******method type: " + request.getMethod());
        logger.info("******IP : " + request.getRemoteAddr());
        logger.info("******request method : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("******params : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 环绕通知方法前必须throws 这样出现异常才能捕捉
     * @throws Throwable
     */
    @Around("loggerManagerCut()")
    public Object aroundLogger(ProceedingJoinPoint pjp) throws Throwable{
        Object result = null;
        try {
            startTimes = System.currentTimeMillis();
            result = pjp.proceed();
            endTimes = System.currentTimeMillis();
            executeTime = endTimes- startTimes; //得到的是毫秒   1秒=1000毫秒
        } catch (Throwable e) {
            throw e;
        }
        return result;
    }

    @AfterReturning(pointcut = "loggerManagerCut()",returning = "object")//打印输出结果
        public void AfterReturning(JoinPoint joinPoint,Object object) throws Exception{
        logger.error("****** AfterReturning success ***********");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SysUser user = ToolUtils.getLoginUser(request);
        String ip = ToolUtils.getRemoteAddr(request);
        Map<String, Object> map = getControllerMethodDescription(joinPoint);
        SysLog sysLog = new SysLog();
        sysLog.setLogTypeName((String)map.get("type"));
        sysLog.setSysLogModule((String)map.get("module"));
        sysLog.setSysLogMethods((String)map.get("methods"));
        sysLog.setSysLogResult("执行成功");
        sysLog.setSysLogIp(ip);
        sysLog.setLogHandleTimes(executeTime);
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
        logger.error("AfterThrowing  error message :"+ex);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SysUser user = ToolUtils.getLoginUser(request);
        String ip = ToolUtils.getRemoteAddr(request);
        Map<String, Object> map = getControllerMethodDescription(joinPoint);
        //异常 表示方法调用失败 保存操作日志
            SysLog errSysLog = new SysLog();
            errSysLog.setLogTypeName((String)map.get("type"));
            errSysLog.setSysLogModule((String)map.get("module"));
            errSysLog.setSysLogMethods((String)map.get("methods"));
            errSysLog.setSysLogResult("执行失败");
            errSysLog.setSysLogIp(ip);
            errSysLog.setLogHandleTimes(executeTime);
            errSysLog.setLogCreateDate(new Date());
            errSysLog.setSysLogDesc((String)map.get("description"));
            errSysLog.setLogErrorMsg(ex.toString());
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
           logger.error("不能得到调用的类",e.getMessage());
        }
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    LogType logType = method.getAnnotation(LoggerManager.class).type();
                    map.put("methods", joinPoint.getSignature().getDeclaringTypeName() + "."
                            + joinPoint.getSignature().getName());
                    map.put("type",logType.GetDescription());
                    map.put("module", method.getAnnotation(LoggerManager.class).module());
                    String de = method.getAnnotation(LoggerManager.class).description();
                    if(org.apache.commons.lang3.StringUtils.isBlank(de)){
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
