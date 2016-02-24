package com.cb.csystem.util;

import java.util.List;

import com.cb.csystem.domain.CodeBookDomain;
import com.cb.csystem.service.ICodeBookService;
import com.cb.system.util.SpringContextUtil;

/**
 * @author chen
 *
 */
public class CodeBookHelper {

	private static ICodeBookService codeBookService=(ICodeBookService)SpringContextUtil.getBean("codeBookService");
	
	/**
	 * 根据值和类型获取名称
	 * @param value
	 * @param type
	 * @return
	 */
	public static String getNameByValueAndType(String value,String type)
	{
		String name="";
		try {
			name = codeBookService.doGetNameByValueAndType(value, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * 根据名称和类型获取值
	 * @param name
	 * @param type
	 * @return
	 */
	public static String getValueByNameAndType(String name,String type)
	{
		String value="";
		try {
			value = codeBookService.doGetValueByNameAndType(name, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 通过值和类型获取id
	 * @param value
	 * @param type
	 * @return
	 */
	public static String getIdByValueAndType(String value,String type)
	{
		String id="";
		try {
			id = codeBookService.doGetIdByValueAndType(value, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 根据类型获取List
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static List<CodeBookDomain> getCodeBookByType(String type)throws Exception
	{
		return codeBookService.doGetCodeBookByType(type);
	}
	
}
