package com.cb.service;

import java.util.List;

import com.cb.domain.ClassDomain;
import com.system.util.PageInfo;

public interface IClassService {
	/**
	 * 通过id获取班级
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ClassDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取班级列表
	 * @return
	 * @throws Exception
	 */
	public List<ClassDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<ClassDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存班级
	 * @param ClassDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(ClassDomain classDomain) throws Exception;
	
	/**
	 * 删除班级
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
	 * @param  
	 * @return
	 * @throws Exception
	 */
	public List<ClassDomain> doSearchclassPageList(PageInfo pageInfo,
			String collegeId,String majorId, String searchText)throws Exception;
}
