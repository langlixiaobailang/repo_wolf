package com.cmos.web.base.iservice;

import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.common.enums.LogType;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * <li>创建文档 Date: 2018年1月3日10:01:33</li>
 * <li>服务接口</li>
 * </ol>
 * @author <a href="mailto:2449709309@qq.com">huangyue</a>
 * @version 1.0
 * @param <T>
 * @since 1.0
 */
public interface IService<T> {
	/**
	 * 插入一条新数据
	 * @param model
	 * @return void
	 */
     void insert(T model);

	/**
	 * 删除一条数据
	 * @param modelid
	 * @return void
	 */
	void delete(Long modelid);

	/**
	 * 批量删除
	 * @return void
	 * @throws Exception
	 */
	void batchDelete(List<Integer> ids);

	/**
	 * 更新一条数据
	 * @param model
	 * @return void
	 * @throws Exception
	 */
	 @LoggerManager(type = LogType.UPDATE)
	 void update(T model);

    /**
	 * 根据id查询一条数据
	 * @param id
	 * @return T
	 * @throws Exception
	 */
     T select(Long id);

	/**
	 * 根据map参数，查询一条数据
	 * @param map
	 * @return T
	 * @throws Exception
	 */
	 T selectByMap(Map<String, Object> map);
	/**
	 * 根据map参数，获取分页列表
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	List<T> getListByMap(Map<String, Object> map);
}
