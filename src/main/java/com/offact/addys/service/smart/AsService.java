/**
 *
 */
package com.offact.addys.service.smart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.smart.AsVO;

/**
 * @author
 */
public interface AsService {
    /**
     * as 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<AsVO> getAsList(AsVO as) throws BizException;

    /**
     * as 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getAsCnt(AsVO as) throws BizException;

    /**
     * as상세조회
     * 
     * @param idx
     * @return
     * @throws BizException
     */
    public AsVO getAsDetail(String idx) throws BizException;
    
    /**
     * as 처리
     * 
     * @return
     * @throws BizException
     */
    public int asStateUpdate(AsVO as) throws BizException;
    
    /**
     * as 처리
     * 
     * @return
     * @throws BizException
     */
    public int asProc(AsVO as) throws BizException;
    
    /**
     * as 상세조회
     * 
     * @return
     * @throws BizException
     */
    public List<AsVO> getAsReply(AsVO as) throws BizException;
    
    /**
     * as 상세 처리
     * 
     * @return
     * @throws BizException
     */
    public int regiReplyInsert(AsVO as) throws BizException;

}
