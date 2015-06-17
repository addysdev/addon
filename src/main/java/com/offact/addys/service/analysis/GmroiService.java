/**
 *
 */
package com.offact.addys.service.analysis;

import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.analysis.GmroiVO;
/**
 * @author 4530
 */
public interface GmroiService {
    
    /**
     * 보유재고 추천 조회
     * 
     * @return
     * @throws BizException
     */
    public List<GmroiVO> getGmroiPageList(GmroiVO gmroi) throws BizException;

    /**
     * 보유재고 추천 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getGmroiCnt(GmroiVO gmroi) throws BizException;

}
