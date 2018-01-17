package com.cmos.web.common.enums;

/**
 * Created by Administrator on 2018/1/16.
 */
public enum LogType {
    SPACE(""),
    INSERT("增加"),
    DELETE("删除"),
    UPDATE("修改"),
    QUERY("查询"),
    LOGINOUT("退出"),
    LOGIN("登录");

    private String description;
    private LogType( String string) {
        description = string;
    }

    public String GetDescription()
    {
        return description;
    }
}
