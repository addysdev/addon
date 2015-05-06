/**
 *
 */
package com.offact.addys.service.impl.recovery;

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
import com.offact.addys.service.recovery.RecoveryService;
import com.offact.addys.vo.recovery.RecoveryVO;

/**
 * @author 4530
 */
@Service
public class RecoveryServiceImpl implements RecoveryService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<RecoveryVO> getRecoveryPageList(RecoveryVO recovery) throws BizException {
    	
        List<RecoveryVO> recoveryList = commonDao.selectList("Recovery.getRecoveryPageList", recovery);

        return recoveryList;
    }

    @Override
    public int getRecoveryCnt(RecoveryVO recovery) throws BizException {
        return commonDao.selectOne("Recovery.getRecoveryCnt", recovery);
    }
    
}
