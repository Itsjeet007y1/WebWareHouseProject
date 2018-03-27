package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.model.OrderMethod;
import com.app.model.Uom;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Long>,JpaSpecificationExecutor<OrderMethod> {
	
	@Query("select count(orderMethodId) from com.app.model.OrderMethod where orderMode=?1 and orderCode=?2")
	long findOrderMethodByOrderModeAndOrderCode(String orderMode,String orderCode);
	public List<OrderMethod> findByOrderMode(String orderMode);
}
