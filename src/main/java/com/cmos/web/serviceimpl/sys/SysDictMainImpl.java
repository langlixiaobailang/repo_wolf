package com.cmos.web.serviceimpl.sys;

import com.cmos.web.beans.sys.SysDictMain;
import com.cmos.web.dao.sys.SysDictMainDao;
import com.cmos.web.iservice.sys.SysDictMainSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
public class SysDictMainImpl implements SysDictMainSV {

    @Autowired
    private SysDictMainDao sysDictMainDao;

    @Override
    public SysDictMain getById(Long id) {
        return sysDictMainDao.getById(id);
    }

    @Override
    public int delete(Long id) {
        return sysDictMainDao.delete(id);
    }

    @Override
    public int insert(SysDictMain sysDictMain) {
        return sysDictMainDao.insert(sysDictMain);
    }

    @Override
    public int update(SysDictMain sysDictMain) {
        return sysDictMainDao.update(sysDictMain);
    }

    @Override
    public List<SysDictMain> getListByMap(Map map) {
        return sysDictMainDao.getListByMap(map);
    }
}
