package com.cmos.base.iservice;

import com.cmos.base.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <ol>
 * <li>创建文档 Date:2018年1月3日10:09:50</li>
 * <li>服务接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">huangyue</a>
 * @version 1.0
 * @param <T>
 * @since 1.0
 */
@Service
public class IServiceImpl<T> implements IService<T> {
	@Autowired
	public IDao<T> iDao;
	/**
	 * 根据对象 插入一条新数据
	 * @param model
	 * @return void
	 * @throws Exception
	 */
    public void insert(T model) throws Exception{
    	iDao.insert(model);
    }

	/**
	 * 根据map参数 插入一条新数据
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	public void insertByMap(Map<String,Object> map) throws Exception{
		iDao.insertByMap(map);
	}
    
    /**
     * 删除一条数据
     * @param modelid
     * @return void
     * @throws Exception
     */
    public void delete(Long modelid) throws Exception{
    	iDao.delete(modelid);
    }

	/**
	 * 删除一条数据
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	public void deleteByMap(Map<String,Object> map) throws Exception{
		iDao.deleteByMap(map);
	}


	/**
     * 更新一条数据
     * @param model
     * @return void
     * @throws Exception
     */
    public void update(T model) throws Exception{
    	iDao.update(model);
    }

	/**
	 * 更新一条数据
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	public void updateByMap(Map<String,Object> map) throws Exception{
		iDao.updateByMap(map);
	}
    
    /**
	 * 查询一条数据 返回对象
	 * @param id
	 * @return T
	 * @throws Exception
	 */
    public T select(Long id) throws Exception{
    	return iDao.select(id);
    }

	/**
	 * 查询一条数据 返回对象
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectByMap(Map<String, Object> map) throws Exception {
		return iDao.selectByMap(map);
	}

	/**
	 * 根据map参数获取列表
	 * @param map
	 * @return List<Map>
	 * @throws Exception
	 */
	public List<Map<String,Object>> getListByMap(Map<String,Object> map) throws Exception{
		return iDao.getListByMap(map);
	}
}
