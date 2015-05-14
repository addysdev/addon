/**
 *
 */
package com.offact.addys.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.order.OrderVO;
import com.offact.addys.vo.order.TargetVO;

/**
 * @author
 */
public interface OrderService {
    /**
     * 발주대상 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<OrderVO> getOrderPageList(OrderVO order) throws BizException;
    /**
     * 발주대상 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getOrderCnt(OrderVO order) throws BizException;

    /**
     * 검수대상 상세조회
     * 
     * @return
     * @throws BizException
     */
    public OrderVO getOrderDetail(OrderVO order) throws BizException;
    
    /**
     * 검수대상 상세목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<OrderVO> getOrderDetailList(OrderVO order) throws BizException;
    
    /**
     * 발주 보류처리
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiDeferProcess(String[] deferlist , OrderVO order, String arrCheckProductId)
    	    throws BizException;
    /**
     * 발주 보류폐기
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiDeferCancel(OrderVO order)
    	    throws BizException;
    
    /**
     * 발주 취소
     * 
     * @param TargetVO
     * @return
     * @throws BizException
     */
    public int regiOrderCancel(OrderVO order)
    	    throws BizException;
}
