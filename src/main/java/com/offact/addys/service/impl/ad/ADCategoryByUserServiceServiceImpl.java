/**
 *
 */
package com.offact.addys.service.impl.ad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADCategoryByUserService;
import com.offact.addys.vo.common.CategoryVO;

/**
 * @author HSH
 * @since 2014.02.19
 */

@Service
public class ADCategoryByUserServiceServiceImpl implements ADCategoryByUserService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

	/**
	 * 딜정보 리스트 조회
	 */
	@Override
	public List<CategoryVO> getCategoryByuserList(CategoryVO category) throws BizException {
		return commonDao.selectList("Category.getCategoryByuserList", category);
	}


	/**
	 * 딜정보 카운트 조회
	 */
	@Override
	public int getCategoryByuserCnt(CategoryVO category) throws BizException {
		return commonDao.selectOne("Category.getCategoryByuserCnt", category);
	}
	@Override
	public List<CategoryVO> getCurrCategoryByUser(CategoryVO category) throws BizException {
		return commonDao.selectList("Category.getCurrCategoryByUser", category);
	}

	@Override
	public void saveCategoryByUser(String categorySCode,String categoryByUserId, String regiUserId) throws BizException {
		categorySCode = categorySCode.substring(0,categorySCode.lastIndexOf("^")) ;

		if(!categoryByUserId.equals("") ){
			categoryByUserId = categoryByUserId.substring(0,categoryByUserId.lastIndexOf("^")) ;
		}

		String[] arrCategorySCode = categorySCode.split("\\^");
		String[] arrCategoryByUserId = categoryByUserId.trim().split("\\^");


		for(int i=0 ; i < arrCategorySCode.length ; i++){
			Map deleteMap = new HashMap<>();

			deleteMap.put("categorySCode", arrCategorySCode[i]);
			commonDao.delete("Category.deleteCategoryByUser", deleteMap);

			for(int j=0;j < arrCategoryByUserId.length ; j++){

				if(!arrCategoryByUserId[j].equals("")){
					Map insertMap = new HashMap<>();
					insertMap.put("categorySCode", arrCategorySCode[i]);
					insertMap.put("categoryByUserId", arrCategoryByUserId[j]);
					insertMap.put("regiUserId", regiUserId);

					commonDao.insert("Category.insertCategoryByUser", insertMap);

				}

			}
		}
	}






}
