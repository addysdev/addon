/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADCounselHistoryVO;

/**
 * @author 홍석훈
 */
public interface ADCounselHistoryService {
    /**
     * 상담이력 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADCounselHistoryVO> getCounselHistoryList(ADCounselHistoryVO counselHistoryVo) throws BizException;

    /**
     * 상담이력 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCounselHistoryListCnt(ADCounselHistoryVO counselHistoryVo) throws BizException;

    /**
     * 상담이력 목록 조회 ExcelDown
     * 
     * @return
     * @throws BizException
     */
    public List<ADCounselHistoryVO> getCounselHistoryListExcelDown(ADCounselHistoryVO counselHistoryVo) throws BizException;

    /**
     * 현재 work time check 09 ~ 20 시인지 여부를 Y,N으로
     * 
     * @return
     * @throws BizException
     */
    public String getCheckWorkTime() throws BizException;

}
