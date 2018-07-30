package com.abasus.pacs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.Ae;

@Repository
public interface AeRepository extends CrudRepository<Ae, Long> {

}
