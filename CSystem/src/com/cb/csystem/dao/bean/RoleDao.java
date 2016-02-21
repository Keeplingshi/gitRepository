package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IRoleDao;
import com.cb.csystem.domain.RoleDomain;
import com.cb.system.dao.bean.BaseDao;

@Repository
public class RoleDao extends BaseDao<RoleDomain> implements IRoleDao{

}
