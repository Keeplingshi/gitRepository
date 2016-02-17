package com.cb.util;

import com.cb.service.ICodeBookService;

/**
 * @author chen
 *
 */
public class CodeBookHelper {

	private static ICodeBookService codeBookService=(ICodeBookService)SpringContextUtil.getBean("codeBookService");
	
	public static String getNameByValueAndType(String value,String type)
	{
		String name="";
		try {
			name = codeBookService.doGetNameByValueAndType(value, type);
			System.out.println(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
}
