package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.IRoleDao;
import com.cb.entity.Role;
import com.system.dao.bean.BaseDao;

@Repository
public class RoleDao extends BaseDao<Role> implements IRoleDao{

}
