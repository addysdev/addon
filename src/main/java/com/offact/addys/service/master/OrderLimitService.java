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
}
