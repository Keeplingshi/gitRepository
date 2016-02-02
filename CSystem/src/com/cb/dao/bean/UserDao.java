package com.cb.dao.bean;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cb.dao.IUserDao;
import com.cb.entity.User;

public class UserDao implements IUserDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> doGetFilterList() {
		// TODO Auto-generated method stub
		
		List<User> list=null;
		
		Session session=null;
		Transaction tx=null;
		try {
			session=sessionFactory.getCurrentSession();
			tx=session.beginTransaction();
			
			Criteria criteria=session.createCriteria(User.class);
			list=criteria.list();
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
			
		}
		
		return list;
	}

	
}
