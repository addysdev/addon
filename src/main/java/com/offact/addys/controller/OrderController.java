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

import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CodeVO;
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

        // 조회조건저장
        mv.addObject("targetConVO", orderConVO);

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
