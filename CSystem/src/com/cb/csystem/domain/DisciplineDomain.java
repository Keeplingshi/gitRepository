package com.cb.csystem.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 违纪表
 * @author Administrator
 *
 */
@Entity
@Table(name="DISCIPLINE")
public class DisciplineDomain {

	private String id;
	//private String studentId;	//学生
	private StudentDomain student;	
	//private String disciplineTypeId;	//违纪类型
	private DisciplineTypeDomain disciplineType;
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date time;	//时间
	private String note;	//备注
	private String courseName;	//课程名称
	private String courseTeacher;	//课程老师
	
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
	
	
	
//	@Column(name = "STUDENTID", nullable = false, length = 200)
//	public String getStudentId() {
//		return studentId;
//	}
//	
//	public void setStudentId(String studentId) {
//		this.studentId = studentId;
//	}
	
//	@Column(name = "DISCIPLINETYPEID", nullable = false, length = 200)
//	public String getDisciplineTypeId() {
//		return disciplineTypeId;
//	}
//	
//	public void setDisciplineTypeId(String disciplineTypeId) {
//		this.disciplineTypeId = disciplineTypeId;
//	}
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "STUDENTID")
	public StudentDomain getStudent() {
		return student;
	}

	public void setStudent(StudentDomain student) {
		this.student = student;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "DISCIPLINETYPEID")
	public DisciplineTypeDomain getDisciplineType() {
		return disciplineType;
	}

	public void setDisciplineType(DisciplineTypeDomain disciplineType) {
		this.disciplineType = disciplineType;
	}

	@Column(name = "TIME", nullable = true, length = 200)
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Column(name = "NOTE", nullable = true, length = 1000)
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "COURSENAME", nullable = true, length = 100)
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "COURSETEACHER", nullable = true, length = 100)
	public String getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	
	
}
