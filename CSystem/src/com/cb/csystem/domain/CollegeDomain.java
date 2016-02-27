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
 * 学院表
 * @author chen
 *
 */
@Entity
@Table(name="COLLEGE")
public class CollegeDomain {

	private String id;
	private String name;
	private Set<MajorDomain> majors = new HashSet<MajorDomain>(0);
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
	
	@Column(name = "NAME",unique = true, nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "college", fetch = FetchType.LAZY)
	public Set<MajorDomain> getMajors() {
		return majors;
	}
	public void setMajors(Set<MajorDomain> majors) {
		this.majors = majors;
	}
	/**
	 * @return the users
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "college", fetch = FetchType.LAZY)
	public Set<UserDomain> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<UserDomain> users) {
		this.users = users;
	}
	
}
