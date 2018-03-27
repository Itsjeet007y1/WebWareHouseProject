package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long>,JpaSpecificationExecutor<PurchaseOrder> {
	
	@Query("select count(orderId) from com.app.model.PurchaseOrder where orderCode=?1 and refNumber=?2")
	long findPurchaseOrderByCodeAndRefNumber(String orderCode,String refNumber);
}