package com.cb.csystem.service;

import java.util.Date;
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

	/**
	 * 违纪搜索
	 * @param pageInfo
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @param disciplineTypeId
	 * @param beginTime
	 * @param endTime
	 * @param searchText
	 * @param sortMode
	 * @param sortValue
	 * @return
	 */
	public List<DisciplineDomain> doSearchPageList(PageInfo pageInfo,
			String gradeId, String collegeId, String majorId, String classId,
			String disciplineTypeId, Date beginTime, Date endTime,
			String searchText, String sortMode, String sortValue) throws Exception;

	/**
	 * 批量删除
	 * @param disciplineIds
	 * @return
	 */
	public boolean doDeleteByIds(String[] disciplineIds)throws Exception;
	
	/**
	 * 搜索
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @param disciplineTypeId
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public List<DisciplineDomain> doSearchList(String gradeId, String collegeId, String majorId, String classId,
			String disciplineTypeId, Date beginTime, Date endTime)throws Exception;
	
	/**
	 * 根据学生查询违纪
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<DisciplineDomain> doSearchByStudent(String studentId)throws Exception;
	
}
