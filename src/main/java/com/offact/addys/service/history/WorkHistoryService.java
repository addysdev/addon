/**
 *
 */
package com.offact.addys.service.history;

import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.history.WorkHistoryVO;
import com.offact.addys.vo.manage.CompanyManageVO;
/**
 * @author 4530
 */
public interface WorkHistoryService {
    
    /**
     * 업무이력 조회
     * 
     * @return
     * @throws BizException
     */
    public List<WorkHistoryVO> getWorkHistoryPageList(WorkHistoryVO work) throws BizException;

    /**
     * 업무이력 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getWorkHistoryCnt(WorkHistoryVO work) throws BizException;

}
