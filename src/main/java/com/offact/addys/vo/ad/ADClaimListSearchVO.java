package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADClaimListSearchVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    // 테이블컬럼
    private String            extOrgReceiveSno;         // 대외기관접수일련번호
    private String            crtNo;                    // 상담이력번호
    private String            customerId;               // 고객ID
    private String            customerNm;               // 고객명
    private String            customerTelno;            // 고객전화번호
    private String            dealId;                   // 딜ID
    private String            dealNm;                   // 딜명
    private String            orderNo;                  // 주문번호
    private String            categoryLNm;              // 카테고리명(대)
    private String            categorySNm;              // 카테고리명(소)
    private String            areaNm;                   // 지역명
    private String            inflowChannelCode;        // 유입채널코드
    private String            organCode;                // 기관코드
    private String            organPersonNm;            // 기관담당자명
    private String            receiveDate;              // 접수일
    private String            replyDate;                // 회신일
    private String            visitYn;                  // 내방여부
    private String            visitDate;                // 내방일자
    private String            compensationMeasureCode;  // 보상수단코드
    private String            compensationMeasureAmount; // 보상수단금액
    private String            remarks;                  // 비고
    private String            frstRegTs;                // 최초등록일시
    private String            frstRegrId;               // 최초등록자ID
    private String            lastModTs;                // 최종수정일시
    private String            lastCortId;               // 최종수정자ID

    // 추가조회컬럼
    private String            frstRegrNm;               // 등록자
    private String            organCodeNm;              // 기관코드명
    private String            compensationMeasureCodeNm;  // 보상수단코드명

    private String            frstRegTsMask;
    private String            userNm;
    private String            userId;
    private String            inflowChannelCodeNm;
    private String            exileCodePath;
    private String            exileCodePath1;
    private String            exileCodePath2;
    private String            exileCodePath3;
    private String            exileCodePath4;
    private String            exileCodePath5;
    private String            consultTitle;
    private String            consultContents;
    private String            vocYn;

    // 검색조건용
    private String            con_searchGb;             // 검색고객조건 구분(고객용)
    private String            con_searchValue;          // 검색고객조건 값(고객용)
    private String            con_startYmd;             // 검색조건 등록기간시작일
    private String            con_endYmd;               // 검색조건 등록기간종료일
    private String            con_userGb;               // 검색조건 등록자조건구분
    private String            con_userNm;               // 검색조건 등록자조건값
    private String            con_orderNo;              // 검색조건 주문번호
    private String            con_organCode;            // 검색조건 기관코드
    private String            con_visitYn;              // 검색조건 내방여부
    private String            con_extOrgReceiveSno;     // 검색조건 일련번호
    private String            con_categoryLCode;        // 검색조건 카테고리대분류
    private String            con_dealGb;               // 검색조건 딜구분
    private String            con_dealValue;            // 검색조건 딜조건값

    // 페이징
    private String            totalCount       = "";
    private String            curPage          = "1";
    private String            rowCount         = "10";
    private String            page_limit_val1;
    private String            page_limit_val2;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getCon_searchGb() {
        return con_searchGb;
    }

    public void setCon_searchGb(String con_searchGb) {
        this.con_searchGb = con_searchGb;
    }

    public String getCon_searchValue() {
        return con_searchValue;
    }

    public void setCon_searchValue(String con_searchValue) {
        this.con_searchValue = con_searchValue;
    }

    public String getCon_startYmd() {
        return con_startYmd;
    }

    public void setCon_startYmd(String con_startYmd) {
        this.con_startYmd = con_startYmd;
    }

    public String getCon_endYmd() {
        return con_endYmd;
    }

    public void setCon_endYmd(String con_endYmd) {
        this.con_endYmd = con_endYmd;
    }

    public String getCon_userNm() {
        return con_userNm;
    }

    public void setCon_userNm(String con_userNm) {
        this.con_userNm = con_userNm;
    }

    public String getCon_orderNo() {
        return con_orderNo;
    }

    public void setCon_orderNo(String con_orderNo) {
        this.con_orderNo = con_orderNo;
    }

    public String getCon_organCode() {
        return con_organCode;
    }

    public void setCon_organCode(String con_organCode) {
        this.con_organCode = con_organCode;
    }

    public String getCon_visitYn() {
        return con_visitYn;
    }

    public void setCon_visitYn(String con_visitYn) {
        this.con_visitYn = con_visitYn;
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

    public String getFrstRegrNm() {
        return frstRegrNm;
    }

    public void setFrstRegrNm(String frstRegrNm) {
        this.frstRegrNm = frstRegrNm;
    }

    public String getOrganCodeNm() {
        return organCodeNm;
    }

    public void setOrganCodeNm(String organCodeNm) {
        this.organCodeNm = organCodeNm;
    }

    public String getCon_extOrgReceiveSno() {
        return con_extOrgReceiveSno;
    }

    public void setCon_extOrgReceiveSno(String con_extOrgReceiveSno) {
        this.con_extOrgReceiveSno = con_extOrgReceiveSno;
    }

    public String getCompensationMeasureCodeNm() {
        return compensationMeasureCodeNm;
    }

    public void setCompensationMeasureCodeNm(String compensationMeasureCodeNm) {
        this.compensationMeasureCodeNm = compensationMeasureCodeNm;
    }

    public String getCon_userGb() {
        return con_userGb;
    }

    public void setCon_userGb(String con_userGb) {
        this.con_userGb = con_userGb;
    }

    public String getCon_categoryLCode() {
        return con_categoryLCode;
    }

    public void setCon_categoryLCode(String con_categoryLCode) {
        this.con_categoryLCode = con_categoryLCode;
    }

    public String getCon_dealGb() {
        return con_dealGb;
    }

    public void setCon_dealGb(String con_dealGb) {
        this.con_dealGb = con_dealGb;
    }

    public String getCon_dealValue() {
        return con_dealValue;
    }

    public void setCon_dealValue(String con_dealValue) {
        this.con_dealValue = con_dealValue;
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

    public String getFrstRegTsMask() {
        return frstRegTsMask;
    }

    public void setFrstRegTsMask(String frstRegTsMask) {
        this.frstRegTsMask = frstRegTsMask;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInflowChannelCodeNm() {
        return inflowChannelCodeNm;
    }

    public void setInflowChannelCodeNm(String inflowChannelCodeNm) {
        this.inflowChannelCodeNm = inflowChannelCodeNm;
    }

    public String getExileCodePath() {
        return exileCodePath;
    }

    public void setExileCodePath(String exileCodePath) {
        this.exileCodePath = exileCodePath;
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

    public String getVocYn() {
        return vocYn;
    }

    public void setVocYn(String vocYn) {
        this.vocYn = vocYn;
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

}
