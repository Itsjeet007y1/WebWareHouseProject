package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.app.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType,Long>,JpaSpecificationExecutor<WhUserType> {
	
	@Query("select count(whUserTypeId) from com.app.model.WhUserType where whUserTyp=?1 and whUserCode=?2")
	long findWhUserByUserTypeAndUserCode(String whUserTyp,String whUserCode);
	List<WhUserType> findByWhUserTyp(String whUserTyp);
}
