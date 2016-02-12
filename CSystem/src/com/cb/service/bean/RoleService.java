package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.IRoleDao;
import com.cb.entity.Role;
import com.cb.service.IRoleService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class RoleService implements IRoleService{

	@Resource private IRoleDao roleDao;

	/**
	 * @see IRoleService#doDeleteRoleById(String)
	 */
	@Override
	public Role doGetRoleById(String id) throws Exception {
		// TODO Auto-generated method stub
		return roleDao.getById(id);
	}

	/**
	 * @see IRoleService#doGetRoleList()
	 */
	@Override
	public List<Role> doGetRoleList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Role.class);
		List<Role> roleList=roleDao.getFilterList(detachedCriteria);
		
		return roleList;
	}

	/**
	 * @see IRoleService#doSaveRole(Role)
	 */
	@Override
	public boolean doSaveRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		
		if(role.getId()==null){
			return roleDao.save(role);
		}else{
			return roleDao.update(role);
		}
	}

	/**
	 * @see IRoleService#doDeleteRoleById(String)
	 */
	@Override
	public boolean doDeleteRoleById(String id) throws Exception {
		// TODO Auto-generated method stub
		return roleDao.deleteById(id);
	}
	
}
