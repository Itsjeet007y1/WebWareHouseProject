package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.app.model.OrderMethod;
import com.app.model.Uom;

public interface IOrderMethodService {
	public long save(OrderMethod orderMethod);
	public void update(OrderMethod orderMethod);
	public void deleteById(long orderMethodId);
	public OrderMethod getOneById(long orderMethodId);
	
	public List<OrderMethod> getAll();
	public List<OrderMethod> saveMultiple(List<OrderMethod> list);
	public Page<OrderMethod> getAll(Specification<OrderMethod> s,Pageable pageable);
	public boolean isOrderModeAndOrderCodeExist(OrderMethod orderMethod);
	public List<OrderMethod> findByOrderMode(String orderMode);
}
	