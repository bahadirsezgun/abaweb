package com.abasus.pacs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.SendKos;

@Repository
public interface SendKosRepository  extends CrudRepository<SendKos, Long> {

}
