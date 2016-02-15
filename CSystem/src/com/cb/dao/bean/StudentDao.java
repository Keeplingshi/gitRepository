package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.IStudentDao;
import com.cb.domain.StudentDomain;
import com.system.dao.bean.BaseDao;

@Repository
public class StudentDao extends BaseDao<StudentDomain> implements IStudentDao{

}
