/**
 *
 */
package com.offact.addys.service.impl.order;

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
import com.offact.addys.service.order.TargetService;
import com.offact.addys.vo.master.StockVO;
import com.offact.addys.vo.order.OrderVO;
import com.offact.addys.vo.order.TargetVO;

/**
 * @author 4530
 */
@Service
public class TargetServiceImpl implements TargetService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<TargetVO> getTargetPageList(TargetVO target) throws BizException {
    	
        List<TargetVO> targetList = commonDao.selectList("Target.getTargetPageList", target);

        return targetList;
    }

    @Override
    public int getTargetCnt(TargetVO target) throws BizException {
        return commonDao.selectOne("Target.getTargetCnt", target);
    }
    
    @Override
    public List<TargetVO> getTargetDetailList(TargetVO target) throws BizException {
    	
        List<TargetVO> targetList = commonDao.selectList("Target.getTargetDetailList", target);

        return targetList;
    }
    @Override
    public int regiDeferProcess(String[] deferList ,TargetVO targetVo,String arrDeferProductId)
    	    throws BizException
	{
	    int retVal=-1;
	    
	    try{//보류사유 등록 /보류처리
	
	    	this.commonDao.insert("Target.deferReasonInsert", targetVo);
	    	this.commonDao.insert("Target.insertDefer", targetVo);
	
	    	String[] r_data=null;
	
		    for(int i=0;i<deferList.length;i++){
	
		        r_data = StringUtil.getTokens(deferList[i], "|");
		        
		        TargetVO targetDetailVo = new TargetVO();
		    	
		    	targetDetailVo.setOrderCode(targetVo.getOrderCode());
		    	targetDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
		    	targetDetailVo.setProductName(StringUtil.nvl(r_data[1],""));
		    	targetDetailVo.setProductPrice(StringUtil.nvl(r_data[2],""));
		    	targetDetailVo.setOrderCnt(StringUtil.nvl(r_data[3],""));
		    	targetDetailVo.setAddCnt(StringUtil.nvl(r_data[4],""));
		    	targetDetailVo.setLossCnt(StringUtil.nvl(r_data[5],""));
		    	targetDetailVo.setSafeStock(StringUtil.nvl(r_data[6],""));
		    	targetDetailVo.setHoldStock(StringUtil.nvl(r_data[7],""));
		    	targetDetailVo.setStockCnt(StringUtil.nvl(r_data[8],""));
		    	targetDetailVo.setStockDate(StringUtil.nvl(r_data[9],""));
		    	targetDetailVo.setVatRate(StringUtil.nvl(r_data[10],""));
		    	targetDetailVo.setEtc(StringUtil.nvl(r_data[11],""));
		    	targetDetailVo.setMinusCnt(StringUtil.nvl(r_data[12],""));
		    	targetDetailVo.setCreateUserId(targetVo.getDeferUserId());
		    	
	            retVal=this.commonDao.insert("Target.insertDeferDetail", targetDetailVo);
		      
		      }
	
		      //바주대상품목 리스트 삭제 업데이트
		      this.commonDao.update("Target.deferDeletesProc", targetVo);
	    
		      arrDeferProductId = arrDeferProductId.substring(0, arrDeferProductId.lastIndexOf("^"));
		      String[] arrDeferId = arrDeferProductId.split("\\^");
	      
		    int deferRetVal=0;
	
		    for (int i = 0; i < arrDeferId.length; i++) {
		    	Map updateMap = new HashMap();
	
		    	updateMap.put("orderCode", targetVo.getOrderCode());
		    	updateMap.put("productCode", arrDeferId[i]);
	        
		    	retVal=this.commonDao.update("Target.deferUpdateProc", updateMap);
	
		    }
		    
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
   }
   @Override
  public int regiOrderProcess(String[] orderList ,TargetVO targetVo)
    	    throws BizException
  {
	    Map rtnMap = new HashMap();
	    int retVal=-1;
   
	    try{
	    	//주문처리
		    this.commonDao.insert("Target.insertOrder", targetVo);
	
		    String[] r_data=null;
		   
		    int idx = 0;
		    
		    for(int i=0;i<orderList.length;i++){
		
		        r_data = StringUtil.getTokens(orderList[i], "|");
		    
		        if(r_data[13].equals("true")){
		        
				    TargetVO targetDetailVo = new TargetVO();
					
					targetDetailVo.setOrderCode(targetVo.getOrderCode());
					targetDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
					targetDetailVo.setProductName(StringUtil.nvl(r_data[1],""));
					targetDetailVo.setProductPrice(StringUtil.nvl(r_data[2],""));
					targetDetailVo.setOrderCnt(StringUtil.nvl(r_data[3],""));
					targetDetailVo.setAddCnt(StringUtil.nvl(r_data[4],""));
					targetDetailVo.setLossCnt(StringUtil.nvl(r_data[5],""));
					targetDetailVo.setSafeStock(StringUtil.nvl(r_data[6],""));
					targetDetailVo.setHoldStock(StringUtil.nvl(r_data[7],""));
					targetDetailVo.setStockCnt(StringUtil.nvl(r_data[8],""));
					targetDetailVo.setStockDate(StringUtil.nvl(r_data[9],""));
					targetDetailVo.setVatRate(StringUtil.nvl(r_data[10],""));
			    	targetDetailVo.setEtc(StringUtil.nvl(r_data[11],""));
			    	targetDetailVo.setMinusCnt(StringUtil.nvl(r_data[14],""));
					targetDetailVo.setCreateUserId(targetVo.getOrderUserId());
					targetDetailVo.setOrderCheck(StringUtil.nvl(r_data[13],"true"));
					
				    retVal=this.commonDao.insert("Target.insertOrderDetail", targetDetailVo);
		        }
			
			}
			    
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());
	
	    }
	
	    return retVal;
	  
	  }
   
   @Override
   public TargetVO getDeferDetail(TargetVO defer) throws BizException {
   	
	   TargetVO deferDetail = commonDao.selectOne("Target.getDeferDetail", defer);

       return deferDetail;
   }
   @Override
   public List<TargetVO> getDeferDetailList(TargetVO defer) throws BizException {
   	
       List<TargetVO> deferList = commonDao.selectList("Target.getDeferDetailList", defer);

       return deferList;
   }
   @Override
   public int regiDeferCancel(TargetVO targetVo)
   	    throws BizException
	{
	    int retVal=-1;
	    
	    try{//보류사유 등록 /보류처리
	
	    	this.commonDao.insert("Target.deferReasonInsert", targetVo);
	    	retVal=this.commonDao.update("Target.updateDefer", targetVo);
	    	this.commonDao.update("Target.updateDeferDetail", targetVo);

	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
  }
   @Override
   public TargetVO getStateCnt(TargetVO targetCon) throws BizException {
   	
	   TargetVO stateVO = commonDao.selectOne("Target.getStateCnt", targetCon);

       return stateVO;
   }
   
   @Override
   public TargetVO getOrderCode(TargetVO target) throws BizException {
       return commonDao.selectOne("Target.getOrderCode", target);
   }
}
