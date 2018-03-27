package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ship_tab1",uniqueConstraints=@UniqueConstraint(columnNames= {"ship_mode","ship_code"}))
public class ShipmentType implements Comparable<ShipmentType> {
	@Id
	@Column(name="ship_id")
	@GeneratedValue(generator="shipgen")
	@GenericGenerator(name="shipgen",strategy="increment")
	private long shipmentId;
	@Column(name="ship_mode")
	private String shipmentMode;
	@Column(name="ship_code")
	private String shipmentCode;
	@Column(name="ship_enabled")
	private String enabled;
	@Column(name="ship_grade")
	private String shipmentGrade;
	@Column(name="ship_desc")
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	public long getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(long shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getShipmentMode() {
		return shipmentMode;
	}
	public void setShipmentMode(String shipmentMode) {
		this.shipmentMode = shipmentMode;
	}
	public String getShipmentCode() {
		return shipmentCode;
	}
	public void setShipmentCode(String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getShipmentGrade() {
		return shipmentGrade;
	}
	public void setShipmentGrade(String shipmentGrade) {
		this.shipmentGrade = shipmentGrade;
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
		return "ShipmentType [shipmentId=" + shipmentId + ", shipmentMode=" + shipmentMode + ", shipmentCode="
				+ shipmentCode + ", enabled=" + enabled + ", shipmentGrade=" + shipmentGrade + ", description="
				+ description + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	//comparTo() method
	@Override
	public int compareTo(ShipmentType o) {
		return (int)(this.shipmentId-o.getShipmentId());
	}
}
