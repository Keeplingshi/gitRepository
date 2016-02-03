package com.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class CopyUtil {

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
