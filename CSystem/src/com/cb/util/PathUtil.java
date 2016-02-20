package com.cb.util;

import java.io.File;

/**
 * 路径工具类
 * @author chen
 *
 */
public class PathUtil {

	/**
	 * 获取类路径
	 * @return
	 */
	public static String getWebInfpath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		path=path.replace('/', '\\'); // 将/换成\ 
		path=path.replace("classes\\", ""); //去掉class
		return path;
	}
	
}
