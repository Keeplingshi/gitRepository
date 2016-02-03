package com.system.dao.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.system.dao.IBaseDao;

public class BaseDao<T> implements IBaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {  
			this.entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];  
		} else {  
			this.entityClass = null;  
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
        return sessionFactory.getCurrentSession();  
    }

	@SuppressWarnings("unchecked")
	@Override
	public T doGetById(Serializable id) {
		// TODO Auto-generated method stub
		if (id == null){
			return null;
		}
        return (T)getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return (T)getSession().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		getSession().delete(t);
	}

	@Override
	public void doDeleteById(Serializable id) {
		// TODO Auto-generated method stub
		
		T t=doGetById(id);
		if(t!=null){
			delete(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> doGetFilterList() {
		// TODO Auto-generated method stub
		
		Criteria criteria=getSession().createCriteria(entityClass);
		
		return criteria.list();
	}


	
}
