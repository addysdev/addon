package com.offact.addys.vo.smart;

import com.offact.addys.vo.AbstractVO;

public class CounselVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

	private String idx;
	private String groupId;
	private String customerKey;
	private String customerId;
	private String customerName;
	private String counselState;
	private String counsel;
	private String counselDateTime;
	private String counselHistory;
	private String counselHistoryDateTime;
	
	private String upidx;
	private String userId;
	private String userName;
	private String counselResult;
	private String counselResultDateTime;
	
	private String stateUpdateUserId;
	private String stateUpdateUserName;
	private String stateUpdateDateTime;
	
    private String searchGubun;
    private String searchValue;

	private String start_counselDate;
	private String end_counselDate;
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
	public String getCounselState() {
		return counselState;
	}
	public void setCounselState(String counselState) {
		this.counselState = counselState;
	}
	public String getCounsel() {
		return counsel;
	}
	public void setCounsel(String counsel) {
		this.counsel = counsel;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCounselResult() {
		return counselResult;
	}
	public void setCounselResult(String counselResult) {
		this.counselResult = counselResult;
	}
	public String getCounselResultDateTime() {
		return counselResultDateTime;
	}
	public void setCounselResultDateTime(String counselResultDateTime) {
		this.counselResultDateTime = counselResultDateTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCounselDateTime() {
		return counselDateTime;
	}
	public void setCounselDateTime(String counselDateTime) {
		this.counselDateTime = counselDateTime;
	}
	public String getStateUpdateUserId() {
		return stateUpdateUserId;
	}
	public void setStateUpdateUserId(String stateUpdateUserId) {
		this.stateUpdateUserId = stateUpdateUserId;
	}
	public String getStateUpdateDateTime() {
		return stateUpdateDateTime;
	}
	public void setStateUpdateDateTime(String stateUpdateDateTime) {
		this.stateUpdateDateTime = stateUpdateDateTime;
	}
	public String getStateUpdateUserName() {
		return stateUpdateUserName;
	}
	public void setStateUpdateUserName(String stateUpdateUserName) {
		this.stateUpdateUserName = stateUpdateUserName;
	}
	public String getStart_counselDate() {
		return start_counselDate;
	}
	public void setStart_counselDate(String start_counselDate) {
		this.start_counselDate = start_counselDate;
	}
	public String getEnd_counselDate() {
		return end_counselDate;
	}
	public void setEnd_counselDate(String end_counselDate) {
		this.end_counselDate = end_counselDate;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getUpidx() {
		return upidx;
	}
	public void setUpidx(String upidx) {
		this.upidx = upidx;
	}
	public String getCounselHistory() {
		return counselHistory;
	}
	public void setCounselHistory(String counselHistory) {
		this.counselHistory = counselHistory;
	}
	public String getCounselHistoryDateTime() {
		return counselHistoryDateTime;
	}
	public void setCounselHistoryDateTime(String counselHistoryDateTime) {
		this.counselHistoryDateTime = counselHistoryDateTime;
	}
    
}
