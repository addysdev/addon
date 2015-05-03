/**
 *
 */
package com.offact.addys.service.impl.master;

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
import com.offact.addys.service.master.StockService;
import com.offact.addys.vo.master.StockVO;

/**
 * @author 4530
 */
@Service
public class StockServiceImpl implements StockService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<StockVO> getStockPageList(StockVO stock) throws BizException {
    	
        List<StockVO> stockList = commonDao.selectList("Stock.getStockPageList", stock);

        return stockList;
    }

    @Override
    public int getStockCnt(StockVO stock) throws BizException {
        return commonDao.selectOne("Stock.getStockCnt", stock);
    }

    @Override
    public List<StockVO> getStockDetailPageList(StockVO stock) throws BizException {
    	
    	List<StockVO> stockList = commonDao.selectList("Stock.getStockDetailPageList", stock);

        return stockList;
    }
    
    @Override
    public int getStockDetailCnt(StockVO stock) throws BizException {
        return commonDao.selectOne("Stock.getStockDetailCnt", stock);
    }
   
    public Map<Object, Object> regiExcelUpload(List<StockVO> excelUploadList ,StockVO stock)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();

    	    List rtnSuccessList = new ArrayList();
    	    List rtnErrorList = new ArrayList();
    	    
    	    this.commonDao.delete("Stock.stockDeleteAll", stock);
    	    this.commonDao.insert("Stock.insertStock", stock);
    	    
    	    this.commonDao.delete("Stock.stockDetailDeleteAll", stock);

    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	      
    	      try 
    	      {
    	        
    	    	idx = i + 2;
    	    	StockVO stockrVO = (StockVO)excelUploadList.get(i);
    	    	stockrVO.setErrMsg("");
                this.commonDao.insert("Stock.insertExcelStockdDetail", stock);
                rtnSuccessList.add(stockrVO);
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((StockVO)excelUploadList.get(i)).setErrMsg(((StockVO)excelUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorList.add((StockVO)excelUploadList.get(i));
    	        
    	        this.logger.debug("[key]:"+ ((StockVO)excelUploadList.get(i)).getProductCode()+" [msg] : " + ((StockVO)excelUploadList.get(i)).getErrMsg());
    	        
    	      }
    	    
    	    }

    	    rtnMap.put("rtnSuccessList", rtnSuccessList);
    	    rtnMap.put("rtnErrorList", rtnErrorList);

    	    return rtnMap;
    	  }

}
