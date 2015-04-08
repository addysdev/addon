package com.offact.addys.vo;

import java.io.Serializable;

public class ADCodeManageVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codeId;
	private String con_codeId;
	private String codeNm;
	private String code;
	private String name;
	private String description;
	private String usingYn;
	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;
	private String attribution_1;

	private String sessionUserDeptcode;
	private String sessionUserGroupcode;

	/** for paging */
	private String searchcodenm;
	private String searchcode;
	private String searchattribution_1;
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCon_codeId() {
		return con_codeId;
	}

	public void setCon_codeId(String con_codeId) {
		this.con_codeId = con_codeId;
	}

	public String getSessionUserDeptcode() {
		return sessionUserDeptcode;
	}

	public void setSessionUserDeptcode(String sessionUserDeptcode) {
		this.sessionUserDeptcode = sessionUserDeptcode;
	}

	public String getSessionUserGroupcode() {
		return sessionUserGroupcode;
	}

	public void setSessionUserGroupcode(String sessionUserGroupcode) {
		this.sessionUserGroupcode = sessionUserGroupcode;
	}

	public String getAttribution_1() {
		return attribution_1;
	}

	public void setAttribution_1(String attribution_1) {
		this.attribution_1 = attribution_1;
	}
	
	public String getSearchcode() {
		return searchcode;
	}

	public void setSearchcode(String searchcode) {
		this.searchcode = searchcode;
	}

	public String getSearchattribution_1() {
		return searchattribution_1;
	}

	public void setSearchattribution_1(String searchattribution_1) {
		this.searchattribution_1 = searchattribution_1;
	}


}
