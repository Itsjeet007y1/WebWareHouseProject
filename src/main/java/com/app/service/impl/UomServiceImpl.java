package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.model.Uom;
import com.app.repo.UomRepository;
import com.app.service.IUomService;

@Service
public class UomServiceImpl implements IUomService {
	
	@Autowired
	private UomRepository repo;

	@Override
	public long saveUom(Uom uom) {
		uom.setCreatedDate(new Date());
		uom=repo.save(uom);
		return uom.getUomId();
	}

	@Override
	public void updateUom(Uom uom) {
		uom.setCreatedDate(repo.getOne(uom.getUomId()).getCreatedDate());
		uom.setLastModifiedDate(new Date());
		repo.save(uom);
		
	}
	@Override
	public void deleteById(long uomId) {
		repo.delete(uomId);
		
	}

	@Override
	public Uom getOneById(long uomId) {
		Uom ob=repo.getOne(uomId);
		return ob;
	}

	@Override
	public List<Uom> getAll() {
		List<Uom> uomList=repo.findAll();
		return uomList;
	}
	
	@Override
	public List<Uom> saveMultiple(List<Uom> uomList) {
		return repo.save(uomList);
	}

	@Override
	public Page<Uom> getAll(Specification<Uom> specification,Pageable pageable) {
		return repo.findAll(specification,pageable);
		
	}

	@Override
	public boolean isUomTypeModelExist(Uom uom) {
		long count=repo.findUomCountByTypeAndModel(uom.getUomType(),uom.getUomModel());
		if(count==0)
			return false;
		else
			return true;
	}
	
}
