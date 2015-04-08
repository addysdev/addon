package com.offact.batch;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;

/**
 *
 * @author lim
 *
 */

@Controller
public class DealBatch extends QuartzJobBean {

    private final Logger 			batchloger = Logger.getLogger("batchlog");

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		batchloger.debug("########################  CRON    DealBatch START.....!");
		URL url;// URL 주소 객체
		URLConnection connection;// URL접속을 가지는 객체
		try {


			if(Inet4Address.getLocalHost().getHostName().equals("offactsolapp01.offactc.net")){

				ResourceBundle rb = ResourceBundle.getBundle("config");
				rb.getString("cs.host.url");
				String strHost = rb.getString("cs.host.url");

				// CATEGORY
				url = new URL(strHost + "/cs/categoryBatch");
				connection = url.openConnection();
				connection.getInputStream();



				Date date = new Date();
		        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");


		        String today = sdformat.format(date);

				// DEAL
		        // 지정한 일자로 강제 호출
		        // http://localhost:8080/cs/dealBatch?start_date=2014-02-01

				url = new URL(strHost + "/cs/dealBatch?start_date="+today);
				connection = url.openConnection();
				connection.getInputStream();





				// ConsultExile
				url = new URL(strHost + "/cs/regiConsultExile");
				connection = url.openConnection();
				connection.getInputStream();
			}else{
				batchloger.debug("CS2 Pass!!!");
			}




		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			batchloger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			batchloger.error(e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		batchloger.debug("########################  CRON    DealBatch END.....!");
	}
}
