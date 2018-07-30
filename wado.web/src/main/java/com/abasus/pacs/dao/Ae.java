package com.abasus.pacs.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ae")
public class Ae {

	
	@Id
	@Column(name="PK")
	private long pk;
	
	@Column(name="AET")
	private String aet;
	
	@Column(name="HOSTNAME")
	private String hostName;
	
	@Column(name="PORT")
	private int port;
	
	@Column(name="TYPE")
	private int type;
	
	@Column(name="COMENT")
	private String coment;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SERVER_TYPE")
	private String serverType;
	
	@Column(name="ACCEPTION")
	private int acception;
	
	@Column(name="ASDEF")
	private int asDef;
	
	@Column(name="MODALITY")
	private String modality;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="URETIMYILI")
	private int uretimYili;
	
	@Column(name="MAXCEKSAY")
	private String maxCekSay;
	
	@Column(name="STATUS")
	private String status;
	
	
	public long getPk() {
		return pk;
	}
	public void setPk(long pk) {
		this.pk = pk;
	}
	public String getAet() {
		return aet;
	}
	public void setAet(String aet) {
		this.aet = aet;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getComent() {
		return coment;
	}
	public void setComent(String coment) {
		this.coment = coment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public int getAcception() {
		return acception;
	}
	public void setAcception(int acception) {
		this.acception = acception;
	}
	public int getAsDef() {
		return asDef;
	}
	public void setAsDef(int asDef) {
		this.asDef = asDef;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getUretimYili() {
		return uretimYili;
	}
	public void setUretimYili(int uretimYili) {
		this.uretimYili = uretimYili;
	}
	public String getMaxCekSay() {
		return maxCekSay;
	}
	public void setMaxCekSay(String maxCekSay) {
		this.maxCekSay = maxCekSay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
