package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.bean.UserDao;
import com.cb.domain.UserDomain;
import com.cb.entity.User;
import com.cb.service.IUserService;
import com.system.service.bean.BaseService;
import com.system.util.CopyUtil;
import com.system.util.PageInfo;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class UserService extends BaseService<User> implements IUserService{

	@Resource private UserDao userDao;
	
	/**
	 * @see IUserService#doGetUserList()
	 */
	@Override
	public List<UserDomain> doGetUserList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		List<User> userList=super.doGetFilterList(detachedCriteria);
		
		@SuppressWarnings("unchecked")
		List<UserDomain> userDomains=CopyUtil.copyList(userList, UserDomain.class);
		
		return userDomains;
	}

	/**
	 * @see IUserService#doSaveUser(UserDomain)
	 */
	@Override
	public boolean doSaveUser(UserDomain userDomain) throws Exception {
		// TODO Auto-generated method stub
		
		User user=new User();
		BeanUtils.copyProperties(userDomain,user);
		
		//判断是否为新用户，如果是，新增，否则更新
		if(user.getId()==null){
			return super.doSave(user);
		}else{
			return super.doUpdate(user);
		}
	}

	/**
	 * @see IUserService#doGetUserById(String)
	 */
	@Override
	public UserDomain doGetUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		User user=super.doGetById(id);
		UserDomain userDomain=new UserDomain();
		BeanUtils.copyProperties(user, userDomain);
		return userDomain;
	}

	/**
	 * @see IUserService#doDeleteUserById(String)
	 */
	@Override
	public boolean doDeleteUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return super.doDeleteById(id);
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
		
		List<User> userList=super.doGetFilterList(detachedCriteria);
		
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
	public UserDomain doGetUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("username", username.trim()));
		
		List<User> userList=super.doGetFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			User user=userList.get(0);
			UserDomain userDomain=new UserDomain();
			BeanUtils.copyProperties(user, userDomain);
			return userDomain;
		}
		
		return null;
	}

	@Override
	public List<UserDomain> doGetUserPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		List<User> userList=super.doGetPageList(detachedCriteria, pageInfo);
		
		@SuppressWarnings("unchecked")
		List<UserDomain> userDomains=CopyUtil.copyList(userList, UserDomain.class);
		return userDomains;
	}
	
	
}
