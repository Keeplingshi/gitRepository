package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.StudentDomain;
import com.cb.system.util.PageInfo;

public interface IStudentService {

	/**
	 * 通过id获取学生
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取学生列表
	 * @return
	 * @throws Exception
	 */
	public List<StudentDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<StudentDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存学生，新增就业
	 * @param StudentDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSaveStuAndOthers(StudentDomain studentDomain) throws Exception;
	
	/**
	 * 保存学生
	 * @param studentDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(StudentDomain studentDomain) throws Exception;
	
	/**
	 * 删除学生
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;

	/**
	 * 搜索功能
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
	public List<StudentDomain> doSearchstudentPageList(PageInfo pageInfo,String gradeId, String collegeId, String majorId, String classId, String searchText,String sortMode,String sortValue)throws Exception;

	/**
	 * 批量删除
	 * @param studentIds
	 * @return
	 */
	public boolean doDeleteByIds(String[] studentIds)throws Exception;
	
	/**
	 * 根据学号获取学生
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public StudentDomain doGetByStudentId(String stuId);
	
	/**
	 * 根据学号获取学生id
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public String doGetIdbystuId(String stuId)throws Exception;

	/**
	 * 查询学生信息
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @return
	 */
	public List<StudentDomain> doSearchstudentList(String gradeId,
			String collegeId, String majorId, String classId)throws Exception;

}
