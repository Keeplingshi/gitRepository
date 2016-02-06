package com.system.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDao<T> {

	public T getById(Serializable id);
	
	public boolean save(T t);
	
	public boolean update(T t);
	
	public void delete(T t);
	
	public void deleteById(Serializable id);
	
	public List<T> getFilterList(DetachedCriteria detachedCriteria);
	
	
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
