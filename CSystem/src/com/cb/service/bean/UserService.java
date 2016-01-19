package com.cb.service.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cb.dao.bean.UserDao;
import com.cb.domain.UserDomain;
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
	public UserDomain getUser(String id) {
		// TODO Auto-generated method stub
		
		User user=userDao.getUser(id);
		UserDomain userDomain=new UserDomain();
		BeanUtils.copyProperties(user,userDomain);
		
		return userDomain;
	}

	@Override
	public List<UserDomain> getAllUser() {
		// TODO Auto-generated method stub
		
		List<UserDomain> userDomains=new ArrayList<UserDomain>();
		List<User> userList=userDao.getAllUser();
		for(User user:userList)
		{
			UserDomain userDomain=new UserDomain();
			BeanUtils.copyProperties(user,userDomain);
			userDomains.add(userDomain);
		}
		
		return userDomains;
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
