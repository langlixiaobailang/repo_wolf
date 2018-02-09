package com.cmos.web.dao.sys;

import com.cmos.web.beans.sys.SysDictDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
public interface SysDictDetailDao {

    SysDictDetail getById(Long id);

    int delete(Long id);

    int insert(SysDictDetail sysDictDetail);

    int update(SysDictDetail sysDictDetail);

    List<SysDictDetail> getListByMap(Map map);

}
