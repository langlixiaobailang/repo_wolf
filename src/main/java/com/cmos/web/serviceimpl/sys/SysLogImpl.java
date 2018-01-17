package com.cmos.web.serviceimpl.sys;

import com.cmos.web.beans.sys.SysLog;
import com.cmos.web.iservice.sys.ISysLogSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/21.
 */
@Service
public class SysLogImpl implements ISysLogSV {
    @Autowired
    private ISysLogSV sysLogSV;

    @Override
    public void insert(SysLog sysLog) {
        sysLogSV.insert(sysLog);
    }
}
