package com.offact.addys.vo.master;

import com.offact.addys.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class StockVO extends AbstractVO {
	
	private String stockDate;
	private String groupId;
	private String groupName;
	private String stockDateTime;
	private String lastUserId;
	private String lastUserName;
	
	private String productCode;
	private String productName;
	private String stockCnt;
	private String productPrice;
	private String stockPrice;

	private String start_stockDate;
	private String end_stockDate;
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
    
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
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
	public String getStockDateTime() {
		return stockDateTime;
	}
	public void setStockDateTime(String stockDateTime) {
		this.stockDateTime = stockDateTime;
	}
	public String getLastUserId() {
		return lastUserId;
	}
	public void setLastUserId(String lastUserId) {
		this.lastUserId = lastUserId;
	}
	public String getLastUserName() {
		return lastUserName;
	}
	public void setLastUserName(String lastUserName) {
		this.lastUserName = lastUserName;
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
	public String getStockCnt() {
		return stockCnt;
	}
	public void setStockCnt(String stockCnt) {
		this.stockCnt = stockCnt;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}
	public String getStart_stockDate() {
		return start_stockDate;
	}
	public void setStart_stockDate(String start_stockDate) {
		this.start_stockDate = start_stockDate;
	}
	public String getEnd_stockDate() {
		return end_stockDate;
	}
	public void setEnd_stockDate(String end_stockDate) {
		this.end_stockDate = end_stockDate;
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
