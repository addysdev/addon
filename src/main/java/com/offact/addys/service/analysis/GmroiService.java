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
     * gmroi 조회
     * 
     * @return
     * @throws BizException
     */
    public List<GmroiVO> getGmroiPageList(GmroiVO gmroi) throws BizException;

    /**
     * gmroi 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getGmroiCnt(GmroiVO gmroi) throws BizException;

    /**
     * gmroi조회(Excel)
     * 
     * @return
     * @throws BizException
     */
    public List<GmroiVO> getGmroiList(GmroiVO gmroi) throws BizException;
    /**
     * gmroi total 갯수
     * 
     * @return
     * @throws BizException
     */
    public GmroiVO getGmroiTotalCnt(GmroiVO gmroi) throws BizException;

}
