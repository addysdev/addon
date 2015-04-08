package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADReserveHistoryVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    // 테이블컬럼
    private String            reserveCallSno;         // 예약콜일련번호
    private String            regTime;                // 등록시각
    private String            reserveTime;            // 예약시각
    private String            reserveUserId;          // 예약상담원ID
    private String            reserveReqTelno;        // 예약요청전화번호
    private String            customerId;             // 고객ID
    private String            customerNm;             // 고객명
    private String            partnerId;              // 파트너ID
    private String            partnerNm;              // 파트너명
    private String            isUrgent;               // 긴급여부
    private String            isConfirm;              // 확인여부
    private String            reserveTitle;           // 예약제목
    private String            reserveContents;        // 예약내용
    private String            reserveStateCode;       // 예약상태코드
    private String            crtNo;                  // 상담이력번호
    private String            frstRegTs;              // 최초등록일시
    private String            frstRegrId;             // 최초등록자ID
    private String            lastModTs;              // 최종수정일시
    private String            lastCortId;             // 최종수정자ID
    // 추가조회컬럼
    private String            reserveUserNm;          // 예약상담원명
    private String            reserveStateCodeNm;     // 예약상태코드명
    private String            recordkey;              // 녹취서버연동키
    private String            regTimeMask;            // 등록시각
    private String            reserveTimeMask;        // 예약시각
    private String            lastModTsMask;          // 최종수정일시
    private String            userDeptCode;

    // 검색조건용
    private String            con_reserveTimeStart;   // 검색기간시작일(예약일)
    private String            con_reserveTimeEnd;     // 검색기간종료일(예약일)
    private String            con_reserveReqTelno;    // 검색예약연락처
    private String            con_customerNm;         // 검색고객명
    private String            con_isUrgent;           // 검색예약형식(일반,긴급)
    private String            con_reserveStateCode;   // 검색처리상태(예약상태)
    private String            con_reserveUserNm;      // 검색상담원명
    private String            con_userDeptCode;
    private String            con_partnerNm;          // 검색파트너명

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

    public String getCrtNo() {
        return crtNo;
    }

    public void setCrtNo(String crtNo) {
        this.crtNo = crtNo;
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

    public String getReserveUserNm() {
        return reserveUserNm;
    }

    public void setReserveUserNm(String reserveUserNm) {
        this.reserveUserNm = reserveUserNm;
    }

    public String getReserveStateCodeNm() {
        return reserveStateCodeNm;
    }

    public void setReserveStateCodeNm(String reserveStateCodeNm) {
        this.reserveStateCodeNm = reserveStateCodeNm;
    }

    public String getCon_reserveTimeStart() {
        return con_reserveTimeStart;
    }

    public void setCon_reserveTimeStart(String con_reserveTimeStart) {
        this.con_reserveTimeStart = con_reserveTimeStart;
    }

    public String getCon_reserveTimeEnd() {
        return con_reserveTimeEnd;
    }

    public void setCon_reserveTimeEnd(String con_reserveTimeEnd) {
        this.con_reserveTimeEnd = con_reserveTimeEnd;
    }

    public String getCon_reserveReqTelno() {
        return con_reserveReqTelno;
    }

    public void setCon_reserveReqTelno(String con_reserveReqTelno) {
        this.con_reserveReqTelno = con_reserveReqTelno;
    }

    public String getCon_customerNm() {
        return con_customerNm;
    }

    public void setCon_customerNm(String con_customerNm) {
        this.con_customerNm = con_customerNm;
    }

    public String getCon_isUrgent() {
        return con_isUrgent;
    }

    public void setCon_isUrgent(String con_isUrgent) {
        this.con_isUrgent = con_isUrgent;
    }

    public String getCon_reserveStateCode() {
        return con_reserveStateCode;
    }

    public void setCon_reserveStateCode(String con_reserveStateCode) {
        this.con_reserveStateCode = con_reserveStateCode;
    }

    public String getCon_reserveUserNm() {
        return con_reserveUserNm;
    }

    public void setCon_reserveUserNm(String con_reserveUserNm) {
        this.con_reserveUserNm = con_reserveUserNm;
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

    public String getRecordkey() {
        return recordkey;
    }

    public void setRecordkey(String recordkey) {
        this.recordkey = recordkey;
    }

    public String getRegTimeMask() {
        return regTimeMask;
    }

    public void setRegTimeMask(String regTimeMask) {
        this.regTimeMask = regTimeMask;
    }

    public String getReserveTimeMask() {
        return reserveTimeMask;
    }

    public void setReserveTimeMask(String reserveTimeMask) {
        this.reserveTimeMask = reserveTimeMask;
    }

    public String getLastModTsMask() {
        return lastModTsMask;
    }

    public void setLastModTsMask(String lastModTsMask) {
        this.lastModTsMask = lastModTsMask;
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

    public String getUserDeptCode() {
        return userDeptCode;
    }

    public void setUserDeptCode(String userDeptCode) {
        this.userDeptCode = userDeptCode;
    }

    public String getCon_userDeptCode() {
        return con_userDeptCode;
    }

    public void setCon_userDeptCode(String con_userDeptCode) {
        this.con_userDeptCode = con_userDeptCode;
    }

    public String getCon_partnerNm() {
        return con_partnerNm;
    }

    public void setCon_partnerNm(String con_partnerNm) {
        this.con_partnerNm = con_partnerNm;
    }

}
