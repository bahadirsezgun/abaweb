package com.abasus.pacs.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="instances")
public class Instances {

	
	@Id
	@Column(name="PK")
	private long pk;
	
	@Column(name="SERIES_FK")
	private long seriesFk;
	
	@Column(name="SOPINSTANCEUID")
	private String sopInstanceUid;

	@Column(name="SOPCLASSUID")
	private int sopClassUid;

	@Column(name="INSTANCENO")
	private int instanceNo;

	@Column(name="UPDATETIME")
	private Date updateTime;

	@Column(name="CREATETIME")
	private Date createTime;

	@Column(name="PATIENTPOS")
	private String patientPos;
	
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

	public long getSeriesFk() {
		return seriesFk;
	}

	public void setSeriesFk(long seriesFk) {
		this.seriesFk = seriesFk;
	}

	public String getSopInstanceUid() {
		return sopInstanceUid;
	}

	public void setSopInstanceUid(String sopInstanceUid) {
		this.sopInstanceUid = sopInstanceUid;
	}

	public int getSopClassUid() {
		return sopClassUid;
	}

	public void setSopClassUid(int sopClassUid) {
		this.sopClassUid = sopClassUid;
	}

	public int getInstanceNo() {
		return instanceNo;
	}

	public void setInstanceNo(int instanceNo) {
		this.instanceNo = instanceNo;
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

	public String getPatientPos() {
		return patientPos;
	}

	public void setPatientPos(String patientPos) {
		this.patientPos = patientPos;
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
	
	
}
