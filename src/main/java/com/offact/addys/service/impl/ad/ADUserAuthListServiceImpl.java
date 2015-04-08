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
import com.offact.addys.service.ad.ADUserAuthListService;
import com.offact.addys.vo.ad.ADUserAuthListVO;

/**
 * @author 4530
 */
@Service
public class ADUserAuthListServiceImpl implements ADUserAuthListService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADUserAuthListVO> getADUserAuthList(ADUserAuthListVO adUserAuthList) throws BizException {
        List<ADUserAuthListVO> adUserAuthListSet = commonDao.selectList("UserAuthList.getAdUserAuthList", adUserAuthList);
        return adUserAuthListSet;
    }

    @Override
    public int getCnt(ADUserAuthListVO adUserAuthList) throws BizException {
        return commonDao.selectOne("UserAuthList.getCnt", adUserAuthList);
    }

    @Override
    public ADUserAuthListVO getUserInfo(ADUserAuthListVO adUserAuthList) throws BizException {
        return commonDao.selectOne("UserAuthList.getUserInfo", adUserAuthList);
    }

    @Override
    public void setADUserAuth(ADUserAuthListVO adUserAuthList) throws BizException {
        ADUserAuthListVO userAuthVO = commonDao.selectOne("GroupAuth.getFromToDate");

        adUserAuthList.setValidFromDt(userAuthVO.getValidFromDt());
        adUserAuthList.setValidToDt(userAuthVO.getValidToDt());

        commonDao.update("UserAuthList.updateUserAuthValidToDt", adUserAuthList);
        commonDao.insert("UserAuthList.insertUserAuthValidToDt", adUserAuthList);
    }

    @Override
    public List<ADUserAuthListVO> getAdUserAuthListInsertList(ADUserAuthListVO adUserAuthList) throws BizException {
        List<ADUserAuthListVO> adUserAuthListSet = commonDao.selectList("UserAuthList.getAdUserAuthListInserList", adUserAuthList);
        return adUserAuthListSet;
    }
}
