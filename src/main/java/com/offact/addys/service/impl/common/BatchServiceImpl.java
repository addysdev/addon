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


}
