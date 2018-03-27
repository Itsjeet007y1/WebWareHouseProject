package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.model.WhUserType;
import com.app.repo.WhUserTypeRepository;
import com.app.service.IWhUserTypeService;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {

	@Autowired
	private WhUserTypeRepository repo;
	
	@Override
	public long save(WhUserType whUserType) {
		whUserType.setCreatedDate(new Date());
		WhUserType obj=repo.save(whUserType);
		return obj.getWhUserTypeId();
	}

	@Override
	public void update(WhUserType whUserType) {
		whUserType.setCreatedDate(repo.getOne(whUserType.getWhUserTypeId()).getCreatedDate());
		whUserType.setLastModifiedDate(new Date());
		repo.save(whUserType);
	}

	@Override
	public void deleteOneById(long whUserTypeId) {
		repo.delete(whUserTypeId);
		
	}

	@Override
	public WhUserType getOneById(long whUserTypeId) {
		WhUserType whUserType=repo.getOne(whUserTypeId);
		return whUserType;
	}

	@Override
	public List<WhUserType> getAll() {
		List<WhUserType> whList=repo.findAll();
		return whList;
	}

	@Override
	public Page<WhUserType> getAll(Specification<WhUserType> spec, Pageable pageable) {
		Page<WhUserType> pageList=repo.findAll(spec,pageable);
		return pageList;
	}

	@Override
	public boolean isWhTypeAndUserCodeExist(WhUserType whUserType) {
		long count=repo.findWhUserByUserTypeAndUserCode(whUserType.getWhUserTyp(), whUserType.getWhUserCode());
		if(count==0) 
			return false;
		else
			return true;
	}

	@Override
	public List<WhUserType> findByWhUserTyp(String whUserTyp) {
		return repo.findByWhUserTyp(whUserTyp);
	}
}
