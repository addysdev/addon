package com.offact.addys.service.impl.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADConsultExileSearchService;
import com.offact.addys.vo.ad.ADConsultExileSearchVO;

@Service
public class ADConsultExileSearchServiceImpl implements ADConsultExileSearchService {
    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADConsultExileSearchVO> getAdConsultExileSearchTree(ADConsultExileSearchVO adConsultExileSearchVO) throws BizException {
        List<ADConsultExileSearchVO> adConsultExileSearchVOList = commonDao.selectList("ConsultExileSearch.getConsultExileSearchList", adConsultExileSearchVO);
        return adConsultExileSearchVOList;
    }

    @Override
    public List<ADConsultExileSearchVO> getAdConsultExileSearchList(ADConsultExileSearchVO adConsultExileSearchVO) throws BizException {
        List<ADConsultExileSearchVO> adConsultExileSearchVOList = commonDao.selectList("ConsultExileSearch.getConsultExileSearchList", adConsultExileSearchVO);
        return adConsultExileSearchVOList;
    }

    @Override
    public ADConsultExileSearchVO getAdConsultExileSearchResult(ADConsultExileSearchVO adConsultExileSearchVO) throws BizException {
        ADConsultExileSearchVO adConsultExileSearchVOResult = commonDao.selectOne("ConsultExileSearch.getConsultExileSearchResult", adConsultExileSearchVO);
        return adConsultExileSearchVOResult;
    }
}
