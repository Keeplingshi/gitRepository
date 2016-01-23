package com.cb.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//在使用hibernate开发项目时，保证只有一个SessionFactory
//一个数据库对应一个SessionFactory对象
final public class MySessionFactory {
	
	private static SessionFactory sessionFactory=null;
	
	private static Configuration configuration=null;
	static{
		configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
	}
	
	private MySessionFactory(){};
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
