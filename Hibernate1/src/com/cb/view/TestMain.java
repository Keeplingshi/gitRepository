package com.cb.view;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cb.domain.Employee;
import com.cb.util.HibernateUtil;
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
		
//		getAndload();
		
//		queryTest();
		
//		criteriaTest();
		
	}

	/**
	 * Criteria的用法
	 */
	public static void criteriaTest() {
		
		Session session=HibernateUtil.getCurrentSession();
		Transaction transaction = null;
		
		try{
			transaction=session.beginTransaction();
			
			Criteria criteria=session.createCriteria(Employee.class);
			//排序
			criteria.addOrder(Order.asc("id"));
			//添加过滤条件
			criteria.add(Restrictions.eq("name", "cb"));
			List<Employee> employeeList = criteria.list();
			for(Employee employee:employeeList){
				System.out.println(employee.getEmail());
			}
			
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭session
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}

	/**
	 * Query的用法
	 */
	public static void queryTest() {
		Session session=HibernateUtil.getCurrentSession();
		Transaction transaction = null;
		
		try{
			transaction=session.beginTransaction();
			
			//Employee是实体名，不是数据库中的表名，name可以使类的属性名或表的字段，但是按照hibernate规定，还是使用类的属性名
			Query query=session.createQuery("from Employee where name='cb'");
			
//			//如果使用uniqueResult();查出两个值，则会报错，抛出异常
//			Employee employee=(Employee) query.uniqueResult();
//			System.out.println(employee.getEmail());
			
			List<Employee> employeeList=query.list();
			for(Employee employee:employeeList){
				System.out.println(employee.getEmail());
			}
			
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭session
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}
	
	/**
	 * get与load的区别
	 * 如何选择使用哪个: 如果你确定DB中有这个对象就用load(),不确定就用get()（这样效率高）,通过id查找一个对象用load
	 */
	public static void getAndload() {
		Session session = null;
		try {
			session = MySessionFactory.getSessionFactory().openSession();
			
			Employee emp = (Employee) session.get(Employee.class, 76);
			System.out.println("get不报错，返回null      ："+emp);
			Employee emp2 = (Employee) session.load(Employee.class, 76);
			System.out.println("load报错                                           ："+emp2);
			
		}finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * //openSession()与getCurrentSession()的区别
	 */
	public static void sessionTest() {
		//openSession()	获取一个新的session
		Session session1=MySessionFactory.getSessionFactory().openSession();
		Session session2=MySessionFactory.getSessionFactory().openSession();
		System.out.println(session1.hashCode()+"\t"+session2.hashCode());
		
		//getCurrentSession()  获取一个线程内同一个session
		//使用getCurrentSession();需要配置hibernate.cfg.xml文件
		Session session3=MySessionFactory.getSessionFactory().getCurrentSession();
		Session session4=MySessionFactory.getSessionFactory().getCurrentSession();
		System.out.println(session3.hashCode()+"\t"+session4.hashCode());
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
/*		SessionFactory sessionFactory=MySessionFactory.getSessionFactory();
		Session session=sessionFactory.openSession();
		
		Transaction transaction=session.beginTransaction();
		//修改一个对象，想得到，在修改
		//load方法是用于获取 指定 主键的对象（记录.）
		//即修改id为1的雇员对象
		Employee emp=(Employee)session.load(Employee.class, id);
		emp.setName("修改名字");
		transaction.commit();*/
		
		Session session=MySessionFactory.getSessionFactory().openSession();
		Transaction transaction=null;
		
		try{
			transaction=session.beginTransaction();
			Employee emp=(Employee)session.load(Employee.class, id);
			emp.setName("修改名字");
			//设置异常，测试hibernate事务回滚
			//int i=9/0;
			
			transaction.commit();
			
		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭session
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
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
