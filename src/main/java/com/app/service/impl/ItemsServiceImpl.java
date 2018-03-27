package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.model.Items;
import com.app.repo.ItemsRepository;
import com.app.service.IItemsService;

@Service
public class ItemsServiceImpl implements IItemsService {

	@Autowired
	private ItemsRepository repo;
	
	@Override
	public long save(Items items) {
		items.setCreatedDate(new Date());
		Items obj=repo.save(items);
		return obj.getItemId();
	}

	@Override
	public void update(Items items) {
		items.setCreatedDate(repo.getOne(items.getItemId()).getCreatedDate());
		items.setLastModifiedDate(new Date());
		repo.save(items);
	}

	@Override
	public void deleteOneById(long itemId) {
		repo.delete(itemId);
		
	}

	@Override
	public Items getOneById(long itemId) {
		Items items=repo.getOne(itemId);
		return items;
	}

	@Override
	public List<Items> getAll() {
		List<Items> itemList=repo.findAll();
		return itemList;
	}

	@Override
	public Page<Items> getAll(Specification<Items> spec, Pageable pageable) {
		Page<Items> pageList=repo.findAll(spec,pageable);
		return pageList;
	}

	@Override
	public List<Items> findItemsByVendor(Long vendorId) {
		return repo.findItemsByVendor(vendorId);
	}
	
}
