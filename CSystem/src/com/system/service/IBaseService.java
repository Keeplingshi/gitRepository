package com.system.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {

	public T doGetById(Serializable id) throws Exception;
	
	public boolean doSave(T t) throws Exception;
	
	public boolean doUpdate(T t) throws Exception;
	
	public boolean doDeleteById(Serializable id);
	
	public List<T> doGetFilterList()throws Exception;
	
}
