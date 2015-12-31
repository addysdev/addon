package com.offact.batch;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;

import com.offact.framework.db.SqlSessionCommonDao;

@Controller
public class AsSmsBatch extends QuartzJobBean {

	@Autowired
	private SqlSessionCommonDao commonDao;

	private final Logger 			
	batchloger = Logger.getLogger("batchlog");

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
		batchloger.debug("########################  CRON    AsSmsBatch START.....!");

        URL url;// URL 주소 객체
        URLConnection connection;// URL접속을 가지는 객체
        try {

        	if(Inet4Address.getLocalHost().getHostName().equals("localhost.localdomain")){
	            ResourceBundle rb = ResourceBundle.getBundle("config");
	            rb.getString("offact.host.url");
	            String strHost = rb.getString("offact.host.url");

	            // CATEGORY
	            url = new URL(strHost + "/addon/batch/closeserviceendsms");
	            connection = url.openConnection();
	            connection.getInputStream();

			}else{
	        	batchloger.debug("Pass!!!");
			}

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    	batchloger.debug("########################  CRON    SmsBatch END.....!");

	}
}
