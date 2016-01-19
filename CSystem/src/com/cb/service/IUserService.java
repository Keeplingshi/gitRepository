package com.cb.service;

import java.util.List;

import com.cb.domain.UserDomain;
import com.cb.entity.User;

public interface IUserService {
	
	public UserDomain getUser(String id);
	
	public List<UserDomain> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String id);
	
	public boolean updateUser(User user);
}
