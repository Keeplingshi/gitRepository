package com.system.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

	public T doGetById(Serializable id);
	
	public T save(T t);
	
	public void delete(T t);
	
	public void doDeleteById(Serializable id);
	
	public List<T> doGetFilterList();
	
	
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
