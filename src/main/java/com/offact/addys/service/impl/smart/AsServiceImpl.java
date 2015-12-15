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
import com.offact.addys.vo.order.TargetVO;
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
    public int asResultInsert(AsVO as) throws BizException {
        //상담처리

    	return commonDao.insert("As.asResultInsert", as);

    }
    
    @Override
    public int asModifyUpdate(AsVO as) throws BizException {
        //상담처리
    	
    	int retVal=-1;
    	
    	retVal=commonDao.insert("As.asResultUpdate", as);
    	
    	String asImage=as.getAsImage();
    	String receiptImage=as.getReceiptImage();
    	
    	if(!asImage.equals("N")){
    		retVal=commonDao.insert("As.asImageUpdate", as);
    	}
    	
    	if(!receiptImage.equals("N")){
    		retVal=commonDao.insert("As.receiptImageUpdate", as);
    	}

    	return retVal;

    }
    
    @Override
    public int asTransUpdate(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;
    	
    	retVal=commonDao.update("As.asTransUpdate", as);
    	
    	retVal=commonDao.insert("As.asTransHistoryInsert", as);
    	
    	return retVal;

    }
    
    @Override
    public int asStateProc(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;
    	
    	retVal=commonDao.update("As.asStateProc", as);
    	
    	retVal=commonDao.insert("As.asTransHistoryInsert", as);
    	
    	return retVal;


    }
    
    @Override
    public int asMemoProc(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;

    	retVal=commonDao.insert("As.asTransHistoryInsert", as);
    	
    	return retVal;


    }
    
    @Override
    public int asCenterStart(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;
    	
    	retVal=commonDao.update("As.asCenterStart", as);
    	
    	retVal=commonDao.insert("As.asCenterHistoryInsert", as);
    	
    	return retVal;


    }

    
    @Override
    public int asReceiveState(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;
    	
    	retVal=commonDao.update("As.asReceiveProc", as);
    	
    	retVal=commonDao.insert("As.asTransHistoryInsert", as);
    	
    	return retVal;

    }
    
    @Override
    public int transUpdateProc(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;
    	
    	retVal=commonDao.update("As.asTransReUpdate", as);

    	return retVal;

    }
    
    @Override
    public int reTransUpdateProc(AsVO as) throws BizException {
        //상담처리

    	int retVal=-1;
    	
    	retVal=commonDao.update("As.reTransReUpdate", as);

    	return retVal;

    }

    
    
    @Override
    public int asRegistInsert(AsVO as) throws BizException {
        //상담처리
    	
    	if(as.getAsState().equals("03")){
    		return commonDao.insert("As.asReplaceInsert", as);
    	}else{
    		return commonDao.insert("As.asRegistInsert", as);
    	}
    	

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
    
    @Override
    public AsVO getAsNo(AsVO as) throws BizException {
        return commonDao.selectOne("As.getAsNo", as);
    }

}
