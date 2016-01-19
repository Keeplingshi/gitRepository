package com.cb.dao.bean;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.cb.dao.IUserDao;
import com.cb.entity.User;

public class UserDao implements IUserDao{

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (User)query.uniqueResult();
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public boolean delUser(String id) {
		// TODO Auto-generated method stub
		String hql = "delete User u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		String hql = "update User u set u.userName = ?,u.passwd=?,u.type=? where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getUserName());
		query.setString(1, user.getPasswd());
		query.setString(2, user.getType());
		query.setString(3, user.getId());
		
		return (query.executeUpdate() > 0);
	}

}
