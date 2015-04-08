package com.offact.addys.vo;

public class ADTendListSearchVO extends AbstractVO  {

	
	private static final long serialVersionUID = 1L;
	
	
	
	private String customerId;
	private String customerNm;
	private String tend1Yn;
	private String tend2Yn;
	private String tend3Yn;
	private String tend4Yn;
	private String tend5Yn;
	private String tend6Yn;
	private String tend7Yn;
	private String tend8Yn;
	private String tend9Yn;
	private String tend10Yn;
	private String tend11Yn;
	private String tend12Yn;
	private String tendContents;
	private String name;
	private String gender;
	private String mId;
	private String mHpEnc;
	private String mMail1;
	private String mMail2;
	private String mRegdate;
	private String grade;

	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;
	
	private String searchGubun;
	private String searchValue;
	
	//	/** for paging */
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";
	private Integer limitStart;
	private Integer limitCount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmHpEnc() {
		return mHpEnc;
	}
	public void setmHpEnc(String mHpEnc) {
		this.mHpEnc = mHpEnc;
	}
	public String getmMail1() {
		return mMail1;
	}
	public void setmMail1(String mMail1) {
		this.mMail1 = mMail1;
	}
	public String getmMail2() {
		return mMail2;
	}
	public void setmMail2(String mMail2) {
		this.mMail2 = mMail2;
	}
	public String getmRegdate() {
		return mRegdate;
	}
	public void setmRegdate(String mRegdate) {
		this.mRegdate = mRegdate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
	public String getTend1Yn() {
		return tend1Yn;
	}
	public void setTend1Yn(String tend1Yn) {
		this.tend1Yn = tend1Yn;
	}
	public String getTend2Yn() {
		return tend2Yn;
	}
	public void setTend2Yn(String tend2Yn) {
		this.tend2Yn = tend2Yn;
	}
	public String getTend3Yn() {
		return tend3Yn;
	}
	public void setTend3Yn(String tend3Yn) {
		this.tend3Yn = tend3Yn;
	}
	public String getTend4Yn() {
		return tend4Yn;
	}
	public void setTend4Yn(String tend4Yn) {
		this.tend4Yn = tend4Yn;
	}
	public String getTend5Yn() {
		return tend5Yn;
	}
	public void setTend5Yn(String tend5Yn) {
		this.tend5Yn = tend5Yn;
	}
	public String getTend6Yn() {
		return tend6Yn;
	}
	public void setTend6Yn(String tend6Yn) {
		this.tend6Yn = tend6Yn;
	}
	public String getTend7Yn() {
		return tend7Yn;
	}
	public void setTend7Yn(String tend7Yn) {
		this.tend7Yn = tend7Yn;
	}
	public String getTend8Yn() {
		return tend8Yn;
	}
	public void setTend8Yn(String tend8Yn) {
		this.tend8Yn = tend8Yn;
	}
	public String getTend9Yn() {
		return tend9Yn;
	}
	public void setTend9Yn(String tend9Yn) {
		this.tend9Yn = tend9Yn;
	}
	public String getTend10Yn() {
		return tend10Yn;
	}
	public void setTend10Yn(String tend10Yn) {
		this.tend10Yn = tend10Yn;
	}
	public String getTend11Yn() {
		return tend11Yn;
	}
	public void setTend11Yn(String tend11Yn) {
		this.tend11Yn = tend11Yn;
	}
	public String getTend12Yn() {
		return tend12Yn;
	}
	public void setTend12Yn(String tend12Yn) {
		this.tend12Yn = tend12Yn;
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
	public Integer getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}
	public Integer getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	
	
	
}