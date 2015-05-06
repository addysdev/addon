/**
 *
 */
package com.offact.addys.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.order.OrderVO;

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

   
}
