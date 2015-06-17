package com.offact.addys.vo.analysis;

import com.offact.addys.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class HoldStockVO extends AbstractVO {
	
	private String groupId;
	private String groupName;
	private String userId;
	private String userName;
	private String applyDateCnt;
	
	private String productCode;
	private String productName;
	private String holdStockCnt;
	private String holdStockPrice;
	private String holdStockDateTime;
	private String holdUserId;
	private String holdUserName;
	
	private String saleAvg;
	private String recomendCnt;
	private String recomendPrice;
	private String resultRate;

	private String start_saleDate;
	private String end_saleDate;
	private String con_groupId;
	private String con_applyDateCnt;
	
    private String searchGubun;
    private String searchValue;
	
	private String errMsg;
	
	// /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
    
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
	public String getApplyDateCnt() {
		return applyDateCnt;
	}
	public void setApplyDateCnt(String applyDateCnt) {
		this.applyDateCnt = applyDateCnt;
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
	public String getHoldStockCnt() {
		return holdStockCnt;
	}
	public void setHoldStockCnt(String holdStockCnt) {
		this.holdStockCnt = holdStockCnt;
	}
	public String getHoldStockPrice() {
		return holdStockPrice;
	}
	public void setHoldStockPrice(String holdStockPrice) {
		this.holdStockPrice = holdStockPrice;
	}
	public String getHoldStockDateTime() {
		return holdStockDateTime;
	}
	public void setHoldStockDateTime(String holdStockDateTime) {
		this.holdStockDateTime = holdStockDateTime;
	}
	public String getHoldUserId() {
		return holdUserId;
	}
	public void setHoldUserId(String holdUserId) {
		this.holdUserId = holdUserId;
	}
	public String getHoldUserName() {
		return holdUserName;
	}
	public void setHoldUserName(String holdUserName) {
		this.holdUserName = holdUserName;
	}
	public String getRecomendCnt() {
		return recomendCnt;
	}
	public void setRecomendCnt(String recomendCnt) {
		this.recomendCnt = recomendCnt;
	}
	public String getRecomendPrice() {
		return recomendPrice;
	}
	public void setRecomendPrice(String recomendPrice) {
		this.recomendPrice = recomendPrice;
	}
	public String getResultRate() {
		return resultRate;
	}
	public void setResultRate(String resultRate) {
		this.resultRate = resultRate;
	}
	public String getStart_saleDate() {
		return start_saleDate;
	}
	public void setStart_saleDate(String start_saleDate) {
		this.start_saleDate = start_saleDate;
	}
	public String getEnd_saleDate() {
		return end_saleDate;
	}
	public void setEnd_saleDate(String end_saleDate) {
		this.end_saleDate = end_saleDate;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getCon_applyDateCnt() {
		return con_applyDateCnt;
	}
	public void setCon_applyDateCnt(String con_applyDateCnt) {
		this.con_applyDateCnt = con_applyDateCnt;
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
	public String getSaleAvg() {
		return saleAvg;
	}
	public void setSaleAvg(String saleAvg) {
		this.saleAvg = saleAvg;
	}
    
}
