package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;


public class ReserveSearchVO extends AbstractVO  {

	/**
	 * 공통 예약이력 조회 vo
	 */
	private static final long serialVersionUID = 1L;
	
	private String reserveCallSno; 	// 예약콜일련번호
	private String regTime; 		// 등록시각
	private String reserveTime; 	// 예약시각
	private String elapseTime;		// 경과시간
	private String reserveUserId; 	// 예약상담원ID
	private String reserveReqTelno; // 예약요청전화번호
	private String reserveReqNm;    // 예약요청자
	private String customerId; 		// 고객ID
	private String customerNm; 		// 고객명
	private String partnerId; 		// 파트너ID
	private String partnerNm; 		// 파트너명
	private String isUrgent; 		// 긴급여부
	private String isConfirm; 		// 확인여부
	private String reserveTitle; 	// 예약제목
	private String reserveContents; // 예약내용
	private String reserveStateCode;// 예약상태코드
	private String reserveStateCodeNm;// 예약상태코드 명
	private String frstRegTs; 		// 최초등록일시
	private String frstRegrId; 		// 최초등록자ID
	private String lastModTs; 		// 최종수정일시
	private String lastCortId; 		// 최종수정자ID
	private int crtNo; 			// 상담이력번호
	private String userDeptCode; 	// 사용자부서코드
	private String dealNm; 			// 딜명
	private String dealId; 			// 딜ID
	private String overTimeYn; 		// 경과시간여부
	private String customerPartnerTelno;// 고객/파트너 전화번호
	// 추가 데이터(상담유형코드)
	private String consultExileCode;	// 상담유형코드
    private String consultExileCodeNm;	// 상담유형코드명
	
	public String getDealNm() {
		return dealNm;
	}
	public void setDealNm(String dealNm) {
		this.dealNm = dealNm;
	}
	public String getUserDeptCode() {
		return userDeptCode;
	}
	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}
	public int getCrtNo() {
		return crtNo;
	}
	public void setCrtNo(int crtNo) {
		this.crtNo = crtNo;
	}
	public String getReserveCallSno() {
		return reserveCallSno;
	}
	public void setReserveCallSno(String reserveCallSno) {
		this.reserveCallSno = reserveCallSno;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public String getReserveUserId() {
		return reserveUserId;
	}
	public void setReserveUserId(String reserveUserId) {
		this.reserveUserId = reserveUserId;
	}
	public String getReserveReqTelno() {
		return reserveReqTelno;
	}
	public void setReserveReqTelno(String reserveReqTelno) {
		this.reserveReqTelno = reserveReqTelno;
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
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerNm() {
		return partnerNm;
	}
	public void setPartnerNm(String partnerNm) {
		this.partnerNm = partnerNm;
	}
	public String getIsUrgent() {
		return isUrgent;
	}
	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
	}
	public String getIsConfirm() {
		return isConfirm;
	}
	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}
	public String getReserveTitle() {
		return reserveTitle;
	}
	public void setReserveTitle(String reserveTitle) {
		this.reserveTitle = reserveTitle;
	}
	public String getReserveContents() {
		return reserveContents;
	}
	public void setReserveContents(String reserveContents) {
		this.reserveContents = reserveContents;
	}
	public String getReserveStateCode() {
		return reserveStateCode;
	}
	public void setReserveStateCode(String reserveStateCode) {
		this.reserveStateCode = reserveStateCode;
	}
	public String getReserveStateCodeNm() {
		return reserveStateCodeNm;
	}
	public void setReserveStateCodeNm(String reserveStateCodeNm) {
		this.reserveStateCodeNm = reserveStateCodeNm;
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
	public String getElapseTime() {
		return elapseTime;
	}
	public void setElapseTime(String elapseTime) {
		this.elapseTime = elapseTime;
	}
	public String getOverTimeYn() {
		return overTimeYn;
	}
	public void setOverTimeYn(String overTimeYn) {
		this.overTimeYn = overTimeYn;
	}
	public String getCustomerPartnerTelno() {
		return customerPartnerTelno;
	}
	public void setCustomerPartnerTelno(String customerPartnerTelno) {
		this.customerPartnerTelno = customerPartnerTelno;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getConsultExileCode() {
		return consultExileCode;
	}
	public void setConsultExileCode(String consultExileCode) {
		this.consultExileCode = consultExileCode;
	}
	public String getConsultExileCodeNm() {
		return consultExileCodeNm;
	}
	public void setConsultExileCodeNm(String consultExileCodeNm) {
		this.consultExileCodeNm = consultExileCodeNm;
	}
	public String getReserveReqNm() {
		return reserveReqNm;
	}
	public void setReserveReqNm(String reserveReqNm) {
		this.reserveReqNm = reserveReqNm;
	}
}
