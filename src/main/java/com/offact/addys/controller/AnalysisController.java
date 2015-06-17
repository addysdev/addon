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
import com.offact.addys.service.analysis.HoldStockService;
import com.offact.addys.service.analysis.GmroiService;
import com.offact.addys.service.master.ProductMasterService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.common.WorkVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.master.ProductMasterVO;
import com.offact.addys.vo.master.StockVO;
import com.offact.addys.vo.manage.UserManageVO;
import com.offact.addys.vo.manage.CompanyManageVO;
import com.offact.addys.vo.master.ProductMasterVO;
import com.offact.addys.vo.analysis.HoldStockVO;
import com.offact.addys.vo.analysis.GmroiVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class AnalysisController {

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
	private UserService userSvc;
	
    @Autowired
    private HoldStockService holdStockSvc;
    
    @Autowired
    private GmroiService gmroiSvc;
    
    /**
     * 보유재고 분석/추천
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/analysis/holdstockmanage")
    public ModelAndView holdStockManage(HttpServletRequest request, 
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
        int movedate=-7;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
        
        HoldStockVO holdStockConVO = new HoldStockVO();
        
        holdStockConVO.setStart_saleDate(strDeliveryDay);
        holdStockConVO.setEnd_saleDate(strToday);
        
        holdStockConVO.setUserId(strUserId);
        holdStockConVO.setGroupId(strGroupId);
        holdStockConVO.setApplyDateCnt("7");
       
        // 조회조건저장
        mv.addObject("holdStockConVO", holdStockConVO);
        
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        mv.setViewName("/analysis/holdStockManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 보유재고 분석/추천 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/analysis/holdstockpagelist")
    public ModelAndView holdStockPageList(@ModelAttribute("holdStockConVO") HoldStockVO holdStockConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : holdStockConVO" + holdStockConVO);

        ModelAndView mv = new ModelAndView();
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        List<HoldStockVO> holdStockList = null;

        // 조회조건 null 일때 공백처리
        if (holdStockConVO.getSearchGubun() == null) {
        	holdStockConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (holdStockConVO.getSearchValue() == null) {
        	holdStockConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("holdStockConVO", holdStockConVO);

        // 페이징코드
        holdStockConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(holdStockConVO.getCurPage(), holdStockConVO.getRowCount()));
        holdStockConVO.setPage_limit_val2(StringUtil.nvl(holdStockConVO.getRowCount(), "10"));
        
        // 보유재고 추천목록조회
        holdStockList = holdStockSvc.getHoldStockPageList(holdStockConVO);
        mv.addObject("holdStockList", holdStockList);

        // totalCount 조회
        String totalCount = String.valueOf(holdStockSvc.getHoldStockCnt(holdStockConVO));
        mv.addObject("totalCount", totalCount);
        
        // totalPrice 조회
        HoldStockVO totalPriceVO = new HoldStockVO();
        totalPriceVO = holdStockSvc.getTotalHoldPrice(holdStockConVO);
        mv.addObject("totalPriceVO", totalPriceVO );

        mv.setViewName("/analysis/holdStockPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
	
    /**
     * gmroi관리
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/analysis/gmroimanage")
    public ModelAndView gmroiManage(HttpServletRequest request, 
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
        int movedate=-7;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
        
        GmroiVO gmroiConVO = new GmroiVO();
        
        gmroiConVO.setStart_saleDate(strDeliveryDay);
        gmroiConVO.setEnd_saleDate(strToday);
        
        gmroiConVO.setUserId(strUserId);
        gmroiConVO.setGroupId(strGroupId);
       
        // 조회조건저장
        mv.addObject("gmroiConVO", gmroiConVO);
        
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        mv.setViewName("/analysis/gmroiManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     *  GMRIO 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/analysis/gmroipagelist")
    public ModelAndView gmroiPageList(@ModelAttribute("gmroiConVO") GmroiVO gmroiConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : gmroiConVO" + gmroiConVO);

        ModelAndView mv = new ModelAndView();
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        List<GmroiVO> gmroiList = null;

        // 조회조건 null 일때 공백처리
        if (gmroiConVO.getSearchGubun() == null) {
        	gmroiConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (gmroiConVO.getSearchValue() == null) {
        	gmroiConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("gmroiConVO", gmroiConVO);

        // 페이징코드
        gmroiConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(gmroiConVO.getCurPage(), gmroiConVO.getRowCount()));
        gmroiConVO.setPage_limit_val2(StringUtil.nvl(gmroiConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        //gmroiList = productMasterSvc.getProductList(gmroiConVO);
        mv.addObject("gmroiList", gmroiList);

        // totalCount 조회
        String totalCount="0";
        //String totalCount = String.valueOf(productMasterSvc.getProductCnt(gmroiConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/analysis/gmroiPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
}
