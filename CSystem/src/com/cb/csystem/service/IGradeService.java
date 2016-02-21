package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.GradeDomain;
import com.cb.system.util.PageInfo;

/**
 * 年级基础服务类
 * @author chen
 *
 */
public interface IGradeService {

	/**
	 * 通过id获取年级
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GradeDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取年级列表
	 * @return
	 * @throws Exception
	 */
	public List<GradeDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<GradeDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存年级
	 * @param GradeDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(GradeDomain gradeDomain) throws Exception;
	
	/**
	 * 删除年级
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
}
