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
	 * @see IRoleService#doDeleteById(String)
	 */
	@Override
	public Role doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return roleDao.getById(id);
	}

	/**
	 * @see IRoleService#doGetFilterList()
	 */
	@Override
	public List<Role> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Role.class);
		List<Role> roleList=roleDao.getFilterList(detachedCriteria);
		
		return roleList;
	}

	/**
	 * @see IRoleService#doSave(Role)
	 */
	@Override
	public boolean doSave(Role role) throws Exception {
		// TODO Auto-generated method stub
		
		if(role.getId()==null){
			return roleDao.save(role);
		}else{
			return roleDao.update(role);
		}
	}

	/**
	 * @see IRoleService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return roleDao.deleteById(id);
	}
	
}
