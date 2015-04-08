package com.offact.addys.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ADSmsFormManageVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String smsId;
	private String smsContents;
	private String usingYn;
	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;
	
	
	/** for paging */
	private String searchcodenm;
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";
	
	
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	public String getSmsContents() {
		return smsContents;
	}
	public void setSmsContents(String smsContnets) {
		this.smsContents = smsContnets;
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
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getRowCount() {
		return rowCount;
	}
	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}

	public String getSearchcodenm() {
		return searchcodenm;
	}

	public void setSearchcodenm(String searchcodenm) {
		this.searchcodenm = searchcodenm;
	}

}
