package com.cb.csystem.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 就业信息
 * @author chen
 *
 */
@Entity
@Table(name="JOBINFO")
public class JobInfoDomain {
	
	private String id;
	//private String studentId;	//学生ID，注意，此处不是学号，而是学生表的ID
	private StudentDomain student;
	private Integer contractStatus;	//签约状态
	private String company;	//公司
	private Integer protocalState;	//协议书状态
	private Integer nowState;	//当前状态
	private String city;	//城市
	private Integer salary;	//薪水
	private String note;	//备注
	private String modifyTime;	//最后修改时间
	private Integer isPositive;	//是否积极
	
//	  @Id  
//	    @Column(name="pid")  
//	    @GenericGenerator(name="foreignKey", strategy="foreign", parameters=@Parameter(name="property", value="user"))  
//	    @GeneratedValue(generator="foreignKey", strategy=GenerationType.IDENTITY) 
//	
//	@Id
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name = "system-uuid",strategy="uuid")
//	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	@GenericGenerator(name = "foreignKey",strategy="foreign",parameters=@Parameter(name = "property", value = "student"))
	@GeneratedValue(generator="foreignKey")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	//@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=StudentDomain.class)
	@OneToOne(targetEntity=StudentDomain.class, cascade=CascadeType.ALL, mappedBy="jobInfo")  
    @PrimaryKeyJoinColumn(name="id", referencedColumnName="id") 
	public StudentDomain getStudent() {
		return student;
	}
	public void setStudent(StudentDomain student) {
		this.student = student;
	}
	
	@Column(name = "CONTRACTSTATUS", nullable = true, length = 10)
	public Integer getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}
	
	@Column(name = "COMPANY", nullable = true, length = 200)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Column(name = "PROTOCALSTATE", nullable = true, length = 10)
	public Integer getProtocalState() {
		return protocalState;
	}
	public void setProtocalState(Integer protocalState) {
		this.protocalState = protocalState;
	}
	
	@Column(name = "NOWSTATE", nullable = true, length = 10)
	public Integer getNowState() {
		return nowState;
	}
	public void setNowState(Integer nowState) {
		this.nowState = nowState;
	}
	
	@Column(name = "CITY", nullable = true, length = 200)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
//	@Column(name = "SALARY", nullable = true, length = 100)
//	public String getSalary() {
//		return salary;
//	}
//	public void setSalary(String salary) {
//		this.salary = salary;
//	}
	
	@Column(name = "SALARY", nullable = true, length = 10)
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	@Column(name = "NOTE", nullable = true, length = 1000)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Column(name = "MODIFYTIME", nullable = true, length = 100)
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name = "ISPOSITIVE", nullable = true, length = 10)
	public Integer getIsPositive() {
		return isPositive;
	}
	public void setIsPositive(Integer isPositive) {
		this.isPositive = isPositive;
	}

}
