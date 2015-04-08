package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;

public class ClaimVO extends AbstractVO {

	/**
	 * 공통 클레임 vo
	 */
	private static final long serialVersionUID = 1L;

	private String customerId; // 고객ID
	private String customerNm; // 고객명
	private String tendCode1; // 성향코드1
	private String tendCode2; // 성향코드2
	private String tendCode3; // 성향코드3
	private String tendCode4; // 성향코드4
	private String tendCode5; // 성향코드5
	private String tendCode6; // 성향코드6
	private String tendCode7; // 성향코드7
	private String tendCode8; // 성향코드8
	private String tendCode9; // 성향코드9
	private String tendCode10; // 성향코드10
	private String tendCode11; // 성향코드11
	private String tendCode12; // 성향코드12
	private String tendContents; // 성향내용
	private String frstRegTs; // 최초등록일시
	private String frstRegrId; // 최초등록자ID
	private String lastModTs; // 최종수정일시
	private String lastCortId; // 최종수정자ID

	// ** 박승한 추가 시작 **//
	private String claimTs; // 대외기관접수반환일련번호

	private String curPage; // 페이징 처리를 위한 현재 페이지

	private String rowCount; // 페이지 당 전체 Row 수

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

	public String getClaimTs() {
		return claimTs;
	}

	public void setClaimTs(String claimTs) {
		this.claimTs = claimTs;
	}

	// ** 박승한 추가 끝 **//

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

	public String getTendCode1() {
		return tendCode1;
	}

	public void setTendCode1(String tendCode1) {
		this.tendCode1 = tendCode1;
	}

	public String getTendCode2() {
		return tendCode2;
	}

	public void setTendCode2(String tendCode2) {
		this.tendCode2 = tendCode2;
	}

	public String getTendCode3() {
		return tendCode3;
	}

	public void setTendCode3(String tendCode3) {
		this.tendCode3 = tendCode3;
	}

	public String getTendCode4() {
		return tendCode4;
	}

	public void setTendCode4(String tendCode4) {
		this.tendCode4 = tendCode4;
	}

	public String getTendCode5() {
		return tendCode5;
	}

	public void setTendCode5(String tendCode5) {
		this.tendCode5 = tendCode5;
	}

	public String getTendCode6() {
		return tendCode6;
	}

	public void setTendCode6(String tendCode6) {
		this.tendCode6 = tendCode6;
	}

	public String getTendCode7() {
		return tendCode7;
	}

	public void setTendCode7(String tendCode7) {
		this.tendCode7 = tendCode7;
	}

	public String getTendCode8() {
		return tendCode8;
	}

	public void setTendCode8(String tendCode8) {
		this.tendCode8 = tendCode8;
	}

	public String getTendCode9() {
		return tendCode9;
	}

	public void setTendCode9(String tendCode9) {
		this.tendCode9 = tendCode9;
	}

	public String getTendCode10() {
		return tendCode10;
	}

	public void setTendCode10(String tendCode10) {
		this.tendCode10 = tendCode10;
	}

	public String getTendCode11() {
		return tendCode11;
	}

	public void setTendCode11(String tendCode11) {
		this.tendCode11 = tendCode11;
	}

	public String getTendCode12() {
		return tendCode12;
	}

	public void setTendCode12(String tendCode12) {
		this.tendCode12 = tendCode12;
	}

	public String getTendContents() {
		return tendContents;
	}

	public void setTendContents(String tendContents) {
		this.tendContents = tendContents;
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
}
