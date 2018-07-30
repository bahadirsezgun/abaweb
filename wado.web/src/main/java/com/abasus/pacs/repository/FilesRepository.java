package com.abasus.pacs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.Files;
import com.abasus.pacs.dao.Instances;

@Repository
public interface FilesRepository extends CrudRepository<Files, Long> {

	public List<Files> findByInstanceFk(long instanceFk);
	
	
	
}
