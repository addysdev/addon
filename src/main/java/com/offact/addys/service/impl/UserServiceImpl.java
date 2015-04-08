package com.offact.addys.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonAdminDao;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.UserService;
import com.offact.addys.vo.UserConditionVO;
import com.offact.addys.vo.UserVO;

/**
 * @author 4530
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SqlSessionCommonDao commonDao;

	@Autowired
	private SqlSessionCommonAdminDao commonSubDao;		//multi datasource test

	@Override
	public UserVO getUser(UserVO user) throws BizException {
		return commonDao.selectOne("User.getUser", user);
	}

	@Override
	public int getUserCnt() throws BizException {
		return commonDao.selectOne("User.getUserCnt");
	}

	@Override
	public List<UserVO> getUserList(UserConditionVO userConVo)
			throws BizException {
		return commonDao.selectList("User.getUserList", userConVo);
	}

	@Override
	public void inserUserProc(UserVO userVO)
			throws BizException {
		commonDao.insert("User.inserUserProc", userVO);
	}

}
