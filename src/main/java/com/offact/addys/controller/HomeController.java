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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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

import com.offact.addys.service.common.UserService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.common.WorkVO;
import com.offact.addys.vo.order.TargetVO;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.framework.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {

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
	
	@Value("#{config['offact.host.url']}")
	private String host_url;

	@RequestMapping("/hello")
	 public @ResponseBody
	 String hello(@RequestParam(value = "name") String name,
				  @RequestParam(value = "gender") String gender,
				  @RequestParam(value = "email") String email,
				  @RequestParam(value = "phone") String phone,
				  @RequestParam(value = "city") String city) 
	{

		  System.out.println(name);
		  System.out.println(gender);
		  System.out.println(email);
		  System.out.println(phone);
		  System.out.println(city);

//		  String str = "{\"user\": { \"name\": \"" + name + "\",\"gender\": \""
//		    + gender + "\",\"email\": \"" + email + "\",\"phone\": \""
//		    + phone + "\",\"city\": \"" + city + "\"}}";

		  String str = name;

		  return str;

	 }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/gmroi", method = RequestMethod.GET)
	public ModelAndView gmroi(HttpServletRequest request,
			                  HttpServletResponse response) throws BizException 
	{
		
		logger.info("gmroi");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("common/gmroiMain");
		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/gmroiclc", method = RequestMethod.GET)
	public ModelAndView gmroiclc(HttpServletRequest request,
                                 HttpServletResponse response) throws BizException 
	{
		
		logger.info("gmroiclc");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("common/gmroiClc");
		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/offactloginform", method = RequestMethod.GET)
	public ModelAndView offactLoginForm(HttpServletRequest request,
                                 HttpServletResponse response) throws BizException 
	{
		
		logger.info("offactLoginForm");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("common/offactLoginForm");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/offactdev", method = RequestMethod.GET)
	public ModelAndView offactDev(HttpServletRequest request,
                                 HttpServletResponse response) throws BizException 
	{
		
		logger.info("offactDev");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("common/offactDev");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/offactmap", method = RequestMethod.GET)
	public ModelAndView offactMap(HttpServletRequest request,
                                 HttpServletResponse response) throws BizException 
	{
		
		logger.info("offactMap");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("common/offactMap");
		return mv;
	}
	
	
	/**
	 * Login 처리
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/offact/login")
	public ModelAndView offactLogin(HttpServletRequest request,
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
		userChkVo.setInPassword(strUserPw);
		UserVO userChk = userSvc.getUser(userChkVo);		

		String strUserName = "";
		String strGroupId = "";
		String strGroupName = "";
		String strAuthId = "";
		String strAuth = "";
		String strAuthName = "";
		String strExcelAuth = "";
		String strPassword = "";
		String strOfficePhone = "";
		String strOfficePhoneFormat = "";
		String strMobliePhone = "";
		String strMobliePhoneFormat = "";
		String strEmail = "";
		String strIp = "";
		
		String ip = request.getRemoteAddr(); 
		logger.info(">>>> RemoteAddr :"+ip);

		strIp = ip;//Client 외부IP or G/W
		
		logger.info(">>>> [strIp] :"+strIp);
	
		if(userChk != null)
		{
			//패스워드 체크
			if(!userChk.getPassword().equals(userChk.getInPassword())){
				
				logger.info(">>> 비밀번호 오류");
				strMainUrl = "common/offactLoginFail";
				
				mv.addObject("userId", strUserId);
				
				mv.setViewName(strMainUrl);
				
				//log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
				return mv;
				
			}
			
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
			strAuth =userChk.getAuth();

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
				session.setAttribute("strAuth", strAuth);
				
				//로그인 상태처리		
				
				userChk.setUserId(strUserId);
				userChk.setLoginYn("Y");
				userChk.setIp(strIp);
				
				try{
					userSvc.regiLoginYnUpdate(userChk);
				}catch(Exception e){
					logger.debug("[Error]USER SQL lock 오류");
				}
		        
				mv.addObject("userId", strUserId);

				strMainUrl = "common/offactDev";
				
			} else {//app 상요자 정보가 없는경우
	
				logger.info(">>> 상담App 사용자 정보 없음");
				strMainUrl = "common/offactLoginFail";
			}
			
			mv.addObject("userId", strUserId);
			
			mv.setViewName(strMainUrl);
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/errors404", method = RequestMethod.GET)
	public ModelAndView errors404(HttpServletRequest request,
			                      HttpServletResponse response) throws BizException 
	{
		logger.info("errors404");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/404");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/errors500", method = RequestMethod.GET)
	public ModelAndView errors500(HttpServletRequest request,
			                      HttpServletResponse response) throws BizException 
	{
		
		logger.info("errors500");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/500");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/errors", method = RequestMethod.GET)
	public ModelAndView errors(HttpServletRequest request,
			                   HttpServletResponse response) throws BizException 
	{
		
		logger.info("errors");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/errors");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/systemworking", method = RequestMethod.GET)
	public ModelAndView systemWorking(HttpServletRequest request,
			                    HttpServletResponse response) throws BizException 
	{
		
		logger.info("systemwork");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/systemWorking");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/loginFail", method = RequestMethod.GET)
	public ModelAndView loginFail(HttpServletRequest request,
			                      HttpServletResponse response) throws BizException 
	{
		
		logger.info("loginFail");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("addys/loginFail");
		return mv;
	}
	//동작
	@RequestMapping(value = "/redirectUrl2", method = RequestMethod.GET)
	public ModelAndView redirectUrl2()
	{
		return new ModelAndView("redirect:/index");
	}

	//동작
	@RequestMapping(value = "/redirectUrl3", method = RequestMethod.GET)
	public ModelAndView redirectUrl3()
	{
		
		RedirectView redirectView = new RedirectView("/index");
		redirectView.setContextRelative(true);

		Map<String, ?> map = null;
		ModelAndView mv = new ModelAndView(redirectView, map);
		return mv;
	}

	//동작
	@RequestMapping(value = "/redirectUrl4", method = RequestMethod.GET)
	public ModelAndView redirectUrl4()
	{
		
		ModelAndView mv = new ModelAndView();

		mv.setView(new RedirectView("/addys/index"));
		Object params = null;
		mv.addObject("parameter",params);
		return mv;
	}

	//동작
	@RequestMapping(value = "/redirectUrl5", method = RequestMethod.POST)
	public ModelAndView redirectUrl5()
	{
		
		ModelAndView mv = new ModelAndView();

		mv.setView(new RedirectView("/addys/index"));
		Object params = null;
		mv.addObject("parameter",params);
		return mv;
	}
	
}
