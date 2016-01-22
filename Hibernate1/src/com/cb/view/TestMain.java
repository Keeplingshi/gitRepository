package com.cb.view;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.cb.domain.Employee;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1、创建Configuration对象，用于读取hibernate.cfg.xml配置文件，并完成初始化
		Configuration configuration=new Configuration().configure();
		//2、创建SessionFactory对象（这是一个会话工厂，是一个重量级对象）
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		//3、创建session 相当于jdbc的connection
		Session session=sessionFactory.openSession();
		//4、对hibernate而言，要求程序员，在进行增删改查的时候使用事务提交
		Transaction transaction=session.beginTransaction();
		//添加一个雇员
		Employee employee=new Employee();
		employee.setName("chenbin");
		employee.setEmail("chenbkeep@163.com");
		employee.setHiredate(new Date());
		//保存
		session.save(employee);
		//提交
		transaction.commit();
		session.close();
	}

}
