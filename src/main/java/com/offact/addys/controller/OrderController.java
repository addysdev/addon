package com.offact.addys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
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
import com.offact.addys.service.order.OrderService;
import com.offact.addys.service.order.TargetService;
import com.offact.addys.service.common.MailService;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.EmailVO;
import com.offact.addys.vo.common.CommentVO;
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
	
    @Autowired
    private CommonService commonSvc;
    
    @Autowired
    private TargetService targetSvc;
   
    @Autowired
    private OrderService orderSvc;
    
    @Autowired
    private MailService mailSvc;
    
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
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
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
        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName"));
        String strGroupName = StringUtil.nvl((String) session.getAttribute("strGroupName"));
        String strOfficePhone = StringUtil.nvl((String) session.getAttribute("strOfficePhone"));
        String strMobliePhone = StringUtil.nvl((String) session.getAttribute("strMobliePhone"));
        String strEmail = StringUtil.nvl((String) session.getAttribute("strEmail"));
        
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
        targetVO.setDeliveryDate(strDeliveryDay);
        
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
		logger.info("["+logid+"] @@@@@@@@ : targetVO.getDeliveryEmail" + targetVO.getEmail());
		
		String[] orders = request.getParameterValues("seqs");
		String orderDate=targetVO.getOrderDate(); 
		String deliveryDate=targetVO.getDeliveryDate();
		String orderDates[]=targetVO.getOrderDate().split("-");
		String deliveryDates[]=targetVO.getDeliveryDate().split("-"); 
		
   		ModelAndView mv = new ModelAndView();
   		
   	    mv.addObject("targetVO", targetVO);
        mv.addObject("orders", orders);
        mv.addObject("orderDates1", orderDates[0]);
        mv.addObject("orderDates2", orderDates[1]);
        mv.addObject("orderDates3", orderDates[2]);
        mv.addObject("deliveryDates1", deliveryDates[0]);
        mv.addObject("deliveryDates2", deliveryDates[1]);
        mv.addObject("deliveryDates3", deliveryDates[2]);
   		
   		mv.setViewName("/order/targetDetailPrint");
   		
   		return mv;
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
     */
    @RequestMapping(value = "/order/orderprocess", method = RequestMethod.POST)
    public @ResponseBody
    String orderProcess(@ModelAttribute("targetVO") TargetVO targetVO, 
    		          HttpServletRequest request, 
    		          HttpServletResponse response) throws BizException
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        //주문코드 생성
		String orderCode="";
		
		if(targetVO.getOrderCode().equals("X")){
			orderCode="O"+targetVO.getCon_groupId()+t1;
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

		ResourceBundle rb = ResourceBundle.getBundle("config");
	    String uploadFilePath = rb.getString("offact.upload.path") + "html/";
	    String szFileName = uploadFilePath+orderCode+".html";                    // 파일 이름
        String szContent = "";
        
        String orderDates[]=targetVO.getOrderDate().split("-");
        String deliveryDates[]=targetVO.getDeliveryDate().split("-"); 
        
		try{//메일전송 발주처리
            /* 파일을 생성해서 내용 쓰기 */
	        
	        File file = new File(szFileName);                        // 파일 생성
	        OutputStream out = new FileOutputStream(file);            // 파일에 문자를 적을 스트림 생성

            szContent += "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
	        szContent += "<html>";
	        szContent += "<head>";
	        szContent += "<title>상품주문서</title>";
	        szContent += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
	        szContent += "<style type='text/css'>"; 
	        szContent += "<!--";
	        szContent += "td {";
	        szContent += "font-family: '굴림', '돋움', 'Seoul', '한강체';";
	        szContent += "font-size: 12px";
	        szContent += "	line-height: 30px;";
	        szContent += "}";
			szContent += ".style1 {";
		    szContent += "	font-size: 30px;";
			szContent += "	font-weight: bold;";
			szContent += "	font-family: '굴림체', '돋움체', Seoul;";
	        szContent += "}";
			szContent += ".style5 {";
			szContent += "	font-size: 24px;";
			szContent += "font-weight: bold;";
	        szContent += "}";
			szContent += "-->";
			szContent += "</style>";
			szContent += "</head>";

			szContent += "<body>";
			szContent += "<div align='center'></div>";

			szContent += "<div align='left'>";
			szContent += "<table width='612' border='0' align='center' cellpadding='0' cellspacing='0'>";
			szContent += "<tr>"; 
			szContent += "<td width='516' valign='top'>";
			szContent += "<table width='722' height='900' border='0' align='center' cellpadding='1' cellspacing='1' bgcolor='#000000'>";
			szContent += "<tr bgcolor='#FFFFFF'>"; 
			szContent += "<td height='55' colspan='12' align='center'><span class='style1'>상 품 주 문 서</span></td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += " <td rowspan='7' align='center' style='background-color:#E4E4E4'>수<br>신</td>";
			szContent += " <td align='center' width='100'>&nbsp;수 신</td>";
			szContent += " <td colspan='5' align='center'>&nbsp;"+targetVO.getDeliveryCharge()+"</td>";
			szContent += " <td rowspan='7'  align='center' style='background-color:#E4E4E4'>발<br>신</td>";
			szContent += " <td align='center' width='100'>&nbsp;발 신</td>";
			szContent += " <td colspan='3' align='center'>&nbsp;"+targetVO.getOrderCharge()+"</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center'>&nbsp;참 조</td>";
			szContent += "<td colspan='5' align='center'>&nbsp;"+targetVO.getDeliveryEtc()+"</td>";
			szContent += "<td align='center'>&nbsp;참 조</td>";
			szContent += "<td colspan='3' align='center'>&nbsp;"+targetVO.getOrderEtc()+"</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td rowspan='2' align='center' >연락처</td>";
			szContent += "<td colspan='5' align='left'>&nbsp;핸드폰:"+targetVO.getMobilePhone()+",<br>E-Mail:"+targetVO.getEmail()+"</td>";
			szContent += "<td rowspan='2' align='center' >연락처</td>";
			szContent += "<td colspan='3' align='left'>&nbsp;핸드폰:"+targetVO.getOrderMobilePhone()+",<br>E-Mail:"+targetVO.getOrderEmail()+"</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='5' align='left'>&nbsp;TEL:"+targetVO.getTelNumber()+",FAX:"+targetVO.getFaxNumber()+"</td>";
			szContent += "<td colspan='5' align='left'>&nbsp;TEL:"+targetVO.getOrderTelNumber()+",FAX:"+targetVO.getOrderFaxNumber()+"</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center' >발주일자</td>";
			szContent += "<td width='70' align='center'><div align='right'>"+orderDates[0]+"년 </div></td>";
			szContent += "<td width='50' align='center'>&nbsp;"+orderDates[1]+"</td>";
			szContent += "<td width='50' align='center'>월</td>";
			szContent += "<td width='50' align='center'>&nbsp;"+orderDates[2]+"</td>";
			szContent += "<td width='50' align='center'>일</td>";
			szContent += "<td rowspan='2' align='center' >배송주소</td>";
			szContent += "<td rowspan='2' colspan='3' align='left'>&nbsp;"+targetVO.getOrderAddress()+"</td>";
			szContent += "</tr>";
            szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center' >납품일자</td>";
			szContent += "<td width='70' align='center'><div align='right'>"+deliveryDates[0]+"년 </div></td>";
			szContent += "<td width='50' align='center'>&nbsp;"+deliveryDates[1]+"</td>";
			szContent += "<td width='50' align='center'>월</td>";
			szContent += "<td width='50' align='center'>&nbsp;"+deliveryDates[2]+"</td>";
			szContent += "<td width='50' align='center'>일</td>";
			szContent += "</tr>";
            szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center'>&nbsp;납품방법</td>";
			szContent += "<td colspan='5' align='center'>&nbsp;"+targetVO.getDeliveryMethod()+"</td>";
			szContent += "<td align='center'>&nbsp;결재방법</td>";
			szContent += "<td colspan='3' align='center'>&nbsp;"+targetVO.getPayMethod()+"</td>";
			szContent += "</tr>";

			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' >메모</td>";
			szContent += "<td colspan='10' align='left'>"+targetVO.getMemo()+"</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='12' align='center' height='27'><div align='left'>1.아래와 같이 발주합니다.</div></td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>번 호</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>수량</td>";
			szContent += "<td width='172' align='center'>비 고</td>";
			szContent += "</tr>";
			
			int num=0;
			int totalnum=orders.length;
			int etcnum=0;
			String[] r_data=null;
			
			if(totalnum<23){
				
				etcnum=23-totalnum;
				
			}
			
			for(int i=0;i<totalnum;i++){
				
				num=i+1;
				r_data = StringUtil.getTokens(orders[i], "|");
			
		        szContent += "<tr bgcolor='#FFFFFF'>";
				szContent += "<td colspan='2' align='center' height='27'>"+num+"</td>";
				szContent += "<td align='left'>"+StringUtil.nvl(r_data[12],"")+"</td>";
				szContent += "<td colspan='7' align='left'>"+StringUtil.nvl(r_data[1],"")+"</td>";
				szContent += "<td width='57' align='center'>"+StringUtil.nvl(r_data[3],"")+"</td>";
				szContent += "<td width='172' align='left'>"+StringUtil.nvl(r_data[11],"")+"</td>";
				szContent += "</tr>";
			
			}
			
			for(int y=0;y<etcnum;y++){
				
				szContent += "<tr bgcolor='#FFFFFF'>";
				szContent += "<td colspan='2' align='center' height='27'>&nbsp;</td>";
				szContent += "<td align='center'>&nbsp;</td>";
				szContent += "<td colspan='7' align='center'>&nbsp;</td>";
				szContent += "<td width='57' align='center'>&nbsp;</td>";
				szContent += "<td width='172' align='center'>&nbsp;</td>";
				szContent += "</tr>";
			
			}
		

			szContent += "</table>";
			szContent += "</div>";

			szContent += "</body>";
			szContent += " </html>";

			szContent += "</html>";
	        
			szContent += "</html>";

	        
	        out.write(szContent.getBytes());                        // 파일에 쓰기
	        out.close();                                            // 파일 쓰기 스트림 닫기
			
			EmailVO mail = new EmailVO();
			
			List<String> toEmails= new ArrayList();
			List<String> attcheFileName= new ArrayList();
			List<File> files = new ArrayList();
			
			toEmails.add(targetVO.getEmail());
			attcheFileName.add("order_"+orderDates[0]+orderDates[1]+orderDates[2]+".html");
			files.add(file);
			
			mail.setToEmails(toEmails);
			mail.setAttcheFileName(attcheFileName);
			mail.setFile(files);
			
			mail.setFromEmail("order@addys.co.kr");
			mail.setMsg("애디스("+targetVO.getGroupName()+")지점 상품주문서 메일입니다.<br>"+targetVO.getDeliveryDate()+"까지 납품 부탁드립니다.<br><br><br><br>[연락처  정보]<br><br>(담당자)  "+targetVO.getOrderCharge()+"<br>(Tel)  "+
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
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
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
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

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
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "order0012\n[errorMsg] : "+errMsg;
	    	
	    }
		
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
		                           String memo) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start memo:"+memo);

        ModelAndView mv = new ModelAndView();
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        // 조회조건저장
        mv.addObject("orderCode", orderCode);
        mv.addObject("category", category);
        mv.addObject("memo", memo);

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
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

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
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        commentVO.setCommentUserId(strUserId);

        try{//01.메모추가
    	    
        	int dbResult=commonSvc.regiCommentInsert(commentVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
	    }

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
		                           String etc) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start [orderCode]:"+orderCode+"[productCode]:"+productCode+"[productName]:"+productName+"[etc]:"+etc);

        ModelAndView mv = new ModelAndView();
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        // 조회조건저장
        mv.addObject("orderCode", orderCode);
        mv.addObject("category", category);
        mv.addObject("productCode", productCode);
        mv.addObject("productName", productName);
        mv.addObject("etc", etc);

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
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

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
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        commentVO.setCommentUserId(strUserId);

        try{//01.메모추가
    	    
        	int dbResult=commonSvc.regiCommentInsert(commentVO);

	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
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
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

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

		ResourceBundle rb = ResourceBundle.getBundle("config");
	    String uploadFilePath = rb.getString("offact.upload.path") + "html/";
	    String szFileName = uploadFilePath+orderVO.getOrderCode()+".html";                    // 파일 이름
        
	    String orderDates[]=orderVO.getOrderDate().split("-");
	    
		try{//메일전송 발주처리
            /* 파일을 생성해서 내용 쓰기 */
	        
	        File file = new File(szFileName);                        // 파일 생성
	       
	        logger.info("["+logid+"] @@@@@@@@ : szFileName" + szFileName);
	        logger.info("["+logid+"] @@@@@@@@ : file" + file.isFile());
	        
			EmailVO mail = new EmailVO();
			
			List<String> toEmails= new ArrayList();
			List<String> attcheFileName= new ArrayList();
			List<File> files = new ArrayList();
			
			toEmails.add(orderVO.getEmail());
			attcheFileName.add("[re]order_"+orderDates[0]+orderDates[1]+orderDates[2]+".html");
			
			files.add(file);
			
			mail.setToEmails(toEmails);
			mail.setAttcheFileName(attcheFileName);
			mail.setFile(files);
			
			mail.setFromEmail("order@addys.co.kr");
			mail.setMsg("[재전송]애디스("+orderVO.getGroupName()+")지점 상품주문서 메일입니다.<br>"+orderVO.getDeliveryDate()+"까지 납품 부탁드립니다.<br><br><br><br>[연락처  정보]<br><br>(담당자)  "+orderVO.getOrderCharge()+"<br>(Tel)  "+
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

		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return "order0030";
    }
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/order/orderdetailprint")
   	public ModelAndView orderDetailprint(@ModelAttribute("orderVO") OrderVO orderVO, HttpServletRequest request) throws BizException 
       {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
    	logger.info("["+logid+"] Controller start : orderVO" + orderVO);
		logger.info("["+logid+"] @@@@@@@@ : targetVO.getDeliveryEmail" + orderVO.getEmail());
   		ModelAndView mv = new ModelAndView();
   		
   		mv.setViewName("/order/orderDetailPrint");
   		
   		return mv;
   	}
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/order/orderexcellist")
   	public ModelAndView orderExcelList(@ModelAttribute("orderVO") OrderVO orderVO, HttpServletRequest request) throws BizException 
       {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
    	logger.info("["+logid+"] Controller start : orderVO" + orderVO);
		logger.info("["+logid+"] @@@@@@@@ : targetVO.getDeliveryEmail" + orderVO.getEmail());
   		ModelAndView mv = new ModelAndView();
   		
   		mv.setViewName("/order/orderExcelList");
   		
   		return mv;
   	}
}
