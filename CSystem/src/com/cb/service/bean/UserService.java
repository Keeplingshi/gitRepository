package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.IUserDao;
import com.cb.entity.User;
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
	 * @see IUserService#doGetUserList()
	 */
	@Override
	public List<User> doGetUserList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		List<User> userList=userDao.getFilterList(detachedCriteria);
		
		return userList;
	}

	/**
	 * @see IUserService#doSaveUser(UserDomain)
	 */
	@Override
	public boolean doSaveUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
		//判断是否为新用户，如果是，新增，否则更新
		if(user.getId()==null){
			return userDao.save(user);
		}else{
			return userDao.update(user);
		}
	}

	/**
	 * @see IUserService#doGetUserById(String)
	 */
	@Override
	public User doGetUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return userDao.getById(id);
	}

	/**
	 * @see IUserService#doDeleteUserById(String)
	 */
	@Override
	public boolean doDeleteUserById(String id) throws Exception {
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
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("username", username.trim()));
		
		List<User> userList=userDao.getFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			User user=userList.get(0);
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
	public User doGetUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("username", username.trim()));
		
		List<User> userList=userDao.getFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			User user=userList.get(0);
			return user;
		}
		
		return null;
	}

	/**
	 * @see IUserService#doGetPageList(DetachedCriteria, PageInfo)
	 */
	@Override
	public List<User> doGetUserPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		List<User> userList=userDao.getPageList(detachedCriteria, pageInfo);
		
		return userList;
	}

	/**
	 * @see IUserService#doDeleteUsersByIds(String[])
	 */
	@Override
	public boolean doDeleteUsersByIds(String[] ids) throws Exception {
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
	public List<User> doSearchUserPageList(PageInfo pageInfo,
			Integer authority, String searchText) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		if(authority!=null){
			detachedCriteria.add(Restrictions.eq("authority", authority));
		}
		if(searchText!=null&&searchText!=""){
			detachedCriteria.add(Restrictions.like("username", "%"+searchText+"%"));
		}
		
		List<User> userList=userDao.getPageList(detachedCriteria, pageInfo);
		
		return userList;
	}

}
