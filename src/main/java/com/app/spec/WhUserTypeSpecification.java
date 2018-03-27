package com.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.model.WhUserType;

public class WhUserTypeSpecification implements Specification<WhUserType> {

	private WhUserType filter;
	public WhUserTypeSpecification(WhUserType filter) {
		super();
		this.filter = filter;
	}
	
	@Override
	public Predicate toPredicate(Root<WhUserType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p=cb.conjunction();
		
		if(filter.getWhUserTyp()!=null && !"".equals(filter.getWhUserTyp().trim())) {
			p.getExpressions().add(cb.equal(root.get("whUserTyp"), filter.getWhUserTyp()));
		}
		if(filter.getWhUserCode()!=null && !"".equals(filter.getWhUserCode().trim())) {
			p.getExpressions().add(cb.like(root.get("whUserCode").as(String.class), "%"+filter.getWhUserCode()+"%"));
		}
		if(filter.getWhUserFor()!=null && !"".equals(filter.getWhUserFor().trim())) {
			p.getExpressions().add(cb.like(root.get("whUserFor").as(String.class), "%"+filter.getWhUserFor()+"%"));
		}
		if(filter.getWhUserMail()!=null && !"".equals(filter.getWhUserMail().trim())) {
			p.getExpressions().add(cb.like(root.get("whUserMail").as(String.class), "%"+filter.getWhUserFor()+"%"));
		}
		if(filter.getWhUserContact()!=null && !"".equals(filter.getWhUserContact().trim())) {
			p.getExpressions().add(cb.like(root.get("whUserContact").as(String.class),"%"+filter.getWhUserContact()+"%"));
		}
		if(filter.getWhUserIdType()!=null && !"".equals(filter.getWhUserIdType().trim())) {
			p.getExpressions().add(cb.like(root.get("whUserIdType").as(String.class),"%"+filter.getWhUserIdType()+"%"));
		}
		if(filter.getWhUserIdTypeOther()!=null && !"".equals(filter.getWhUserIdTypeOther().trim())) {
			p.getExpressions().add(cb.like(root.get("whUserIdTypeOther").as(String.class),"%"+filter.getWhUserIdTypeOther()+"%"));
		}
		if(filter.getWhUserIdNumber()!=null && !"".equals(filter.getWhUserIdNumber().trim())) {
			p.getExpressions().add(cb.like(root.get("whUserIdNumber").as(String.class),"%"+filter.getWhUserIdNumber()+"%"));
		}
		return p;
	}
	
}
