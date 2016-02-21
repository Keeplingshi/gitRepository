package com.cb.system.service.bean;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.system.dao.bean.BaseDao;
import com.cb.system.service.IBaseService;
import com.cb.system.util.PageInfo;

/**
 * 基础服务
 * @author chen
 *
 * @param <T>
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public abstract class BaseService<T> implements IBaseService<T>{

	//@Resource private BaseDao<T> baseDao;

	public abstract BaseDao<T> getBaseDao();  
	
	/**
	 * @see IBaseService#doGetById(Serializable)
	 */
	@Override
	public T doGetById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return getBaseDao().getById(id);
	}

	/**
	 * @see IBaseService#doSave(Object)
	 */
	@Override
	public boolean doSave(T t) throws Exception {
		// TODO Auto-generated method stub
		return getBaseDao().save(t);
	}

	/**
	 * @see IBaseService#doUpdate(Object)
	 */
	@Override
	public boolean doUpdate(T t) throws Exception {
		// TODO Auto-generated method stub
		return getBaseDao().update(t);
	}

	/**
	 * @see IBaseService#doDeleteById(Serializable)
	 */
	@Override
	public boolean doDeleteById(Serializable id) {
		// TODO Auto-generated method stub
		try{
			getBaseDao().deleteById(id);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}

	/**
	 * @see IBaseService#doGetFilterList(DetachedCriteria)
	 */
	@Override
	public List<T> doGetFilterList(DetachedCriteria detachedCriteria) throws Exception {
		// TODO Auto-generated method stub
		return getBaseDao().getFilterList(detachedCriteria);
	}

	/**
	 * @see IBaseService#doGetPageList(DetachedCriteria, PageInfo)
	 */
	@Override
	public List<T> doGetPageList(DetachedCriteria detachedCriteria,
			PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		return getBaseDao().getPageList(detachedCriteria, pageInfo);
	}

	
	
//调用时需要在子类加入下面方法
//	/**
//	 * 返回基类baseDao
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public BaseDao<User> getBaseDao() {
//		// TODO Auto-generated method stub
//		return (BaseDao<User>) userDao;
//	}
}
