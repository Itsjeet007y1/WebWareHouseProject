package com.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.Uom;

public class UomSpecification implements Specification<Uom>{

	private Uom filter;

	public UomSpecification(Uom filter) {
		this.filter=filter;
	}

	@Override
	public Predicate toPredicate(Root<Uom> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		Predicate p=cb.conjunction();

		if(filter.getUomType()!=null && !"".equals(filter.getUomType().trim())){
			p.getExpressions()
			.add(
					cb.equal(root.get("uomType"), filter.getUomType())
				);
		}
		if( filter.getUomModel()!=null && !"".equals(filter.getUomModel().trim()))	{
			p.getExpressions()
			.add(
					cb.like(root.get("uomModel").as(String.class), "%"+filter.getUomModel()+"%")
			);
		}
		if( filter.getDescription()!=null && !"".equals(filter.getDescription().trim())){
			p.getExpressions()
			.add(
					cb.like(root.get("description").as(String.class), "%"+filter.getDescription()+"%")	
			);
		}

		return p;
	}

}
