package com.cb.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cb.service.UserService;
import com.cb.util.ApplicaionContextUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext applicationContext=ApplicaionContextUtil.getApplicationContext();
		UserService userService=(UserService)applicationContext.getBean("userService");
		userService.sayHello();
	}

}
