/**
 *
 */
package com.offact.addys.service.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.master.StockMasterVO;

/**
 * @author
 */
public interface StockMasterService {
    /**
     * 재고 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<StockMasterVO> getStockList(StockMasterVO stock) throws BizException;

    /**
     * 안전재고 업로드
     * 
     * @param StockMasterVO
     * @return
     * @throws BizException
     */
    public abstract Map safeRegiExcelUpload(List<StockMasterVO> paramList)
    	    throws BizException;
    
    /**
     * 보유재고 업로드
     * 
     * @param StockMasterVO
     * @return
     * @throws BizException
     */
    public abstract Map holdRegiExcelUpload(List<StockMasterVO> paramList)
    	    throws BizException;

}
