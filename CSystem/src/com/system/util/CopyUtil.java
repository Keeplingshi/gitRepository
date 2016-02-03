package com.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class CopyUtil {

	/**
	 * list拷贝函数，将List<poObj> poList转换为List<voObj> voList
	 * @param poList 要转换的list
	 * @param voClass 希望转换成的对象
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List copyList (List <? extends Object> poList , Class voClass){
		
		List voList=new ArrayList();	
		Object voObj =null;
		for(Object poObj:poList){
			try {
				voObj = voClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(poObj, voObj);
			voList.add(voObj);
		}
		return voList;
	}
	
}
