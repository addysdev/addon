package com.offact.addys.vo.history;

import java.io.Serializable;

import com.offact.addys.vo.AbstractVO;

public class WorkHistoryVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String groupId;
	
	private String idx;
	private String workDateTime;
	private String workIp;
	private String workUserId;
	private String workUserName;
	private String workGroupId;
	private String workGroupName;
	private String workCategory;
	private String workCategoryName;
	private String workCode;
	private String workCodeName;
	
	private String con_groupId;
	private String con_userId;
	private String con_workCategory;
	private String con_workCode;
	private String searchGubun;
	private String searchValue;
	
	private String start_workDate;
	private String end_workDate;

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
	public String getWorkDateTime() {
		return workDateTime;
	}
	public void setWorkDateTime(String workDateTime) {
		this.workDateTime = workDateTime;
	}
	public String getWorkIp() {
		return workIp;
	}
	public void setWorkIp(String workIp) {
		this.workIp = workIp;
	}
	public String getWorkUserId() {
		return workUserId;
	}
	public void setWorkUserId(String workUserId) {
		this.workUserId = workUserId;
	}
	public String getWorkCategory() {
		return workCategory;
	}
	public void setWorkCategory(String workCategory) {
		this.workCategory = workCategory;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getCon_workCategory() {
		return con_workCategory;
	}
	public void setCon_workCategory(String con_workCategory) {
		this.con_workCategory = con_workCategory;
	}
	public String getCon_workCode() {
		return con_workCode;
	}
	public void setCon_workCode(String con_workCode) {
		this.con_workCode = con_workCode;
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
	public String getWorkUserName() {
		return workUserName;
	}
	public void setWorkUserName(String workUserName) {
		this.workUserName = workUserName;
	}
	public String getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}
	public String getWorkGroupName() {
		return workGroupName;
	}
	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}
	public String getCon_userId() {
		return con_userId;
	}
	public void setCon_userId(String con_userId) {
		this.con_userId = con_userId;
	}
	public String getWorkCategoryName() {
		return workCategoryName;
	}
	public void setWorkCategoryName(String workCategoryName) {
		this.workCategoryName = workCategoryName;
	}
	public String getWorkCodeName() {
		return workCodeName;
	}
	public void setWorkCodeName(String workCodeName) {
		this.workCodeName = workCodeName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getStart_workDate() {
		return start_workDate;
	}
	public void setStart_workDate(String start_workDate) {
		this.start_workDate = start_workDate;
	}
	public String getEnd_workDate() {
		return end_workDate;
	}
	public void setEnd_workDate(String end_workDate) {
		this.end_workDate = end_workDate;
	}

}
