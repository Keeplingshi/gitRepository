package com.cb.csystem.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 班级表
 * @author chen
 *
 */
@Entity
@Table(name="CLASS")
public class ClassDomain {

	private String id;	//ID
	private String name;	//名称
	//private String majorId;	//所属专业
	private MajorDomain major;	//所属专业
	//private String gradeId;	//所属年级
	private GradeDomain grade;
	//private String monitorId;	//班长id
	private Set<StudentDomain> students=new HashSet<StudentDomain>();
	private Set<UserDomain> users=new HashSet<UserDomain>(0);
	
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
	
	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	@Column(name = "MAJORID", nullable = false, length = 100)
//	public String getMajorId() {
//		return majorId;
//	}
//	public void setMajorId(String majorId) {
//		this.majorId = majorId;
//	}
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "MAJORID")
	public MajorDomain getMajor() {
		return major;
	}
	public void setMajor(MajorDomain major) {
		this.major = major;
	}
	
//	@Column(name = "GRADEID", nullable = false, length = 100)
//	public String getGradeId() {
//		return gradeId;
//	}
//	
//	public void setGradeId(String gradeId) {
//		this.gradeId = gradeId;
//	}
	
//	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=StudentDomain.class)
//	@JoinColumn(name="MONITORID")
//	public StudentDomain getMonitor() {
//		return monitor;
//	}
//	public void setMonitor(StudentDomain monitor) {
//		this.monitor = monitor;
//	}
	
//	@Column(name = "MONITORID", nullable = false, length = 200)
//	public String getMonitorId() {
//		return monitorId;
//	}
//	public void setMonitorId(String monitorId) {
//		this.monitorId = monitorId;
//	}	
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "GRADEID")
	public GradeDomain getGrade() {
		return grade;
	}

	public void setGrade(GradeDomain grade) {
		this.grade = grade;
	}	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "classDomain", fetch = FetchType.LAZY)
	public Set<StudentDomain> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentDomain> students) {
		this.students = students;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "classDomain", fetch = FetchType.LAZY)
	public Set<UserDomain> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDomain> users) {
		this.users = users;
	}
	
}
