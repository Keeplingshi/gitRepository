package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IUserDao;
import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IRoleService;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.EndecryptUtils;
import com.cb.system.util.PageInfo;
import com.cb.system.util.ValidateUtil;

/**
 * 账户服务层
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class UserService implements IUserService{

	@Resource private IUserDao userDao;
	@Resource private IRoleService roleService;
	
	/**
	 * @see IUserService#doGetFilterList()
	 */
	@Override
	public List<UserDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(UserDomain.class);
		List<UserDomain> userList=userDao.getFilterList(detachedCriteria);
		
		return userList;
	}

	/**
	 * @see IUserService#doSave(UserDomain)
	 */
	@Override
	public boolean doSave(UserDomain user) throws Exception {
		// TODO Auto-generated method stub

		//判断是否为新用户，如果是，新增，否则更新
		if(user.getId()==null){
			return userDao.save(user);
		}else{
			return userDao.update(user);
		}
	}

	/**
	 * @see IUserService#doGetById(String)
	 */
	@Override
	public UserDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return userDao.getById(id);
	}

	/**
	 * @see IUserService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return userDao.deleteById(id);
	}

	/**
	 * @see IUserService#doCheckUserPassword(String, String)
	 */
	@Override
	public boolean doCheckUserPassword(String username, char[] password)
			throws Exception {
		// TODO Auto-generated method stub
		if(ValidateUtil.isEmpty(username)){
			return false;
		}
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(UserDomain.class);
		detachedCriteria.add(Restrictions.eq("username", username.trim()));
		
		List<UserDomain> userList=userDao.getFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			UserDomain user=userList.get(0);
			//判断密码是否等于
			if(EndecryptUtils.md5((String.valueOf(password))).equals(user.getPassword())){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @see IUserService#doGetUserByUsername(String)
	 */
	@Override
	public UserDomain doGetUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(UserDomain.class);
		detachedCriteria.add(Restrictions.eq("username", username.trim()));
		
		List<UserDomain> userList=userDao.getFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			UserDomain user=userList.get(0);
			return user;
		}
		
		return null;
	}

	/**
	 * @see IUserService#doGetPageList(DetachedCriteria, PageInfo)
	 */
	@Override
	public List<UserDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(UserDomain.class);
		List<UserDomain> userList=userDao.getPageList(detachedCriteria, pageInfo);
		
		return userList;
	}

	/**
	 * @see IUserService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=userDao.deleteById(id);
			if(!b){
				return false;
			}
		}
		
		return b;
	}

	/**
	 * @see IUserService#doSearchUserPageList(PageInfo, Integer, String)
	 */
	@Override
	public List<UserDomain> doSearchUserPageList(PageInfo pageInfo,
			String roleId, String searchText) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(UserDomain.class);
		if(ValidateUtil.notEmpty(roleId)){
			detachedCriteria.add(Restrictions.eq("role.id", roleId));
		}
		if(ValidateUtil.notEmpty(searchText)){
			detachedCriteria.add(Restrictions.like("username", searchText,MatchMode.ANYWHERE).ignoreCase());
		}
		
		List<UserDomain> userList=userDao.getPageList(detachedCriteria, pageInfo);
		
		return userList;
	}

	/**
	 * @see com.cb.csystem.service.IUserService#doSetMonitorByClassDomain(com.cb.csystem.domain.StudentDomain, com.cb.csystem.domain.ClassDomain)
	 */
	@Override
	public void doSetMonitorByClassDomain(StudentDomain monitorDomain,
			ClassDomain classDomain) throws Exception {
		// TODO Auto-generated method stub
		
		if(monitorDomain==null||classDomain==null){
			return;
		}
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(UserDomain.class);
		detachedCriteria.add(Restrictions.eq("classDomain.id", classDomain.getId()));
		List<UserDomain> userList=userDao.getFilterList(detachedCriteria);
		//删除原班长账户
		for(UserDomain userDomain:userList){
			userDao.delete(userDomain);
		}
		UserDomain userDomain=new UserDomain();
		userDomain.setUsername(monitorDomain.getStuId());
		userDomain.setPassword(EndecryptUtils.md5(monitorDomain.getStuId()));
		userDomain.setClassDomain(classDomain);
		userDomain.setRole(roleService.doGetRoleByAuthority(Consts.AUTHORITY_MONITOR));
		userDao.save(userDomain);
		
	}

}
