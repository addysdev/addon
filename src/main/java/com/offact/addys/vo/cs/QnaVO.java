package com.offact.addys.vo.cs;

import com.offact.addys.vo.AbstractVO;


public class QnaVO extends AbstractVO  {

	/**
	 * Q&A vo
	 */
	private static final long serialVersionUID = 1L;
	
	private String qnaNo; // 
	private String memberId; // 
	private String memberName; // 
	private String title; // 
	private String content; // 
	private String contentReply; // 
	private String memberSMSYn; // 
	private String htmlyn; // 
	private String dealSrl; // 
	private Integer promotionNo; // 
	private String registerDate; // 
	private String updateDate; // 
	private String confirmDate; // 
	private String repliedyn; // 
	private String registerIp; // 
	private Integer category1; // 
	private Integer category2; // 
	private Integer category3; // 
	private Integer category4; // 
	private Integer category5; // 
	private String csRequest; // 
	private String image1; // 
	private String image2; // 
	private String image3; // 
	private String answerId; // 
	private String answerName; // 
	private String answerDept; // 
	private String answerDate; // 
	private Integer grade; // 고객등급
	private String dealType; // 딜 타입
	private Integer bcatSrl; // 매출 카테고리 시리얼
	private Integer pcatSrl1; // 노출 카테고리 시리얼 1 depth
	private Integer pcatSrl2; // 노출 카테고리 시리얼 2 depth
	private String faqCat1; // 문의유형 카테고리 1depth
	private String faqCat2; // 문의유형 카테고리 2depth
	private String faqCat3; // 문의유형 카테고리 3depth
	private String faqCatNm; // 문의유형명 
	private String faqCatCd; // 문의유형코드

	private String receiveStatus; // 상태코드
	private String receiveStatusNm; // 상태코드명
	// 신규 추가 컬럼
	private String assignDept;	// 항당상담원부서코드
	private String assignId; // 할당상담원ID
	private String assignNm; // 할당상담원
	private String assignDate; // 할당일시
	private String autoAssignNm; // 자동할당상담원
	
	// 곽대식 추가
	private String elapseTime; // 경과시간
	private String pcatNm; // 노출카테고리명
	private String dealNm; // 딜명
	private Integer elapseTimeSec;	// 경과시간(초)
	private String autoAssignId; // 자동할당상담원
	private Integer autoCnt;	//자동할당 건수
	
	// 조회 전용
	private String searchGubun; // 검색기준(전체, 고객ID, 딜명, 카테고리)
	private String searchValue; // 조회값
	private String exileDeptCd; // 상담유형부서코드
	
