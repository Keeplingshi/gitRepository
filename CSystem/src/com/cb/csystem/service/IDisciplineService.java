package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.DisciplineDomain;
import com.cb.system.util.PageInfo;

/**
 * @author Administrator
 *
 */
public interface IDisciplineService {

	/**
	 * 通过id获取违纪
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DisciplineDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取违纪列表
	 * @return
	 * @throws Exception
	 */
	public List<DisciplineDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<DisciplineDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存违纪
	 * @param DisciplineDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(DisciplineDomain disciplineDomain) throws Exception;
	
	/**
	 * 删除违纪
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
}
