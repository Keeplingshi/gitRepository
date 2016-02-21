package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.ICollegeDao;
import com.cb.csystem.domain.CollegeDomain;
import com.cb.system.dao.bean.BaseDao;

@Repository
public class CollegeDao extends BaseDao<CollegeDomain> implements ICollegeDao{

}
