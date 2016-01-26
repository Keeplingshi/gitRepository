package com.cb.view;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cb.util.*;
import com.cb.domain.Course;
import com.cb.domain.Studcourse;
import com.cb.domain.Student;
public class TestMain2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//这里我们使用增强的HibernateUtil来完成curd.
	/*	String hql="select sname,saddress from Student where sdept=? and sage>?";
		String parameters[]={"计算机系","3"};
		List<Object[]> list= HibernateUtil.executeQuery(hql,parameters);
		for(Object[] s: list){
			System.out.println(s[0].toString()+" "+s[1].toString());
		}*/
		
		//使用工具分页
	/*	String hql="select sname,saddress from Student order by sage";
		String parameters[]=null;
		List<Object[]> list= HibernateUtil.
		executeQueryByPage(hql, parameters, 2, 3) ;
		for(Object[] s: list){
			System.out.println(s[0].toString()+" "+s[1].toString());
		}*/
		
		/*添加*/
	/*	Course c=new Course();
		c.setCname("servlet");
		c.setCid(4L);
		HibernateUtil.save(c);*/
		
		//调用修改/删除
	/*	String hql="update Student set sage=sage+1 where sdept=?";
		String parameters[]={"计算机系"};
		try {
			HibernateUtil.executeUpdate(hql, parameters);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}*/
		//请显示所有选择了21号课程的学生信息
	/*	String hql="select student.sname,student.sdept from Studcourse where course.cid=?";
		String parameters[]={"21"};
		List<Object[]> list=HibernateUtil.executeQuery(hql, parameters);
		for(Object[] s:list){
			System.out.println(s[0].toString()+" "+s[1].toString());
		}*/
		
	/*	String hql="from Studcourse where course.cid=21";
		List<Studcourse> list=HibernateUtil.executeQuery(hql, null);
		//懒加载我们有一个章节详解.
		for(Studcourse sc:list){
			System.out.println(sc.getGrade()+sc.getStudent().getSname());
		}*/

		//查询年龄大于10岁的学生 criteria
		
		Session s=HibernateUtil.getCurrentSession();
		Transaction tx=s.beginTransaction();
		Criteria cri=s.createCriteria(Student.class);
		//添加检索条件
		cri.add(Restrictions.gt("sage", new Long(10)));
		List<Student> list=cri.list();
		for(Student s1: list){
			System.out.println(s1.getSname());
		}
		
		tx.commit();
	}

}
