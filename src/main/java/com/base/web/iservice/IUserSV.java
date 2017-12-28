package com.base.web.iservice;

import com.base.web.beans.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21.
 */
public interface IUserSV {
    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
     User getUserById(Long id);

    List getUserList();

    List<Map<String,Object>> getUserListMap();

}
