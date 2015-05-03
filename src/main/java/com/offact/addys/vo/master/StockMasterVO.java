package com.offact.addys.vo.master;

import com.offact.addys.vo.AbstractVO;
/**
 * @author 4530
 *
 */
public class StockMasterVO extends AbstractVO {
	
	private String productCode;
	private String groupId;
	private String safeStock;
	private String holdStock;

	private String createUserId;
	private String createDateTime;
	private String updateUserId;
	private String updateDateTime;
	private String deletedYn;
	
	private String errMsg;
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getSafeStock() {
		return safeStock;
	}
	public void setSafeStock(String safeStock) {
		this.safeStock = safeStock;
	}
	public String getHoldStock() {
		return holdStock;
	}
	public void setHoldStock(String holdStock) {
		this.holdStock = holdStock;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getDeletedYn() {
		return deletedYn;
	}
	public void setDeletedYn(String deletedYn) {
		this.deletedYn = deletedYn;
	}
	
	
}
