package com.cb.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 拷贝工具类
 * @author chen
 *
 */
public class CopyUtil {

	/**
	 * list拷贝函数，将List<poObj> poList转换为List<voObj> voList
	 * @param entityList 要转换的list
	 * @param domainClazz 希望转换成的对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List copyList (List <? extends Object> entityList , Class domainClazz){
		
		List domainList=new ArrayList();	
		Object domainObj =null;
		for(Object entityObj:entityList){
			try {
				domainObj = domainClazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(entityObj, domainObj);
			domainList.add(domainObj);
		}
		return domainList;
	}
	
}
