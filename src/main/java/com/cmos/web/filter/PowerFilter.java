package com.cmos.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hy on 2017/12/18.
 */
@WebFilter(filterName = "PowerFilter",urlPatterns = "/*")
public class PowerFilter implements Filter {
    private static final Logger Logger = LoggerFactory.getLogger(PowerFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Logger.info("权限控制过滤器启动中..........");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("P3P","CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
        //配置如果链接中有login关键字放行，否则进行拦截
        String servletPath = ((HttpServletRequest) request).getServletPath();
        if(servletPath.indexOf("login") != -1){
            chain.doFilter(req, res);
        }else{
            //判断是否登录,未登录跳转到登录页面
            if(req.getSession().getAttribute("loginUser") == null ){
                Logger.info(req.getSession().getAttribute("loginUser")+"********用户未登录..........");
                logout(res,"用户未登录");
            }else{
                chain.doFilter(req, res);
            }

        }
    }
    private void logout(HttpServletResponse res, String tipMessage) throws IOException {
        res.setHeader("Content-type", "text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(tipMessage);
        res.sendRedirect("/login.html");
    }
    @Override
    public void destroy() {

    }
}
