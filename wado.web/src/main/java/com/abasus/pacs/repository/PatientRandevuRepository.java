package com.abasus.pacs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.PatientRandevu;

@Repository
public interface PatientRandevuRepository extends CrudRepository<PatientRandevu, Long>  {

	public PatientRandevu findByPatIdAndAccessionNumber(@Param("patId") String patId,@Param("accsessionNumber") String accsessionNumber);
	
	
	
}
