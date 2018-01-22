package com.cmos.web.common.utils;

import com.cmos.web.beans.sys.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/1/18.
 */
public class ToolUtils {

    private static int seq = 0;
    private static final int MAX_PER_SECOND = 1000;

    /**
     * 获取HttpSession
     * @return HttpSession
     */
    public static HttpSession getSession(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        return session;
    }

    /**
     * 获取登录用户
     * @return
     */
    public static SysUser getLoginUser(HttpServletRequest request) {
        HttpSession session = getSession(request);
        SysUser user = (SysUser)session.getAttribute("loginUser");
        if(user != null){
            return user;
        }
        return null;
    }

    /**
     * 获取IP地址
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    /**
     * 生成15位的数字流水号(16位以后会出现科学计数影响业务操作)
     * <p>
     * <I>生成规则为:</I><b>yyMMddHHmmss+1位顺序号</b>
     * </p>
     *
     * @return 15位流水号
     */
    public static synchronized String getNextSeq() {
        seq++;
        return (new SimpleDateFormat("yyMMddHHmmss").format(new Date()))
                + String.format("%03d", seq %= MAX_PER_SECOND);
    }

    /**
     * 去掉特殊字符串【<>】
     * @return
     */
    public static String OperationStr(String specialStr){
        String newSpecialStr = specialStr;
        if(newSpecialStr == null){
            return newSpecialStr;
        }
        newSpecialStr = newSpecialStr.replaceAll("<", "");
        newSpecialStr = newSpecialStr.replaceAll(">", "");
        return newSpecialStr;
    }

    //验证身份证号码  15或者18位就行
    public static boolean validateIdCard(String number){
        String rgx = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    //验证身份证号码  15或者18位就行
    public static boolean validateIdCard2(String number){
        String rgx = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     * 验证手机号
     * @param mobiles
     * @return
     */
    public static boolean validateMobile(String mobiles){
        Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
