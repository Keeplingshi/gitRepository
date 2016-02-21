package com.cb.system.util;

import java.io.File;

/**
 * 路径工具类
 * @author chen
 *
 */
public class PathUtil {

	/**
	 * 获取web-inf路径
	 * @return
	 */
	public static String getWebInfPath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		path=path.replace('/', '\\'); // 将/换成\ 
		path=path.replace("classes\\", ""); //去掉class
		return path;
	}
	
	/**
	 * 获取webapp路径
	 * @return
	 */
	public static String getWebappPath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		path=path.replace('/', '\\'); // 将/换成\ 
		path=path.replace("WEB-INF\\classes\\", ""); //去掉class
		return path;
	}
}
