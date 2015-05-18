/**
 *
 */
package com.offact.addys.service.recovery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.order.TargetVO;
import com.offact.addys.vo.recovery.RecoveryVO;

/**
 * @author
 */
public interface RecoveryService {
    /**
     * 회수대상 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<RecoveryVO> getRecoveryPageList(RecoveryVO recovery) throws BizException;
    /**
     * 회수대상 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getRecoveryCnt(RecoveryVO recovery) throws BizException;
    /**
     * 회수 등록처리
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiRecoveryRegist(RecoveryVO recovery, String arrCheckGroupId ,String arrSelectProductId)
    	    throws BizException;
    
    /**
     * 회수대상 상세 조회
     * 
     * @return
     * @throws BizException
     */
    public RecoveryVO getRecoveryDetail(RecoveryVO recoveryCon) throws BizException;
    /**
     * 회수대상 상세목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<RecoveryVO> getRecoveryDetailList(RecoveryVO recovery) throws BizException;
   
   
}
