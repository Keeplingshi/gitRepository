package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.IRoleDao;
import com.cb.domain.RoleDomain;
import com.system.dao.bean.BaseDao;

@Repository
public class RoleDao extends BaseDao<RoleDomain> implements IRoleDao{

}
