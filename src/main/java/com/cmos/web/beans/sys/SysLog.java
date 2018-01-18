package com.cmos.web.beans.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/21.
 */
@Data
public class SysLog implements Serializable{
    private Long id;
    private String logTypeName;
    private String sysLogModule;
    private String sysLogMethods;
    private String sysLogResult;
    private String sysLogIp;
    private Date logCreateDate;
    private Long logHandleTimes;
    private String sysLogDesc;
    private String logErrorMsg;
    private String logCreateUser;
}
