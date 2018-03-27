package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.model.PurchaseOrder;
import com.app.repo.PurchaseOrderRepository;
import com.app.service.IPurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {
	
	@Autowired
	private PurchaseOrderRepository repo;
	
	@Override
	public long save(PurchaseOrder purchaseOrder) {
		purchaseOrder.setCreatedDate(new Date());
		long id=	repo.save(purchaseOrder).getOrderId();
		return id;
	}

	@Override
	public void update(PurchaseOrder purchaseOrder) {
		purchaseOrder.setCreatedDate(repo.getOne(purchaseOrder.getOrderId()).getCreatedDate());
		purchaseOrder.setLastModifiedDate(new Date());
		repo.save(purchaseOrder);
	}

	@Override
	public void deleteOneById(long orderId) {
		repo.delete(orderId);
		
	}

	@Override
	public PurchaseOrder getOneById(long orderId) {
		PurchaseOrder purchaseOrder=repo.getOne(orderId);
		return purchaseOrder;
	}

	@Override
	public List<PurchaseOrder> getAll() {
		List<PurchaseOrder> poList=repo.findAll();
		return poList;
	}

	@Override
	public Page<PurchaseOrder> getAll(Specification<PurchaseOrder> spec, Pageable pageable) {
		Page<PurchaseOrder> pageList=repo.findAll(spec,pageable);
		return pageList;
	}

	@Override
	public boolean isPurchaseCodeAndRefNumberExist(PurchaseOrder purchaseOrder) {
		long count=repo.findPurchaseOrderByCodeAndRefNumber(purchaseOrder.getOrderCode(), purchaseOrder.getRefNumber());
		if(count==0) 
			return false;
		else
			return true;
	}
	
}
