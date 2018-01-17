package com.cmos.web.dao.sys;

import com.cmos.web.beans.sys.User;

/**
 * Created by  on 2017/12/21.
 */
public interface SysLogDao{
    /**
     * 根据对象插入一条新数据
     * @return void
     * @throws Exception
     */
    void insert(User user);
}
