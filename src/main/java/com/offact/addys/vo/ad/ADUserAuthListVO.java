package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADUserAuthListVO extends AbstractVO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 데이타셋
    private String            userDeptCode;         // 사용자부서코드
    private String            funcAuthCode;         // 기능권한코드
    private String            userId;               // 사용자ID
    private String            authYn;               // 권한여부
    private String            frstRegTs;            // 최초등록일시
    private String            frstRegrId;           // 최초등록자ID
    private String            lastModTs;            // 최종수정일시
    private String            lastCortId;           // 최종수정자ID

    private String            validFromDt;          // 유효기간 시작일
    private String            validToDt;            // 유효기간 종료일

    private String            funcAuthNm;           // 권한명칭
    private String            menuYn;               // 메뉴여부
    private String            description;          // 비고

    // 조회조건
    private String            con_userId;
    private String            con_userNm;
    private String            con_funcAuthCode;     // 셋팅할 대상조건
    private String            con_userDeptCode;     // 셋팅할 대상조건
    private String            con_userGroupCode;    // 셋팅할 대상조건

    // SESSION USER_ID
    private String            session_userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDeptCode() {
        return userDeptCode;
    }

    public void setUserDeptCode(String userDeptCode) {
        this.userDeptCode = userDeptCode;
    }

    public String getFuncAuthCode() {
        return funcAuthCode;
    }

    public void setFuncAuthCode(String funcAuthCode) {
        this.funcAuthCode = funcAuthCode;
    }

    public String getAuthYn() {
        return authYn;
    }

    public void setAuthYn(String authYn) {
        this.authYn = authYn;
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

    public String getFuncAuthNm() {
        return funcAuthNm;
    }

    public void setFuncAuthNm(String funcAuthNm) {
        this.funcAuthNm = funcAuthNm;
    }

    public String getMenuYn() {
        return menuYn;
    }

    public void setMenuYn(String menuYn) {
        this.menuYn = menuYn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCon_userId() {
        return con_userId;
    }

    public void setCon_userId(String con_userId) {
        this.con_userId = con_userId;
    }

    public String getCon_userNm() {
        return con_userNm;
    }

    public void setCon_userNm(String con_userNm) {
        this.con_userNm = con_userNm;
    }

    public String getValidFromDt() {
        return validFromDt;
    }

    public void setValidFromDt(String validFromDt) {
        this.validFromDt = validFromDt;
    }

    public String getValidToDt() {
        return validToDt;
    }

    public void setValidToDt(String validToDt) {
        this.validToDt = validToDt;
    }

    public String getCon_funcAuthCode() {
        return con_funcAuthCode;
    }

    public void setCon_funcAuthCode(String con_funcAuthCode) {
        this.con_funcAuthCode = con_funcAuthCode;
    }

    public String getCon_userDeptCode() {
        return con_userDeptCode;
    }

    public void setCon_userDeptCode(String con_userDeptCode) {
        this.con_userDeptCode = con_userDeptCode;
    }

    public String getSession_userId() {
        return session_userId;
    }

    public void setSession_userId(String session_userId) {
        this.session_userId = session_userId;
    }

    public String getCon_userGroupCode() {
        return con_userGroupCode;
    }

    public void setCon_userGroupCode(String con_userGroupCode) {
        this.con_userGroupCode = con_userGroupCode;
    }

}
