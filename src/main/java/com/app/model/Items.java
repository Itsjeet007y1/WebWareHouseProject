package com.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Items")
public class Items implements Comparable<Items> {
	@Id
	@Column(name = "item_id")
	@GeneratedValue(generator = "itemgen")
	@GenericGenerator(name = "itemgen", strategy = "increment")
	private long itemId;
	@Column(name = "item_code")
	private String itemCode;
	@Column(name = "item_width")
	private double itemWidth;
	@Column(name = "item_length")
	private double itemLength;
	@Column(name = "item_height")
	private double itemHeight;
	@Column(name = "item_Cost")
	private double itemCost;
	@Column(name = "item_currency")
	private String currency;
	@Column(name = "item_desc")
	private String description;
	@Column(name = "item_cdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "item_ladate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	// Relation Coding for Uom
	@ManyToOne
	@JoinColumn(name = "uidFk")
	Uom uom = new Uom();
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}

	// Relation Coding for OrderMethod Sale and Purchase
	@ManyToOne
	@JoinColumn(name="item_oms_fk")
	private OrderMethod itemSaleOrdMethod=new OrderMethod();
	
	@ManyToOne
	@JoinColumn(name="item_omp_fk")
	private OrderMethod itemPurchaseMethod=new OrderMethod();
	
	public OrderMethod getItemSaleOrdMethod() {
		return itemSaleOrdMethod;
	}
	public void setItemSaleOrdMethod(OrderMethod itemSaleOrdMethod) {
		this.itemSaleOrdMethod = itemSaleOrdMethod;
	}
	public OrderMethod getItemPurchaseMethod() {
		return itemPurchaseMethod;
	}
	public void setItemPurchaseMethod(OrderMethod itemPurchaseMethod) {
		this.itemPurchaseMethod = itemPurchaseMethod;
	}

	//Relation Coding for WhUserType for Vendors
	@ManyToMany
	@JoinTable(name="item_ven1",joinColumns=@JoinColumn(name="item_fk"),inverseJoinColumns=@JoinColumn(name="user_ven_fk"))
	private List<WhUserType> itemVendors=new ArrayList<WhUserType>(0);
	
	//Relation Coding for WhUserType for Customers
	@ManyToMany
	@JoinTable(name="item_cust1",joinColumns=@JoinColumn(name="item_fk"),inverseJoinColumns=@JoinColumn(name="user_cust_fk"))
	private List<WhUserType> itemCustomers=new ArrayList<WhUserType>(0);

	// default Constructor
	public Items() {
		super();
	}
	// parameterized constructor

	public Items(int itemId) {
		super();
		this.itemId = itemId;
	}
	// setters and getters

	public String getItemCode() {
		return itemCode;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public double getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(double itemWidth) {
		this.itemWidth = itemWidth;
	}

	public double getItemLength() {
		return itemLength;
	}

	public void setItemLength(double itemLength) {
		this.itemLength = itemLength;
	}

	public double getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(double itemHeight) {
		this.itemHeight = itemHeight;
	}

	public double getItemCost() {
		return itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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
	// compareTo() method

	@Override
	public int compareTo(Items o) {
		return (int) (this.getItemId() - o.getItemId());
	}

	public List<WhUserType> getItemVendors() {
		return itemVendors;
	}

	public void setItemVendors(List<WhUserType> itemVendors) {
		this.itemVendors = itemVendors;
	}

	public List<WhUserType> getItemCustomers() {
		return itemCustomers;
	}

	public void setItemCustomers(List<WhUserType> itemCustomers) {
		this.itemCustomers = itemCustomers;
	}
}
