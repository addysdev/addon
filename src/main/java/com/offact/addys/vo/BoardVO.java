package com.offact.addys.vo;

/**
 * 게시판 정보
 * @author 4530
 *
 */
public class BoardVO extends AbstractVO  {
 
	private static final long serialVersionUID = 1L;

	private String postId;
	private String boardCategoryId;
	private String userdeptCode;
	private String catalogCode;
	private String catalogCodeNm;
	private String dealId;
	private String dealNm;
	private String title;
	private String contents;
	private String readCount;
	private String openYn;
	private String frstRegTs;
	private String frstRegrId;
	private String frstRegrNm;
	private String lastModTs;
	private String lastCortId;
	private String noticeYn;
	private String categorySCode;
	private String categorySNm;
	private String categoryLCode;
	private String categoryLNm;
	private String userNm;
	private String replyId;
	private String fileId;
	private String companyGbn;
	private String cataloggroupCode;
	private String name;
	private String imgType;
	private String superusereditYn;
	private String superuserautosaveYn;
	private String companyGbnGroup;
	private String codeId;

	//검색조건
	private String strBoardCategoryId;
	private String strCatalogCode;
	private String strDealId;
	private String strTitle;
	private String strContents;
	private String strOpenYn;
	private String strFrstRegrId;
	private String strFrstRegrNm;
	private String strCategorySCode;
	private String strCategoryLCode;
	private String strFrstRegrInfo;
	private String searchCondition;
	private String con_companyGbnCode;
	private String boardCategoryIdType;
	private String userGroupCode;

	/** for paging */
	private String totalCount;
    private String            curPage          = "1";
    private String            rowCount         = "10";
    private String            page_limit_val1;
    private String            page_limit_val2;

	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getBoardCategoryId() {
		return boardCategoryId;
	}
	public void setBoardCategoryId(String boardCategoryId) {
		this.boardCategoryId = boardCategoryId;
	}
	
	public String getUserdeptCode() {
		return userdeptCode;
	}
	public void setUserdeptCode(String userdeptCode) {
		this.userdeptCode = userdeptCode;
	}
	public String getCatalogCode() {
		return catalogCode;
	}
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	public String getCatalogCodeNm() {
		return catalogCodeNm;
	}
	public void setCatalogCodeNm(String catalogCodeNm) {
		this.catalogCodeNm = catalogCodeNm;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReadCount() {
		return readCount;
	}
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
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
	public String getFrstRegrNm() {
		return frstRegrNm;
	}
	public void setFrstRegrNm(String frstRegrNm) {
		this.frstRegrNm = frstRegrNm;
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
	public String getStrBoardCategoryId() {
		return strBoardCategoryId;
	}
	public void setStrBoardCategoryId(String strBoardCategoryId) {
		this.strBoardCategoryId = strBoardCategoryId;
	}
	public String getStrCatalogCode() {
		return strCatalogCode;
	}
	public void setStrCatalogCode(String strCatalogCode) {
		this.strCatalogCode = strCatalogCode;
	}
	public String getStrDealId() {
		return strDealId;
	}
	public void setStrDealId(String strDealId) {
		this.strDealId = strDealId;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	public String getStrContents() {
		return strContents;
	}
	public void setStrContents(String strContents) {
		this.strContents = strContents;
	}
	public String getStrOpenYn() {
		return strOpenYn;
	}
	public void setStrOpenYn(String strOpenYn) {
		this.strOpenYn = strOpenYn;
	}
	public String getStrFrstRegrId() {
		return strFrstRegrId;
	}
	public void setStrFrstRegrId(String strFrstRegrId) {
		this.strFrstRegrId = strFrstRegrId;
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
	public String getStrFrstRegrNm() {
		return strFrstRegrNm;
	}
	public void setStrFrstRegrNm(String strFrstRegrNm) {
		this.strFrstRegrNm = strFrstRegrNm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCategorySCode() {
		return categorySCode;
	}
	public void setCategorySCode(String categorySCode) {
		this.categorySCode = categorySCode;
	}
	public String getCategorySNm() {
		return categorySNm;
	}
	public void setCategorySNm(String categorySNm) {
		this.categorySNm = categorySNm;
	}
	public String getCategoryLCode() {
		return categoryLCode;
	}
	public void setCategoryLCode(String categoryLCode) {
		this.categoryLCode = categoryLCode;
	}
	public String getCategoryLNm() {
		return categoryLNm;
	}
	public void setCategoryLNm(String categoryLNm) {
		this.categoryLNm = categoryLNm;
	}
	public String getDealNm() {
		return dealNm;
	}
	public void setDealNm(String dealNm) {
		this.dealNm = dealNm;
	}
	public String getStrCategorySCode() {
		return strCategorySCode;
	}
	public void setStrCategorySCode(String strCategorySCode) {
		this.strCategorySCode = strCategorySCode;
	}
	public String getStrcCategoryLCode() {
		return strCategoryLCode;
	}
	public void setStrcCategoryLCode(String strcCategoryLCode) {
		this.strCategoryLCode = strcCategoryLCode;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getStrCategoryLCode() {
		return strCategoryLCode;
	}
	public void setStrCategoryLCode(String strCategoryLCode) {
		this.strCategoryLCode = strCategoryLCode;
	}
	public String getStrFrstRegrInfo() {
		return strFrstRegrInfo;
	}
	public void setStrFrstRegrInfo(String strFrstRegrInfo) {
		this.strFrstRegrInfo = strFrstRegrInfo;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
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
	public String getCompanyGbn() {
		return companyGbn;
	}
	public void setCompanyGbn(String companyGbn) {
		this.companyGbn = companyGbn;
	}
	public String getCataloggroupCode() {
		return cataloggroupCode;
	}
	public void setCataloggroupCode(String cataloggroupCode) {
		this.cataloggroupCode = cataloggroupCode;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCon_companyGbnCode() {
		return con_companyGbnCode;
	}
	public void setCon_companyGbnCode(String con_companyGbnCode) {
		this.con_companyGbnCode = con_companyGbnCode;
	}
	public String getBoardCategoryIdType() {
		return boardCategoryIdType;
	}
	public void setBoardCategoryIdType(String boardCategoryIdType) {
		this.boardCategoryIdType = boardCategoryIdType;
	}
	public String getUserGroupCode() {
		return userGroupCode;
	}
	public void setUserGroupCode(String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public String getNoticeYn() {
		return noticeYn;
	}
	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}
	public String getCompanyGbnGroup() {
		return companyGbnGroup;
	}
	public void setCompanyGbnGroup(String companyGbnGroup) {
		this.companyGbnGroup = companyGbnGroup;
	}
	public String getSuperusereditYn() {
		return superusereditYn;
	}
	public void setSuperusereditYn(String superusereditYn) {
		this.superusereditYn = superusereditYn;
	}
	public String getSuperuserautosaveYn() {
		return superuserautosaveYn;
	}
	public void setSuperuserautosaveYn(String superuserautosaveYn) {
		this.superuserautosaveYn = superuserautosaveYn;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	
}
