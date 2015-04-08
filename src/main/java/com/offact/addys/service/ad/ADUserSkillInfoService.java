/**
 * 
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ad.ADUserSkillInfoVO;

/**
 * @author HSH
 */
public interface ADUserSkillInfoService {
    /**
     * 콜스킬목록조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADUserSkillInfoVO> getUserSkillInfoCallList(ADUserSkillInfoVO userSkillInfo) throws BizException;

    /**
     * 게시판스킬목록조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADUserSkillInfoVO> getUserSkillInfoBoardList(ADUserSkillInfoVO userSkillInfo) throws BizException;

    /**
     * 사용자정보
     * 
     * @return
     * @throws BizException
     */
    public String getUserInfo(ADUserSkillInfoVO userSkillInfo) throws BizException;
}
