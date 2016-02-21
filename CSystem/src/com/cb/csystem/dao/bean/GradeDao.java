package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IGradeDao;
import com.cb.csystem.domain.GradeDomain;
import com.cb.system.dao.bean.BaseDao;

/**
 * @author chen
 *
 */
@Repository
public class GradeDao extends BaseDao<GradeDomain> implements IGradeDao{

}
