package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ordr_dtls_tab1")
public class PurchaseOrderDetails implements Comparable<PurchaseOrderDetails> {
	@Id
	@GeneratedValue(generator="ordrtblgen")
	@GenericGenerator(name="ordrtblgen",strategy="increment")
	@Column(name="dtl_id")
	private Long dtlId;
	
	@Column(name="pohdr_id")
	private Long poHdrId;
	
	@Column(name="sl_no")
	private int slno;
	
	@ManyToOne
	@JoinColumn(name="item_id_fk")
	private Items itemDetails;
	
	@Column(name="base_cost")
	private Double baseCost;
	
	@Column(name="base_qty")
	private Long itemQty;
	
	@Column(name="line_val")
	private Double lineValue;
	
	//default Constructor
	public PurchaseOrderDetails() {
		super();
	}

	//parameterized Constructor
	public PurchaseOrderDetails(int slno, Items itemDetails, Double baseCost, Long itemQty, Double lineValue) {
		super();
		this.slno = slno;
		this.itemDetails = itemDetails;
		this.baseCost = baseCost;
		this.itemQty = itemQty;
		this.lineValue = lineValue;
	}
	//setters and getters

	public Long getDtlId() {
		return dtlId;
	}

	public void setDtlId(Long dtlId) {
		this.dtlId = dtlId;
	}

	public Long getPoHdrId() {
		return poHdrId;
	}

	public void setPoHdrId(Long poHdrId) {
		this.poHdrId = poHdrId;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public Items getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(Items itemDetails) {
		this.itemDetails = itemDetails;
	}

	public Double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public Long getItemQty() {
		return itemQty;
	}

	public void setItemQty(Long itemQty) {
		this.itemQty = itemQty;
	}

	public Double getLineValue() {
		return lineValue;
	}

	public void setLineValue(Double lineValue) {
		this.lineValue = lineValue;
	}
	//compareTo() method to sort the values based on

	@Override
	public int compareTo(PurchaseOrderDetails o) {
		return (int)(this.getSlno()-o.getSlno());
	}
}
