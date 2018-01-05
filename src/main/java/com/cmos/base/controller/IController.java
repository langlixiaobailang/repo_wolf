package com.cmos.base.controller;

import com.cmos.web.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/3.
 */
public class IController {
    public static final  String SUCCESS = "200";
    public static final  String ERROR = "-1";
    public static final  String SELECT_SUCCESS_MSG = "查询列表成功";
    public static final  String SELECT_ERROR_MSG = "查询列表失败";
    public static final  String SELECT_SUCCESS_MO_MSG = "查询对象成功";
    public static final  String SELECT_ERROR_MO_MSG = "查询对象失败";
    public static final  String INSERT_SUCCESS_MSG = "保存成功";
    public static final  String INSERT_ERROR_MSG = "保存失败";
    public static final  String UPDATE_SUCCESS_MSG = "修改成功";
    public static final  String UPDATE_ERROR_MSG = "修改失败";
    public static final  String DELETE_SUCCESS_MSG = "删除成功";
    public static final  String DELETE_ERROR_MSG = "删除失败";
    public static final  String GETPARAM_ERROR_MSG = "获取参数失败";
    public static final  String LOGIN_OUT_MSG = "用户未登录";
    public static final  Integer pageNum = 1;
    public static final  Integer pageSize = 20;

    /**
     * 获取HttpSession
     * @return HttpSession
     */
    protected HttpSession getSession(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        return session;
    }

    /**
     * 获取登录用户id
     * @return
     */
    public User getLoginUser(HttpServletRequest request) {
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
    protected String getRemoteAddr(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
}
