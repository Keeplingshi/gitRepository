package com.cb.csystem.domain;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 学生表
 * @author chen
 *
 */
@Entity
@Table(name="STUDENT")
public class StudentDomain {

	private String id;
	private String stuId;	//学号
	private String name;	//姓名
	private Integer sex;	//性别 0,男性   1,女性
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date birthday;	//出生日期
	private String nationality;	//民族
	private Integer politicalStatus;	//政治面貌
	private String IDnumber;	//身份证号
	private String nativePlace;	//籍贯
	private String dormitory;	//宿舍号
	//private String classId;		//班级，通过班级可以查到专业学院
	private ClassDomain classDomain;	//班级,通过班级可以查到专业学院所处年级
	private String email;	//电子邮件
	private String teachClass;
	//private String telephone;	//联系电话
	private String cellphone;	//手机
	private Integer isMonitor;	//是否为班长，0否1是
	private JobInfoDomain jobInfo;	//就业
	private PatyDomain paty;	//党建
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
	
	@Column(name = "STUID",unique = true, nullable = false, length = 100)
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "SEX", nullable = true, length = 10)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name = "BIRTHDAY", nullable = true, length = 100)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "POLITICALSTATUS", nullable = true, length = 10)
	public Integer getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	
	@Column(name = "IDNUMBER", nullable = true, length = 100)
	public String getIDnumber() {
		return IDnumber;
	}
	public void setIDnumber(String iDnumber) {
		IDnumber = iDnumber;
	}
	
	@Column(name = "NATIVEPLACE", nullable = true, length = 100)
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Column(name = "DORMITORY", nullable = true, length = 100)
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	
//	
//	@Column(name = "CLASSID", nullable = true, length = 100)
//	public String getClassId() {
//		return classId;
//	}
//	public void setClassId(String classId) {
//		this.classId = classId;
//	}
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CLASSID")
	public ClassDomain getClassDomain() {
		return classDomain;
	}
	public void setClassDomain(ClassDomain classDomain) {
		this.classDomain = classDomain;
	}
	
	@Column(name = "EMAIL", nullable = true, length = 100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	@Column(name = "TELEPHONE", nullable = true, length = 100)
//	public String getTelephone() {
//		return telephone;
//	}
//	public void setTelephone(String telephone) {
//		this.telephone = telephone;
//	}
	
	@Column(name = "TEACHCLASS", nullable = true, length = 100)
	public String getTeachClass() {
		return teachClass;
	}

	public void setTeachClass(String teachClass) {
		this.teachClass = teachClass;
	}
	
	@Column(name = "CELLPHONE", nullable = true, length = 100)
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	@Column(name = "ISMONITOR", nullable = true, length = 10,columnDefinition="INT default 0")
	public Integer getIsMonitor() {
		return isMonitor;
	}
	public void setIsMonitor(Integer isMonitor) {
		this.isMonitor = isMonitor;
	}
	
	@OneToOne(targetEntity=JobInfoDomain.class, cascade=CascadeType.ALL)  
    @PrimaryKeyJoinColumn
	public JobInfoDomain getJobInfo() {
		return jobInfo;
	}
	public void setJobInfo(JobInfoDomain jobInfo) {
		this.jobInfo = jobInfo;
	}
	
	@OneToOne(targetEntity=PatyDomain.class, cascade=CascadeType.ALL)  
    @PrimaryKeyJoinColumn
	public PatyDomain getPaty() {
		return paty;
	}
	
	public void setPaty(PatyDomain paty) {
		this.paty = paty;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.LAZY)
	public Set<DisciplineDomain> getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(Set<DisciplineDomain> disciplines) {
		this.disciplines = disciplines;
	}
	
	@Column(name = "NATIONALITY", nullable = true, length = 100)
	public String getNationality() {
		return nationality;
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
}
