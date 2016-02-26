package com.cb.csystem.util.bean;

/**
 * 就业信息统计类型
 * @author chen
 *
 */
public class JobInfoCountBean {
	
	private String className;		//班级名称
	private int classMemberNum;		//班级人数
	private int contractState_A;	//已签人数
	private int contractState_B;	//未签人数
	private int contractState_C;	//保研人数
	private int contractState_D;	//考研人数
	private int contractState_E;	//考公务员人数
	private int contractState_F;	//出国人数
	private double averageSalary;	//已签平均工资
	private String countDate;	//统计日期
	
	public JobInfoCountBean() {
		super();
	}
	
	public JobInfoCountBean(String className, int classMemberNum,
			int contractState_A, int contractState_B, int contractState_C,
			int contractState_D, int contractState_E, int contractState_F,
			double averageSalary, String countDate) {
		super();
		this.className = className;
		this.classMemberNum = classMemberNum;
		this.contractState_A = contractState_A;
		this.contractState_B = contractState_B;
		this.contractState_C = contractState_C;
		this.contractState_D = contractState_D;
		this.contractState_E = contractState_E;
		this.contractState_F = contractState_F;
		this.averageSalary = averageSalary;
		this.countDate = countDate;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassMemberNum() {
		return classMemberNum;
	}
	public void setClassMemberNum(int classMemberNum) {
		this.classMemberNum = classMemberNum;
	}
	public int getContractState_A() {
		return contractState_A;
	}
	public void setContractState_A(int contractState_A) {
		this.contractState_A = contractState_A;
	}
	public int getContractState_B() {
		return contractState_B;
	}
	public void setContractState_B(int contractState_B) {
		this.contractState_B = contractState_B;
	}
	public int getContractState_C() {
		return contractState_C;
	}
	public void setContractState_C(int contractState_C) {
		this.contractState_C = contractState_C;
	}
	public int getContractState_D() {
		return contractState_D;
	}
	public void setContractState_D(int contractState_D) {
		this.contractState_D = contractState_D;
	}
	public int getContractState_E() {
		return contractState_E;
	}
	public void setContractState_E(int contractState_E) {
		this.contractState_E = contractState_E;
	}
	public int getContractState_F() {
		return contractState_F;
	}
	public void setContractState_F(int contractState_F) {
		this.contractState_F = contractState_F;
	}
	public double getAverageSalary() {
		return averageSalary;
	}
	public void setAverageSalary(double averageSalary) {
		this.averageSalary = averageSalary;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	
	
}
