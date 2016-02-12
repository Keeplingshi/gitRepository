package com.cb.service;

import java.util.List;

import com.cb.entity.Role;

public interface IRoleService{
	/**
	 * 通过id获取角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Role doGetRoleById(String id)throws Exception;
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public List<Role> doGetRoleList() throws Exception;
	
	/**
	 * 保存角色
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public boolean doSaveRole(Role role) throws Exception;
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteRoleById(String id) throws Exception;
	
}
