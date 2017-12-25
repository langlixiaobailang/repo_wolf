package com.base.web.serviceimpl;

import com.base.web.beans.User;
import com.base.web.dao.UserDao;
import com.base.web.iservice.IUserSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */
@Service
public class UserImpl implements IUserSV {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List getUserList() {
        return userDao.getUserList();
    }
}
