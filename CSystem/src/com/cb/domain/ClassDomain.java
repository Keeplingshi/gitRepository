package com.cb.domain;

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
	private String gradeId;	//所属年级
	
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
	
	@Column(name = "GRADEID", nullable = false, length = 100)
	public String getGradeId() {
		return gradeId;
	}
	
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
}
