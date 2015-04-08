package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADTransferHistoryVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    // 테이블컬럼
    private String            transferSno;             // 이관일련번호
    private String            crtNo;                   // 상담이력번호
    private String            originalTransferSno;     // 부모이관일련번호
    private String            otherDeptTransferYn;     // 타부서이관여부
    private String            customerId;              // 고객ID
    private String            customerNm;              // 고객명
    private String            partnerId;               // 파트너ID
    private String            partnerNm;               // 파트너명
    private String            dealId;                  // 딜ID
    private String            dealNm;                  // 딜명
    private String            assignTime;              // 할당시각
    private String            completeTime;            // 완료시각
    private String            elapseTime;              // 경과시간
    private String            totalHandleTime;         // 총처리시간
    private String            transferResultCode;      // 이관결과코드
    private String            transferRegUserId;       // 이관등록자ID
    private String            transferProcUserId;      // 이관처리담당자ID
    private String            transferReqTitle;        // 이관요청제목
    private String            transferTreatContents;   // 이관처리내용
    private String            lastTransferSno;         // 최종이관일련번호
    private String            lastTransferResultCode;  // 최종이관결과코드
    private String            urgentYn;                // 긴급여부
    private String            frstRegTs;               // 최초등록일시
    private String            frstRegrId;              // 최초등록자ID
    private String            lastModTs;               // 최종수정일시
    private String            lastCortId;              // 최종수정자ID

    // 추가조회컬럼
    private String            frstTime;                // 최초등록일자(상담이력)
    private String            categorySNm;             // 지역명
    private String            consultTitle;            // 상담제목
    private String            transferRegUserNm;       // 이관등록자성명
    private String            transferProcUserNm;      // 이관처리담당자성명
    private String            transferResultCodeNm;    // 이관결과코드명
    private String            calcElapseTime;          // 계산경과시간
    private String            calcTotTime;             // 계산총처리시간
    private String            calcElapseTimeMask;      // 계산경과시간
    private String            calcTotTimeMask;         // 계산총처리시간
    private String            recordkey;               // 녹취서버연동키
    private String            customerTelno;           // 고객전화번호
    private String            partnerTelno;            // 파트너전화번호
    private String            frstRegTsMask;
    private String            userDeptCode;
    private String            callDirectionCodeNm;
    private String            ivrInflowRoute;
    private String            assignTimeMask;
    private String            completeTimeMask;
    private String            inflowChannelCodeNm;
    private String            interactionTotTime;
    private String            interactionStartTimeMask;
    private String            interactionEndTimeMask;
    private String            totAcwEndTime;
    private String            isUrgent;
    private String            reserveReqNm;
    private String            reserveReqTelno;
    private String            counselTelno;
    private String            customerGradeCodeNm;
    private String            sexNm;
    private String            callPersonGbnCodeNm;
    private String            callResultCodeNm;
    private String            exileCodePath;
    private String            exileCodePath1;
    private String            exileCodePath2;
    private String            exileCodePath3;
    private String            exileCodePath4;
    private String            exileCodePath5;
    private String            categoryLNm;
    private String            orderNo;
    private String            extOrgReceiveSno;
    private String            smsContents;

    // 검색조건용
    private String            con_searchPeriodType;    // 검색기간구분
    private String            con_searchPeriodStart;   // 검색기간시작일
    private String            con_searchPeriodEnd;     // 검색기간종료일
    private String            con_customerId;          // 검색고객아이디
    private String            con_customerNm;          // 검색고객명
    private String            con_customerTelno;       // 검색고객전화번호
    private String            con_partnerId;           // 검색파트너아이디
    private String            con_partnerNm;           // 검색파트너명
    private String            con_partnerTelno;        // 검색파트너전화번호
    private String            con_urgentYn;            // 검색이관형식(일반,긴급)
    private String            con_transferRegUserId;   // 검색등록자
    private String            con_transferProcUserId;  // 검색이관처리자
    private String            con_dealId;              // 검색딜ID
    private String            con_dealNm;              // 검색딜명
    private String            con_transferResultCode;  // 검색처리상태
    private String            con_area;                // 검색지역
    private String            con_elapsedTimeStart;    // 검색경과시간시작(분)
    private String            con_elapsedTimeEnd;      // 검색경과시간종료(분)
    private String            con_totTimeStart;        // 검색총처리시간시작(분)
    private String            con_totTimeEnd;          // 검색총처리시간종료(분)
    private String            con_transferSno;         // 대상 이관일련번호
    private String            con_userDeptCode;        // 부서
    private String            con_customerGradeCode;   // 회원등급
    private String            con_consultExileCode;    // 상담유형
    private String            con_consultExileDeptCode; // 상담유형부서
    private String            con_categoryLCode;       // 카테고리 대분류
    private String            con_categorySCode;       // 카테고리 소분류

    // 페이징
    private String            totalCount       = "0";
    private String            curPage          = "1";
    private String            rowCount         = "10";
    private String            page_limit_val1;
    private String            page_limit_val2;

    // 관리여부
    private String            ad_adConYn;              // 관리자여부
    private String            ad_sessionUserId;        // 관리자가 아닌경우필수 조회조건(현재사용자)
    private String            ad_sessionUserNm;        // 관리자가 아닌경우필수 조회조건(현재사용자)

    public String getTransferSno() {
        return transferSno;
    }

    public void setTransferSno(String transferSno) {
        this.transferSno = transferSno;
    }

    public String getCrtNo() {
        return crtNo;
    }

    public void setCrtNo(String crtNo) {
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

    public String getFrstTime() {
        return frstTime;
    }

    public void setFrstTime(String frstTime) {
        this.frstTime = frstTime;
    }

    public String getCategorySNm() {
        return categorySNm;
    }

    public void setCategorySNm(String categorySNm) {
        this.categorySNm = categorySNm;
    }

    public String getConsultTitle() {
        return consultTitle;
    }

    public void setConsultTitle(String consultTitle) {
        this.consultTitle = consultTitle;
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

    public String getTransferResultCodeNm() {
        return transferResultCodeNm;
    }

    public void setTransferResultCodeNm(String transferResultCodeNm) {
        this.transferResultCodeNm = transferResultCodeNm;
    }

    public String getCalcElapseTime() {
        return calcElapseTime;
    }

    public void setCalcElapseTime(String calcElapseTime) {
        this.calcElapseTime = calcElapseTime;
    }

    public String getCon_searchPeriodType() {
        return con_searchPeriodType;
    }

    public void setCon_searchPeriodType(String con_searchPeriodType) {
        this.con_searchPeriodType = con_searchPeriodType;
    }

    public String getCon_searchPeriodStart() {
        return con_searchPeriodStart;
    }

    public void setCon_searchPeriodStart(String con_searchPeriodStart) {
        this.con_searchPeriodStart = con_searchPeriodStart;
    }

    public String getCon_searchPeriodEnd() {
        return con_searchPeriodEnd;
    }

    public void setCon_searchPeriodEnd(String con_searchPeriodEnd) {
        this.con_searchPeriodEnd = con_searchPeriodEnd;
    }

    public String getCon_customerNm() {
        return con_customerNm;
    }

    public void setCon_customerNm(String con_customerNm) {
        this.con_customerNm = con_customerNm;
    }

    public String getCon_urgentYn() {
        return con_urgentYn;
    }

    public void setCon_urgentYn(String con_urgentYn) {
        this.con_urgentYn = con_urgentYn;
    }

    public String getCon_transferRegUserId() {
        return con_transferRegUserId;
    }

    public void setCon_transferRegUserId(String con_transferRegUserId) {
        this.con_transferRegUserId = con_transferRegUserId;
    }

    public String getCon_transferProcUserId() {
        return con_transferProcUserId;
    }

    public void setCon_transferProcUserId(String con_transferProcUserId) {
        this.con_transferProcUserId = con_transferProcUserId;
    }

    public String getCon_dealId() {
        return con_dealId;
    }

    public void setCon_dealId(String con_dealId) {
        this.con_dealId = con_dealId;
    }

    public String getCon_dealNm() {
        return con_dealNm;
    }

    public void setCon_dealNm(String con_dealNm) {
        this.con_dealNm = con_dealNm;
    }

    public String getCon_transferResultCode() {
        return con_transferResultCode;
    }

    public void setCon_transferResultCode(String con_transferResultCode) {
        this.con_transferResultCode = con_transferResultCode;
    }

    public String getCon_area() {
        return con_area;
    }

    public void setCon_area(String con_area) {
        this.con_area = con_area;
    }

    public String getCon_elapsedTimeStart() {
        return con_elapsedTimeStart;
    }

    public void setCon_elapsedTimeStart(String con_elapsedTimeStart) {
        this.con_elapsedTimeStart = con_elapsedTimeStart;
    }

    public String getCon_elapsedTimeEnd() {
        return con_elapsedTimeEnd;
    }

    public void setCon_elapsedTimeEnd(String con_elapsedTimeEnd) {
        this.con_elapsedTimeEnd = con_elapsedTimeEnd;
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

    public String getCon_transferSno() {
        return con_transferSno;
    }

    public void setCon_transferSno(String con_transferSno) {
        this.con_transferSno = con_transferSno;
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

    public String getCustomerTelno() {
        return customerTelno;
    }

    public void setCustomerTelno(String customerTelno) {
        this.customerTelno = customerTelno;
    }

    public String getCon_customerTelno() {
        return con_customerTelno;
    }

    public void setCon_customerTelno(String con_customerTelno) {
        this.con_customerTelno = con_customerTelno;
    }

    public String getFrstRegTsMask() {
        return frstRegTsMask;
    }

    public void setFrstRegTsMask(String frstRegTsMask) {
        this.frstRegTsMask = frstRegTsMask;
    }

    public String getCalcTotTime() {
        return calcTotTime;
    }

    public void setCalcTotTime(String calcTotTime) {
        this.calcTotTime = calcTotTime;
    }

    public String getCalcElapseTimeMask() {
        return calcElapseTimeMask;
    }

    public void setCalcElapseTimeMask(String calcElapseTimeMask) {
        this.calcElapseTimeMask = calcElapseTimeMask;
    }

    public String getCalcTotTimeMask() {
        return calcTotTimeMask;
    }

    public void setCalcTotTimeMask(String calcTotTimeMask) {
        this.calcTotTimeMask = calcTotTimeMask;
    }

    public String getCon_totTimeStart() {
        return con_totTimeStart;
    }

    public void setCon_totTimeStart(String con_totTimeStart) {
        this.con_totTimeStart = con_totTimeStart;
    }

    public String getCon_totTimeEnd() {
        return con_totTimeEnd;
    }

    public void setCon_totTimeEnd(String con_totTimeEnd) {
        this.con_totTimeEnd = con_totTimeEnd;
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

    public String getCon_customerGradeCode() {
        return con_customerGradeCode;
    }

    public void setCon_customerGradeCode(String con_customerGradeCode) {
        this.con_customerGradeCode = con_customerGradeCode;
    }

    public String getCon_consultExileCode() {
        return con_consultExileCode;
    }

    public void setCon_consultExileCode(String con_consultExileCode) {
        this.con_consultExileCode = con_consultExileCode;
    }

    public String getCon_consultExileDeptCode() {
        return con_consultExileDeptCode;
    }

    public void setCon_consultExileDeptCode(String con_consultExileDeptCode) {
        this.con_consultExileDeptCode = con_consultExileDeptCode;
    }

    public String getCon_categoryLCode() {
        return con_categoryLCode;
    }

    public void setCon_categoryLCode(String con_categoryLCode) {
        this.con_categoryLCode = con_categoryLCode;
    }

    public String getCon_categorySCode() {
        return con_categorySCode;
    }

    public void setCon_categorySCode(String con_categorySCode) {
        this.con_categorySCode = con_categorySCode;
    }

    public String getPartnerTelno() {
        return partnerTelno;
    }

    public void setPartnerTelno(String partnerTelno) {
        this.partnerTelno = partnerTelno;
    }

    public String getCon_customerId() {
        return con_customerId;
    }

    public void setCon_customerId(String con_customerId) {
        this.con_customerId = con_customerId;
    }

    public String getCon_partnerId() {
        return con_partnerId;
    }

    public void setCon_partnerId(String con_partnerId) {
        this.con_partnerId = con_partnerId;
    }

    public String getCon_partnerNm() {
        return con_partnerNm;
    }

    public void setCon_partnerNm(String con_partnerNm) {
        this.con_partnerNm = con_partnerNm;
    }

    public String getCon_partnerTelno() {
        return con_partnerTelno;
    }

    public void setCon_partnerTelno(String con_partnerTelno) {
        this.con_partnerTelno = con_partnerTelno;
    }

    public String getCallDirectionCodeNm() {
        return callDirectionCodeNm;
    }

    public void setCallDirectionCodeNm(String callDirectionCodeNm) {
        this.callDirectionCodeNm = callDirectionCodeNm;
    }

    public String getIvrInflowRoute() {
        return ivrInflowRoute;
    }

    public void setIvrInflowRoute(String ivrInflowRoute) {
        this.ivrInflowRoute = ivrInflowRoute;
    }

    public String getAssignTimeMask() {
        return assignTimeMask;
    }

    public void setAssignTimeMask(String assignTimeMask) {
        this.assignTimeMask = assignTimeMask;
    }

    public String getCompleteTimeMask() {
        return completeTimeMask;
    }

    public void setCompleteTimeMask(String completeTimeMask) {
        this.completeTimeMask = completeTimeMask;
    }

    public String getInflowChannelCodeNm() {
        return inflowChannelCodeNm;
    }

    public void setInflowChannelCodeNm(String inflowChannelCodeNm) {
        this.inflowChannelCodeNm = inflowChannelCodeNm;
    }

    public String getInteractionTotTime() {
        return interactionTotTime;
    }

    public void setInteractionTotTime(String interactionTotTime) {
        this.interactionTotTime = interactionTotTime;
    }

    public String getInteractionStartTimeMask() {
        return interactionStartTimeMask;
    }

    public void setInteractionStartTimeMask(String interactionStartTimeMask) {
        this.interactionStartTimeMask = interactionStartTimeMask;
    }

    public String getInteractionEndTimeMask() {
        return interactionEndTimeMask;
    }

    public void setInteractionEndTimeMask(String interactionEndTimeMask) {
        this.interactionEndTimeMask = interactionEndTimeMask;
    }

    public String getTotAcwEndTime() {
        return totAcwEndTime;
    }

    public void setTotAcwEndTime(String totAcwEndTime) {
        this.totAcwEndTime = totAcwEndTime;
    }

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getReserveReqNm() {
        return reserveReqNm;
    }

    public void setReserveReqNm(String reserveReqNm) {
        this.reserveReqNm = reserveReqNm;
    }

    public String getReserveReqTelno() {
        return reserveReqTelno;
    }

    public void setReserveReqTelno(String reserveReqTelno) {
        this.reserveReqTelno = reserveReqTelno;
    }

    public String getCounselTelno() {
        return counselTelno;
    }

    public void setCounselTelno(String counselTelno) {
        this.counselTelno = counselTelno;
    }

    public String getCustomerGradeCodeNm() {
        return customerGradeCodeNm;
    }

    public void setCustomerGradeCodeNm(String customerGradeCodeNm) {
        this.customerGradeCodeNm = customerGradeCodeNm;
    }

    public String getSexNm() {
        return sexNm;
    }

    public void setSexNm(String sexNm) {
        this.sexNm = sexNm;
    }

    public String getCallPersonGbnCodeNm() {
        return callPersonGbnCodeNm;
    }

    public void setCallPersonGbnCodeNm(String callPersonGbnCodeNm) {
        this.callPersonGbnCodeNm = callPersonGbnCodeNm;
    }

    public String getCallResultCodeNm() {
        return callResultCodeNm;
    }

    public void setCallResultCodeNm(String callResultCodeNm) {
        this.callResultCodeNm = callResultCodeNm;
    }

    public String getExileCodePath() {
        return exileCodePath;
    }

    public void setExileCodePath(String exileCodePath) {
        this.exileCodePath = exileCodePath;
    }

    public String getCategoryLNm() {
        return categoryLNm;
    }

    public void setCategoryLNm(String categoryLNm) {
        this.categoryLNm = categoryLNm;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getExtOrgReceiveSno() {
        return extOrgReceiveSno;
    }

    public void setExtOrgReceiveSno(String extOrgReceiveSno) {
        this.extOrgReceiveSno = extOrgReceiveSno;
    }

    public String getSmsContents() {
        return smsContents;
    }

    public void setSmsContents(String smsContents) {
        this.smsContents = smsContents;
    }

    public String getExileCodePath1() {
        return exileCodePath1;
    }

    public void setExileCodePath1(String exileCodePath1) {
        this.exileCodePath1 = exileCodePath1;
    }

    public String getExileCodePath2() {
        return exileCodePath2;
    }

    public void setExileCodePath2(String exileCodePath2) {
        this.exileCodePath2 = exileCodePath2;
    }

    public String getExileCodePath3() {
        return exileCodePath3;
    }

    public void setExileCodePath3(String exileCodePath3) {
        this.exileCodePath3 = exileCodePath3;
    }

    public String getExileCodePath4() {
        return exileCodePath4;
    }

    public void setExileCodePath4(String exileCodePath4) {
        this.exileCodePath4 = exileCodePath4;
    }

    public String getExileCodePath5() {
        return exileCodePath5;
    }

    public void setExileCodePath5(String exileCodePath5) {
        this.exileCodePath5 = exileCodePath5;
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
