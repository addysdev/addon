/**
 *
 */
package com.offact.addys.service.impl.smart;

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
import com.offact.addys.service.smart.AsHistoryService;
import com.offact.addys.vo.order.TargetVO;
import com.offact.addys.vo.smart.AsHistoryVO;

/**
 * @author 4530
 */
@Service
public class AsHistoryServiceImpl implements AsHistoryService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<AsHistoryVO> getAsHistoryList(AsHistoryVO as) throws BizException {
    	
        List<AsHistoryVO> aslList = commonDao.selectList("AsHistory.getAsHistoryList", as);

        return aslList;
    }

}
