package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Items;

public interface ItemsRepository extends JpaRepository<Items,Long>,JpaSpecificationExecutor<Items> {
	
	@Query("Select itm from com.app.model.Items itm inner join itm.itemVendors ven where ven.whUserTypeId=?1")
	List<Items> findItemsByVendor(Long vendorId);
}
