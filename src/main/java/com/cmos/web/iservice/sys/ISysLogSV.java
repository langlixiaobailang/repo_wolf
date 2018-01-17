package com.cmos.web.iservice.sys;

import com.cmos.web.beans.sys.SysLog;

/**
 * Created by Administrator on 2017/12/21.
 */
public interface ISysLogSV {
    /**
     * 插入一条新数据
     * @return void
     */
    void insert(SysLog sysLog);
}
