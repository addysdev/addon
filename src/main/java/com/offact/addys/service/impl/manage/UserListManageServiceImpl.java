/**
 *
 */
package com.offact.addys.service.impl.manage;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;

import com.offact.addys.service.manage.UserListManageService;

import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.manage.UserListManageVO;

/**
 * @author 4530
 */
@Service
public class UserListManageServiceImpl implements UserListManageService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<UserListManageVO> getUserList(UserListManageVO usercondition) throws BizException {
    	
        List<UserListManageVO> userListManage = commonDao.selectList("UserManage.getUserListManage", usercondition);

        return userListManage;
    }

    @Override
    public int getUserCnt(UserListManageVO usercondition) throws BizException {
        return commonDao.selectOne("UserManage.getUserCnt", usercondition);
    }

    @Override
    public void userUpdateProc(UserListManageVO userDetail) throws BizException {
        // 사용자 상세정보 수정

        commonDao.update("UserManage.userUpdateProc", userDetail);

    }

    @Override
    public void userInsertProc(UserListManageVO userDetail) throws BizException {
        // 사용자 추가

        commonDao.update("UserManage.userInsertProc", userDetail);

    }

    @Override
    public UserListManageVO getUserDetail(String userId) throws BizException {
    	UserListManageVO userDetailVO = commonDao.selectOne("UserManage.getUserDetail", userId);

        return userDetailVO;
    }

}
