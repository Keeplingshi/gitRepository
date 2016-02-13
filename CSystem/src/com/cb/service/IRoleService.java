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
	public Role doGetById(String id)throws Exception;
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public List<Role> doGetFilterList() throws Exception;
	
	/**
	 * 保存角色
	 * @param Role
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(Role role) throws Exception;
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
}
