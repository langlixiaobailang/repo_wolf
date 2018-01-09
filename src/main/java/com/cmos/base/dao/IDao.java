package com.cmos.base.dao;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * <li>创建文档 Date: 2018年1月3日10:08:23</li>
 * <li>DAO接口</li>
 * </ol>
 *
 * @author <a href="mailto:2449709309@qq.com">huangyue</a>
 * @version 1.0
 * @param <T>
 * @since 1.0
 */
public interface IDao<T> {
	/**
	 * 根据对象插入一条新数据
	 * @param model
	 * @return void
	 * @throws Exception
	 */
     void insert(T model) throws Exception;

	/**
	 * 删除一条数据
	 * @param modelid
	 * @return void
	 * @throws Exception
	 */
	void delete(Long modelid) throws Exception;

	/**
	 * 批量删除
	 * @return void
	 * @throws Exception
	 */
	void batchDelete(List<Integer> ids) throws Exception;

	/**
     * 更新一条数据
     * @param model
     * @return void
     * @throws Exception
     */
     void update(T model) throws Exception;

    /**
	 * 根据id返回 查询一条数据
	 * @param id
	 * @return T
	 * @throws Exception
	 */
     T select(Long id) throws Exception;

	/**
	 * 查询一条数据
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	 T selectByMap(Map<String,Object> map) throws Exception;

	/**
	 * 查询列表
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	List<T> getListByMap(Map<String,Object> map) throws Exception;
}
