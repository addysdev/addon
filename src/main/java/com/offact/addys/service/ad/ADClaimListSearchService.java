/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADClaimListSearchVO;

/**
 * @author 홍석훈
 */
public interface ADClaimListSearchService {
    /**
     * 클레임관리 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADClaimListSearchVO> getClaimListSearchList(ADClaimListSearchVO adClaimListSearchVO) throws BizException;

    /**
     * 클레임관리 목록 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getClaimListSearchListCnt(ADClaimListSearchVO adClaimListSearchVO) throws BizException;

    /**
     * 클레임관리 상세조회
     * 
     * @return
     * @throws BizException
     */
    public ADClaimListSearchVO getClaimListSearchListDetail(ADClaimListSearchVO adClaimListSearchVO) throws BizException;

    /**
     * 목록 조회 ExcelDown
     * 
     * @return
     * @throws BizException
     */
    public List<ADClaimListSearchVO> getClaimListSearchListExcelDown(ADClaimListSearchVO adClaimListSearchVO) throws BizException;

}
