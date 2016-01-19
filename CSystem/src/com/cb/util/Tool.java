package com.cb.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class Tool {

	/** 
	 * 复制集合 
	 * @param <E> 
	 * @param source 
	 * @param destinationClass 
	 * @return 
	 * @throws InstantiationException  
	 * @throws InvocationTargetException  
	 * @throws IllegalAccessException  
	 */  
	public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass) throws IllegalAccessException, InvocationTargetException, InstantiationException{  

		if (source.size()==0) return Collections.emptyList();  
	    List<E> res = new ArrayList<E>(source.size());  
	    for (Object o : source) {  
	        E e = destinationClass.newInstance();  
	        BeanUtils.copyProperties(e, o);  
	        res.add(e);  
	    }  
	    return res;  
	}  
	
}
