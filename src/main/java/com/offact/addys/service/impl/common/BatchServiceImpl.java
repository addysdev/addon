package com.offact.addys.service.impl.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.common.BatchService;
import com.offact.addys.vo.ad.ADCategoryVO;

/**
 * @author 4530
 *
 */
@Service
public class BatchServiceImpl implements BatchService {

    private final Logger 			batchloger = Logger.getLogger("batchlog");

	@Autowired
	private SqlSessionCommonDao commonDao;

	@Override
	public void deleteTbPreCategory() throws BizException {
		commonDao.delete("Batch.deleteTbPreCategory");
//		commonDao.delete("Batch.deleteTbCategory");

	}

	@Override
	public void insertTbCategory(JSONArray arrayGetAllCategory) throws BizException {
		Object arryObjGetAllCategory=null;
		for (int i=0;i<arrayGetAllCategory.size();i++){
			arryObjGetAllCategory = JSONValue.parse(arrayGetAllCategory.get(i).toString());
			batchloger.debug("INSERT : " + arryObjGetAllCategory);
            commonDao.insert("Batch.insertTbPreCategory",arryObjGetAllCategory);
		}
		batchloger.debug("EXECUTE QUERY : Batch.insertTbCategory");
		commonDao.insert("Batch.insertTbCategory");
	}

	@Override
	public void insertTbCconsultCategory() throws BizException {


		List<ADCategoryVO> categoryLList = commonDao.selectList("Batch.getAddLCetetory");
		for(int i=0;i<categoryLList.size();i++){
			ADCategoryVO  categoryVO = categoryLList.get(i);
			commonDao.insert("Batch.addtbConsultCategory",categoryVO);

		}



	}



	@Override
	public void deleteInsertTbDeal(JSONArray arrayDeal) throws BizException {
		Object arryObj=null;
		for (int i=0;i<arrayDeal.size();i++){
			arryObj = JSONValue.parse(arrayDeal.get(i).toString());
			batchloger.debug("INSERT : " + arryObj);
			  commonDao.insert("Batch.insertDeal",arryObj);
		}

	}



	@Override
	public int regiConsultExile() throws BizException {
			  return commonDao.insert("Batch.regiConsultExile");

	}



	@Override
	public int regiFaqCategory() throws BizException {
			  return commonDao.insert("Batch.regiFaqCategory");

	}







}
