/**
 *
 */
package com.offact.addys.service.impl.ad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ad.ADDealByUserService;
import com.offact.addys.vo.DealVO;

/**
 * @author HSH
 * @since 2014.02.19
 */

@Service
public class ADDealByUserServiceServiceImpl implements ADDealByUserService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

	/**
	 * 딜정보 리스트 조회
	 */
	@Override
	public List<DealVO> getDealByuserList(DealVO deal) throws BizException {
		return commonDao.selectList("Deal.getDealByuserList", deal);
	}


	/**
	 * 딜정보 카운트 조회
	 */
	@Override
	public int getDealByuserCnt(DealVO deal) throws BizException {
		return commonDao.selectOne("Deal.getDealByuserCnt", deal);
	}
	@Override
	public List<DealVO> getCurrDealByUser(DealVO deal) throws BizException {
		return commonDao.selectList("Deal.getCurrDealByUser", deal);
	}

	@Override
	public void saveDealByUser(String dealId,String dealByUserId,String regiUuserId) throws BizException {
		dealId = dealId.substring(0,dealId.lastIndexOf("^")) ;

		if(!dealByUserId.equals("") ){
			dealByUserId = dealByUserId.substring(0,dealByUserId.lastIndexOf("^")) ;
		}


		String[] arrDealId = dealId.split("\\^");
		String[] arrDealByUserId = dealByUserId.trim().split("\\^");


		for(int i=0 ; i < arrDealId.length ; i++){
			Map deleteMap = new HashMap<>();

			deleteMap.put("dealId", arrDealId[i]);
			commonDao.delete("Deal.deleteDealByUser", deleteMap);


			for(int j=0;j < arrDealByUserId.length ; j++){

				if(!arrDealByUserId[j].equals("")){
					Map insertMap = new HashMap<>();
					insertMap.put("dealId", arrDealId[i]);
					insertMap.put("dealByUserId", arrDealByUserId[j]);

					insertMap.put("regiUuserId", regiUuserId);



					commonDao.insert("Deal.insertDealByUser", insertMap);

				}

			}
		}
	}

	/**
	 * 딜번호로 딜 카테고리정보 가져오기.
	 */
	@Override
	public DealVO getDealCategoryNm(DealVO deal) throws BizException {
		return commonDao.selectOne("Deal.getDealCategoryNm", deal);
	}




}
