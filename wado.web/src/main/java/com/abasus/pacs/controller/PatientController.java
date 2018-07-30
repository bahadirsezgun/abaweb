package com.abasus.pacs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abasus.pacs.dao.Files;
import com.abasus.pacs.dao.Patient;
import com.abasus.pacs.dao.SendKos;
import com.abasus.pacs.dao.SendKosModality;
import com.abasus.pacs.dao.Study;
import com.abasus.pacs.service.ModalityService;
import com.abasus.pacs.service.PacsService;
import com.abasus.pacs.util.HmiResultObj;
import com.abasus.pacs.util.ResultDetailObj;
import com.abasus.pacs.util.SearchUtil;




@RestController
@RequestMapping(value="/pacs/patient")
public class PatientController {

	
	
	@Autowired
	PacsService pacsService;
	
	
	@Autowired
	ModalityService modalityService;
	
	@Value("${kos.hospitalName}")
	private String hospitalName;
	
	
	@Value("${kos.skrs}")
	private String skrs;
	
	@Value("${kos.repositoryUrl}")
	private String repositoryUrl;
	
	
	
	
	@RequestMapping(value="/findPatientByIdToSendKOS/{patId}", method = RequestMethod.POST) 
	public @ResponseBody List<Patient> findPatientByIdToSendKOS(@PathVariable String patId ) {
		
		
		return pacsService.findPatientById(patId);
	}
	
	@RequestMapping(value="/findPatientsToSendKOS", method = RequestMethod.POST) 
	public @ResponseBody List<Patient> findPatientsToSendKOS(@RequestBody SearchUtil searchUtil ) {
		return pacsService.findPatientsToSendKOS(searchUtil);
	}
	
	
	@RequestMapping(value="/sendPatientToSend/{production}", method = RequestMethod.POST) 
	public @ResponseBody HmiResultObj sendPatientToSend(@RequestBody Patient patient,@PathVariable int production ) {
		HmiResultObj hmiResultObj=new HmiResultObj();
	    patient=pacsService.findPatientByIdAndAccessionNumber(patient.getPatId(), patient.getAccessionNumber());
		
	    
		
		if(patient!=null) {
		
			
			
		List<Study> studies=pacsService.findStudiesbyPatientFk(patient);
		
		for (Study study : studies) {
			List<Files> files=pacsService.getFilePathsByStudyUID(study);
			
			for (Files file : files) {
				String dcmFilePath=file.getFilePath()+file.getFileName();
				String retrieveAET="";
				String retrieveURL="";
			
				if(hmiResultObj.getResultDetails()==null) {
					hmiResultObj.setResultDetails(new ArrayList<>());
				}
				HmiResultObj result=pacsService.createAndSendKosFile(dcmFilePath, retrieveAET, retrieveURL, file.getFileName(),patient.getTcKimlikNo(),production);
				
				ResultDetailObj resultDetailObj=new ResultDetailObj();
					resultDetailObj.setAccessionNumber(patient.getAccessionNumber());
					resultDetailObj.setFileUid(""+file.getPk());
					resultDetailObj.setPatientId(patient.getPatId());
					resultDetailObj.setTcKimlikNo(patient.getTcKimlikNo());
					resultDetailObj.setResult(result.getResultKosFile());
					if(result.getResultStatu().equals("OK")) {	
						
						SendKos sendKos=new SendKos();
						sendKos.setAccessionNumber(patient.getAccessionNumber());
						sendKos.setPatId(patient.getPatId());
						sendKos.setCreateTime(new Date());
						sendKos.setKosFile(file.getFileName());
						sendKos.setSendFlag(0);
						sendKos.setStatus("PENDING");
						pacsService.createSendKos(sendKos);
					}
				
				hmiResultObj.getResultDetails().add(resultDetailObj);
			}
			hmiResultObj.setResultStatu("HASTANIN GÖRÜNTÜLERİ TELETIP SISTEMINE GONDERILMIŞTIR. LÜTFEN DETAYLI INCELEMENİZİ YAPINIZ ...");
			
			System.out.println("HASTANIN GÖRÜNTÜLERİ TELETIP SISTEMINE GONDERILMIŞTIR. LÜTFEN DETAYLI INCELEMENİZİ YAPINIZ ...");
		}
		
		}else {
			hmiResultObj.setResultStatu("HASTAYA AIT RANDEVU BULUNAMAMIŞTIR. ÖNCE ACCESSION NUMBER ILE BIRLIKTE RANDEVUSUNU OLUŞTURMANIZ GEREKMEKTEDİR");
		}
		
		
		
		return hmiResultObj;
	}
	
	@RequestMapping(value="/sendAllPatientToSend/{production}", method = RequestMethod.POST) 
	public @ResponseBody HmiResultObj sendAllPatientToSend(@RequestBody List<Patient> patients,@PathVariable int production ) {
		HmiResultObj hmiResultObj=new HmiResultObj();
		
		//List<SendKosModality> sendKosModalities=modalityService.findSendKosModalities();
		
		patients.forEach(p->{
		
		Patient patient=pacsService.findPatientByIdAndAccessionNumber(p.getPatId(), p.getAccessionNumber());
		if(patient!=null) {
				SendKos sendKos=new SendKos();
				sendKos.setAccessionNumber(patient.getAccessionNumber());
				sendKos.setPatId(patient.getPatId());
				sendKos.setCreateTime(new Date());
				List<Study> studies=pacsService.findStudiesbyPatientFk(patient);
			
			for (Study study : studies) {
				List<Files> files=pacsService.getFilePathsByStudyUID(study);
				
				for (Files file : files) {
					String dcmFilePath=file.getFilePath()+file.getFileName();
					String retrieveAET="";
					String retrieveURL="";
					
					if(hmiResultObj.getResultDetails()==null) {
						hmiResultObj.setResultDetails(new ArrayList<>());
					}
					
					
					HmiResultObj result=pacsService.createAndSendKosFile(dcmFilePath, retrieveAET, retrieveURL, file.getFileName(),patient.getTcKimlikNo(),production);
					
					ResultDetailObj resultDetailObj=new ResultDetailObj();
						resultDetailObj.setAccessionNumber(patient.getAccessionNumber());
						resultDetailObj.setFileUid(""+file.getPk());
						resultDetailObj.setPatientId(patient.getPatId());
						resultDetailObj.setTcKimlikNo(patient.getTcKimlikNo());
						resultDetailObj.setResult(file.getFileName());
						if(result.getResultStatu().equals("OK")) {	
							sendKos.setKosFile(result.getResultKosFile());
							sendKos.setSendFlag(0);
							sendKos.setStatus("PENDING");
							pacsService.createSendKos(sendKos);
						}
				}
			}
			hmiResultObj.setResultStatu("HASTANIN GÖRÜNTÜLERİ TELETIP SISTEMINE GONDERILMIŞTIR. LÜTFEN DETAYLI INCELEMENİZİ YAPINIZ ...");
			pacsService.createSendKos(sendKos);
		}else {
				hmiResultObj.setResultStatu("HASTAYA AIT RANDEVU BULUNAMAMIŞTIR. ÖNCE ACCESSION NUMBER ILE BIRLIKTE RANDEVUSUNU OLUŞTURMANIZ GEREKMEKTEDİR");
		}
		
		});
		
		return hmiResultObj;
	}
	
}
