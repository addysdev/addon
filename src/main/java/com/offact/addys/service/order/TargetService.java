/**
 *
 */
package com.offact.addys.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.order.TargetVO;

/**
 * @author
 */
public interface TargetService {
    /**
     * 발주대상 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<TargetVO> getTargetPageList(TargetVO target) throws BizException;
    /**
     * 발주대상 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getTargetCnt(TargetVO target) throws BizException;
    
    /**
     * 발주대상 상세목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<TargetVO> getTargetDetailList(TargetVO target) throws BizException;
   
}
