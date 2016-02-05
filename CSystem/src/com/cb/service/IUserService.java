package com.cb.service;

import java.util.List;

import com.cb.domain.UserDomain;
import com.cb.entity.User;
import com.system.service.IBaseService;

public interface IUserService extends IBaseService<User>{

	public UserDomain doGetUser(String id)throws Exception;
	
	public List<UserDomain> doGetUserList() throws Exception;
	
	public boolean doSaveUser(UserDomain userDomain) throws Exception;
}
