package com.offact.addys.service.impl.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.common.CodeService;
import com.offact.addys.vo.ADCodeManageVO;
import com.offact.addys.vo.common.CategoryVO;

/**
 * @author 4530
 *
 */
@Service
public class CodeServiceImpl implements CodeService {

	private final Logger logger = Logger.getLogger(getClass());


	@Autowired
	private SqlSessionCommonDao commonDao;


	@Override
	public List<CategoryVO> getCategoryLList() throws BizException {

		List<CategoryVO> categoryLList = commonDao.selectList("ADCodeManage.getCategoryLList");

		return categoryLList;
	}


	@Override
	public List<CategoryVO> getCategorySList(String categoryLCode) throws BizException {

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryLCode(categoryLCode);

		List<CategoryVO> categoryLList = commonDao.selectList("ADCodeManage.getCategorySList",categoryVO);

		return categoryLList;
	}

	@Override
	public List<ADCodeManageVO> getPopupCodeList(String codeId) throws BizException {

		ADCodeManageVO codeManageVO = new ADCodeManageVO();
		codeManageVO.setCodeId(codeId);

		List<ADCodeManageVO> codeList = commonDao.selectList("ADCodeManage.getPopupCodeList",codeManageVO);

		return codeList;
	}






}
