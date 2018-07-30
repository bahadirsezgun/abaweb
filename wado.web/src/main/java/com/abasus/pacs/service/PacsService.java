package com.abasus.pacs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abasus.kos.MakeToKos;
import com.abasus.pacs.dao.Files;
import com.abasus.pacs.dao.Instances;
import com.abasus.pacs.dao.Patient;
import com.abasus.pacs.dao.PatientRandevu;
import com.abasus.pacs.dao.SendKos;
import com.abasus.pacs.dao.Series;
import com.abasus.pacs.dao.Study;
import com.abasus.pacs.facade.PatientHasModalityFacade;
import com.abasus.pacs.repository.FilesRepository;
import com.abasus.pacs.repository.InstancesRepository;
import com.abasus.pacs.repository.PatientRandevuRepository;
import com.abasus.pacs.repository.PatientRepository;
import com.abasus.pacs.repository.SendKosRepository;
import com.abasus.pacs.repository.SeriesRepository;
import com.abasus.pacs.repository.StudyRepository;
import com.abasus.pacs.util.HmiResultObj;
import com.abasus.pacs.util.SearchUtil;

@Service
@Qualifier("pacsService")
public class PacsService {

	
	
	@Autowired
	MakeToKos makeToKos;

	
	
	@Value("${kos.hospitalName}")
	private String hospitalName;
	
	
	@Value("${kos.skrs}")
	private String skrs;
	
	@Value("${kos.repositoryUrl}")
	private String repositoryUrl;

	@Value("${kos.repositorySSLUrl}")
	private String repositorySSLUrl;
	
	
	@Autowired
	PatientHasModalityFacade patientHasModalityFacade;
	
	
	@Autowired
	SendKosRepository sendKosRepository;
	
	@Autowired
	InstancesRepository instancesRepository;
	
	
	@Autowired
	SeriesRepository seriesRepository;
	
	
	@Autowired
	StudyRepository studyRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientRandevuRepository patientRandevuRepository;
	
	
	@Autowired
	FilesRepository filesRepository;
	
	
	
	public HmiResultObj createAndSendKosFile(String dcmFilePath,String retrieveAET,String retrieveURL,String fileName,String tcKimlik,int production) {
		HmiResultObj hmiResultObj=new HmiResultObj();
		hmiResultObj.setResultStatu("NOK");
		String kosFilePath="";
		
		
		try {
			kosFilePath = makeToKos.createKosFile(dcmFilePath, retrieveAET, retrieveURL, hospitalName, skrs, fileName,tcKimlik);
			
			
			if(kosFilePath==null) {
				hmiResultObj.setResultKosFile("HASTANIN GÖRÜNTÜSÜ BULUNAMAMIS VEYA HATALI OLUÅ�MUÅ�TUR. LÜTFEN GÖRÜNTÜYÜ TEKRAR GÖNDERİNİZ");
			}else {
				hmiResultObj.setResultStatu("OK");
				hmiResultObj.setResultKosFile(kosFilePath);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hmiResultObj;
	}
	
	public void createSendKos(SendKos sendKos) {
		sendKosRepository.save(sendKos);
	}
	
	public List<Patient> findPatientById(String patId) {
		
		List<Patient> patients=patientRepository.findByPatId(patId);
		if(patients!=null) {
			patients.forEach(p->{
				PatientRandevu patientRandevu=patientRandevuRepository.findByPatIdAndAccessionNumber(p.getPatId(), p.getAccessionNumber());
				if(patientRandevu!=null) {
					p.setTcKimlikNo(patientRandevu.getTcKimlikNo());
				}else {
					p=null;
				}
			});
			
		}
		
		
		
		
		return patients;
	}
	
	public Patient findPatientByIdAndAccessionNumber(String patId,String accessionNumber) {
		
		Patient patient=patientRepository.findByPatIdAndAccessionNumber(patId, accessionNumber);
		
		PatientRandevu patientRandevu=patientRandevuRepository.findByPatIdAndAccessionNumber(patient.getPatId(), patient.getAccessionNumber());
		if(patientRandevu!=null) {
			patient.setTcKimlikNo(patientRandevu.getTcKimlikNo());
		}else {
			patient=null;
		}
		
		return patient;
	}
	
	public List<Patient> findPatientsToSendKOS(SearchUtil searchUtil) {
		
		List<Patient> patientsToSend=new ArrayList<>();
		List<Patient> patients=patientRepository.findPatientsToSendKOS(searchUtil.getStartTime(), searchUtil.getEndTime());
		patients.forEach(p->{
			if(patientHasModalityFacade.isPatientSendToKos(p.getPk()))
				patientsToSend.add(p);
		});
		
		return patientsToSend;
	}
	
	public List<Study> findStudiesbyPatientFk(Patient patient) {
		return studyRepository.findPatientInModality(patient.getPk());
	}
	
	public List<Files> getFilePathsByInstanceUID(String sopInstanceUid){
		Instances instances= instancesRepository.findBySopInstanceUid(sopInstanceUid);
		return filesRepository.findByInstanceFk(instances.getPk());
	}
	
	public List<Files> getFilePathsBySeriesUID(String seriesInstanceUid){
		Series series= seriesRepository.findBySeriesInstanceUid(seriesInstanceUid);
		List<Instances> instances=instancesRepository.findBySeriesFk(series.getPk());
		List<Files> files=new ArrayList<>();
		instances.stream().forEach(its->{
			List<Files> f=filesRepository.findByInstanceFk(its.getPk());
			files.addAll(f);
		});
		return files;
	}
		
	public List<Files> getFilePathsByStudyUID(Study study){
		
		//Study study=studyRepository.findByInstanceUid(studyInstanceUid);
		List<Series> series=seriesRepository.findByStudyFk(study.getPk());
		List<Files> files=new ArrayList<>();
		
		series.stream().forEach(s->{
			List<Instances> instances=instancesRepository.findBySeriesFk(s.getPk());
				instances.stream().forEach(its->{
					List<Files> f=filesRepository.findByInstanceFk(its.getPk());
					files.addAll(f);
				});
		});;
		
		
		return files;
	}
	
	public List<Files> getFilePathsBySUID(String studyInstanceUid){
		
		Study study=studyRepository.findByInstanceUid(studyInstanceUid);
		List<Series> series=seriesRepository.findByStudyFk(study.getPk());
		List<Files> files=new ArrayList<>();
		
		series.stream().forEach(s->{
			List<Instances> instances=instancesRepository.findBySeriesFk(s.getPk());
				instances.stream().forEach(its->{
					List<Files> f=filesRepository.findByInstanceFk(its.getPk());
					files.addAll(f);
				});
		});;
		
		
		return files;
	}
		
	
}
