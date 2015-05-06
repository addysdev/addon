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
import com.offact.addys.service.recovery.RecoveryService;

import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.recovery.RecoveryVO;

import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class RecoveryController {

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
    private RecoveryService recoverySvc;
    
	 /**
     * 회수대상 화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/recovery/recoverymanage")
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
        
        RecoveryVO recoveryConVO = new RecoveryVO();
        
        recoveryConVO.setGroupId(groupId);

        // 조회조건저장
        mv.addObject("recoveryConVO", recoveryConVO);

        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(groupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (발주상태코드)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("RE01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);
       
        mv.setViewName("/recovery/recoveryManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 회수대상 목록조회
     * 
     * @param RecoveryVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/recovery/recoverypagelist")
    public ModelAndView targetPageList(@ModelAttribute("recoveryConVO") RecoveryVO recoveryConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : recoveryConVO" + recoveryConVO);

        ModelAndView mv = new ModelAndView();
        List<RecoveryVO> recoveryList = null;

        // 조직값 null 일때 공백처리
        if (recoveryConVO.getCon_groupId() == null) {
        	recoveryConVO.setCon_groupId("");
        }

        // 상태 값 null 일때 공백처리
        if (recoveryConVO.getCon_recoveryState() == null) {
        	recoveryConVO.setCon_groupId("G00000");
        }

        // 조회조건저장
        mv.addObject("recoveryConVO", recoveryConVO);

        // 페이징코드
        recoveryConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(recoveryConVO.getCurPage(), recoveryConVO.getRowCount()));
        recoveryConVO.setPage_limit_val2(StringUtil.nvl(recoveryConVO.getRowCount(), "10"));
        
        // 발주대상목록조회
        recoveryList = recoverySvc.getRecoveryPageList(recoveryConVO);
        mv.addObject("recoveryList", recoveryList);

        // totalCount 조회
        String totalCount = String.valueOf(recoverySvc.getRecoveryCnt(recoveryConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/recovery/recoveryPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
}
