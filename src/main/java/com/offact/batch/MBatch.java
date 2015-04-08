package com.offact.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

public class MBatch {


	public static void getHttps(String urlString) throws IOException, NoSuchAlgorithmException, KeyManagementException {

		  // Get HTTPS URL connection
		  URL url = new URL(urlString);
		  HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

		  // Set Hostname verification
		  conn.setHostnameVerifier(new HostnameVerifier() {
		   @Override
		   public boolean verify(String hostname, SSLSession session) {
		    // Ignore host name verification. It always returns true.
		    return true;
		   }

		  });

		  // SSL setting
		  SSLContext context = SSLContext.getInstance("TLS");
		  context.init(null, null, null);  // No validation for now
		  conn.setSSLSocketFactory(context.getSocketFactory());

		  // Connect to host
		  conn.connect();
		  conn.setInstanceFollowRedirects(true);

		  // Print response from host
		  InputStream in = conn.getInputStream();
		  BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		  String line = null;
		  while ((line = reader.readLine()) != null) {
		   System.out.printf("%s\n", line);
		  }

		  reader.close();
		 }


	public static void main(String[] args) {
		// TODO Auto-generated method stub




		URL url;// URL 주소 객체
		HttpsURLConnection  connection;// URL접속을 가지는 객체
		try {
			ResourceBundle rb = ResourceBundle.getBundle("config");
			rb.getString("cs.host.url");
			String strHost = rb.getString("cs.host.url");

			strHost = "https://cs1.offactcorp.com";


			Date date = new Date();
	        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");


	        String today = sdformat.format(date);

	        try {
				getHttps(strHost + "/cs/dealBatch?start_date=2014-04-01");
			} catch (KeyManagementException | NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-01");
//			connection = url.openConnection();
//			connection.getInputStream();
			System.gc();




			// DEAL
	        // 지정한 일자로 강제 호출
	        // http://localhost:8080/cs/dealBatch?start_date=2014-02-01

//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-01");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-02");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-03");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-04");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-05");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-06");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-07");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-08");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-09");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-10");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-11");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-12");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-13");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-14");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-15");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-16");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-17");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-18");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-19");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-20");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-21");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-22");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-23");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-24");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-25");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-26");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-27");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-28");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-29");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-30");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-03-31");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-01");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-02");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-03");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-04");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-05");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-06");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-07");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-08");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-09");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-10");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-11");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-12");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-13");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-14");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-15");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-16");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-17");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-18");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-19");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-20");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-21");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-22");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-23");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-24");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-25");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-26");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-27");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-28");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-29");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-04-30");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-05-01");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-05-02");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-05-03");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-05-04");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-05-05");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-05-06");connection = url.openConnection();connection.getInputStream();System.gc();
//	        url = new URL(strHost + "/cs/dealBatch?start_date=2014-05-07");connection = url.openConnection();connection.getInputStream();System.gc();


		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
