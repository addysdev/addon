package com.offact.addys.service.impl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.common.CommonService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.common.WorkVO;

/**
 * @author 4530
 */
@Service
public class CommonServiceImpl implements CommonService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;


   @Override
    public List<CodeVO> getCodeComboList(CodeVO code) throws BizException {
        List<CodeVO> codeList = commonDao.selectList("Code.getCodeComboList", code);

        return codeList;
    }
   

   @Override
    public List<GroupVO> getGroupComboList(GroupVO group) throws BizException {
        List<GroupVO> grolupList = commonDao.selectList("Group.getGroupComboList", group);

        return grolupList;
    }

   @Override
   public CompanyVO getCompanyDetail(CompanyVO company) throws BizException {
	   CompanyVO companyDetail = commonDao.selectOne("Company.getCompany", company);

       return companyDetail;
   }
   @Override
   public List<CommentVO> getCommentList(CommentVO comment) throws BizException {
   	
       List<CommentVO> commentList = commonDao.selectList("Comment.getCommnetList", comment);

       return commentList;
   }

   @Override
   public int regiCommentInsert(CommentVO comment)
   	    throws BizException
	{
	    int retVal=-1;
	    
	    try{
	
	    	retVal=this.commonDao.insert("Comment.CommnetInsert", comment);
	
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
  }
   @Override
   public List<CommentVO> getProductEtcList(CommentVO comment) throws BizException {
   	
       List<CommentVO> commentList = commonDao.selectList("Comment.getProductEtcList", comment);

       return commentList;
   }
   
   @Override
   public List<UserVO> getSmsList(UserVO usercon) throws BizException {
       List<UserVO> smsnoList = commonDao.selectList("User.getSmsList", usercon);

       return smsnoList;
   }
   
   @Override
   public List<UserVO> getSmsBatchList(UserVO usercon) throws BizException {
       List<UserVO> smsnoList = commonDao.selectList("User.getSmsBatchList", usercon);

       return smsnoList;
   }
   
   @Override
   public int regiHistoryInsert(WorkVO work)
   	    throws BizException
	{
	    int retVal=-1;
	    
	    try{
	
	    	retVal=this.commonDao.insert("Work.WorkInsert", work);
	
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());

	    }
	
	    return retVal;
	    
  }
   
   @Override
   public List<WorkVO> getWorkHistoryPageList(WorkVO workcondition) throws BizException {
   	
       List<WorkVO> workList = commonDao.selectList("Work.getWorkHistoryPageList", workcondition);

       return workList;
   }

   @Override
   public int getCompanyCnt(WorkVO workcondition) throws BizException {
       return commonDao.selectOne("Work.getWorkHistoryCnt", workcondition);
   }
}
