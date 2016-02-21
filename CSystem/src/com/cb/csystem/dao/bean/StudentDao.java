package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IStudentDao;
import com.cb.csystem.domain.StudentDomain;
import com.cb.system.dao.bean.BaseDao;

@Repository
public class StudentDao extends BaseDao<StudentDomain> implements IStudentDao{

}
