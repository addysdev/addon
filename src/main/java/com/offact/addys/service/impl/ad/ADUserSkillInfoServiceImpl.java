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
import com.offact.addys.service.ad.ADUserSkillInfoService;
import com.offact.addys.vo.ad.ADUserSkillInfoVO;

/**
 * @author 4530
 */
@Service
public class ADUserSkillInfoServiceImpl implements ADUserSkillInfoService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ADUserSkillInfoVO> getUserSkillInfoCallList(ADUserSkillInfoVO skillCondition) throws BizException {
        List<ADUserSkillInfoVO> userSkillInfo = commonDao.selectList("UserSkillInfo.getUserSkillInfoCallList", skillCondition);
        return userSkillInfo;
    }

    @Override
    public List<ADUserSkillInfoVO> getUserSkillInfoBoardList(ADUserSkillInfoVO skillCondition) throws BizException {
        List<ADUserSkillInfoVO> userSkillInfo = commonDao.selectList("UserSkillInfo.getUserSkillInfoBoardList", skillCondition);
        return userSkillInfo;
    }

    @Override
    public String getUserInfo(ADUserSkillInfoVO skillCondition) throws BizException {
        return commonDao.selectOne("UserSkillInfo.getUserInfo", skillCondition);
    }

}
