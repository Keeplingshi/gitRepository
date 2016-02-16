package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.IGradeDao;
import com.cb.domain.GradeDomain;
import com.system.dao.bean.BaseDao;

/**
 * @author chen
 *
 */
@Repository
public class GradeDao extends BaseDao<GradeDomain> implements IGradeDao{

}
