package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;


public class OutCallSearchVO extends AbstractVO  {

	/**
	 * 공통 아웃콜이력 조회 vo
	 */
	private static final long serialVersionUID = 1L;

	private String outcallSno; 			// 아웃콜일련번호
	private String con_userDeptCode; 		// 사용자부서코드 검색조건
	private String con1_userDeptCode; 		// 사용자부서코드 검색조건
	private String userDeptCode; 		// 사용자부서코드
	private String userDeptCodeNm;		// 사용자부서코드명
	private String outboundGbnCode; 	// 아웃바운드구분코드
	private String outboundGbnCodeNm; 	// 아웃바운드구분코드 명
	private String regTime; 			// 등록시각

	private String regFromDate; 		// 등록일 시작일
	private String regToDate; 			// 등록일 종료일


	private String assignTime; 		    // 상담원할당시각
	private String completeTime; 		// 콜백처리완료시각
	private String ani; 				// 발신번호
	private String dnis; 				// 착신번호
	private String callbackReqTelno; 	// 콜백요청전화번호
	private String callbackVdnCode; 	// VDN코드
	private String skillCode; 			// 스킬코드
	private String skillNm; 			// 스킬코드명
	private String memberCertGbn; 		// 회원인증구분
	private String memberCustomerNum; 	// 회원고객번호(시리얼번호)
	private String customerId; 			// 고객ID
	private String customerNm; 			// 고객명
	private String partnerId; 			// 파트너ID
	private String partnerNm; 			// 파트너명
	private String callbackUserId; 		// 콜백처리상담원ID
	private String callbackUserNm; 		// 콜백처리상담원 이름
	private String con_callbackStateCode; 	// 콜백상태코드 검색조건
	private String con_outboundGbnCode; 	// 콜백구분코드 검색조건

	private String callbackStateCode; 	// 콜백상태코드
	private String callbackStateCodeNm; 	// 콜백상태코드명
	private String treatTitle; 			// 처리제목
	private String treatContents; 		// 처리내용
	private String frstRegTs; 			// 최초등록일시
	private String frstRegrId; 			// 최초등록자ID
	private String lastModTs; 			// 최종수정일시
	private String lastCortId; 			// 최종수정자ID
	private String elapseTime;			// 경과시간
	private String elapseTimeSec;		// 경과시간(초단위 표시)
	private String eventNm;			// 업로드 이벤트명
	private String ivrInflowRoute;		// 아웃콜 유입경로

	private String[] outCallCkeck;

	private String prePage;		// 경과시간(초단위 표시)


	private String regCnt;			// 등록건수
	private String assignCnt;		// 할당건수
	private String renounceCnt;		// 포기건수
	private String completeCnt;		// 완료건수

	private String tbSkillOk;              //스킬검증
	private String tbUserOk;              // 상담원검증
	private String tbPartnerInfoIsOk; // 파트너 검증
	private String errMsg; // 업로드 Error Message


    // 페이징
    private String            totalCount       = "0";
    private String            curPage          = "1";
    private String            rowCount         = "10";

