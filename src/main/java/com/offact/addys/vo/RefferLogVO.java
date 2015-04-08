package com.offact.addys.vo;

/**
 * 게시판 정보
 * @author 4530
 *
 */
public class RefferLogVO extends AbstractVO  {

	private static final long serialVersionUID = 1L;
	
	private String ReferLogSno;
	private String UserId;
	private String RefferDate;
	private String ConnectIP;
	private String RefferUserSrl;
	private String Frst_Reg_Ts;
	private String Frst_regr_Id;
	private String ActionId;
	private String ResultCnt;
	
	
	public String getResultCnt() {
		return ResultCnt;
	}
	public void setResultCnt(String resultCnt) {
		ResultCnt = resultCnt;
	}
	public String getReferLogSno() {
		return ReferLogSno;
	}
	public void setReferLogSno(String referLogSno) {
		ReferLogSno = referLogSno;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getRefferDate() {
		return RefferDate;
	}
	public void setRefferDate(String refferDate) {
		RefferDate = refferDate;
	}
	public String getConnectIP() {
		return ConnectIP;
	}
	public void setConnectIP(String connectIP) {
		ConnectIP = connectIP;
	}
	public String getRefferUserSrl() {
		return RefferUserSrl;
	}
	public void setRefferUserSrl(String refferUserSrl) {
		RefferUserSrl = refferUserSrl;
	}
	public String getFrst_Reg_Ts() {
		return Frst_Reg_Ts;
	}
	public void setFrst_Reg_Ts(String frst_Reg_Ts) {
		Frst_Reg_Ts = frst_Reg_Ts;
	}
	public String getFrst_regr_Id() {
		return Frst_regr_Id;
	}
	public void setFrst_regr_Id(String frst_regr_Id) {
		Frst_regr_Id = frst_regr_Id;
	}
	public String getActionId() {
		return ActionId;
	}
	public void setActionId(String actionId) {
		ActionId = actionId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	

}
