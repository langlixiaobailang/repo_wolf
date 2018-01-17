package com.cmos.web.dao.sys;

import com.cmos.web.beans.sys.User;

import java.util.List;
import java.util.Map;

/**
 * Created by  on 2017/12/21.
 */
public interface  UserDao{
    /**
     * 根据对象插入一条新数据
     * @return void
     * @throws Exception
     */
    void insert(User user);

    /**
     * 删除一条数据
     * @return void
     * @throws Exception
     */
    void delete(Long userId);

    /**
     * 批量删除
     * @return void
     * @throws Exception
     */
    void batchDelete(List<Integer> ids);

    /**
     * 更新一条数据
     * @return void
     * @throws Exception
     */
    void update(User user);

    /**
     * 根据id返回 查询一条数据
     * @param id
     * @return T
     * @throws Exception
     */
    User select(Long id);

    /**
     * 查询一条数据
     * @param map
     * @return map
     * @throws Exception
     */
    User selectByMap(Map<String, Object> map);

    /**
     * 查询列表
     * @param map
     * @return map
     * @throws Exception
     */
    List<User> getListByMap(Map<String, Object> map);
}
