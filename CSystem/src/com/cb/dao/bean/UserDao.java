package com.cb.dao.bean;

import org.springframework.stereotype.Repository;
import com.cb.dao.IUserDao;
import com.cb.entity.User;
import com.system.dao.bean.BaseDao;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao{

}
