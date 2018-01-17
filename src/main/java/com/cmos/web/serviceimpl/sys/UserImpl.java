package com.cmos.web.serviceimpl.sys;

import com.cmos.web.beans.sys.User;
import com.cmos.web.dao.sys.UserDao;
import com.cmos.web.iservice.sys.IUserSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21.
 */
@Service
public class  UserImpl implements  IUserSV {
    @Autowired
    private UserDao userDao;

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        userDao.batchDelete(ids);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User select(Long id) {
        return userDao.select(id);
    }

    @Override
    public User selectByMap(Map<String, Object> map) {
        return userDao.selectByMap(map);
    }

    @Override
    public List<User> getListByMap(Map<String, Object> map) {
        return userDao.getListByMap(map);
    }
}
