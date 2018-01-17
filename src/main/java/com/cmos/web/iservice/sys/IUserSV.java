package com.cmos.web.iservice.sys;


import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.beans.sys.User;
import com.cmos.web.common.enums.LogType;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21.
 */
public interface IUserSV{
    /**
     * 插入一条新数据
     * @return void
     */
    void insert(User user);

    /**
     * 删除一条数据
     * @return void
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
     * @param user
     * @return void
     * @throws Exception
     */
    @LoggerManager(type = LogType.UPDATE)
    void update(User user);

    /**
     * 根据id查询一条数据
     * @param id
     * @return T
     * @throws Exception
     */
    User select(Long id);

    /**
     * 根据map参数，查询一条数据
     * @param map
     * @return T
     * @throws Exception
     */
    User selectByMap(Map<String, Object> map);
    /**
     * 根据map参数，获取分页列表
     * @param map
     * @return map
     * @throws Exception
     */
    List<User> getListByMap(Map<String, Object> map);
}
