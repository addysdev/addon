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
import com.offact.addys.service.master.SalesService;
import com.offact.addys.vo.master.SalesVO;

/**
 * @author 4530
 */
@Service
public class SalesServiceImpl implements SalesService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<SalesVO> getSalesPageList(SalesVO sales) throws BizException {
    	
        List<SalesVO> salesList = commonDao.selectList("Sales.getSalesPageList", sales);

        return salesList;
    }

    @Override
    public int getSalesCnt(SalesVO sales) throws BizException {
        return commonDao.selectOne("Sales.getSalesCnt", sales);
    }

    @Override
    public List<SalesVO> getSalesDetailPageList(SalesVO sales) throws BizException {
    	
    	List<SalesVO> salesList = commonDao.selectList("Sales.getSalesDetailPageList", sales);

        return salesList;
    }
    
    @Override
    public int getSalesDetailCnt(SalesVO sales) throws BizException {
        return commonDao.selectOne("Sales.getSalesDetailCnt", sales);
    }
   
    public Map<Object, Object> regiExcelUpload(List<SalesVO> excelUploadList ,SalesVO salesTotal)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();

    	    List rtnSuccessList = new ArrayList();
    	    List rtnErrorList = new ArrayList();
    	    
    	    this.commonDao.delete("Sales.salesDeleteAll", salesTotal);
    	    this.commonDao.insert("Sales.insertSales", salesTotal);
    	    
    	    this.commonDao.delete("Sales.salesDetailDeleteAll", salesTotal);

    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	      
    	      try 
    	      {
    	        
    	    	idx = i + 2;
    	    	SalesVO salesrVO = (SalesVO)excelUploadList.get(i);
    	    	salesrVO.setErrMsg("");
                this.commonDao.insert("Sales.insertExcelSalesdDetail", salesrVO);
                rtnSuccessList.add(salesrVO);
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((SalesVO)excelUploadList.get(i)).setErrMsg(((SalesVO)excelUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorList.add((SalesVO)excelUploadList.get(i));
    	        
    	        this.logger.debug("[key]:"+ ((SalesVO)excelUploadList.get(i)).getProductCode()+" [msg] : " + ((SalesVO)excelUploadList.get(i)).getErrMsg());
    	        
    	      }
    	    
    	    }

    	    rtnMap.put("rtnSuccessList", rtnSuccessList);
    	    rtnMap.put("rtnErrorList", rtnErrorList);

    	    return rtnMap;
    	  }

}
