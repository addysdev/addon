/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADUserSearchVO;

/**
 * @author HSH
 */
public interface ADUserSearchService {
    /**
     * 상담원목록조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADUserSearchVO> getAdUserSearchList(ADUserSearchVO adUserSearchVO) throws BizException;

    /**
     * 상담원정보조회
     * 
     * @return
     * @throws BizException
     */
    public ADUserSearchVO getAdUserSearchInfo(ADUserSearchVO adUserSearchVO) throws BizException;

    /**
     * 자동배정상담원정보조회
     * 
     * @return
     * @throws BizException
     */
    public ADUserSearchVO getAdUserSearchAutoSetInfo(ADUserSearchVO adUserSearchVO) throws BizException;

}
