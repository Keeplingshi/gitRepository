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
 * 违纪类型表
 * @author chenbin
 *
 */
@Entity
@Table(name="DISCIPLINETYPE")
public class DisciplineTypeDomain {

	private String id;	//ID
	private String name;	//名称
	private Set<DisciplineDomain> disciplines=new HashSet<DisciplineDomain>(0);
	
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
	
	@Column(name = "NAME",unique = true, nullable = false, length = 100)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplineType", fetch = FetchType.LAZY)
	public Set<DisciplineDomain> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(Set<DisciplineDomain> disciplines) {
		this.disciplines = disciplines;
	}
	
	
}
