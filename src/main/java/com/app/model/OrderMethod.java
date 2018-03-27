package com.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ordr_mtd_tab",uniqueConstraints=@UniqueConstraint(columnNames={"ordr_mode","ordr_code"}))
public class OrderMethod implements Comparable<OrderMethod>{
	@Id
	@Column(name="ordr_mtd_id")
	@GeneratedValue(generator="ordrmthd")
	@GenericGenerator(name="ordrmthd",strategy="increment")
	private long orderMethodId;
	@Column(name="ordr_mode")
	private String orderMode;
	@Column(name="ordr_code")
	private String orderCode;
	@Column(name="ordr_mtd")
	private String orderMetd;
	@ElementCollection
	@CollectionTable(name="ordrmtd_acc_tab",
	joinColumns=@JoinColumn(name="ordr_mtd_idFk"))
	@Column(name="ordr_accept")
	private List<String> orderAccept;
	@Column(name="ordr_desc")
	private String description;
	
	@Column(name="ordr_cdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="ordr_ldate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	public OrderMethod() {
		super();
	}
	public long getOrderMethodId() {
		return orderMethodId;
	}
	public void setOrderMethodId(long orderMethodId) {
		this.orderMethodId = orderMethodId;
	}
	public String getOrderMode() {
		return orderMode;
	}
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderMetd() {
		return orderMetd;
	}
	public void setOrderMetd(String orderMetd) {
		this.orderMetd = orderMetd;
	}
	public List<String> getOrderAccept() {
		return orderAccept;
	}
	public void setOrderAccept(List<String> orderAccept) {
		this.orderAccept = orderAccept;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@Override
	public String toString() {
		return "OrderMethod [orderMethodId=" + orderMethodId + ", orderMode=" + orderMode + ", orderCode=" + orderCode
				+ ", orderMetd=" + orderMetd + ", orderAccept=" + orderAccept + ", description=" + description
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	@Override
	public int compareTo(OrderMethod o) {
		return (int) (this.getOrderMethodId()-o.getOrderMethodId());
	}
}