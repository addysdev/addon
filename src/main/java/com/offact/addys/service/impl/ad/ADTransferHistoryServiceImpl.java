package com.offact.addys.service.impl.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADTransferHistoryService;
import com.offact.addys.vo.ad.ADTransferHistoryVO;

@Service
public class ADTransferHistoryServiceImpl implements ADTransferHistoryService {
    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADTransferHistoryVO> getTransferHistoryList(ADTransferHistoryVO transferHistoryVo) throws BizException {
        List<ADTransferHistoryVO> adTransferHistoryVOList = commonDao.selectList("TransferHistory.getTransferHistoryList", transferHistoryVo);
        return adTransferHistoryVOList;
    }

    @Override
    public int getTransferHistoryListCnt(ADTransferHistoryVO transferHistoryVo) throws BizException {
        return commonDao.selectOne("TransferHistory.getTransferHistoryListCnt", transferHistoryVo);
    }

    @Override
    public int getTransferHistoryIngCnt(ADTransferHistoryVO transferHistoryVo) throws BizException {
        return commonDao.selectOne("TransferHistory.getTransferHistoryIngCnt", transferHistoryVo);
    }

    @Override
    public List<ADTransferHistoryVO> getTransferHistoryLinkedList(ADTransferHistoryVO transferHistoryVo) throws BizException {
        List<ADTransferHistoryVO> adTransferHistoryVOList = commonDao.selectList("TransferHistory.getTransferHistoryLinkedList", transferHistoryVo);
        return adTransferHistoryVOList;
    }

    @Override
    public int getTransferHistoryLinkedListCnt(ADTransferHistoryVO transferHistoryVo) throws BizException {
        return commonDao.selectOne("TransferHistory.getTransferHistoryLinkedListCnt", transferHistoryVo);
    }

    @Override
    public List<ADTransferHistoryVO> getTransferHistoryListExcelDown(ADTransferHistoryVO transferHistoryVo) throws BizException {
        List<ADTransferHistoryVO> adTransferHistoryVOList = commonDao.selectList("TransferHistory.getTransferHistoryListExcelDown", transferHistoryVo);
        return adTransferHistoryVOList;
    }

}
