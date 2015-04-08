package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADUserSearchVO extends AbstractVO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 데이타셋
    private String            userId;               // 상담원ID
    private String            userNm;               // 상담원이름
    private String            employeeNumber;       // 사번
    private String            userDeptCode;         // 부서코드
    private String            userDeptName;         // 부서명
    private String            userGroupCode;        // 그룹코드
    private String            userGroupName;        // 그룹명
    private String            usingYn;              // 사용유무
    private String            agentId;              // 에이전트ID
    private String            innerNum;             // 내선번호
    private String            managerYn;            // 관리자여부
    private String            workYn;               // 휴무유무
    private String            companyGbn;           // 회사구분
    private String            companyGbnName;       // 회사구분명
    private String            chargeBusinessCode;
    private String            chargeBusinessName;
    private String            frstRegTs;
    private String            frstRegrId;
    private String            lastModTs;
    private String            lastCortId;
    private String            partnerChargeYn;		// 파트너담당여부

    // 조건
    private String            con_companyGbnCode;
    private String            con_userDeptCode;
    private String            con_userId;
    private String            searchGubun;
    private String            searchValue;
    private String            callbackFunc;
    private String            autoSetDealId;
    private String            autoSetUserDeptCode;

    public String getCon_userId() {
        return con_userId;
    }

    public void setCon_userId(String con_userId) {
        this.con_userId = con_userId;
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getUserDeptCode() {
        return userDeptCode;
    }

    public void setUserDeptCode(String userDeptCode) {
        this.userDeptCode = userDeptCode;
    }

    public String getUserGroupCode() {
        return userGroupCode;
    }

    public void setUserGroupCode(String userGroupCode) {
        this.userGroupCode = userGroupCode;
    }

    public String getUsingYn() {
        return usingYn;
    }

    public void setUsingYn(String usingYn) {
        this.usingYn = usingYn;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getInnerNum() {
        return innerNum;
    }

    public void setInnerNum(String innerNum) {
        this.innerNum = innerNum;
    }

    public String getManagerYn() {
        return managerYn;
    }

    public void setManagerYn(String managerYn) {
        this.managerYn = managerYn;
    }

    public String getWorkYn() {
        return workYn;
    }

    public void setWorkYn(String workYn) {
        this.workYn = workYn;
    }

    public String getCompanyGbn() {
        return companyGbn;
    }

    public void setCompanyGbn(String companyGbn) {
        this.companyGbn = companyGbn;
    }

    public String getChargeBusinessCode() {
        return chargeBusinessCode;
    }

    public void setChargeBusinessCode(String chargeBusinessCode) {
        this.chargeBusinessCode = chargeBusinessCode;
    }

    public String getChargeBusinessName() {
        return chargeBusinessName;
    }

    public void setChargeBusinessName(String chargeBusinessName) {
        this.chargeBusinessName = chargeBusinessName;
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

    public String getCon_companyGbnCode() {
        return con_companyGbnCode;
    }

    public void setCon_companyGbnCode(String con_companyGbnCode) {
        this.con_companyGbnCode = con_companyGbnCode;
    }

    public String getCon_userDeptCode() {
        return con_userDeptCode;
    }

    public void setCon_userDeptCode(String con_userDeptCode) {
        this.con_userDeptCode = con_userDeptCode;
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

    public String getUserDeptName() {
        return userDeptName;
    }

    public void setUserDeptName(String userDeptName) {
        this.userDeptName = userDeptName;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getCompanyGbnName() {
        return companyGbnName;
    }

    public void setCompanyGbnName(String companyGbnName) {
        this.companyGbnName = companyGbnName;
    }

    public String getCallbackFunc() {
        return callbackFunc;
    }

    public void setCallbackFunc(String callbackFunc) {
        this.callbackFunc = callbackFunc;
    }

    public String getAutoSetDealId() {
        return autoSetDealId;
    }

    public void setAutoSetDealId(String autoSetDealId) {
        this.autoSetDealId = autoSetDealId;
    }

    public String getAutoSetUserDeptCode() {
        return autoSetUserDeptCode;
    }

    public void setAutoSetUserDeptCode(String autoSetUserDeptCode) {
        this.autoSetUserDeptCode = autoSetUserDeptCode;
    }

	public String getPartnerChargeYn() {
		return partnerChargeYn;
	}

	public void setPartnerChargeYn(String partnerChargeYn) {
		this.partnerChargeYn = partnerChargeYn;
	}
}
