package com.offact.addys.vo.order;

import com.offact.addys.vo.AbstractVO;

/**
 * @author 4530
 *
 */
public class TargetVO extends AbstractVO {
	
	private String orderCode;
	private String groupId;
	private String groupName;
	private String companyCode;
	private String companyName;
	private String orderDateTime;
	private String orderUserID;
	private String orderUserName;
	private String faxKey;
	private String smsKey;
	private String emailKey;
	private String faxNumber;
	private String mobilePhone;
	private String phone;
	private String email;
	private String orderEtc;
	private String orderAdress;
	private String buyResult;
	private String buyResultView;
	private String buyDateTime;
	private String buyUserId;
	private String buyUserName;
	private String deliveryDate;
	private String deliveryEtc;
	private String deliveryMethod;
	private String deliveryCharge;
	
	private String deliveryMobile;
	private String deliveryEmail;
	private String deliveryTel;
	private String deliveryFax;
	
	private String memo;
	
	private String orderCnt;
	private String orderPrice;
	private String productPrice;
	private String vatRate;
	private String vat;
	private String safeOrderCnt;
	
	private String defer_reason;
	
	private String con_groupId;
	private String con_orderState;
	private String con_companyCode;
	
    private String searchGubun;
    private String searchValue;
	
	private String errMsg;
	//상세 추가항목
	private String productCode;
	private String productName;
	private String stockDate;
	private String stockCnt;
	private String safeStock;
	private String holdStock;
	
	// /** for paging */
    private String totalCount       = "0";
    private String curPage          = "1";
    private String rowCount         = "10";
    private String page_limit_val1;
    private String page_limit_val2;
    
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	public String getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public String getOrderUserID() {
		return orderUserID;
	}
	public void setOrderUserID(String orderUserID) {
		this.orderUserID = orderUserID;
	}
	public String getOrderUserName() {
		return orderUserName;
	}
	public void setOrderUserName(String orderUserName) {
		this.orderUserName = orderUserName;
	}
	public String getFaxKey() {
		return faxKey;
	}
	public void setFaxKey(String faxKey) {
		this.faxKey = faxKey;
	}
	public String getSmsKey() {
		return smsKey;
	}
	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}
	public String getEmailKey() {
		return emailKey;
	}
	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrderEtc() {
		return orderEtc;
	}
	public void setOrderEtc(String orderEtc) {
		this.orderEtc = orderEtc;
	}
	public String getOrderAdress() {
		return orderAdress;
	}
	public void setOrderAdress(String orderAdress) {
		this.orderAdress = orderAdress;
	}
	public String getBuyResult() {
		return buyResult;
	}
	public void setBuyResult(String buyResult) {
		this.buyResult = buyResult;
	}
	public String getBuyResultView() {
		return buyResultView;
	}
	public void setBuyResultView(String buyResultView) {
		this.buyResultView = buyResultView;
	}
	public String getBuyDateTime() {
		return buyDateTime;
	}
	public void setBuyDateTime(String buyDateTime) {
		this.buyDateTime = buyDateTime;
	}
	public String getBuyUserId() {
		return buyUserId;
	}
	public void setBuyUserId(String buyUserId) {
		this.buyUserId = buyUserId;
	}
	public String getBuyUserName() {
		return buyUserName;
	}
	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryEtc() {
		return deliveryEtc;
	}
	public void setDeliveryEtc(String deliveryEtc) {
		this.deliveryEtc = deliveryEtc;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public String getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(String deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	public String getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(String orderCnt) {
		this.orderCnt = orderCnt;
	}
	
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getCon_groupId() {
		return con_groupId;
	}
	public void setCon_groupId(String con_groupId) {
		this.con_groupId = con_groupId;
	}
	public String getCon_orderState() {
		return con_orderState;
	}
	public void setCon_orderState(String con_orderState) {
		this.con_orderState = con_orderState;
	}
	public String getCon_companyCode() {
		return con_companyCode;
	}
	public void setCon_companyCode(String con_companyCode) {
		this.con_companyCode = con_companyCode;
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
	public String getStockCnt() {
		return stockCnt;
	}
	public void setStockCnt(String stockCnt) {
		this.stockCnt = stockCnt;
	}
	public String getSafeStock() {
		return safeStock;
	}
	public void setSafeStock(String safeStock) {
		this.safeStock = safeStock;
	}
	public String getHoldStock() {
		return holdStock;
	}
	public void setHoldStock(String holdStock) {
		this.holdStock = holdStock;
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
	public String getDeliveryMobile() {
		return deliveryMobile;
	}
	public void setDeliveryMobile(String deliveryMobile) {
		this.deliveryMobile = deliveryMobile;
	}
	public String getDeliveryEmail() {
		return deliveryEmail;
	}
	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public String getDeliveryFax() {
		return deliveryFax;
	}
	public void setDeliveryFax(String deliveryFax) {
		this.deliveryFax = deliveryFax;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getVatRate() {
		return vatRate;
	}
	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}
	public String getDefer_reason() {
		return defer_reason;
	}
	public void setDefer_reason(String defer_reason) {
		this.defer_reason = defer_reason;
	}
	public String getSafeOrderCnt() {
		return safeOrderCnt;
	}
	public void setSafeOrderCnt(String safeOrderCnt) {
		this.safeOrderCnt = safeOrderCnt;
	}
	
}
