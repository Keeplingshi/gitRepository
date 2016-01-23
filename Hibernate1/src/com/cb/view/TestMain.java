package com.cb.view;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.cb.domain.Employee;
import com.cb.util.MySessionFactory;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Employee employee=new Employee();
//		employee.setName("chenbin");
//		employee.setEmail("chenbkeep@163.com");
//		employee.setHiredate(new Date());
//		
//		addEmployee(employee);
		
//		Integer id=1;
//		updateEmployee(id);
//		delEmp(id);
		
//		SessionFactory sessionFactory= MySessionFactory.getSessionFactory();
//		Session session=sessionFactory.openSession();
//		//查询可以不使用事务
//		Employee emp=(Employee) session.load(Employee.class, 1);
//		System.out.println(emp.getName()+" "+emp.getEmail());
//		session.close();
	}
	
	/**
	 * 删除雇员
	 * @param id
	 */
	public static void delEmp(Integer id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory= MySessionFactory.getSessionFactory();
		Session session=sessionFactory.openSession();
		//删除一个雇员,先得到，再修改
		Transaction ts=session.beginTransaction();
		
		Employee emp=(Employee) session.load(Employee.class, id);
		session.delete(emp);
		ts.commit();
	}
	
	/**
	 * 修改雇员
	 */
	public static void updateEmployee(Integer id)
	{
		SessionFactory sessionFactory=MySessionFactory.getSessionFactory();
		Session session=sessionFactory.openSession();
		
		Transaction transaction=session.beginTransaction();
		//修改一个对象，想得到，在修改
		//load方法是用于获取 指定 主键的对象（记录.）
		//即修改id为1的雇员对象
		Employee emp=(Employee)session.load(Employee.class, id);
		emp.setName("修改名字");
		transaction.commit();
	}

	/**
	 * 添加雇员
	 */
	public static void addEmployee(Employee employee)
	{
		//1、创建Configuration对象，用于读取hibernate.cfg.xml配置文件，并完成初始化
		Configuration configuration=new Configuration().configure();
		//2、创建SessionFactory对象（这是一个会话工厂，是一个重量级对象）
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		//3、创建session 相当于jdbc的connection
		Session session=sessionFactory.openSession();
		//4、对hibernate而言，要求程序员，在进行增删改查的时候使用事务提交
		Transaction transaction=session.beginTransaction();
		//添加一个雇员
		//保存
		session.save(employee);
		//提交
		transaction.commit();
		session.close();
	}
	
}
