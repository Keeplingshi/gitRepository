package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.IMajorDao;
import com.cb.domain.MajorDomain;
import com.system.dao.bean.BaseDao;

@Repository
public class MajorDao extends BaseDao<MajorDomain> implements IMajorDao{

}
