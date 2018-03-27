package com.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="whUser_tab",uniqueConstraints=@UniqueConstraint(columnNames= {"whuser_type","whuser_typ"}))
public class WhUserType implements Comparable<WhUserType> {
	@Id
	@Column(name="whuser_type")
	@GeneratedValue(generator="whusr")
	@GenericGenerator(name="whusr",strategy="increment")
	private long whUserTypeId;
	@Column(name="whuser_typ")
	private String whUserTyp;
	@Column(name="whusrcd")
	private String whUserCode;
	@Column(name="whusrfr")
	private String whUserFor;
	@Column(name="whusrmail")
	private String whUserMail;
	@Column(name="whusrcntct")
	private String whUserContact;
	@Column(name="whusrid")
	private String whUserIdType;
	@Column(name="whusrtypother")
	private String whUserIdTypeOther;
	@Column(name="whusrnumber")
	private String whUserIdNumber;
	@Column(name="whcrtd")
	private Date createdDate;
	@Column(name="whlstmdfd")
	private Date lastModifiedDate;
	
	//Relation to Items
	@ManyToMany(mappedBy="itemVendors")
	List<Items> venItems=new ArrayList<Items>(0);
	
	@ManyToMany(mappedBy="itemCustomers")
	List<Items> custItems=new ArrayList<Items>(0);
	
	public long getWhUserTypeId() {
		return whUserTypeId;
	}
	public void setWhUserTypeId(long whUserTypeId) {
		this.whUserTypeId = whUserTypeId;
	}
	public String getWhUserTyp() {
		return whUserTyp;
	}
	public void setWhUserTyp(String whUserTyp) {
		this.whUserTyp = whUserTyp;
	}
	public String getWhUserCode() {
		return whUserCode;
	}
	public void setWhUserCode(String whUserCode) {
		this.whUserCode = whUserCode;
	}
	public String getWhUserFor() {
		return whUserFor;
	}
	public void setWhUserFor(String whUserFor) {
		this.whUserFor = whUserFor;
	}
	public String getWhUserMail() {
		return whUserMail;
	}
	public void setWhUserMail(String whUserMail) {
		this.whUserMail = whUserMail;
	}
	public String getWhUserContact() {
		return whUserContact;
	}
	public void setWhUserContact(String whUserContact) {
		this.whUserContact = whUserContact;
	}
	public String getWhUserIdType() {
		return whUserIdType;
	}
	public void setWhUserIdType(String whUserIdType) {
		this.whUserIdType = whUserIdType;
	}
	public String getWhUserIdTypeOther() {
		return whUserIdTypeOther;
	}
	public void setWhUserIdTypeOther(String whUserIdTypeOther) {
		this.whUserIdTypeOther = whUserIdTypeOther;
	}
	public String getWhUserIdNumber() {
		return whUserIdNumber;
	}
	public void setWhUserIdNumber(String whUserIdNumber) {
		this.whUserIdNumber = whUserIdNumber;
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
	//default constructor
	public WhUserType() {
		super();
	}
	//primary key parameterized constructor
	public WhUserType(long whUserTypeId) {
		super();
		this.whUserTypeId = whUserTypeId;
	}
	//toString() 
	@Override
	public String toString() {
		return "WhUserType [whUserTypeId=" + whUserTypeId + ", whUserTyp=" + whUserTyp + ", whUserCode=" + whUserCode
				+ ", whUserFor=" + whUserFor + ", whUserMail=" + whUserMail + ", whUserContact=" + whUserContact
				+ ", whUserIdType=" + whUserIdType + ", whUserIdTypeOther=" + whUserIdTypeOther + ", whUserIdNumber="
				+ whUserIdNumber + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	//comparTo() method
	@Override
	public int compareTo(WhUserType o) {
		return (int)(this.getWhUserTypeId()-o.getWhUserTypeId());
	}
	public List<Items> getVenItems() {
		return venItems;
	}
	public void setVenItems(List<Items> venItems) {
		this.venItems = venItems;
	}
	public List<Items> getCustItems() {
		return custItems;
	}
	public void setCustItems(List<Items> custItems) {
		this.custItems = custItems;
	}
}
