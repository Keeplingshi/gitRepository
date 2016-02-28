package com.cb.csystem.util;

import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IClassService;
import com.cb.system.util.SpringContextUtil;

/**
 * 常用函数库，为commonutil.tld提供方法，在jsp页面使用
 * @author chen
 *
 */
public class CommonUtil {

	private static IClassService classService=(IClassService)SpringContextUtil.getBean("classService");
	
	/**
	 * 根据班级ID获取班长
	 * @param classId
	 * @return
	 */
	public static String getMonitorNameByClassId(String classId)
	{
		try {
			StudentDomain studentDomain=classService.doGetMonitor(classService.doGetById(classId));
			if(studentDomain!=null){
				return studentDomain.getName();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
