/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADConsultExileSearchVO;

/**
 * @author 홍석훈
 */
public interface ADConsultExileSearchService {
    /**
     * 상담유형 트리 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADConsultExileSearchVO> getAdConsultExileSearchTree(ADConsultExileSearchVO adConsultExileSearchVO) throws BizException;

    /**
     * 상담유형 검색 리스트 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADConsultExileSearchVO> getAdConsultExileSearchList(ADConsultExileSearchVO adConsultExileSearchVO) throws BizException;

    /**
     * 상담유형 코드정보조회
     * 
     * @return
     * @throws BizException
     */
    public ADConsultExileSearchVO getAdConsultExileSearchResult(ADConsultExileSearchVO adConsultExileSearchVO) throws BizException;

}
