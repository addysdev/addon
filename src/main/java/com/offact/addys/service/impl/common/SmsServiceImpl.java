package com.offact.addys.service.impl.common;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.common.SmsService;
import com.offact.addys.vo.common.SmsVO;


import com.whois.whoisSMS;

import javax.mail.internet.*;

/**
 * @author 4530
 *
 */
@Service
public class SmsServiceImpl implements SmsService {

	private final Logger        logger = Logger.getLogger(getClass());


	@Override

	public SmsVO sendSms(SmsVO sms) throws BizException{


        try {

        	String sms_id = sms.getSmsId();
    		String sms_passwd = sms.getSmsPw();
    		String sms_type = sms.getSmsType();	// 설정 하지 않는다면 80byte 넘는 메시지는 쪼개져서 sms로 발송, L 로 설정하면 80byte 넘으면 자동으로 lms 변환
    		
    		whoisSMS whois_sms = new whoisSMS();

    		// 로그인
    		whois_sms.login (sms_id, sms_passwd);

    		/*
    		* 문자발송에 필요한 발송정보
    		*/
    		// 받는사람번호
    		String sms_to = sms.getSmsTo();
    		// 보내는 사람번호
    		String sms_from = sms.getSmsFrom();
    		// 발송예약시간 (현재시간보다 작거나 같으면 즉시 발송이며 현재시간보다 10분이상 큰경우는 예약발송입니다.)
    		String sms_date = null;
    		// 보내는 메세지
    		String sms_msg = sms.getSmsMsg();
    		// 발송시간을 파라메터로 받지 못한경우 현재시간을 입력해줍니다.
    		if (sms_date == null) {
    		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREA);
    		   Date cNow = new Date();
    		   sms_date = sdf.format(cNow);
    		}

    		// UTF-8 설정
    		whois_sms.setUtf8();
    	    sms_msg = new String(sms_msg.getBytes(), "ISO-8859-1");
    	    logger.debug("Send SMS Msg : {}"+sms_msg);
    	    //logger.debug("Send SMS Msg UTF-8: {}"+URLEncoder.encode(sms_msg, "UTF-8"));

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
           
    		sms.setResultCode(""+retCode);
    		sms.setResultMessage(""+retMessage);
    		sms.setResultLastPoint(""+retLastPoint);
    		

        } catch(Exception e){
	    	
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());
	
	    }

        return sms;

	}

}