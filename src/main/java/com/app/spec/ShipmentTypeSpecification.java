package com.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.ShipmentType;

public class ShipmentTypeSpecification implements Specification<ShipmentType> {

	private ShipmentType filter;
	public ShipmentTypeSpecification(ShipmentType filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<ShipmentType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p=cb.conjunction();
		
		if(filter.getShipmentMode()!=null && !"".equals(filter.getShipmentMode().trim())) {
			p.getExpressions().add(cb.equal(root.get("shipmentMode"), filter.getShipmentMode()));
		}
		if(filter.getShipmentCode()!=null && !"".equals(filter.getShipmentCode().trim())) {
			p.getExpressions().add(cb.like(root.get("shipmentCode").as(String.class), "%"+filter.getShipmentCode()+"%"));
		}
		if(filter.getEnabled()!=null ) {
			p.getExpressions().add(cb.equal(root.get("enabled"), filter.getEnabled()));
		}
		if(filter.getShipmentGrade()!=null && !"".equals(filter.getShipmentGrade().trim())) {
			p.getExpressions().add(cb.like(root.get("shipmentGrade").as(String.class), "%"+filter.getShipmentGrade()+"%"));
		}
		if(filter.getDescription()!=null && !"".equals(filter.getDescription().trim())) {
			p.getExpressions().add(cb.like(root.get("description").as(String.class), "%"+filter.getDescription()+"%"));
		}
		
		return p;
	}
}
