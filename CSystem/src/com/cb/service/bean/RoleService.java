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
import com.system.dao.bean.BaseDao;
import com.system.service.bean.BaseService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class RoleService extends BaseService<Role> implements IRoleService{

	@Resource private IRoleDao roleDao;

	/**
	 * @see IRoleService#doDeleteRoleById(String)
	 */
	@Override
	public Role doGetRoleById(String id) throws Exception {
		// TODO Auto-generated method stub
		return super.doGetById(id);
	}

	/**
	 * @see IRoleService#doGetRoleList()
	 */
	@Override
	public List<Role> doGetRoleList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Role.class);
		List<Role> roleList=super.doGetFilterList(detachedCriteria);
		
		return roleList;
	}

	/**
	 * @see IRoleService#doSaveRole(Role)
	 */
	@Override
	public boolean doSaveRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		
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
