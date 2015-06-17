package com.offact.addys.service.impl.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.analysis.GmroiService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.analysis.GmroiVO;

/**
 * @author 4530
 */
@Service
public class GmroiServiceImpl implements GmroiService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

   @Override
   public List<GmroiVO> getGmroiPageList(GmroiVO gmroi) throws BizException {
   	
       List<GmroiVO> gmroiList = commonDao.selectList("Gmroi.getGmroiPageList", gmroi);

       return gmroiList;
   }

   @Override
   public int getGmroiCnt(GmroiVO gmroi) throws BizException {
       return commonDao.selectOne("Gmroi.getGmroiCnt", gmroi);
   }
}
