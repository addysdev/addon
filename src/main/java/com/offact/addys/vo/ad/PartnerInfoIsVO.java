package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;


public class PartnerInfoIsVO extends AbstractVO  {

	private String errMsg;

    private String partnerId;               // 파트너ID
	private String partnerNm;               // 파트너명
	private String businessType1;           // 1차업종분류
	private String businessType2;           // 2차업종분류
	private String businessType3;           // 3차업종분류
	private String partnerAddress;          // 주소
	private String partnerTelno;            // 파트너전화번호
	private String mobilePhoneNo;           // 휴대폰
	private String referenceUrl1;           // 참고URL1
	private String referenceUrl2;           // 참고URL2
	private String partnerMeno1;            // 파트너메모1
	private String partnerMeno2;            // 파트너메모2
	private String partnerMeno3;            // 파트너메모3
	private String priority;                // 우선순위
	private String source;                  // source
	private String readzenDt;               // 리드젠작업날짜
	private String partnerGradeScale;       // 파트너등급_규모
	private String partnerGradeAware;       // 파트너등급_인지도
	private String lastPartnerGrade;        // 최종파트너등급
	private String areaGbn;                 // 지역구분
	private String largeArea;               // 권역
	private String area;                    // 지역
	private String otherEncore;             // 타사앵콜
	private String selfEncore;              // 자사앵콜
	private String readzenPerson;           // 리드젠담당자
	private String partnerProvideDt;        // 파트너제공날짜
	private String isPersonId;              // IS담당자
	private String mcPersonNm;              // MC담당자
	private String progressStep;            // 진행단계
	private String reContactDt;             // 추후연락/재통화 날짜
	private String skillCode;             	// 스킬코드
	private String frstRegTs;               // 최초등록일시
	private String frstRegrId;              // 최초등록자ID
	private String lastModTs;               // 최종수정일시
	private String lastCortId;              // 최종수정자ID






	// 검색조건
	private String frstRegFromDate;            // 최초등록일시 From
	private String frstRegToDate;              // 최초등록일시 To


	private String tbSkillOk;              //스킬검증
	private String tbUserOk;              // 상담원검증




	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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
	public String getBusinessType1() {
		return businessType1;
	}
	public void setBusinessType1(String businessType1) {
		this.businessType1 = businessType1;
	}
	public String getBusinessType2() {
		return businessType2;
	}
	public void setBusinessType2(String businessType2) {
		this.businessType2 = businessType2;
	}
	public String getBusinessType3() {
		return businessType3;
	}
	public void setBusinessType3(String businessType3) {
		this.businessType3 = businessType3;
	}
	public String getPartnerAddress() {
		return partnerAddress;
	}
	public void setPartnerAddress(String partnerAddress) {
		this.partnerAddress = partnerAddress;
	}
	public String getPartnerTelno() {
		return partnerTelno;
	}
	public void setPartnerTelno(String partnerTelno) {
		this.partnerTelno = partnerTelno;
	}
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}
	public String getReferenceUrl1() {
		return referenceUrl1;
	}
	public void setReferenceUrl1(String referenceUrl1) {
		this.referenceUrl1 = referenceUrl1;
	}
	public String getReferenceUrl2() {
		return referenceUrl2;
	}
	public void setReferenceUrl2(String referenceUrl2) {
		this.referenceUrl2 = referenceUrl2;
	}
	public String getPartnerMeno1() {
		return partnerMeno1;
	}
	public void setPartnerMeno1(String partnerMeno1) {
		this.partnerMeno1 = partnerMeno1;
	}
	public String getPartnerMeno2() {
		return partnerMeno2;
	}
	public void setPartnerMeno2(String partnerMeno2) {
		this.partnerMeno2 = partnerMeno2;
	}
	public String getPartnerMeno3() {
		return partnerMeno3;
	}
	public void setPartnerMeno3(String partnerMeno3) {
		this.partnerMeno3 = partnerMeno3;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getReadzenDt() {
		return readzenDt;
	}
	public void setReadzenDt(String readzenDt) {
		this.readzenDt = readzenDt;
	}
	public String getPartnerGradeScale() {
		return partnerGradeScale;
	}
	public void setPartnerGradeScale(String partnerGradeScale) {
		this.partnerGradeScale = partnerGradeScale;
	}
	public String getPartnerGradeAware() {
		return partnerGradeAware;
	}
	public void setPartnerGradeAware(String partnerGradeAware) {
		this.partnerGradeAware = partnerGradeAware;
	}
	public String getLastPartnerGrade() {
		return lastPartnerGrade;
	}
	public void setLastPartnerGrade(String lastPartnerGrade) {
		this.lastPartnerGrade = lastPartnerGrade;
	}
	public String getAreaGbn() {
		return areaGbn;
	}
	public void setAreaGbn(String areaGbn) {
		this.areaGbn = areaGbn;
	}
	public String getLargeArea() {
		return largeArea;
	}
	public void setLargeArea(String largeArea) {
		this.largeArea = largeArea;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOtherEncore() {
		return otherEncore;
	}
	public void setOtherEncore(String otherEncore) {
		this.otherEncore = otherEncore;
	}
	public String getSelfEncore() {
		return selfEncore;
	}
	public void setSelfEncore(String selfEncore) {
		this.selfEncore = selfEncore;
	}
	public String getReadzenPerson() {
		return readzenPerson;
	}
	public void setReadzenPerson(String readzenPerson) {
		this.readzenPerson = readzenPerson;
	}
	public String getPartnerProvideDt() {
		return partnerProvideDt;
	}
	public void setPartnerProvideDt(String partnerProvideDt) {
		this.partnerProvideDt = partnerProvideDt;
	}
	public String getMcPersonNm() {
		return mcPersonNm;
	}
	public void setMcPersonNm(String mcPersonNm) {
		this.mcPersonNm = mcPersonNm;
	}
	public String getProgressStep() {
		return progressStep;
	}
	public void setProgressStep(String progressStep) {
		this.progressStep = progressStep;
	}
	public String getReContactDt() {
		return reContactDt;
	}
	public void setReContactDt(String reContactDt) {
		this.reContactDt = reContactDt;
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
	public String getSkillCode() {
		return skillCode;
	}
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
	public String getFrstRegFromDate() {
		return frstRegFromDate;
	}
	public void setFrstRegFromDate(String frstRegFromDate) {
		this.frstRegFromDate = frstRegFromDate;
	}
	public String getFrstRegToDate() {
		return frstRegToDate;
	}
	public void setFrstRegToDate(String frstRegToDate) {
		this.frstRegToDate = frstRegToDate;
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
	public String getIsPersonId() {
		return isPersonId;
	}
	public void setIsPersonId(String isPersonId) {
		this.isPersonId = isPersonId;
	}



}
