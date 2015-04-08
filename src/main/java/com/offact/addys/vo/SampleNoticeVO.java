package com.offact.addys.vo;

public class SampleNoticeVO extends AbstractVO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String no;
	private String title;
	private String contents;
	private String writer;
	private String regDt;
	private String modDt;

	/** for paging */
	private String totalCount;
	private String curPage = "1";
	private String rowCount = "10";
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
//	public String getCurPage() {
//		return curPage;
//	}
//	public void setCurPage(String curPage) {
//		this.curPage = curPage;
//	}
//	public String getRowCount() {
//		return rowCount;
//	}
//	public void setRowCount(String rowCount) {
//		this.rowCount = rowCount;
//	}
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
	
}
