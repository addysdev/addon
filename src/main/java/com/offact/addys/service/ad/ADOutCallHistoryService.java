/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADOutCallHistoryVO;

/**
 * @author 홍석훈
 */
public interface ADOutCallHistoryService {
    /**
     * 콜이력 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADOutCallHistoryVO> getOutCallHistoryList(ADOutCallHistoryVO outCallHistoryVo) throws BizException;

    /**
     * 콜이력 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getOutCallHistoryListCnt(ADOutCallHistoryVO outCallHistoryVo) throws BizException;

    /**
     * 목록 조회 ExcelDown
     * 
     * @return
     * @throws BizException
     */
    public List<ADOutCallHistoryVO> getOutCallHistoryListExcelDown(ADOutCallHistoryVO outCallHistoryVo) throws BizException;

}
