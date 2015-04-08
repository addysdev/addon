package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADCounselHistoryVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    // 테이블컬럼
    private String            crtNo;                   // 상담이력번호
    private String            counselTypeCode;         // 상담결과유형코드
    private String            callDirectionCode;       // 콜방향코드
    private String            vdnCode;                 // VDN코드
    private String            outcallSno;              // 아웃콜일련번호
    private String            interactionId;           // 콜ID
    private String            interactionStartTime;    // 콜시작시각
    private String            interactionEndTime;      // 콜종료시각
    private String            acwEndTime;              // 후처리시각
    private String            userDeptCode;            // 사용자부서코드
    private String            counselTelno;            // 상담전화번호
    private String            callPersonGbnCode;       // 통화자구분코드
    private String            callResultCode;          // 상담결과코드
    private String            consultExileCode;        // 상담유형코드
    private String            userId;                  // 사용자ID
    private String            consultTitle;            // 상담처리제목
    private String            consultContents;         // 상담처리내용
    private String            inflowChannelCode;       // 유입경로코드
    private String            vocYn;                   // VOC여부
    private String            sdYn;                    // S&D여부
    private String            mailSendYn;              // MAIL발송여부
    private String            smsContents;             // SMS내용
    private String            smsTelno;                // SMS전화번호
    private String            recordkey;               // 녹취서버연동키
    private String            meetingDate;             // 미팅일시
    private String            memberYn;                // 회원여부
    private String            sex;                     // 성별
    private String            customerId;              // 고객ID
    private String            customerNm;              // 고객명
    private String            customerTelno;           // 고객전화번호
    private String            customerGradeCode;       // 고객등급코드
    private String            customerGradeNm;         // 고객등급명
    private String            partnerId;               // 파트너ID
    private String            partnerNm;               // 파트너명
    private String            partnerTelno;            // 파트너전화번호
    private String            dealId;                  // 딜ID
    private String            dealNm;                  // 딜명
    private String            orderNo;                 // 주문번호
    private String            categoryLCode;           // 카테고리코드(대)
    private String            categorySCode;           // 카테고리코드(소)
    private String            frstRegTs;               // 최초등록일시
    private String            frstRegrId;              // 최초등록자ID
    private String            lastModTs;               // 최종수정일시
    private String            lastCortId;              // 최종수정자ID
    private String            marketcallSno;           // 마케팅콜일련번호
    private String            ivrInflowRoute;          // ivr유입경로
    private String            smsSendDate;             // SMS발송일

    // 추가조회컬럼
    private String            userNm;                  // 사용자성명
    private String            callResultCodeNm;        // 상담결과코드명칭(처리상태)
    private String            consultExileCodeNm;      // 상담유형코드명
    private String            interactionTime;         // 통화시간
    private String            extOrgReceiveYn;         // 대외기관접수여부
    private String            frstRegTsMask;           // 최초등록일시(마스킹처리)
    private String            callDirectionCodeNm;
    private String            inflowChannelCodeNm;
    private String            interactionTotTime;
    private String            interactionStartTimeMask;
    private String            interactionEndTimeMask;
    private String            totAcwEndTime;
    private String            transferYn;
    private String            reserveReqNm;
    private String            reserveReqTelno;
    private String            customerGradeCodeNm;
    private String            sexNm;
    private String            callPersonGbnCodeNm;
    private String            tendCode1Nm;
    private String            tendCode2Nm;
    private String            exileCodePath;
    private String            exileCodePath1;
    private String            exileCodePath2;
    private String            exileCodePath3;
    private String            exileCodePath4;
    private String            exileCodePath5;
    private String            categoryLNm;
    private String            categorySNm;
    private String            isUrgent;
    private String            extOrgReceiveSno;
    private String            qnaRegisterDateMask;
    private String            qnaAnswerDateMask;

    // 검색조건용
    private String            con_interactionStartTime; // 검색조건 상담일시작
    private String            con_interactionEndTime;  // 검색조건 상담일종료
    private String            con_callResultCode;      // 검색조건 처리상태
    private String            con_counselTelno;        // 검색조건 상담연락처
    private String            con_inflowChannelCode;   // 검색조건 유입경로
    private String            con_consultExileCode;    // 검색조건 상담유형
    private String            con_dealId;              // 검색조건 딜ID
    private String            con_dealNm;              // 검색조건 딜이름
    private String            con_userNm;              // 검색조건 상담원명
    private String            con_customerId;          // 검색조건 고객ID
    private String            con_customerNm;          // 검색조건 고객성명
    private String            con_orderNo;             // 검색조건 주문번호
    private String            con_vocSdYn;             // 검색조건 대상선정
    private String            con_vocYn;               // 검색조건 대상선정
    private String            con_sdYn;                // 검색조건 대상선정
    private String            con_partnerId;           // 검색조건 파트너ID
    private String            con_partnerNm;           // 검색조건 파트너명
    private String            con_userDeptCode;        // 검색조건 사용자부서
    private String            con_inOutGB;             // 콜구분
    private String            con_categoryLCode;       // 카테고리대구분
    private String            con_categorySCode;       // 카테고리소구분
    private String            con_customerGradeCode;   // 회원등급
    private String            con_extOrgReceiveYn;     // 대외기관접수여부
    private String            con_consultExileDeptCode; // 상담유형부서

    // 페이징
    private String            totalCount       = "";
    private String            curPage          = "1";
    private String            rowCount         = "10";
    private String            page_limit_val1;
    private String            page_limit_val2;

    // 관리여부
    private String            ad_adConYn;              // 관리자여부
    private String            ad_sessionUserId;        // 관리자가 아닌경우필수 조회조건(현재사용자)
    private String            ad_sessionUserNm;        // 관리자가 아닌경우필수 조회조건(현재사용자)

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

    public String getCallDirectionCode() {
        return callDirectionCode;
    }

    public void setCallDirectionCode(String callDirectionCode) {
        this.callDirectionCode = callDirectionCode;
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

    public String getCallResultCode() {
        return callResultCode;
    }

    public void setCallResultCode(String callResultCode) {
        this.callResultCode = callResultCode;
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

    public String getRecordkey() {
        return recordkey;
    }

    public void setRecordkey(String recordkey) {
        this.recordkey = recordkey;
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

    public String getMarketcallSno() {
        return marketcallSno;
    }

    public void setMarketcallSno(String marketcallSno) {
        this.marketcallSno = marketcallSno;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getCallResultCodeNm() {
        return callResultCodeNm;
    }

    public void setCallResultCodeNm(String callResultCodeNm) {
        this.callResultCodeNm = callResultCodeNm;
    }

    public String getConsultExileCodeNm() {
        return consultExileCodeNm;
    }

    public void setConsultExileCodeNm(String consultExileCodeNm) {
        this.consultExileCodeNm = consultExileCodeNm;
    }

    public String getInteractionTime() {
        return interactionTime;
    }

    public void setInteractionTime(String interactionTime) {
        this.interactionTime = interactionTime;
    }

    public String getExtOrgReceiveYn() {
        return extOrgReceiveYn;
    }

    public void setExtOrgReceiveYn(String extOrgReceiveYn) {
        this.extOrgReceiveYn = extOrgReceiveYn;
    }

    public String getCon_interactionStartTime() {
        return con_interactionStartTime;
    }

    public void setCon_interactionStartTime(String con_interactionStartTime) {
        this.con_interactionStartTime = con_interactionStartTime;
    }

    public String getCon_interactionEndTime() {
        return con_interactionEndTime;
    }

    public void setCon_interactionEndTime(String con_interactionEndTime) {
        this.con_interactionEndTime = con_interactionEndTime;
    }

    public String getCon_callResultCode() {
        return con_callResultCode;
    }

    public void setCon_callResultCode(String con_callResultCode) {
        this.con_callResultCode = con_callResultCode;
    }

    public String getCon_counselTelno() {
        return con_counselTelno;
    }

    public void setCon_counselTelno(String con_counselTelno) {
        this.con_counselTelno = con_counselTelno;
    }

    public String getCon_inflowChannelCode() {
        return con_inflowChannelCode;
    }

    public void setCon_inflowChannelCode(String con_inflowChannelCode) {
        this.con_inflowChannelCode = con_inflowChannelCode;
    }

    public String getCon_consultExileCode() {
        return con_consultExileCode;
    }

    public void setCon_consultExileCode(String con_consultExileCode) {
        this.con_consultExileCode = con_consultExileCode;
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

    public String getCon_userNm() {
        return con_userNm;
    }

    public void setCon_userNm(String con_userNm) {
        this.con_userNm = con_userNm;
    }

    public String getCon_customerId() {
        return con_customerId;
    }

    public void setCon_customerId(String con_customerId) {
        this.con_customerId = con_customerId;
    }

    public String getCon_customerNm() {
        return con_customerNm;
    }

    public void setCon_customerNm(String con_customerNm) {
        this.con_customerNm = con_customerNm;
    }

    public String getCon_orderNo() {
        return con_orderNo;
    }

    public void setCon_orderNo(String con_orderNo) {
        this.con_orderNo = con_orderNo;
    }

    public String getCon_vocSdYn() {
        return con_vocSdYn;
    }

    public void setCon_vocSdYn(String con_vocSdYn) {
        this.con_vocSdYn = con_vocSdYn;
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

    public String getIvrInflowRoute() {
        return ivrInflowRoute;
    }

    public void setIvrInflowRoute(String ivrInflowRoute) {
        this.ivrInflowRoute = ivrInflowRoute;
    }

    public String getSmsSendDate() {
        return smsSendDate;
    }

    public void setSmsSendDate(String smsSendDate) {
        this.smsSendDate = smsSendDate;
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

    public String getFrstRegTsMask() {
        return frstRegTsMask;
    }

    public void setFrstRegTsMask(String frstRegTsMask) {
        this.frstRegTsMask = frstRegTsMask;
    }

    public String getCon_vocYn() {
        return con_vocYn;
    }

    public void setCon_vocYn(String con_vocYn) {
        this.con_vocYn = con_vocYn;
    }

    public String getCon_sdYn() {
        return con_sdYn;
    }

    public void setCon_sdYn(String con_sdYn) {
        this.con_sdYn = con_sdYn;
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

    public String getCon_userDeptCode() {
        return con_userDeptCode;
    }

    public void setCon_userDeptCode(String con_userDeptCode) {
        this.con_userDeptCode = con_userDeptCode;
    }

    public String getCon_inOutGB() {
        return con_inOutGB;
    }

    public void setCon_inOutGB(String con_inOutGB) {
        this.con_inOutGB = con_inOutGB;
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

    public String getCon_customerGradeCode() {
        return con_customerGradeCode;
    }

    public void setCon_customerGradeCode(String con_customerGradeCode) {
        this.con_customerGradeCode = con_customerGradeCode;
    }

    public String getCon_extOrgReceiveYn() {
        return con_extOrgReceiveYn;
    }

    public void setCon_extOrgReceiveYn(String con_extOrgReceiveYn) {
        this.con_extOrgReceiveYn = con_extOrgReceiveYn;
    }

    public String getCon_consultExileDeptCode() {
        return con_consultExileDeptCode;
    }

    public void setCon_consultExileDeptCode(String con_consultExileDeptCode) {
        this.con_consultExileDeptCode = con_consultExileDeptCode;
    }

    public String getCallDirectionCodeNm() {
        return callDirectionCodeNm;
    }

    public void setCallDirectionCodeNm(String callDirectionCodeNm) {
        this.callDirectionCodeNm = callDirectionCodeNm;
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

    public String getTransferYn() {
        return transferYn;
    }

    public void setTransferYn(String transferYn) {
        this.transferYn = transferYn;
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

    public String getTendCode1Nm() {
        return tendCode1Nm;
    }

    public void setTendCode1Nm(String tendCode1Nm) {
        this.tendCode1Nm = tendCode1Nm;
    }

    public String getTendCode2Nm() {
        return tendCode2Nm;
    }

    public void setTendCode2Nm(String tendCode2Nm) {
        this.tendCode2Nm = tendCode2Nm;
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

    public String getCategorySNm() {
        return categorySNm;
    }

    public void setCategorySNm(String categorySNm) {
        this.categorySNm = categorySNm;
    }

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getExtOrgReceiveSno() {
        return extOrgReceiveSno;
    }

    public void setExtOrgReceiveSno(String extOrgReceiveSno) {
        this.extOrgReceiveSno = extOrgReceiveSno;
    }

    public String getQnaRegisterDateMask() {
        return qnaRegisterDateMask;
    }

    public void setQnaRegisterDateMask(String qnaRegisterDateMask) {
        this.qnaRegisterDateMask = qnaRegisterDateMask;
    }

    public String getQnaAnswerDateMask() {
        return qnaAnswerDateMask;
    }

    public void setQnaAnswerDateMask(String qnaAnswerDateMask) {
        this.qnaAnswerDateMask = qnaAnswerDateMask;
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
