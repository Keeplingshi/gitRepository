package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.bean.UserDao;
import com.cb.entity.User;
import com.cb.service.IUserService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserService implements IUserService{

	@Resource(name="userDao") private UserDao userDao;
	
	@Override
	public List<User> doGetFilterList() {
		// TODO Auto-generated method stub
		return userDao.doGetFilterList();
	}

	
}
