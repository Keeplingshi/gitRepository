package com.cb.service;

import java.util.List;

import com.cb.domain.RoleDomain;
import com.cb.entity.Role;
import com.system.service.IBaseService;

public interface IRoleService extends IBaseService<Role>{
	/**
	 * 通过id获取角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RoleDomain doGetRoleById(String id)throws Exception;
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws Exception
	 */
	public List<RoleDomain> doGetRoleList() throws Exception;
	
	/**
	 * 保存角色
	 * @param RoleDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSaveRole(RoleDomain roleDomain) throws Exception;
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteRoleById(String id) throws Exception;
	
}
