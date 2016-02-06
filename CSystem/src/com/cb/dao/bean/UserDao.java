package com.cb.dao.bean;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.IUserDao;
import com.cb.entity.User;
import com.system.dao.bean.BaseDao;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao{

}
