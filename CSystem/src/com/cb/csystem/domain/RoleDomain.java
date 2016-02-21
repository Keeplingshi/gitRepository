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
 * 角色表
 * @author chen
 *
 */
@Entity
@Table(name="ROLE")
public class RoleDomain {

	private String id;	//id
	private String name;	//角色名称
	private Integer authority;	//角色权限
	private String value;	//角色值
	private Set<UserDomain> users = new HashSet<UserDomain>(0);
	
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
	
	@Column(name = "AUTHORITY", nullable = false, length = 100)
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
	@Column(name = "VALUE", nullable = false, length = 100)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY)
	public Set<UserDomain> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDomain> users) {
		this.users = users;
	}
	
	
}
