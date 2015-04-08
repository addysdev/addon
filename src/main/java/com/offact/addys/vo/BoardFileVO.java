package com.offact.addys.vo;

/**
 * 게시판 첨부파일 정보
 * @author 4530
 *
 */
public class BoardFileVO extends AbstractVO  {

	private static final long serialVersionUID = 1L;

	private String fileId;
	private String postId;
	private String boardCategoryId;
	private String userdeptCode;
	private String fileSize;
//	private String fileType;
	private String orgFileName;
	private String fileName;
	private String deleteYn;
	private String frstRegTs;
	private String frstRegrId;
	private String lastModTs;
	private String lastCortId;

	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
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
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
//	public String getFileType() {
//		return fileType;
//	}
//	public void setFileType(String fileType) {
//		this.fileType = fileType;
//	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
