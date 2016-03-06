package com.cb.csystem.domain;

import java.util.Date;

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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 党建表
 * @author Administrator
 *
 */
@Entity
@Table(name="PATY")
public class PatyDomain {
	
	private String id;
	//private String studentId;	//学生
	private StudentDomain student;
	@DateTimeFormat( pattern = "yyyy-MM-dd" ) private Date applicationDate;	//提交入党申请书日期
	@DateTimeFormat( pattern = "yyyy-MM-dd" ) private Date activeDate;	//确定积极份子日期
	private Integer isPassActive;	//中党即积极分子是否通过
	@DateTimeFormat( pattern = "yyyy-MM-dd" ) private Date developDate;	//确定发展对象日期
	@DateTimeFormat( pattern = "yyyy-MM-dd" ) private Date joinpatyDate;	//入党日期
	@DateTimeFormat( pattern = "yyyy-MM-dd" ) private Date confirmDate;	//转正日期
	private String note;	//备注
	
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
	
	
	
//	@Column(name = "STUDENTID",unique = true, nullable = false, length = 100)
//	public String getStudentId() {
//		return studentId;
//	}
//	
//	public void setStudentId(String studentId) {
//		this.studentId = studentId;
//	}
	
	@OneToOne(targetEntity=StudentDomain.class, cascade=CascadeType.ALL, mappedBy="paty")  
    @PrimaryKeyJoinColumn(name="id", referencedColumnName="id") 
	public StudentDomain getStudent() {
		return student;
	}

	public void setStudent(StudentDomain student) {
		this.student = student;
	}

	@Column(name = "APPLICATIONDATE", nullable = true, length = 100)
	public Date getApplicationDate() {
		return applicationDate;
	}
	
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	@Column(name = "ACTIVEDATE", nullable = true, length = 100)
	public Date getActiveDate() {
		return activeDate;
	}
	
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}
	
	@Column(name = "ISPASSACTIVE", nullable = true, length = 10)
	public Integer getIsPassActive() {
		return isPassActive;
	}
	
	public void setIsPassActive(Integer isPassActive) {
		this.isPassActive = isPassActive;
	}
	
	@Column(name = "DEVELOPDATE", nullable = true, length = 100)
	public Date getDevelopDate() {
		return developDate;
	}
	
	public void setDevelopDate(Date developDate) {
		this.developDate = developDate;
	}
	
	@Column(name = "JOINPATYDATE", nullable = true, length = 100)
	public Date getJoinpatyDate() {
		return joinpatyDate;
	}
	
	public void setJoinpatyDate(Date joinpatyDate) {
		this.joinpatyDate = joinpatyDate;
	}
	
	@Column(name = "CONFIRMDATE", nullable = true, length = 100)
	public Date getConfirmDate() {
		return confirmDate;
	}
	
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "NOTE", nullable = true, length = 2000)
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}

}
