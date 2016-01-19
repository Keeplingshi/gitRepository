package com.cb.service.bean;

import java.util.List;

import com.cb.dao.bean.UserDao;
import com.cb.entity.User;
import com.cb.service.IUserService;

public class UserService implements IUserService{

	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public boolean delUser(String id) {
		// TODO Auto-generated method stub
		return userDao.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

}
