package com.cb.system.dao.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cb.system.dao.IBaseDao;
import com.cb.system.util.PageInfo;

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
	
	/**
	 * 获取session
	 * @return
	 */
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

	/**
	 * @see IBaseDao#getById(Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		if (id == null){
			return null;
		}
		Session session=getSession();
		T t=(T)session.get(entityClass, id);
        return t;
	}

	/**
	 * @see IBaseDao#save(Object)
	 */
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

	/**
	 * @see IBaseDao#update(Object)
	 */
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

	/**
	 * @see IBaseDao#delete(Object)
	 */
	@Override
	public boolean delete(T t) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(t);
			session.flush();			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @see IBaseDao#deleteById(Serializable)
	 */
	@Override
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		
		T t=getById(id);
		if(t!=null){
			return delete(t);
		}
		return false;
	}

	/**
	 * @see IBaseDao#getFilterList(DetachedCriteria)
	 */
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
		int totalCount=0;
		try {
			totalCount = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		//计算分页数
		pageInfo.setTotalPages((int)Math.ceil(((double)totalCount/pagePerSize)));
		
		if(pageInfo.getCurrentPageNo()>pageInfo.getTotalPages()){
			pageInfo.setCurrentPageNo(pageInfo.getTotalPages());
		}
		//起始序号
		int startIndex=(pageInfo.getCurrentPageNo()-1)*pagePerSize;
		
		criteria.setProjection(projection);
		if(projection==null){
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		
		//进行分页查询
		criteria.setFirstResult(startIndex).setMaxResults(pagePerSize);
		List<T> list=criteria.list();
		
		return list;
	}

	/**
	 * @see com.cb.system.dao.IBaseDao#getTotalCount(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public int getTotalCount(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		Session session=getSession();
		Criteria criteria=detachedCriteria.getExecutableCriteria(session);
		int totalCount=0;
		try {
			totalCount = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return totalCount;
	}
	
	/**
	 * @see com.cb.system.dao.IBaseDao#getCountNumber(org.hibernate.criterion.DetachedCriteria, java.lang.String)
	 */
	@Override
	public double getCountNumber(DetachedCriteria detachedCriteria,
			String propertyName) {
		// TODO Auto-generated method stub
		Session session=getSession();
		Criteria criteria=detachedCriteria.getExecutableCriteria(session);
		double countNumber=0;
		try {
			countNumber = ((Double)criteria.setProjection(Projections.count(propertyName)).uniqueResult()).doubleValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return countNumber;
	}

	/**
	 * @see com.cb.system.dao.IBaseDao#getAverageCount(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public double getAverageCount(DetachedCriteria detachedCriteria,String propertyName) {
		// TODO Auto-generated method stub
		Session session=getSession();
		Criteria criteria=detachedCriteria.getExecutableCriteria(session);
		double averageValue=0;
		try{
			averageValue= ((Double)(criteria.setProjection(Projections.avg(propertyName)).uniqueResult())).doubleValue();
		}catch(Exception e){
			averageValue=0;
		}
		return averageValue;
	}

}
