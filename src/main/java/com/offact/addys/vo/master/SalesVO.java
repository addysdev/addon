package com.offact.addys.vo.master;

import com.offact.addys.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class SalesVO extends AbstractVO {
	
	private String salesDate;
	private String groupId;
	private String groupName;
	private String updateDateTime;
	private String updateUserId;
	private String updateUserName;
	
	private String productCode;
	private String productName;
	
	private String salesCnt;
	private String productPrice;
	private String supplyPrice;
	private String vat;
	private String salesPrice;

	private String start_salesDate;
	private String end_salesDate;
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
    
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
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
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
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
	public String getSalesCnt() {
		return salesCnt;
	}
	public void setSalesCnt(String salesCnt) {
		this.salesCnt = salesCnt;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(String supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}
	public String getStart_salesDate() {
		return start_salesDate;
	}
	public void setStart_salesDate(String start_salesDate) {
		this.start_salesDate = start_salesDate;
	}
	public String getEnd_salesDate() {
		return end_salesDate;
	}
	public void setEnd_salesDate(String end_salesDate) {
		this.end_salesDate = end_salesDate;
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
