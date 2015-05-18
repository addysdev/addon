package com.offact.addys.vo.recovery;

import com.offact.addys.vo.AbstractVO;

/**
 * @author 4530
 *
 */
public class RecoveryVO extends AbstractVO {

	private String companyName;
	private String recoveryCode;
	private String groupId;
	private String groupName;
	private String recoveryClosingDate;
	private String recoveryDateTime;
	private String recoveryUserID;
	private String recoveryUserName;
	private String recoveryState;
	private String recoveryStateView;
	private String regDateTime;
	private String regUserId;
	private String regUserName;
	private String completeDateTime;
	private String completeUserId;
	private String completeUserName;

	private String productCode;
	private String productName;
	private String productPrice;
	private String stockDate;
	private String recoveryCnt;
	private String stockCnt;
	private String addCnt;
	private String lossCnt;
	private String memo;
	private String memoCnt;
	private String recoveryResultCnt;
	private String recoveryResultPrice;
	private String recoveryMemo;
	private String recoveryMemoCnt;
	private String recoveryYn;
	private String createUserId;
	private String createUserName;
	private String createDateTime;
	private String updateUserId;
	private String updateUserName;
	private String updateDateTime;
	
	private String con_groupId;
	private String con_recoveryState;
	private String regroupid;
	
    private String searchGubun;
    private String searchValue;
    
    private String start_recoveryDate;
    private String end_recoveryDate;
	
	private String errMsg;
	
	// /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
	public String getRecoveryCode() {
		return recoveryCode;
	}
	public void setRecoveryCode(String recoveryCode) {
		this.recoveryCode = recoveryCode;
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
	public String getRecoveryClosingDate() {
		return recoveryClosingDate;
	}
	public void setRecoveryClosingDate(String recoveryClosingDate) {
		this.recoveryClosingDate = recoveryClosingDate;
	}
	public String getRecoveryDateTime() {
		return recoveryDateTime;
	}
	public void setRecoveryDateTime(String recoveryDateTime) {
		this.recoveryDateTime = recoveryDateTime;
	}
	public String getRecoveryUserID() {
		return recoveryUserID;
	}
	public void setRecoveryUserID(String recoveryUserID) {
		this.recoveryUserID = recoveryUserID;
	}
	public String getRecoveryUserName() {
		return recoveryUserName;
	}
	public void setRecoveryUserName(String recoveryUserName) {
		this.recoveryUserName = recoveryUserName;
	}
	public String getRecoveryState() {
		return recoveryState;
	}
	public void setRecoveryState(String recoveryState) {
		this.recoveryState = recoveryState;
	}
	public String getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}
	public String getRegUserId() {
		return regUserId;
	}
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	public String getRegUserName() {
		return regUserName;
	}
	public void setRegUserName(String regUserName) {
		this.regUserName = regUserName;
	}
	public String getCompleteDateTime() {
		return completeDateTime;
	}
	public void setCompleteDateTime(String completeDateTime) {
		this.completeDateTime = completeDateTime;
	}
	public String getCompleteUserId() {
		return completeUserId;
	}
	public void setCompleteUserId(String completeUserId) {
		this.completeUserId = completeUserId;
	}
	public String getCompleteUserName() {
		return completeUserName;
	}
	public void setCompleteUserName(String completeUserName) {
		this.completeUserName = completeUserName;
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
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
	public String getRecoveryCnt() {
		return recoveryCnt;
	}
	public void setRecoveryCnt(String recoveryCnt) {
		this.recoveryCnt = recoveryCnt;
	}
	public String getStockCnt() {
		return stockCnt;
	}
	public void setStockCnt(String stockCnt) {
		this.stockCnt = stockCnt;
	}
	public String getAddCnt() {
		return addCnt;
	}
	public void setAddCnt(String addCnt) {
		this.addCnt = addCnt;
	}
	public String getLossCnt() {
		return lossCnt;
	}
	public void setLossCnt(String lossCnt) {
		this.lossCnt = lossCnt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRecoveryResultCnt() {
		return recoveryResultCnt;
	}
	public void setRecoveryResultCnt(String recoveryResultCnt) {
		this.recoveryResultCnt = recoveryResultCnt;
	}
	public String getRecoveryMemo() {
		return recoveryMemo;
	}
	public void setRecoveryMemo(String recoveryMemo) {
		this.recoveryMemo = recoveryMemo;
	}
	public String getRecoveryYn() {
		return recoveryYn;
	}
	public void setRecoveryYn(String recoveryYn) {
		this.recoveryYn = recoveryYn;
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
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getCon_recoveryState() {
		return con_recoveryState;
	}
	public void setCon_recoveryState(String con_recoveryState) {
		this.con_recoveryState = con_recoveryState;
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
	public String getStart_recoveryDate() {
		return start_recoveryDate;
	}
	public void setStart_recoveryDate(String start_recoveryDate) {
		this.start_recoveryDate = start_recoveryDate;
	}
	public String getEnd_recoveryDate() {
		return end_recoveryDate;
	}
	public void setEnd_recoveryDate(String end_recoveryDate) {
		this.end_recoveryDate = end_recoveryDate;
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
	public String getRecoveryResultPrice() {
		return recoveryResultPrice;
	}
	public void setRecoveryResultPrice(String recoveryResultPrice) {
		this.recoveryResultPrice = recoveryResultPrice;
	}
	public String getRecoveryStateView() {
		return recoveryStateView;
	}
	public void setRecoveryStateView(String recoveryStateView) {
		this.recoveryStateView = recoveryStateView;
	}
	public String getRegroupid() {
		return regroupid;
	}
	public void setRegroupid(String regroupid) {
		this.regroupid = regroupid;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getMemoCnt() {
		return memoCnt;
	}
	public void setMemoCnt(String memoCnt) {
		this.memoCnt = memoCnt;
	}
	public String getRecoveryMemoCnt() {
		return recoveryMemoCnt;
	}
	public void setRecoveryMemoCnt(String recoveryMemoCnt) {
		this.recoveryMemoCnt = recoveryMemoCnt;
	}
	
}
