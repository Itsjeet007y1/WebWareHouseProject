package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType,Long>,JpaSpecificationExecutor<ShipmentType> {
	
	@Query("Select count(shipmentId) from com.app.model.ShipmentType where shipmentMode=?1 and shipmentCode=?2")
	public long findShipmentTypeByModeAndCode(String shipmentMode,String shipmentCode);
	public List<ShipmentType> findByEnabled(String enabled);
}
