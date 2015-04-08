package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;


public class CallHistorySearchVO extends AbstractVO  {

	/**
	 * 공통 상담이력 조회 vo
	 */
	private static final long serialVersionUID = 1L;
	
	private String crtNo; // 상담이력번호
	private String counselTypeCode; // 상담결과유형코드
	private String counselTypeCodeNm; // 상담결과유형코드명
	private String callDirectionCode; // 콜방향코드
	private String callDirectionCodeNm; // 콜방향코드 명
	private String vdnCode; // VDN코드
	private String outcallSno; // 아웃콜일련번호
	private String marketcallSno; // 마케팅콜일련번호
	private String interactionId; // 콜ID
	private String interactionStartTime; // 콜시작시각
	private String interactionEndTime; // 콜종료시각
	private String callTime; // 통화시간
	private String acwEndTime; // 후처리시각
	private String userDeptCode; // 사용자부서코드
	private String counselTelno; // 상담전화번호
	private String callPersonGbnCode; // 통화자구분코드
	private String callPersonGbnCodeNm; // 통화자구분코드명
	private String callResultCode; // 상담결과코드
	private String callResultCodeNm; // 상담결과코드명
	private String consultExileCode; // 상담유형코드
	private String consultExileCodeNm; // 상담유형코드명
	private String userId; // 사용자ID
	private String userNm; // 사용자명
	private String consultTitle; // 상담처리제목
	private String consultContents; // 상담처리내용
	private String inflowChannelCode; // 유입경로코드
	private String inflowChannelCodeNm; // 유입경로코드명
	private String vocYn; // VOC여부
	private String sdYn; // S&D여부
	private String mailSendYn; // MAIL발송여부
	private String smsContents; // SMS내용
	private String smsTelno; // SMS전화번호
	private String recordKey; // 녹취서버연동키
	private String meetingDate; // 미팅일시
	private String memberYn; // 회원여부
	private String sex; // 성별
	private String customerId; // 고객ID
	private String customerNm; // 고객명
	private String customerTelno; // 고객전화번호
	private String customerEmail; // 고객이메일
	private String customerGradeCode; // 고객등급코드
	private String customerGradeNm; // 고객등급명
	private String partnerId; // 파트너ID
	private String partnerNm; // 파트너명
	private String partnerTelno; // 파트너전화번호
	private String dealId; // 딜ID
	private String dealNm; // 딜명
	private String orderNo; // 주문번호
	private String categoryLCode; // 카테고리코드(대)
	private String categorySCode; // 카테고리코드(소)
	private String frstRegTs; // 최초등록일시
	private String frstRegrId; // 최초등록자ID
	private String lastModTs; // 최종수정일시
	private String lastCortId; // 최종수정자ID
	private String transferResultCodeNm;			// 이관처리결과 명
	private String claim;			// 클레임
	private String smsSendDate;			// sms발송일시
	private String ivrInflowRoute;			// ivr유입경로	
	private String reserveTime;			// 예약일시
	private String reserveInfo;			// 예약자/예약전화번호
	private String extOrgYn;			// 대외기관접수여부
	private int id;			// 상담번호
	private String counselResultInsertJobSno; // 업무별시퀀스
	private String counselResultInsertJobCrtNo; // 업무별 상담이력번호
	private String counselResultInsertJobDiv; // 업무별 구분코드
	private String counselCallYn; // 콜여부
	private String qnaNo; // 게시판 번호
	private String outinExpectDt; // 해지설치예정일
	private String meetingUrgentYn; // 미팅긴급여부
	private String meetingPersonNm; // 미팅담당자이름
	// 게시판 관련 내용 추가
	private String qnaRegisterDate; // 게시판 등록일시
	private String qnaAssignDate; 	// 게시판 할당일시
	private String qnaTitle; 		// 게시판 문의제목
	private String qnaContent; 		// 게시판 문의내용
	private String userIp; 		// 사용자IP
	private String smsSendTelNo; 		// SMS발송번호
	
	private String customerAge; // 고객연령
	private String customerEmail1; // 고객EMAIL_1
	private String customerEmail2; // 고객EMAIL_2
	private String customerEmailYn; // 게시판 등록일시

