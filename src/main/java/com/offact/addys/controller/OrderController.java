package com.offact.addys.controller;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
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

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.*;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.*;

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
import com.offact.addys.service.order.OrderService;
import com.offact.addys.service.order.TargetService;
import com.offact.addys.service.common.MailService;
import com.offact.addys.service.common.SmsService;
import com.offact.addys.service.common.UserService;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.EmailVO;
import com.offact.addys.vo.common.SmsVO;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.common.WorkVO;
import com.offact.addys.vo.manage.UserManageVO;
import com.offact.addys.vo.master.StockVO;
import com.offact.addys.vo.order.TargetVO;
import com.offact.addys.vo.order.OrderVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class OrderController {

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
    
    @Value("#{config['offact.mail.orderfromemail']}")
    private String orderfromemail;
    
    @Value("#{config['offact.mail.ordersubject']}")
    private String ordersubject;
    
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
    private TargetService targetSvc;
   
    @Autowired
    private OrderService orderSvc;
    
    @Autowired
    private MailService mailSvc;
    
    @Autowired
    private SmsService smsSvc;
    
	@Autowired
	private UserService userSvc;
    
	 /**
     * 발주대상 화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/targetmanage")
    public ModelAndView targetManage(HttpServletRequest request, 
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

        TargetVO targetConVO = new TargetVO();
        
        targetConVO.setGroupId(groupId);

        // 조회조건저장
        mv.addObject("targetConVO", targetConVO);

        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(groupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (발주상태코드)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("OD01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);
       
        mv.setViewName("/order/targetManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 발주대상 목록조회
     * 
     * @param targetConVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/targetpagelist")
    public ModelAndView targetPageList(@ModelAttribute("targetConVO") TargetVO targetConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : targetConVO" + targetConVO);

        ModelAndView mv = new ModelAndView();
        List<TargetVO> targetList = null;
        TargetVO stateVO = new TargetVO();

        // 조직값 null 일때 공백처리
        if (targetConVO.getCon_groupId() == null) {
        	targetConVO.setCon_groupId("");
        }

        // 상태 값 null 일때 공백처리
        if (targetConVO.getCon_orderState() == null) {
        	targetConVO.setCon_groupId("G00000");
        }

        // 조회조건저장
        mv.addObject("targetConVO", targetConVO);

        // 페이징코드
        targetConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(targetConVO.getCurPage(), targetConVO.getRowCount()));
        targetConVO.setPage_limit_val2(StringUtil.nvl(targetConVO.getRowCount(), "10"));
        
        // 발주대상목록조회
        targetList = targetSvc.getTargetPageList(targetConVO);
        mv.addObject("targetList", targetList);

        // totalCount 조회
        String totalCount = String.valueOf(targetSvc.getTargetCnt(targetConVO));
        mv.addObject("totalCount", totalCount);
        
        // 상태통계 조회
        stateVO= targetSvc.getStateCnt(targetConVO);
        mv.addObject("stateVO", stateVO);

        mv.setViewName("/order/targetPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
    /**
     * 발주대상 상세조회
     * 
     * @param targetConVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/targetdetailview")
    public ModelAndView targetDetailView( HttpServletRequest request, 
    		                              HttpServletResponse response,
    		                              String orderCode,
    		                              String groupId,
    		                              String groupName,
    		                              String companyCode,
    		                              String orderState,
    		                              String productPrice,
    		                              String vat,
    		                              String orderPrice,
    		                              String safeOrderCnt,
    		                              String orderAddress) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : groupId : [" + groupId+"] groupName : ["+groupName+
				"] companyCode : ["+companyCode+"] orderState : ["+orderState+"]"+"] safeOrderCnt : ["+safeOrderCnt+"]");

        ModelAndView mv = new ModelAndView();
        List<TargetVO> targetDetailList = null;
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName"));
        String strGroupName = StringUtil.nvl((String) session.getAttribute("strGroupName"));
        String strOfficePhone = StringUtil.nvl((String) session.getAttribute("strOfficePhone"));
        String strMobliePhone = StringUtil.nvl((String) session.getAttribute("strMobliePhone"));
        String strEmail = StringUtil.nvl((String) session.getAttribute("strEmail"));
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
        int movedate=1;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
        
        CompanyVO companyVO = new CompanyVO();
        TargetVO targetConVO = new TargetVO();
        TargetVO targetVO = new TargetVO();

        targetConVO.setCon_groupId(groupId);
        targetConVO.setCon_orderState(orderState);
        targetConVO.setGroupName(groupName);
        targetConVO.setCon_companyCode(companyCode);

        // 조회조건저장
        mv.addObject("targetConVO", targetConVO);
        
        //업체 상세 정보조회
        companyVO.setCompanyCode(companyCode);
        companyVO = commonSvc.getCompanyDetail(companyVO);

        //발주기본 정보
        targetVO.setOrderCode(orderCode);
        targetVO.setGroupId(strGroupId);
        targetVO.setCon_groupId(groupId);
        targetVO.setGroupName(groupName);
        targetVO.setProductPrice(productPrice);
        targetVO.setVat(vat);
        targetVO.setOrderPrice(orderPrice);
        targetVO.setSafeOrderCnt(safeOrderCnt);
        
        targetVO.setOrderUserName(strUserName);
        targetVO.setOrderDate(strToday);
        targetVO.setOrderMobilePhone(strMobliePhone);
        targetVO.setOrderTelNumber(strOfficePhone);
        targetVO.setOrderEmail(strEmail);
        targetVO.setOrderFaxNumber("");
        
        targetVO.setCompanyCode(companyVO.getCompanyCode());
        targetVO.setCompanyName(companyVO.getCompanyName());
        targetVO.setDeliveryCharge(companyVO.getChargeName());
        targetVO.setMobilePhone(companyVO.getMobilePhone());
        targetVO.setFaxNumber(companyVO.getFaxNumber());
        targetVO.setTelNumber(companyVO.getCompanyPhone());
        targetVO.setEmail(companyVO.getEmail());
        targetVO.setEmail_cc(companyVO.getEmail_cc());
        targetVO.setDeliveryDate(strDeliveryDay);
        
        targetVO.setOrderAddress(orderAddress);
        
        mv.addObject("targetVO", targetVO);
        
        // 발주대상 상세목록조회
        targetDetailList = targetSvc.getTargetDetailList(targetConVO);
        mv.addObject("targetDetailList", targetDetailList);

        mv.setViewName("/order/targetDetailView");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 보류대상 상세조회
     * 
     * @param targetConVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/deferdetailview")
    public ModelAndView deferDetailView( HttpServletRequest request, 
    		                              HttpServletResponse response,
    		                              String orderCode,
    		                              String groupId,
    		                              String groupName,
    		                              String companyCode,
    		                              String orderState,
    		                              String productPrice,
    		                              String vat,
    		                              String orderPrice) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : groupId : [" + groupId+"] groupName : ["+groupName+
				"] companyCode : ["+companyCode+"] orderState : ["+orderState+"]");

        ModelAndView mv = new ModelAndView();
        List<TargetVO> targetDetailList = null;
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
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
         
        String strToday = simpleDateFormat.format(currentTime);
        
        TargetVO targetConVO = new TargetVO();
        TargetVO targetVO = new TargetVO();

        targetConVO.setOrderCode(orderCode);
        targetConVO.setCon_groupId(groupId);
        targetConVO.setCon_orderState(orderState);
        targetConVO.setGroupName(groupName);
        targetConVO.setCon_companyCode(companyCode);

        // 조회조건저장
        mv.addObject("targetConVO", targetConVO);
        
        //보류대상 정보
        targetVO=targetSvc.getDeferDetail(targetConVO);
        targetVO.setOrderCode(orderCode);
        
        //보류대상 상세정보
        targetDetailList=targetSvc.getDeferDetailList(targetConVO);
 
        mv.addObject("targetVO", targetVO);
        mv.addObject("targetDetailList", targetDetailList);
   
        mv.setViewName("/order/deferDetailView");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/order/targetdetailprint")
   	public ModelAndView targetDetailPrint(@ModelAttribute("targetVO") TargetVO targetVO, HttpServletRequest request) throws BizException 
       {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
    	logger.info("["+logid+"] Controller start : targetVO" + targetVO);
    	
    	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
		
		String[] orderList = request.getParameterValues("seqs"); 		
   	    String[] r_data=null;

        List<TargetVO> targetExcelList = new ArrayList();
	   
	    int idx = 0;
	    
		logger.info("["+logid+"] @@@@@@@@ : orderList.length" + orderList.length);
		
        try{//01.처리사유
        	
        	CommentVO commentVO =new CommentVO();

        	commentVO.setOrderCode(""+t1);
        	commentVO.setCommentUserId(strUserId);
        	commentVO.setCommentCategory("07");
        	commentVO.setComment(targetVO.getDeferReason());
    	    
        	int dbResult=commonSvc.regiCommentInsert(commentVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }

	    for(int i=0;i<orderList.length;i++){
	
	        r_data = StringUtil.getTokens(orderList[i], "|");
	        
	        if(r_data[13].equals("true") && !r_data[3].equals("0") ){ //체크박스 선택된 발주건 과 수량이 0이 아닌 대상건 선별
	    
		        TargetVO targetDetailVo = new TargetVO();
				
		        targetDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
				targetDetailVo.setProductName(StringUtil.nvl(r_data[1],""));
				targetDetailVo.setProductPrice(StringUtil.nvl(r_data[2],""));
				targetDetailVo.setOrderCnt(StringUtil.nvl(r_data[3],""));
				targetDetailVo.setAddCnt(StringUtil.nvl(r_data[4],""));
				targetDetailVo.setLossCnt(StringUtil.nvl(r_data[5],""));
				targetDetailVo.setSafeStock(StringUtil.nvl(r_data[6],""));
				targetDetailVo.setHoldStock(StringUtil.nvl(r_data[7],""));
				targetDetailVo.setStockCnt(StringUtil.nvl(r_data[8],""));
				targetDetailVo.setStockDate(StringUtil.nvl(r_data[9],""));
				targetDetailVo.setVatRate(StringUtil.nvl(r_data[10],""));
		    	targetDetailVo.setEtc(StringUtil.nvl(r_data[11],""));
		    	targetDetailVo.setOrderCheck(StringUtil.nvl(r_data[13],"false"));
				
		        targetExcelList.add(targetDetailVo);
		      //  targetExcelList.add(targetDetailVo);
		      //  targetExcelList.add(targetDetailVo);
		      //  targetExcelList.add(targetDetailVo);
		      //  targetExcelList.add(targetDetailVo);
		      //  targetExcelList.add(targetDetailVo);
		      //  targetExcelList.add(targetDetailVo);
		      //  targetExcelList.add(targetDetailVo);
	        
	        }

		}

		String orderDate=targetVO.getOrderDate(); 
		String deliveryDate=targetVO.getDeliveryDate();
		String orderDates[]=targetVO.getOrderDate().split("-");
		String deliveryDates[]=targetVO.getDeliveryDate().split("-"); 
		
   		ModelAndView mv = new ModelAndView();
   		
   	    mv.addObject("targetVO", targetVO);
        mv.addObject("targetExcelList", targetExcelList);
        mv.addObject("orderDates1", orderDates[0]);
        mv.addObject("orderDates2", orderDates[1]);
        mv.addObject("orderDates3", orderDates[2]);
        mv.addObject("deliveryDates1", deliveryDates[0]);
        mv.addObject("deliveryDates2", deliveryDates[1]);
        mv.addObject("deliveryDates3", deliveryDates[2]);
   		
   		mv.setViewName("/order/targetDetailPrint");
  
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("OD");
		work.setWorkCode("OD003");
		work.setWorkKey1(""+t1);
		work.setSearchKey1(targetVO.getCompanyCode());
		
		if(targetVO.getPrintYn().equals("Y")){
		
		commonSvc.regiHistoryInsert(work);
		
		}
   		
   		return mv;
   	}
    /**
     * 발주서 다운로드
     *
     * @param 
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping("/order/orderdownload")
    public ModelAndView orderDownLoad(HttpServletRequest request) throws Exception {
     
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
    	logger.info("["+logid+"] Controller start : orderDownLoad ");
    	
    	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        String orderCode=request.getParameter("orderCode");
    	
        ResourceBundle rb = ResourceBundle.getBundle("config");
	    String orderFilePath = rb.getString("offact.upload.path") + "order/";
	    
	    int dateFolderbeginIndex=orderCode.length()-9;
	    int dateFolderendIndex=orderCode.length()-3;

	    String uploadFilePath = orderFilePath + orderCode.substring(dateFolderbeginIndex, dateFolderendIndex)+"/";
	    
	    String szFileName = uploadFilePath+orderCode+".html";                    // 파일 이름
	    
	    logger.info("filedownload szFileName :"+szFileName);
	    
	    File downloadFile = new File(szFileName);
	    
	    logger.info("filedownload "+downloadFile);
	    logger.info("filedownload isFile :"+downloadFile.isFile());

        return new ModelAndView("customFileDownLoadView", "downloadFile", downloadFile);
    }
    /**
     * 발주처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     * @throws DocumentException 
     */
    @RequestMapping(value = "/order/orderprocess", method = RequestMethod.POST)
    public @ResponseBody
    String orderProcess(@ModelAttribute("targetVO") TargetVO targetVO, 
    		          HttpServletRequest request, 
    		          HttpServletResponse response) throws BizException, DocumentException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		logger.info("["+logid+"] Controller start : targetVO" + targetVO);
		logger.info("["+logid+"] @@@@@@@@ : targetVO.getDeliveryEmail" + targetVO.getEmail());

		boolean orderResult=false;
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
		
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        //주문코드 생성
		String orderCode="";
		TargetVO orderCodeVO= new TargetVO();
	
		if(targetVO.getOrderCode().equals("X")){
			
			targetVO.setOrderCode("O"+targetVO.getCon_groupId()+strToday);
	
			orderCodeVO=targetSvc.getOrderCode(targetVO);
			orderCode=orderCodeVO.getOrderCode();
			
		}else{
			orderCode=targetVO.getOrderCode();
		}
		
	    String[] orders = request.getParameterValues("seqs");

		String orderDate=targetVO.getOrderDate();
		String deliveryDate=targetVO.getDeliveryDate();

	    targetVO.setOrderCode(orderCode);
	    targetVO.setOrderUserId(strUserId);
	    targetVO.setOrderState("03");
	    targetVO.setOrderDate(orderDate);
	    targetVO.setDeliveryDate(deliveryDate);

	    try{//01.발주처리
	    
	    	int dbResult=targetSvc.regiOrderProcess(orders , targetVO);
	    	
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "order0001";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "order0002\n[errorMsg] : "+errMsg;
	    	
	    }
	    
	    //이메일 주문서에 담기위한 데이타 선별값 START
		String[] orderList = orders; 		
   	    String[] r_data=null;

        List<TargetVO> targetEailList = new ArrayList();
        
	    for(int i=0;i<orderList.length;i++){
	
	        r_data = StringUtil.getTokens(orderList[i], "|");
	        
	        if(r_data[13].equals("true") && !r_data[3].equals("0") ){//체크박스 선택된 발주건 과 수량이 0이 아닌 대상건 선별
	    
		        TargetVO targetDetailVo = new TargetVO();
				
		        targetDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
				targetDetailVo.setProductName(StringUtil.nvl(r_data[1],""));
				targetDetailVo.setProductPrice(StringUtil.nvl(r_data[2],""));
				targetDetailVo.setOrderCnt(StringUtil.nvl(r_data[3],""));
				targetDetailVo.setAddCnt(StringUtil.nvl(r_data[4],""));
				targetDetailVo.setLossCnt(StringUtil.nvl(r_data[5],""));
				targetDetailVo.setSafeStock(StringUtil.nvl(r_data[6],""));
				targetDetailVo.setHoldStock(StringUtil.nvl(r_data[7],""));
				targetDetailVo.setStockCnt(StringUtil.nvl(r_data[8],""));
				targetDetailVo.setStockDate(StringUtil.nvl(r_data[9],""));
				targetDetailVo.setVatRate(StringUtil.nvl(r_data[10],""));
		    	targetDetailVo.setEtc(StringUtil.nvl(r_data[11],""));
		    	targetDetailVo.setOrderCheck(StringUtil.nvl(r_data[13],"false"));
				
		    	targetEailList.add(targetDetailVo);
		    	//targetEailList.add(targetDetailVo);
		    	//targetEailList.add(targetDetailVo);
		    	//targetEailList.add(targetDetailVo);
	        
	        }

		}
	   //이메일 주문서에 담기위한 데이타 선별값 END

		ResourceBundle rb = ResourceBundle.getBundle("config");
		
		String hostUrl = rb.getString("offact.host.url");
		
		String orderfilePath =rb.getString("offact.upload.path") + "order/";
	    String uploadFilePath = orderfilePath+strToday+"/";
	    
	    boolean check=setDirectory(uploadFilePath);
	    
	    String szFileName = uploadFilePath+orderCode+".html";                    // 파일 이름
	    String pdfFileName = uploadFilePath+"[PDF]"+orderCode+".html";                    // PDF용 파일 이름
        String szContent = "";
        String pdfszContent = "";
        
        String orderDates[]=targetVO.getOrderDate().split("-");
        String deliveryDates[]=targetVO.getDeliveryDate().split("-"); 
        
    	String [] getToMails=targetVO.getEmail().split(";");
    	String [] getToMail_Ccs=targetVO.getEmail_cc().split(";");
        
		try{//메일전송 발주처리
            /* 파일을 생성해서 내용 쓰기 */
	        
	        File file = new File(szFileName);                        // 파일 생성
	        OutputStream out = new FileOutputStream(file);            // 파일에 문자를 적을 스트림 생성

	        szContent += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
		    szContent += "<html>";
	        szContent += "<head>";
	        szContent += "<title>(주)애디스다이렉트</title>";
	        szContent += "<meta http-equiv='X-UA-Compatible' content='IE=edge' />";  
	        szContent += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";  
	        szContent += "<style type='text/css'>"; 
	        szContent += "<!--";
	        szContent += " table {";
	        szContent += "border-collapse: collapse;";
	        szContent += "}";
	        szContent += "table, th, td {";
	        szContent += "border: 1px solid black;";
	        szContent += "}";
			szContent += ".style1 {";
		    szContent += "	font-size: 30px;";
			szContent += "	font-weight: bold;";
			szContent += "	font-family: '돋움체', '굴림체', Seoul;";
	        szContent += "}";
	        szContent += "body {color : #000000; background : #FFFFFF; font-family : 굴림,'Times New Roman'; font-size : 12pt;}";
	        szContent += "@page{  size:auto; margin : 10mm;  }";
	        szContent += "h1 {page-break-before: always;}";
			szContent += "-->";
			szContent += "</style>";
			
			szContent += "</head>";

			szContent += "<body>";
			szContent += "<div align='center'>";
			
			int num=0;
			int totalnum=targetEailList.size();
			int etcnum=0;
			int maxlist=20;
			int resultlist=totalnum;
			int removecnt=0;
			int numcnt=0;

			int pagenum = Math.floorDiv(totalnum, maxlist);
			
			int pageCal = totalnum%maxlist;
			
			if(pageCal==0){
				pagenum=pagenum-1;
			}
			
			for(int x=0; x<=pagenum; x++){
			
				if(x!=0){
					szContent += "<h1></h1>";
				}
	
				szContent += "<table width='722' height='900'>";
				szContent += "<tr>"; 
				szContent += "<td height='55' colspan='12' align='center'><span class='style1' >상 품 주 문 서</span></td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += " <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>수<br></br>신</td>";
				szContent += " <td width='90' align='center'>&nbsp;회사명</td>";
				szContent += " <td colspan='5' align='center'>&nbsp;"+targetVO.getDeliveryName()+"</td>";
				szContent += " <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>발<br></br>신</td>";
				szContent += " <td width='90' align='center' >&nbsp;회사명</td>";
				szContent += " <td colspan='3' align='center'>&nbsp;"+targetVO.getOrderName()+"</td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += "<td rowspan='4' align='center' >담당자</td>";
				szContent += "<td colspan='5' align='left'>&nbsp;성명:"+targetVO.getDeliveryCharge()+"</td>";
				szContent += "<td rowspan='4' align='center' >담당자</td>";
				szContent += "<td colspan='3' align='left'>&nbsp;성명:"+targetVO.getOrderCharge()+"</td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += "<td colspan='5' align='left'>&nbsp;핸드폰:"+targetVO.getMobilePhone()+"</td>";
				szContent += "<td colspan='3' align='left'>&nbsp;핸드폰:"+targetVO.getOrderMobilePhone()+"</td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += "<td colspan='5' align='left'>&nbsp;TEL:"+targetVO.getTelNumber()+"&nbsp;/&nbsp;FAX:"+targetVO.getFaxNumber()+"</td>";
				szContent += "<td colspan='3' align='left'>&nbsp;TEL:"+targetVO.getOrderTelNumber()+"&nbsp;/&nbsp;FAX:"+targetVO.getOrderFaxNumber()+"</td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += "<td colspan='5' align='left'>&nbsp;email:"+getToMails[0]+"</td>";
				szContent += "<td colspan='3' align='left'>&nbsp;email:"+targetVO.getOrderEmail()+"</td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += "<td align='center' >발주일자</td>";
				szContent += "<td width='35' align='center'><div align='right'>"+orderDates[0]+"년 </div></td>";
				szContent += "<td width='25' align='center'>&nbsp;"+orderDates[1]+"</td>";
				szContent += "<td width='25' align='center'>월</td>";
				szContent += "<td width='25' align='center'>&nbsp;"+orderDates[2]+"</td>";
				szContent += "<td width='25' align='center'>일</td>";
				szContent += "<td rowspan='2' align='center' >배송주소</td>";
				szContent += "<td rowspan='2' colspan='3' align='left'>&nbsp;"+targetVO.getOrderAddress()+"</td>";
				szContent += "</tr>";
	            szContent += "<tr>";
				szContent += "<td align='center' >납품일자</td>";
				szContent += "<td align='center'><div align='right'>"+deliveryDates[0]+"년 </div></td>";
				szContent += "<td align='center'>&nbsp;"+deliveryDates[1]+"</td>";
				szContent += "<td align='center'>월</td>";
				szContent += "<td align='center'>&nbsp;"+deliveryDates[2]+"</td>";
				szContent += "<td align='center'>일</td>";
				szContent += "</tr>";
	            szContent += "<tr>";
				szContent += "<td align='center'>납품방법</td>";
				szContent += "<td colspan='5' align='center'>&nbsp;"+targetVO.getDeliveryMethod()+"</td>";
				szContent += "<td align='center'>결제방법</td>";
				szContent += "<td colspan='3' align='center'>&nbsp;"+targetVO.getPayMethod()+"</td>";
				szContent += "</tr>";
	
				szContent += "<tr>";
				szContent += "<td colspan='2' align='center' >메모</td>";
				szContent += "<td colspan='10' align='left'>&nbsp;"+targetVO.getMemo()+"</td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += "<td colspan='12' align='center' height='27'><div align='left'>&nbsp;1.아래와 같이 발주합니다.</div></td>";
				szContent += "</tr>";
				szContent += "<tr>";
				szContent += "<td width='30' align='center' height='27'>번 호</td>";
				szContent += "<td colspan='9' align='center'>상 품 명</td>";
				szContent += "<td width='40' align='center'>수량</td>";
				szContent += "<td width='180' align='center'>비 고</td>";
				szContent += "</tr>";
				
				if(resultlist<=maxlist){
					
					etcnum=maxlist-resultlist;
					
					for(int i=0;i<resultlist;i++){
						num++;
						TargetVO targetDetaiList = new TargetVO();
						targetDetaiList=targetEailList.get(num-1);
						
						numcnt++;
	
				        szContent += "<tr>";
						szContent += "<td align='center' height='27'>"+numcnt+"</td>";
						szContent += "<td colspan='9' align='left'>&nbsp;"+targetDetaiList.getProductName()+"</td>";
						szContent += "<td align='center'>"+targetDetaiList.getOrderCnt()+"</td>";
						szContent += "<td  align='left'>&nbsp;"+targetDetaiList.getEtc()+"</td>";
						szContent += "</tr>";
				
					}
	
					for(int y=0;y<etcnum;y++){
						
						szContent += "<tr>";
						szContent += "<td align='center' height='27'>&nbsp;</td>";
						szContent += "<td colspan='9' align='center'>&nbsp;</td>";
						szContent += "<td align='center'>&nbsp;</td>";
						szContent += "<td  align='center'>&nbsp;</td>";
						szContent += "</tr>";
				
					}
				
				}else if(resultlist>maxlist){
					
					for(int z=0;z<maxlist;z++){
						num++;
						TargetVO targetDetaiList = new TargetVO();
						targetDetaiList=targetEailList.get(num-1);
						
						numcnt++;
							
						szContent += "<tr>";
						szContent += "<td align='center' height='27'>"+numcnt+"</td>";
						szContent += "<td colspan='9' align='left'>&nbsp;"+targetDetaiList.getProductName()+"</td>";
						szContent += "<td align='center'>"+targetDetaiList.getOrderCnt()+"</td>";
						szContent += "<td align='left'>&nbsp;"+targetDetaiList.getEtc()+"</td>";
						szContent += "</tr>";

					}
					
					resultlist=resultlist-maxlist;
					
				}
			
				szContent += "</table>";
			}

			szContent += "</div>";
			szContent += "</body>";
			szContent += "</html>";
	        
	        out.write(szContent.getBytes());                        // 파일에 쓰기
	        out.close();                                            // 파일 쓰기 스트림 닫기
       
            //추가 주문서 레이아웃(PDF or JSP용)
/*
	        File pdffile = new File(pdfFileName);                        // 파일 생성
	        OutputStream pdfout = new FileOutputStream(pdffile);            // 파일에 문자를 적을 스트림 생성

	        pdfszContent += "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
	        pdfszContent += "<html>";
	        pdfszContent += "<head>";
	        pdfszContent += "<title>(주)애디스다이렉트</title>";
	        pdfszContent += "<meta http-equiv='X-UA-Compatible' content='IE=edge' />";  
	        pdfszContent += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";  
	        pdfszContent += "<style type='text/css'>"; 
	        pdfszContent += " table {";
	        pdfszContent += "border-collapse: collapse;";
	        pdfszContent += "}";
	        pdfszContent += "table, th, td {";
	        pdfszContent += "border: 1px solid black;";
	        pdfszContent += "}";
	        pdfszContent += ".style1 {";
	        pdfszContent += "	font-size: 30px;";
	        pdfszContent += "	font-weight: bold;";
	        pdfszContent += "	font-family: '돋움체', '굴림체', Seoul;";
	        pdfszContent += "}";
	        pdfszContent += "body {color : #000000; background : #FFFFFF; font-family : MalgunGothic, 굴림,'Times New Roman'; font-size : 12pt;}";
	        pdfszContent += "@page{  size:auto; margin : 10mm;  }";
	        pdfszContent += "h1 {page-break-before: always;}";
	        pdfszContent += "</style>";
	        pdfszContent += "</head>";

	       // pdfszContent += "<body style='font-family: MalgunGothic;'>";
	        pdfszContent += "<body>";
	        pdfszContent += "<div align='center'>";
			
			int pdfnum=0;
			int pdftotalnum=targetEailList.size();
			int pdfetcnum=0;
			int pdfmaxlist=20;
			int pdfresultlist=pdftotalnum;
			int pdfremovecnt=0;
			int pdfnumcnt=0;

			int pdfpagenum = Math.floorDiv(pdftotalnum, pdfmaxlist);
			
			int pdfpageCal = pdftotalnum%pdfmaxlist;
			
			if(pdfpageCal==0){
				pdfpagenum=pdfpagenum-1;
			}
			
			for(int pdfx=0; pdfx<=pdfpagenum; pdfx++){
			
				if(pdfx!=0){
					pdfszContent += "<h1></h1>";
				}
	
				pdfszContent += "<table width='722' height='900'>";
				pdfszContent += "<tr>"; 
				pdfszContent += "<td height='55' colspan='12' align='center'><span class='style1' >상 품 주 문 서</span></td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += " <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>수<br></br>신</td>";
				pdfszContent += " <td width='90' align='center'>&nbsp;회사명</td>";
				pdfszContent += " <td colspan='5' align='center'>&nbsp;"+targetVO.getDeliveryName()+"</td>";
				pdfszContent += " <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>발<br></br>신</td>";
				pdfszContent += " <td width='90' align='center' >&nbsp;회사명</td>";
				pdfszContent += " <td colspan='3' align='center'>&nbsp;"+targetVO.getOrderName()+"</td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += "<td rowspan='4' align='center' >담당자</td>";
				pdfszContent += "<td colspan='5' align='left'>&nbsp;성명:"+targetVO.getDeliveryCharge()+"</td>";
				pdfszContent += "<td rowspan='4' align='center' >담당자</td>";
				pdfszContent += "<td colspan='3' align='left'>&nbsp;성명:"+targetVO.getOrderCharge()+"</td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += "<td colspan='5' align='left'>&nbsp;핸드폰:"+targetVO.getMobilePhone()+"</td>";
				pdfszContent += "<td colspan='3' align='left'>&nbsp;핸드폰:"+targetVO.getOrderMobilePhone()+"</td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += "<td colspan='5' align='left'>&nbsp;TEL:"+targetVO.getTelNumber()+"&nbsp;/&nbsp;FAX:"+targetVO.getFaxNumber()+"</td>";
				pdfszContent += "<td colspan='3' align='left'>&nbsp;TEL:"+targetVO.getOrderTelNumber()+"&nbsp;/&nbsp;FAX:"+targetVO.getOrderFaxNumber()+"</td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += "<td colspan='5' align='left'>&nbsp;email:"+getToMails[0]+"</td>";
				pdfszContent += "<td colspan='3' align='left'>&nbsp;email:"+targetVO.getOrderEmail()+"</td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += "<td align='center' >발주일자</td>";
				pdfszContent += "<td width='35' align='center'><div align='right'>"+orderDates[0]+"년 </div></td>";
				pdfszContent += "<td width='25' align='center'>&nbsp;"+orderDates[1]+"</td>";
				pdfszContent += "<td width='25' align='center'>월</td>";
				pdfszContent += "<td width='25' align='center'>&nbsp;"+orderDates[2]+"</td>";
				pdfszContent += "<td width='25' align='center'>일</td>";
				pdfszContent += "<td rowspan='2' align='center' >배송주소</td>";
				pdfszContent += "<td rowspan='2' colspan='3' align='left'>&nbsp;"+targetVO.getOrderAddress()+"</td>";
				pdfszContent += "</tr>";
	            pdfszContent += "<tr>";
				pdfszContent += "<td align='center' >납품일자</td>";
				pdfszContent += "<td align='center'><div align='right'>"+deliveryDates[0]+"년 </div></td>";
				pdfszContent += "<td align='center'>&nbsp;"+deliveryDates[1]+"</td>";
				pdfszContent += "<td align='center'>월</td>";
				pdfszContent += "<td align='center'>&nbsp;"+deliveryDates[2]+"</td>";
				pdfszContent += "<td align='center'>일</td>";
				pdfszContent += "</tr>";
	            pdfszContent += "<tr>";
				pdfszContent += "<td align='center'>납품방법</td>";
				pdfszContent += "<td colspan='5' align='center'>&nbsp;"+targetVO.getDeliveryMethod()+"</td>";
				pdfszContent += "<td align='center'>결제방법</td>";
				pdfszContent += "<td colspan='3' align='center'>&nbsp;"+targetVO.getPayMethod()+"</td>";
				pdfszContent += "</tr>";
	
				pdfszContent += "<tr>";
				pdfszContent += "<td colspan='2' align='center' >메모</td>";
				pdfszContent += "<td colspan='10' align='left'>&nbsp;"+targetVO.getMemo()+"</td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += "<td colspan='12' align='center' height='27'><div align='left'>&nbsp;1.아래와 같이 발주합니다.</div></td>";
				pdfszContent += "</tr>";
				pdfszContent += "<tr>";
				pdfszContent += "<td width='30' align='center' height='27'>번 호</td>";
				pdfszContent += "<td colspan='9' align='center'>상 품 명</td>";
				pdfszContent += "<td width='40' align='center'>수량</td>";
				pdfszContent += "<td width='180' align='center'>비 고</td>";
				pdfszContent += "</tr>";
				
				if(pdfresultlist<=pdfmaxlist){
					
					pdfetcnum=pdfmaxlist-pdfresultlist;
					
					for(int pdfi=0;pdfi<pdfresultlist;pdfi++){
						pdfnum++;
						TargetVO targetDetaiList = new TargetVO();
						targetDetaiList=targetEailList.get(pdfnum-1);
						
						pdfnumcnt++;
	
				        pdfszContent += "<tr>";
						pdfszContent += "<td align='center' height='27'>"+pdfnumcnt+"</td>";
						pdfszContent += "<td colspan='9' align='left'>&nbsp;"+targetDetaiList.getProductName()+"</td>";
						pdfszContent += "<td align='center'>"+targetDetaiList.getOrderCnt()+"</td>";
						pdfszContent += "<td align='left'>&nbsp;"+targetDetaiList.getEtc()+"</td>";
						pdfszContent += "</tr>";
				
					}
	
					for(int pdfy=0;pdfy<pdfetcnum;pdfy++){
						
						pdfszContent += "<tr>";
						pdfszContent += "<td align='center' height='27'>&nbsp;</td>";
						pdfszContent += "<td colspan='9' align='center'>&nbsp;</td>";
						pdfszContent += "<td align='center'>&nbsp;</td>";
						pdfszContent += "<td align='center'>&nbsp;</td>";
						pdfszContent += "</tr>";
				
					}
				
				}else if(pdfresultlist>pdfmaxlist){
					
					for(int pdfz=0;pdfz<pdfmaxlist;pdfz++){
						pdfnum++;
						TargetVO targetDetaiList = new TargetVO();
						targetDetaiList=targetEailList.get(pdfnum-1);
						
						pdfnumcnt++;
							
						pdfszContent += "<tr>";
						pdfszContent += "<td align='center' height='27'>"+pdfnumcnt+"</td>";
						pdfszContent += "<td colspan='9' align='left'>&nbsp;"+targetDetaiList.getProductName()+"</td>";
						pdfszContent += "<td align='center'>"+targetDetaiList.getOrderCnt()+"</td>";
						pdfszContent += "<td align='left'>&nbsp;"+targetDetaiList.getEtc()+"</td>";
						pdfszContent += "</tr>";

					}
					
					pdfresultlist=pdfresultlist-pdfmaxlist;
					
				}
			
				pdfszContent += "</table>";
				pdfszContent += "<br></br><br></br>";
			}
			pdfszContent += "</div>";
			pdfszContent += "</body>";
			pdfszContent += " </html>";
	        
			pdfout.write(pdfszContent.getBytes());                        // 파일에 쓰기
			pdfout.close();                           
*/	  
	        // PDF 변환1 (linux서버에서 한글변환문제 있음)
			/*
	        // step 1
	        Document document_pdf = new Document(PageSize.A4, 10, 10, 10, 10); // 용지 및 여백 설정

		    try{
		        // step 2
		        PdfWriter writer = PdfWriter.getInstance(document_pdf, new FileOutputStream(uploadFilePath+"[TEST]"+orderCode+".pdf"));
		        // step 3
		        document_pdf.open();
		        // step 4
		        XMLWorkerHelper.getInstance().parseXHtml(writer, document_pdf,
		                new FileInputStream(pdfFileName)); 
		        //step 5
		        document_pdf.close();
		        logger.debug("PDF Created! :");
		        
		        
	        }catch(Exception e){
	        	//step 5
	        	document_pdf.close();
	            logger.info("["+logid+"] pdf convert error : "+e);    
	        }
	        */   
			
	        // PDF 변환2(폰트 및 CSS적용)
/*		    
	        Document document = new Document(PageSize.A4, 0, 0, 0, 0); // 용지 및 여백 설정
	             
	        // PdfWriter 생성
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(uploadFilePath+orderCode+".pdf")); // 바로 다운로드.
	        //PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
	        writer.setInitialLeading(12.5f);
	         
	        // 파일 다운로드 설정
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Transper-Encoding", "binary");
	        response.setHeader("Content-Disposition", "inline; filename=" + orderCode + ".pdf");
	         
	        // Document 오픈
	        document.open();
	        XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
	             
	        // CSS
	        CSSResolver cssResolver = new StyleAttrCSSResolver();
	        CssFile cssFile = helper.getCSS(new FileInputStream(orderfilePath+"/css/pdf.css"));
	        cssResolver.addCss(cssFile);
	             
	        // HTML, 폰트 설정
	        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	        fontProvider.register(orderfilePath+"/font/H2MKPB.TTF", "MalgunGothic"); // MalgunGothic은 alias,
	        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	         
	        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	         
	        // Pipelines
	        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	         
	        XMLWorker worker = new XMLWorker(css, true);
	        XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
	           
	        xmlParser.parse(new FileInputStream(pdfFileName));
	         
	        document.close();
	        writer.close();

	       // 폰트 설정에서 별칭으로 줬던 "MalgunGothic"을 html 안에 폰트로 지정한다.
	       //String htmlStr = "<html><head><body style='font-family: MalgunGothic;'>"
	       //             + "<p>PDF 안에 들어갈 내용입니다.</p>"
	       //             + "<h3>한글, English, 漢字.</h3>"
	       //         + "</body></head></html>";
	         
	       //  StringReader strReader = new StringReader(htmlStr);
	       //  xmlParser.parse(strReader);
	        
	
            /////image변환//////////////////////////////////////////////////////////////////////////////////////
	        
		    
	        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
	        //imageGenerator.loadHtml(pdfszContent);
	        imageGenerator.loadUrl(hostUrl+"/addon/order/orderdownload?orderCode="+orderCode);
	        imageGenerator.saveAsImage(uploadFilePath+orderCode+".png");
	        imageGenerator.saveAsHtmlWithMap(orderCode+".html", orderCode+".png");
	        
	        
	        ///////////////////////////////////////////////////////////////////////////////////////////////////        
	*/		        
			EmailVO mail = new EmailVO();
			
			List<String> toEmails= new ArrayList();
			List<String> toEmail_Ccs= new ArrayList();
			List<String> attcheFileName= new ArrayList();
			List<File> files = new ArrayList();

			for(int m=0;m<getToMails.length;m++){	
				toEmails.add(getToMails[m]);	
			}
			
			for(int c=0;c<getToMail_Ccs.length;c++){	
				toEmail_Ccs.add(getToMail_Ccs[c]);	
			}

			attcheFileName.add(orderCode+".html");
			files.add(file);
	
			//추가 파일 첨부
			/*
			attcheFileName.add(orderCode+".pdf");
			String pdfConvertFileName = uploadFilePath+orderCode+".pdf";                    // PDF 파일 이름
			File pdfConvertFile = new File(pdfConvertFileName);                        // 파일 생성
			files.add(pdfConvertFile);
			
			attcheFileName.add("[TEST]"+orderCode+".pdf");
			String testpdfConvertFileName = uploadFilePath+"[TEST]"+orderCode+".pdf";                    // PDF 파일 이름
			File testpdfConvertFile = new File(testpdfConvertFileName);                        // 파일 생성
			files.add(testpdfConvertFile);
			*/
			
			mail.setToEmails(toEmails);
			mail.setToEmail_Ccs(toEmail_Ccs);
			mail.setAttcheFileName(attcheFileName);
			mail.setFile(files);

			mail.setFromEmail(orderfromemail);
			mail.setMsg(targetVO.getOrderName()+" 상품주문서 메일입니다.<br>"+targetVO.getDeliveryDate()+"까지 납품 부탁드립니다.<br><br><br>*첨부파일이 정상적으로 확인이 안되실 경우 아래 링크로 다시한번 다운로드 부탁드립니다.<br><a href='"+hostUrl+"/addon/order/orderdownload?orderCode="+orderCode+"' >[발주서 다운로드]</a><br><br><font style='color:red'>*본 메일은 발주전용 메일입니다.회신시 아래 연락처 정보의 메일로 회신 부탁드립니다.</font><br><br>[연락처  정보]<br><br>(담당자)  "+targetVO.getOrderCharge()+"<br>(Tel)  "+
			targetVO.getOrderTelNumber()+"<br>(핸드폰)  "+targetVO.getOrderMobilePhone()+"<br>(E-Mail)  "+targetVO.getOrderEmail()+"<br>(FAX)  "+targetVO.getOrderFaxNumber());
			
			
			mail.setSubject("애디스 다이렉트 발주서");
	
			try{
				
				orderResult=mailSvc.sendMail(mail);
				
				logger.debug("mail result :"+orderResult);
				
				if(orderResult==false){
					
					//log Controller execute time end
			       	long t2 = System.currentTimeMillis();
			       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	
			        return "order0003";
					
				}
				
				try{
					//SMS발송
					SmsVO smsVO = new SmsVO();
					SmsVO resultSmsVO = new SmsVO();
					
					smsVO.setSmsId(smsId);
					smsVO.setSmsPw(smsPw);
					smsVO.setSmsType(smsType);
					smsVO.setSmsTo(targetVO.getMobilePhone());
					smsVO.setSmsFrom(targetVO.getOrderMobilePhone());
					smsVO.setSmsMsg(targetVO.getSms());
					smsVO.setSmsUserId(strUserId);
					
					logger.debug("sms targetVO.getMobilePhone() :"+targetVO.getMobilePhone());
					logger.debug("sms targetVO.getOrderMobilePhone() :"+targetVO.getOrderMobilePhone());
					logger.debug("sms targetVO.getSms() :"+targetVO.getSms());
					
					logger.debug("#########devOption :"+devOption);
					String[] devSmss= devSms.split("\\^");
					
		    		if(devOption.equals("true")){
						for(int i=0;i<devSmss.length;i++){
							
							if(devSmss[i].equals(targetVO.getMobilePhone().trim().replace("-", ""))){
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
				
				
			}catch(BizException e){
		       	
		    	e.printStackTrace();
		        String errMsg = e.getMessage();
		        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

		        return "order0003\n[errorMsg] : "+errMsg;
		    	
		    }
		
		}catch(IOException e){
			
			e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "order0004\n[errorMsg] : "+errMsg;
		}
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("OD");
		work.setWorkCode("OD004");
		work.setWorkKey1(orderCode);
		work.setSearchKey1(targetVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);

		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return "order0000";
    }
    /**
     * 보류 처리
     *
     * @param TargetVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/deferprocess"})
    public @ResponseBody
    String deferProcess(@ModelAttribute("targetVO") TargetVO targetVO,
    		           @RequestParam(value="arrDeferProductId", required=false, defaultValue="") String arrDeferProductId,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : targetVO" + targetVO);
			
		String deferResult="defer0000";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
       
	    String[] defers = request.getParameterValues("seqs");

        logger.info("@#@#@# targetVO.getDefer_reason : " + targetVO.getDeferReason());
        logger.info("@#@#@# arrDeferProductId : " + arrDeferProductId);
        logger.info("@#@#@# safeOrderCnt : " + targetVO.getSafeOrderCnt());
	    
	    String orderDate=targetVO.getOrderDate();
	    String deliveryDate=targetVO.getDeliveryDate();
	    
	   //주문코드 생성
  		String orderCode="";
  		
  		if(targetVO.getOrderCode().equals("X")){
  			orderCode="O"+targetVO.getCon_groupId()+t1;
  		}else{
  			orderCode=targetVO.getOrderCode();
  		}
		 
	    targetVO.setOrderCode(orderCode);
	    targetVO.setDeferUserId(strUserId);
	    targetVO.setOrderState("02");
	    targetVO.setOrderDate(orderDate);
	    targetVO.setDeliveryDate(deliveryDate);
  
        try{//01.보류처리
    	    
        	int dbResult=targetSvc.regiDeferProcess(defers , targetVO ,arrDeferProductId);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "defer0001";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "defer0002\n[errorMsg] : "+errMsg;
	    	
	    }
        
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("OD");
		work.setWorkCode("OD001");
		work.setWorkKey1(orderCode);
		work.setSearchKey1(targetVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
    }
    /**
     * 보류 처리
     *
     * @param TargetVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/defercancel"})
    public @ResponseBody
    String deferCancel(@ModelAttribute("targetVO") TargetVO targetVO,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : targetVO" + targetVO);
			
		String deferResult="defer0000";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
       
        logger.info("@#@#@# targetVO.getDefer_reason : " + targetVO.getDeferReason());
	    
	    targetVO.setDeferUserId(strUserId);
	    targetVO.setOrderState("08");
	    targetVO.setDeletedYn("Y");
	    targetVO.setDeletedUserId(strUserId);
  
        try{//01.보류처리
    	    
        	int dbResult=targetSvc.regiDeferCancel(targetVO);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "defer0001";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "defer0002\n[errorMsg] : "+errMsg;
	    	
	    }
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("OD");
		work.setWorkCode("OD002");
		work.setWorkKey1(targetVO.getOrderCode());
		work.setSearchKey1(targetVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
    }
	 /**
     * 검수대상 화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/ordermanage")
    public ModelAndView orderManage(HttpServletRequest request, 
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
        
        OrderVO orderConVO = new OrderVO();
        
        orderConVO.setGroupId(groupId);
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        Date deliveryTime = new Date();
        int movedate=-7;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
        
        orderConVO.setStart_orderDate(strDeliveryDay);
        orderConVO.setEnd_orderDate(strToday);

        // 조회조건저장
        mv.addObject("orderConVO", orderConVO);

        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(groupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (발주상태코드)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("OD02");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);
       
        mv.setViewName("/order/orderManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 검수대상 목록조회
     * 
     * @param orderConVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/orderpagelist")
    public ModelAndView orderPageList(@ModelAttribute("orderConVO") OrderVO orderConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderConVO" + orderConVO);

        ModelAndView mv = new ModelAndView();
        List<OrderVO> orderList = null;
        OrderVO stateVO = new OrderVO();

        // 조직값 null 일때 공백처리
        if (orderConVO.getCon_groupId() == null) {
        	orderConVO.setCon_groupId("");
        }

        // 상태 값 null 일때 공백처리
        if (orderConVO.getCon_orderState() == null) {
        	orderConVO.setCon_groupId("G00000");
        }

        // 조회조건저장
        mv.addObject("orderConVO", orderConVO);

        // 페이징코드
        orderConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(orderConVO.getCurPage(), orderConVO.getRowCount()));
        orderConVO.setPage_limit_val2(StringUtil.nvl(orderConVO.getRowCount(), "10"));
        
        // 검수대상목록조회
        orderList = orderSvc.getOrderPageList(orderConVO);
        mv.addObject("orderList", orderList);

        // totalCount 조회
        String totalCount = String.valueOf(orderSvc.getOrderCnt(orderConVO));
        mv.addObject("totalCount", totalCount);
        
        // 상태통계 조회
        stateVO= orderSvc.getStateCnt(orderConVO);
        mv.addObject("stateVO", stateVO);

        mv.setViewName("/order/orderPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 검수대상 상세조회
     * 
     * @param orderCode
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/orderdetailview")
    public ModelAndView orderDetailview( HttpServletRequest request, 
    		                              HttpServletResponse response,
    		                              String orderCode) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderCode : [" + orderCode+"]");

        ModelAndView mv = new ModelAndView();
        List<OrderVO> orderDetailList = null;
        
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
         
        String strToday = simpleDateFormat.format(currentTime);
        
        OrderVO orderConVO = new OrderVO();
        OrderVO orderVO = new OrderVO();

        orderConVO.setOrderCode(orderCode);

        // 조회조건저장
        mv.addObject("orderConVO", orderConVO);
        
        //검수대상 정보
        orderVO=orderSvc.getOrderDetail(orderConVO);
        
        //검수대상 상세정보
        orderDetailList=orderSvc.getOrderDetailList(orderConVO);
 
        mv.addObject("orderVO", orderVO);
        mv.addObject("orderDetailList", orderDetailList);
   
        mv.setViewName("/order/orderDetailView");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 검수보류 처리
     *
     * @param OrderVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/orderdeferprocess"})
    public @ResponseBody
    String orderDeferProcess(@ModelAttribute("orderVO") OrderVO orderVO,
    		                @RequestParam(value="arrCheckProductId", required=false, defaultValue="") String arrCheckProductId,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderVO" + orderVO);
			
		String deferResult="defer0000";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
       
	    String[] defers = request.getParameterValues("seqs");

        logger.info("@#@#@# orderVO.getDefer_reason : " + orderVO.getDeferReason());
 
	    orderVO.setDeferUserId(strUserId);
	    orderVO.setOrderState("04");
  
        try{//01.보류처리
    	    
        	int dbResult=orderSvc.regiDeferProcess(defers , orderVO , arrCheckProductId);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "defer0001";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "defer0002\n[errorMsg] : "+errMsg;
	    	
	    }
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH001");
		work.setWorkKey1(orderVO.getOrderCode());
		work.setSearchKey1(orderVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
        
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
    }
    /**
     * 검수보류폐기 처리
     *
     * @param OrderVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/orderdefercancel"})
    public @ResponseBody
    String orderDeferCancel(@ModelAttribute("orderVO") OrderVO orderVO,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderVO" + orderVO);
			
		String deferResult="defer0000";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        logger.info("@#@#@# orderVO.getDefer_reason : " + orderVO.getDeferReason());
 
	    orderVO.setDeferUserId(strUserId);
	    orderVO.setOrderState("03");//검수대기로변경
  
        try{//01.보류처리
    	    
        	int dbResult=orderSvc.regiDeferCancel(orderVO);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "defer0001";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "defer0002\n[errorMsg] : "+errMsg;
	    	
	    }
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH002");
		work.setWorkKey1(orderVO.getOrderCode());
		work.setSearchKey1(orderVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
    }
    /**
     * 발주 취소 처리
     *
     * @param OrderVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/ordercancel"})
    public @ResponseBody
    String orderCancel(@ModelAttribute("orderVO") OrderVO orderVO,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderVO" + orderVO);
			
		String deferResult="order0010";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        try{//01.처리사유
        	
        	CommentVO commentVO =new CommentVO();

        	commentVO.setOrderCode(orderVO.getOrderCode());
        	commentVO.setCommentUserId(strUserId);
        	commentVO.setCommentCategory("08");
        	commentVO.setComment(orderVO.getDeferReason());
    	    
        	int dbResult=commonSvc.regiCommentInsert(commentVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }

	    orderVO.setDeletedYn("Y");
	    orderVO.setDeletedUserId(strUserId);
	    orderVO.setOrderState("09");//발주취소로변경
  
        try{//01.취소처리
    	    
        	int dbResult=orderSvc.regiOrderCancel(orderVO);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "order0011";
		        
	    	}
	    	
	    	try{

				//SMS발송
				SmsVO smsVO = new SmsVO();
				SmsVO resultSmsVO = new SmsVO();
				
				smsVO.setSmsId(smsId);
				smsVO.setSmsPw(smsPw);
				smsVO.setSmsType(smsType);
				smsVO.setSmsTo(orderVO.getMobilePhone());
				smsVO.setSmsFrom(orderVO.getOrderMobilePhone());
				smsVO.setSmsMsg(orderVO.getDeliveryCharge()+"님 '"+orderVO.getOrderName()+"' 에서 '"+strToday+"'자로 '"+orderVO.getOrderCode()+"'의 발주건이 취소되었습니다.");
				smsVO.setSmsUserId(strUserId);
				
				logger.debug("sms orderVO.getMobilePhone() :"+orderVO.getMobilePhone());
				logger.debug("sms orderVO.getOrderMobilePhone() :"+orderVO.getOrderMobilePhone());

				logger.debug("#########devOption :"+devOption);
				String[] devSmss= devSms.split("\\^");
				
	    		if(devOption.equals("true")){
					for(int i=0;i<devSmss.length;i++){
						
						if(devSmss[i].equals(orderVO.getMobilePhone().trim().replace("-", ""))){
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
	    	
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "order0012\n[errorMsg] : "+errMsg;
	    	
	    }
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH004");
		work.setWorkKey1(orderVO.getOrderCode());
		work.setSearchKey1(orderVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
    }
    /**
     * 발주 등록 처리
     *
     * @param OrderVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/orderbuy"})
    public @ResponseBody
    String orderBuy(@ModelAttribute("orderVO") OrderVO orderVO,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderVO" + orderVO);
			
		String deferResult="order0020";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

	    orderVO.setDeletedYn("N");
	    orderVO.setBuyUserId(strUserId);
	    orderVO.setOrderState("07");//등록완료로변경
  
        try{//01.취소처리
    	    
        	int dbResult=orderSvc.regiOrderBuy(orderVO);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "order0021";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "order0022\n[errorMsg] : "+errMsg;
	    	
	    }
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH010");
		work.setWorkKey1(orderVO.getOrderCode());
		work.setSearchKey1(orderVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
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
    @RequestMapping(value = "/order/memomanage")
    public ModelAndView memoManage(HttpServletRequest request, 
    		                       HttpServletResponse response,
		                           String orderCode,
		                           String category,
		                           String memo,
		                           String companyCode) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start memo:"+memo);

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
        mv.addObject("orderCode", orderCode);
        mv.addObject("category", category);
        mv.addObject("companyCode", companyCode);    
        mv.addObject("memo", memo);
        
        CommentVO commentVO = new CommentVO();
        
        commentVO.setOrderCode(orderCode);
        commentVO.setCommentCategory(category);
        
        List<CommentVO> commentList = new ArrayList();

        //품목 비고 정보
        commentList=commonSvc.getCommentList(commentVO);

        mv.addObject("commentList", commentList);
        

        mv.setViewName("/order/memoManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 메모내용
     * 
     * @param orderCode
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/memolist")
    public ModelAndView memoList( @ModelAttribute("commentVO") CommentVO commentVO,
    		                      HttpServletRequest request) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : commentVO : " + commentVO);

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
         
        String strToday = simpleDateFormat.format(currentTime);

        List<CommentVO> commentList = new ArrayList();

        //품목 비고 정보
        commentList=commonSvc.getCommentList(commentVO);

        mv.addObject("commentList", commentList);
        
        mv.setViewName("/order/memoList");
        
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
    @RequestMapping(value = "/order/memoaddlist")
    public ModelAndView memoAddList( @ModelAttribute("commentVO") CommentVO commentVO,
    		                      HttpServletRequest request) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : commentVO : " + commentVO);

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
        
        commentVO.setCommentUserId(strUserId);

        try{//01.메모추가
    	    
        	int dbResult=commonSvc.regiCommentInsert(commentVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }
        
        // 조회조건저장
        mv.addObject("orderCode", commentVO.getOrderCode());
        mv.addObject("category", commentVO.getCommentCategory());
        mv.addObject("memo", commentVO.getComment());

        List<CommentVO> commentList = new ArrayList();

        //품목 비고 정보
        commentList=commonSvc.getCommentList(commentVO);

        mv.addObject("commentList", commentList);
        
        mv.setViewName("/order/memoManage");
        
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		
		if(commentVO.getCommentCategory().equals("03")){
			work.setWorkCategory("CH");
			work.setWorkCode("CH006");
			work.setWorkKey1(commentVO.getOrderCode());
			work.setWorkKey2(commentVO.getTitle());
			work.setSearchKey1(commentVO.getCompanyCode());
			commonSvc.regiHistoryInsert(work);
		}else if(commentVO.getCommentCategory().equals("05")){
			work.setWorkCategory("RE");
			work.setWorkKey1(commentVO.getOrderCode());
			work.setWorkKey2(commentVO.getTitle());
			work.setSearchKey2(commentVO.getCompanyCode());
			work.setWorkCode("RE003");
			commonSvc.regiHistoryInsert(work);
		}
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 품목 비고관리
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/etcmanage")
    public ModelAndView etcManage(HttpServletRequest request, 
    		                       HttpServletResponse response,
		                           String orderCode,
		                           String category,
		                           String productCode,
		                           String productName,
		                           String etc,
		                           String idx,
		                           String companyCode) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start [orderCode]:"+orderCode+"[productCode]:"+productCode+"[productName]:"+productName+"[etc]:"+etc+"[idx]:"+idx);

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
        mv.addObject("orderCode", orderCode);
        mv.addObject("category", category);
        mv.addObject("productCode", productCode);
        mv.addObject("productName", productName);
        mv.addObject("etc", etc);
        mv.addObject("idx", idx);
        mv.addObject("companyCode", companyCode);
        
        CommentVO commentVO = new CommentVO();
        
        commentVO.setOrderCode(orderCode);
        commentVO.setCommentCategory(category);
        commentVO.setProductCode(productCode);
        
        List<CommentVO> commentList = new ArrayList();

        //품목 비고 정보
        commentList=commonSvc.getProductEtcList(commentVO);

        mv.addObject("commentList", commentList);
        
        mv.setViewName("/order/etcManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 품목 비고내용
     * 
     * @param orderCode
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/etclist")
    public ModelAndView etcList( @ModelAttribute("commentVO") CommentVO commentVO,
    		                      HttpServletRequest request) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : commentVO : " + commentVO);

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

        List<CommentVO> commentList = new ArrayList();

        //품목 비고 정보
        commentList=commonSvc.getProductEtcList(commentVO);

        mv.addObject("commentList", commentList);
        
        mv.setViewName("/order/etcList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 품목 비고추가
     * 
     * @param orderCode
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/etcaddlist")
    public ModelAndView etcAddList( @ModelAttribute("commentVO") CommentVO commentVO,
    		                      HttpServletRequest request) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : commentVO : " + commentVO);

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
        
        commentVO.setCommentUserId(strUserId);

        try{//01.메모추가
    	    
        	int dbResult=commonSvc.regiCommentInsert(commentVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }
        
        // 조회조건저장
        mv.addObject("orderCode", commentVO.getOrderCode());
        mv.addObject("category", commentVO.getCommentCategory());
        mv.addObject("productCode", commentVO.getProductCode());
        mv.addObject("productName", commentVO.getProductName());
        mv.addObject("etc", commentVO.getComment());

        List<CommentVO> commentList = new ArrayList();

        //품목 비고 정보
        commentList=commonSvc.getProductEtcList(commentVO);

        mv.addObject("commentList", commentList);
        
        mv.setViewName("/order/etcManage");
        
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		
		if(commentVO.getCommentCategory().equals("04")){
			work.setWorkCategory("CH");
			work.setWorkCode("CH007");
			work.setWorkKey1(commentVO.getOrderCode());
			work.setWorkKey2(commentVO.getProductCode());
			work.setWorkKey3(commentVO.getTitle());
			work.setSearchKey1(commentVO.getCompanyCode());
			commonSvc.regiHistoryInsert(work);
		}else if(commentVO.getCommentCategory().equals("06")){
			work.setWorkCategory("RE");
			work.setWorkCode("RE007");
			work.setWorkKey1(commentVO.getOrderCode());
			work.setWorkKey2(commentVO.getProductCode());
			work.setWorkKey3(commentVO.getTitle());
			work.setSearchKey2(commentVO.getCompanyCode());
			commonSvc.regiHistoryInsert(work);
		}
        
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
    @RequestMapping(value = "/order/deferreasonlist")
    public ModelAndView deferReasonList( HttpServletRequest request, 
    		                              HttpServletResponse response,
    		                              String orderCode,
    		                              String category) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderCode : [" + orderCode+"]");

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
         
        String strToday = simpleDateFormat.format(currentTime);

        List<CommentVO> commentList = new ArrayList();

        CommentVO commentConVO = new CommentVO();
        commentConVO.setOrderCode(orderCode);
        commentConVO.setCommentCategory(category);
 
        //보류사유 정보
        commentList=commonSvc.getCommentList(commentConVO);

        mv.addObject("commentConVO", commentConVO);
        mv.addObject("commentList", commentList);
        
        mv.setViewName("/order/deferReasonList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 검수완료 처리
     *
     * @param OrderVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/ordercomplete"})
    public @ResponseBody
    String orderComplete(@ModelAttribute("orderVO") OrderVO orderVO,
                         @RequestParam(value="arrCheckProductId", required=false, defaultValue="") String arrCheckProductId,
    		             HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderVO" + orderVO);
			
		String deferResult="check0000";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
       
	    String[] orders = request.getParameterValues("seqs");

	    orderVO.setBuyUserId(strUserId);
	    orderVO.setOrderState("06");
  
        try{//01.검수처리
    	    
        	int dbResult=orderSvc.regiOrderComplete(orders , orderVO , arrCheckProductId);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "check0001";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "check0002\n[errorMsg] : "+errMsg;
	    	
	    }
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH008");
		work.setWorkKey1(orderVO.getOrderCode());
		work.setSearchKey1(orderVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
        
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
  }
    
    /**
     * 발주서 재송부
     *
     * @param 
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/orderremail", method = RequestMethod.POST)
    public @ResponseBody
    String orderReMail(@ModelAttribute("orderVO") OrderVO orderVO, 
    		           HttpServletRequest request, 
    		           HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		logger.info("["+logid+"] Controller start : orderVO" + orderVO);
		boolean orderResult=false;
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
		
		 //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        try{//01.처리사유
        	
        	CommentVO commentVO =new CommentVO();

        	commentVO.setOrderCode(orderVO.getOrderCode());
        	commentVO.setCommentUserId(strUserId);
        	commentVO.setCommentCategory("09");
        	commentVO.setComment(orderVO.getDeferReason());
    	    
        	int dbResult=commonSvc.regiCommentInsert(commentVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }
        
 
	    ResourceBundle rb = ResourceBundle.getBundle("config");
	    String orderFilePath = rb.getString("offact.upload.path") + "order/";
	    
	    int dateFolderbeginIndex=orderVO.getOrderCode().length()-9;
	    int dateFolderendIndex=orderVO.getOrderCode().length()-3;

	    String uploadFilePath = orderFilePath + orderVO.getOrderCode().substring(dateFolderbeginIndex, dateFolderendIndex)+"/";
	    
	    String szFileName = uploadFilePath+orderVO.getOrderCode()+".html";                    // 파일 이름

	    String orderDates[]=orderVO.getOrderDate().split("-");
	    
		String [] getToMails=orderVO.getEmail().split(";");
		String [] getToMail_Ccs=null;
	    
		try{//메일전송 발주처리
            /* 파일을 생성해서 내용 쓰기 */
	        
	        File file = new File(szFileName);                        // 파일 생성
	       
	        logger.info("["+logid+"] @@@@@@@@ : szFileName" + szFileName);
	        logger.info("["+logid+"] @@@@@@@@ : file" + file.isFile());
	        
			EmailVO mail = new EmailVO();
			
			List<String> toEmails= new ArrayList();
			List<String> toEmail_Ccs= new ArrayList();
			List<String> attcheFileName= new ArrayList();
			List<File> files = new ArrayList();
			
			for(int m=0;m<getToMails.length;m++){	
				toEmails.add(getToMails[m]);	
			}
			/*
			for(int c=0;c<getToMail_Ccs.length;c++){	
				toEmail_Ccs.add(getToMail_Ccs[c]);	
			}
			*/
			attcheFileName.add("[reSend]"+orderVO.getOrderCode()+".html");
			
			files.add(file);
			
			mail.setToEmails(toEmails);
			mail.setToEmail_Ccs(toEmail_Ccs);
			mail.setAttcheFileName(attcheFileName);
			mail.setFile(files);
			
			mail.setFromEmail(orderfromemail);
			mail.setMsg(orderVO.getOrderName()+" 상품주문서 메일입니다.<br>"+orderVO.getDeliveryDate()+"까지 납품 부탁드립니다.<br><br><br>*첨부파일이 정상적으로 확인이 안되실 경우 아래 링크로 다시한번 다운로드 부탁드립니다.<br><a href='"+hostUrl+"/addon/order/orderdownload?orderCode="+orderVO.getOrderCode()+"' >[발주서 다운로드]</a><br><br><font style='color:red'>*본 메일은 발주전용 메일입니다.회신시 아래 연락처 정보의 메일로 회신 부탁드립니다.</font><br><br>[연락처  정보]<br><br>(담당자)  "+orderVO.getOrderCharge()+"<br>(Tel)  "+
			orderVO.getOrderTelNumber()+"<br>(핸드폰)  "+orderVO.getOrderMobilePhone()+"<br>(E-Mail)  "+orderVO.getOrderEmail()+"<br>(FAX)  "+orderVO.getOrderFaxNumber());
					
			
			mail.setSubject("애디스 다이렉트 발주서");
	
			try{
				orderResult=mailSvc.sendMail(mail);
				logger.debug("mail result :"+orderResult);
				
				if(orderResult==false){
					
					//log Controller execute time end
			       	long t2 = System.currentTimeMillis();
			       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	
			        return "order0033";
					
				}
			}catch(BizException e){
		       	
		    	e.printStackTrace();
		        String errMsg = e.getMessage();
		        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

		        return "order0033\n[errorMsg] : "+errMsg;
		    	
		    }
		
		}catch(Exception e){
			
			e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "order0033\n[errorMsg] : "+errMsg;
		}
		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH005");
		work.setWorkKey1(orderVO.getOrderCode());
		work.setSearchKey1(orderVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);

		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return "order0030";
    }
    /**
     * 검수대상 상세조회
     * 
     * @param orderCode
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/orderdetailprint")
    public ModelAndView orderDetailPrint( HttpServletRequest request, 
    		                              HttpServletResponse response,
    		                              String orderCode) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderCode : [" + orderCode+"]");

        ModelAndView mv = new ModelAndView();
        List<OrderVO> orderDetailList = null;
        
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
         
        String strToday = simpleDateFormat.format(currentTime);
        
        OrderVO orderConVO = new OrderVO();
        OrderVO orderVO = new OrderVO();

        orderConVO.setOrderCode(orderCode);

        // 조회조건저장
        mv.addObject("orderConVO", orderConVO);
        
        //검수대상 정보
        orderVO=orderSvc.getOrderDetail(orderConVO);
        
        //검수대상 상세정보
        orderDetailList=orderSvc.getOrderDetailList(orderConVO);
 
        mv.addObject("orderVO", orderVO);
        mv.addObject("orderDetailList", orderDetailList);
   
        mv.setViewName("/order/orderDetailPrint");
        
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH003");
		work.setWorkKey1(orderCode);
		work.setSearchKey1(orderVO.getCompanyCode());
		commonSvc.regiHistoryInsert(work);
		
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/order/orderexcellist")
   	public ModelAndView orderExcelList(HttpServletRequest request, HttpServletResponse response) throws BizException 
       {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();

   		ModelAndView mv = new ModelAndView();
   		
   		String orderCode=request.getParameter("orderCode");
   		String companyCode=request.getParameter("companyCode");
   		String transDate=request.getParameter("excelTransDate");
   		
      	// 사용자 세션정보
	   	 HttpSession session = request.getSession();
	     String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
	     String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName")); 
	     String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
	     String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
	     String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
     
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date currentTime = new Date();
         
        String strToday = simpleDateFormat.format(currentTime);
        
        if(transDate.equals("")){
        	transDate=strToday;
        }
        
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

   		
   		String[] orderList = request.getParameterValues("seqs"); 		
   	    String[] r_data=null;

        List<OrderVO> orderExcelList = new ArrayList();
	   
	    int idx = 0;
	    
	    for(int i=0;i<orderList.length;i++){
	
	        r_data = StringUtil.getTokens(orderList[i], "|");
	    
	        OrderVO orderDetailVo = new OrderVO();
			
	        orderDetailVo.setOrderDate(transDate);
	        orderDetailVo.setProductCode(StringUtil.nvl(r_data[0],""));
	        orderDetailVo.setBarCode(StringUtil.nvl(r_data[1],""));
	        orderDetailVo.setOrderResultPrice(StringUtil.nvl(r_data[2],""));
	        orderDetailVo.setOrderResultCnt(StringUtil.nvl(r_data[3],""));
	        orderDetailVo.setOrderVatRate(StringUtil.nvl(r_data[4],""));
	        orderDetailVo.setEtc(StringUtil.nvl(r_data[5],""));
	        orderDetailVo.setCompanyCode(StringUtil.nvl(r_data[6],""));
	        orderDetailVo.setGroupId(StringUtil.nvl(r_data[7],""));
	        orderDetailVo.setBuyUserName(strUserName);
			
	        orderExcelList.add(orderDetailVo);
		
		}
 
   	    mv.addObject("orderExcelList", orderExcelList);
   	 
   		mv.setViewName("/order/orderExcelList");
   		
        //작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("CH");
		work.setWorkCode("CH009");
		work.setWorkKey1(orderCode);
		work.setSearchKey1(companyCode);
		commonSvc.regiHistoryInsert(work);
   		
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
    @RequestMapping(value = "/order/deferreason")
    public ModelAndView deferReason(HttpServletRequest request, 
    		                       HttpServletResponse response,
    		                       String deferType,
    		                       String reMail) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller deferReason start");

        ModelAndView mv = new ModelAndView();
        
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName"));    
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
        
        mv.addObject("deferType", deferType);
        mv.addObject("reMail", reMail);
        mv.setViewName("/order/deferReason");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller deferReason end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
    @RequestMapping(value = "/order/ordercompleteinput")
    public ModelAndView orderCompleteInput(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller orderCompleteInput start");

        ModelAndView mv = new ModelAndView();
        
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName"));    
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
         
        String strToday = simpleDateFormat.format(currentTime);
        
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
        
        mv.addObject("strToday", strToday);
        mv.setViewName("/order/orderCompleteInput");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller orderCompleteInput end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
    @RequestMapping(value = "/order/barcodecheck")
    public ModelAndView barCodeCheck(HttpServletRequest request, 
    		                       HttpServletResponse response,
    		                       String orderCnt) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller barcodelist start");

        ModelAndView mv = new ModelAndView();
        
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName"));    
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
        
        
        mv.addObject("orderCnt", orderCnt);
        
        mv.setViewName("/order/barCodeCheck");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller deferReason end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
    @RequestMapping(value = "/order/barcodecheckresult")
    public ModelAndView barCodeCheckResult(HttpServletRequest request, 
    		                       HttpServletResponse response,
    		                       String totalFMsg,
    		                       String fCnt) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller barcodelist start");

        ModelAndView mv = new ModelAndView();
        
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName"));    
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
        
        mv.addObject("fCnt", fCnt);
        mv.addObject("totalFMsg", totalFMsg);
        
        mv.setViewName("/order/barCodeCheckResult");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller deferReason end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
    @RequestMapping(value = "/order/mailcc")
    public ModelAndView mailCc(HttpServletRequest request, 
    		                       HttpServletResponse response,
    		                       String cc) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller deferReason start cc:"+cc);

        ModelAndView mv = new ModelAndView();
        
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName"));    
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
        
        mv.addObject("cc", cc);
        mv.setViewName("/order/mailCc");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller deferReason end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
	
}
