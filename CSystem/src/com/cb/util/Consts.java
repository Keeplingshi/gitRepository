package com.cb.util;

import com.system.util.PathUtil;

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
	public static final String STUDENTEXCEL="student.xls";
	
	/**
	 * student表下载模板
	 */
	public static final String DOWNLOAD_PATH=PathUtil.getWebappPath()+"downloadTemplate//";
	
	/**
	 * student导出学生信息文件路径
	 */
	public static final String DBTOEXCEL_PATH=PathUtil.getWebappPath()+"downloadStudnetInfo//";
	

}
