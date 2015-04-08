/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADTransferHistoryVO;

/**
 * @author 홍석훈
 */
public interface ADTransferHistoryService {
    /**
     * 이관이력 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADTransferHistoryVO> getTransferHistoryList(ADTransferHistoryVO transferHistoryVo) throws BizException;

    /**
     * 이관이력 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getTransferHistoryListCnt(ADTransferHistoryVO transferHistoryVo) throws BizException;

    /**
     * 당일 미처리 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getTransferHistoryIngCnt(ADTransferHistoryVO transferHistoryVo) throws BizException;

    /**
     * 상세이관이력 목록조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADTransferHistoryVO> getTransferHistoryLinkedList(ADTransferHistoryVO transferHistoryVo) throws BizException;

    /**
     * 상세이관이력 전체갯수
     * 
     * @return
     * @throws BizException
     */
    public int getTransferHistoryLinkedListCnt(ADTransferHistoryVO transferHistoryVo) throws BizException;

    /**
     * 목록 조회 ExcelDown
     * 
     * @return
     * @throws BizException
     */
    public List<ADTransferHistoryVO> getTransferHistoryListExcelDown(ADTransferHistoryVO transferHistoryVo) throws BizException;
}
