package com.cmos.web.serviceimpl.sys;

import com.cmos.web.beans.sys.SysDept;
import com.cmos.web.dao.sys.SysDeptDao;
import com.cmos.web.iservice.sys.ISysDeptSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public class SysDeptImpl implements ISysDeptSV {

    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public SysDept getById(Long id) {
        return sysDeptDao.getById(id);
    }

    @Override
    public int delete(Long id) {
        return sysDeptDao.delete(id);
    }

    @Override
    public int insert(SysDept sysDept) {
        return sysDeptDao.insert(sysDept);
    }

    @Override
    public int update(SysDept sysDept) {
        return sysDeptDao.update(sysDept);
    }

    @Override
    public List<SysDept> getListByMap(Map map) {
        return sysDeptDao.getListByMap(map);
    }
}
