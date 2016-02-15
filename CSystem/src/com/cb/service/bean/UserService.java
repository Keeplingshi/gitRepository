package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.IUserDao;
import com.cb.domain.UserDomain;
import com.cb.service.IUserService;
import com.system.util.PageInfo;

/**
 * 账户服务层
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class UserService implements IUserService{

	@Resource private IUserDao userDao;
	
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
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(UserDomain.class);
		detachedCriteria.add(Restrictions.eq("username", username.trim()));
		
		List<UserDomain> userList=userDao.getFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			UserDomain user=userList.get(0);
			//判断密码是否等于
			if((String.valueOf(password)).equals(user.getPassword())){
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
		if(roleId!=null&&!"".equals(roleId)){
			detachedCriteria.add(Restrictions.eq("role.id", roleId));
		}
		if(searchText!=null&&!"".equals(searchText)){
			detachedCriteria.add(Restrictions.like("username", "%"+searchText+"%"));
		}
		
		List<UserDomain> userList=userDao.getPageList(detachedCriteria, pageInfo);
		
		return userList;
	}

}
