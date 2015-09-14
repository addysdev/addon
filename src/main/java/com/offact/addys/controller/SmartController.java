package com.offact.addys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.offact.framework.util.StringUtil;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.addys.service.common.CommonService;
import com.offact.addys.service.common.UserService;
import com.offact.addys.service.manage.UserManageService;
import com.offact.addys.service.smart.CounselService;
import com.offact.addys.service.smart.ComunityService;
import com.offact.addys.service.history.WorkHistoryService;
import com.offact.addys.service.common.SmsService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.WorkVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.common.SmsVO;
import com.offact.addys.vo.history.WorkHistoryVO;
import com.offact.addys.vo.manage.UserManageVO;
import com.offact.addys.vo.smart.CounselVO;
import com.offact.addys.vo.smart.ComunityVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class SmartController {

	private final Logger logger = Logger.getLogger(getClass());
	 /*
     * log id 생성 
     */
	public String logid(){
		
		double id=Math.random();
		long t1 = System.currentTimeMillis ( ); 
		
		String logid=""+t1+id;
		
		return logid;
	}
    
    @Value("#{config['offact.dev.option']}")
    private String devOption;
    
    @Value("#{config['offact.dev.sms']}")
    private String devSms;
    
    @Value("#{config['offact.sms.smsid']}")
    private String smsId;
    
    @Value("#{config['offact.sms.smspw']}")
    private String smsPw;
    
    @Value("#{config['offact.sms.smstype']}")
    private String smsType;
	
    @Autowired
    private CommonService commonSvc;
    
    @Autowired
	private UserService userSvc;
    
    @Autowired
    private UserManageService userManageSvc;
    
    @Autowired
    private CounselService counselSvc;
    
    @Autowired
    private ComunityService comunitySvc;
	
    @Autowired
    private SmsService smsSvc;
    
	 /**
     * 상담관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/counselmanage")
    public ModelAndView counselManage(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        CounselVO counselConVO = new CounselVO();
        
        counselConVO.setCustomerKey(strUserId);
        counselConVO.setGroupId(strGroupId);

        // 조회조건저장
        mv.addObject("counselConVO", counselConVO);
        
        mv.setViewName("/smart/counselManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 상담관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/counselpagelist")
    public ModelAndView counselPageList(@ModelAttribute("counselConVO") CounselVO counselConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : counselConVO" + counselConVO);

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        List<CounselVO> counselList = null;
 
        
        // 조회조건 null 일때 공백처리
        if (counselConVO.getSearchGubun() == null) {
        	counselConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (counselConVO.getSearchValue() == null) {
        	counselConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("counselConVO", counselConVO);

        // 페이징코드
        counselConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(counselConVO.getCurPage(), counselConVO.getRowCount()));
        counselConVO.setPage_limit_val2(StringUtil.nvl(counselConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        counselList = counselSvc.getCounselList(counselConVO);
        mv.addObject("counselList", counselList);

        // totalCount 조회
        String totalCount = String.valueOf(counselSvc.getCounselCnt(counselConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/smart/counselPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/smart/counselprodessform")
	public ModelAndView counselProcessForm(String idx,
			                               HttpServletRequest request) throws BizException 
    {
		
		ModelAndView mv = new ModelAndView();
		
		CounselVO counselVO = new CounselVO();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
		
        counselVO.setIdx(idx);
        
        counselVO = counselSvc.getCounselDetail(idx);
        
		mv.addObject("counselVO", counselVO);
		
		//조직정보 조회
		/*
        GroupVO group = new GroupVO();
        group.setGroupId("G00000");
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        */
   
		mv.setViewName("/smart/counselProcessForm");
		return mv;
	}
	 /**
     * 사용자관리 등록처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/counselprocess", method = RequestMethod.POST)
    public @ResponseBody
    String counselProcess(@ModelAttribute("counselVO") CounselVO counselVO,
    		       HttpServletRequest request, 
    		       HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : counselVO" + counselVO);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strMobliePhone = StringUtil.nvl((String) session.getAttribute("strMobliePhone"));
        
        counselVO.setUserId(strUserId);
        counselVO.setGroupId(strGroupId);
        counselVO.setStateUpdateUserId(strUserId);
        
        int retVal=this.counselSvc.counselProc(counselVO);
        
        try{
			//SMS발송
			SmsVO smsVO = new SmsVO();
			SmsVO resultSmsVO = new SmsVO();
			
			smsVO.setSmsId(smsId);
			smsVO.setSmsPw(smsPw);
			smsVO.setSmsType(smsType);
			smsVO.setSmsTo(counselVO.getCustomerKey());
			smsVO.setSmsFrom(strMobliePhone);
			smsVO.setSmsMsg(counselVO.getCounselResult());
			smsVO.setSmsUserId(strUserId);
			
			logger.debug("#########devOption :"+devOption);
			String[] devSmss= devSms.split("\\^");
			
    		if(devOption.equals("true")){
				for(int i=0;i<devSmss.length;i++){
					
					if(devSmss[i].equals(counselVO.getCustomerKey().trim().replace("-", ""))){
						resultSmsVO=smsSvc.sendSms(smsVO);
					}
				}
			}else{
				resultSmsVO=smsSvc.sendSms(smsVO);
			}

			logger.debug("sms resultSmsVO.getResultCode() :"+resultSmsVO.getResultCode());
			logger.debug("sms resultSmsVO.getResultMessage() :"+resultSmsVO.getResultMessage());
			logger.debug("sms resultSmsVO.getResultLastPoint() :"+resultSmsVO.getResultLastPoint());
			
		}catch(BizException e){
			
			logger.info("["+logid+"] Controller SMS전송오류");
			
		}

		//작업이력
        /*
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("MU");
		work.setWorkCode("MU001");
		commonSvc.regiHistoryInsert(work);
		*/
        
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }
    
	 /**
     * 상담상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/counselstateupdate", method = RequestMethod.POST)
    public @ResponseBody
    String counselStateUpdate(String  idx,
    		       String  counselState,
    		       HttpServletRequest request, 
    		       HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : idx" + idx);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        CounselVO counselConVO = new CounselVO();
        
        counselConVO.setUserId(strUserId);
        counselConVO.setGroupId(strGroupId);
        counselConVO.setIdx(idx);
        counselConVO.setCounselState(counselState);
        counselConVO.setStateUpdateUserId(strUserId);
        
        int retVal=this.counselSvc.counselStateUpdate(counselConVO);

		//작업이력
        /*
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("MU");
		work.setWorkCode("MU001");
		commonSvc.regiHistoryInsert(work);
		*/
        
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }
    
    /**
     * 커뮤니티 관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/comunitymanage")
    public ModelAndView comunityManage(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        ComunityVO comunityConVO = new ComunityVO();
        
        comunityConVO.setCustomerKey(strUserId);

        // 조회조건저장
        mv.addObject("counselConVO", comunityConVO);
        
        mv.setViewName("/smart/comunityManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 커뮤니티관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/comunitypagelist")
    public ModelAndView comunityPageList(@ModelAttribute("comunityConVO") ComunityVO comunityConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : comunityConVO" + comunityConVO);

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        List<ComunityVO> comunityList = null;
 
        
        // 조회조건 null 일때 공백처리
        if (comunityConVO.getSearchGubun() == null) {
        	comunityConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (comunityConVO.getSearchValue() == null) {
        	comunityConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("comunityConVO", comunityConVO);

        // 페이징코드
        comunityConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(comunityConVO.getCurPage(), comunityConVO.getRowCount()));
        comunityConVO.setPage_limit_val2(StringUtil.nvl(comunityConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        comunityList = comunitySvc.getComunityList(comunityConVO);
        mv.addObject("comunityList", comunityList);

        // totalCount 조회
        String totalCount = String.valueOf(comunitySvc.getComunityCnt(comunityConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/smart/comunityPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 메모관리
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/comunityprodessform")
    public ModelAndView comunityProdessForm(HttpServletRequest request, 
    		                       HttpServletResponse response,
		                           String idx,
		                           String comment) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start comment:"+comment);

        ModelAndView mv = new ModelAndView();
        
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName")); 
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); 
 	       	//로그인 상태처리		
 	   		UserVO userState =new UserVO();
 	   		userState.setUserId(strUserId);
 	   		userState.setLoginYn("N");
 	   		userState.setIp(strIp);
 	   		userState.setConnectIp(sClientIP);
 	   		userSvc.regiLoginYnUpdate(userState);
 	           
 	        //작업이력
 	   		WorkVO work = new WorkVO();
 	   		work.setWorkUserId(strUserId);
 	   	    work.setWorkIp(strIp);
 	   		work.setWorkCategory("CM");
 	   		work.setWorkCode("CM004");
 	   		commonSvc.regiHistoryInsert(work);
 	   		
 	       	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        // 조회조건저장
        mv.addObject("comment", comment);
        mv.addObject("idx", idx);
        
        ComunityVO comunityVO = new ComunityVO();
        
        comunityVO.setUpidx(idx);
        
        List<ComunityVO> comunityReply = new ArrayList();

        //품목 비고 정보
        comunityReply=comunitySvc.getComunityReply(comunityVO);

        mv.addObject("comunityReply", comunityReply);
        

        mv.setViewName("/smart/comunityProdessForm");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
    /**
     * 메모추가
     * 
     * @param orderCode
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/replyddlist")
    public ModelAndView replyAddList( @ModelAttribute("comunityVO") ComunityVO comunityVO,
    		                      HttpServletRequest request) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : comunityVO : " + comunityVO);

        ModelAndView mv = new ModelAndView();
 
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName")); 
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        String strMobliePhone = StringUtil.nvl((String) session.getAttribute("strMobliePhone"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); 
 	       	//로그인 상태처리		
 	   		UserVO userState =new UserVO();
 	   		userState.setUserId(strUserId);
 	   		userState.setLoginYn("N");
 	   		userState.setIp(strIp);
 	   		userState.setConnectIp(sClientIP);
 	   		userSvc.regiLoginYnUpdate(userState);
 	           
 	        //작업이력
 	   		WorkVO work = new WorkVO();
 	   		work.setWorkUserId(strUserId);
 	   	    work.setWorkIp(strIp);
 	   		work.setWorkCategory("CM");
 	   		work.setWorkCode("CM004");
 	   		commonSvc.regiHistoryInsert(work);
 	   		
 	       	mv.setViewName("/addys/loginForm");
       		return mv;
		}

		comunityVO.setCustomerKey(strMobliePhone);
		comunityVO.setCustomerId("");
		comunityVO.setGroupId(groupId);

        try{//01.리플추가
    	    
        	int dbResult=comunitySvc.regiReplyInsert(comunityVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }
        
        // 조회조건저장
        mv.addObject("comment", comunityVO.getComment());
        mv.addObject("idx", comunityVO.getIdx());
       
        List<ComunityVO> comunityReply = new ArrayList();

        //품목 비고 정보
        comunityReply=comunitySvc.getComunityReply(comunityVO);

        mv.addObject("comunityReply", comunityReply);

        mv.setViewName("/smart/comunityProdessForm");
        
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		//추가필요
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
}
