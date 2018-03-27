package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.app.model.ShipmentType;

public interface IShipmentTypeService {
	public long saveShipment(ShipmentType shipmentType);
	public void updateShipment(ShipmentType shipmentType);
	public void deleteOneById(long shipmentId);
	public ShipmentType getOneById(long shipmentId);
	public List<ShipmentType> getAll();
	public Page<ShipmentType> getAll(Specification<ShipmentType> s,Pageable pageable);
	public boolean isShipmentModeAndCodeExist(ShipmentType shipmentType);
	public List<ShipmentType> findByEnabled(String enabled);
}
