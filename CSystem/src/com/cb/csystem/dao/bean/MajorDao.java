package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IMajorDao;
import com.cb.csystem.domain.MajorDomain;
import com.cb.system.dao.bean.BaseDao;

@Repository
public class MajorDao extends BaseDao<MajorDomain> implements IMajorDao{

}
