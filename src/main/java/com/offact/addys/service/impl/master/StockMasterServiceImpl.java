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
import com.offact.addys.service.master.StockMasterService;
import com.offact.addys.vo.master.StockMasterVO;

/**
 * @author 4530
 */
@Service
public class StockMasterServiceImpl implements StockMasterService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<StockMasterVO> getStockList(StockMasterVO stockcondition) throws BizException {
    	
        List<StockMasterVO> stockMaster = commonDao.selectList("StockMaster.getStockList", stockcondition);

        return stockMaster;
    }

    public Map<Object, Object> safeRegiExcelUpload(List<StockMasterVO> excelUploadList)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();
    	    Map deleteMap = new HashMap();

    	    List rtnSuccessList = new ArrayList();
    	    List rtnErrorList = new ArrayList();
    	    
    	    deleteMap.put("updateUserId", "system");  //기존 데이타 삭제필드 업데이트
    	    this.commonDao.update("StockMaster.stockDeleteAll", deleteMap);

    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	      
    	      try 
    	      {
    	        
    	    	idx = i + 2;
    	    	StockMasterVO stockMasterVO = (StockMasterVO)excelUploadList.get(i);
    	    	stockMasterVO.setErrMsg("");
                this.commonDao.insert("StockMaster.insertExcelSafe", stockMasterVO);
                rtnSuccessList.add(stockMasterVO);
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((StockMasterVO)excelUploadList.get(i)).setErrMsg(((StockMasterVO)excelUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorList.add((StockMasterVO)excelUploadList.get(i));
    	        
    	        this.logger.debug("[key1]:"+ ((StockMasterVO)excelUploadList.get(i)).getProductCode()+"[key2]:"+ ((StockMasterVO)excelUploadList.get(i)).getGroupId()+" [msg] : " + ((StockMasterVO)excelUploadList.get(i)).getErrMsg());
    	        
    	      }
    	    
    	    }

    	    rtnMap.put("rtnSuccessList", rtnSuccessList);
    	    rtnMap.put("rtnErrorList", rtnErrorList);

    	    return rtnMap;
    	  }
    
    public Map<Object, Object> holdRegiExcelUpload(List<StockMasterVO> excelUploadList)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();
    	    Map deleteMap = new HashMap();

    	    List rtnSuccessList = new ArrayList();
    	    List rtnErrorList = new ArrayList();
    	    
    	    deleteMap.put("updateUserId", "system");  //기존 데이타 삭제필드 업데이트
    	    this.commonDao.update("StockMaster.stockDeleteAll", deleteMap);

    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	      
    	      try 
    	      {
    	        
    	    	idx = i + 2;
    	    	StockMasterVO stockMasterVO = (StockMasterVO)excelUploadList.get(i);
    	    	stockMasterVO.setErrMsg("");
                this.commonDao.insert("StockMaster.insertExcelHold", stockMasterVO);
                rtnSuccessList.add(stockMasterVO);
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((StockMasterVO)excelUploadList.get(i)).setErrMsg(((StockMasterVO)excelUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorList.add((StockMasterVO)excelUploadList.get(i));
    	        
    	        this.logger.debug("[key1]:"+ ((StockMasterVO)excelUploadList.get(i)).getProductCode()+"[key2]:"+ ((StockMasterVO)excelUploadList.get(i)).getGroupId()+" [msg] : " + ((StockMasterVO)excelUploadList.get(i)).getErrMsg());
    	        
    	      }
    	    
    	    }

    	    rtnMap.put("rtnSuccessList", rtnSuccessList);
    	    rtnMap.put("rtnErrorList", rtnErrorList);

    	    return rtnMap;
    	  }

}
