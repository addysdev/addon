/**
 *
 */
package com.offact.addys.service.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.master.OrderLimitVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.recovery.RecoveryVO;

/**
 * @author
 */
public interface OrderLimitService {
    /**
     * 발주제한 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<OrderLimitVO> getOrderLimitPageList(OrderLimitVO orderlimit) throws BizException;
    /**
     * 발주제한 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getOrderLimitCnt(OrderLimitVO orderlimit) throws BizException;

   
    /**
     * 발주제한 업로드
     * 
     * @param OrderLimitVO
     * @return
     * @throws BizException
     */
    public abstract Map regiExcelUpload(List<OrderLimitVO> paramList , OrderLimitVO orderlimit)
    	    throws BizException;
    
    /**
     * 제한업체 attach
     * 
     * @return
     * @throws BizException
     */
    public List<CompanyVO> getExcelAttach(List<CompanyVO> excelUploadList) throws BizException;
    
    /**
     * 발주제한 등록
     * 
     * @param OrderLimitVO
     * @return
     * @throws BizException
     */
    public int regiOrderLimitRegist(OrderLimitVO orderlimt, String arrCheckGroupId ,String arrSelectCompanyCode)
    	    throws BizException;
    
    /**
     * 발주제한 해제
     * 
     * @return
     * @throws BizException
     */
    public int orderLimitCance(OrderLimitVO orderlimt) throws BizException;
}
