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
import com.offact.addys.service.smart.AsService;
import com.offact.addys.vo.smart.AsVO;

/**
 * @author 4530
 */
@Service
public class AsServiceImpl implements AsService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<AsVO> getAsList(AsVO as) throws BizException {
    	
        List<AsVO> aslList = commonDao.selectList("As.getAsPageList", as);

        return aslList;
    }

    @Override
    public int getAsCnt(AsVO as) throws BizException {
        return commonDao.selectOne("As.getAsCnt", as);
    }

    @Override
    public AsVO getAsDetail(String idx) throws BizException {
    	        
    	AsVO asDetailVO = commonDao.selectOne("As.getAsDetail", idx);

        return asDetailVO;
    }

    @Override
    public int asProc(AsVO as) throws BizException {
        //상담처리
    	
    	int retval=0;
    	
    	retval=commonDao.update("As.asStateUpdate", as);
    	
    	retval=commonDao.insert("As.asResultInsert", as);
    	
    	return retval;

    }
    
    @Override
    public int asStateUpdate(AsVO as) throws BizException {
        //상담처리
    	
    	int retval=0;
    	
    	retval=commonDao.update("As.asStateUpdate", as);
    	
    	return retval;

    }
    
    @Override
    public List<AsVO> getAsReply(AsVO as) throws BizException {
    	
        List<AsVO> asList = commonDao.selectList("As.getAsReply", as);

        return asList;
    }

    @Override
    public int regiReplyInsert(AsVO as) throws BizException {
        //상담처리
    	
    	int retval=0;

    	retval=commonDao.insert("As.regiReplyInsert", as);
    	
    	return retval;

    }

}
