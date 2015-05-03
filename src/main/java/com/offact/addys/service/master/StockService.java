/**
 *
 */
package com.offact.addys.service.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.master.StockVO;

/**
 * @author
 */
public interface StockService {
    /**
     * 재고현황 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<StockVO> getStockPageList(StockVO stock) throws BizException;
    /**
     * 재고현황 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getStockCnt(StockVO stock) throws BizException;

    /**
     * 재고현황 상세목록조회
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public List<StockVO> getStockDetailPageList(StockVO stock) throws BizException;
    
    /**
     * 재고현황 상세 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getStockDetailCnt(StockVO stock) throws BizException;
    /**
     * 재고현황 업로드
     * 
     * @param StockMasterVO
     * @return
     * @throws BizException
     */
    public abstract Map regiExcelUpload(List<StockVO> paramList , StockVO stock)
    	    throws BizException;
    


}
