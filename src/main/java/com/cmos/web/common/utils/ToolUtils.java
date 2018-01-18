package com.cmos.web.common.utils;

import com.cmos.web.beans.sys.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = getSession(request);
        User user = (User)session.getAttribute("loginUser");
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
}
