/**
 *
 */
package com.offact.addys.service.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.master.SalesVO;

/**
 * @author
 */
public interface SalesService {
    /**
     * 매출현황 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<SalesVO> getSalesPageList(SalesVO sales) throws BizException;
    /**
     * 매출현황 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getSalesCnt(SalesVO sales) throws BizException;

    /**
     * 매출현황 상세목록조회
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public List<SalesVO> getSalesDetailPageList(SalesVO sales) throws BizException;
    
    /**
     * 매출현황 상세 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getSalesDetailCnt(SalesVO stock) throws BizException;
    /**
     * 매출현황 업로드
     * 
     * @param StockMasterVO
     * @return
     * @throws BizException
     */
    public abstract Map regiExcelUpload(List<SalesVO> paramList , SalesVO stock)
    	    throws BizException;
    


}
