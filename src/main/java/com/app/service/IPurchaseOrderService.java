package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.app.model.PurchaseOrder;

public interface IPurchaseOrderService {
	public long save(PurchaseOrder purchaseOrder);
	public void update(PurchaseOrder purchaseOrder);
	public void deleteOneById(long orderId);
	public PurchaseOrder getOneById(long orderId);
	public List<PurchaseOrder> getAll();
	public Page<PurchaseOrder> getAll(Specification<PurchaseOrder> spec,Pageable pageable);
	boolean isPurchaseCodeAndRefNumberExist(PurchaseOrder purchaseOrder);
}