	public String getCrtNo() {
		return crtNo;
	}
	public void setCrtNo(String crtNo) {
		this.crtNo = crtNo;
	}
	public String getCounselTypeCode() {
		return counselTypeCode;
	}
	public void setCounselTypeCode(String counselTypeCode) {
		this.counselTypeCode = counselTypeCode;
	}
	public String getCounselTypeCodeNm() {
		return counselTypeCodeNm;
	}
	public void setCounselTypeCodeNm(String counselTypeCodeNm) {
		this.counselTypeCodeNm = counselTypeCodeNm;
	}
	public String getCallDirectionCode() {
		return callDirectionCode;
	}
	public void setCallDirectionCode(String callDirectionCode) {
		this.callDirectionCode = callDirectionCode;
	}
	public String getCallDirectionCodeNm() {
		return callDirectionCodeNm;
	}
	public void setCallDirectionCodeNm(String callDirectionCodeNm) {
		this.callDirectionCodeNm = callDirectionCodeNm;
	}
	public String getVdnCode() {
		return vdnCode;
	}
	public void setVdnCode(String vdnCode) {
		this.vdnCode = vdnCode;
	}
	public String getOutcallSno() {
		return outcallSno;
	}
	public void setOutcallSno(String outcallSno) {
		this.outcallSno = outcallSno;
	}
	public String getMarketcallSno() {
		return marketcallSno;
	}
	public void setMarketcallSno(String marketcallSno) {
		this.marketcallSno = marketcallSno;
	}
	public String getInteractionId() {
		return interactionId;
	}
	public void setInteractionId(String interactionId) {
		this.interactionId = interactionId;
	}
	public String getInteractionStartTime() {
		return interactionStartTime;
	}
	public void setInteractionStartTime(String interactionStartTime) {
		this.interactionStartTime = interactionStartTime;
	}
	public String getInteractionEndTime() {
		return interactionEndTime;
	}
	public void setInteractionEndTime(String interactionEndTime) {
		this.interactionEndTime = interactionEndTime;
	}
	public String getAcwEndTime() {
		return acwEndTime;
	}
	public void setAcwEndTime(String acwEndTime) {
		this.acwEndTime = acwEndTime;
	}
	public String getUserDeptCode() {
		return userDeptCode;
	}
	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}
	public String getCounselTelno() {
		return counselTelno;
	}
	public void setCounselTelno(String counselTelno) {
		this.counselTelno = counselTelno;
	}
	public String getCallPersonGbnCode() {
		return callPersonGbnCode;
	}
	public void setCallPersonGbnCode(String callPersonGbnCode) {
		this.callPersonGbnCode = callPersonGbnCode;
	}
	public String getCallPersonGbnCodeNm() {
		return callPersonGbnCodeNm;
	}
	public void setCallPersonGbnCodeNm(String callPersonGbnCodeNm) {
		this.callPersonGbnCodeNm = callPersonGbnCodeNm;
	}
	public String getCallResultCode() {
		return callResultCode;
	}
	public void setCallResultCode(String callResultCode) {
		this.callResultCode = callResultCode;
	}
	public String getCallResultCodeNm() {
		return callResultCodeNm;
	}
	public void setCallResultCodeNm(String callResultCodeNm) {
		this.callResultCodeNm = callResultCodeNm;
	}
	public String getConsultExileCode() {
		return consultExileCode;
	}
	public void setConsultExileCode(String consultExileCode) {
		this.consultExileCode = consultExileCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getConsultTitle() {
		return consultTitle;
	}
	public void setConsultTitle(String consultTitle) {
		this.consultTitle = consultTitle;
	}
	public String getConsultContents() {
		return consultContents;
	}
	public void setConsultContents(String consultContents) {
		this.consultContents = consultContents;
	}
	public String getInflowChannelCode() {
		return inflowChannelCode;
	}
	public void setInflowChannelCode(String inflowChannelCode) {
		this.inflowChannelCode = inflowChannelCode;
	}
	public String getInflowChannelCodeNm() {
		return inflowChannelCodeNm;
	}
	public void setInflowChannelCodeNm(String inflowChannelCodeNm) {
		this.inflowChannelCodeNm = inflowChannelCodeNm;
	}
	public String getVocYn() {
		return vocYn;
	}
	public void setVocYn(String vocYn) {
		this.vocYn = vocYn;
	}
	public String getSdYn() {
		return sdYn;
	}
	public void setSdYn(String sdYn) {
		this.sdYn = sdYn;
	}
	public String getMailSendYn() {
		return mailSendYn;
	}
	public void setMailSendYn(String mailSendYn) {
		this.mailSendYn = mailSendYn;
	}
	public String getSmsContents() {
		return smsContents;
	}
	public void setSmsContents(String smsContents) {
		this.smsContents = smsContents;
	}
	public String getSmsTelno() {
		return smsTelno;
	}
	public void setSmsTelno(String smsTelno) {
		this.smsTelno = smsTelno;
	}
	public String getRecordKey() {
		return recordKey;
	}
	public void setRecordKey(String recordKey) {
		this.recordKey = recordKey;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String getMemberYn() {
		return memberYn;
	}
	public void setMemberYn(String memberYn) {
		this.memberYn = memberYn;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getCustomerGradeCode() {
		return customerGradeCode;
	}
	public void setCustomerGradeCode(String customerGradeCode) {
		this.customerGradeCode = customerGradeCode;
	}
	public String getCustomerGradeNm() {
		return customerGradeNm;
	}
	public void setCustomerGradeNm(String customerGradeNm) {
		this.customerGradeNm = customerGradeNm;
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
	public String getPartnerTelno() {
		return partnerTelno;
	}
	public void setPartnerTelno(String partnerTelno) {
		this.partnerTelno = partnerTelno;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCategoryLCode() {
		return categoryLCode;
	}
	public void setCategoryLCode(String categoryLCode) {
		this.categoryLCode = categoryLCode;
	}
	public String getCategorySCode() {
		return categorySCode;
	}
	public void setCategorySCode(String categorySCode) {
		this.categorySCode = categorySCode;
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
	public String getTransferResultCodeNm() {
		return transferResultCodeNm;
	}
	public void setTransferResultCodeNm(String transferResultCodeNm) {
		this.transferResultCodeNm = transferResultCodeNm;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCounselResultInsertJobSno() {
		return counselResultInsertJobSno;
	}
	public void setCounselResultInsertJobSno(String counselResultInsertJobSno) {
		this.counselResultInsertJobSno = counselResultInsertJobSno;
	}
	public String getCounselResultInsertJobCrtNo() {
		return counselResultInsertJobCrtNo;
	}
	public void setCounselResultInsertJobCrtNo(String counselResultInsertJobCrtNo) {
		this.counselResultInsertJobCrtNo = counselResultInsertJobCrtNo;
	}
	public String getCounselResultInsertJobDiv() {
		return counselResultInsertJobDiv;
	}
	public void setCounselResultInsertJobDiv(String counselResultInsertJobDiv) {
		this.counselResultInsertJobDiv = counselResultInsertJobDiv;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	public String getSmsSendDate() {
		return smsSendDate;
	}
	public void setSmsSendDate(String smsSendDate) {
		this.smsSendDate = smsSendDate;
	}
	public String getIvrInflowRoute() {
		return ivrInflowRoute;
	}
	public void setIvrInflowRoute(String ivrInflowRoute) {
		this.ivrInflowRoute = ivrInflowRoute;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public String getReserveInfo() {
		return reserveInfo;
	}
	public void setReserveInfo(String reserveInfo) {
		this.reserveInfo = reserveInfo;
	}
	public String getExtOrgYn() {
		return extOrgYn;
	}
	public void setExtOrgYn(String extOrgYn) {
		this.extOrgYn = extOrgYn;
	}
	public String getConsultExileCodeNm() {
		return consultExileCodeNm;
	}
	public void setConsultExileCodeNm(String consultExileCodeNm) {
		this.consultExileCodeNm = consultExileCodeNm;
	}
	public String getCounselCallYn() {
		return counselCallYn;
	}
	public void setCounselCallYn(String counselCallYn) {
		this.counselCallYn = counselCallYn;
	}
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getQnaRegisterDate() {
		return qnaRegisterDate;
	}
	public void setQnaRegisterDate(String qnaRegisterDate) {
		this.qnaRegisterDate = qnaRegisterDate;
	}
	public String getQnaAssignDate() {
		return qnaAssignDate;
	}
	public void setQnaAssignDate(String qnaAssignDate) {
		this.qnaAssignDate = qnaAssignDate;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getOutinExpectDt() {
		return outinExpectDt;
	}
	public void setOutinExpectDt(String outinExpectDt) {
		this.outinExpectDt = outinExpectDt;
	}
	public String getMeetingUrgentYn() {
		return meetingUrgentYn;
	}
	public void setMeetingUrgentYn(String meetingUrgentYn) {
		this.meetingUrgentYn = meetingUrgentYn;
	}
	public String getMeetingPersonNm() {
		return meetingPersonNm;
	}
	public void setMeetingPersonNm(String meetingPersonNm) {
		this.meetingPersonNm = meetingPersonNm;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getSmsSendTelNo() {
		return smsSendTelNo;
	}
	public void setSmsSendTelNo(String smsSendTelNo) {
		this.smsSendTelNo = smsSendTelNo;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAge() {
		return customerAge;
	}
	public void setCustomerAge(String customerAge) {
		this.customerAge = customerAge;
	}
	public String getCustomerEmail1() {
		return customerEmail1;
	}
	public void setCustomerEmail1(String customerEmail1) {
		this.customerEmail1 = customerEmail1;
	}
	public String getCustomerEmail2() {
		return customerEmail2;
	}
	public void setCustomerEmail2(String customerEmail2) {
		this.customerEmail2 = customerEmail2;
	}
	public String getCustomerEmailYn() {
		return customerEmailYn;
	}
	public void setCustomerEmailYn(String customerEmailYn) {
		this.customerEmailYn = customerEmailYn;
	}
}
