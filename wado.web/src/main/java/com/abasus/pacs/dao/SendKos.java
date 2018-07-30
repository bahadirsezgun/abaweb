package com.abasus.pacs.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sendkos")
public class SendKos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PK")
	private long pk;
	
	@Column(name="PATID")
	private String patId;
	
	@Column(name="ACCESSION_NUMBER")
	private String accessionNumber;

	@Column(name="RESULT_STATU")
	private String resultStatu;

	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="STATUS")
	private String status;
	
	@Column(name="SENDFLAG")
	private int sendFlag;
	
	@Column(name="KOSFILE")
	private String kosFile;
	
	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

	public String getResultStatu() {
		return resultStatu;
	}

	public void setResultStatu(String resultStatu) {
		this.resultStatu = resultStatu;
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

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(int sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getKosFile() {
		return kosFile;
	}

	public void setKosFile(String kosFile) {
		this.kosFile = kosFile;
	}
	
	
	
	
}
