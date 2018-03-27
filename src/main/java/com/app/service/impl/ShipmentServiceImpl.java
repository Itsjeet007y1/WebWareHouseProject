package com.app.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.model.ShipmentType;
import com.app.repo.ShipmentTypeRepository;
import com.app.service.IShipmentTypeService;

@Service
public class ShipmentServiceImpl implements IShipmentTypeService {
	@Autowired
	private ShipmentTypeRepository repo;
	
	@Override
	public long saveShipment(ShipmentType shipmentType) {
		shipmentType.setCreatedDate(new Date());
		ShipmentType type=repo.save(shipmentType);
		return type.getShipmentId();
	}

	@Override
	public void updateShipment(ShipmentType shipmentType) {
		shipmentType.setCreatedDate(repo.getOne(shipmentType.getShipmentId()).getCreatedDate());
		shipmentType.setLastModifiedDate(new Date());
		repo.save(shipmentType);
	}

	@Override
	public void deleteOneById(long shipmentId) {
		repo.delete(shipmentId);	
	}

	@Override
	public ShipmentType getOneById(long shipmentId) {
		ShipmentType type=repo.getOne(shipmentId);
		return type;
	}

	@Override
	public List<ShipmentType> getAll() {
		List<ShipmentType> shipList=repo.findAll();
		Collections.sort(shipList);
		return shipList;
	}

	@Override
	public Page<ShipmentType> getAll(Specification<ShipmentType> s, Pageable pageable) {
		return repo.findAll(s,pageable);
	}

	@Override
	public boolean isShipmentModeAndCodeExist(ShipmentType shipmentType) {
		long count=repo.findShipmentTypeByModeAndCode(shipmentType.getShipmentMode(), shipmentType.getShipmentCode());
		//System.out.println(count);
		if(count==0)
			return false;
		else
			return true;
	}

	@Override
	public List<ShipmentType> findByEnabled(String enabled) {
		return repo.findByEnabled(enabled);
	}
}
