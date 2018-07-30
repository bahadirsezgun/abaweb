package com.abasus.pacs.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="send_kos_modality")
public class SendKosModality {

	
	@Id
	@Column(name="PK")
	private int pk;
	
	@Column(name="MODALITY")
	private String modality;

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}
	
	
}
