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
import com.offact.addys.service.UserMenuService;
import com.offact.addys.service.manage.UserManageService;
import com.offact.addys.service.manage.ProductMasterService;
import com.offact.addys.vo.UserMenuVO;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.UserConditionVO;
import com.offact.addys.vo.manage.UserManageVO;
import com.offact.addys.vo.manage.StockMasterVO;
import com.offact.addys.vo.manage.ProductMasterVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class MasterController {

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
    private UserManageService userManageSvc;
    
    @Autowired
    private ProductMasterService productMasterSvc;
    
	 
    /**
     * 품목현황 관리화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/productmanage")
    public ModelAndView productManage(HttpServletRequest request, 
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
        
        UserManageVO userConVO = new UserManageVO();
        
        userConVO.setUserId(userId);
        userConVO.setGroupId(groupId);

        // 조회조건저장
        mv.addObject("userConVO", userConVO);

        // 공통코드 조회 (사용자그룹코드)
        /*
        ADCodeManageVO code = new ADCodeManageVO();
        code.setCodeId("IG11");
        List<ADCodeManageVO> searchCondition1 = codeService.getCodeComboList(code);
        mv.addObject("searchCondition1", searchCondition1);
       */
       
        mv.setViewName("/master/productManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 품목 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/productpagelist")
    public ModelAndView productPageList(@ModelAttribute("productConVO") ProductMasterVO productConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : productConVO" + productConVO);

        ModelAndView mv = new ModelAndView();
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

        mv.setViewName("/master/productPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 재고현황 관리화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/stockmanage")
    public ModelAndView stockManage(HttpServletRequest request, 
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
        
        UserManageVO userConVO = new UserManageVO();
        
        userConVO.setUserId(userId);
        userConVO.setGroupId(groupId);

        // 조회조건저장
        mv.addObject("userConVO", userConVO);

        // 공통코드 조회 (사용자그룹코드)
        /*
        ADCodeManageVO code = new ADCodeManageVO();
        code.setCodeId("IG11");
        List<ADCodeManageVO> searchCondition1 = codeService.getCodeComboList(code);
        mv.addObject("searchCondition1", searchCondition1);
       */
       
        mv.setViewName("/master/stockManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 재고현황 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/stockpagelist")
    public ModelAndView stockPageList(@ModelAttribute("userConVO") UserManageVO userConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userConVO" + userConVO);

        ModelAndView mv = new ModelAndView();
        List<UserManageVO> userList = null;
        UserManageVO userDetail = null;

        // 사용여부 값 null 일때 공백처리
        if (userConVO.getCon_useYn() == null) {
            userConVO.setCon_useYn("Y");
        }

        // 그룹 값 null 일때 공백처리
        if (userConVO.getCon_groupId() == null) {
            userConVO.setCon_groupId("G00000");
        }
        
        // 조회조건 null 일때 공백처리
        if (userConVO.getSearchGubun() == null) {
            userConVO.setSearchGubun("02");
        }
        
        // 조회값 null 일때 공백처리
        if (userConVO.getSearchValue() == null) {
            userConVO.setSearchValue("system");
        }
        
        // 조회조건저장
        mv.addObject("userCon", userConVO);

        // 페이징코드
        userConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(userConVO.getCurPage(), userConVO.getRowCount()));
        userConVO.setPage_limit_val2(StringUtil.nvl(userConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        userList = userManageSvc.getUserList(userConVO);
        mv.addObject("userList", userList);

        // totalCount 조회
        String totalCount = String.valueOf(userManageSvc.getUserCnt(userConVO));
        mv.addObject("totalCount", totalCount);

        // 기능 권한 리스트
        /*
        HttpSession session = request.getSession();
        UserMenuVO authListVo = new UserMenuVO();
        authListVo.setUSER_ID((String) session.getAttribute("strUserId"));
        List<UserMenuVO> funcList = userMenuSvc.getAuthPerFunction(authListVo);
        mv.addObject("funcList", funcList);
        */
        mv.setViewName("/master/stockPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 매출현황 관리화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/salesmanage")
    public ModelAndView salesManage(HttpServletRequest request, 
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
        
        UserManageVO userConVO = new UserManageVO();
        
        userConVO.setUserId(userId);
        userConVO.setGroupId(groupId);

        // 조회조건저장
        mv.addObject("userConVO", userConVO);

        // 공통코드 조회 (사용자그룹코드)
        /*
        ADCodeManageVO code = new ADCodeManageVO();
        code.setCodeId("IG11");
        List<ADCodeManageVO> searchCondition1 = codeService.getCodeComboList(code);
        mv.addObject("searchCondition1", searchCondition1);
       */
       
        mv.setViewName("/master/salesManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 매출현황 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/salespagelist")
    public ModelAndView salesPageList(@ModelAttribute("userConVO") UserManageVO userConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userConVO" + userConVO);

        ModelAndView mv = new ModelAndView();
        List<UserManageVO> userList = null;
        UserManageVO userDetail = null;

        // 사용여부 값 null 일때 공백처리
        if (userConVO.getCon_useYn() == null) {
            userConVO.setCon_useYn("Y");
        }

        // 그룹 값 null 일때 공백처리
        if (userConVO.getCon_groupId() == null) {
            userConVO.setCon_groupId("G00000");
        }
        
        // 조회조건 null 일때 공백처리
        if (userConVO.getSearchGubun() == null) {
            userConVO.setSearchGubun("02");
        }
        
        // 조회값 null 일때 공백처리
        if (userConVO.getSearchValue() == null) {
            userConVO.setSearchValue("system");
        }
        
        // 조회조건저장
        mv.addObject("userCon", userConVO);

        // 페이징코드
        userConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(userConVO.getCurPage(), userConVO.getRowCount()));
        userConVO.setPage_limit_val2(StringUtil.nvl(userConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        userList = userManageSvc.getUserList(userConVO);
        mv.addObject("userList", userList);

        // totalCount 조회
        String totalCount = String.valueOf(userManageSvc.getUserCnt(userConVO));
        mv.addObject("totalCount", totalCount);

        // 기능 권한 리스트
        /*
        HttpSession session = request.getSession();
        UserMenuVO authListVo = new UserMenuVO();
        authListVo.setUSER_ID((String) session.getAttribute("strUserId"));
        List<UserMenuVO> funcList = userMenuSvc.getAuthPerFunction(authListVo);
        mv.addObject("funcList", funcList);
        */
        mv.setViewName("/master/salesPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
}
