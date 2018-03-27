package com.app.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.model.OrderMethod;
import com.app.repo.OrderMethodRepository;
import com.app.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	OrderMethodRepository repo;
	
	@Override
	public long save(OrderMethod orderMethod) {
		orderMethod.setCreatedDate(new Date());
		return repo.save(orderMethod).getOrderMethodId();
	}

	@Override
	public void update(OrderMethod orderMethod) {
		orderMethod.setCreatedDate(repo.getOne(orderMethod.getOrderMethodId()).getCreatedDate());
		orderMethod.setLastModifiedDate(new Date());
		repo.save(orderMethod);
	}

	@Override
	public void deleteById(long orderMethodId) {
		if(repo.exists(orderMethodId)) {
			repo.delete(orderMethodId);
		}
	}

	@Override
	public OrderMethod getOneById(long orderMethodId) {
		return repo.getOne(orderMethodId);
	}

	@Override
	public List<OrderMethod> getAll() {
		List<OrderMethod> list=repo.findAll();
		Collections.sort(list);
		return list;
	}

	@Override
	public List<OrderMethod> saveMultiple(List<OrderMethod> list) {
		return repo.save(list);
	}

	@Override
	public Page<OrderMethod> getAll(Specification<OrderMethod> s, Pageable pageable) {
		Page<OrderMethod> page=repo.findAll(s,pageable);
		return page;
	}

	@Override
	public boolean isOrderModeAndOrderCodeExist(OrderMethod orderMethod) {
		long count=repo.findOrderMethodByOrderModeAndOrderCode(orderMethod.getOrderMode(),orderMethod.getOrderCode());
		if(count==0)
			return false;
		else 
			return true;
	}
	
	@Override
	public List<OrderMethod> findByOrderMode(String orderMode) {
		List<OrderMethod> orderList=repo.findByOrderMode(orderMode);
		return orderList;
	}
	
}
