/**
 *
 */
package com.offact.addys.service.common;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ADCommonFormManageVO;


/**
 * @author 4530
 *
 */
public interface PopupService {

	public int getSmsFormCnt() throws BizException;
	public List<ADCommonFormManageVO> getSmsFormList(ADCommonFormManageVO commonForm) throws BizException;

	public int getTransferFormCnt() throws BizException;
	public List<ADCommonFormManageVO> getTransferFormList(ADCommonFormManageVO commonForm) throws BizException;


	public int getCommonFormCnt(ADCommonFormManageVO commonForm) throws BizException;
	public List<ADCommonFormManageVO> getCommonFormList(ADCommonFormManageVO commonForm) throws BizException;




}
