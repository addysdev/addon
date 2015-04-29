/**
 *
 */
package com.offact.addys.service.impl.manage;

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
import com.offact.addys.service.manage.ProductMasterService;
import com.offact.addys.vo.manage.ProductMasterVO;

/**
 * @author 4530
 */
@Service
public class ProductMasterServiceImpl implements ProductMasterService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ProductMasterVO> getProductList(ProductMasterVO productcondition) throws BizException {
    	
        List<ProductMasterVO> productMaster = commonDao.selectList("ProductMaster.getProductPageList", productcondition);

        return productMaster;
    }

    @Override
    public int getProductCnt(ProductMasterVO productcondition) throws BizException {
        return commonDao.selectOne("ProductMaster.getProductCnt", productcondition);
    }

    @Override
    public ProductMasterVO getProductDetail(String productCode) throws BizException {
    	
    	ProductMasterVO productDetailVO = commonDao.selectOne("ProductMaster.getProductDetail", productCode);

        return productDetailVO;
    }
   
    public Map<Object, Object> regiExcelUpload(List<ProductMasterVO> productUploadList)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();

    	    List rtnErrorUserVOList = new ArrayList();
    	    List rtnSuccessUserVOList = new ArrayList();

    	    Boolean errFg = Boolean.valueOf(false);

    	    int idx = 0;

    	    for (int i = 0; i < productUploadList.size(); i++) {
    	      errFg = Boolean.valueOf(false);
    	      try {
    	        idx = i + 2;
    	        ProductMasterVO productMasterVO = (ProductMasterVO)productUploadList.get(i);
    	        productMasterVO.setErrMsg("");

    	        ProductMasterVO validationUserVO = (ProductMasterVO)this.commonDao.selectOne("ProductMaster.validationUploadFile", productMasterVO);

    	        String lineStr = "";
    	        if (!validationUserVO.getUserResult().equals("SUCCESS")) {
    	          errFg = Boolean.valueOf(true);
    	          if (!validationUserVO.getErrMsg().equals(""))
    	            lineStr = "\n\r";
    	          else {
    	            lineStr = "";
    	          }
    	          validationUserVO.setErrMsg(validationUserVO.getErrMsg() + lineStr + "(" + idx + ")사용자 정보가 유효 하지 않습니다.");
    	          System.out.println("ProductMasterVO.getErrMsg() : " + validationUserVO.getErrMsg());
    	        }

    	        if (!errFg.booleanValue()) {
    	          rtnSuccessUserVOList.add(productMasterVO);
    	          this.commonDao.insert("UserManage.insertExcelUser", productMasterVO);
    	        } else {
    	          rtnErrorUserVOList.add(productMasterVO);
    	        }
    	      }
    	      catch (Exception e) {
    	        e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((ProductMasterVO)productUploadList.get(i)).setErrMsg(((ProductMasterVO)productUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorUserVOList.add((ProductMasterVO)productUploadList.get(i));
    	      }
    	    }

    	    rtnMap.put("rtnErrorUserVOList", rtnErrorUserVOList);
    	    rtnMap.put("rtnSuccessUserVOList", rtnSuccessUserVOList);

    	    return rtnMap;
    	  }

}
