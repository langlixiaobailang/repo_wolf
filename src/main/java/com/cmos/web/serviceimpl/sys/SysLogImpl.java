package com.cmos.web.serviceimpl.sys;

import com.cmos.web.beans.sys.SysLog;
import com.cmos.web.dao.sys.SysLogDao;
import com.cmos.web.iservice.sys.ISysLogSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/21.
 */
@Service
public class SysLogImpl implements ISysLogSV {
    /**
     *
     */
    @Resource
    public SysLogDao sysLogDao;

    @Override
    public void insert(SysLog sysLog)throws Exception {
        sysLogDao.insert(sysLog);
    }
}
