/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADUserAuthListVO;

/**
 * @author HSH
 */
public interface ADUserAuthListService {
    /**
     * 사용자권한목록조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADUserAuthListVO> getADUserAuthList(ADUserAuthListVO adUserAuthList) throws BizException;

    /**
     * 사용자권한목록 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCnt(ADUserAuthListVO adUserAuthList) throws BizException;

    /**
     * 사용자정보
     * 
     * @return
     * @throws BizException
     */
    public ADUserAuthListVO getUserInfo(ADUserAuthListVO adUserAuthList) throws BizException;

    /**
     * 사용자권한셋팅
     * 
     * @return
     * @throws BizException
     */
    public void setADUserAuth(ADUserAuthListVO adUserAuthList) throws BizException;

    /**
     * 사용자권한입력리스트
     * 
     * @return
     * @throws BizException
     */
    public List<ADUserAuthListVO> getAdUserAuthListInsertList(ADUserAuthListVO adUserAuthList) throws BizException;
}
