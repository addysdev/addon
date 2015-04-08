package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADUserSkillInfoVO extends AbstractVO {

    /**
     * 
     */

    // 데이타셋
    private String userId;
    private String userNm;
    private String skillCode;
    private String skillNm;
    private String skillGroupCode;
    private String skillGroupNm;

    // 조회조건
    private String con_userId;
    private String con_userNm;

    // /** for paging */
    private String totalCount;
    private String curPage = "1";
    private String rowCount = "10";

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

    public String getSkillCode() {
        return skillCode;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }

    public String getSkillNm() {
        return skillNm;
    }

    public void setSkillNm(String skillNm) {
        this.skillNm = skillNm;
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

    public String getSkillGroupCode() {
        return skillGroupCode;
    }

    public void setSkillGroupCode(String skillGroupCode) {
        this.skillGroupCode = skillGroupCode;
    }

    public String getSkillGroupNm() {
        return skillGroupNm;
    }

    public void setSkillGroupNm(String skillGroupNm) {
        this.skillGroupNm = skillGroupNm;
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

}
