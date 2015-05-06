/**
 *
 */
package com.offact.addys.service.recovery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
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

   
}
