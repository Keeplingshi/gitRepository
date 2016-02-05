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
@Transactional
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

	@Override
	public boolean doSaveUser(UserDomain userDomain) throws Exception {
		// TODO Auto-generated method stub
		
		User user=new User();
		BeanUtils.copyProperties(userDomain,user);
		
		if(user.getId()==null){
			return super.doSave(user);
		}else{
			return super.doUpdate(user);
		}
	}

	@Override
	public UserDomain doGetUser(String id) throws Exception {
		// TODO Auto-generated method stub
		User user=super.doGetById(id);
		UserDomain userDomain=new UserDomain();
		BeanUtils.copyProperties(user, userDomain);
		return userDomain;
	}

	@Override
	public boolean doDeleteUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return super.doDeleteById(id);
	}
	
}
