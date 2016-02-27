package com.cb.csystem.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 年级
 * @author chen
 *
 */
@Entity
@Table(name="GRADE")
public class GradeDomain {

	private String id;
	private Integer grade;
	private Set<ClassDomain> classes=new HashSet<ClassDomain>(0);
	private Set<UserDomain> users=new HashSet<UserDomain>(0);
	
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
	
	@Column(name = "GRADE",unique = true, nullable = false, length = 100)
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grade", fetch = FetchType.LAZY)
	public Set<ClassDomain> getClasses() {
		return classes;
	}
	public void setClasses(Set<ClassDomain> classes) {
		this.classes = classes;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grade", fetch = FetchType.LAZY)
	public Set<UserDomain> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDomain> users) {
		this.users = users;
	}
	
	
	
}
