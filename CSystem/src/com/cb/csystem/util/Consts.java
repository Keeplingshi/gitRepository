package com.cb.csystem.util;

import com.cb.system.util.PathUtil;

/**
 * 常用常量
 * @author chen
 *
 */
public class Consts {

	/**
	 * 成功
	 */
	public static final String SUCCESS="success";
	
	/**
	 * 出错
	 */
	public static final String ERROR="error";
	
	/**
	 * 当前用户
	 */
	public static final String CURRENT_USER="currentUser";
	
	/**
	 * student模板文件名称
	 */
	public static final String STUDENT_EXCEL="student.xls";
	
	/**
	 * 就业信息导出文件名称
	 */
	public static final String JOBINFO_EXCEL="jobinfo.xls";
	
	/**
	 * 就业统计信息导出文件名称
	 */
	public static final String JOBCOUNT_EXCEL="jobcount.xls";
	
	/**
	 * 违纪报表导出文件
	 */
	public static final String DISCIPLINE_EXCEL="违纪报表.xls";
	
	/**
	 * 党建信息
	 */
	public static final String PATYOUT_EXCEL="党建信息.xls";
	
	/**
	 * 党建信息导入模板
	 */
	public static final String PATY_EXCEL="党建信息导入模板.xls";
	
	/**
	 * student表下载模板
	 */
	public static final String DOWNLOAD_PATH=PathUtil.getWebappPath()+"downloadTemplate//";
	
	/**
	 * student导出学生信息文件路径
	 */
	public static final String DBTOEXCEL_PATH=PathUtil.getWebappPath()+"downloadExcelInfo//";
	
	/**
	 * 降序
	 */
	public static final String SORT_DESC="desc";
	
	/**
	 * 升序
	 */
	public static final String SORT_ASC="asc";
	
	/**
	 * 班长    0-否  1-是
	 */
	public static final Integer IS_MONITOR_A=0;
	
	/**
	 * 班长    0-否  1-是
	 */	
	public static final Integer IS_MONITOR_B=1;
	
	/**
	 * 权限  0-管理员  1-辅导员  2-老师  3-班长  4-学生  5-违纪管理员
	 */
	public static final Integer AUTHORITY_ADMIN=0;
	
	/**
	 * 权限  0-管理员  1-辅导员  2-老师  3-班长  4-学生  5-违纪管理员
	 */
	public static final Integer AUTHORITY_INSTRUCTOR=1;
	
	/**
	 * 权限  0-管理员  1-辅导员  2-老师  3-班长  4-学生  5-违纪管理员
	 */
	public static final Integer AUTHORITY_TEACHER=2;
	
	/**
	 * 权限  0-管理员  1-辅导员  2-老师  3-班长  4-学生  5-违纪管理员
	 */
	public static final Integer AUTHORITY_MONITOR=3;
	
	/**
	 * 权限  0-管理员  1-辅导员  2-老师  3-班长  4-学生  5-违纪管理员
	 */
	public static final Integer AUTHORITY_STUDENT=4;
	
	/**
	 * 权限  0-管理员  1-辅导员  2-老师  3-班长  4-学生  5-违纪管理员
	 */
	public static final Integer AUTHORITY_DISCIPLINEADMIN=5;
	
}
