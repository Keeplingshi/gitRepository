package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.CollegeDomain;
import com.cb.system.util.PageInfo;

public interface ICollegeService {

	/**
	 * 通过id获取学院
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CollegeDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取学院列表
	 * @return
	 * @throws Exception
	 */
	public List<CollegeDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<CollegeDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存学院
	 * @param CollegeDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(CollegeDomain collegeDomain) throws Exception;
	
	/**
	 * 删除学院
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
		
}
