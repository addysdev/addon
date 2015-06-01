package com.offact.addys.controller;

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
import com.offact.addys.service.common.CommonService;
import com.offact.addys.service.common.UserService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.order.TargetVO;

import com.whois.whoisSMS;

/**
 * Handles requests for the application home page.
 */
@Controller

public class SmsController {

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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/whois/sms", method = RequestMethod.POST)
	public ModelAndView whoisSms(HttpServletRequest request,
			                  HttpServletResponse response) throws BizException 
	{
		
		logger.info("sms");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("whois/whois_sms_utf8");
		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/whois/point", method = RequestMethod.GET)
	public ModelAndView whoisPoint(HttpServletRequest request,
			                  HttpServletResponse response) throws BizException 
	{
		
		logger.info("sms:point");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("whois/whois_point_utf8");
		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/whois/statistic", method = RequestMethod.GET)
	public ModelAndView whoisStatistic(HttpServletRequest request,
			                  HttpServletResponse response) throws BizException 
	{
		
		logger.info("sms:statistic");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("whois/whois_statistic_utf8");
		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/whois/smssendrsult", method = RequestMethod.GET)
	public ModelAndView smsSendResult(HttpServletRequest request,
			                  HttpServletResponse response) throws BizException 
	{
		
		logger.info("Send");
		
		ModelAndView mv = new ModelAndView();

		String sms_id = "netscyber";
		String sms_passwd = "doeltm0407";
		String sms_type = "L";	// 설정 하지 않는다면 80byte 넘는 메시지는 쪼개져서 sms로 발송, L 로 설정하면 80byte 넘으면 자동으로 lms 변환
		
		whoisSMS whois_sms = new whoisSMS();

		// 로그인
		whois_sms.login (sms_id, sms_passwd);

		/*
		* 문자발송에 필요한 발송정보
		*/
		// 받는사람번호
		String sms_to = "010-6747-1995";
		// 보내는 사람번호
		String sms_from = "0707-012-4250";
		// 발송예약시간 (현재시간보다 작거나 같으면 즉시 발송이며 현재시간보다 10분이상 큰경우는 예약발송입니다.)
		String sms_date = request.getParameter("sms_date");
		// 보내는 메세지
		String sms_msg = "[애디스]SMS 테스트 메세지입니다.";
		// 발송시간을 파라메터로 받지 못한경우 현재시간을 입력해줍니다.
		if (sms_date == null) {
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
		   Date cNow = new Date();
		   sms_date = sdf.format(cNow);
		}

		// UTF-8 설정
		whois_sms.setUtf8();

		// 파라메터 설정
		whois_sms.setParams (sms_to,sms_from,sms_msg,sms_date, sms_type);

		// 문자발송
		whois_sms.emmaSend();

		// 결과값 가져오기
		int retCode = whois_sms.getRetCode();

		// 발송결과 메세지
		String retMessage = whois_sms.getRetMessage();

		// 성공적으로 발송한경우 남은 문자갯수( 종량제 사용의 경우, 남은 발송가능한 문자수를 확인합니다.)
		int retLastPoint = whois_sms.getLastPoint();

        mv.addObject("sms_to", sms_to);
        mv.addObject("sms_from",sms_from);
        mv.addObject("sms_date", "");
        mv.addObject("sms_msg", "");
        mv.addObject("retCode", retCode);
        mv.addObject("retMessage", retMessage);
        mv.addObject("retLastPoint", retLastPoint);

		mv.setViewName("whois/smsSendResult");
		return mv;
	}

}
