package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;


public class UserSkillVO extends AbstractVO  {

	/**
	 * 스킬목록 조회 vo
	 */
	private static final long serialVersionUID = 1L;

    // 출력값
	private String skillGroupCode; // 스킬그룹코드
	private String skillGroupCodeNm; // 스킬그룹코드
	private String skillGroupCodeCnt; // 하위 스킬 갯수



	private String skillCode; // 스킬코드
	private String skillNm; // 스킬명
	private String skillYn; // 스킬여부
	private String vdnCode; // VDN코드
	private String description; // 비고
	private String callSkillYn; // 콜스킬여부
	// 사용자 조회용
	private String userId; // 사용자ID
	private String userNm; // 사용자명
	private String innerNum; // 내선번호
	private String userDeptCode; // 사용자부서코드
	private String userDeptCodeNm; // 사용자부서코드명
	private String loginState; // 로그인여부
	private String agentId; // 에이전트ID

	// 입력값
	private String inSkillNm;	// 조회조건(스킬명)
	private String deptCode;	// 조회조건(부서코드)
	private String pageType;	// 조회조건(페이지유형)

	// 사용자 조회용 입력값
	private String inSearchType;// 조회구분(ID, NM)
	private String inSearchValue;// 조회조건
	private String inCompanyGbn;// 업체구분
	private String inDeptCode;	// 사용자부서코드


	private String frstRegrId;
	private String lastCortId;


	/** for paging */
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";

	public String getSkillGroupCode() {
		return skillGroupCode;
	}
	public void setSkillGroupCode(String skillGroupCode) {
		this.skillGroupCode = skillGroupCode;
	}
	public String getSkillGroupCodeNm() {
		return skillGroupCodeNm;
	}
	public void setSkillGroupCodeNm(String skillGroupCodeNm) {
		this.skillGroupCodeNm = skillGroupCodeNm;
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
	public String getVdnCode() {
		return vdnCode;
	}
	public void setVdnCode(String vdnCode) {
		this.vdnCode = vdnCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCallSkillYn() {
		return callSkillYn;
	}
	public void setCallSkillYn(String callSkillYn) {
		this.callSkillYn = callSkillYn;
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
	public String getInnerNum() {
		return innerNum;
	}
	public void setInnerNum(String innerNum) {
		this.innerNum = innerNum;
	}
	public String getUserDeptCode() {
		return userDeptCode;
	}
	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}
	public String getUserDeptCodeNm() {
		return userDeptCodeNm;
	}
	public void setUserDeptCodeNm(String userDeptCodeNm) {
		this.userDeptCodeNm = userDeptCodeNm;
	}
	public String getLoginState() {
		return loginState;
	}
	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getInSkillNm() {
		return inSkillNm;
	}
	public void setInSkillNm(String inSkillNm) {
		this.inSkillNm = inSkillNm;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getInSearchType() {
		return inSearchType;
	}
	public void setInSearchType(String inSearchType) {
		this.inSearchType = inSearchType;
	}
	public String getInSearchValue() {
		return inSearchValue;
	}
	public void setInSearchValue(String inSearchValue) {
		this.inSearchValue = inSearchValue;
	}
	public String getInCompanyGbn() {
		return inCompanyGbn;
	}
	public void setInCompanyGbn(String inCompanyGbn) {
		this.inCompanyGbn = inCompanyGbn;
	}
	public String getInDeptCode() {
		return inDeptCode;
	}
	public void setInDeptCode(String inDeptCode) {
		this.inDeptCode = inDeptCode;
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
	public String getSkillGroupCodeCnt() {
		return skillGroupCodeCnt;
	}
	public void setSkillGroupCodeCnt(String skillGroupCodeCnt) {
		this.skillGroupCodeCnt = skillGroupCodeCnt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSkillYn() {
		return skillYn;
	}
	public void setSkillYn(String skillYn) {
		this.skillYn = skillYn;
	}
	public String getFrstRegrId() {
		return frstRegrId;
	}
	public void setFrstRegrId(String frstRegrId) {
		this.frstRegrId = frstRegrId;
	}
	public String getLastCortId() {
		return lastCortId;
	}
	public void setLastCortId(String lastCortId) {
		this.lastCortId = lastCortId;
	}




}
