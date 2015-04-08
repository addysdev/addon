/**
 * 
 */
package com.offact.addys.service.impl.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADUserSearchService;
import com.offact.addys.vo.ad.ADUserSearchVO;

/**
 * @author HSH
 * @since 2014.02.19
 */

@Service
public class ADUserSearchServiceImpl implements ADUserSearchService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADUserSearchVO> getAdUserSearchList(ADUserSearchVO adUserSearchVO) throws BizException {
        List<ADUserSearchVO> adUserSearchVoList = commonDao.selectList("UserSearch.getAdUserSearchList", adUserSearchVO);
        return adUserSearchVoList;
    }

    @Override
    public ADUserSearchVO getAdUserSearchInfo(ADUserSearchVO adUserSearchVO) throws BizException {
        ADUserSearchVO adUserSearchRtVal = commonDao.selectOne("UserSearch.getAdUserSearchInfo", adUserSearchVO);
        return adUserSearchRtVal;
    }

    @Override
    public ADUserSearchVO getAdUserSearchAutoSetInfo(ADUserSearchVO adUserSearchVO) throws BizException {
        ADUserSearchVO adUserSearchRtVal = commonDao.selectOne("UserSearch.getAdUserSearchAutoSetInfo", adUserSearchVO);
        return adUserSearchRtVal;
    }

}
