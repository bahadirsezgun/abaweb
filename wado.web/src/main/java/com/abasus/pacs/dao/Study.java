package com.abasus.pacs.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="study")
public class Study {

	
	
	
	@Id
	@Column(name="PK")
	private long pk;
	
	@Column(name="PATIENT_FK")
	private long patientFk;
	
	@Column(name="INSTANCE_UID")
	private String instanceUid;

	@Column(name="STUDY_ID")
	private int studyId;

	@Column(name="STUDY_DATE")
	private Date studyDate;

	@Column(name="REF_PHYSICIAN")
	private String refPhysician;

	@Column(name="STUDY_DESC")
	private String studyDesc;

	@Column(name="MODALITY_IN_STUDY")
	private String modalityInStudy;

	@Column(name="NUMBER_OF_SERIES")
	private int numberOfSeries;

	@Column(name="UPDATE_TIME")
	private Date updateTime;

	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="YIL")
	private int yil;
	
	@Column(name="AP")
	private int ap;

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public long getPatientFk() {
		return patientFk;
	}

	public void setPatientFk(long patientFk) {
		this.patientFk = patientFk;
	}

	public String getInstanceUid() {
		return instanceUid;
	}

	public void setInstanceUid(String instanceUid) {
		this.instanceUid = instanceUid;
	}

	public int getStudyId() {
		return studyId;
	}

	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}

	public Date getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}

	public String getRefPhysician() {
		return refPhysician;
	}

	public void setRefPhysician(String refPhysician) {
		this.refPhysician = refPhysician;
	}

	

	public String getModalityInStudy() {
		return modalityInStudy;
	}

	public void setModalityInStudy(String modalityInStudy) {
		this.modalityInStudy = modalityInStudy;
	}

	public int getNumberOfSeries() {
		return numberOfSeries;
	}

	public void setNumberOfSeries(int numberOfSeries) {
		this.numberOfSeries = numberOfSeries;
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

	public String getStudyDesc() {
		return studyDesc;
	}

	public void setStudyDesc(String studyDesc) {
		this.studyDesc = studyDesc;
	}
	
	
	
	
}
