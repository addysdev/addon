/**
 *
 */
package com.offact.addys.service.impl.ad;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.common.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADUserListManageService;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.ad.ADTransferHistoryVO;
import com.offact.addys.vo.ad.ADUserAuthListVO;
import com.offact.addys.vo.ad.ADUserListManageVO;

/**
 * @author 4530
 */
@Service
public class ADUserListManageServiceImpl implements ADUserListManageService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADUserListManageVO> getUserList(ADUserListManageVO usercondition) throws BizException {
        List<ADUserListManageVO> userListManage = commonDao.selectList("User.getUserListManage", usercondition);
        return userListManage;
    }

    @Override
    public List<ADUserListManageVO> getSkillUserList(ADUserListManageVO usercondition) throws BizException {
        List<ADUserListManageVO> userListManage = commonDao.selectList("User.getSkillUserListManage", usercondition);
        return userListManage;
    }

    @Override
    public int getUserCnt(ADUserListManageVO usercondition) throws BizException {
        return commonDao.selectOne("User.getUserCnt", usercondition);
    }

    @Override
    public int getUserSkillCnt(ADUserListManageVO usercondition) throws BizException {
        return commonDao.selectOne("User.getUserSkillCnt", usercondition);
    }

    @Override
    public void userUpdateProc(ADUserListManageVO userDetail) throws BizException {
        // 사용자 상세정보 수정
        logger.debug("userDetail=================" + userDetail);
        commonDao.update("User.userUpdateProc", userDetail);

    }

    @Override
    public void userInsertProc(ADUserListManageVO userDetail) throws BizException {
        // 사용자 추가
        logger.debug("userDetail=================" + userDetail);
        commonDao.update("User.userInsertProc", userDetail);

    }

    @Override
    public String userInfoReload(ArrayList<UserVO> userList, String sessionUserId) throws BizException {
        // 사용자 최신정보갱신
        logger.debug("userDetail=================" + userList);

        String rtCode = "0";
        // 권한처리용
        ADUserAuthListVO userAuthVO = commonDao.selectOne("GroupAuth.getFromToDate");

        if (userList.size() == 0 || userList == null) {
            return rtCode;
        } else {
            // 먼저 전체 N 처리
            commonDao.update("User.setUserInfoFromLdapFirstSet");
            // 실제 적용시작
            for (int i = 0; i < userList.size(); i++) {
                UserVO userVo = userList.get(i);
                // 권한처리용
                userAuthVO.setUserId(userVo.getUserId());
                userAuthVO.setSession_userId(sessionUserId);
                userVo.setLastCortId(sessionUserId);

                logger.debug("LDAP USER LIST (" + i + ")" + userVo);
                // 기존에 있는지 확인한다.
                int rtCnt = commonDao.selectOne("User.getUserCntLDAP", userVo);
                // update 전에 기존 사용자 정보에서 그룹과 부서를 체크 하여 권한 셋팅 여부 확인
                ADUserListManageVO checkUserVo = commonDao.selectOne("User.getUserChkLDAP", userVo);
                if (checkUserVo == null) {
                    checkUserVo = new ADUserListManageVO();
                }
                String ldapUserGroupCode = StringUtil.nvl(userVo.getUserGroupCode());
                String ldapUserDeptCode = StringUtil.nvl(userVo.getUserDeptCode());
                String ldapUsingYn = StringUtil.nvl(userVo.getUsingYn());
                String chkUserGroupCode = StringUtil.nvl(checkUserVo.getUserGroupCode());
                String chkUserDeptCode = StringUtil.nvl(checkUserVo.getUserDeptCode());
                String chkUsingYn = StringUtil.nvl(checkUserVo.getUsingYn());

                logger.debug("ldapUserGroupCode:" + ldapUserGroupCode);
                logger.debug("chkUserGroupCode :" + chkUserGroupCode);
                logger.debug("ldapUserDeptCode :" + ldapUserDeptCode);
                logger.debug("chkUserDeptCode  :" + chkUserDeptCode);
                logger.debug("ldapUsingYn      :" + ldapUsingYn);
                logger.debug("chkUsingYn       :" + chkUsingYn);

                logger.debug(userVo);
                logger.debug(checkUserVo);

                if (rtCnt > 0) {// 있으면 Update
                    commonDao.update("User.setUserInfoFromLdapUpdate", userVo);

                    // 권한 처리 : 부서,그룹,사용여부 중 하나라도 변경된경우 권한 처리
                    if (!ldapUserGroupCode.equals("") && !ldapUserDeptCode.equals("") && !ldapUsingYn.equals("")) {
                        if ((!ldapUserGroupCode.equals(chkUserGroupCode) || !ldapUserDeptCode.equals(chkUserDeptCode) || !ldapUsingYn.equals(chkUsingYn))) {
                            logger.debug("사용자 업데이트 이후 권한 업데이트");
                            userAuthVO.setUserDeptCode(ldapUserDeptCode);
                            commonDao.update("User.setUserInfoFromLdapAuthCut", userAuthVO);
                            commonDao.insert("User.setUserInfoFromLdapAuthInsert", userAuthVO);
                        }
                    }

                } else { // 없으면 insert
                    userVo.setFrstRegrId(sessionUserId);
                    commonDao.insert("User.setUserInfoFromLdapInsert", userVo);
                    if (!ldapUserGroupCode.equals("") && !ldapUserDeptCode.equals("") && !ldapUsingYn.equals("")) {
                        if ((!ldapUserGroupCode.equals(chkUserGroupCode) || !ldapUserDeptCode.equals(chkUserDeptCode) || !ldapUsingYn.equals(chkUsingYn))) {
                            logger.debug("사용자 입력후 권한 셋팅");
                            userAuthVO.setUserDeptCode(ldapUserDeptCode);
                            commonDao.update("User.setUserInfoFromLdapAuthCut", userAuthVO);
                            commonDao.insert("User.setUserInfoFromLdapAuthInsert", userAuthVO);
                        }
                    }
                }
            }
        }
        return rtCode;
    }

    @Override
    public void transferRollBack(ADUserListManageVO usercondition) throws BizException {
        logger.debug("보낸이관 회수작업");
        commonDao.update("User.transferSendRollBack", usercondition);

        logger.debug("받은이관 회수 리스트확인");
        List<ADTransferHistoryVO> transferList = commonDao.selectList("User.transferReceiveRollBackSearch", usercondition);

        if (transferList != null && transferList.size() > 0) {
            logger.debug("받은이관 회수 입력 및 해당 이관내역들 최종상태 변경작업");
            for (int i = 0; i < transferList.size(); i++) {
                commonDao.insert("User.transferReceiveRollBackInsert", transferList.get(i));
                // 현재 실행자로 수정자 셋팅
                transferList.get(i).setLastCortId(usercondition.getCon_userId());
                commonDao.update("User.transferReceiveRollBackUpdate", transferList.get(i));
            }
        }
    }

    @Override
    public ADUserListManageVO getUserDetail(String id) throws BizException {
        ADUserListManageVO userDetailVO = commonDao.selectOne("User.getUserDetail", id);

        return userDetailVO;
    }

}
