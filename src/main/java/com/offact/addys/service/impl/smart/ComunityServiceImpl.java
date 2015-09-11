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
import com.offact.addys.service.smart.ComunityService;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.smart.ComunityVO;

/**
 * @author 4530
 */
@Service
public class ComunityServiceImpl implements ComunityService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ComunityVO> getComunityList(ComunityVO comunity) throws BizException {
    	
        List<ComunityVO> comunityList = commonDao.selectList("Comunity.getComunityPageList", comunity);

        return comunityList;
    }

    @Override
    public int getComunityCnt(ComunityVO comunity) throws BizException {
        return commonDao.selectOne("Comunity.getComunityCnt", comunity);
    }

    @Override
    public List<ComunityVO> getComunityReply(ComunityVO comunity) throws BizException {
    	
        List<ComunityVO> comunityList = commonDao.selectList("Comunity.getComunityReply", comunity);

        return comunityList;
    }

    @Override
    public int regiReplyInsert(ComunityVO comunity) throws BizException {
        //상담처리
    	
    	int retval=0;

    	retval=commonDao.insert("Comunity.regiReplyInsert", comunity);
    	
    	return retval;

    }
    
}
