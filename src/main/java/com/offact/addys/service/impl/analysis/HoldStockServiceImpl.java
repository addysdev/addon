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
import com.offact.addys.service.analysis.HoldStockService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.analysis.HoldStockVO;

/**
 * @author 4530
 */
@Service
public class HoldStockServiceImpl implements HoldStockService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

   @Override
   public List<HoldStockVO> getHoldStockPageList(HoldStockVO holdstock) throws BizException {
   	
       List<HoldStockVO> holdStockList = commonDao.selectList("HoldStock.getHoldStockPageList", holdstock);

       return holdStockList;
   }

   @Override
   public int getHoldStockCnt(HoldStockVO holdstock) throws BizException {
       return commonDao.selectOne("HoldStock.getHoldStockCnt", holdstock);
   }
   
   @Override
   public HoldStockVO getTotalHoldPrice(HoldStockVO holdstock) throws BizException {
       return commonDao.selectOne("HoldStock.getTotalHoldPrice", holdstock);
   }
}
