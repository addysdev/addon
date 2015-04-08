package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;


public class CategoryVO extends AbstractVO  {

	private String categorySCode;
	private String categorySNm;
	private String categoryLCode;
	private String categoryLNm;
	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;








	private String dealId;
	private String dealNm;
	private String strDealId;
	private String strDealNm;


	private String categoryUserLastModTs;
	private String assignFg;
	private String assignFgNm;


	private String userNm;
	private String userId;
	private String userDeptNm;


	private String userCnt;



	/** for paging */
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";


	public String getCategorySCode() {
		return categorySCode;
	}
	public void setCategorySCode(String categorySCode) {
		this.categorySCode = categorySCode;
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
	public String getUserDeptNm() {
		return userDeptNm;
	}
	public void setUserDeptNm(String userDeptNm) {
		this.userDeptNm = userDeptNm;
	}
	public String getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(String userCnt) {
		this.userCnt = userCnt;
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
	public String getRowCount() {
		return rowCount;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}
	public String getCategoryUserLastModTs() {
		return categoryUserLastModTs;
	}
	public void setCategoryUserLastModTs(String categoryUserLastModTs) {
		this.categoryUserLastModTs = categoryUserLastModTs;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}











}
