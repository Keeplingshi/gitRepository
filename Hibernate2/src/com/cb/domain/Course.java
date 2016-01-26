package com.cb.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private Long cid;
	private String cname;
	private Long ccredit;
	private Set studcourses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(Long cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Course(Long cid, String cname, Long ccredit, Set studcourses) {
		this.cid = cid;
		this.cname = cname;
		this.ccredit = ccredit;
		this.studcourses = studcourses;
	}

	// Property accessors

	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Long getCcredit() {
		return this.ccredit;
	}

	public void setCcredit(Long ccredit) {
		this.ccredit = ccredit;
	}

	public Set getStudcourses() {
		return this.studcourses;
	}

	public void setStudcourses(Set studcourses) {
		this.studcourses = studcourses;
	}

}