package com.offact.addys.service.impl.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADClaimListSearchService;
import com.offact.addys.vo.ad.ADClaimListSearchVO;

@Service
public class ADClaimListSearchServiceImpl implements ADClaimListSearchService {
    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADClaimListSearchVO> getClaimListSearchList(ADClaimListSearchVO adClaimListSearchVO) throws BizException {
        List<ADClaimListSearchVO> adClaimListSearchVOList = commonDao.selectList("ClaimListSearch.getClaimListSearchList", adClaimListSearchVO);
        return adClaimListSearchVOList;
    }

    @Override
    public int getClaimListSearchListCnt(ADClaimListSearchVO adClaimListSearchVO) throws BizException {
        return commonDao.selectOne("ClaimListSearch.getClaimListSearchListCnt", adClaimListSearchVO);
    }

    @Override
    public ADClaimListSearchVO getClaimListSearchListDetail(ADClaimListSearchVO adClaimListSearchVO) throws BizException {
        ADClaimListSearchVO adClaimListSearchVODetail = commonDao.selectOne("ClaimListSearch.getClaimListSearchListDetail", adClaimListSearchVO);
        return adClaimListSearchVODetail;
    }

    @Override
    public List<ADClaimListSearchVO> getClaimListSearchListExcelDown(ADClaimListSearchVO adClaimListSearchVO) throws BizException {
        List<ADClaimListSearchVO> adClaimListSearchVOList = commonDao.selectList("ClaimListSearch.getClaimListSearchListExcelDown", adClaimListSearchVO);
        return adClaimListSearchVOList;
    }

}
