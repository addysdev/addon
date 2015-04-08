package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADDealByUserMngVO extends AbstractVO {

	private String dealId       ;
	private String userId       ;
	private String frstRegTs    ;
	private String frstRegrId   ;
	private String lastModTs    ;
	private String lastCortId   ;
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
