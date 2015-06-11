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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

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
import com.offact.addys.service.common.SmsService;
import com.offact.addys.service.master.ProductMasterService;
import com.offact.addys.service.master.StockMasterService;
import com.offact.addys.vo.common.SmsVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class BatchController {

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
	
	@Value("#{config['offact.dev.option']}")
    private String devOption;
    
    @Value("#{config['offact.dev.sms']}")
    private String devSms;
    
    @Value("#{config['offact.sms.smsid']}")
    private String smsId;
    
    @Value("#{config['offact.sms.smspw']}")
    private String smsPw;
    
    @Value("#{config['offact.sms.smstype']}")
    private String smsType;
    
    @Value("#{config['offact.sms.sendno']}")
    private String sendNo;
	
    @Autowired
    private StockMasterService stockMasterSvc;
    
    @Autowired
    private CommonService commonSvc;
    
    @Autowired
    private SmsService smsSvc;
    
   /**
    * 보유재고 마스터 일괄배치
    *
    * @param MultipartFileVO
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping({"/batch/holdstockBatch"})
   public ModelAndView holdStockBatch(HttpServletRequest request, 
   		                              HttpServletResponse response) throws IOException, BizException
   {
     
     //log Controller execute time start
	 String logid=logid();
     long t1 = System.currentTimeMillis();
     logger.info("["+logid+"] BatchController start ");
   			
     ModelAndView mv = new ModelAndView();

     String strUserId = "system";

     List stockGroupList = new ArrayList(); //타이틀을 통한 재고값 대상 지점을 담아둔다.
     List stockMasterListResult = new ArrayList(); //지점별 품목별 재고마스터(보유재고) 데이타를 담는다.
     
     List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
     List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타

     
     StockMasterVO stockGroupVO = null; //재고대상지점
   	 StockMasterVO stockMasterResultVO = null; //재고마스터

   	 
   	 //보유재고 자동 계산후 업데이트 대상 데이타를 담는 로직 (stockMasterListResult)
    
     
     /*
     for(int t=0 ; t<stockMasterListResult.size(); t++){//테스트 프린트
     	StockMasterVO stockMasterTestVO = new StockMasterVO();
     	stockMasterTestVO=(StockMasterVO)stockMasterListResult.get(t);
     	stockMasterTestVO.getProductCode();
     	this.logger.debug("STOCK 품목아이디:" + stockMasterTestVO.getProductCode());
     	this.logger.debug("STOCK 조직아이디:" + stockMasterTestVO.getGroupId());
     	this.logger.debug("STOCK 보유재고값:" + stockMasterTestVO.getSafeStock());
     }


   
     //DB Update처리
     
     Map rtmMap = this.stockMasterSvc.holdRegiExcelUpload(stockMasterListResult);

     rtnErrorList = (List)rtmMap.get("rtnErrorList");
     rtnSuccessList = (List)rtmMap.get("rtnSuccessList");

     this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
  
     mv.addObject("rtnErrorList", rtnErrorList);
     mv.addObject("rtnSuccessList", rtnSuccessList);
    
     mv.setViewName("/master/uploadResult");
     */
     //log Controller execute time end
     long t2 = System.currentTimeMillis();
     logger.info("["+logid+"] BatchController end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 	
     return mv;
     
   }  
   
   /**
    * 마감일자 미발신건 SMS 일괄배치
    *
    * @param MultipartFileVO
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping({"/batch/closemisssendsms"})
   public ModelAndView closeMissSendSms(HttpServletRequest request, 
   		                              HttpServletResponse response) throws IOException, BizException
   {
     
     //log Controller execute time start
	 String logid=logid();
     long t1 = System.currentTimeMillis();
     logger.info("["+logid+"] Batch start ");
   			
     ModelAndView mv = new ModelAndView();

     try{
         
    	//SMS발송
    	
		SmsVO smsVO = new SmsVO();
		SmsVO resultSmsVO = new SmsVO();
		
		smsVO.setSmsId(smsId);
		smsVO.setSmsPw(smsPw);
		smsVO.setSmsType(smsType);
		smsVO.setSmsFrom(sendNo);
		smsVO.setSmsUserId("system");

    	List<UserVO> smsNoList = null;
    	UserVO userConVO = new UserVO();
    	String smsNo="";
    	
    	smsNoList=commonSvc.getSmsBatchList(userConVO);

		smsVO.setSmsMsg("[애디스] 회수 마감일이 하루 남았습니다.회수요청건에 대해 발신처리 부탁드립니다.");

		for (int j=0;j<smsNoList.size();j++){
			
			UserVO smsNoVO =new UserVO();
			smsNoVO=smsNoList.get(j);
			smsNo=smsNoVO.getMobliePhone();
			logger.debug("sms smsNo:"+smsNo);
			
			smsVO.setSmsTo(smsNo);
			
			logger.debug("#########devOption :"+devOption);
			String[] devSmss= devSms.split("\\^");
			
    		if(devOption.equals("true")){
				for(int z=0;z<devSmss.length;z++){
					
					if(devSmss[z].equals(smsNo.trim().replace("-", ""))){
						resultSmsVO=smsSvc.sendSms(smsVO);
					}
				}
			}else{
				resultSmsVO=smsSvc.sendSms(smsVO);
			}
    		
    		logger.debug("sms resultSmsVO.getResultCode() :"+resultSmsVO.getResultCode());
			logger.debug("sms resultSmsVO.getResultMessage() :"+resultSmsVO.getResultMessage());
			logger.debug("sms resultSmsVO.getResultLastPoint() :"+resultSmsVO.getResultLastPoint());

		}

	
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
	        mv.setViewName("/master/uploadResult");
	
	        //log Controller execute time end
	        long t2 = System.currentTimeMillis();
	        logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	    	
	    }
    
     mv.setViewName("/master/uploadResult");

     //log Controller execute time end
     long t2 = System.currentTimeMillis();
     logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 	
     return mv;
     
   }   
    
}
