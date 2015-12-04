/**
 *
 */
package com.offact.addys.service.smart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.smart.BrandVO;

/**
 * @author
 */
public interface BrandService {
    /**
     * as 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<BrandVO> getBrandPageList(BrandVO brand) throws BizException;

    /**
     * as 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getBrandCnt(BrandVO brand) throws BizException;

    /**
     * as상세조회
     * 
     * @param idx
     * @return
     * @throws BizException
     */
    public BrandVO getBrandDetail(BrandVO brand) throws BizException;
    


}
