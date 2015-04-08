package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;

public class AddInfoVO extends AbstractVO  {

	/**
	 * 상단 추가정보
	 */
	private static final long serialVersionUID = 1L;
	
	// 업무정보
	private int inboundCallCnt;			// 인바운드 통화건수
	private int outboundCallCnt;		// 아웃바운드 통화건수
	private int boardCnt;				// 게시판 건수
	private int transferCallCnt;		// 이관수신 건수
	private int transferCallComplitCnt;	// 이관처리 건수
	private int reserveCnt;				// 예약건수
	private int outcallCallbackCnt;		// 아웃콜(콜백) 건수   
	
	// 조회값
	private String UserId;				// 상담원ID

	// 수정시 사용(업무구분)
	private String workYn;				// 근무여부
	
	public int getInboundCallCnt() {
		return inboundCallCnt;
	}

	public void setInboundCallCnt(int inboundCallCnt) {
		this.inboundCallCnt = inboundCallCnt;
	}

	public int getOutboundCallCnt() {
		return outboundCallCnt;
	}

	public void setOutboundCallCnt(int outboundCallCnt) {
		this.outboundCallCnt = outboundCallCnt;
	}

	public int getBoardCnt() {
		return boardCnt;
	}

	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}

	public int getTransferCallCnt() {
		return transferCallCnt;
	}

	public void setTransferCallCnt(int transferCallCnt) {
		this.transferCallCnt = transferCallCnt;
	}

	public int getTransferCallComplitCnt() {
		return transferCallComplitCnt;
	}

	public void setTransferCallComplitCnt(int transferCallComplitCnt) {
		this.transferCallComplitCnt = transferCallComplitCnt;
	}

	public int getReserveCnt() {
		return reserveCnt;
	}

	public void setReserveCnt(int reserveCnt) {
		this.reserveCnt = reserveCnt;
	}

	public int getOutcallCallbackCnt() {
		return outcallCallbackCnt;
	}

	public void setOutcallCallbackCnt(int outcallCallbackCnt) {
		this.outcallCallbackCnt = outcallCallbackCnt;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getWorkYn() {
		return workYn;
	}

	public void setWorkYn(String workYn) {
		this.workYn = workYn;
	}
	
}