	public String getOutcallSno() {
		return outcallSno;
	}
	public void setOutcallSno(String outcallSno) {
		this.outcallSno = outcallSno;
	}
	public String getUserDeptCode() {
		return userDeptCode;
	}
	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}
	public String getOutboundGbnCode() {
		return outboundGbnCode;
	}
	public void setOutboundGbnCode(String outboundGbnCode) {
		this.outboundGbnCode = outboundGbnCode;
	}
	public String getOutboundGbnCodeNm() {
		return outboundGbnCodeNm;
	}
	public void setOutboundGbnCodeNm(String outboundGbnCodeNm) {
		this.outboundGbnCodeNm = outboundGbnCodeNm;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
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
	public String getAni() {
		return ani;
	}
	public void setAni(String ani) {
		this.ani = ani;
	}
	public String getDnis() {
		return dnis;
	}
	public void setDnis(String dnis) {
		this.dnis = dnis;
	}
	public String getCallbackReqTelno() {
		return callbackReqTelno;
	}
	public void setCallbackReqTelno(String callbackReqTelno) {
		this.callbackReqTelno = callbackReqTelno;
	}
	public String getCallbackVdnCode() {
		return callbackVdnCode;
	}
	public void setCallbackVdnCode(String callbackVdnCode) {
		this.callbackVdnCode = callbackVdnCode;
	}
	public String getSkillCode() {
		return skillCode;
	}
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
	public String getMemberCertGbn() {
		return memberCertGbn;
	}
	public void setMemberCertGbn(String memberCertGbn) {
		this.memberCertGbn = memberCertGbn;
	}
	public String getMemberCustomerNum() {
		return memberCustomerNum;
	}
	public void setMemberCustomerNum(String memberCustomerNum) {
		this.memberCustomerNum = memberCustomerNum;
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
	public String getCallbackUserId() {
		return callbackUserId;
	}
	public void setCallbackUserId(String callbackUserId) {
		this.callbackUserId = callbackUserId;
	}
	public String getCallbackStateCode() {
		return callbackStateCode;
	}
	public void setCallbackStateCode(String callbackStateCode) {
		this.callbackStateCode = callbackStateCode;
	}
	public String getTreatTitle() {
		return treatTitle;
	}
	public void setTreatTitle(String treatTitle) {
		this.treatTitle = treatTitle;
	}
	public String getTreatContents() {
		return treatContents;
	}
	public void setTreatContents(String treatContents) {
		this.treatContents = treatContents;
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
	public String getElapseTimeSec() {
		return elapseTimeSec;
	}
	public void setElapseTimeSec(String elapseTimeSec) {
		this.elapseTimeSec = elapseTimeSec;
	}
	public String getUserDeptCodeNm() {
		return userDeptCodeNm;
	}
	public void setUserDeptCodeNm(String userDeptCodeNm) {
		this.userDeptCodeNm = userDeptCodeNm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getSkillNm() {
		return skillNm;
	}
	public void setSkillNm(String skillNm) {
		this.skillNm = skillNm;
	}
	public String getCallbackStateCodeNm() {
		return callbackStateCodeNm;
	}
	public void setCallbackStateCodeNm(String callbackStateCodeNm) {
		this.callbackStateCodeNm = callbackStateCodeNm;
	}
	public String getCon_userDeptCode() {
		return con_userDeptCode;
	}
	public void setCon_userDeptCode(String con_userDeptCode) {
		this.con_userDeptCode = con_userDeptCode;
	}
	public String getCon_callbackStateCode() {
		return con_callbackStateCode;
	}
	public void setCon_callbackStateCode(String con_callbackStateCode) {
		this.con_callbackStateCode = con_callbackStateCode;
	}
	public String getCallbackUserNm() {
		return callbackUserNm;
	}
	public void setCallbackUserNm(String callbackUserNm) {
		this.callbackUserNm = callbackUserNm;
	}
	public String getRegFromDate() {
		return regFromDate;
	}
	public void setRegFromDate(String regFromDate) {
		this.regFromDate = regFromDate;
	}
	public String getRegToDate() {
		return regToDate;
	}
	public void setRegToDate(String regToDate) {
		this.regToDate = regToDate;
	}
	public String getRegCnt() {
		return regCnt;
	}
	public void setRegCnt(String regCnt) {
		this.regCnt = regCnt;
	}
	public String getAssignCnt() {
		return assignCnt;
	}
	public void setAssignCnt(String assignCnt) {
		this.assignCnt = assignCnt;
	}
	public String getRenounceCnt() {
		return renounceCnt;
	}
	public void setRenounceCnt(String renounceCnt) {
		this.renounceCnt = renounceCnt;
	}
	public String getCompleteCnt() {
		return completeCnt;
	}
	public void setCompleteCnt(String completeCnt) {
		this.completeCnt = completeCnt;
	}
	public String getPrePage() {
		return prePage;
	}
	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}
	public String getTbSkillOk() {
		return tbSkillOk;
	}
	public void setTbSkillOk(String tbSkillOk) {
		this.tbSkillOk = tbSkillOk;
	}
	public String getTbUserOk() {
		return tbUserOk;
	}
	public void setTbUserOk(String tbUserOk) {
		this.tbUserOk = tbUserOk;
	}

	public String getTbPartnerInfoIsOk() {
		return tbPartnerInfoIsOk;
	}
	public void setTbPartnerInfoIsOk(String tbPartnerInfoIsOk) {
		this.tbPartnerInfoIsOk = tbPartnerInfoIsOk;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getEventNm() {
		return eventNm;
	}
	public void setEventNm(String eventNm) {
		this.eventNm = eventNm;
	}
	public String getIvrInflowRoute() {
		return ivrInflowRoute;
	}
	public void setIvrInflowRoute(String ivrInflowRoute) {
		this.ivrInflowRoute = ivrInflowRoute;
	}
	public String[] getOutCallCkeck() {
		return outCallCkeck;
	}
	public void setOutCallCkeck(String[] outCallCkeck) {
		this.outCallCkeck = outCallCkeck;
	}
	public String getCon_outboundGbnCode() {
		return con_outboundGbnCode;
	}
	public void setCon_outboundGbnCode(String con_outboundGbnCode) {
		this.con_outboundGbnCode = con_outboundGbnCode;
	}
	public String getCon1_userDeptCode() {
		return con1_userDeptCode;
	}
	public void setCon1_userDeptCode(String con1_userDeptCode) {
		this.con1_userDeptCode = con1_userDeptCode;
	}




}
