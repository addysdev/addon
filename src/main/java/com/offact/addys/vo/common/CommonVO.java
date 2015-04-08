package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;


public class CommonVO extends AbstractVO  {

	/**
	 * 스킬목록 조회 vo
	 */
	private static final long serialVersionUID = 1L;


	private String fromDate;
	private String toDate;











	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}








}