	/** for paging */
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "7";
	private String page_limit_val1;
    private String page_limit_val2;
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHtmlyn() {
		return htmlyn;
	}
	public void setHtmlyn(String htmlyn) {
		this.htmlyn = htmlyn;
	}
	public String getDealSrl() {
		return dealSrl;
	}
	public void setDealSrl(String dealSrl) {
		this.dealSrl = dealSrl;
	}
	public Integer getPromotionNo() {
		return promotionNo;
	}
	public void setPromotionNo(Integer promotionNo) {
		this.promotionNo = promotionNo;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getRepliedyn() {
		return repliedyn;
	}
	public void setRepliedyn(String repliedyn) {
		this.repliedyn = repliedyn;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public Integer getCategory1() {
		return category1;
	}
	public void setCategory1(Integer category1) {
		this.category1 = category1;
	}
	public Integer getCategory2() {
		return category2;
	}
	public void setCategory2(Integer category2) {
		this.category2 = category2;
	}
	public Integer getCategory3() {
		return category3;
	}
	public void setCategory3(Integer category3) {
		this.category3 = category3;
	}
	public Integer getCategory4() {
		return category4;
	}
	public void setCategory4(Integer category4) {
		this.category4 = category4;
	}
	public Integer getCategory5() {
		return category5;
	}
	public void setCategory5(Integer category5) {
		this.category5 = category5;
	}
	public String getCsRequest() {
		return csRequest;
	}
	public void setCsRequest(String csRequest) {
		this.csRequest = csRequest;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getAnswerName() {
		return answerName;
	}
	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}
	public String getAnswerDept() {
		return answerDept;
	}
	public void setAnswerDept(String answerDept) {
		this.answerDept = answerDept;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public Integer getBcatSrl() {
		return bcatSrl;
	}
	public void setBcatSrl(Integer bcatSrl) {
		this.bcatSrl = bcatSrl;
	}
	public Integer getPcatSrl1() {
		return pcatSrl1;
	}
	public void setPcatSrl1(Integer pcatSrl1) {
		this.pcatSrl1 = pcatSrl1;
	}
	public Integer getPcatSrl2() {
		return pcatSrl2;
	}
	public void setPcatSrl2(Integer pcatSrl2) {
		this.pcatSrl2 = pcatSrl2;
	}
	public String getFaqCat1() {
		return faqCat1;
	}
	public void setFaqCat1(String faqCat1) {
		this.faqCat1 = faqCat1;
	}
	public String getFaqCat2() {
		return faqCat2;
	}
	public void setFaqCat2(String faqCat2) {
		this.faqCat2 = faqCat2;
	}
	public String getFaqCat3() {
		return faqCat3;
	}
	public void setFaqCat3(String faqCat3) {
		this.faqCat3 = faqCat3;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public String getReceiveStatusNm() {
		return receiveStatusNm;
	}
	public void setReceiveStatusNm(String receiveStatusNm) {
		this.receiveStatusNm = receiveStatusNm;
	}
	public String getElapseTime() {
		return elapseTime;
	}
	public void setElapseTime(String elapseTime) {
		this.elapseTime = elapseTime;
	}
	public String getPcatNm() {
		return pcatNm;
	}
	public void setPcatNm(String pcatNm) {
		this.pcatNm = pcatNm;
	}
	public String getDealNm() {
		return dealNm;
	}
	public void setDealNm(String dealNm) {
		this.dealNm = dealNm;
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
	public Integer getElapseTimeSec() {
		return elapseTimeSec;
	}
	public void setElapseTimeSec(Integer elapseTimeSec) {
		this.elapseTimeSec = elapseTimeSec;
	}
	public String getAssignId() {
		return assignId;
	}
	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getAssignNm() {
		return assignNm;
	}
	public void setAssignNm(String assignNm) {
		this.assignNm = assignNm;
	}
	public String getAutoAssignNm() {
		return autoAssignNm;
	}
	public void setAutoAssignNm(String autoAssignNm) {
		this.autoAssignNm = autoAssignNm;
	}
	public String getAutoAssignId() {
		return autoAssignId;
	}
	public void setAutoAssignId(String autoAssignId) {
		this.autoAssignId = autoAssignId;
	}
	public String getAssignDept() {
		return assignDept;
	}
	public void setAssignDept(String assignDept) {
		this.assignDept = assignDept;
	}
	public Integer getAutoCnt() {
		return autoCnt;
	}
	public void setAutoCnt(Integer autoCnt) {
		this.autoCnt = autoCnt;
	}
	public String getExileDeptCd() {
		return exileDeptCd;
	}
	public void setExileDeptCd(String exileDeptCd) {
		this.exileDeptCd = exileDeptCd;
	}
	public String getFaqCatNm() {
		return faqCatNm;
	}
	public void setFaqCatNm(String faqCatNm) {
		this.faqCatNm = faqCatNm;
	}
	public String getFaqCatCd() {
		return faqCatCd;
	}
	public void setFaqCatCd(String faqCatCd) {
		this.faqCatCd = faqCatCd;
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
	public String getContentReply() {
		return contentReply;
	}
	public void setContentReply(String contentReply) {
		this.contentReply = contentReply;
	}
	public String getMemberSMSYn() {
		return memberSMSYn;
	}
	public void setMemberSMSYn(String memberSMSYn) {
		this.memberSMSYn = memberSMSYn;
	}
}
