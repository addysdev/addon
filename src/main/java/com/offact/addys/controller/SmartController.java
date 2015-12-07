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
import com.offact.addys.service.master.ProductMasterService;
import com.offact.addys.service.smart.CounselService;
import com.offact.addys.service.smart.ComunityService;
import com.offact.addys.service.smart.AsService;
import com.offact.addys.service.smart.AsHistoryService;
import com.offact.addys.service.smart.BrandService;
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
import com.offact.addys.vo.master.ProductMasterVO;
import com.offact.addys.vo.master.StockVO;
import com.offact.addys.vo.recovery.RecoveryVO;
import com.offact.addys.vo.smart.CounselVO;
import com.offact.addys.vo.smart.ComunityVO;
import com.offact.addys.vo.smart.AsVO;
import com.offact.addys.vo.smart.AsHistoryVO;
import com.offact.addys.vo.smart.BrandVO;
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
    @Value("#{config['offact.host.url']}")
    private String hostUrl;
    
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
    private AsService asSvc;
    
    @Autowired
    private AsHistoryService asHistorySvc;
    
    @Autowired
    private BrandService brandSvc;
    
    @Autowired
    private SmsService smsSvc;
    
    @Autowired
    private ProductMasterService productMasterSvc;
    
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

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        CounselVO counselConVO = new CounselVO();
        
        counselConVO.setCustomerKey(strUserId);
        counselConVO.setGroupId(strGroupId);
        
        //stockConVO.setStart_stockDate(strToday);
        counselConVO.setStart_counselDate(strToday.substring(0,8)+"01");
        counselConVO.setEnd_counselDate(strToday);

        // 조회조건저장
        mv.addObject("counselConVO", counselConVO);
        
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
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
			//smsVO.setSmsMsg(counselVO.getCounselResult());
			smsVO.setSmsMsg("[애디스]문의하신 상담내용에 대한 답변이 등록되었습니다. 답변확인하러가기-> addys.kr");
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

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        ComunityVO comunityConVO = new ComunityVO();
        
        comunityConVO.setCustomerKey(strUserId);
        
        comunityConVO.setGroupId(strGroupId);
        
        //stockConVO.setStart_stockDate(strToday);
        comunityConVO.setStart_comunityDate(strToday.substring(0,8)+"01");
        comunityConVO.setEnd_comunityDate(strToday);

        // 조회조건저장
        mv.addObject("comunityConVO", comunityConVO);
        
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
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
		                           String upidx,
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
        mv.addObject("upidx", upidx);
        
        ComunityVO comunityVO = new ComunityVO();
        
        comunityVO.setUpidx(upidx);
        
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
		comunityVO.setUserId(strUserId);

        try{//01.리플추가
    	    
        	int dbResult=comunitySvc.regiReplyInsert(comunityVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }
        
        // 조회조건저장
        mv.addObject("comment", comunityVO.getComment());
        mv.addObject("upidx", comunityVO.getUpidx());
       
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
    @RequestMapping(value = "/smart/counselhistory")
    public ModelAndView counselHistory(HttpServletRequest request, 
    		                       HttpServletResponse response,
		                           String idx,
		                           String customerKey,
		                           String counsel) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start idx:"+idx);

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
        mv.addObject("customerKey", customerKey);
        mv.addObject("idx", idx);
        mv.addObject("counsel", counsel);
        
        CounselVO counselVO = new CounselVO();
        
        counselVO.setUpidx(idx);
        
        List<CounselVO> counselReply = new ArrayList();

        //품목 비고 정보
        counselReply=counselSvc.getCounselReply(counselVO);

        mv.addObject("counselReply", counselReply);
        

        mv.setViewName("/smart/counselHistory");
        
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
    @RequestMapping(value = "/smart/counselreplyddlist")
    public ModelAndView counselReplyddlist( @ModelAttribute("counselVO") CounselVO counselVO,
    		                      HttpServletRequest request) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : counselVO : " + counselVO);

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

        counselVO.setCustomerKey(strMobliePhone);
        counselVO.setGroupId(groupId);
        counselVO.setUserId(strUserId);

        try{//01.리플추가
    	    
        	int dbResult=counselSvc.regiReplyInsert(counselVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }
        
        // 조회조건저장
        mv.addObject("counsel", counselVO.getCounsel());
        mv.addObject("idx", counselVO.getIdx());
       
        List<CounselVO> counselReply = new ArrayList();

        //품목 비고 정보
        counselReply=counselSvc.getCounselReply(counselVO);

        mv.addObject("counselReply", counselReply);

        mv.setViewName("/smart/counselHistory");
        
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		//추가필요
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
 
    /**
     * AS관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/asmanage")
    public ModelAndView asManage(HttpServletRequest request, 
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

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        AsVO asConVO = new AsVO();
        
        asConVO.setCustomerKey(strUserId);
        asConVO.setGroupId(strGroupId);
        
        //stockConVO.setStart_stockDate(strToday);
        asConVO.setStart_asDate(strToday.substring(0,8)+"01");
        asConVO.setEnd_asDate(strToday);

        // 조회조건저장
        mv.addObject("asConVO", asConVO);
        
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (발주상태코드)
        CodeVO acode = new CodeVO();
        acode.setCodeGroupId("AS01");
        List<CodeVO> acode_comboList = commonSvc.getCodeComboList(acode);
        mv.addObject("acode_comboList", acode_comboList);
        
        // 공통코드 조회 (발주상태코드)
        CodeVO sacode = new CodeVO();
        sacode.setCodeGroupId("AS02");
        List<CodeVO> sacode_comboList = commonSvc.getCodeComboList(sacode);
        mv.addObject("sacode_comboList", sacode_comboList);
        
        mv.setViewName("/smart/asManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * AS관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/aspagelist")
    public ModelAndView asPageList(@ModelAttribute("asConVO") AsVO asConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asConVO" + asConVO);

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
        
        List<AsVO> asList = null;
 
        
        // 조회조건 null 일때 공백처리
        if (asConVO.getSearchGubun() == null) {
        	asConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (asConVO.getSearchValue() == null) {
        	asConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("asConVO", asConVO);

        // 페이징코드
        asConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(asConVO.getCurPage(), asConVO.getRowCount()));
        asConVO.setPage_limit_val2(StringUtil.nvl(asConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        asList = asSvc.getAsList(asConVO);
        mv.addObject("asList", asList);

        // totalCount 조회
        String totalCount = String.valueOf(asSvc.getAsCnt(asConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/smart/asPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/smart/asprodessform")
	public ModelAndView asProcessForm(String asNo,
			                          HttpServletRequest request) throws BizException 
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asNo : " + asNo);
    			
		ModelAndView mv = new ModelAndView();
		
		AsVO asVO = new AsVO();
		
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
		
        asVO.setAsNo(asNo);
        
        asVO = asSvc.getAsDetail(asNo);
        
		mv.addObject("asVO", asVO);
		
        // 공통코드 조회 (배송업체코드)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("DE01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);
   
		mv.setViewName("/smart/asProcessForm");
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
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
    @RequestMapping(value = "/smart/asprocess", method = RequestMethod.POST)
    public @ResponseBody
    String asProcess(@ModelAttribute("asVO") AsVO asVO,
    		       HttpServletRequest request, 
    		       HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO" + asVO);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strMobliePhone = StringUtil.nvl((String) session.getAttribute("strMobliePhone"));
        
        asVO.setUserId(strUserId);
        asVO.setGroupId(strGroupId);
        
        int retVal=-1;//this.asSvc.asProc(asVO);
        
        try{
			//SMS발송
			SmsVO smsVO = new SmsVO();
			SmsVO resultSmsVO = new SmsVO();
			
			smsVO.setSmsId(smsId);
			smsVO.setSmsPw(smsPw);
			smsVO.setSmsType(smsType);
			smsVO.setSmsTo(asVO.getCustomerKey());
			smsVO.setSmsFrom(strMobliePhone);
			//smsVO.setSmsMsg(asVO.getAsResult());
			smsVO.setSmsMsg("[애디스]요청하신 AS내용에 대한 상태가 변경되었습니다. AS상태확인하러가기-> addys.kr");
			smsVO.setSmsUserId(strUserId);
			
			logger.debug("#########devOption :"+devOption);
			String[] devSmss= devSms.split("\\^");
			
    		if(devOption.equals("true")){
				for(int i=0;i<devSmss.length;i++){
					
					if(devSmss[i].equals(asVO.getCustomerKey().trim().replace("-", ""))){
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
     * AS상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/asstateupdate", method = RequestMethod.POST)
    public @ResponseBody
    String asStateUpdate(@ModelAttribute("asVO") AsVO asVO,
    		             HttpServletRequest request, 
    		             HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO" + asVO);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        String asNo="";

        AsVO asConVO = new AsVO();

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        asConVO.setAsNo("A"+strGroupId+strToday);
        asConVO=asSvc.getAsNo(asConVO);
        
        asConVO.setGroupId(strGroupId);
        asConVO.setAsStartUserId(strUserId);
        asConVO.setAsCompleteUserId(strUserId);
        asConVO.setProductCode(asVO.getProductCode());
        asConVO.setCustomerKey(asVO.getCustomerKey());
        asConVO.setAsState(asVO.getAsState());

        int retVal=this.asSvc.asResultInsert(asConVO);

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
     * 메모관리
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/ashistory")
    public ModelAndView asHistory(HttpServletRequest request, 
    		                       HttpServletResponse response,
		                           String asNo,
		                           String customerKey,
		                           String asDetail) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start asNo:"+asNo);

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
        mv.addObject("customerKey", customerKey);
        mv.addObject("asNo", asNo);
        mv.addObject("asDetail", asDetail);
        
        AsVO asVO = new AsVO();
        
        asVO.setAsNo(asNo);
        
        List<AsVO> asReply = new ArrayList();

        //품목 비고 정보
        asReply=asSvc.getAsReply(asVO);

        mv.addObject("asReply", asReply);
        

        mv.setViewName("/smart/asHistory");
        
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
    @RequestMapping(value = "/smart/asreplyddlist")
    public ModelAndView asReplyddlist( @ModelAttribute("asVO") AsVO asVO,
    		                      HttpServletRequest request) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO : " + asVO);

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

        asVO.setCustomerKey(strMobliePhone);
        asVO.setGroupId(groupId);
        asVO.setUserId(strUserId);

        try{//01.리플추가
    	    
        	int dbResult=asSvc.regiReplyInsert(asVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }
        
        // 조회조건저장
        mv.addObject("as", asVO.getAsDetail());
        mv.addObject("idx", asVO.getIdx());
       
        List<AsVO> asReply = new ArrayList();

        //품목 비고 정보
        asReply=asSvc.getAsReply(asVO);

        mv.addObject("asReply", asReply);

        mv.setViewName("/smart/asHistory");
        
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		//추가필요
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
    /**
     * 글올리기
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/imageview")
    public ModelAndView imageView(HttpServletRequest request, 
    		                      HttpServletResponse response,
    		                      String imageurl) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start imageView");

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
        
        mv.addObject("imageurl", imageurl);

        mv.setViewName("/smart/imageView");
        
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
    @RequestMapping(value = "/smart/asregistform")
    public ModelAndView asRegistForm(String customerKey ,
						    		 String brandCode ,
						    		 String brandName ,
						    		 String productCode ,
						    		 String productName ,
						    		 String asPolicy ,
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start asregistform customerKey :"+customerKey);

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
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        Date deliveryTime = new Date();
        int movedate=14;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
           
        mv.addObject("strToday", strToday);
        mv.addObject("strDeliveryDay", strDeliveryDay);
        mv.addObject("customerKey", customerKey);
        mv.addObject("brandCode", brandCode);
        mv.addObject("brandName", brandName);
        mv.addObject("productCode", productCode);
        mv.addObject("productName", productName);
        mv.addObject("asPolicy", asPolicy);
        
        mv.setViewName("/smart/asRegistForm");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
    /**
     * 보류사유 리스트
     * 
     * @param orderCode
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/replceresult")
    public ModelAndView replceResult( HttpServletRequest request, 
    		                              HttpServletResponse response) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : replcereason");

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
 
        mv.setViewName("/smart/replceResult");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
    /**
     * A/S품목조회화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/productsearch")
    public ModelAndView productSearch(HttpServletRequest request, 
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

      mv.setViewName("/smart/productSearch");
        
       //log Controller execute time end
      long t2 = System.currentTimeMillis();
      logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
      return mv;
    }
    /**
     * A/S품목 검색목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/productsearchlist")
    public ModelAndView productSearchList(@ModelAttribute("productConVO") ProductMasterVO productConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
  	  	String logid=logid();
  		long t1 = System.currentTimeMillis();
  		logger.info("["+logid+"] Controller start : productConVO" + productConVO);

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
        
        List<ProductMasterVO> productList = null;

        // 조회조건 null 일때 공백처리
        if (productConVO.getSearchGubun() == null) {
        	productConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (productConVO.getSearchValue() == null) {
        	productConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("productCon", productConVO);

        // 페이징코드
        productConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(productConVO.getCurPage(), productConVO.getRowCount()));
        productConVO.setPage_limit_val2(StringUtil.nvl(productConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        productList = productMasterSvc.getProductList(productConVO);
        mv.addObject("productList", productList);

        // totalCount 조회
        String totalCount = String.valueOf(productMasterSvc.getProductCnt(productConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/smart/productSearchList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
    /**
     * A/S품목 검색목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/brandinfo")
    public ModelAndView brandInfo(String brandCode, 
    							  String productCode,
    							  String productName, 
    		                      HttpServletRequest request, 
    		                      HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
  	  	String logid=logid();
  		long t1 = System.currentTimeMillis();
  		logger.info("["+logid+"] Controller start : brandCode" + brandCode);

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
        
        BrandVO brandVo = new BrandVO();
        brandVo.setBrandCode(brandCode);
        
        brandVo = brandSvc.getBrandDetail(brandVo);
        // 조회조건저장
        mv.addObject("brandVo", brandVo);
        mv.addObject("productCode", productCode);
        mv.addObject("productName", productName);

        mv.setViewName("/smart/brandInfo");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
    /**
     * AS등록
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/asregist")
    public ModelAndView asRegist(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO,
    		                     @ModelAttribute("asVO") AsVO asVO,
    		                     HttpServletRequest request, 
    		                     HttpServletResponse response,
    		                     String fileAttach) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : fileVO" + fileVO);
		logger.info("["+logid+"] Controller start : asVO" + asVO);

        ModelAndView mv = new ModelAndView();

        String fname ="";

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
     
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
     
        String imagePath="as/"+strToday+"/";
        
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String uploadFilePath = rb.getString("offact.upload.path") + imagePath;

        try {
        
	        if (fileAttach.equals("Y")) {
		    	  
		        List<MultipartFile> files = fileVO.getFiles();
		        List fileNames = new ArrayList();
		        String orgFileName = null;
		        
		        int filecnt=0;

		        if ((files != null) && (files.size() > 0))
		        {
		          for (MultipartFile multipartFile : files)
		          {
		            orgFileName = multipartFile.getOriginalFilename();
		            this.logger.debug("orgFileName :" + orgFileName);
		            
		            if(!orgFileName.equals("")){
		            	int extIndex = orgFileName.lastIndexOf(".");
			            String extension=orgFileName.substring(extIndex+1);
			            
			            long fileName = System.currentTimeMillis();
			            orgFileName = fileName +"."+ extension;
			            this.logger.debug("orgFileName 2 :" + orgFileName);
			            
			            boolean check=setDirectory(uploadFilePath);

			            String filePath = uploadFilePath;

			            File file = new File(filePath + orgFileName);
			            multipartFile.transferTo(file);
			            fileNames.add(orgFileName);
			            
			            if(filecnt==0){
			            	asVO.setAsImage(hostUrl+"/upload/"+imagePath+orgFileName);
			            }else{
			            	asVO.setReceiptImage(hostUrl+"/upload/"+imagePath+orgFileName);
			            }
			            
		            }
		            
		            filecnt++;   
		            
		          }
		     
		        }
		        
		        fname = uploadFilePath + orgFileName;

	        }
        }catch (Exception e){
        	
        	logger.info("["+logid+"][error] : "+e.getMessage()); 
        	
        }
        
        AsVO asConVO = new AsVO();

        asConVO.setAsNo("A"+strGroupId+strToday);
        asConVO=asSvc.getAsNo(asConVO);
        
        asVO.setAsNo(asConVO.getAsNo());
        asVO.setAsCompleteUserId(strUserId);
        asVO.setAsTargetDate(asVO.getAsTargetDate().trim().replace("-", ""));
        asVO.setPurchaseDate(asVO.getPurchaseDate().trim().replace("-", ""));

        int retVal=asSvc.asRegistInsert(asVO);
        
        mv.addObject("retVal",""+retVal);
        mv.addObject("asState", asVO.getAsState());
        mv.setViewName("/smart/asRegist");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
	/**
	 * 업로드 디렉토리 세팅
	 */
	private static boolean setDirectory( String directory) {
		File wantedDirectory = new File(directory);
		if (wantedDirectory.isDirectory())
			return true;
	    
		return wantedDirectory.mkdirs();
	}
	
	/**
     * AS관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/ashitorylist")
    public ModelAndView asHitoryList(String asNo, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asNo" + asNo);

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
        
        List<AsHistoryVO> asHistoryList = null;
        
        AsHistoryVO asConVO = new AsHistoryVO();
        asConVO.setAsNo(asNo);

        // 목록조회
        asHistoryList = asHistorySvc.getAsHistoryList(asConVO);
        mv.addObject("asHistoryList", asHistoryList);

        mv.setViewName("/smart/asHitoryList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
   
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/smart/asnoprint")
   	public ModelAndView asNoPrint(HttpServletRequest request,
                                          String asNo,
                                          String groupId,
                                          String groupName) throws BizException 
       {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));

   		ModelAndView mv = new ModelAndView();
   		
   	    mv.addObject("groupName", groupName);
     	mv.addObject("groupId", groupId);
     	mv.addObject("asNo", asNo);
   		
   		mv.setViewName("/smart/asNoPrint");
   		
   		
   		return mv;
   	}
    
    /**
     * AS상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/astransupdate", method = RequestMethod.POST)
    public @ResponseBody
    String asTransUpdate(@ModelAttribute("asVO") AsVO asVO,
    		             HttpServletRequest request, 
    		             HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO" + asVO);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        asVO.setUserId(strUserId);
        asVO.setGroup1Id(strGroupId);
        asVO.setAsState("05");
        asVO.setAsSubState("01");
        asVO.setAsHistory("점포->본사배송");
        
        int retVal=this.asSvc.asTransUpdate(asVO);

        //SMS발송
        
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
     * 운송업체 정보 수정처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/astransreupdate", method = RequestMethod.POST)
    public @ResponseBody
    String transUpdate(@ModelAttribute("asVO") AsVO asVO, 
    		          HttpServletRequest request, 
    		          HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO" + asVO);
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
      //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        CodeVO code =new CodeVO();
		code.setCodeGroupId("DE01");
		code.setCodeId(asVO.getAsTransportCode());
		
		if(asVO.getAsDeliveryMethod().equals("01")){
		
			code=commonSvc.getCodeName(code);
		
		}
		
		asVO.setAsTransport(code.getCodeName());

		int retVal=this.asSvc.transUpdateProc(asVO);

		//작업이력
		/*
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory(workCategory);
		work.setWorkCode(workCode);
		commonSvc.regiHistoryInsert(work);
		*/
		
		String transResult="-1";
		
		if(retVal>0){
			
			transResult=code.getCodeName()+"^"+code.getDescription()+"^"+asVO.getAsTransportNo();
			
		}
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return transResult;
    }
    
    /**
     * 운송업체 정보 수정처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/retransreupdate", method = RequestMethod.POST)
    public @ResponseBody
    String retransUpdate(@ModelAttribute("asVO") AsVO asVO, 
    		          HttpServletRequest request, 
    		          HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO" + asVO);
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
      //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        CodeVO code =new CodeVO();
		code.setCodeGroupId("DE01");
		code.setCodeId(asVO.getReTransportCode());
		
		if(asVO.getReDeliveryMethod().equals("01")){
		
			code=commonSvc.getCodeName(code);
		
		}
		
		asVO.setReTransport(code.getCodeName());

		int retVal=this.asSvc.reTransUpdateProc(asVO);

		//작업이력
		/*
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory(workCategory);
		work.setWorkCode(workCode);
		commonSvc.regiHistoryInsert(work);
		*/
		
		String transResult="-1";
		
		if(retVal>0){
			
			transResult=code.getCodeName()+"^"+code.getDescription()+"^"+asVO.getReTransportNo();
			
		}
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return transResult;
    }
    
    /**
     * AS상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/asstateprocess", method = RequestMethod.POST)
    public @ResponseBody
    String asStateProcess(String asNo,
    		              String asState,
    					  String asSubState,
    					  String asHistory,
    		              HttpServletRequest request, 
    		              HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asNo" + asNo);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        AsVO asVO = new AsVO();
        
        asVO.setAsNo(asNo);
        asVO.setUserId(strUserId);
        asVO.setGroup1Id(strGroupId);
        asVO.setAsState(asState);
        asVO.setAsSubState(asSubState);
        asVO.setAsHistory(asHistory);
        
        int retVal=this.asSvc.asStateProc(asVO);

        //SMS발송
        
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
     * AS상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/ascenterstart", method = RequestMethod.POST)
    public @ResponseBody
    String asCenterStart(String asNo,
    		              String centerAsNo,
    					  String asHistory,
    		              HttpServletRequest request, 
    		              HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : centerAsNo" + centerAsNo);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        AsVO asVO = new AsVO();
        
        asVO.setAsNo(asNo);
        asVO.setUserId(strUserId);
        asVO.setGroup1Id(strGroupId);
        asVO.setAsState("06");
        asVO.setAsSubState("04");
        asVO.setAsHistory(asHistory);
        asVO.setCenterAsNo(centerAsNo);
        
        int retVal=this.asSvc.asCenterStart(asVO);

        //SMS발송
        
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
     * AS상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/ascenterend", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView asCenterEnd(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO,
			            @ModelAttribute("asVO") AsVO asVO,
			            HttpServletRequest request, 
			            HttpServletResponse response,
			            String fileAttach) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : fileVO" + fileVO);
		logger.info("["+logid+"] Controller start : asVO" + asVO);

        ModelAndView mv = new ModelAndView();

        String fname ="";

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
     
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
     
        String imagePath="as/"+strToday+"/";
        
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String uploadFilePath = rb.getString("offact.upload.path") + imagePath;

        try {
        
	        if (fileAttach.equals("Y")) {
		    	  
		        List<MultipartFile> files = fileVO.getFiles();
		        List fileNames = new ArrayList();
		        String orgFileName = null;
		        
		        int filecnt=0;

		        if ((files != null) && (files.size() > 0))
		        {
		          for (MultipartFile multipartFile : files)
		          {
		            orgFileName = multipartFile.getOriginalFilename();
		            this.logger.debug("orgFileName :" + orgFileName);
		            
		            if(!orgFileName.equals("")){
		            	int extIndex = orgFileName.lastIndexOf(".");
			            String extension=orgFileName.substring(extIndex+1);
			            
			            long fileName = System.currentTimeMillis();
			            orgFileName = fileName +"."+ extension;
			            this.logger.debug("orgFileName 2 :" + orgFileName);
			            
			            boolean check=setDirectory(uploadFilePath);

			            String filePath = uploadFilePath;

			            File file = new File(filePath + orgFileName);
			            multipartFile.transferTo(file);
			            fileNames.add(orgFileName);
			            
			            asVO.setCenterImage(hostUrl+"/upload/"+imagePath+orgFileName);
			            
		            }
		            
		            filecnt++;   
		            
		          }
		     
		        }
		        
		        fname = uploadFilePath + orgFileName;

	        }
        }catch (Exception e){
        	
        	logger.info("["+logid+"][error] : "+e.getMessage()); 
        	
        }

        asVO.setUserId(strUserId);
        asVO.setGroupId(strGroupId);
        asVO.setAsState("06");
        asVO.setAsSubState("06");
        
        int retVal=this.asSvc.asCenterStart(asVO);
        
        mv.addObject("retVal",""+retVal);
        mv.addObject("asState", asVO.getAsState());
        mv.setViewName("/smart/asRegist");
        
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
    @RequestMapping(value = "/smart/astransmodifyform")
    public ModelAndView asTransModifyForm(HttpServletRequest request, 
    		                       HttpServletResponse response,
		                           String asNo,
		                           String asDeliveryMethod) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start asNo:"+asNo);

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
        mv.addObject("asNo", asNo);
        mv.addObject("asDeliveryMethod", asDeliveryMethod);
        
        // 공통코드 조회 (배송업체코드)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("DE01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);

        mv.setViewName("/smart/asTransModifyForm");
        
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
    @RequestMapping(value = "/smart/retransmodifyform")
    public ModelAndView reTransModifyForm(HttpServletRequest request, 
    		                       HttpServletResponse response,
		                           String asNo,
		                           String reDeliveryMethod) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start asNo:"+asNo);

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
        mv.addObject("asNo", asNo);
        mv.addObject("reDeliveryMethod", reDeliveryMethod);
        
        // 공통코드 조회 (배송업체코드)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("DE01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);

        mv.setViewName("/smart/reTransModifyForm");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
    /**
     * AS상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/asreceivetrans", method = RequestMethod.POST)
    public @ResponseBody
    String asReceiveTrans(@ModelAttribute("asVO") AsVO asVO,
    					  String asHistory,
    		              HttpServletRequest request, 
    		              HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO" + asVO);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        asVO.setUserId(strUserId);
        asVO.setGroup1Id(strGroupId);
        asVO.setAsState("06");
        asVO.setAsSubState("07");
        asVO.setAsHistory(asHistory);
        
        int retVal=this.asSvc.asReceiveState(asVO);

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
     * AS상태변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/smart/ascustomerreceive", method = RequestMethod.POST)
    public @ResponseBody
    String asCustomerReceive(@ModelAttribute("asVO") AsVO asVO,
    					  String asHistory,
    		              HttpServletRequest request, 
    		              HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asVO" + asVO);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        asVO.setUserId(strUserId);
        asVO.setGroup1Id(strGroupId);
        asVO.setAsState("08");
        asVO.setAsSubState("09");
        asVO.setAsHistory(asHistory);
        
        int retVal=this.asSvc.asReceiveState(asVO);

        //SMS발송
        
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
    
}
