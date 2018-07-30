package com.abasus.pacs.util;

import java.util.List;

public class HmiResultObj {

	private String resultStatu;

	private String resultKosFile;

	private List<ResultDetailObj> resultDetails;
	
	public List<ResultDetailObj> getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(List<ResultDetailObj> resultDetails) {
		this.resultDetails = resultDetails;
	}

	public String getResultStatu() {
		return resultStatu;
	}

	public void setResultStatu(String resultStatu) {
		this.resultStatu = resultStatu;
	}

	public String getResultKosFile() {
		return resultKosFile;
	}

	public void setResultKosFile(String resultKosFile) {
		this.resultKosFile = resultKosFile;
	}
	
	
	
}
