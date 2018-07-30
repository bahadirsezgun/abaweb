package com.abasus.pacs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.Patient;
import com.abasus.pacs.dao.PatientRandevu;



@Repository
public interface PatientRepository  extends CrudRepository<Patient, Long>{

	public List<Patient> findByPatId(String patId);
	
	
	public Patient findByPatIdAndAccessionNumber(@Param("patId") String patId,@Param("accessionNumber") String accessionNumber);
	
	
	@Query(value="SELECT b.* " + 
			"				 FROM patient b " + 
			"				 WHERE b.PATID NOT IN (SELECT PATID FROM sendkos a ) " + 
			"				 AND b.CREATE_TIME>=:startTime AND b.CREATE_TIME<=:endTime",nativeQuery=true )
	public List<Patient> findPatientsToSendKOS(@Param("startTime") Date startTime,@Param("endTime") Date endTime);
	
	
	
	
}
