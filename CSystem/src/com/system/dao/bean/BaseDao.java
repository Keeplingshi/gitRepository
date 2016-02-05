package com.system.dao.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.system.dao.IBaseDao;

@Repository
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
	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		if (id == null){
			return null;
		}
        return (T)getSession().get(entityClass, id);
	}

	@Override
	public boolean save(T t) {
		// TODO Auto-generated method stub
		
		Session session=getSession();
		try{
			session.save(t);
			session.flush();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean update(T t) {
		// TODO Auto-generated method stub
		
		Session session=getSession();
		try{
			session.update(t);
			session.flush();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		getSession().delete(t);
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		
		T t=getById(id);
		if(t!=null){
			delete(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getFilterList() {
		// TODO Auto-generated method stub
		
		Criteria criteria=getSession().createCriteria(entityClass);
		
		return criteria.list();
	}

}
