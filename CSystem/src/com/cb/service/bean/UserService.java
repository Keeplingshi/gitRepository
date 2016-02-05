package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.bean.UserDao;
import com.cb.domain.UserDomain;
import com.cb.entity.User;
import com.cb.service.IUserService;
import com.system.service.bean.BaseService;
import com.system.util.CopyUtil;

@Service
public class UserService extends BaseService<User> implements IUserService{

	@Resource private UserDao userDao;

	/**
	 * 获取用户列表
	 */
	@Override
	public List<UserDomain> doGetUserList() throws Exception {
		// TODO Auto-generated method stub
		List<User> userList=super.doGetFilterList();
		
		@SuppressWarnings("unchecked")
		List<UserDomain> userDomains=CopyUtil.copyList(userList, UserDomain.class);
		
		return userDomains;
	}

	/**
	 * 保存用户
	 */
	@Override
	public boolean doSaveUser(UserDomain userDomain) throws Exception {
		// TODO Auto-generated method stub
		
		User user=new User();
		BeanUtils.copyProperties(userDomain,user);
		
		return super.doSave(user);
	}
	
}
