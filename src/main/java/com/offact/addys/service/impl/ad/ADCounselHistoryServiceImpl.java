package com.offact.addys.service.impl.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADCounselHistoryService;
import com.offact.addys.vo.ad.ADCounselHistoryVO;

@Service
public class ADCounselHistoryServiceImpl implements ADCounselHistoryService {
    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADCounselHistoryVO> getCounselHistoryList(ADCounselHistoryVO counselHistoryVo) throws BizException {
        List<ADCounselHistoryVO> adCounselHistoryVOList = commonDao.selectList("CounselHistory.getCounselHistoryList", counselHistoryVo);
        return adCounselHistoryVOList;
    }

    @Override
    public int getCounselHistoryListCnt(ADCounselHistoryVO counselHistoryVo) throws BizException {
        return commonDao.selectOne("CounselHistory.getCounselHistoryListCnt", counselHistoryVo);
    }

    @Override
    public List<ADCounselHistoryVO> getCounselHistoryListExcelDown(ADCounselHistoryVO counselHistoryVo) throws BizException {
        List<ADCounselHistoryVO> adCounselHistoryVOList = commonDao.selectList("CounselHistory.getCounselHistoryListExcelDown", counselHistoryVo);
        return adCounselHistoryVOList;
    }

    @Override
    public String getCheckWorkTime() throws BizException {
        String yn = commonDao.selectOne("CounselHistory.getCheckWorkTime");
        return yn;
    }

}
