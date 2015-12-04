package com.offact.addys.vo.smart;

import com.offact.addys.vo.AbstractVO;

public class AsVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

	private String asNo;
	private String groupId;
	private String groupName;
	private String customerKey;
	private String customerName;
	private String asState;
	private String asStateTrans;
	private String asSubState;
	private String productCode;
	private String productName;
	private String group1Id;
	private String group1Name;
	private String asCategory;
	private String asCode;
	private String asDetail;
	private String asImage;
	private String asTargetDate;
	
	private String asStartUserId;
	private String asStartUserName;
	private String asStartDateTime;
	private String asRequestUserId;
	private String asRequestUserName;
	private String asRequestDateTime;
	private String asResponseUserId;
	private String asResponseUserName;
	private String asResponseDateTime;
	private String asCompleteUserId;
	private String asCompleteUserName;
	private String asCompleteDateTime;
	private String asResult;
	private String asResultDateTime;

	private String asPolicy;
	
	private String receiveName;
	private String receiveTelNo;
	private String receiveType;
	private String receiveAddress;
	private String receiveAddressDetail;
	private String receivePost;
	private String customerRequest;
	private String purchaseDate;
	private String receiptImage;
	private String memo;
	private String asTransferNo;
	private String receiveTransferNo;
	
	private String idx;
	private String userId;
	private String userName;
	private String asHistory;
	private String asHistoryDateTime;
	
    private String searchGubun;
    private String searchValue;
    
    private String searchState;
    private String searchSubState;

	private String start_asDate;
	private String end_asDate;
	private String con_groupId;
    
    // /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
	public String getAsNo() {
		return asNo;
	}
	public void setAsNo(String asNo) {
		this.asNo = asNo;
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
	public String getAsImage() {
		return asImage;
	}
	public void setAsImage(String asImage) {
		this.asImage = asImage;
	}
	public String getAsTargetDate() {
		return asTargetDate;
	}
	public void setAsTargetDate(String asTargetDate) {
		this.asTargetDate = asTargetDate;
	}
	public String getAsStartUserId() {
		return asStartUserId;
	}
	public void setAsStartUserId(String asStartUserId) {
		this.asStartUserId = asStartUserId;
	}
	public String getAsStartDateTime() {
		return asStartDateTime;
	}
	public void setAsStartDateTime(String asStartDateTime) {
		this.asStartDateTime = asStartDateTime;
	}
	public String getAsRequestUserId() {
		return asRequestUserId;
	}
	public void setAsRequestUserId(String asRequestUserId) {
		this.asRequestUserId = asRequestUserId;
	}
	public String getAsRequestDateTime() {
		return asRequestDateTime;
	}
	public void setAsRequestDateTime(String asRequestDateTime) {
		this.asRequestDateTime = asRequestDateTime;
	}
	public String getAsResponseUserId() {
		return asResponseUserId;
	}
	public void setAsResponseUserId(String asResponseUserId) {
		this.asResponseUserId = asResponseUserId;
	}
	public String getAsResponseDateTime() {
		return asResponseDateTime;
	}
	public void setAsResponseDateTime(String asResponseDateTime) {
		this.asResponseDateTime = asResponseDateTime;
	}
	public String getAsCompleteUserId() {
		return asCompleteUserId;
	}
	public void setAsCompleteUserId(String asCompleteUserId) {
		this.asCompleteUserId = asCompleteUserId;
	}
	public String getAsCompleteDateTime() {
		return asCompleteDateTime;
	}
	public void setAsCompleteDateTime(String asCompleteDateTime) {
		this.asCompleteDateTime = asCompleteDateTime;
	}
	public String getAsResult() {
		return asResult;
	}
	public void setAsResult(String asResult) {
		this.asResult = asResult;
	}
	public String getAsResultDateTime() {
		return asResultDateTime;
	}
	public void setAsResultDateTime(String asResultDateTime) {
		this.asResultDateTime = asResultDateTime;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
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
	public String getAsSubState() {
		return asSubState;
	}
	public void setAsSubState(String asSubState) {
		this.asSubState = asSubState;
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
	
	public String getGroup1Id() {
		return group1Id;
	}
	public void setGroup1Id(String group1Id) {
		this.group1Id = group1Id;
	}
	public String getGroup1Name() {
		return group1Name;
	}
	public void setGroup1Name(String group1Name) {
		this.group1Name = group1Name;
	}
	
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceiveTelNo() {
		return receiveTelNo;
	}
	public void setReceiveTelNo(String receiveTelNo) {
		this.receiveTelNo = receiveTelNo;
	}
	public String getReceiveType() {
		return receiveType;
	}
	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getReceiveAddressDetail() {
		return receiveAddressDetail;
	}
	public void setReceiveAddressDetail(String receiveAddressDetail) {
		this.receiveAddressDetail = receiveAddressDetail;
	}
	public String getReceivePost() {
		return receivePost;
	}
	public void setReceivePost(String receivePost) {
		this.receivePost = receivePost;
	}
	public String getCustomerRequest() {
		return customerRequest;
	}
	public void setCustomerRequest(String customerRequest) {
		this.customerRequest = customerRequest;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getReceiptImage() {
		return receiptImage;
	}
	public void setReceiptImage(String receiptImage) {
		this.receiptImage = receiptImage;
	}
	
	public String getAsPolicy() {
		return asPolicy;
	}
	public void setAsPolicy(String asPolicy) {
		this.asPolicy = asPolicy;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAsTransferNo() {
		return asTransferNo;
	}
	public void setAsTransferNo(String asTransferNo) {
		this.asTransferNo = asTransferNo;
	}
	public String getReceiveTransferNo() {
		return receiveTransferNo;
	}
	public void setReceiveTransferNo(String receiveTransferNo) {
		this.receiveTransferNo = receiveTransferNo;
	}
	
	public String getAsStartUserName() {
		return asStartUserName;
	}
	public void setAsStartUserName(String asStartUserName) {
		this.asStartUserName = asStartUserName;
	}
	public String getAsRequestUserName() {
		return asRequestUserName;
	}
	public void setAsRequestUserName(String asRequestUserName) {
		this.asRequestUserName = asRequestUserName;
	}
	public String getAsResponseUserName() {
		return asResponseUserName;
	}
	public void setAsResponseUserName(String asResponseUserName) {
		this.asResponseUserName = asResponseUserName;
	}
	public String getAsCompleteUserName() {
		return asCompleteUserName;
	}
	public void setAsCompleteUserName(String asCompleteUserName) {
		this.asCompleteUserName = asCompleteUserName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSearchState() {
		return searchState;
	}
	public void setSearchState(String searchState) {
		this.searchState = searchState;
	}
	public String getSearchSubState() {
		return searchSubState;
	}
	public void setSearchSubState(String searchSubState) {
		this.searchSubState = searchSubState;
	}
	public String getAsStateTrans() {
		return asStateTrans;
	}
	public void setAsStateTrans(String asStateTrans) {
		this.asStateTrans = asStateTrans;
	}
    
}
