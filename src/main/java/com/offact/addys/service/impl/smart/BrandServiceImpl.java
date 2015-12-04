/**
 *
 */
package com.offact.addys.service.impl.smart;

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
import com.offact.addys.service.smart.BrandService;
import com.offact.addys.vo.smart.BrandVO;

/**
 * @author 4530
 */
@Service
public class BrandServiceImpl implements BrandService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<BrandVO> getBrandPageList(BrandVO brand) throws BizException {
    	
        List<BrandVO> brandlList = commonDao.selectList("Brand.getBrandPageList", brand);

        return brandlList;
    }

    @Override
    public int getBrandCnt(BrandVO brand) throws BizException {
        return commonDao.selectOne("Brand.getBrandCnt", brand);
    }

    @Override
    public BrandVO getBrandDetail(BrandVO brand) throws BizException {
    	        
    	BrandVO brandDetailVO = commonDao.selectOne("Brand.getBrandDetail", brand);

        return brandDetailVO;
    }

  
}
