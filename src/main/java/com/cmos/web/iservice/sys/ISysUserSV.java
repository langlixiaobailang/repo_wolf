package com.cmos.web.iservice.sys;


import com.cmos.web.beans.sys.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21.
 */
public interface ISysUserSV {
    /**
     * 插入一条新数据
     * @return void
     */
    void insert(SysUser sysUser) throws Exception;

    /**
     * 删除一条数据
     * @return void
     */
    void delete(Long userId) throws Exception;

    /**
     * 批量删除
     * @return void
     * @throws Exception
     */
    void batchDelete(List<Integer> ids) throws Exception;

    /**
     * 更新一条数据
     * @return void
     * @throws Exception
     */
    void update(SysUser sysUser);

    /**
     * 根据id查询一条数据
     * @param id
     * @return T
     * @throws Exception
     */
    SysUser select(Long id) throws Exception;

    /**
     * 根据map参数，查询一条数据
     * @param map
     * @return T
     * @throws Exception
     */
    SysUser selectByMap(Map<String, Object> map)throws Exception;
    /**
     * 根据map参数，获取分页列表
     * @param map
     * @return map
     * @throws Exception
     */
    List<SysUser> getListByMap(Map<String, Object> map) throws Exception;
}
