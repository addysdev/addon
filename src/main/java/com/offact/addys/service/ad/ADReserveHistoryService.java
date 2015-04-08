/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADReserveHistoryVO;

/**
 * @author 홍석훈
 */
public interface ADReserveHistoryService {
    /**
     * 예약이력 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADReserveHistoryVO> getReserveHistoryList(ADReserveHistoryVO reserveHistoryVo) throws BizException;

    /**
     * 예약이력 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getReserveHistoryListCnt(ADReserveHistoryVO reserveHistoryVo) throws BizException;

    /**
     * 당일 미처리 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getReserveHistoryIngCnt(ADReserveHistoryVO reserveHistoryVo) throws BizException;

    /**
     * 목록 조회 ExcelDown
     * 
     * @return
     * @throws BizException
     */
    public List<ADReserveHistoryVO> getReserveHistoryListExcelDown(ADReserveHistoryVO reserveHistoryVo) throws BizException;
}
