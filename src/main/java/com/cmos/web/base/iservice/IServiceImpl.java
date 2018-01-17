package com.cmos.web.base.iservice;

import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.base.dao.IDao;
import com.cmos.web.common.enums.LogType;
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
	private IDao<T> iDao;
	/**
	 * 根据对象 插入一条新数据
	 * @param model
	 * @return void
	 * @throws Exception
	 */
    public void insert(T model){
    	iDao.insert(model);
    }

    /**
     * 删除一条数据
     * @param modelid
     * @return void
     * @throws Exception
     */
    public void delete(Long modelid){
    	iDao.delete(modelid);
    }

	/**
	 * 批量删除
	 * @return void
	 * @throws Exception
	 */
	public void batchDelete(List<Integer> ids){
		iDao.batchDelete(ids);
	}

	/**
     * 更新一条数据
     * @param model
     * @return void
     * @throws Exception
     */
    public void update(T model){
    	iDao.update(model);
    }

    /**
	 * 查询一条数据 返回对象
	 * @param id
	 * @return T
	 * @throws Exception
	 */
    public T select(Long id){
    	return iDao.select(id);
    }

	/**
	 * 查询一条数据 返回对象
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@Override

	public T selectByMap(Map<String, Object> map){
		//int aaa = 10/0;
		return iDao.selectByMap(map);
	}

	/**
	 * 根据map参数获取列表
	 * @param map
	 * @return List<Map>
	 * @throws Exception
	 */
	public List<T> getListByMap(Map<String,Object> map){
		return iDao.getListByMap(map);
	}
}
