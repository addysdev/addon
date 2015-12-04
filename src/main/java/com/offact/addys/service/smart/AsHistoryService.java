/**
 *
 */
package com.offact.addys.service.smart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.smart.AsHistoryVO;

/**
 * @author
 */
public interface AsHistoryService {
    /**
     * as 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<AsHistoryVO> getAsHistoryList(AsHistoryVO as) throws BizException;

}
