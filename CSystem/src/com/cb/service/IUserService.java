package com.cb.service;

import java.util.List;

import com.cb.domain.UserDomain;
import com.cb.entity.User;
import com.system.service.IBaseService;

public interface IUserService extends IBaseService<User>{

	/**
	 * 通过id获取用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserDomain doGetUserById(String id)throws Exception;
	
	/**
	 * 获取用户列表
	 * @return
	 * @throws Exception
	 */
	public List<UserDomain> doGetUserList() throws Exception;
	
	/**
	 * 保存用户
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSaveUser(UserDomain userDomain) throws Exception;
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteUserById(String id) throws Exception;
	
	
}
