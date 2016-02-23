package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IJobInfoDao;
import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.service.IJobInfoService;
import com.cb.system.util.PageInfo;

/**
 * 就业信息服务层
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class JobInfoService implements IJobInfoService{

	@Resource private IJobInfoDao jobInfoDao;
	
	/**
	 * @see com.cb.csystem.service.IJobInfoService#doGetById(java.lang.String)
	 */
	@Override
	public JobInfoDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doGetFilterList()
	 */
	@Override
	public List<JobInfoDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doGetPageList(com.cb.system.util.PageInfo)
	 */
	@Override
	public List<JobInfoDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doSave(com.cb.csystem.domain.JobInfoDomain)
	 */
	@Override
	public boolean doSave(JobInfoDomain jobInfoDomain) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doSearchjobInfoPageList(com.cb.system.util.PageInfo, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<JobInfoDomain> doSearchjobInfoPageList(PageInfo pageInfo,
			String searchText, String sortMode, String sortValue)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doDeleteByIds(java.lang.String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] jobInfoIds) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
