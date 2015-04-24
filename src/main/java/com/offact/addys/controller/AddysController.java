package com.offact.addys.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.offact.framework.util.StringUtil;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.UserService;
import com.offact.addys.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class AddysController {

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
	private UserService userSvc;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addys/loginform", method = RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request) throws BizException {
		
		logger.info("Welcome addys loginForm! ");
		
		 // 사용자 세션정보
        HttpSession session = request.getSession();
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
		ModelAndView mv = new ModelAndView();
		String strMainUrl = "addys/loginForm";
		
		try{

			if(!"".equals(userId)){
				strMainUrl="addys/addysMain";
			}
			
		    mv.setViewName(strMainUrl);
	
			return mv;
			
		}catch(Exception e){
			
			mv.setViewName(strMainUrl);
			return mv;
		}

	}

	/**
	 * Login 처리
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/addys/login", method = RequestMethod.POST)
	public ModelAndView addyslogin(HttpServletRequest request,
			                       HttpServletResponse response) throws Exception
	{
		
		//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start");
		
		ModelAndView  mv = new ModelAndView();

		String strUserId = StringUtil.nvl(request.getParameter("id"));
		String strUserPw = StringUtil.nvl(request.getParameter("pwd"));
		
		logger.info(">>>> userId :"+strUserId);
		logger.info(">>>> userPw :"+strUserPw);
		
		String strMainUrl = "";
		
		// # 2. 넘겨받은 아이디로 데이터베이스를 조회하여 사용자가 있는지를 체크한다.
		UserVO userChkVo = new UserVO();
		userChkVo.setUserId(strUserId);
		UserVO userChk = userSvc.getUser(userChkVo);		

		String strUserName = "";
		String strGroupId = "";
		String strGroupName = "";
		String strAuthId = "";
		String strAuthName = "";
		String strExcelAuth = "";
		String strPassword = "";
		String strOfficePhone = "";
		String strOfficePhoneFormat = "";
		String strMobliePhone = "";
		String strMobliePhoneFormat = "";
		String strEmail = "";
		String strIp = "";
		
		String userIp = request.getRemoteAddr();

		logger.info(">>>> userIp :"+userIp);
	
		if(userChk != null)
		{
			
			strUserId= userChk.getUserId();
			strUserName = userChk.getUserName();
			strGroupId = userChk.getGroupId();
			strGroupName = userChk.getGroupName();
			strAuthId = userChk.getAuthId();
			strAuthName = userChk.getAuthName();
			strExcelAuth = userChk.getExcelAuth();
			strPassword = userChk.getPassword();
			strOfficePhone = userChk.getOfficePhone();
			strOfficePhoneFormat = userChk.getOfficePhoneFormat();
			strMobliePhone = userChk.getMobliePhone();
			strMobliePhoneFormat = userChk.getMobliePhoneFormat();
			strEmail = userChk.getEmail();
			strIp = userChk.getIp();

			// # 3. Session 객체에 셋팅
			
			HttpSession session = request.getSession(false);
			
			if(session != null)
			{
				session.invalidate();
			}
				
				session = request.getSession(true);
				session.setAttribute("strUserId", strUserId);
				session.setAttribute("strUserName", strUserName);
				session.setAttribute("strGroupId", strGroupId);
				session.setAttribute("strGroupName", strGroupName);
				session.setAttribute("strAuthId", strAuthId);
				session.setAttribute("strAuthName", strAuthName);
				session.setAttribute("strExcelAuth", strExcelAuth);
				session.setAttribute("strPassword", strPassword);
				session.setAttribute("strOfficePhone", strOfficePhone);
				session.setAttribute("strOfficePhoneFormat", strOfficePhoneFormat);
				session.setAttribute("strMobliePhone", strMobliePhone);
				session.setAttribute("strMobliePhoneFormat", strMobliePhoneFormat);
				session.setAttribute("strEmail", strEmail);
				session.setAttribute("strIp", strIp);
				
				//로그인 이력처리		
				/*
				userChk.setLoginYn("Y");
				userChk.setConnectIp(userIp);
				try{
					//cmmService.setUserState(userChk);
					//cmmService.setInsertUserState(userChk);
					//cmmService.setUpdateUserState(userChk);
				}catch(Exception e){
					logger.debug("[Error]USER SQL lock 오류");
				}
		        */
				mv.addObject("userId", strUserId);
				
				strMainUrl = "addys/addysMain";
				
			} else {//app 상요자 정보가 없는경우
	
				logger.info(">>> 상담App 사용자 정보 없음");
				strMainUrl = "addys/loginFail";
			}
			
			mv.addObject("userId", strUserId);
			
			mv.setViewName(strMainUrl);
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
	/**
	 * Logout 처리
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addys/logout")
	public ModelAndView logout(HttpServletRequest request) throws BizException
	{
		
		logger.info("Good bye addys! ");
		
		HttpSession session = request.getSession(false);
	 	session.removeAttribute("strUserId");
        session.removeAttribute("strUserName");
        session.removeAttribute("strGroupId");
        session.removeAttribute("strGroupName");
        session.removeAttribute("strAuthId");
        session.removeAttribute("strAuthName");
        session.removeAttribute("strExcelAuth");
        session.removeAttribute("strPassword");
        session.removeAttribute("strOfficePhone");
        session.removeAttribute("strOfficePhoneFormat");
        session.removeAttribute("strMobliePhone");
        session.removeAttribute("strMobliePhoneFormat");
        session.removeAttribute("strEmail");
        session.removeAttribute("strIp");
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addys/loginForm");

		return mv;
	}
	
	/**
	 *main request test
	 */
	@RequestMapping("/addysmain")
    public String addysmain(@RequestParam(value = "addys1") String addys1) 
	{
		
		logger.info("[addys1]"+addys1);

		return "addys/addysMain";
	}
	
	/**
	 * ajax test
	 */
	@RequestMapping("/addys/addyscheck")
	public @ResponseBody
	String addysCheck(@RequestParam(value = "id") String id,
				       @RequestParam(value = "pwd") String pwd) 
	{

		logger.info("[id]"+id);
		logger.info("[pwd]" + pwd);
		
		String chkresult="0";
		
		if(id.equals(pwd)){
			chkresult="1";
		}
		
		return chkresult;

	 }
}
