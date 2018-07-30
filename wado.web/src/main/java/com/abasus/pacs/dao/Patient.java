package com.abasus.pacs.dao;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="patient")
public class Patient {

	
	
	@Id
	@Column(name="PK")
	private long pk;
	
	@Column(name="PATID")
	private String patId;
	
	@Column(name="PAT_NAME")
	private String patName;

	@Column(name="PAT_BIRTHDAY")
	private Date patBirthday;

	@Column(name="PAT_SEX")
	private String patSex;

	@Column(name="UPDATE_TIME")
	private Date updateTime;

	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="AGE")
	private String age;

	@Column(name="MODALITY")
	private String modality;

	@Column(name="REASONFORSTUDY")
	private String reasonForStudy;

	@Column(name="STATU")
	private String statu;

	@Column(name="ACCESSION_NUMBER")
	private String accessionNumber;
	
	
	@Column(name="PHYSICIAN_NAME")
	private String physicianName;
	
	@Column(name="AET")
	private String aet;
	
	
	@Column(name="YIL")
	private int yil;
	
	@Column(name="AP")
	private int ap;
	
	@Column(name="SCHEMA_NAME")
	private String schemaName;
	
	@Column(name="PROTOKOL_NO")
	private String protokolNo;
	
	@Column(name="UYARI")
	private String uyari;
	
	@Column(name="SUT_KOD")
	private String sutKod;

	
	@Transient
	private String tcKimlikNo;

	
	public String getTcKimlikNo() {
		return tcKimlikNo;
	}

	public void setTcKimlikNo(String tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public Date getPatBirthday() {
		return patBirthday;
	}

	public void setPatBirthday(Date patBirthday) {
		this.patBirthday = patBirthday;
	}

	public String getPatSex() {
		return patSex;
	}

	public void setPatSex(String patSex) {
		this.patSex = patSex;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getReasonForStudy() {
		return reasonForStudy;
	}

	public void setReasonForStudy(String reasonForStudy) {
		this.reasonForStudy = reasonForStudy;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getAet() {
		return aet;
	}

	public void setAet(String aet) {
		this.aet = aet;
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

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getProtokolNo() {
		return protokolNo;
	}

	public void setProtokolNo(String protokolNo) {
		this.protokolNo = protokolNo;
	}

	public String getUyari() {
		return uyari;
	}

	public void setUyari(String uyari) {
		this.uyari = uyari;
	}

	public String getSutKod() {
		return sutKod;
	}

	public void setSutKod(String sutKod) {
		this.sutKod = sutKod;
	}
	
	
	
}
