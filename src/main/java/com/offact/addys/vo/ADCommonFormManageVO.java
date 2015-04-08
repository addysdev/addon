package com.offact.addys.vo;

import java.io.Serializable;

public class ADCommonFormManageVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String formId;
	private String formTitle;
	private String formContents;
	private String formGubun;
	private String con_userDeptCode;
	private String con_formGubun;
	private String userDeptCode;
	private String userDeptName;
	private String formGubunNm;
	private String usingYn;
	private String usingYnNm;
	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;


	/** for paging */
	private String searchcodenm;
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public String getFormContents() {
		return formContents;
	}
	public void setFormContents(String formContents) {
		this.formContents = formContents;
	}
	public String getFormGubun() {
		return formGubun;
	}
	public void setFormGubun(String formGubun) {
		this.formGubun = formGubun;
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
	public String getSearchcodenm() {
		return searchcodenm;
	}
	public void setSearchcodenm(String searchcodenm) {
		this.searchcodenm = searchcodenm;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUsingYnNm() {
		return usingYnNm;
	}
	public void setUsingYnNm(String usingYnNm) {
		this.usingYnNm = usingYnNm;
	}
	public String getFormGubunNm() {
		return formGubunNm;
	}
	public void setFormGubunNm(String formGubunNm) {
		this.formGubunNm = formGubunNm;
	}
	public String getUserDeptCode() {
		return userDeptCode;
	}
	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}
	public String getUserDeptName() {
		return userDeptName;
	}
	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}
	public String getCon_userDeptCode() {
		return con_userDeptCode;
	}
	public void setCon_userDeptCode(String con_userDeptCode) {
		this.con_userDeptCode = con_userDeptCode;
	}
	public String getCon_formGubun() {
		return con_formGubun;
	}
	public void setCon_formGubun(String con_formGubun) {
		this.con_formGubun = con_formGubun;
	}





}
