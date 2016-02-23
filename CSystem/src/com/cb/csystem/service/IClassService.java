package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

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

	/**
	 * 多选删除
	 * @param classIds
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteByIds(String[] classIds)throws Exception;

	/**
	 * 根据专业查找班级
	 * @param major_id
	 * @return
	 */
	public List<SelectItem> dogetClasssByMajorId(String major_id)throws Exception;
	
	/**
	 * 根据学号设置班长
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public boolean doSetMonitor(String stuId)throws Exception;
	
	/**
	 * 获取班级班长
	 * @param classDomain
	 * @return
	 * @throws Exception
	 */
	public StudentDomain doGetMonitor(ClassDomain classDomain)throws Exception;
}