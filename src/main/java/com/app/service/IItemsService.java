package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.app.model.Items;
import com.app.model.Uom;

public interface IItemsService {
	public long save(Items items);
	public void update(Items items);
	public void deleteOneById(long itemId);
	public Items getOneById(long itemId);
	public List<Items> getAll();
	public Page<Items> getAll(Specification<Items> spec,Pageable pageable);
	public List<Items> findItemsByVendor(Long vendorId);
}
