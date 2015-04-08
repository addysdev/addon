package com.offact.addys.vo;

import java.io.Serializable;

public class ADGroupCodeManageVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codeId;
	private String codeNm;
	private String description;
	private String usingYn;
	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;
	
	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsingYn() {
		return usingYn;
	}

	public void setUsingYn(String usingYn) {
		this.usingYn = usingYn;
	}

	public String getFrstRegTs() {
		return frstRegTs;
	}

	public void setFrstRegTs(String frstRegTs) {
		this.frstRegTs = frstRegTs;
	}

	public String getFrstRegrId() {
		return frstRegrId;
	}

	public void setFrstRegrId(String frstRegrId) {
		this.frstRegrId = frstRegrId;
	}

	public String getLastModTs() {
		return lastModTs;
	}

	public void setLastModTs(String lastModTs) {
		this.lastModTs = lastModTs;
	}

	public String getLastCortId() {
		return lastCortId;
	}

	public void setLastCortId(String lastCortId) {
		this.lastCortId = lastCortId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
