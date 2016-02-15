package com.cb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 学生表
 * @author chen
 *
 */
@Entity
@Table(name="STUDENT")
public class StudentDomain {

	private String id;
	private String stuId;	//学号
	private String name;	//姓名
	private Integer sex;	//性别 0,男性   1,女性
	private Date birthday;	//出生日期
	private Integer politicalStatus;	//政治面貌
	private String IDnumber;	//身份证号
	private String nativePlace;	//籍贯
	private String dormitory;	//宿舍号
	private String gradeId;		//年级
	private String classId;		//班级，通过班级可以查到专业学院
	private String email;	//电子邮件
	private String telephone;	//联系电话
	private String cellphone;	//手机
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "STUID",unique = true, nullable = false, length = 100)
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "SEX", nullable = false, length = 10)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name = "BIRTHDAY", nullable = true, length = 100)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "POLITICALSTATUS", nullable = true, length = 10)
	public Integer getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	
	@Column(name = "IDNUMBER", nullable = true, length = 100)
	public String getIDnumber() {
		return IDnumber;
	}
	public void setIDnumber(String iDnumber) {
		IDnumber = iDnumber;
	}
	
	@Column(name = "NATIVEPLACE", nullable = true, length = 100)
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Column(name = "DORMITORY", nullable = true, length = 100)
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	
	@Column(name = "GRADEID", nullable = true, length = 100)
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	@Column(name = "CLASSID", nullable = true, length = 100)
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	@Column(name = "EMAIL", nullable = true, length = 100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "TELEPHONE", nullable = true, length = 100)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "CELLPHONE", nullable = true, length = 100)
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

}
