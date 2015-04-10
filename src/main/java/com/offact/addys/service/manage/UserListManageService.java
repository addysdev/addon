/**
 *
 */
package com.offact.addys.service.manage;

import java.util.ArrayList;
import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.manage.UserListManageVO;

/**
 * @author
 */
public interface UserListManageService {
    /**
     * 사용자 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<UserListManageVO> getUserList(UserListManageVO user) throws BizException;

    /**
     * 사용자 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getUserCnt(UserListManageVO user) throws BizException;

    /**
     * 사용자 상세정보 수정
     * 
     * @return
     * @throws BizException
     */
    public void userUpdateProc(UserListManageVO userDetail) throws BizException;

    /**
     * 사용자 상세정보 저장
     * 
     * @return
     * @throws BizException
     */
    public void userInsertProc(UserListManageVO userDetail) throws BizException;

    /**
     * 사용자상세조회
     * 
     * @param userId
     * @return
     * @throws BizException
     */
    public UserListManageVO getUserDetail(String userId) throws BizException;

}
