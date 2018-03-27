package com.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.OrderMethod;

public class OrderMethodSpecification implements Specification<OrderMethod>{

	private OrderMethod filter;

	public OrderMethodSpecification(OrderMethod filter) {
		this.filter=filter;
	}

	@Override
	public Predicate toPredicate(Root<OrderMethod> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		Predicate p=cb.conjunction();
		
		//for orderMode
		if(filter.getOrderMode()!=null && !"".equals(filter.getOrderMode().trim())){
			p.getExpressions()
			.add(
					cb.equal(root.get("orderMode"), filter.getOrderMode())
				);
		}
		
		//for orderMethod
		if(filter.getOrderMetd()!=null && !"".equals(filter.getOrderMetd().trim())){
			p.getExpressions()
			.add(
					cb.equal(root.get("orderMetd"), filter.getOrderMetd())
				);
		}
		
		//for OrderCode
		if( filter.getOrderCode()!=null && !"".equals(filter.getOrderCode().trim()))	{
			p.getExpressions()
			.add(
					cb.like(root.get("orderCode").as(String.class), "%"+filter.getOrderCode()+"%")
			);
		}
		
		//for description
		if( filter.getDescription()!=null && !"".equals(filter.getDescription().trim())){
			p.getExpressions()
			.add(
					cb.like(root.get("description").as(String.class), "%"+filter.getDescription()+"%")	
			);
		}
		
		/*//for orderAccept
		if( filter.getOrderAccept()!=null && !"".equals(filter.getOrderAccept())){
			p.getExpressions()
			.add(
					cb.like(root.get("orderAccept").as(String.class), "%"+filter.getOrderAccept()+"%")	
			);
		}*/
		
		return p;
	}

}





