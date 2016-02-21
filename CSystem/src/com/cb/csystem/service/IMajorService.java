package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.MajorDomain;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

public interface IMajorService {
	
	/**
	 * 通过id获取专业
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MajorDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取专业列表
	 * @return
	 * @throws Exception
	 */
	public List<MajorDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<MajorDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存专业
	 * @param MajorDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(MajorDomain majorDomain) throws Exception;
	
	/**
	 * 删除专业
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;

	/**
	 * 搜索功能
	 * @param pageInfo
	 * @param collegeId
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	public List<MajorDomain> doSearchmajorPageList(PageInfo pageInfo,
			String collegeId, String searchText)throws Exception;

	/**
	 * 根据学院id获取专业
	 * @param collegeId
	 * @return
	 */
	public List<SelectItem> dogetMajorsByCollegeId(String collegeId);
}
