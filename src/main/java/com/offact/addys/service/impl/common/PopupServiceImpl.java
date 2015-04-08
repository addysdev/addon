package com.offact.addys.service.impl.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.common.PopupService;
import com.offact.addys.vo.ADCommonFormManageVO;

/**
 * @author 4530
 *
 */
@Service
public class PopupServiceImpl implements PopupService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SqlSessionCommonDao commonDao;


	@Override
	public int getSmsFormCnt() throws BizException {
		return commonDao.selectOne("Popup.getSmsFormCnt");
	}

	@Override
	public List<ADCommonFormManageVO> getSmsFormList(ADCommonFormManageVO commonForm)
			throws BizException {
		List<ADCommonFormManageVO> commonList = commonDao.selectList("Popup.getSmsFormList", commonForm);
		return commonList;
	}


	@Override
	public int getTransferFormCnt() throws BizException {
		return commonDao.selectOne("Popup.getTransferFormCnt");
	}

	@Override
	public List<ADCommonFormManageVO> getTransferFormList(ADCommonFormManageVO commonForm)
			throws BizException {
		List<ADCommonFormManageVO> commonList = commonDao.selectList("Popup.getTransferFormList", commonForm);
		return commonList;
	}




	@Override
	public int getCommonFormCnt(ADCommonFormManageVO commonForm) throws BizException {
		return commonDao.selectOne("Popup.getCommonFormCnt",commonForm);
	}

	@Override
	public List<ADCommonFormManageVO> getCommonFormList(ADCommonFormManageVO commonForm)
			throws BizException {
		List<ADCommonFormManageVO> commonList = commonDao.selectList("Popup.getCommonFormList", commonForm);
		return commonList;
	}




}
