package com.abasus.pacs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.Instances;

@Repository
public interface InstancesRepository extends CrudRepository<Instances, Long>  {

	public List<Instances> findBySeriesFk(long seriesFk);
	
	public Instances findBySopInstanceUid(String sopInstanceUid);
	
	
}
