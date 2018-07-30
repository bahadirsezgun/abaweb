package com.abasus.pacs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.SendKosModality;

@Repository
public interface SendKosModalityRepository  extends CrudRepository<SendKosModality, Long> {

	
}
