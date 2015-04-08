package com.offact.addys.vo.common;

import org.springframework.web.bind.annotation.RequestParam;

import com.offact.addys.vo.AbstractVO;


public class TransferSearchVO extends AbstractVO  {

	/**
	 * 공통 이관이력 조회 vo
	 */
	private static final long serialVersionUID = 1L;
		
	private String transferSno; 		// 이관일련번호
	private int crtNo; 				// 상담이력번호
	private String originalTransferSno; // 부모이관일련번호
	private String otherDeptTransferYn; // 타부서이관여부
	private String customerId; 			// 고객ID
	private String customerNm; 			// 고객명
	private String partnerId; 			// 파트너ID
	private String partnerNm; 			// 파트너명
	private String dealId; 				// 딜ID
	private String dealNm; 				// 딜명
	private String assignTime; 			// 할당시각
	private String completeTime; 		// 완료시각
	private String elapseTime; 			// 경과시간
	private String totalHandleTime; 	// 총처리시간
	private String transferResultCode; 	// 이관결과코드
	private String transferResultCodeNm;// 이관결과코드 명
	private String transferRegUserId; 	// 이관등록자ID
	private String transferRegUserNm; 	// 이관등록자명
	private String transferProcUserId; 	// 이관처리담당자ID	
	private String transferProcUserNm; 	// 이관처리담당자명
	private String transferReqTitle; 	// 이관요청제목
	private String transferTreatContents; // 이관처리내용
	private String urgentYn; 			// 긴급여부
	private String frstRegTs; 			// 최초등록일시
	private String frstRegrId; 			// 최초등록자ID
	private String lastModTs; 			// 최종수정일시
	private String lastCortId; 			// 최종수정자ID
	private String trClass; 			// TR CLASS명
	private int elapseTimeSec; 			// 경과시간(초)
	private String customerPartnerTelno;// 고객/파트너 전화번호
	// 추가 데이터(상담유형코드)
	private String consultExileCode;	// 상담유형코드
    private String consultExileCodeNm;	// 상담유형코드명
    
    private String lastTransferSno;	// 최종이관번호
    private String lastTransferResultCode;	// 최종이관상태코드
    
	// 조회조건
	private String searchType1;
	private String searchType2;
	private String searchValue;
	private String strCompliteYn;			// 완료건 제외여부
	private int id;			// 완료건 제외여부
	
	public String getTransferSno() {
		return transferSno;
	}
	public void setTransferSno(String transferSno) {
		this.transferSno = transferSno;
	}
	public int getCrtNo() {
		return crtNo;
	}
	public void setCrtNo(int crtNo) {
		this.crtNo = crtNo;
	}
	public String getOriginalTransferSno() {
		return originalTransferSno;
	}
	public void setOriginalTransferSno(String originalTransferSno) {
		this.originalTransferSno = originalTransferSno;
	}
	public String getOtherDeptTransferYn() {
		return otherDeptTransferYn;
	}
	public void setOtherDeptTransferYn(String otherDeptTransferYn) {
		this.otherDeptTransferYn = otherDeptTransferYn;
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
	public String getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public String getElapseTime() {
		return elapseTime;
	}
	public void setElapseTime(String elapseTime) {
		this.elapseTime = elapseTime;
	}
	public String getTotalHandleTime() {
		return totalHandleTime;
	}
	public void setTotalHandleTime(String totalHandleTime) {
		this.totalHandleTime = totalHandleTime;
	}
	public String getTransferResultCode() {
		return transferResultCode;
	}
	public void setTransferResultCode(String transferResultCode) {
		this.transferResultCode = transferResultCode;
	}
	public String getTransferResultCodeNm() {
		return transferResultCodeNm;
	}
	public void setTransferResultCodeNm(String transferResultCodeNm) {
		this.transferResultCodeNm = transferResultCodeNm;
	}
	public String getTransferRegUserId() {
		return transferRegUserId;
	}
	public void setTransferRegUserId(String transferRegUserId) {
		this.transferRegUserId = transferRegUserId;
	}
	public String getTransferProcUserId() {
		return transferProcUserId;
	}
	public void setTransferProcUserId(String transferProcUserId) {
		this.transferProcUserId = transferProcUserId;
	}
	public String getTransferReqTitle() {
		return transferReqTitle;
	}
	public void setTransferReqTitle(String transferReqTitle) {
		this.transferReqTitle = transferReqTitle;
	}
	public String getTransferTreatContents() {
		return transferTreatContents;
	}
	public void setTransferTreatContents(String transferTreatContents) {
		this.transferTreatContents = transferTreatContents;
	}
	public String getUrgentYn() {
		return urgentYn;
	}
	public void setUrgentYn(String urgentYn) {
		this.urgentYn = urgentYn;
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
	public String getTransferRegUserNm() {
		return transferRegUserNm;
	}
	public void setTransferRegUserNm(String transferRegUserNm) {
		this.transferRegUserNm = transferRegUserNm;
	}
	public String getTransferProcUserNm() {
		return transferProcUserNm;
	}
	public void setTransferProcUserNm(String transferProcUserNm) {
		this.transferProcUserNm = transferProcUserNm;
	}
	public String getTrClass() {
		return trClass;
	}
	public void setTrClass(String trClass) {
		this.trClass = trClass;
	}
	public int getElapseTimeSec() {
		return elapseTimeSec;
	}
	public void setElapseTimeSec(int elapseTimeSec) {
		this.elapseTimeSec = elapseTimeSec;
	}
	public String getSearchType1() {
		return searchType1;
	}
	public void setSearchType1(String searchType1) {
		this.searchType1 = searchType1;
	}
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getCustomerPartnerTelno() {
		return customerPartnerTelno;
	}
	public void setCustomerPartnerTelno(String customerPartnerTelno) {
		this.customerPartnerTelno = customerPartnerTelno;
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
	public String getStrCompliteYn() {
		return strCompliteYn;
	}
	public void setStrCompliteYn(String strCompliteYn) {
		this.strCompliteYn = strCompliteYn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastTransferSno() {
		return lastTransferSno;
	}
	public void setLastTransferSno(String lastTransferSno) {
		this.lastTransferSno = lastTransferSno;
	}
	public String getLastTransferResultCode() {
		return lastTransferResultCode;
	}
	public void setLastTransferResultCode(String lastTransferResultCode) {
		this.lastTransferResultCode = lastTransferResultCode;
	}
}
