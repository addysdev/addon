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
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

// import com.offact.framework.ldap.PersonDaoImpl;

/**
 * @author lim
 */
public class LdapBatch extends QuartzJobBean {

	private final Logger 			batchloger = Logger.getLogger("batchlog");

    @Override
    // protected void executeInternal(JobExecutionContext arg0)
    // throws JobExecutionException {
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
    	batchloger.debug("########################  CRON    LdapBatch START.....!");



        URL url;// URL 주소 객체
        URLConnection connection;// URL접속을 가지는 객체
        try {

			if(Inet4Address.getLocalHost().getHostName().equals("offactsolapp01.offactc.net")){
	            ResourceBundle rb = ResourceBundle.getBundle("config");
	            rb.getString("cs.host.url");
	            String strHost = rb.getString("cs.host.url");

	            // CATEGORY
	            url = new URL(strHost + "/cs/adUserInfoReload");
	            connection = url.openConnection();
	            connection.getInputStream();

			}else{
	        	batchloger.debug("CS2 Pass!!!");
			}



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    	batchloger.debug("########################  CRON    LdapBatch END.....!");

    }
}
