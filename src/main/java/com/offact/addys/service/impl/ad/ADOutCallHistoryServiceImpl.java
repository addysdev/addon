package com.offact.addys.service.impl.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADOutCallHistoryService;
import com.offact.addys.vo.ad.ADOutCallHistoryVO;

@Service
public class ADOutCallHistoryServiceImpl implements ADOutCallHistoryService {
    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADOutCallHistoryVO> getOutCallHistoryList(ADOutCallHistoryVO outCallHistoryVo) throws BizException {
        List<ADOutCallHistoryVO> adOutCallHistoryVOList = commonDao.selectList("OutCallHistory.getOutCallHistoryList", outCallHistoryVo);
        return adOutCallHistoryVOList;
    }

    @Override
    public int getOutCallHistoryListCnt(ADOutCallHistoryVO outCallHistoryVo) throws BizException {
        return commonDao.selectOne("OutCallHistory.getOutCallHistoryListCnt", outCallHistoryVo);
    }

    @Override
    public List<ADOutCallHistoryVO> getOutCallHistoryListExcelDown(ADOutCallHistoryVO outCallHistoryVo) throws BizException {
        List<ADOutCallHistoryVO> adOutCallHistoryVOList = commonDao.selectList("OutCallHistory.getOutCallHistoryListExcelDown", outCallHistoryVo);
        return adOutCallHistoryVOList;
    }

}
