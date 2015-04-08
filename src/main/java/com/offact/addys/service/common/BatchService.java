/**
 *
 */
package com.offact.addys.service.common;

import org.json.simple.JSONArray;

import com.offact.framework.exception.BizException;

/**
 * @author 4530
 *
 */
public interface BatchService {

	public void deleteTbPreCategory() throws BizException;

	public void insertTbCategory(JSONArray arrayGetAllCategory) throws BizException;

	public void insertTbCconsultCategory() throws BizException;


	public void deleteInsertTbDeal(JSONArray arrayDeal) throws BizException;

	public int regiConsultExile() throws BizException;


	public int regiFaqCategory() throws BizException;



}
