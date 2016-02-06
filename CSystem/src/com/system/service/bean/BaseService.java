package com.system.service.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.bean.BaseDao;
import com.system.service.IBaseService;

@Transactional
public class BaseService<T> implements IBaseService<T>{

	@Resource private BaseDao<T> baseDao;

	@Override
	public T doGetById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.getById(id);
	}

	@Override
	public boolean doSave(T t) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.save(t);
	}

	@Override
	public boolean doUpdate(T t) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.update(t);
	}

	@Override
	public boolean doDeleteById(Serializable id) {
		// TODO Auto-generated method stub
		try{
			baseDao.deleteById(id);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}

	@Override
	public List<T> doGetFilterList(DetachedCriteria detachedCriteria) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.getFilterList(detachedCriteria);
	}

}
