package com.cmos.web.iservice.sys;

import com.cmos.web.beans.sys.SysDept;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
public interface ISysDeptSV {

    SysDept getById(Long id);

    int delete(Long id);

    int insert(SysDept sysDept);

    int update(SysDept sysDept);

    List<SysDept> getListByMap(Map map);
    

}
