package com.offact.addys.vo.common;

import com.offact.addys.vo.AbstractVO;

/**
 * @author 4530
 *
 */
public class CommentVO extends AbstractVO {
	
	private String idx;
	private String orderCode;
	private String productCode;
	private String productName;
	private String commentCategory;
	private String commentType;
	private String commentTypeView;
	private String comment;
	private String commentDateTime;
	private String commentUserId;
	private String commentUserName;
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentDateTime() {
		return commentDateTime;
	}
	public void setCommentDateTime(String commentDateTime) {
		this.commentDateTime = commentDateTime;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	public String getCommentCategory() {
		return commentCategory;
	}
	public void setCommentCategory(String commentCategory) {
		this.commentCategory = commentCategory;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getCommentTypeView() {
		return commentTypeView;
	}
	public void setCommentTypeView(String commentTypeView) {
		this.commentTypeView = commentTypeView;
	}
	
}
