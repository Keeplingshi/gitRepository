package com.cb.service;

import java.util.List;

import com.cb.entity.User;
import com.system.util.PageInfo;

/**
 * 账户服务层接口
 * @author chen
 *
 */
public interface IUserService{

	/**
	 * 通过id获取用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User doGetById(String id)throws Exception;
	
	/**
	 * 获取用户列表
	 * @return
	 * @throws Exception
	 */
	public List<User> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<User> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 根据查询条件返回用户列表
	 * @param pageInfo 分页
	 * @param authority 权限
	 * @param searchText 关键字
	 * @return
	 * @throws Exception
	 */
	public List<User> doSearchUserPageList(PageInfo pageInfo,String roleId,String searchText)throws Exception;
	
	/**
	 * 保存用户
	 * @param User
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(User user) throws Exception;
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
	/**
	 * 删除多个用户
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteByIds(String[] ids)throws Exception;
	
	/**
	 * 验证用户名密码，如果通过，返回User，不通过，返回null
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean doCheckUserPassword(String username,char[] password)throws Exception;
	
	/**
	 * 通过用户名获取User实体
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public User doGetUserByUsername(String username)throws Exception;
}
