package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.JobInfoDomain;
import com.cb.system.util.PageInfo;

/**
 * 就业信息表基础服务类
 * @author chen
 *
 */
public interface IJobInfoService {

	/**
	 * 通过id获取就业信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JobInfoDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取就业信息列表
	 * @return
	 * @throws Exception
	 */
	public List<JobInfoDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<JobInfoDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存就业信息
	 * @param JobInfoDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(JobInfoDomain jobInfoDomain) throws Exception;
	
	/**
	 * 删除就业信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;

	/**
	 * 搜索功能
	 * @param pageInfo
	 * @param searchText
	 * @param sortMode
	 * @param sortValue
	 * @return
	 * @throws Exception
	 */
	public List<JobInfoDomain> doSearchjobInfoPageList(PageInfo pageInfo, String searchText,String sortMode,String sortValue)throws Exception;

	/**
	 * 批量删除
	 * @param jobInfoIds
	 * @return
	 */
	public boolean doDeleteByIds(String[] jobInfoIds)throws Exception;
	
}