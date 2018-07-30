package com.abasus.pacs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.Study;

@Repository
public interface StudyRepository extends CrudRepository<Study, Long> {

	
	public Study findByInstanceUid(String instanceUid);
	
	//public List<Study> findByPatientFk(long patientFk);
	
	@Query(value="SELECT * FROM study WHERE PATIENT_FK=:patientFk AND MODALITY_IN_STUDY IN "
			+ " (SELECT MODALITY FROM send_kos_modality)",nativeQuery=true)
	public List<Study> findPatientInModality(@Param("patientFk")  long patientFk);
	
	
}
