/**
 *
 */
package com.offact.addys.service.smart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.smart.CounselVO;

/**
 * @author
 */
public interface CounselService {
    /**
     * 상담 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<CounselVO> getCounselList(CounselVO counsel) throws BizException;

    /**
     * 사용자 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCounselCnt(CounselVO counsel) throws BizException;

    /**
     * 상담상세조회
     * 
     * @param idx
     * @return
     * @throws BizException
     */
    public CounselVO getCounselDetail(String idx) throws BizException;
    
    /**
     * 상태 처리
     * 
     * @return
     * @throws BizException
     */
    public int counselStateUpdate(CounselVO counsel) throws BizException;
    
    /**
     * 상담 처리
     * 
     * @return
     * @throws BizException
     */
    public int counselProc(CounselVO counsel) throws BizException;

}
