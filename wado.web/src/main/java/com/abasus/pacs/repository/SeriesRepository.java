package com.abasus.pacs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abasus.pacs.dao.Series;
import com.abasus.pacs.dao.Study;

@Repository
public interface SeriesRepository extends CrudRepository<Series, Long>  {

	public List<Series> findByStudyFk(long studyFk);
	
	public Series findBySeriesInstanceUid(String seriesInstanceUid);
	
}
