/**
 *
 */
package com.offact.addys.service.common;

import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.common.WorkVO;
import com.offact.addys.vo.manage.CompanyManageVO;
/**
 * @author 4530
 */
public interface CommonService {
    
    /**
     * 코드 목록
     *
     * @return
     * @throws BizException
     */
    public List<CodeVO> getCodeComboList(CodeVO code) throws BizException;

    /**
     * 그룹 목록
     *
     * @return
     * @throws BizException
     */
    public List<GroupVO> getGroupComboList(GroupVO group) throws BizException;
    
    
    /**
     * 업체정보
     *
     * @return
     * @throws BizException
     */
    public CompanyVO getCompanyDetail(CompanyVO company) throws BizException;
    /**
     * comment 조회
     * 
     * @return
     * @throws BizException
     */
    public List<CommentVO> getCommentList(CommentVO comment) throws BizException;
    /**
     * 품목별 비고 조회
     * 
     * @return
     * @throws BizException
     */
    public List<CommentVO> getProductEtcList(CommentVO comment) throws BizException;
    /**
     * comment저장
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiCommentInsert(CommentVO comment)
    	    throws BizException;
    
    /**
     * sms대상 목록
     *
     * @return
     * @throws BizException
     */
    public List<UserVO> getSmsList(UserVO usercon) throws BizException;
    
    /**
     * sms batch대상 목록
     *
     * @return
     * @throws BizException
     */
    public List<UserVO> getSmsBatchList(UserVO usercon) throws BizException;
    /**
     * 업무이력저장
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiHistoryInsert(WorkVO work)
    	    throws BizException;
    
    /**
     * 업무이력 조회
     * 
     * @return
     * @throws BizException
     */
    public List<WorkVO> getWorkHistoryPageList(WorkVO work) throws BizException;

    /**
     * 업무이력 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getCompanyCnt(WorkVO work) throws BizException;

}
