/**
 *
 */
package com.offact.addys.service.common;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ADCodeManageVO;
import com.offact.addys.vo.common.CategoryVO;

/**
 * @author 4530
 *
 */
public interface CodeService {

	public List<CategoryVO> getCategoryLList() throws BizException;

	public List<CategoryVO> getCategorySList(String categoryLCode) throws BizException;

	public List<ADCodeManageVO> getPopupCodeList(String codeId)  throws BizException;


}
