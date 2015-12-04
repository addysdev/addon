package com.offact.addys.vo.smart;

import com.offact.addys.vo.AbstractVO;

public class AsHistoryVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;

    private String idx;
	private String asNo;
	private String groupId;
	private String groupName;
	private String userId;
	private String userName;
	private String asHistory;
	private String asHistoryDateTime;
	private String asSubState;
	private String asSubStateTrans;
	private String centerAsNo;
	private String centerImage;
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getAsNo() {
		return asNo;
	}
	public void setAsNo(String asNo) {
		this.asNo = asNo;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAsHistory() {
		return asHistory;
	}
	public void setAsHistory(String asHistory) {
		this.asHistory = asHistory;
	}
	public String getAsHistoryDateTime() {
		return asHistoryDateTime;
	}
	public void setAsHistoryDateTime(String asHistoryDateTime) {
		this.asHistoryDateTime = asHistoryDateTime;
	}
	public String getAsSubState() {
		return asSubState;
	}
	public void setAsSubState(String asSubState) {
		this.asSubState = asSubState;
	}
	public String getCenterAsNo() {
		return centerAsNo;
	}
	public void setCenterAsNo(String centerAsNo) {
		this.centerAsNo = centerAsNo;
	}
	public String getCenterImage() {
		return centerImage;
	}
	public void setCenterImage(String centerImage) {
		this.centerImage = centerImage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAsSubStateTrans() {
		return asSubStateTrans;
	}
	public void setAsSubStateTrans(String asSubStateTrans) {
		this.asSubStateTrans = asSubStateTrans;
	}
}
