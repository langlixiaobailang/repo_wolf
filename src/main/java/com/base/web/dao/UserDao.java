package com.base.web.dao;

import com.base.web.beans.User;

import java.util.List;

/**
 * Created by  on 2017/12/21.
 */
public interface  UserDao {
    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
     User getUserById(Long id);

    List getUserList();

}
