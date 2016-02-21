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
 * 专业表
 * @author chen
 *
 */
@Entity
@Table(name="MAJOR")
public class MajorDomain {

	private String id;
	private String name;
	//private String collegeId;
	private CollegeDomain college;	//所属学院
	private Set<ClassDomain> classes=new HashSet<ClassDomain>(0);
	
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
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "COLLEGEID")
	public CollegeDomain getCollege() {
		return college;
	}
	public void setCollege(CollegeDomain college) {
		this.college = college;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "major", fetch = FetchType.LAZY)
	public Set<ClassDomain> getClasses() {
		return classes;
	}
	public void setClasses(Set<ClassDomain> classes) {
		this.classes = classes;
	}

	
}
