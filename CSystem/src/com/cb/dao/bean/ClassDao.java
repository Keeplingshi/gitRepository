package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.IClassDao;
import com.cb.domain.ClassDomain;
import com.system.dao.bean.BaseDao;

@Repository
public class ClassDao extends BaseDao<ClassDomain> implements IClassDao{

}
