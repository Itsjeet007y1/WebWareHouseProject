package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Uom;

public interface UomRepository extends JpaRepository<Uom,Long>, JpaSpecificationExecutor<Uom> {

	@Query("Select count(uomId) from com.app.model.Uom where uomType=?1 and uomModel=?2")
	long findUomCountByTypeAndModel(String uomType, String uomModel);
}
