package com.offact.addys.service.impl.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADReserveHistoryService;
import com.offact.addys.vo.ad.ADReserveHistoryVO;

@Service
public class ADReserveHistoryServiceImpl implements ADReserveHistoryService {
    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADReserveHistoryVO> getReserveHistoryList(ADReserveHistoryVO reserveHistoryVo) throws BizException {
        List<ADReserveHistoryVO> adReserveHistoryVOList = commonDao.selectList("ReserveHistory.getReserveHistoryList", reserveHistoryVo);
        return adReserveHistoryVOList;
    }

    @Override
    public int getReserveHistoryListCnt(ADReserveHistoryVO reserveHistoryVo) throws BizException {
        return commonDao.selectOne("ReserveHistory.getReserveHistoryListCnt", reserveHistoryVo);
    }

    @Override
    public int getReserveHistoryIngCnt(ADReserveHistoryVO reserveHistoryVo) throws BizException {
        return commonDao.selectOne("ReserveHistory.getReserveHistoryIngCnt", reserveHistoryVo);
    }

    @Override
    public List<ADReserveHistoryVO> getReserveHistoryListExcelDown(ADReserveHistoryVO reserveHistoryVo) throws BizException {
        List<ADReserveHistoryVO> adReserveHistoryVOList = commonDao.selectList("ReserveHistory.getReserveHistoryListExcelDown", reserveHistoryVo);
        return adReserveHistoryVOList;
    }
}
