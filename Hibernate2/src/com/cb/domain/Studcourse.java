package com.cb.domain;

/**
 * Studcourse entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Studcourse implements java.io.Serializable {

	// Fields

	private Long stucourseid;
	private Student student;
	private Course course;
	private Long grade;

	// Constructors

	/** default constructor */
	public Studcourse() {
	}

	/** minimal constructor */
	public Studcourse(Long grade) {
		this.grade = grade;
	}

	/** full constructor */
	public Studcourse(Student student, Course course, Long grade) {
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	// Property accessors

	public Long getStucourseid() {
		return this.stucourseid;
	}

	public void setStucourseid(Long stucourseid) {
		this.stucourseid = stucourseid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getGrade() {
		return this.grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

}