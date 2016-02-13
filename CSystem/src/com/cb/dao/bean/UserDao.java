package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.IUserDao;
import com.cb.domain.UserDomain;
import com.system.dao.bean.BaseDao;

@Repository
public class UserDao extends BaseDao<UserDomain> implements IUserDao{

}
