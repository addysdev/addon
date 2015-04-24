/**
 *
 */
package com.offact.addys.service.impl.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.manage.UserManageService;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.manage.UserManageVO;

/**
 * @author 4530
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<UserManageVO> getUserList(UserManageVO usercondition) throws BizException {
    	
        List<UserManageVO> userListManage = commonDao.selectList("UserManage.getUserPageList", usercondition);

        return userListManage;
    }

    @Override
    public int getUserCnt(UserManageVO usercondition) throws BizException {
        return commonDao.selectOne("UserManage.getUserCnt", usercondition);
    }

    @Override
    public int userUpdateProc(UserManageVO userDetail) throws BizException {
        // 사용자 상세정보 수정

        return commonDao.update("UserManage.userUpdateProc", userDetail);

    }

    @Override
    public int userInsertProc(UserManageVO userDetail) throws BizException {
        // 사용자 추가

    	return commonDao.update("UserManage.userInsertProc", userDetail);

    }

    @Override
    public UserManageVO getUserDetail(String userId) throws BizException {
    	
    	UserManageVO userDetailVO = commonDao.selectOne("UserManage.getUserDetail", userId);

        return userDetailVO;
    }
    
    public int userDeleteProc(String updateUserId , String arrDelUserId) throws BizException
    {
    	
      arrDelUserId = arrDelUserId.substring(0, arrDelUserId.lastIndexOf("^"));

      String[] arrUserId = arrDelUserId.split("\\^");
      
      int retVal=0;

      for (int i = 0; i < arrUserId.length; i++) {
        Map deleteMap = new HashMap();

        deleteMap.put("updateUserId", updateUserId);
        deleteMap.put("userId", arrUserId[i]);
        
        retVal=this.commonDao.delete("UserManage.userDeleteProc", deleteMap);

      }
      return retVal;
    }

}
