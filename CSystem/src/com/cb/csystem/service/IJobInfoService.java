package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.util.bean.JobInfoCountBean;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

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
	 * 搜索
	 * @param pageInfo
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @param searchText
	 * @param sortMode
	 * @param sortValue
	 * @return
	 * @throws Exception
	 */
	public List<JobInfoDomain> doSearchjobInfoPageList(PageInfo pageInfo,String gradeId,String collegeId
			,String majorId,String classId,String contractStatusId
			,String protocalStateId,String searchText,String sortMode,String sortValue)throws Exception;

	/**
	 * 批量删除
	 * @param jobInfoIds
	 * @return
	 */
	public boolean doDeleteByIds(String[] jobInfoIds)throws Exception;

	/**
	 * 根据签约状态获取协议书状态
	 * @param contractStatusValue	签约状态
	 * @return
	 */
	public List<SelectItem> doGetProtocalState(String contractStatusValue)throws Exception;

	/**
	 * 搜索jobinfo信息
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @return
	 */
	public List<JobInfoDomain> doSearchJobInfoList(String gradeId,
			String collegeId, String majorId, String classId)throws Exception;
	
	/**
	 * 就业信息统计
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public List<SelectItem> doJobInfoCount(String gradeId,
			String collegeId, String majorId, String classId)throws Exception;
	
	/**
	 * 就业信息统计以班级导出
	 * @param gradeId
	 * @param collegeId
	 * @return
	 * @throws Exception
	 */
	public List<JobInfoCountBean> doJobInfoCountByClassInfo(String gradeId,String collegeId)throws Exception;
	
}
