/**
 *
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.DealVO;

/**
 * @author HSH
 */
public interface ADDealByUserService {
    /**
     * 상담원목록조회
     *
     * @return
     * @throws BizException
     */

	public List<DealVO> getDealByuserList(DealVO deal) throws BizException;

	public int getDealByuserCnt(DealVO deal) throws BizException;
	public List<DealVO> getCurrDealByUser(DealVO deal) throws BizException;
	public void saveDealByUser(String arrDealId,String arrDealByUserId,String regiUuserId) throws BizException;
	public DealVO getDealCategoryNm(DealVO deal) throws BizException;


}
