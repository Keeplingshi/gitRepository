package com.cb.util;

public class Const {

	public static final String SESSION_USER = "sessionUser";
	public static final String LOGIN = "/login";				//登录地址
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(resources)).*";	//不对匹配该值的访问路径拦截（正则）
}
