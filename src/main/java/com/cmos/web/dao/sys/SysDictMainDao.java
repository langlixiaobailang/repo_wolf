package com.cmos.web.dao.sys;

import com.cmos.web.beans.sys.SysDictMain;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
public interface SysDictMainDao {

    SysDictMain getById(Long id);

    int delete(Long id);

    int insert(SysDictMain sysDictMain);

    int update(SysDictMain sysDictMain);

    List<SysDictMain> getListByMap(Map map);

}
