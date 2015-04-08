package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADUserListManageVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    private String            userId;
    private String            userNm;
    private String            employeeNumber;
    private String            userDeptCode;
    private String            userDeptCodeNm;
    private String            userGroupCode;
    private String            userGroupCodeNm;
    private String            usingYn;
    private String            loginYn;
    private String            agentId;
    private String            innerNum;
    private String            managerYn;
    private String            workYn;
    private String            companyGbn;
    private String            chargeBusinessCode;
    private String            tplusChargeBusinessCode;
    private String            frstRegTs;
    private String            frstRegrId;
    private String            lastModTs;
    private String            lastCortId;
    private String            partnerChargeYn;

    private String            con_userGroupCode;
    private String            con_companyGbnCode;
    private String            con_userDeptCode;
    private String            con_usingYn;
    private String            searchGubun;
    private String            searchValue;
    private String            con_userId;
    private String            assignCnt;
    private String            revokeUser;
    private String            transferCnt;

    // /** for paging */
//    private String            totalCount;
//    private String            curPage          = "1";
//    private String            rowCount         = "10";
//    private String            page_limit_val1;
//    private String            page_limit_val2;


    private String            totalCount       = "0";
    private String            curPage          = "1";
    private String            rowCount         = "10";
    private String            page_limit_val1;
    private String            page_limit_val2;


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

    public String getCon_userGroupCode() {
        return con_userGroupCode;
    }

    public void setCon_userGroupCode(String con_userGroupCode) {
        this.con_userGroupCode = con_userGroupCode;
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

    public String getCon_usingYn() {
        return con_usingYn;
    }

    public void setCon_usingYn(String con_usingYn) {
        this.con_usingYn = con_usingYn;
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

    public String getCon_userId() {
        return con_userId;
    }

    public void setCon_userId(String con_userId) {
        this.con_userId = con_userId;
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

    public String getUserDeptCodeNm() {
        return userDeptCodeNm;
    }

    public void setUserDeptCodeNm(String userDeptCodeNm) {
        this.userDeptCodeNm = userDeptCodeNm;
    }

    public String getUserGroupCodeNm() {
        return userGroupCodeNm;
    }

    public void setUserGroupCodeNm(String userGroupCodeNm) {
        this.userGroupCodeNm = userGroupCodeNm;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getTplusChargeBusinessCode() {
        return tplusChargeBusinessCode;
    }

    public void setTplusChargeBusinessCode(String tplusChargeBusinessCode) {
        this.tplusChargeBusinessCode = tplusChargeBusinessCode;
    }

    public String getPartnerChargeYn() {
        return partnerChargeYn;
    }

    public void setPartnerChargeYn(String partnerChargeYn) {
        this.partnerChargeYn = partnerChargeYn;
    }

    public String getLoginYn() {
        return loginYn;
    }

    public void setLoginYn(String loginYn) {
        this.loginYn = loginYn;
    }

    public String getAssignCnt() {
        return assignCnt;
    }

    public void setAssignCnt(String assignCnt) {
        this.assignCnt = assignCnt;
    }

    public String getRevokeUser() {
        return revokeUser;
    }

    public void setRevokeUser(String revokeUser) {
        this.revokeUser = revokeUser;
    }

    public String getTransferCnt() {
        return transferCnt;
    }

    public void setTransferCnt(String transferCnt) {
        this.transferCnt = transferCnt;
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

}
