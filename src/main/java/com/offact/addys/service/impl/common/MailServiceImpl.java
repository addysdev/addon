package com.offact.addys.service.impl.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.common.MailService;
import com.offact.addys.vo.common.EmailVO;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;

import javax.mail.internet.*;

/**
 * @author 4530
 *
 */
@Service
public class MailServiceImpl implements MailService {

	private final Logger        logger = Logger.getLogger(getClass());

	@Autowired

    private JavaMailSender mailSender;

	@Override

	public boolean sendMail(EmailVO mail) throws BizException{

        MimeMessage message = mailSender.createMimeMessage();

        try {

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setSubject(mail.getSubject());

            // 수신인 다수
            String[] toArrayResult = new String[mail.getToEmails().size()];

            for(int i=0; i<mail.getToEmails().size(); i++){

            	toArrayResult[i] =  mail.getToEmails().get(i);

            }
            messageHelper.setTo(toArrayResult);
            //messageHelper.setBcc(bcc);
            messageHelper.setFrom(mail.getFromEmail(), mail.getSubject());
            messageHelper.setText(mail.getMsg(), true);

            // 여러개의 파일첨부시
            if(mail.getFile()!=null){

            	for(int i=0; i< mail.getFile().size(); i++){

            		if( mail.getFile().get(i).isFile()){

            			messageHelper.addAttachment(mail.getAttcheFileName().get(i), mail.getFile().get(i));

            		}
            	}
            }

            logger.debug("Send Mail Subject : {}"+mail.getSubject());

            mailSender.send(message);

           

        } catch(Exception e){
	    	
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());
	
	    }

        return true;

	}

}