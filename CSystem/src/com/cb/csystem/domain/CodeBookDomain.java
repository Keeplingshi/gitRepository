package com.cb.csystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 关键字表
 * @author chen
 *
 */
@Entity
@Table(name="CODEBOOK")
public class CodeBookDomain {

	private String id;	//ID
	private String name;	//名称
	private String value;	//值
	private String type;	//类型
	private String remark;	//备注
	private String parentId;	//父节点
	private CodeBookDomain codeBookDomain;
	
	public CodeBookDomain(){
		
	}

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
	
	@Column(name = "VALUE", nullable = false, length = 100)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name = "TYPE",nullable = false, length = 100)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "REMARK", nullable = true, length = 100)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "PARENTID", nullable = true, length = 100)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTID", insertable = false, updatable = false)
	public CodeBookDomain getCodeBookDomain() {
		return codeBookDomain;
	}
	public void setCodeBookDomain(CodeBookDomain codeBookDomain) {
		this.codeBookDomain = codeBookDomain;
	}
	
	
}
