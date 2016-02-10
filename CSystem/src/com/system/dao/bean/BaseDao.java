package com.system.dao.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.dao.IBaseDao;
import com.system.util.PageInfo;

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
//		Session session=null;
//		try {
//			session = sessionFactory.getCurrentSession();
//		} catch (HibernateException e) {
//			// TODO Auto-generated catch block
//		}
//		if(session==null){
//			session=sessionFactory.openSession();
//		}
//        return session;
    }
	
//	private void closeSession(Session session){
//		//关闭session
//		if(session!=null&&session.isOpen()){
//			session.close();
//		}
//	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		if (id == null){
			return null;
		}
		Session session=getSession();
		T t=(T)session.get(entityClass, id);
		//this.closeSession(session);
        return t;
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
			//this.closeSession(session);
			return false;
		}
		//this.closeSession(session);
		
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
			//this.closeSession(session);
			return false;
		}
		//this.closeSession(session);
		
		return true;
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		Session session=getSession();
		session.delete(t);
		session.flush();
		//this.closeSession(session);
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
	public List<T> getFilterList(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		
		Session session=getSession();
		List<T> list=detachedCriteria.getExecutableCriteria(session).list();
		//this.closeSession(session);
		return list;
	}

	/**
	 * @see IBaseDao#getPageList(DetachedCriteria, PageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPageList(DetachedCriteria detachedCriteria,
			PageInfo pageInfo) {
		// TODO Auto-generated method stub
		
		if(pageInfo==null){
			pageInfo=new PageInfo();
		}
		
		Session session=getSession();
		Criteria criteria=detachedCriteria.getExecutableCriteria(session);
		
		CriteriaImpl criteriaImpl=(CriteriaImpl)criteria;
		Projection projection=criteriaImpl.getProjection();
		//每页显示几条
		int pagePerSize=pageInfo.getSizePerPage();
		//计算总条目数
		int totalCount=((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		//起始序号
		int startIndex=(pageInfo.getCurrentPageNo()-1)*pagePerSize;
		
		//计算分页数
		pageInfo.setTotalPages((int)Math.ceil(((double)totalCount/pagePerSize)));
		
		criteria.setProjection(projection);
		if(projection==null){
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		
		//进行分页查询
		criteria.setFirstResult(startIndex).setMaxResults(pagePerSize);
		List<T> list=criteria.list();
		
		return list;
	}

}
