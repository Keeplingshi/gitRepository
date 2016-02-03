package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.bean.UserDao;
import com.cb.domain.UserDomain;
import com.cb.entity.User;
import com.cb.service.IUserService;
import com.system.service.bean.BaseService;
import com.system.util.CopyUtil;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserService extends BaseService<User> implements IUserService{

	@Resource private UserDao userDao;

	@Override
	public List<UserDomain> doGetUserList() throws Exception {
		// TODO Auto-generated method stub
		List<User> userList=super.doGetFilterList();
		
		@SuppressWarnings("unchecked")
		List<UserDomain> userDomains=CopyUtil.copyList(userList, UserDomain.class);
		
		return userDomains;
	}
	
}
