package com.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="purchase_order_tab")
public class PurchaseOrder implements Comparable<PurchaseOrder> {
	@Id
	@Column(name="porder_id")
	@GeneratedValue(generator="pogen")
	@GenericGenerator(name="pogen",strategy="increment")
	private long orderId;
	@Column(name="order_code")
	private String orderCode;
	@Column(name="ref_num")
	private String refNumber;
	@Column(name="quality_chk")
	private String qualityCheck;
	@Column(name="dflt_sts")
	private String defaultStatus;
	@Column(name="po_desc")
	private String description;
	@Column(name="po_cdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="po_ldate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@ManyToOne
	@JoinColumn(name="ship_id")
	private ShipmentType shipmentMode=new ShipmentType();
	
	@ManyToOne
	@JoinColumn(name="ven_id")
	private WhUserType vendor=new WhUserType();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="po_dtls_fk")
	private List<PurchaseOrderDetails> details=new ArrayList<PurchaseOrderDetails>(0);
		
	public List<PurchaseOrderDetails> getDetails() {
		return details;
	}
	public void setDetails(List<PurchaseOrderDetails> details) {
		this.details = details;
	}
	public WhUserType getVendor() {
		return vendor;
	}
	public void setVendor(WhUserType vendor) {
		this.vendor = vendor;
	}
	public ShipmentType getShipmentMode() {
		return shipmentMode;
	}
	public void setShipmentMode(ShipmentType shipmentMode) {
		this.shipmentMode = shipmentMode;
	}
	
	
	//default Constructor
	public PurchaseOrder() {
		super();
	}
	//parameterized constructor for status
	public PurchaseOrder(String defaultStatus) {
		super();
		this.defaultStatus = defaultStatus;
	}
	//setters and getters
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getQualityCheck() {
		return qualityCheck;
	}
	public void setQualityCheck(String qualityCheck) {
		this.qualityCheck = qualityCheck;
	}
	public String getDefaultStatus() {
		return defaultStatus;
	}
	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
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
	//compareTo() method
	@Override
	public int compareTo(PurchaseOrder o) {
		return (int)(this.getOrderId()-o.getOrderId());
	}
}
