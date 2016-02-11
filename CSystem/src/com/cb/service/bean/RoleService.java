package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.IRoleDao;
import com.cb.domain.RoleDomain;
import com.cb.entity.Role;
import com.cb.service.IRoleService;
import com.system.dao.bean.BaseDao;
import com.system.service.bean.BaseService;
import com.system.util.CopyUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class RoleService extends BaseService<Role> implements IRoleService{

	@Resource private IRoleDao roleDao;

	/**
	 * @see IRoleService#doDeleteRoleById(String)
	 */
	@Override
	public RoleDomain doGetRoleById(String id) throws Exception {
		// TODO Auto-generated method stub
		Role role=super.doGetById(id);
		RoleDomain roleDomain=new RoleDomain();
		BeanUtils.copyProperties(role, roleDomain);
		return roleDomain;
	}

	/**
	 * @see IRoleService#doGetRoleList()
	 */
	@Override
	public List<RoleDomain> doGetRoleList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Role.class);
		List<Role> roleList=super.doGetFilterList(detachedCriteria);
		
		@SuppressWarnings("unchecked")
		List<RoleDomain> roleDomains=CopyUtil.copyList(roleList, RoleDomain.class);
		return roleDomains;
	}

	/**
	 * @see IRoleService#doSaveRole(RoleDomain)
	 */
	@Override
	public boolean doSaveRole(RoleDomain roleDomain) throws Exception {
		// TODO Auto-generated method stub
		
		Role role=new Role();
		BeanUtils.copyProperties(roleDomain, role);
		
		if(role.getId()==null){
			return super.doSave(role);
		}else{
			return super.doUpdate(role);
		}
	}

	/**
	 * @see IRoleService#doDeleteRoleById(String)
	 */
	@Override
	public boolean doDeleteRoleById(String id) throws Exception {
		// TODO Auto-generated method stub
		return super.doDeleteById(id);
	}

	/**
	 * 返回基类baseDao
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaseDao<Role> getBaseDao() {
		// TODO Auto-generated method stub
		return (BaseDao<Role>) roleDao;
	}
	
}
