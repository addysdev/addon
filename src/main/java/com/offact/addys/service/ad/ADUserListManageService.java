/**
 *
 */
package com.offact.addys.service.ad;

import java.util.ArrayList;
import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.ad.ADUserListManageVO;

/**
 * @author
 */
public interface ADUserListManageService {
    /**
     * 사용자 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ADUserListManageVO> getUserList(ADUserListManageVO user) throws BizException;

    public List<ADUserListManageVO> getSkillUserList(ADUserListManageVO user) throws BizException;

    /**
     * 사용자 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getUserCnt(ADUserListManageVO user) throws BizException;

    /**
     * 스킬 사용자 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getUserSkillCnt(ADUserListManageVO user) throws BizException;

    /**
     * 사용자 상세정보 수정
     * 
     * @return
     * @throws BizException
     */
    public void userUpdateProc(ADUserListManageVO userDetail) throws BizException;

    /**
     * 사용자 상세정보 저장
     * 
     * @return
     * @throws BizException
     */
    public void userInsertProc(ADUserListManageVO userDetail) throws BizException;

    /**
     * 사용자최신정보갱신
     * 
     * @return
     * @throws BizException
     */
    public String userInfoReload(ArrayList<UserVO> userVo, String sessionUserId) throws BizException;

    /**
     * 사용자상세조회
     * 
     * @param userId
     * @return
     * @throws BizException
     */
    public ADUserListManageVO getUserDetail(String id) throws BizException;

    /**
     * 배정회수
     * 
     * @return
     * @throws BizException
     */
    public void transferRollBack(ADUserListManageVO userDetail) throws BizException;
}
