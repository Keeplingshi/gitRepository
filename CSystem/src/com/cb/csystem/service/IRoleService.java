package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.RoleDomain;

public interface IRoleService{
	/**
	 * 通过id获取角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RoleDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public List<RoleDomain> doGetFilterList() throws Exception;
	
	/**
	 * 保存角色
	 * @param RoleDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(RoleDomain role) throws Exception;
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
	/**
	 * 根据value值获取角色
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public RoleDomain doGetRoleByAuthority(Integer authority)throws Exception;
}
