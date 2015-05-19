/**
 *
 */
package com.offact.addys.service.impl.recovery;

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
import com.offact.addys.service.recovery.RecoveryService;
import com.offact.addys.vo.order.TargetVO;
import com.offact.addys.vo.recovery.RecoveryVO;

/**
 * @author 4530
 */
@Service
public class RecoveryServiceImpl implements RecoveryService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<RecoveryVO> getRecoveryPageList(RecoveryVO recovery) throws BizException {
    	
        List<RecoveryVO> recoveryList = commonDao.selectList("Recovery.getRecoveryPageList", recovery);

        return recoveryList;
    }

    @Override
    public int getRecoveryCnt(RecoveryVO recovery) throws BizException {
        return commonDao.selectOne("Recovery.getRecoveryCnt", recovery);
    }
    @Override
    public int regiRecoveryRegist(RecoveryVO recovery, String arrCheckGroupId ,String arrSelectProductId)
    	    throws BizException
	{
	    int retVal=-1;
	    
	    try{//회수 등록
	    	
	    	arrCheckGroupId = arrCheckGroupId.substring(0, arrCheckGroupId.lastIndexOf("^"));
		    String[] arrGroupId = arrCheckGroupId.split("\\^");
	    	
		    for (int i = 0; i < arrGroupId.length; i++) {
		    	
		    	long t1 = System.currentTimeMillis();
		    	String groupId=arrGroupId[i];
		    	String recoveryCode="R"+groupId+t1;
		    	
		    	RecoveryVO recoveryVO = new RecoveryVO();
		    	
		    	recoveryVO.setGroupId(groupId);
		    	recoveryVO.setRecoveryCode(recoveryCode);
		    	recoveryVO.setRecoveryClosingDate(recovery.getRecoveryClosingDate());
		    	recoveryVO.setRegUserId(recovery.getRegUserId());
		    	recoveryVO.setMemo(recovery.getMemo());
		    	
		    	retVal=this.commonDao.insert("Recovery.recoveryInsert", recoveryVO);
		    	
		    	String arrGroupSelectProductId = arrSelectProductId.substring(0, arrSelectProductId.lastIndexOf("^"));
			    String[] arrGroupProductCode = arrGroupSelectProductId.split("\\^");
			    
			    for (int j = 0; j < arrGroupProductCode.length; j++) {
			    	
			    	String productCode=arrGroupProductCode[j];
			    	
			    	RecoveryVO recoveryDetailVO = new RecoveryVO();
			    	
			    	recoveryDetailVO.setRecoveryCode(recoveryCode);
			    	recoveryDetailVO.setCreateUserId(recovery.getCreateUserId());
			    	recoveryDetailVO.setProductCode(productCode);
	
			    	this.commonDao.insert("Recovery.recoveryDetailInsert", recoveryDetailVO);
			    	
			    }
	
		    }
		    
		    String arrProductSelectProductId = arrSelectProductId.substring(0, arrSelectProductId.lastIndexOf("^"));
		    String[] arrProductCode = arrProductSelectProductId.split("\\^");
		    //회수품목 품목상태 업데이트
		    for (int k = 0; k < arrProductCode.length; k++) {
		    	Map updateMap = new HashMap();
	
		    	updateMap.put("updateUserId", recovery.getUpdateUserId());
		    	updateMap.put("productCode", arrProductCode[k]);
	        
		    	retVal=this.commonDao.update("ProductMaster.productRecoveryUpdate", updateMap);
	
		    }
	    	
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
   }
    @Override
    public RecoveryVO getRecoveryDetail(RecoveryVO recoveryCon) throws BizException {
        return commonDao.selectOne("Recovery.getRecoveryDetail", recoveryCon);
    }
    @Override
    public List<RecoveryVO> getRecoveryDetailList(RecoveryVO recovery) throws BizException {
    	
        List<RecoveryVO> recoveryList = commonDao.selectList("Recovery.getRecoveryDetailList", recovery);

        return recoveryList;
    }
    @Override
    public int regiRecoveryProcess(String[] recoveryList , RecoveryVO recoveryVo)
    	    throws BizException
	{
	    int retVal=-1;
	    
	    try{//회수처리
	
	    	this.commonDao.update("Recovery.recoveryProcUpdate", recoveryVo);
	
	    	String[] r_data=null;
	    	
		    for(int i=0;i<recoveryList.length;i++){
	
		        r_data = StringUtil.getTokens(recoveryList[i], "|");
		        
		        RecoveryVO recoveryDetailVo = new RecoveryVO();

		        recoveryDetailVo.setRecoveryCode(recoveryVo.getRecoveryCode());
		        recoveryDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
		        recoveryDetailVo.setStockDate(StringUtil.nvl(r_data[1],""));
		        recoveryDetailVo.setStockCnt(StringUtil.nvl(r_data[2],""));
		        recoveryDetailVo.setRecoveryCnt(StringUtil.nvl(r_data[3],""));
		        recoveryDetailVo.setAddCnt(StringUtil.nvl(r_data[4],""));
		    	recoveryDetailVo.setLossCnt(StringUtil.nvl(r_data[5],""));
		    	recoveryDetailVo.setEtc(StringUtil.nvl(r_data[6],""));
		    	recoveryDetailVo.setUpdateUserId(recoveryVo.getUpdateUserId());
		    	
	            retVal=this.commonDao.update("Recovery.recoveryDetailProcUpdate", recoveryDetailVo);
		      
		      }

	    /*
		      arrDeferProductId = arrDeferProductId.substring(0, arrDeferProductId.lastIndexOf("^"));
		      String[] arrDeferId = arrDeferProductId.split("\\^");
	      
		    int deferRetVal=0;
	
		    for (int i = 0; i < arrDeferId.length; i++) {
		    	Map updateMap = new HashMap();
	
		    	updateMap.put("orderCode", targetVo.getOrderCode());
		    	updateMap.put("productCode", arrDeferId[i]);
	        
		    	retVal=this.commonDao.update("Target.deferUpdateProc", updateMap);
	
		    }
		  */  
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
   }
    @Override
    public int regiRecoveryComplete(String[] recoveryList , RecoveryVO recoveryVo)
    	    throws BizException
	{
	    int retVal=-1;
	    
	    try{//회수처리
	
	    	this.commonDao.update("Recovery.recoveryCompleteUpdate", recoveryVo);
	
	    	String[] r_data=null;
	    	
		    for(int i=0;i<recoveryList.length;i++){
	
		        r_data = StringUtil.getTokens(recoveryList[i], "|");
		        
		        RecoveryVO recoveryDetailVo = new RecoveryVO();

		        recoveryDetailVo.setRecoveryCode(recoveryVo.getRecoveryCode());
		        recoveryDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
		        recoveryDetailVo.setRecoveryResultCnt(StringUtil.nvl(r_data[1],""));
		    	recoveryDetailVo.setUpdateUserId(recoveryVo.getUpdateUserId());
		    	
	            retVal=this.commonDao.update("Recovery.recoveryCompleteDetailProcUpdate", recoveryDetailVo);
		      
		      }

	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
   }
}
