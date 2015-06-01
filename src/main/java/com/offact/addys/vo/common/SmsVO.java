package com.offact.addys.vo.common;

import java.io.Serializable;
import java.io.File;
import java.util.List;

import com.offact.addys.vo.AbstractVO;

public class SmsVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	 String idx;
	 String smsTo;
	 String smsFrom;
	 String smsMsg;
	 String smsDateTime;
	 String smsUserId;
	 String resultCode;
	 String resultMessage;
	 String resultLastPoint;
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getSmsTo() {
		return smsTo;
	}
	public void setSmsTo(String smsTo) {
		this.smsTo = smsTo;
	}
	public String getSmsFrom() {
		return smsFrom;
	}
	public void setSmsFrom(String smsFrom) {
		this.smsFrom = smsFrom;
	}
	public String getSmsMsg() {
		return smsMsg;
	}
	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}
	public String getSmsDateTime() {
		return smsDateTime;
	}
	public void setSmsDateTime(String smsDateTime) {
		this.smsDateTime = smsDateTime;
	}
	public String getSmsUserId() {
		return smsUserId;
	}
	public void setSmsUserId(String smsUserId) {
		this.smsUserId = smsUserId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getResultLastPoint() {
		return resultLastPoint;
	}
	public void setResultLastPoint(String resultLastPoint) {
		this.resultLastPoint = resultLastPoint;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
