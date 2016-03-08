package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.PatyDomain;
import com.cb.system.util.PageInfo;

/**
 * @author Administrator
 *
 */
public interface IPatyService {

	/**
	 * 通过id获取就业信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PatyDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取就业信息列表
	 * @return
	 * @throws Exception
	 */
	public List<PatyDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<PatyDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存就业信息
	 * @param PatyDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(PatyDomain patyDomain) throws Exception;
	
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
	public List<PatyDomain> doSearchPatyPageList(PageInfo pageInfo,String gradeId,String collegeId
			,String majorId,String classId,String searchText,String sortMode,String sortValue)throws Exception;

	/**
	 * 查询列表
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @return
	 */
	public List<PatyDomain> doSearchPatyList(String gradeId, String collegeId,
			String majorId, String classId)throws Exception;

	
}
