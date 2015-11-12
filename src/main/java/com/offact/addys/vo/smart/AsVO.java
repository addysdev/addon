package com.offact.addys.vo.smart;

import com.offact.addys.vo.AbstractVO;

public class AsVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

	private String idx;
	private String groupId;
	private String groupName;
	private String customerKey;
	private String customerId;
	private String customerName;
	private String asState;
	private String productCode;
	private String productName;
	private String asCompanyCode;
	private String asCompanyName;
	private String asCategory;
	private String asCode;
	private String asDetail;
	private String asTargetDate;
	private String asResult;
	
	private String upidx;
	private String userId;
	private String userName;
	private String asHistory;
	private String asHistoryDateTime;
	
    private String searchGubun;
    private String searchValue;

	private String start_asDate;
	private String end_asDate;
	private String con_groupId;
    
    // /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAsState() {
		return asState;
	}
	public void setAsState(String asState) {
		this.asState = asState;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAsCompanyCode() {
		return asCompanyCode;
	}
	public void setAsCompanyCode(String asCompanyCode) {
		this.asCompanyCode = asCompanyCode;
	}
	public String getAsCompanyName() {
		return asCompanyName;
	}
	public void setAsCompanyName(String asCompanyName) {
		this.asCompanyName = asCompanyName;
	}
	public String getAsCategory() {
		return asCategory;
	}
	public void setAsCategory(String asCategory) {
		this.asCategory = asCategory;
	}
	public String getAsCode() {
		return asCode;
	}
	public void setAsCode(String asCode) {
		this.asCode = asCode;
	}
	public String getAsDetail() {
		return asDetail;
	}
	public void setAsDetail(String asDetail) {
		this.asDetail = asDetail;
	}
	public String getAsTargetDate() {
		return asTargetDate;
	}
	public void setAsTargetDate(String asTargetDate) {
		this.asTargetDate = asTargetDate;
	}
	public String getAsResult() {
		return asResult;
	}
	public void setAsResult(String asResult) {
		this.asResult = asResult;
	}
	public String getUpidx() {
		return upidx;
	}
	public void setUpidx(String upidx) {
		this.upidx = upidx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAsHistory() {
		return asHistory;
	}
	public void setAsHistory(String asHistory) {
		this.asHistory = asHistory;
	}
	public String getAsHistoryDateTime() {
		return asHistoryDateTime;
	}
	public void setAsHistoryDateTime(String asHistoryDateTime) {
		this.asHistoryDateTime = asHistoryDateTime;
	}
	public String getSearchGubun() {
		return searchGubun;
	}
	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getStart_asDate() {
		return start_asDate;
	}
	public void setStart_asDate(String start_asDate) {
		this.start_asDate = start_asDate;
	}
	public String getEnd_asDate() {
		return end_asDate;
	}
	public void setEnd_asDate(String end_asDate) {
		this.end_asDate = end_asDate;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
