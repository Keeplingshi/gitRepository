package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IRoleDao;
import com.cb.csystem.domain.RoleDomain;
import com.cb.csystem.service.IRoleService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class RoleService implements IRoleService{

	@Resource private IRoleDao roleDao;

	/**
	 * @see IRoleService#doDeleteById(String)
	 */
	@Override
	public RoleDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return roleDao.getById(id);
	}

	/**
	 * @see IRoleService#doGetFilterList()
	 */
	@Override
	public List<RoleDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(RoleDomain.class);
		List<RoleDomain> roleList=roleDao.getFilterList(detachedCriteria);
		
		return roleList;
	}

	/**
	 * @see IRoleService#doSave(RoleDomain)
	 */
	@Override
	public boolean doSave(RoleDomain role) throws Exception {
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

	/**
	 * @see com.cb.csystem.service.IRoleService#doGetRoleByValue(Integer))
	 */
	@Override
	public RoleDomain doGetRoleByAuthority(Integer authority) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(RoleDomain.class);
		detachedCriteria.add(Restrictions.eq("authority", authority));
		List<RoleDomain> roleList=roleDao.getFilterList(detachedCriteria);
		
		if(roleList.size()==1){
			return roleList.get(0);
		}
		return null;
	}
	
}
