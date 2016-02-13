package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.ICollegeDao;
import com.cb.domain.CollegeDomain;
import com.system.dao.bean.BaseDao;

@Repository
public class CollegeDao extends BaseDao<CollegeDomain> implements ICollegeDao{

}
