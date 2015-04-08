package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;


public class ExtOrgReceiveVO extends AbstractVO  {

	/**
	 * 공통 대외기관 접수 vo
	 */
	private static final long serialVersionUID = 1L;
	
	private String extOrgReceiveSno; // 대외기관접수일련번호
	private String crtNo; // 상담이력번호
	private String customerId; // 고객ID
	private String customerNm; // 고객명
	private String customerTelno; // 고객전화번호
	private String dealId; // 딜ID
	private String dealNm; // 딜명
	private String orderNo; // 주문번호
	private String categoryLNm; // 카테고리명(대)
	private String categorySNm; // 카테고리명(소)
	private String areaNm; // 지역명
	private String inflowChannelCode; // 유입채널코드
	private String organCode; // 기관코드
	private String organName; // 기관명
	private String organPersonNm; // 기관담당자명
	private String receiveDate; // 접수일
	private String replyDate; // 회신일
	private String visitYn; // 내방여부
	private String visitDate; // 내방일자
	private String compensationMeasureCode; // 보상수단코드
	private String compensationMeasureAmount; // 보상수단금액
	private String remarks; // 비고
	private String frstRegTs; // 최초등록일시
	private String frstRegrId; // 최초등록자ID
	private String frstRegrNm; // 최초등록자명
	private String lastModTs; // 최종수정일시
	private String lastCortId; // 최종수정자ID
	private String id; //대외기관접수반환일련번호
	
	
	//**박승한 작업 시작 **//
	private String extOrgReceiveCnt; //대외기관접수건수
	
	private String compensationMeasureName; // 보상수단명칭
	
	private String curPage; // 페이징 처리를 위한 현재 페이지
	
	private String rowCount; // 페이지 당 전체 Row 수
	
	
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
	public String getCompensationMeasureName() {
		return compensationMeasureName;
	}
	public void setCompensationMeasureName(String compensationMeasureName) {
		this.compensationMeasureName = compensationMeasureName;
	}
	public String getExtOrgReceiveCnt() {
		return extOrgReceiveCnt;
	}
	public void setExtOrgReceiveCnt(String extOrgReceiveCnt) {
		this.extOrgReceiveCnt = extOrgReceiveCnt;
	}
	//**박승한 작업 종료 **//
	public String getExtOrgReceiveSno() {
		return extOrgReceiveSno;
	}
	
	public void setExtOrgReceiveSno(String extOrgReceiveSno) {
		this.extOrgReceiveSno = extOrgReceiveSno;
	}
	public String getCrtNo() {
		return crtNo;
	}
	public void setCrtNo(String crtNo) {
		this.crtNo = crtNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getCustomerTelno() {
		return customerTelno;
	}
	public void setCustomerTelno(String customerTelno) {
		this.customerTelno = customerTelno;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getDealNm() {
		return dealNm;
	}
	public void setDealNm(String dealNm) {
		this.dealNm = dealNm;
	}
	public String getCategoryLNm() {
		return categoryLNm;
	}
	public void setCategoryLNm(String categoryLNm) {
		this.categoryLNm = categoryLNm;
	}
	public String getCategorySNm() {
		return categorySNm;
	}
	public void setCategorySNm(String categorySNm) {
		this.categorySNm = categorySNm;
	}
	public String getAreaNm() {
		return areaNm;
	}
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}
	public String getInflowChannelCode() {
		return inflowChannelCode;
	}
	public void setInflowChannelCode(String inflowChannelCode) {
		this.inflowChannelCode = inflowChannelCode;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	public String getOrganPersonNm() {
		return organPersonNm;
	}
	public void setOrganPersonNm(String organPersonNm) {
		this.organPersonNm = organPersonNm;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public String getVisitYn() {
		return visitYn;
	}
	public void setVisitYn(String visitYn) {
		this.visitYn = visitYn;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getCompensationMeasureCode() {
		return compensationMeasureCode;
	}
	public void setCompensationMeasureCode(String compensationMeasureCode) {
		this.compensationMeasureCode = compensationMeasureCode;
	}
	public String getCompensationMeasureAmount() {
		return compensationMeasureAmount;
	}
	public void setCompensationMeasureAmount(String compensationMeasureAmount) {
		this.compensationMeasureAmount = compensationMeasureAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFrstRegTs() {
		return frstRegTs;
	}
	public void setFrstRegTs(String frstRegTs) {
		this.frstRegTs = frstRegTs;
	}
	public String getFrstRegrId() {
		return frstRegrId;
	}
	public void setFrstRegrId(String frstRegrId) {
		this.frstRegrId = frstRegrId;
	}
	public String getLastModTs() {
		return lastModTs;
	}
	public void setLastModTs(String lastModTs) {
		this.lastModTs = lastModTs;
	}
	public String getLastCortId() {
		return lastCortId;
	}
	public void setLastCortId(String lastCortId) {
		this.lastCortId = lastCortId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getFrstRegrNm() {
		return frstRegrNm;
	}
	public void setFrstRegrNm(String frstRegrNm) {
		this.frstRegrNm = frstRegrNm;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
