package com.abasus.pacs.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="series")
public class Series {

	
	@Id
	@Column(name="PK")
	private long pk;
	
	@Column(name="STUDY_FK")
	private long studyFk;
	
	@Column(name="SERIES_INSTANCE_UID")
	private String seriesInstanceUid;

	@Column(name="SERIES_NO")
	private int seriesNo;

	@Column(name="MODALITY")
	private String modality;

	@Column(name="INSTITUTION")
	private String institution;

	@Column(name="DEPARTMENT")
	private String department;

	@Column(name="NUMBEROFINSTANCE")
	private int numberOfInstance;

	@Column(name="CALLINGAET")
	private String callingAet;

	@Column(name="UPDATETIME")
	private Date updateTime;

	@Column(name="CREATETIME")
	private Date createTime;

	@Column(name="BODYPART")
	private String bodyPart;
	
	@Column(name="YIL")
	private int yil;
	
	@Column(name="AP")
	private int ap;
	/*
	@Column(name="SERIES_DESC")
	private String seriesDesc;
*/
	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	

	public String getSeriesInstanceUid() {
		return seriesInstanceUid;
	}

	public void setSeriesInstanceUid(String seriesInstanceUid) {
		this.seriesInstanceUid = seriesInstanceUid;
	}

	public int getSeriesNo() {
		return seriesNo;
	}

	public void setSeriesNo(int seriesNo) {
		this.seriesNo = seriesNo;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getNumberOfInstance() {
		return numberOfInstance;
	}

	public void setNumberOfInstance(int numberOfInstance) {
		this.numberOfInstance = numberOfInstance;
	}

	public String getCallingAet() {
		return callingAet;
	}

	public void setCallingAet(String callingAet) {
		this.callingAet = callingAet;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public int getYil() {
		return yil;
	}

	public void setYil(int yil) {
		this.yil = yil;
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int ap) {
		this.ap = ap;
	}

	/*
	public String getSeriesDesc() {
		return seriesDesc;
	}

	public void setSeriesDesc(String seriesDesc) {
		this.seriesDesc = seriesDesc;
	}
*/
	public long getStudyFk() {
		return studyFk;
	}

	public void setStudyFk(long studyFk) {
		this.studyFk = studyFk;
	}
	
	
	
	
}
