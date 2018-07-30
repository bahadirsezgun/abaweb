package com.abasus.pacs.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="files")
public class Files {

	
	@Id
	@Column(name="PK")
	private long pk;
	
	@Column(name="INSTANCE_FK")
	private long instanceFk;
	
	@Column(name="FILEPATH")
	private String filePath;
	
	@Column(name="FILEUID")
	private String fileUid;
	
	@Column(name="CREATETIME")
	private Date createTime;
	
	@Column(name="FILENAME")
	private String fileName;
	
	@Column(name="COLS")
	private long cols;
	
	@Column(name="RWS")
	private long rws;
	
	@Column(name="POS")
	private double pos;
	
	@Column(name="YIL")
	private long yil;
	
	@Column(name="AP")
	private long ap;
	
	@Column(name="DOCTOR")
	private String doctor;
	/*
	@Column(name="TESCILKODU")
	private String tescilKodu;
	*/
	public long getPk() {
		return pk;
	}
	public void setPk(long pk) {
		this.pk = pk;
	}
	public long getInstanceFk() {
		return instanceFk;
	}
	public void setInstanceFk(long instanceFk) {
		this.instanceFk = instanceFk;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileUid() {
		return fileUid;
	}
	public void setFileUid(String fileUid) {
		this.fileUid = fileUid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getCols() {
		return cols;
	}
	public void setCols(long cols) {
		this.cols = cols;
	}
	public long getRws() {
		return rws;
	}
	public void setRws(long rws) {
		this.rws = rws;
	}
	public double getPos() {
		return pos;
	}
	public void setPos(double pos) {
		this.pos = pos;
	}
	public long getYil() {
		return yil;
	}
	public void setYil(long yil) {
		this.yil = yil;
	}
	public long getAp() {
		return ap;
	}
	public void setAp(long ap) {
		this.ap = ap;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	/*
	public String getTescilKodu() {
		return tescilKodu;
	}
	public void setTescilKodu(String tescilKodu) {
		this.tescilKodu = tescilKodu;
	}
	*/
	
	
}
