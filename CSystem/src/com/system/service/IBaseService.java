package com.system.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

public interface IBaseService<T> {

	public T doGetById(Serializable id) throws Exception;
	
	public boolean doSave(T t) throws Exception;
	
	public boolean doUpdate(T t) throws Exception;
	
	public boolean doDeleteById(Serializable id);
	
	public List<T> doGetFilterList(DetachedCriteria detachedCriteria)throws Exception;
	
}
