/**
 *
 */
package com.offact.addys.service.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.master.OrderAddVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.recovery.RecoveryVO;

/**
 * @author
 */
public interface OrderAddService {
    /**
     * 발주제한 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<OrderAddVO> getOrderAddPageList(OrderAddVO orderadd) throws BizException;
    /**
     * 발주제한 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getOrderAddCnt(OrderAddVO orderadd) throws BizException;

   
    /**
     * 발주제한 업로드
     * 
     * @param OrderAddVO
     * @return
     * @throws BizException
     */
    public abstract Map regiExcelUpload(List<OrderAddVO> paramList , OrderAddVO orderadd)
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
     * @param OrderAddVO
     * @return
     * @throws BizException
     */
    public int regiOrderAddRegist(OrderAddVO orderadd, String arrCheckGroupId ,String arrSelectCompanyCode)
    	    throws BizException;
    
    /**
     * 발주제한 해제
     * 
     * @return
     * @throws BizException
     */
    public int orderAddCance(OrderAddVO orderadd) throws BizException;
}
