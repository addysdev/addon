package com.offact.addys.vo.master;

import com.offact.addys.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class OrderLimitVO extends AbstractVO {
	
	private String limitCode;
	private String groupId;
	private String groupName;
	private String companyCode;
	private String companyName;
	private String limitStartDate;
	private String limitEndDate;
	private String limitDateTime;
	private String limitUserId;
	private String deletedYn;
	private String deletedDateTime;
	private String deletedUserId;

	private String start_limitDate;
	private String end_limitDate;
	private String con_groupId;
	
    private String searchGubun;
    private String searchValue;
	
	private String errMsg;
	
	// /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
	public String getLimitCode() {
		return limitCode;
	}
	public void setLimitCode(String limitCode) {
		this.limitCode = limitCode;
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
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLimitStartDate() {
		return limitStartDate;
	}
	public void setLimitStartDate(String limitStartDate) {
		this.limitStartDate = limitStartDate;
	}
	public String getLimitEndDate() {
		return limitEndDate;
	}
	public void setLimitEndDate(String limitEndDate) {
		this.limitEndDate = limitEndDate;
	}
	public String getLimitDateTime() {
		return limitDateTime;
	}
	public void setLimitDateTime(String limitDateTime) {
		this.limitDateTime = limitDateTime;
	}
	public String getLimitUserId() {
		return limitUserId;
	}
	public void setLimitUserId(String limitUserId) {
		this.limitUserId = limitUserId;
	}
	public String getDeletedYn() {
		return deletedYn;
	}
	public void setDeletedYn(String deletedYn) {
		this.deletedYn = deletedYn;
	}
	public String getDeletedDateTime() {
		return deletedDateTime;
	}
	public void setDeletedDateTime(String deletedDateTime) {
		this.deletedDateTime = deletedDateTime;
	}
	public String getDeletedUserId() {
		return deletedUserId;
	}
	public void setDeletedUserId(String deletedUserId) {
		this.deletedUserId = deletedUserId;
	}
	public String getStart_limitDate() {
		return start_limitDate;
	}
	public void setStart_limitDate(String start_limitDate) {
		this.start_limitDate = start_limitDate;
	}
	public String getEnd_limitDate() {
		return end_limitDate;
	}
	public void setEnd_limitDate(String end_limitDate) {
		this.end_limitDate = end_limitDate;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
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
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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

}
