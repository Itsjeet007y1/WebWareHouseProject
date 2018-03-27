package com.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.Items;

public class ItemsSpecification implements Specification<Items> {

	private Items filter;
	public ItemsSpecification(Items filter) {
		super();
		this.filter = filter;
	}
	
	@Override
	public Predicate toPredicate(Root<Items> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p=cb.conjunction();
		
		if(filter.getItemCode()!=null && !"".equals(filter.getItemCode().trim())) {
			p.getExpressions().add(cb.equal(root.get("itemCode"), filter.getItemCode()));
		}
		
		/*if(filter.getItemWidth()!=0.0) {
			p.getExpressions().add(cb.like(root.get("itemWidth").as(String.class), "%"+filter.getItemWidth()+"%"));
		}
		if(filter.getItemLength()!=0.0) {
			p.getExpressions().add(cb.like(root.get("itemLength()").as(String.class), "%"+filter.getItemLength()+"%"));
		}
		if(filter.getItemHeight()!=0.0) {
			p.getExpressions().add(cb.like(root.get("itemHeight()").as(String.class), "%"+filter.getItemHeight()+"%"));
		}*/
		
		/*if(filter.getItemCost()!=0.0) {
			p.getExpressions().add(cb.like(root.get("itemCost").as(String.class), "%"+filter.getItemCost()+"%"));
		}*/
		
		
		if(filter.getCurrency()!=null && !"".equals(filter.getCurrency().trim())) {
			p.getExpressions().add(cb.like(root.get("currency").as(String.class), "%"+filter.getCurrency()+"%"));
		}
		if(filter.getUom().getUomModel()!=null && !"".equals(filter.getUom().getUomModel().trim())) {
			p.getExpressions().add(cb.like(root.get("uom").as(String.class), "%"+filter.getUom().getUomModel()+"%"));
		}
		if(filter.getDescription()!=null && !"".equals(filter.getDescription().trim())) {
			p.getExpressions().add(cb.like(root.get("description").as(String.class),"%"+filter.getDescription()+"%"));
		}
		return p;
	}
	
}