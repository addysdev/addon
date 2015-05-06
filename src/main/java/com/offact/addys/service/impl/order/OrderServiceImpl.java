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
    
}
