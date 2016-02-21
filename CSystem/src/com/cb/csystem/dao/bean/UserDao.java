package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IUserDao;
import com.cb.csystem.domain.UserDomain;
import com.cb.system.dao.bean.BaseDao;

@Repository
public class UserDao extends BaseDao<UserDomain> implements IUserDao{

}
