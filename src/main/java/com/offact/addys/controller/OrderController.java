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
        targetVO.setGroupName(groupName);
        targetVO.setProductPrice(productPrice);
        targetVO.setVat(vat);
        targetVO.setOrderPrice(orderPrice);
        
        targetVO.setOrderUserName(strUserName);
        targetVO.setOrderDateTime(strToday);
        targetVO.setMobilePhone(strMobliePhone);
        targetVO.setPhone(strOfficePhone);
        targetVO.setEmail(strEmail);
        targetVO.setFaxNumber("");
        
        targetVO.setCompanyName(companyVO.getCompanyName());
        targetVO.setDeliveryCharge(companyVO.getChargeName());
        targetVO.setDeliveryMobile(companyVO.getMobilePhone());
        targetVO.setDeliveryFax(companyVO.getFaxNumber());
        targetVO.setDeliveryTel(companyVO.getCompanyPhone());
        targetVO.setDeliveryEmail(companyVO.getEmail());
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
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/order/targetdetailprint")
   	public ModelAndView targetDetailPrint(HttpServletRequest request) throws BizException 
       {
   		
   		ModelAndView mv = new ModelAndView();
   		
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
    @RequestMapping(value = "/order/orderProcess", method = RequestMethod.POST)
    public @ResponseBody
    String orderProcess(@ModelAttribute("targetVO") TargetVO targetVO, 
    		          HttpServletRequest request, 
    		          HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : targetVO" + targetVO);
		
		logger.info("["+logid+"] @@@@@@@@ : targetVO.getDeliveryEmail" + targetVO.getDeliveryEmail());
		
		boolean mailResult=false;
		
		 //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

		//String ordercode="O"+targetVO.getGroupId()+targetVO.getCompanyCode()+strToday;
        String ordercode="OAD001987654321"+strToday;

		ResourceBundle rb = ResourceBundle.getBundle("config");
	    String uploadFilePath = rb.getString("offact.upload.path") + "html/";
	    String szFileName = uploadFilePath+ordercode+".html";                    // 파일 이름
        String szContent = "";
	    
		try{
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
			szContent += " <td align='center'>&nbsp;수 신</td>";
			szContent += " <td colspan='5' align='center'>&nbsp;</td>";
			szContent += " <td rowspan='7'  align='center' style='background-color:#E4E4E4'>발<br>신</td>";
			szContent += " <td align='center'>&nbsp;발 신</td>";
			szContent += " <td colspan='3' align='center'>&nbsp;</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center'>&nbsp;참 조</td>";
			szContent += "<td colspan='5' align='center'>&nbsp;</td>";
			szContent += "<td align='center'>&nbsp;참 조</td>";
			szContent += "<td colspan='3' align='center'>&nbsp;</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td rowspan='2' align='center' >연락처</td>";
			szContent += "<td colspan='5' align='center'>&nbsp;</td>";
			szContent += "<td rowspan='2' align='center' >연락처</td>";
			szContent += "<td colspan='3' align='center'>&nbsp;</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='5' align='center'>&nbsp;</td>";
			szContent += "<td colspan='5' align='center'>&nbsp;</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center' >발주일자</td>";
			szContent += "<td width='70' align='center'><div align='right'>2015년 </div></td>";
			szContent += "<td width='50' align='center'>&nbsp;5</td>";
			szContent += "<td width='50' align='center'>월</td>";
			szContent += "<td width='50' align='center'>&nbsp;28</td>";
			szContent += "<td width='50' align='center'>일</td>";
			szContent += "<td rowspan='2' align='center' >배송주소</td>";
			szContent += "<td rowspan='2' colspan='3' align='center'>&nbsp;</td>";
			szContent += "</tr>";
            szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center' >납품일자</td>";
			szContent += "<td width='70' align='center'><div align='right'>2015년 </div></td>";
			szContent += "<td width='50' align='center'>&nbsp;5</td>";
			szContent += "<td width='50' align='center'>월</td>";
			szContent += "<td width='50' align='center'>&nbsp;28</td>";
			szContent += "<td width='50' align='center'>일</td>";
			szContent += "</tr>";
            szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td align='center'>&nbsp;납품방법</td>";
			szContent += "<td colspan='5' align='center'>&nbsp;</td>";
			szContent += "<td align='center'>&nbsp;결재방법</td>";
			szContent += "<td colspan='3' align='center'>&nbsp;</td>";
			szContent += "</tr>";

			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' >메모</td>";
			szContent += "<td colspan='10' align='center'></td>";
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
	        szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";
			szContent += "<tr bgcolor='#FFFFFF'>";
			szContent += "<td colspan='2' align='center' height='27'>1</td>";
			szContent += "<td align='center'>제조사</td>";
			szContent += "<td colspan='7' align='center'>상 품 명</td>";
			szContent += "<td width='57' align='center'>10</td>";
			szContent += "<td width='172' align='center'>블랙</td>";
			szContent += "</tr>";

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
			
			toEmails.add("kevin.jeon@offact.com");
			attcheFileName.add(ordercode+".html");
			files.add(file);
			
			mail.setToEmails(toEmails);
			mail.setAttcheFileName(attcheFileName);
			mail.setFile(files);
			
			mail.setFromEmail("order@addys.co.kr");
			mail.setMsg("addys 상품 주문서 메일입니다.\n확인하신 후 발송처리 부탁드립니다.");
			mail.setSubject("[애디스다이렉트] 상품주문서 발송메일");
	
			mailResult=mailSvc.sendMail(mail);
			logger.debug("mail result :"+mailResult);
		
		}catch(IOException e){
			e.printStackTrace();
		}
       
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+mailResult;
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
        String strToday = simpleDateFormat.format(currentTime);
        
        orderConVO.setStart_orderDate(strToday);
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

        mv.setViewName("/order/orderPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
}
