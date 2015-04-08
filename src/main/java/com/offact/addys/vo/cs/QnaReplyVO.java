package com.offact.addys.vo.cs;

import com.offact.addys.vo.AbstractVO;


public class QnaReplyVO extends AbstractVO  {

	/**
	 * Q&A Reply vo
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer replyNo; // 
	private String qnaNo; // 
	private String answererName; // 
	private String content; // 
	private String htmlYN; // 
	private String registerDate; // 
	private String updateDate; // 
	private String registerIp; // 
	private int id; // 
	
	private String customerNm; // 
	private String customerEmail; // 
	
	public Integer getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(Integer replyNo) {
		this.replyNo = replyNo;
	}
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getAnswererName() {
		return answererName;
	}
	public void setAnswererName(String answererName) {
		this.answererName = answererName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHtmlYN() {
		return htmlYN;
	}
	public void setHtmlYN(String htmlYN) {
		this.htmlYN = htmlYN;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}
