/**
 *
 */
package com.offact.addys.service.ad;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CategoryVO;

/**
 * @author HSH
 */
public interface ADCategoryByUserService {
    /**
     * 상담원목록조회
     *
     * @return
     * @throws BizException
     */

	public List<CategoryVO> getCategoryByuserList(CategoryVO category) throws BizException;

	public int getCategoryByuserCnt(CategoryVO category) throws BizException;
	public List<CategoryVO> getCurrCategoryByUser(CategoryVO category) throws BizException;
	public void saveCategoryByUser(String arrCategorySCode,String arrCategoryByUserId, String regiUserId) throws BizException;


}
