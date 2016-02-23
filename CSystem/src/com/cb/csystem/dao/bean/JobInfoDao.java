package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IJobInfoDao;
import com.cb.csystem.domain.JobInfoDomain;
import com.cb.system.dao.bean.BaseDao;

/**
 * @author chen
 *
 */
@Repository
public class JobInfoDao extends BaseDao<JobInfoDomain> implements IJobInfoDao{

}
