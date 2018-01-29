package com.cmos.web.serviceimpl.sys;

import com.cmos.web.beans.sys.SysUser;
import com.cmos.web.dao.sys.SysUserDao;
import com.cmos.web.iservice.sys.ISysUserSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21.
 */

@Service
public class SysUserImpl implements ISysUserSV {
    @Resource
    private SysUserDao sysUserDao;

    @Override
    public void insert(SysUser user) throws Exception{
        sysUserDao.insert(user);
    }

    @Override
    public void delete(Long userId) throws Exception{
        sysUserDao.delete(userId);

    }

    @Override
    public void batchDelete(List<Integer> ids) throws Exception{
        sysUserDao.batchDelete(ids);
    }

    @Override
    public void update(SysUser user) throws Exception{
        sysUserDao.update(user);
        int i =10/0;
//        user.setUserName("事务回滚");
//        sysUserDao.update(user);

    }

    @Override
    public SysUser select(Long id) throws Exception{
        return sysUserDao.select(id);
    }

    @Override
    public SysUser selectByMap(Map<String, Object> map) throws Exception{
        return sysUserDao.selectByMap(map);
    }

    @Override
    public List<SysUser> getListByMap(Map<String, Object> map) throws Exception{
        return sysUserDao.getListByMap(map);
    }
}
