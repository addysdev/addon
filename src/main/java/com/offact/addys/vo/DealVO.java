package com.offact.addys.vo;

import java.io.Serializable;

/**
 * 딜 정보
 * @author 4530
 *
 */
public class DealVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dealId;
	private String dealNm;
	private String categorySCode;
	private String categorySNm;
	private String categoryLCode;
	private String categoryLNm;
	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;
	private String strDealId;
	private String strDealNm;

	private String page_limit_val1;
	private String page_limit_val2;


	private String dealUserLastModTs;
	private String assignFg;
	private String assignFgNm;


	private String userId;
	private String userNm;
	private String userDeptNm;


	private String userCnt;



	/** for paging */
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";

	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getDealNm() {
		return dealNm;
	}
	public void setDealNm(String dealNm) {
		this.dealNm = dealNm;
	}
	public String getCategorySCode() {
		return categorySCode;
	}
	public void setCategorySCode(String categorySCode) {
		this.categorySCode = categorySCode;
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
	public String getStrDealId() {
		return strDealId;
	}
	public void setStrDealId(String strDealId) {
		this.strDealId = strDealId;
	}
	public String getStrDealNm() {
		return strDealNm;
	}
	public void setStrDealNm(String strDealNm) {
		this.strDealNm = strDealNm;
	}
	public String getCategorySNm() {
		return categorySNm;
	}
	public void setCategorySNm(String categorySNm) {
		this.categorySNm = categorySNm;
	}
	public String getCategoryLCode() {
		return categoryLCode;
	}
	public void setCategoryLCode(String categoryLCode) {
		this.categoryLCode = categoryLCode;
	}
	public String getCategoryLNm() {
		return categoryLNm;
	}
	public void setCategoryLNm(String categoryLNm) {
		this.categoryLNm = categoryLNm;
	}
	public String getAssignFg() {
		return assignFg;
	}
	public void setAssignFg(String assignFg) {
		this.assignFg = assignFg;
	}
	public String getAssignFgNm() {
		return assignFgNm;
	}
	public void setAssignFgNm(String assignFgNm) {
		this.assignFgNm = assignFgNm;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(String userCnt) {
		this.userCnt = userCnt;
	}
	public String getUserDeptNm() {
		return userDeptNm;
	}
	public void setUserDeptNm(String userDeptNm) {
		this.userDeptNm = userDeptNm;
	}
	public String getDealUserLastModTs() {
		return dealUserLastModTs;
	}
	public void setDealUserLastModTs(String dealUserLastModTs) {
		this.dealUserLastModTs = dealUserLastModTs;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPage_limit_val1() {
		return page_limit_val1;
	}
	public void setPage_limit_val1(String page_limit_val1) {
		this.page_limit_val1 = page_limit_val1;
	}
	public String getPage_limit_val2() {
		return page_limit_val2;
	}
	public void setPage_limit_val2(String page_limit_val2) {
		this.page_limit_val2 = page_limit_val2;
	}


}
