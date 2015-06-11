package com.offact.addys.vo.common;

import java.io.Serializable;

import com.offact.addys.vo.AbstractVO;

public class WorkVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String groupId;
	
	private String idx;
	private String workDateTime;
	private String workIp;
	private String workUserId;
	private String workUserName;
	private String workGroupId;
	private String workGroupName;
	private String workCategory;
	private String workCategoryName;
	private String workCode;
	private String workCodeName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getWorkDateTime() {
		return workDateTime;
	}
	public void setWorkDateTime(String workDateTime) {
		this.workDateTime = workDateTime;
	}
	public String getWorkIp() {
		return workIp;
	}
	public void setWorkIp(String workIp) {
		this.workIp = workIp;
	}
	public String getWorkUserId() {
		return workUserId;
	}
	public void setWorkUserId(String workUserId) {
		this.workUserId = workUserId;
	}
	public String getWorkUserName() {
		return workUserName;
	}
	public void setWorkUserName(String workUserName) {
		this.workUserName = workUserName;
	}
	public String getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}
	public String getWorkGroupName() {
		return workGroupName;
	}
	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}
	public String getWorkCategory() {
		return workCategory;
	}
	public void setWorkCategory(String workCategory) {
		this.workCategory = workCategory;
	}
	public String getWorkCategoryName() {
		return workCategoryName;
	}
	public void setWorkCategoryName(String workCategoryName) {
		this.workCategoryName = workCategoryName;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getWorkCodeName() {
		return workCodeName;
	}
	public void setWorkCodeName(String workCodeName) {
		this.workCodeName = workCodeName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
