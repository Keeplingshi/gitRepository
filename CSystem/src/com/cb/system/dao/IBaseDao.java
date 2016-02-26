package com.cb.system.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.cb.system.util.PageInfo;

public interface IBaseDao<T> {

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);
	
	/**
	 * 保存
	 * @param t
	 * @return
	 */
	public boolean save(T t);
	
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	public boolean update(T t);
	
	/**
	 * 根据实体删除
	 * @param t
	 */
	public boolean delete(T t);
	
	/**
	 * 根据id删除实体
	 * @param id
	 */
	public boolean deleteById(Serializable id);
	
	/**
	 * 根据过滤条件查询所有实体，并返回list
	 * @param detachedCriteria
	 * @return
	 */
	public List<T> getFilterList(DetachedCriteria detachedCriteria);
	
	/**
	 * 翻页查询list
	 * @param detachedCriteria
	 * @param pageInfo
	 * @return
	 */
	public List<T> getPageList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	
	/**
	 * 获取数目
	 * @param detachedCriteria
	 * @return
	 */
	public int getTotalCount(DetachedCriteria detachedCriteria);
	
	/**
	 * 获取某一字段统计值
	 * @param detachedCriteria
	 * @param propertyName
	 * @return
	 */
	public double getCountNumber(DetachedCriteria detachedCriteria,String propertyName);
	
	/**
	 * 根据过滤条件获取平均数
	 * @param detachedCriteria
	 * @param propertyName	需要求平均值的字段
	 * @return
	 */
	public double getAverageCount(DetachedCriteria detachedCriteria,String propertyName);
	
//	  public abstract E insert(E paramE);
//
//	  public abstract E save(E paramE);
//
//	  public abstract void saveAll(List<E> paramList);
//
//	  public abstract void delete(E paramE);
//
//	  public abstract void deleteById(Serializable paramSerializable);
//
//	  public abstract void delete(EntityFilter paramEntityFilter);
//
//	  public abstract void deletePhysical(E paramE);
//
//	  public abstract void deleteByIdPhysical(Serializable paramSerializable);
//
//	  public abstract void deletePhysical(EntityFilter paramEntityFilter);
//
//	  public abstract E get(Serializable paramSerializable);
//
//	  public abstract IPagedList<E> getPagedList(EntityFilter paramEntityFilter, PagedInfo paramPagedInfo);
//
//	  public abstract IPagedList<E> getPagedList(EntityFilter paramEntityFilter, Integer paramInteger1, Integer paramInteger2);
//
//	  public abstract List<E> getFilterList(EntityFilter paramEntityFilter);
//
//	  public abstract int getTotalCount(EntityFilter paramEntityFilter);
//
//	  public abstract List<E> getNoDataControlFilterList(EntityFilter paramEntityFilter);
//
//	  public abstract List<E> getNoDataControlPagedList(EntityFilter paramEntityFilter, PagedInfo paramPagedInfo);
//
//	  public abstract Class<E> getEntityClass();
//
//	  public abstract Class getEntityIdType();
//
//	  public abstract String getEntityIdName();
//
//	  public abstract void flush();
	
}
