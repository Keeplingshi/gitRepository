package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IClassDao;
import com.cb.csystem.domain.ClassDomain;
import com.cb.system.dao.bean.BaseDao;

@Repository
public class ClassDao extends BaseDao<ClassDomain> implements IClassDao{

}
