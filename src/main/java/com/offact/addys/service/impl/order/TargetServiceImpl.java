/**
 *
 */
package com.offact.addys.service.impl.order;

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
import com.offact.addys.service.order.TargetService;
import com.offact.addys.vo.order.TargetVO;

/**
 * @author 4530
 */
@Service
public class TargetServiceImpl implements TargetService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<TargetVO> getTargetPageList(TargetVO target) throws BizException {
    	
        List<TargetVO> targetList = commonDao.selectList("Target.getTargetPageList", target);

        return targetList;
    }

    @Override
    public int getTargetCnt(TargetVO target) throws BizException {
        return commonDao.selectOne("Target.getTargetCnt", target);
    }
    
    @Override
    public List<TargetVO> getTargetDetailList(TargetVO target) throws BizException {
    	
        List<TargetVO> targetList = commonDao.selectList("Target.getTargetDetailList", target);

        return targetList;
    }
}
