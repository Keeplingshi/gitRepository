package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.DisciplineTypeDomain;

/**
 * 违纪类型基础服务类
 * @author chenbin
 *
 */
public interface IDisciplineTypeService {

	/**
	 * 通过id获取违纪类型
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DisciplineTypeDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取违纪类型列表
	 * @return
	 * @throws Exception
	 */
	public List<DisciplineTypeDomain> doGetFilterList() throws Exception;
	
	/**
	 * 保存违纪类型
	 * @param disciplineTypeDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(DisciplineTypeDomain disciplineTypeDomain) throws Exception;
	
	/**
	 * 删除违纪类型
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
}
