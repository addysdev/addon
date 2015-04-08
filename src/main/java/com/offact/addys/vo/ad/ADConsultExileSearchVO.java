package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADConsultExileSearchVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    // 테이블컬럼
    private String            userDeptCode;         // 사용자부서코드
    private String            consultExileCode;     // 상담유형코드
    private String            consultExileNm;       // 상담유형명
    private String            upCode;               // 상위코드
    private String            codePath;             // 상담유형경로명
    private String            lowestLevelYn;        // 최하위레벨여부
    private String            consultExile1lCode;   // 상담유형1레벨코드
    private String            consultExile2lCode;   // 상담유형2레벨코드
    private String            consultExile3lCode;   // 상담유형3레벨코드
    private String            consultExile4lCode;   // 상담유형4레벨코드
    private String            consultExile5lCode;   // 상담유형5레벨코드
    private String            usingYn;              // 사용여부
    private String            description;          // 비고
    private String            defaultYn;            // 디폴트표시여부
    private String            frstRegTs;            // 최초등록일시
    private String            frstRegrId;           // 최초등록자ID
    private String            lastModTs;            // 최종수정일시
    private String            lastCortId;           // 최종수정자ID

    // 추가조회컬럼
    private String            consultExileLv;       // 상담유형코드레벨

    // 검색조건용
    private String            con_callBackFunction; // 콜백펑션
    private String            con_consultExileCode; // 상담유형코드
    private String            con_userDeptCode;     // 부서조건
    private String            con_adAdminYn;        // 관리자여부
    private String            con_consultExileNm;   // 상담유형명조건

    public String getUserDeptCode() {
        return userDeptCode;
    }

    public void setUserDeptCode(String userDeptCode) {
        this.userDeptCode = userDeptCode;
    }

    public String getConsultExileCode() {
        return consultExileCode;
    }

    public void setConsultExileCode(String consultExileCode) {
        this.consultExileCode = consultExileCode;
    }

    public String getConsultExileNm() {
        return consultExileNm;
    }

    public void setConsultExileNm(String consultExileNm) {
        this.consultExileNm = consultExileNm;
    }

    public String getUpCode() {
        return upCode;
    }

    public void setUpCode(String upCode) {
        this.upCode = upCode;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public String getLowestLevelYn() {
        return lowestLevelYn;
    }

    public void setLowestLevelYn(String lowestLevelYn) {
        this.lowestLevelYn = lowestLevelYn;
    }

    public String getConsultExile1lCode() {
        return consultExile1lCode;
    }

    public void setConsultExile1lCode(String consultExile1lCode) {
        this.consultExile1lCode = consultExile1lCode;
    }

    public String getConsultExile2lCode() {
        return consultExile2lCode;
    }

    public void setConsultExile2lCode(String consultExile2lCode) {
        this.consultExile2lCode = consultExile2lCode;
    }

    public String getConsultExile3lCode() {
        return consultExile3lCode;
    }

    public void setConsultExile3lCode(String consultExile3lCode) {
        this.consultExile3lCode = consultExile3lCode;
    }

    public String getConsultExile4lCode() {
        return consultExile4lCode;
    }

    public void setConsultExile4lCode(String consultExile4lCode) {
        this.consultExile4lCode = consultExile4lCode;
    }

    public String getConsultExile5lCode() {
        return consultExile5lCode;
    }

    public void setConsultExile5lCode(String consultExile5lCode) {
        this.consultExile5lCode = consultExile5lCode;
    }

    public String getUsingYn() {
        return usingYn;
    }

    public void setUsingYn(String usingYn) {
        this.usingYn = usingYn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultYn() {
        return defaultYn;
    }

    public void setDefaultYn(String defaultYn) {
        this.defaultYn = defaultYn;
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

    public String getCon_callBackFunction() {
        return con_callBackFunction;
    }

    public void setCon_callBackFunction(String con_callBackFunction) {
        this.con_callBackFunction = con_callBackFunction;
    }

    public String getCon_userDeptCode() {
        return con_userDeptCode;
    }

    public void setCon_userDeptCode(String con_userDeptCode) {
        this.con_userDeptCode = con_userDeptCode;
    }

    public String getCon_adAdminYn() {
        return con_adAdminYn;
    }

    public void setCon_adAdminYn(String con_adAdminYn) {
        this.con_adAdminYn = con_adAdminYn;
    }

    public String getConsultExileLv() {
        return consultExileLv;
    }

    public void setConsultExileLv(String consultExileLv) {
        this.consultExileLv = consultExileLv;
    }

    public String getCon_consultExileCode() {
        return con_consultExileCode;
    }

    public void setCon_consultExileCode(String con_consultExileCode) {
        this.con_consultExileCode = con_consultExileCode;
    }

    public String getCon_consultExileNm() {
        return con_consultExileNm;
    }

    public void setCon_consultExileNm(String con_consultExileNm) {
        this.con_consultExileNm = con_consultExileNm;
    }

}
