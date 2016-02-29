package com.cb.csystem.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 违纪表
 * @author Administrator
 *
 */
@Entity
@Table(name="DISCIPLINE")
public class DisciplineDomain {

	private String id;
	private String studentId;	//学生
	private String disciplineTypeId;	//违纪类型
	private Date time;	//时间
	private String note;	//备注
	
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
	
	@Column(name = "STUDENTID", nullable = false, length = 200)
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Column(name = "DISCIPLINETYPEID", nullable = false, length = 200)
	public String getDisciplineTypeId() {
		return disciplineTypeId;
	}
	
	public void setDisciplineTypeId(String disciplineTypeId) {
		this.disciplineTypeId = disciplineTypeId;
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
	
}
