package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADOutCallHistoryVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    // 테이블컬럼
    private String            outcallSno;             // 아웃콜일련번호
    private String            userDeptCode;           // 사용자부서코드
    private String            outboundGbnCode;        // 아웃바운드구분코드
    private String            regTime;                // 등록시각
    private String            assignTime;             // 상담원할당시각
    private String            completeTime;           // 콜백처리완료시각
    private String            ani;                    // 발신번호
    private String            dnis;                   // 착신번호
    private String            callbackReqTelno;       // 콜백요청전화번호
    private String            vdnCode;                // VDN코드
    private String            skillCode;              // 스킬코드
    private String            memberCertGbn;          // 회원인증구분
    private String            memberCustomerNum;      // 회원고객번호(시리얼번호)
    private String            customerId;             // 고객ID
    private String            customerNm;             // 고객명
    private String            partnerId;              // 파트너ID
    private String            partnerNm;              // 파트너명
    private String            callbackUserId;         // 콜백처리상담원ID
    private String            callbackStateCode;      // 콜백상태코드
    private String            treatTitle;             // 처리제목
    private String            treatContents;          // 처리내용
    private String            frstRegTs;              // 최초등록일시
    private String            frstRegrId;             // 최초등록자ID
    private String            lastModTs;              // 최종수정일시
    private String            lastCortId;             // 최종수정자ID

    // 추가조회컬럼
    private String            outboundGbnCodeNm;      // 아웃바운드구분코드명
    private String            skillCodeNm;            // 스킬코드명
    private String            skillCodeExpNm;         // 스킬그룹+스킬코드명(상담유형)
    private String            callbackUserNm;         // 콜백처리상담원성명
    private String            callbackStateCodeNm;    // 처리상태코드명
    private String            recordkey;              // 녹취서버연동키
    private String            regTimeMask;            // 등록시각
    private String            assignTimeMask;         // 상담원할당시각

    // 검색조건용
    private String            con_outboundGbnCode;    // 검색조건 아웃바운드구분
    private String            con_regTimeStart;       // 검색조건 등록시작일
    private String            con_regTimeEnd;         // 검색조건 등록종료일
    private String            con_callbackReqTelno;   // 검색조건 콜백전화번호
    private String            con_callbackUserNm;     // 검색조건 상담원명
    private String            con_callbackStateCode;  // 검색조건 처리상태
    private String            con_userDeptCode;       // 검색조건 부서

    // 페이징
    private String            totalCount       = "0";
    private String            curPage          = "1";
    private String            rowCount         = "10";
    private String            page_limit_val1;
    private String            page_limit_val2;

    // 관리여부
    private String            ad_adConYn;             // 관리자여부
    private String            ad_sessionUserId;       // 관리자가 아닌경우필수 조회조건(현재사용자)
    private String            ad_sessionUserNm;       // 관리자가 아닌경우필수 조회조건(현재사용자)

    //
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

    public String getVdnCode() {
        return vdnCode;
    }

    public void setVdnCode(String vdnCode) {
        this.vdnCode = vdnCode;
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

    public String getOutboundGbnCodeNm() {
        return outboundGbnCodeNm;
    }

    public void setOutboundGbnCodeNm(String outboundGbnCodeNm) {
        this.outboundGbnCodeNm = outboundGbnCodeNm;
    }

    public String getSkillCodeNm() {
        return skillCodeNm;
    }

    public void setSkillCodeNm(String skillCodeNm) {
        this.skillCodeNm = skillCodeNm;
    }

    public String getSkillCodeExpNm() {
        return skillCodeExpNm;
    }

    public void setSkillCodeExpNm(String skillCodeExpNm) {
        this.skillCodeExpNm = skillCodeExpNm;
    }

    public String getCallbackUserNm() {
        return callbackUserNm;
    }

    public void setCallbackUserNm(String callbackUserNm) {
        this.callbackUserNm = callbackUserNm;
    }

    public String getCallbackStateCodeNm() {
        return callbackStateCodeNm;
    }

    public void setCallbackStateCodeNm(String callbackStateCodeNm) {
        this.callbackStateCodeNm = callbackStateCodeNm;
    }

    public String getCon_outboundGbnCode() {
        return con_outboundGbnCode;
    }

    public void setCon_outboundGbnCode(String con_outboundGbnCode) {
        this.con_outboundGbnCode = con_outboundGbnCode;
    }

    public String getCon_regTimeStart() {
        return con_regTimeStart;
    }

    public void setCon_regTimeStart(String con_regTimeStart) {
        this.con_regTimeStart = con_regTimeStart;
    }

    public String getCon_regTimeEnd() {
        return con_regTimeEnd;
    }

    public void setCon_regTimeEnd(String con_regTimeEnd) {
        this.con_regTimeEnd = con_regTimeEnd;
    }

    public String getCon_callbackReqTelno() {
        return con_callbackReqTelno;
    }

    public void setCon_callbackReqTelno(String con_callbackReqTelno) {
        this.con_callbackReqTelno = con_callbackReqTelno;
    }

    public String getCon_callbackUserNm() {
        return con_callbackUserNm;
    }

    public void setCon_callbackUserNm(String con_callbackUserNm) {
        this.con_callbackUserNm = con_callbackUserNm;
    }

    public String getCon_callbackStateCode() {
        return con_callbackStateCode;
    }

    public void setCon_callbackStateCode(String con_callbackStateCode) {
        this.con_callbackStateCode = con_callbackStateCode;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public String getCurPage() {
        return curPage;
    }

    public String getRowCount() {
        return rowCount;
    }

    public String getAd_adConYn() {
        return ad_adConYn;
    }

    public void setAd_adConYn(String ad_adConYn) {
        this.ad_adConYn = ad_adConYn;
    }

    public String getAd_sessionUserId() {
        return ad_sessionUserId;
    }

    public void setAd_sessionUserId(String ad_sessionUserId) {
        this.ad_sessionUserId = ad_sessionUserId;
    }

    public String getAd_sessionUserNm() {
        return ad_sessionUserNm;
    }

    public void setAd_sessionUserNm(String ad_sessionUserNm) {
        this.ad_sessionUserNm = ad_sessionUserNm;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getRecordkey() {
        return recordkey;
    }

    public void setRecordkey(String recordkey) {
        this.recordkey = recordkey;
    }

    public String getCon_userDeptCode() {
        return con_userDeptCode;
    }

    public void setCon_userDeptCode(String con_userDeptCode) {
        this.con_userDeptCode = con_userDeptCode;
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

    public String getRegTimeMask() {
        return regTimeMask;
    }

    public void setRegTimeMask(String regTimeMask) {
        this.regTimeMask = regTimeMask;
    }

    public String getAssignTimeMask() {
        return assignTimeMask;
    }

    public void setAssignTimeMask(String assignTimeMask) {
        this.assignTimeMask = assignTimeMask;
    }

}
