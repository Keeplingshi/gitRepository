package com.cb.system.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.cb.system.util.PageInfo;

/**
 * 基础服务接口
 * @author chen
 *
 * @param <T>
 */
public interface IBaseService<T> {

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T doGetById(Serializable id) throws Exception;
	
	/**
	 * 保存实体
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(T t) throws Exception;
	
	/**
	 * 更新实体
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdate(T t) throws Exception;
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public boolean doDeleteById(Serializable id);
	
	/**
	 * 根据过滤条件获取所有数据
	 * @param detachedCriteria
	 * @return
	 * @throws Exception
	 */
	public List<T> doGetFilterList(DetachedCriteria detachedCriteria)throws Exception;
	
	/**
	 * 分页查询
	 * @param detachedCriteria
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<T> doGetPageList(DetachedCriteria detachedCriteria,PageInfo pageInfo)throws Exception;
}
