package com.abasus.pacs.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.abasus.pacs.dao.Study;
import com.abasus.pacs.repository.StudyRepository;

@Service
@Qualifier("patientHasModalityFacade")
public class PatientHasModalityFacade {

	@Autowired
	StudyRepository studyRepository;
	
	public boolean isPatientSendToKos(long pk) {
		List<Study> studies=studyRepository.findPatientInModality(pk);
		if(studies==null) {
			return false;
		}
		if(studies.size()==0)
			return false;
		
		return true;
	}
	
}
