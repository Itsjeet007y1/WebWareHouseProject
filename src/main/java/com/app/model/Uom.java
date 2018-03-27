package com.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="uom_tab8")
public class Uom implements Comparable<Uom> {
	
	@Id
	@Column(name="uom_id")
	@GeneratedValue(generator="uomgen")
	@GenericGenerator(name="uomgen",strategy="increment")
	private long uomId;
	
	@Column(name="uom_type")
	private String uomType;
	
	@Column(name="uom_model")
	private String uomModel;
	
	@Column(name="uom_cdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name="uom_lmdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@Column(name="uom_desc")
	private String description;
	
	
	//Relation coding
	@OneToMany(mappedBy="uom")
	List<Items> its=new ArrayList<Items>();
	public List<Items> getIts() {
		return its;
	}
	public void setIts(List<Items> its) {
		this.its = its;
	}
	
	
	//create default constructor
	public Uom() {
		super();
	}
	//create primary key parameterized constructor
	public Uom(long uomId) {
		super();
		this.uomId = uomId;
	}
	//setter and getters
	public long getUomId() {
		return uomId;
	}
	public void setUomId(long uomId) {
		this.uomId = uomId;
	}
	public String getUomType() {
		return uomType;
	}
	public void setUomType(String uomType) {
		this.uomType = uomType;
	}
	public String getUomModel() {
		return uomModel;
	}
	public void setUomModel(String uomModel) {
		this.uomModel = uomModel;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//override compareTo() method of Comparable interface
	@Override
	public int compareTo(Uom o) {
		return (int)(this.getUomId()-o.getUomId());
	}
	public Uom(String uomType, String uomModel, String description, Date createdDate) {
		super();
		this.uomType = uomType;
		this.uomModel = uomModel;
		this.createdDate = createdDate;
		this.description = description;
	}
}
