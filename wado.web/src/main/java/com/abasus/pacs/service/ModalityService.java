package com.abasus.pacs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.abasus.pacs.dao.SendKosModality;
import com.abasus.pacs.repository.SendKosModalityRepository;

@Service
@Qualifier("modalityService")
public class ModalityService {

	@Autowired
	SendKosModalityRepository sendKosModalityRepository;

	
	public List<SendKosModality> findSendKosModalities(){
		List<SendKosModality> sendKosModalities=new ArrayList<>();
		sendKosModalityRepository.findAll().forEach(sk->{
			sendKosModalities.add(sk);
		});
		
		
		return sendKosModalities;
	}
	
	
}
