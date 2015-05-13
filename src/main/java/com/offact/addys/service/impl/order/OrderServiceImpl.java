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
import com.offact.addys.service.order.OrderService;
import com.offact.addys.vo.order.OrderVO;
import com.offact.addys.vo.order.TargetVO;

/**
 * @author 4530
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<OrderVO> getOrderPageList(OrderVO order) throws BizException {
    	
        List<OrderVO> orderList = commonDao.selectList("Order.getOrderPageList", order);

        return orderList;
    }

    @Override
    public int getOrderCnt(OrderVO order) throws BizException {
        return commonDao.selectOne("Order.getOrderCnt", order);
    }
    
    @Override
    public OrderVO getOrderDetail(OrderVO order) throws BizException {
    	
    	OrderVO orderDetail = commonDao.selectOne("Order.getOrderDetail", order);

        return orderDetail;
    }
    @Override
    public List<OrderVO> getOrderDetailList(OrderVO order) throws BizException {
    	
        List<OrderVO> orderList = commonDao.selectList("Order.getOrderDetailList", order);

        return orderList;
    }
    @Override
    public int regiDeferProcess(String[] deferList ,OrderVO orderVo,String arrCheckProductId)
    	    throws BizException
	{
	    int retVal=-1;
	    
	    try{//보류사유 등록 /보류처리
	
	    	this.commonDao.insert("Order.deferReasonInsert", orderVo);
	    	this.commonDao.update("Order.updateDefer", orderVo);
	
	    	String[] r_data=null;
	
		    for(int i=0;i<deferList.length;i++){
	
		        r_data = StringUtil.getTokens(deferList[i], "|");
		        
		        OrderVO orderDetailVo = new OrderVO();
		    	
		        orderDetailVo.setOrderCode(orderVo.getOrderCode());
		        orderDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
		        orderDetailVo.setBarCode(StringUtil.nvl(r_data[1],""));
		        orderDetailVo.setOrderResultPrice(StringUtil.nvl(r_data[2],""));
		        orderDetailVo.setOrderResultCnt(StringUtil.nvl(r_data[3],""));
		        orderDetailVo.setOrderVatRate(StringUtil.nvl(r_data[4],""));
		        orderDetailVo.setEtc(StringUtil.nvl(r_data[5],""));
		        orderDetailVo.setUpdateUserId(orderVo.getDeferUserId());
		    	
	            retVal=this.commonDao.update("Order.updateDeferDetail", orderDetailVo);
		      
		      }
	
		      //검수대상품목 리스트 삭제 업데이트
		      this.commonDao.update("Order.checkDeletesProc", orderVo);
	    
		      arrCheckProductId = arrCheckProductId.substring(0, arrCheckProductId.lastIndexOf("^"));
		      String[] arrCheckId = arrCheckProductId.split("\\^");

		      for (int i = 0; i < arrCheckId.length; i++) {
		    	Map updateMap = new HashMap();
	
		    	updateMap.put("orderCode", orderVo.getOrderCode());
		    	updateMap.put("productCode", arrCheckId[i]);
	        
		    	this.commonDao.update("Order.checkUpdateProc", updateMap);
	
		      }
		    
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
   }
}
