package com.offact.addys.vo.master;

import com.offact.addys.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class OrderAddVO extends AbstractVO {
	
	private String addCode;
	private String groupId;
	private String groupName;
	private String companyCode;
	private String companyName;
	private String addStartDate;
	private String addEndDate;
	private String addDateTime;
	private String addUserId;
	private String deletedYn;
	private String deletedDateTime;
	private String deletedUserId;

	private String start_addDate;
	private String end_addDate;
	private String con_groupId;
	
    private String searchGubun;
    private String searchValue;
	
	private String errMsg;
	
	// /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_add_val1;
    private String page_add_val2;
    
	public String getAddCode() {
		return addCode;
	}
	public void setAddCode(String addCode) {
		this.addCode = addCode;
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
	public String getAddStartDate() {
		return addStartDate;
	}
	public void setAddStartDate(String addStartDate) {
		this.addStartDate = addStartDate;
	}
	public String getAddEndDate() {
		return addEndDate;
	}
	public void setAddEndDate(String addEndDate) {
		this.addEndDate = addEndDate;
	}
	public String getAddDateTime() {
		return addDateTime;
	}
	public void setAddDateTime(String addDateTime) {
		this.addDateTime = addDateTime;
	}
	public String getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
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
	public String getStart_addDate() {
		return start_addDate;
	}
	public void setStart_addDate(String start_addDate) {
		this.start_addDate = start_addDate;
	}
	public String getEnd_addDate() {
		return end_addDate;
	}
	public void setEnd_addDate(String end_addDate) {
		this.end_addDate = end_addDate;
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
	public String getPage_add_val1() {
		return page_add_val1;
	}
	public void setPage_add_val1(String page_add_val1) {
		this.page_add_val1 = page_add_val1;
	}
	public String getPage_add_val2() {
		return page_add_val2;
	}
	public void setPage_add_val2(String page_add_val2) {
		this.page_add_val2 = page_add_val2;
	}
	
    

}
