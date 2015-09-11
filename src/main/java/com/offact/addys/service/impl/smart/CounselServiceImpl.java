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
import com.offact.addys.service.smart.CounselService;
import com.offact.addys.vo.smart.CounselVO;

/**
 * @author 4530
 */
@Service
public class CounselServiceImpl implements CounselService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<CounselVO> getCounselList(CounselVO counsel) throws BizException {
    	
        List<CounselVO> counselList = commonDao.selectList("Counsel.getCounselPageList", counsel);

        return counselList;
    }

    @Override
    public int getCounselCnt(CounselVO counsel) throws BizException {
        return commonDao.selectOne("Counsel.getCounselCnt", counsel);
    }

    @Override
    public CounselVO getCounselDetail(String idx) throws BizException {
    	
    	CounselVO counselDetailVO = commonDao.selectOne("Counsel.getCounselDetail", idx);

        return counselDetailVO;
    }

    @Override
    public int counselProc(CounselVO counsel) throws BizException {
        //상담처리
    	
    	int retval=0;
    	
    	retval=commonDao.update("Counsel.counselStateUpdate", counsel);
    	
    	retval=commonDao.insert("Counsel.counselResultInsert", counsel);
    	
    	return retval;

    }
    
    @Override
    public int counselStateUpdate(CounselVO counsel) throws BizException {
        //상담처리
    	
    	int retval=0;
    	
    	retval=commonDao.update("Counsel.counselStateUpdate", counsel);
    	
    	return retval;

    }

}
