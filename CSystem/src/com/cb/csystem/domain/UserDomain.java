package com.cb.csystem.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户登录表
 * @author chen
 *
 */
@Entity
@Table(name="USER")
public class UserDomain {

	private String id;	//id
	private String username;	//用户名
	private String password;	//密码
	//private String roleId;
	private RoleDomain role;	//角色
	//private String collegeId;	//学院	辅导员使用
	private CollegeDomain college;
	//private String gradeId;		//年级	辅导员使用
	private GradeDomain grade;
	//private String classId;		//班级	班长账户使用
	private ClassDomain classDomain;
	
	public UserDomain(){
		
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "ID", unique = true, nullable = true, precision = 10, scale = 0)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USERNAME",unique = true, nullable = false, length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ROLEID")
	public RoleDomain getRole() {
		return role;
	}

	public void setRole(RoleDomain role) {
		this.role = role;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "COLLEGEID")
	public CollegeDomain getCollege() {
		return college;
	}

	public void setCollege(CollegeDomain college) {
		this.college = college;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "GRADEID")
	public GradeDomain getGrade() {
		return grade;
	}

	public void setGrade(GradeDomain grade) {
		this.grade = grade;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CLASSID")
	public ClassDomain getClassDomain() {
		return classDomain;
	}

	public void setClassDomain(ClassDomain classDomain) {
		this.classDomain = classDomain;
	}

//	@Column(name = "COLLEGEID", nullable = true, length = 100)
//	public String getCollegeId() {
//		return collegeId;
//	}
//
//	public void setCollegeId(String collegeId) {
//		this.collegeId = collegeId;
//	}

	
	
	
//	@Column(name = "GRADEID", nullable = true, length = 100)
//	public String getGradeId() {
//		return gradeId;
//	}
//
//	public void setGradeId(String gradeId) {
//		this.gradeId = gradeId;
//	}
//
//	@Column(name = "CLASSID", nullable = true, length = 100)
//	public String getClassId() {
//		return classId;
//	}
//
//	public void setClassId(String classId) {
//		this.classId = classId;
//	}
	
	

//	@Column(name = "ROLEID", nullable = false, length = 100)
//	public String getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(String roleId) {
//		this.roleId = roleId;
//	}
	
	

//	@Column(name = "AUTHORITY", nullable = false, length = 50)
//	public Integer getAuthority() {
//		return authority;
//	}
//
//	public void setAuthority(Integer authority) {
//		this.authority = authority;
//	}
	
}
