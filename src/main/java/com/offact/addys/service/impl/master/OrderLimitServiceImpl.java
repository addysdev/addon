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
import com.offact.addys.service.master.OrderLimitService;
import com.offact.addys.vo.master.OrderLimitVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.recovery.RecoveryVO;
import com.offact.addys.vo.common.CompanyVO;

/**
 * @author 4530
 */
@Service
public class OrderLimitServiceImpl implements OrderLimitService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<OrderLimitVO> getOrderLimitPageList(OrderLimitVO orderlimit) throws BizException {
    	
        List<OrderLimitVO> orderlimitList = commonDao.selectList("OrderLimit.getOrderLimitPageList", orderlimit);

        return orderlimitList;
    }

    @Override
    public int getOrderLimitCnt(OrderLimitVO orderlimit) throws BizException {
        return commonDao.selectOne("OrderLimit.getOrderLimitCnt", orderlimit);
    }

   
    public Map<Object, Object> regiExcelUpload(List<OrderLimitVO> excelUploadList ,OrderLimitVO orderlimit)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();

    	    List rtnSuccessList = new ArrayList();
    	    List rtnErrorList = new ArrayList();
    	    
    	    this.commonDao.delete("Stock.stockDeleteAll", orderlimit);
    	    this.commonDao.insert("Stock.insertStock", orderlimit);
    	    
    	    this.commonDao.delete("Stock.stockDetailDeleteAll", orderlimit);

    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	      
    	      try 
    	      {
    	        
    	    	idx = i + 2;
    	    	OrderLimitVO stockVO = (OrderLimitVO)excelUploadList.get(i);
    	    	stockVO.setErrMsg("");
                this.commonDao.insert("Stock.insertExcelStockdDetail", stockVO);
                rtnSuccessList.add(stockVO);
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((OrderLimitVO)excelUploadList.get(i)).setErrMsg(((OrderLimitVO)excelUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorList.add((OrderLimitVO)excelUploadList.get(i));
    	        
    	      //  this.logger.debug("[key]:"+ ((OrderLimitVO)excelUploadList.get(i)).getProductCode()+" [msg] : " + ((OrderLimitVO)excelUploadList.get(i)).getErrMsg());
    	        
    	      }
    	    
    	    }

    	    rtnMap.put("rtnSuccessList", rtnSuccessList);
    	    rtnMap.put("rtnErrorList", rtnErrorList);

    	    return rtnMap;
    	  }
    
    public List<CompanyVO> getExcelAttach(List<CompanyVO> excelUploadList)
    	    throws BizException
    	  {

    	    List companyAttachList = new ArrayList();
    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	      
    	      try 
    	      {
    	        
    	    	idx = i + 2;
    	    	CompanyVO companyVO = (CompanyVO)excelUploadList.get(i);
    	    	
    	    	companyVO=this.commonDao.selectOne("Company.getCompany", companyVO);
    	    	
    	    	if(null!=companyVO){
    	    		companyAttachList.add(companyVO);
    	    	}
    	    	
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	       
    	      }
    	    
    	    }

    	    return companyAttachList;
    	  }
    
    @Override
    public int regiOrderLimitRegist(OrderLimitVO orderlimt, String arrCheckGroupId ,String arrSelectCompanyCode)
    	    throws BizException
	{
	    int retVal=-1;
	    
	    try{//제한 등록
	    	
	    	arrCheckGroupId = arrCheckGroupId.substring(0, arrCheckGroupId.lastIndexOf("^"));
		    String[] arrGroupId = arrCheckGroupId.split("\\^");
	    	
		    for (int i = 0; i < arrGroupId.length; i++) {
		    	
		    	long t1 = System.currentTimeMillis();
		    	String groupId=arrGroupId[i];

		    	String arrGroupSelectCompanyCode = arrSelectCompanyCode.substring(0, arrSelectCompanyCode.lastIndexOf("^"));
			    String[] arrGroupCompanyCode = arrGroupSelectCompanyCode.split("\\^");
			    
			    for (int j = 0; j < arrGroupCompanyCode.length; j++) {
			    	
			    	String companyCode=arrGroupCompanyCode[j];
			    	
			    	OrderLimitVO orderLimitVO = new OrderLimitVO();
			    	
			    	orderLimitVO.setLimitCode(groupId+companyCode);
			    	orderLimitVO.setGroupId(groupId);
			    	orderLimitVO.setCompanyCode(companyCode);
			    	orderLimitVO.setLimitStartDate(orderlimt.getLimitStartDate());
			    	orderLimitVO.setLimitEndDate(orderlimt.getLimitEndDate());
			    	orderLimitVO.setLimitUserId(orderlimt.getLimitUserId());
			    	
	
			    	retVal=this.commonDao.insert("OrderLimit.orderLimitInsert", orderLimitVO);
			    	
			    }
	
		    }
		    
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
   }
    
    @Override
    public int orderLimitCance(OrderLimitVO orderlimt) throws BizException {
        // 사용자 상세정보 수정
	
    	return commonDao.update("OrderLimit.orderLimitCance", orderlimt);

    }

}
