/**
 *
 */
package com.offact.addys.service.analysis;

import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.analysis.HoldStockVO;
/**
 * @author 4530
 */
public interface HoldStockService {
    
    /**
     * 보유재고 추천 조회
     * 
     * @return
     * @throws BizException
     */
    public List<HoldStockVO> getHoldStockPageList(HoldStockVO holdstock) throws BizException;

    /**
     * 보유재고 추천 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getHoldStockCnt(HoldStockVO holdstock) throws BizException;
    
    /**
     * 보유재고 총금액
     * 
     * @return
     * @throws BizException
     */
    public HoldStockVO getTotalHoldPrice(HoldStockVO holdstock) throws BizException;
    
    /**
     * 보유재고 추천 일괄업데이트
     * 
     * @return
     * @throws BizException
     */
    public int holdStockUpdatesProc(HoldStockVO holdstock) throws BizException;
    
    /**
     * 보유재고 추천 일괄업데이트
     * 
     * @return
     * @throws BizException
     */
    public int holdStockUpdateProc(HoldStockVO holdstock) throws BizException;
   
    /**
     * 보유재고 추천 조회(EXCEL)
     * 
     * @return
     * @throws BizException
     */
    public List<HoldStockVO> getHoldStockList(HoldStockVO holdstock) throws BizException;
}
