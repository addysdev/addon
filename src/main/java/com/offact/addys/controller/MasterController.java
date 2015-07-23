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
import com.offact.addys.service.common.UserService;
import com.offact.addys.service.master.ProductMasterService;
import com.offact.addys.service.master.StockMasterService;
import com.offact.addys.service.master.StockService;
import com.offact.addys.service.master.SalesService;
import com.offact.addys.service.master.OrderLimitService;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.SmsVO;
import com.offact.addys.vo.common.UserVO;
import com.offact.addys.vo.common.CompanyVO;
import com.offact.addys.vo.common.WorkVO;
import com.offact.addys.vo.manage.UserManageVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.master.ProductMasterVO;
import com.offact.addys.vo.master.StockVO;
import com.offact.addys.vo.master.SalesVO;
import com.offact.addys.vo.master.OrderLimitVO;
import com.offact.addys.vo.order.OrderVO;
import com.offact.addys.vo.recovery.RecoveryVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class MasterController {

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
    private CommonService commonSvc;
    
	@Autowired
	private UserService userSvc;
    
    @Autowired
    private ProductMasterService productMasterSvc;
    
    @Autowired
    private StockMasterService stockMasterSvc;
    
    @Autowired
    private StockService stockSvc;
    
    @Autowired
    private SalesService salesSvc;
    
    @Autowired
    private OrderLimitService orderLimitSvc;
    
    /**
     * 품목현황 관리화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/productmanage")
    public ModelAndView productManage(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){

        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        UserManageVO userConVO = new UserManageVO();
        
        userConVO.setUserId(strUserId);
        userConVO.setGroupId(strGroupId);

        // 조회조건저장
        mv.addObject("userConVO", userConVO);

        // 공통코드 조회 (사용자그룹코드)
        /*
        ADCodeManageVO code = new ADCodeManageVO();
        code.setCodeId("IG11");
        List<ADCodeManageVO> searchCondition1 = codeService.getCodeComboList(code);
        mv.addObject("searchCondition1", searchCondition1);
       */
       
        mv.setViewName("/master/productManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 품목 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/productpagelist")
    public ModelAndView productPageList(@ModelAttribute("productConVO") ProductMasterVO productConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : productConVO" + productConVO);

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        List<ProductMasterVO> productList = null;

        // 조회조건 null 일때 공백처리
        if (productConVO.getSearchGubun() == null) {
        	productConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (productConVO.getSearchValue() == null) {
        	productConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("productCon", productConVO);

        // 페이징코드
        productConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(productConVO.getCurPage(), productConVO.getRowCount()));
        productConVO.setPage_limit_val2(StringUtil.nvl(productConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        productList = productMasterSvc.getProductList(productConVO);
        mv.addObject("productList", productList);

        // totalCount 조회
        String totalCount = String.valueOf(productMasterSvc.getProductCnt(productConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/master/productPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/master/productexcelform")
   	public ModelAndView productExcelForm(HttpServletRequest request) throws BizException 
       {
   		
   		ModelAndView mv = new ModelAndView();
   		
   		mv.setViewName("/master/productExcelForm");
   		
   		return mv;
   	}
   /**
    * 품목관리 일괄등록
    *
    * @param MultipartFileVO
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping({"/master/productexcelimport"})
   public ModelAndView productExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
   		                            HttpServletRequest request, 
   		                            HttpServletResponse response, 
   		                            String fileName, 
   		                            String extension ) throws IOException, BizException
   {
     
     //log Controller execute time start
	 String logid=logid();
     long t1 = System.currentTimeMillis();
     logger.info("["+logid+"] Controller start : fileVO" + fileVO);
   			
     ModelAndView mv = new ModelAndView();
     
     String fname="";

  // 사용자 세션정보
     HttpSession session = request.getSession();
     String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
     String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
     String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
     String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
     
   //오늘 날짜
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
     Date currentTime = new Date();
     String strToday = simpleDateFormat.format(currentTime);
     
     if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
     	
     	strIp = request.getRemoteAddr(); //로그인 상태처리		
 		UserVO userState =new UserVO();
 		userState.setUserId(strUserId);
 		userState.setLoginYn("N");
 		userState.setIp(strIp);
 		userState.setConnectIp(sClientIP);
 		userSvc.regiLoginYnUpdate(userState);
         
         //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
 		
     	mv.setViewName("/addys/loginForm");
    		return mv;
		}

     ResourceBundle rb = ResourceBundle.getBundle("config");
     String uploadFilePath = rb.getString("offact.upload.path") + "excel/productmaster/"+strToday+"/";
     
     this.logger.debug("파일정보:" + fileName + extension);
     this.logger.debug("file:" + fileVO);

     List excelUploadList = new ArrayList();//업로드 대상 데이타
     
     String excelInfo = "";//excel 추출데이타
     List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
     List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타
     
     String errorMsgList="";

     if (fileName != null) {
   	  
       List<MultipartFile> files = fileVO.getFiles();
       List fileNames = new ArrayList();
       String orgFileName = null;

       if ((files != null) && (files.size() > 0))
       {
         for (MultipartFile multipartFile : files)
         {
           orgFileName = t1 +"."+ extension;
           boolean check=setDirectory(uploadFilePath);
           
           String filePath = uploadFilePath;

           File file = new File(filePath + orgFileName);
           multipartFile.transferTo(file);
           fileNames.add(orgFileName);
         }
    
       }

       fname = uploadFilePath + orgFileName;

       FileInputStream fileInput = null;

       fileInput = new FileInputStream(fname);

       XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
       XSSFSheet sheet = workbook.getSheetAt(0);//첫번째 sheet
  
       int TITLE_POINT =0;//타이틀 항목위치
       int ROW_START = 1;//data row 시작지점
       
       int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
       int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
       
       XSSFCell myCell = null;
     
       this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
       this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);
           
           try {
 
	           for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS; rowcnt++) {
	             
	             ProductMasterVO productMasterVO = new ProductMasterVO();
	             XSSFRow row = sheet.getRow(rowcnt);

	             //cell type 구분하여 담기  
	             String[] cellItemTmp = new String[TOTAL_CELLS]; 
		         for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
		            myCell = row.getCell(cellcnt); 
		            
		            if(myCell!=null){
			            if(myCell.getCellType()==0){ //cell type 이 숫자인경우
			            	String rawCell = String.valueOf(myCell.getNumericCellValue());
			            	int endChoice = rawCell.lastIndexOf("E");
			            	if(endChoice>0){
			            		rawCell= rawCell.substring(0, endChoice);
				            	rawCell= rawCell.replace(".", "");
			            	}
			            	cellItemTmp[cellcnt]=rawCell;
			            }else if(myCell.getCellType()==1){ //cell type 이 일반/문자 인경우
			            	cellItemTmp[cellcnt] = myCell.getStringCellValue(); 
			            }else{//그외 cell type
			            	cellItemTmp[cellcnt] = ""; 
			            }
			            this.logger.debug("row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt]);
			            excelInfo="row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt];
		            }else{
		            	
		            	cellItemTmp[cellcnt] = "";
		            }
		         }
	         
		         if(cellItemTmp.length>0 && cellItemTmp[0] != ""){
		        	 
		        	 //productCode 값 celltype에 의해 뒤에 0이 없는경우 처리
		        	 String cellProductCode="";
		
		        	 if(cellItemTmp[0].length()<8){
		        		 int fill=8-cellItemTmp[0].length();
		        		 String fillString="0";
		        		 
		        		 for (int f=0; f<fill-1;f++){
		        			 fillString=fillString+"0";
		        		 }    		 
		        		 cellProductCode= cellItemTmp[0]+fillString;
		        		 
		        	 }else{
		        		 cellProductCode= cellItemTmp[0];
		        	 }
		        		 
		        	 if(cellItemTmp.length>0){ productMasterVO.setProductCode(cellProductCode);}
		        	 if(cellItemTmp.length>1){ productMasterVO.setBarCode(cellItemTmp[1]);}
		        	 if(cellItemTmp.length>2){ productMasterVO.setProductName(cellItemTmp[2]);}
		        	 if(cellItemTmp.length>3){ productMasterVO.setProductPrice(cellItemTmp[3]);}
		        	 if(cellItemTmp.length>4){ productMasterVO.setVatRate(cellItemTmp[4]);}
		        	 if(cellItemTmp.length>5){ productMasterVO.setCompanyCode(cellItemTmp[5]);}
		        	 if(cellItemTmp.length>6){ productMasterVO.setGroup1(cellItemTmp[6]);}
		        	 if(cellItemTmp.length>7){ productMasterVO.setGroup1Name(cellItemTmp[7]);}
		        	 if(cellItemTmp.length>8){ productMasterVO.setGroup2(cellItemTmp[8]);}
		        	 if(cellItemTmp.length>9){ productMasterVO.setGroup2Name(cellItemTmp[9]);}
		        	 if(cellItemTmp.length>10){ productMasterVO.setGroup3(cellItemTmp[10]);}
		        	 if(cellItemTmp.length>11){ productMasterVO.setGroup3Name(cellItemTmp[11]);}
	
		             productMasterVO.setCreateUserId(strUserId);
		             productMasterVO.setUpdateUserId(strUserId);
		             productMasterVO.setDeletedYn("N");
			
			         excelUploadList.add(productMasterVO);
		         }
		     	
		       }
           }catch (Exception e){
  
   	    	  excelInfo = excelInfo+"[error] : "+e.getMessage();
	    	  ProductMasterVO productMasterVO = new ProductMasterVO();
	    	  productMasterVO.setErrMsg(excelInfo);
	    	 
	    	  this.logger.info("["+logid+"] Controller getErrMsg : "+productMasterVO.getErrMsg());
	         
	    	  rtnErrorList.add(productMasterVO);
	
	          mv.addObject("rtnErrorList", rtnErrorList);
	          mv.addObject("rtnSuccessList", rtnSuccessList);
	
	          mv.setViewName("/master/uploadResult");
	          
	          //작업이력
	    	  WorkVO work = new WorkVO();
	    	  work.setWorkUserId(strUserId);
	    	  work.setWorkCategory("PD");
	    	  work.setWorkCode("PD005");
	    	  work.setWorkKey3(fname);
	    	  commonSvc.regiHistoryInsert(work);
	          
	          //log Controller execute time end
	          long t2 = System.currentTimeMillis();
	          logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	  	 	
	          return mv;
    	   
       	}
     }
     
     //DB처리
     Map rtmMap = this.productMasterSvc.regiExcelUpload(excelUploadList);

     rtnErrorList = (List)rtmMap.get("rtnErrorList");
     rtnSuccessList = (List)rtmMap.get("rtnSuccessList");
     errorMsgList = (String)rtmMap.get("errorMsgList");

     this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
  
     mv.addObject("rtnErrorList", rtnErrorList);
     mv.addObject("rtnSuccessList", rtnSuccessList);
     
     mv.addObject("errorMsgList", errorMsgList);
       
     mv.setViewName("/master/uploadResult");
     
     //작업이력
	  WorkVO work = new WorkVO();
	  work.setWorkUserId(strUserId);
	  work.setWorkCategory("PD");
	  work.setWorkCode("PD001");
	  work.setWorkKey3(fname);
	  commonSvc.regiHistoryInsert(work);
	 
     //log Controller execute time end
     long t2 = System.currentTimeMillis();
     logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 	
     return mv;
         
    }
   /**
    * 품목상세현황
    *
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping(value = "/master/productmasterdetail")
   public ModelAndView productMasterDetail(HttpServletRequest request, 
   		                                 HttpServletResponse response,
   		                                 String productCode ) throws BizException 
   {
       
	   //log Controller execute time start
	   String logid=logid();
	   long t1 = System.currentTimeMillis();
	   logger.info("["+logid+"] Controller start ");
	
	   ModelAndView mv = new ModelAndView();
	   
	// 사용자 세션정보
       HttpSession session = request.getSession();
       String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
       String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
       String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
       String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
       
       if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
       	
       	strIp = request.getRemoteAddr(); //로그인 상태처리		
   		UserVO userState =new UserVO();
   		userState.setUserId(strUserId);
   		userState.setLoginYn("N");
   		userState.setIp(strIp);
   		userState.setConnectIp(sClientIP);
   		userSvc.regiLoginYnUpdate(userState);
           
           //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
   		
       	mv.setViewName("/addys/loginForm");
      		return mv;
		}
	   
	   ProductMasterVO productConVO = new ProductMasterVO();
	   ProductMasterVO productMasterVO = new ProductMasterVO();
	   StockMasterVO stockConVO = new StockMasterVO();
	   List<StockMasterVO> stockMasterList = null;
	   
	   productConVO.setProductCode(productCode);
	   stockConVO.setProductCode(productCode);
	   
       // 품목 상세조회
	   productMasterVO = productMasterSvc.getProductDetail(productConVO);
       mv.addObject("productMasterVO", productMasterVO);
	
	   // 품목 지점별 안전/보유재고현황
       stockMasterList = stockMasterSvc.getStockList(stockConVO);
	   mv.addObject("stockMasterList", stockMasterList);
	  
	   mv.setViewName("/master/productMasterDetail");
	   
	  //log Controller execute time end
	  long t2 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	 	
	   return mv;
   }   
    /**
  	 * Simply selects the home view to render by returning its name.
  	 * @throws BizException
  	 */
    @RequestMapping(value = "/master/safestockexcelform")
  	public ModelAndView safeStockExcelForm(HttpServletRequest request) throws BizException 
      {

  		ModelAndView mv = new ModelAndView();
  		mv.setViewName("/master/safeStockExcelForm");
  		
  		return mv;
  	}
    /**
 	 * Simply selects the home view to render by returning its name.
 	 * @throws BizException
 	 */
    @RequestMapping(value = "/master/holdstockexcelform")
 	public ModelAndView holdStockExcelForm(HttpServletRequest request) throws BizException 
     {
 		
 		ModelAndView mv = new ModelAndView();
 		
 		mv.setViewName("/master/holdStockExcelForm");
 		
 		return mv;
 	}
	  /**
	   * 안전/보유재고 마스터 일괄등록
	   *
	   * @param MultipartFileVO
	   * @param request
	   * @param response
	   * @param model
	   * @param locale
	   * @return
	   * @throws BizException
	   */
	  @RequestMapping({"/master/stockmasterexcelimport"})
	  public ModelAndView stockMasterExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
	  		                            HttpServletRequest request, 
	  		                            HttpServletResponse response, 
	  		                            String fileName, 
	  		                            String extension, 
	  		                            String importType ) throws IOException, BizException
	  {
	    
	    //log Controller execute time start
		String logid=logid();
	    long t1 = System.currentTimeMillis();
	    logger.info("["+logid+"] Controller start : fileVO" + fileVO);
	  			
	    ModelAndView mv = new ModelAndView();
	    
	    String fname ="";
	    
	 // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
      //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);

        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        String excelPath="";
        
        if("hold".equals(importType)){
        	excelPath="excel/holdmaster/";
        }else{
        	excelPath="excel/safemaster/";
        }
	
	    ResourceBundle rb = ResourceBundle.getBundle("config");
	    String uploadFilePath = rb.getString("offact.upload.path") + excelPath+strToday+"/";
	    
	    this.logger.debug("파일정보:" + fileName + extension);
	    this.logger.debug("file:" + fileVO);
	    
	    List stockGroupList = new ArrayList(); //타이틀을 통한 재고값 대상 지점을 담아둔다.
	    List stockMasterListResult = new ArrayList(); //지점별 품목별 재고마스터(안전재고) 데이타를 담는다.
	    
	    String excelInfo = "";//excel 추출데이타
	    List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
	    List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타
	    String errorMsgList ="";
	
	    if (fileName != null) {
	  	  
	      List<MultipartFile> files = fileVO.getFiles();
	      List fileNames = new ArrayList();
	      String orgFileName = null;
	
	      if ((files != null) && (files.size() > 0))
	      {
	        for (MultipartFile multipartFile : files)
	        {
	          orgFileName = t1 +"."+ extension;
	          boolean check=setDirectory(uploadFilePath);
	          
	          String filePath = uploadFilePath;
	
	          File file = new File(filePath + orgFileName);
	          multipartFile.transferTo(file);
	          fileNames.add(orgFileName);
	        }
	   
	      }
	
	      fname = uploadFilePath + orgFileName;
	
	      FileInputStream fileInput = null;
	
	      fileInput = new FileInputStream(fname);
	
	      XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
	      XSSFSheet sheet = workbook.getSheetAt(0);//첫번째 sheet
	 
	      int TITLE_POINT =0;//타이틀 항목위치
	      int ROW_START = 1;//data row 시작지점
	      int MASTER_START=3;//master cell 시작지점
	      
	      int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
	      int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
	      
	      XSSFCell productCodeCell = null;
	      XSSFCell safeStockCell = null;
	    
	      this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
	      this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);
	      
	      StockMasterVO stockGroupVO = null; //재고대상지점
	  	  StockMasterVO stockMasterResultVO = null; //재고마스터
	
	      XSSFRow titleRow = sheet.getRow(TITLE_POINT);
	      String[] groupInfoCodes=null;
	      String groupInfoCode="";
	      
	      //엑셀 타이틀을 통해 대상지점 리스트를 담는로직
	      for (int groupinfo=MASTER_START; groupinfo < TOTAL_CELLS; groupinfo++){
	      	
	      	stockGroupVO=new StockMasterVO();
	
	      	//titleRow.getCell(groupinfo).getStringCellValue();
	      	groupInfoCodes=titleRow.getCell(groupinfo).getStringCellValue().split("_");//지점 타이틀을 코드 _ 지점명으로 구분하여 자른다.
	      	groupInfoCode=groupInfoCodes[0];
	      	stockGroupVO.setGroupId(groupInfoCode);
	      	
	      	stockGroupList.add(stockGroupVO);
	      	this.logger.debug("담긴 조직아이디:" + stockGroupVO.getGroupId());
	
	      }
	
	      for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS; rowcnt++) {
	          
	          XSSFRow row = sheet.getRow(rowcnt);
	          int MASTER_RE_START=MASTER_START;//master cell 시작지점
	
	          try {
	        	  
	        	  for(int groupcnt=0; groupcnt<stockGroupList.size(); groupcnt++){
	        		  
	        		  stockMasterResultVO = new StockMasterVO();
	              	  
	              	  String excelProductCode ="";
	              	  String excelGroupId ="";
	              	  String excelStock="";
	        		  
	        		  //품목코드담기
	        		  productCodeCell = row.getCell(0);
	        		  
	        		  if(productCodeCell!=null){
			              if(productCodeCell.getCellType()==0){ //cell type 이 숫자인경우
			          
			            	 String rawCell = String.valueOf(productCodeCell.getNumericCellValue());
			            	 int endChoice = rawCell.lastIndexOf("E");
			            	 if(endChoice>0){
			            		rawCell= rawCell.substring(0, endChoice);
				            	rawCell= rawCell.replace(".", "");
			            	 }
			            	 excelProductCode=rawCell;
			            	 
			              }else if(productCodeCell.getCellType()==1){ //cell type 이 일반/문자 인경우
			            	 excelProductCode=productCodeCell.getStringCellValue();
			              }else{//그외 cell type
			            	stockMasterResultVO.setProductCode(""); 
			            	excelProductCode="";
			              }
	        		  }else{
	        			  excelProductCode="";
	        		  }
	        		  
		              //그룹아이디 담기
	        		  stockGroupVO= (StockMasterVO)stockGroupList.get(groupcnt);
	        		  excelGroupId=stockGroupVO.getGroupId();
	        		  
	        		  //재고 담기
	        		  safeStockCell = row.getCell(MASTER_RE_START++); 
	        		  
	        		  if(safeStockCell!=null){
			              if(safeStockCell.getCellType()==0){ //cell type 이 숫자인경우
			            	  excelStock=String.valueOf(safeStockCell.getNumericCellValue());
			              }else if(safeStockCell.getCellType()==1){ //cell type 이 일반/문자 인경우
			            	  excelStock=String.valueOf(safeStockCell.getStringCellValue());
			              }else{//그외 cell type
			            	  excelStock="";
			              }
	        		  }else{
	        			  excelStock="";
	        		  }
			          
	        		//productCode 값 celltype에 의해 뒤에 0이 없는경우 처리
		        	 String cellProductCode="";
		
		        	 if(excelProductCode.length()<8){
		        		 int fill=8-excelProductCode.length();
		        		 String fillString="0";
		        		 
		        		 for (int f=0; f<fill-1;f++){
		        			 fillString=fillString+"0";
		        		 }    		 
		        		 cellProductCode= excelProductCode+fillString;
		        		 
		        	 }else{
		        		 cellProductCode= excelProductCode;
		        	 }
	        		  
	        		  stockMasterResultVO.setProductCode(cellProductCode);
			          stockMasterResultVO.setGroupId(excelGroupId);
	
	      		      if("hold".equals(importType)){
	      		    	  stockMasterResultVO.setHoldStock(excelStock);
	      		      }else{
	      		    	  stockMasterResultVO.setSafeStock(excelStock);
	      		      }
	
	      		      stockMasterResultVO.setCreateUserId(strUserId);
	      		      stockMasterResultVO.setUpdateUserId(strUserId);
	      		      stockMasterResultVO.setDeletedYn("N");
	        		  
	        		  stockMasterListResult.add(stockMasterResultVO);
	        		  
	        		  this.logger.debug("row : ["+rowcnt+"]["+excelProductCode+"] cell : ["+MASTER_RE_START+"]["+excelGroupId+"] ->"+ excelStock);
		              excelInfo="row : ["+rowcnt+"]["+excelProductCode+"] cell : ["+MASTER_RE_START+"]["+excelGroupId+"] ->"+ excelStock;
	
	        	  }
	          	
	          }catch (Exception e) {
	
	        	  excelInfo = excelInfo+"[error] : "+e.getMessage();
	        	  StockMasterVO stockMasterVO = new StockMasterVO();
	        	  stockMasterVO.setErrMsg(excelInfo);
	  	    	 
	  	    	  this.logger.info("["+logid+"] Controller getErrMsg : "+stockMasterVO.getErrMsg());
	  	         
	  	    	  rtnErrorList.add(stockMasterVO);
	  	
	  	          mv.addObject("rtnErrorList", rtnErrorList);
	  	          mv.addObject("rtnSuccessList", rtnSuccessList);
	
	  	          mv.setViewName("/master/uploadResult");
	  	          
	  		     //작업이력
	  	          WorkVO work = new WorkVO();
	  		    
	  		      if("hold".equals(importType)){
	  		    	  work.setWorkCode("PD007");
	  		      }else{
	  		    	  work.setWorkCode("PD006");
	  		      }
	  			  work.setWorkUserId(strUserId);
	  			  work.setWorkCategory("PD");
	  			  work.setWorkKey3(fname);
	  			  commonSvc.regiHistoryInsert(work);
	  	    	 
	  	          //log Controller execute time end
	  	          long t2 = System.currentTimeMillis();
	  	          logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	     	 	
	  	          return mv;
	          }
	
	        }
	      
	    }
	
	    //DB처리
	    
	     Map rtmMap=null;
		 WorkVO work = new WorkVO();
	    
	     if("hold".equals(importType)){
	    	 this.logger.info("보유재고 DB Insert");
	    	 rtmMap = this.stockMasterSvc.holdRegiExcelUpload(stockGroupList,stockMasterListResult);
	    	 work.setWorkCode("PD003");
	     }else{
	    	 this.logger.info("안전재고 DB Insert");
	    	 rtmMap = this.stockMasterSvc.safeRegiExcelUpload(stockGroupList,stockMasterListResult);
	    	 work.setWorkCode("PD002");
	     }
	
	     rtnErrorList = (List)rtmMap.get("rtnErrorList");
	     rtnSuccessList = (List)rtmMap.get("rtnSuccessList");
	     errorMsgList = (String)rtmMap.get("errorMsgList");
	
	     this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
	  
	     mv.addObject("rtnErrorList", rtnErrorList);
	     mv.addObject("rtnSuccessList", rtnSuccessList);
	     
	     mv.addObject("errorMsgList", errorMsgList);
	    
	     mv.setViewName("/master/uploadResult");
	     
	     //작업이력

		 work.setWorkUserId(strUserId);
		 work.setWorkCategory("PD");
		 work.setWorkKey3(fname);
		 commonSvc.regiHistoryInsert(work);
	
	    //log Controller execute time end
	     long t2 = System.currentTimeMillis();
	     logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	 	
	    return mv;
	    
	  }
    /**
     * 재고 마스터 일괄등록(통파일 샘플-안전재고 ,보유재고)
     *
     * @param MultipartFileVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/master/stockmasterimport"})
    public ModelAndView stockMasterImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
    		                            HttpServletRequest request, 
    		                            HttpServletResponse response, 
    		                            String fileName, 
    		                            String extension ) throws IOException, BizException
    {
      
      //log Controller execute time start
	  String logid=logid();
	  long t1 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller start : fileVO" + fileVO);
    			
      ModelAndView mv = new ModelAndView();

   // 사용자 세션정보
      HttpSession session = request.getSession();
      String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
      String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
      String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
      String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
      
      if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
      	
      	strIp = request.getRemoteAddr(); //로그인 상태처리		
  		UserVO userState =new UserVO();
  		userState.setUserId(strUserId);
  		userState.setLoginYn("N");
  		userState.setIp(strIp);
  		userState.setConnectIp(sClientIP);
  		userSvc.regiLoginYnUpdate(userState);
          
          //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
  		
      	mv.setViewName("/addys/loginForm");
     		return mv;
		}

      ResourceBundle rb = ResourceBundle.getBundle("config");
      String uploadFilePath = rb.getString("offact.upload.path") + "excel/";
      
      this.logger.info("파일정보:" + fileName + extension);
      this.logger.info("file:" + fileVO);

      List userUploadList = new ArrayList();

      if (fileName != null) {
    	  
        List<MultipartFile> files = fileVO.getFiles();
        List fileNames = new ArrayList();
        String orgFileName = null;

        if ((files != null) && (files.size() > 0))
        {
          for (MultipartFile multipartFile : files)
          {
            orgFileName = multipartFile.getOriginalFilename();
            String filePath = uploadFilePath;

            File file = new File(filePath + orgFileName);
            multipartFile.transferTo(file);
            fileNames.add(orgFileName);
          }
          
        }

        String fname = uploadFilePath + orgFileName;

        FileInputStream fileInput = null;

        fileInput = new FileInputStream(fname);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        XSSFSheet sheet = workbook.getSheet("Sheet1");

        boolean validation = true;

        int DATA_START = 4;//data row 시작지점
        int MASTER_START=7;//master cell 시작지점
        int TITLE_POINT =2;//타이틀 항목위치
        int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
        
        this.logger.debug("sheet.getPhysicalNumberOfRows():" + sheet.getPhysicalNumberOfRows());
        this.logger.debug("sheet.getPhysicalNumberOfCells():" + sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells());
        
        List stockGroupList = new ArrayList(); //타이틀을 통한 재고값 대상 지점을 담아둔다.
        List stockMasterListResult = new ArrayList(); //지점별 품목별 재고마스터(안전재고,보유재고) 데이타를 담는다.
    	StockMasterVO stockGroupVO = null; //재고대상지점
    	StockMasterVO stockMasterResultVO = null; //재고마스터

        XSSFRow titleRow = sheet.getRow(TITLE_POINT);
        String[] groupInfoCodes=null;
        String groupInfoCode="";
        
        //엑셀 타이틀을 통해 대상지점 리스트를 담는로직
        for (int groupinfo=MASTER_START; groupinfo < TOTAL_CELLS; groupinfo++){
        	
        	stockGroupVO=new StockMasterVO();

        	titleRow.getCell(groupinfo).getStringCellValue();
        	groupInfoCodes=titleRow.getCell(groupinfo).getStringCellValue().split("_");//지점 타이틀을 코드 _ 지점명으로 구분하여 자른다.
        	groupInfoCode=groupInfoCodes[0];
        	stockGroupVO.setGroupId(groupInfoCode);
        	
        	stockGroupList.add(stockGroupVO);
        	this.logger.debug("담긴 조직아이디:" + stockGroupVO.getGroupId());
        	groupinfo++;//보유재고 거르기 
        }
          
        for (int i = DATA_START; i < sheet.getPhysicalNumberOfRows(); i++) {
          
          ProductMasterVO productMasterVO = new ProductMasterVO();
          XSSFRow row = sheet.getRow(i);
          
          int MASTER_RE_START=MASTER_START;//master cell 시작지점
          
          try {
        	    productMasterVO.setProductCode(row.getCell(2).getStringCellValue()); } catch (NullPointerException e) { productMasterVO.setProductCode(""); 
              } 

          try {
        	  
        	  for(int s=0; s<stockGroupList.size(); s++){
        		  
        		  stockMasterResultVO = new StockMasterVO();
        		  stockMasterResultVO.setProductCode(row.getCell(2).getStringCellValue());//품목코드
        		  
        		  stockGroupVO= (StockMasterVO)stockGroupList.get(s);
        		  
        		  stockMasterResultVO.setGroupId(stockGroupVO.getGroupId());//
        		  stockMasterResultVO.setSafeStock(String.valueOf(row.getCell(MASTER_RE_START++).getNumericCellValue()));//
        		  stockMasterResultVO.setHoldStock(String.valueOf(row.getCell(MASTER_RE_START++).getNumericCellValue()));//
        		  
        		  stockMasterListResult.add(stockMasterResultVO);

        	  }
          	
          }catch (NullPointerException e) {stockMasterListResult=null;
          }

        }
        
        for(int t=0 ; t<stockMasterListResult.size(); t++){//테스트 프린트
        	StockMasterVO stockMasterTestVO = new StockMasterVO();
        	stockMasterTestVO=(StockMasterVO)stockMasterListResult.get(t);
        	stockMasterTestVO.getProductCode();
        	this.logger.debug("STOCK 품목아이디:" + stockMasterTestVO.getProductCode());
        	this.logger.debug("STOCK 조직아이디:" + stockMasterTestVO.getGroupId());
        	this.logger.debug("STOCK 안젖재고값:" + stockMasterTestVO.getSafeStock());
        	this.logger.debug("STOCK 보유재고값:" + stockMasterTestVO.getHoldStock());
        }
   
      }

      mv.setViewName("/manage/uploadResultList");

      //log Controller execute time end
	  long t2 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	 	
      return mv;
    }
    /**
     * 재고현황 관리화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/stockmanage")
    public ModelAndView stockManage(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        StockVO stockConVO = new StockVO();
        
        stockConVO.setStart_stockDate(strToday);
        stockConVO.setEnd_stockDate(strToday);
        stockConVO.setGroupId(strGroupId);
       
        // 조회조건저장
        mv.addObject("stockConVO", stockConVO);
        
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
       
        mv.setViewName("/master/stockManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 재고현황 목록조회
     * 
     * @param StockVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/stockpagelist")
    public ModelAndView stockPageList(@ModelAttribute("stockConVO") StockVO stockConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : stockConVO" + stockConVO);

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        List<StockVO> stockList = null;

        // 조회조건저장
        mv.addObject("stockConVO", stockConVO);

        // 페이징코드
        stockConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(stockConVO.getCurPage(), stockConVO.getRowCount()));
        stockConVO.setPage_limit_val2(StringUtil.nvl(stockConVO.getRowCount(), "10"));
        
        // 재고현황목록조회
        stockList = stockSvc.getStockPageList(stockConVO);
        mv.addObject("stockList", stockList);

        // totalCount 조회
        String totalCount = String.valueOf(stockSvc.getStockCnt(stockConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/master/stockPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
       @RequestMapping(value = "/master/stockexcelform")
   	public ModelAndView stockExcelForm(HttpServletRequest request) throws BizException 
       {
   		
   		ModelAndView mv = new ModelAndView();
   		
   	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        StockVO stockConVO = new StockVO();
        
        stockConVO.setGroupId(strGroupId);
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        stockConVO.setStart_stockDate(strToday);

        // 조회조건저장
        mv.addObject("stockConVO", stockConVO);

        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
   		
   		mv.setViewName("/master/stockExcelForm");
   		
   		return mv;
   	}
   /**
    * 재고현황 일괄등록
    *
    * @param MultipartFileVO
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping({"/master/stockexcelimport"})
   public ModelAndView stockExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
   		                            HttpServletRequest request, 
   		                            HttpServletResponse response, 
   		                            String fileName, 
   		                            String extension, 
   		                            String upload_stockDate, 
   		                            String upload_groupId ) throws IOException, BizException
   {
     
     //log Controller execute time start
	 String logid=logid();
     long t1 = System.currentTimeMillis();
     logger.info("["+logid+"] Controller start : fileVO" + fileVO);
   			
     ModelAndView mv = new ModelAndView();
     
     String fname ="";
     
  // 사용자 세션정보
     HttpSession session = request.getSession();
     String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
     String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
     String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
     String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));

     if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
     	
     	strIp = request.getRemoteAddr(); //로그인 상태처리		
 		UserVO userState =new UserVO();
 		userState.setUserId(strUserId);
 		userState.setLoginYn("N");
 		userState.setIp(strIp);
 		userState.setConnectIp(sClientIP);
 		userSvc.regiLoginYnUpdate(userState);
         
         //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
 		
     	mv.setViewName("/addys/loginForm");
    		return mv;
		}
     
     String excelPath="excel/stock/"+upload_groupId+"/"+upload_stockDate+"/";

     ResourceBundle rb = ResourceBundle.getBundle("config");
     String uploadFilePath = rb.getString("offact.upload.path") + excelPath;
     
     this.logger.debug("파일정보:" + fileName + extension);
     this.logger.debug("file:" + fileVO);

     List excelUploadList = new ArrayList();//업로드 대상 데이타
     StockVO stockTotal = new StockVO();
     
     String stockDate=upload_stockDate;
     
     stockTotal.setStockDate(stockDate);
     stockTotal.setGroupId(upload_groupId);
     stockTotal.setLastUserId(strUserId);
     
     String excelInfo = "";//excel 추출데이타
     List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
     List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타
     
     String  errorMsgList ="";   

     if (fileName != null) {
   	  
       List<MultipartFile> files = fileVO.getFiles();
       List fileNames = new ArrayList();
       String orgFileName = null;

       if ((files != null) && (files.size() > 0))
       {
         for (MultipartFile multipartFile : files)
         {
           orgFileName = multipartFile.getOriginalFilename();
           
           this.logger.debug("orgFileName 1 :" + orgFileName);
           orgFileName = t1 +"."+ extension;
           this.logger.debug("orgFileName 2 :" + orgFileName);
        		   
           boolean check=setDirectory(uploadFilePath);
           
           String filePath = uploadFilePath;

           File file = new File(filePath + orgFileName);
           multipartFile.transferTo(file);
           fileNames.add(orgFileName);
         }
    
       }

       fname = uploadFilePath + orgFileName;

       FileInputStream fileInput = null;

       fileInput = new FileInputStream(fname);

       XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
       XSSFSheet sheet = workbook.getSheetAt(0);//첫번째 sheet
  
       int TITLE_POINT =1;//타이틀 항목위치
       int ROW_START = 2;//data row 시작지점
       
       int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
       int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
       
       XSSFCell myCell = null;
     
       this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
       this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);
           
           try {
 
	           for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS-2; rowcnt++) {
	             
	        	 StockVO stockVO = new StockVO();
	             XSSFRow row = sheet.getRow(rowcnt);

	             //cell type 구분하여 담기  
	             String[] cellItemTmp = new String[TOTAL_CELLS]; 
	             for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
		            myCell = row.getCell(cellcnt); 
		            
		            if(myCell!=null){
		            	
			            if(myCell.getCellType()==0){ //cell type 이 숫자인경우
	
			            	String rawCell = String.valueOf(myCell.getNumericCellValue()); 
			            	int endChoice = rawCell.lastIndexOf("E");
			            	if(endChoice>0){
			            		rawCell= rawCell.substring(0, endChoice);
			            		rawCell= rawCell.replace(".", "");
			            	}
			            	cellItemTmp[cellcnt]=rawCell;
			            	
			            }else if(myCell.getCellType()==1){ //cell type 이 일반/문자 인경우
			            	cellItemTmp[cellcnt] = myCell.getStringCellValue(); 
			            }else{//그외 cell type
			            	cellItemTmp[cellcnt] = ""; 
			            }
			            this.logger.debug("row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt]);
			            excelInfo="row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt];
		            }else{
		            	cellItemTmp[cellcnt] = "";
		            }
		         }
	         
		         if(cellItemTmp.length>0 && cellItemTmp[0] != ""){
		        	 
		        	 if(cellItemTmp.length>0){ stockVO.setProductCode(cellItemTmp[0]);}
		        	 if(cellItemTmp.length>3){ stockVO.setStockCnt(cellItemTmp[3]);}
		        	 if(cellItemTmp.length>4){ stockVO.setProductPrice(cellItemTmp[4]);}
		        	 if(cellItemTmp.length>5){ stockVO.setStockPrice(cellItemTmp[5]);}

		        	 stockVO.setLastUserId(strUserId);
		        	 stockVO.setStockDate(stockDate);
		        	 stockVO.setGroupId(upload_groupId);
			
			         excelUploadList.add(stockVO);
			      }
			     	
			    }
           }catch (Exception e){
  
	          excelInfo = excelInfo+"[error] : "+e.getMessage();
	          StockVO stockVO = new StockVO();
	          stockVO.setErrMsg(excelInfo);
    	 
	          this.logger.info("["+logid+"] Controller getErrMsg : "+stockVO.getErrMsg());
         
	          rtnErrorList.add(stockVO);
	          errorMsgList=errorMsgList+stockVO.getErrMsg()+"\\^";

	          mv.addObject("rtnErrorList", rtnErrorList);
	          mv.addObject("rtnSuccessList", rtnSuccessList);
	          
	          mv.addObject("errorMsgList", errorMsgList);

	          mv.setViewName("/master/uploadResult");
	          
		        //작업이력
		     	 WorkVO work = new WorkVO();
		     	 work.setWorkUserId(strUserId);
		     	 work.setWorkCategory("ST");
		     	 work.setWorkCode("ST002");
		     	 work.setWorkKey3(fname);
		     	 commonSvc.regiHistoryInsert(work);
    	 
	          //log Controller execute time end
	          long t2 = System.currentTimeMillis();
	          logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
  	 	
	          return mv;
    	   
       	}
     }
     
     //DB처리
     Map rtmMap = this.stockSvc.regiExcelUpload(excelUploadList,stockTotal);

     rtnErrorList = (List)rtmMap.get("rtnErrorList");
     rtnSuccessList = (List)rtmMap.get("rtnSuccessList");
     errorMsgList = (String)rtmMap.get("errorMsgList");

     this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
  
     mv.addObject("rtnErrorList", rtnErrorList);
     mv.addObject("rtnSuccessList", rtnSuccessList);
     
     mv.addObject("errorMsgList", errorMsgList);
       
     mv.setViewName("/master/uploadResult");
     
     //작업이력
	 WorkVO work = new WorkVO();
	 work.setWorkUserId(strUserId);
	 work.setWorkCategory("ST");
	 work.setWorkCode("ST001");
	 work.setWorkKey3(fname);
	 commonSvc.regiHistoryInsert(work);

     //log Controller execute time end
     long t2 = System.currentTimeMillis();
     logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 	
     return mv;
         
    }
   /**
    * 재고상세현황 관리화면
    *
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping(value = "/master/stockdetailmanage")
   public ModelAndView stockDetailManage(HttpServletRequest request, 
   		                                 HttpServletResponse response,
   		                                 String stockDate, 
  		                                 String groupId ) throws BizException 
   {
       
	   //log Controller execute time start
	   String logid=logid();
	   long t1 = System.currentTimeMillis();
	   logger.info("["+logid+"] Controller start ");
	
	   ModelAndView mv = new ModelAndView();
	   
	// 사용자 세션정보
       HttpSession session = request.getSession();
       String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
       String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
       String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
       String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
       
       if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
       	
       	strIp = request.getRemoteAddr(); //로그인 상태처리		
   		UserVO userState =new UserVO();
   		userState.setUserId(strUserId);
   		userState.setLoginYn("N");
   		userState.setIp(strIp);
   		userState.setConnectIp(sClientIP);
   		userSvc.regiLoginYnUpdate(userState);
           
           //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
   		
       	mv.setViewName("/addys/loginForm");
      		return mv;
		}
	   
	   StockVO stockDetailConVO = new StockVO();
	   
	   stockDetailConVO.setStockDate(stockDate);
	   stockDetailConVO.setGroupId(groupId);
	
	   // 조회조건저장
	   mv.addObject("stockDetailConVO", stockDetailConVO);
	  
	   mv.setViewName("/master/stockDetailManage");
	   
	  //log Controller execute time end
	  long t2 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	 	
	   return mv;
   }
   /**
    * 재고상세현황 목록조회
    * 
    * @param StockVO
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping(value = "/master/stockdetailpagelist")
   public ModelAndView stockDetailPageList(@ModelAttribute("stockDetailConVO") StockVO stockDetailConVO, 
   		                         HttpServletRequest request, 
   		                         HttpServletResponse response) throws BizException 
   {
       
   	//log Controller execute time start
	String logid=logid();
	long t1 = System.currentTimeMillis();
	logger.info("["+logid+"] Controller start : stockDetailConVO" + stockDetailConVO);

       ModelAndView mv = new ModelAndView();
       
    // 사용자 세션정보
       HttpSession session = request.getSession();
       String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
       String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
       String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
       String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
       
       if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
       	
       	strIp = request.getRemoteAddr(); //로그인 상태처리		
   		UserVO userState =new UserVO();
   		userState.setUserId(strUserId);
   		userState.setLoginYn("N");
   		userState.setIp(strIp);
   		userState.setConnectIp(sClientIP);
   		userSvc.regiLoginYnUpdate(userState);
           
           //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
   		
       	mv.setViewName("/addys/loginForm");
      		return mv;
		}
       
       List<StockVO> stockDetailList = null;

       // 조회조건저장
       mv.addObject("stockDetailConVO", stockDetailConVO);

       // 페이징코드
       stockDetailConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(stockDetailConVO.getCurPage(), stockDetailConVO.getRowCount()));
       stockDetailConVO.setPage_limit_val2(StringUtil.nvl(stockDetailConVO.getRowCount(), "10"));
       
       // 재고상세현황목록조회
       stockDetailList = stockSvc.getStockDetailPageList(stockDetailConVO);
       mv.addObject("stockDetailList", stockDetailList);

       // totalCount 조회
       String totalCount = String.valueOf(stockSvc.getStockDetailCnt(stockDetailConVO));
       mv.addObject("totalCount", totalCount);

       mv.setViewName("/master/stockDetailPageList");
       
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
       return mv;
   }
   /**
    * 매출현황 관리화면
    *
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping(value = "/master/salesmanage")
   public ModelAndView salesManage(HttpServletRequest request, 
   		                       HttpServletResponse response) throws BizException 
   {
       
   	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

       ModelAndView mv = new ModelAndView();
       
    // 사용자 세션정보
       HttpSession session = request.getSession();
       String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
       String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
       String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
       String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
       
       if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
       	
       	strIp = request.getRemoteAddr(); //로그인 상태처리		
   		UserVO userState =new UserVO();
   		userState.setUserId(strUserId);
   		userState.setLoginYn("N");
   		userState.setIp(strIp);
   		userState.setConnectIp(sClientIP);
   		userSvc.regiLoginYnUpdate(userState);
           
           //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
   		
       	mv.setViewName("/addys/loginForm");
      		return mv;
		}
       
       //오늘 날짜
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
       Date currentTime = new Date();
       String strToday = simpleDateFormat.format(currentTime);
       
       SalesVO salesConVO = new SalesVO();
       
       salesConVO.setStart_salesDate(strToday);
       salesConVO.setEnd_salesDate(strToday);
       salesConVO.setGroupId(strGroupId);

       // 조회조건저장
       mv.addObject("salesConVO", salesConVO);
       
       //조직정보 조회
       GroupVO group = new GroupVO();
       group.setGroupId(strGroupId);
       List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
       mv.addObject("group_comboList", group_comboList);
      
       mv.setViewName("/master/salesManage");
       
      //log Controller execute time end
     	long t2 = System.currentTimeMillis();
     	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
     	
       return mv;
   }
   /**
    * 매출현황 목록조회
    * 
    * @param SalesVO
    * @param request
    * @param response
    * @param model
    * @param locale
    * @return
    * @throws BizException
    */
   @RequestMapping(value = "/master/salespagelist")
   public ModelAndView salesPageList(@ModelAttribute("salesConVO") SalesVO salesConVO, 
   		                         HttpServletRequest request, 
   		                         HttpServletResponse response) throws BizException 
   {
       
   	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : salesConVO" + salesConVO);

       ModelAndView mv = new ModelAndView();
       
    // 사용자 세션정보
       HttpSession session = request.getSession();
       String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
       String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
       String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
       String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
       
       if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
       	
       	strIp = request.getRemoteAddr(); //로그인 상태처리		
   		UserVO userState =new UserVO();
   		userState.setUserId(strUserId);
   		userState.setLoginYn("N");
   		userState.setIp(strIp);
   		userState.setConnectIp(sClientIP);
   		userSvc.regiLoginYnUpdate(userState);
           
           //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
   		
       	mv.setViewName("/addys/loginForm");
      		return mv;
		}
       
       List<SalesVO> salesList = null;

       // 조회조건저장
       mv.addObject("salesConVO", salesConVO);

       // 페이징코드
       salesConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(salesConVO.getCurPage(), salesConVO.getRowCount()));
       salesConVO.setPage_limit_val2(StringUtil.nvl(salesConVO.getRowCount(), "10"));
       
       // 재고현황목록조회
       salesList = salesSvc.getSalesPageList(salesConVO);
       mv.addObject("salesList", salesList);

       // totalCount 조회
       String totalCount = String.valueOf(salesSvc.getSalesCnt(salesConVO));
       mv.addObject("totalCount", totalCount);

       mv.setViewName("/master/salesPageList");
       
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
       return mv;
   }
   /**
  	 * Simply selects the home view to render by returning its name.
  	 * @throws BizException
  	 */
      @RequestMapping(value = "/master/salesexcelform")
  	public ModelAndView salesExcelForm(HttpServletRequest request) throws BizException 
      {
  		
  		ModelAndView mv = new ModelAndView();
  		
  	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        SalesVO salesConVO = new SalesVO();
        
        salesConVO.setGroupId(strGroupId);
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        salesConVO.setStart_salesDate(strToday);

        // 조회조건저장
        mv.addObject("salesConVO", salesConVO);

        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
  		
  		mv.setViewName("/master/salesExcelForm");
  		
  		return mv;
  	}
  /**
   * 매출현황 일괄등록
   *
   * @param MultipartFileVO
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping({"/master/salesexcelimport"})
  public ModelAndView salesExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
  		                            HttpServletRequest request, 
  		                            HttpServletResponse response, 
  		                            String fileName, 
  		                            String extension, 
  		                            String upload_salesDate, 
  		                            String upload_groupId ) throws IOException, BizException
  {
    
    //log Controller execute time start
    String logid=logid();
    long t1 = System.currentTimeMillis();
    logger.info("["+logid+"] Controller start : fileVO" + fileVO);
  			
    ModelAndView mv = new ModelAndView();

    String fname ="";

 // 사용자 세션정보
    HttpSession session = request.getSession();
    String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
    String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
    String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
    String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
    
    //오늘 날짜
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
    Date currentTime = new Date();
    String strToday = simpleDateFormat.format(currentTime);
    
    if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
    	
    	strIp = request.getRemoteAddr(); //로그인 상태처리		
		UserVO userState =new UserVO();
		userState.setUserId(strUserId);
		userState.setLoginYn("N");
		userState.setIp(strIp);
		userState.setConnectIp(sClientIP);
		userSvc.regiLoginYnUpdate(userState);
        
        //작업이력
 		WorkVO work = new WorkVO();
 		work.setWorkUserId(strUserId);
 		work.setWorkIp(strIp);
 		work.setWorkCategory("CM");
 		work.setWorkCode("CM004");
 		commonSvc.regiHistoryInsert(work);
		
    	mv.setViewName("/addys/loginForm");
   		return mv;
	}

    String excelPath="excel/sales/"+upload_groupId+"/"+upload_salesDate+"/";

    ResourceBundle rb = ResourceBundle.getBundle("config");
    String uploadFilePath = rb.getString("offact.upload.path") + excelPath;

    this.logger.debug("파일정보:" + fileName + extension);
    this.logger.debug("file:" + fileVO);

    List excelUploadList = new ArrayList();//업로드 대상 데이타
    SalesVO salesTotal = new SalesVO();
    
    String salesDate=upload_salesDate;
    
    salesTotal.setSalesDate(salesDate);
    salesTotal.setGroupId(upload_groupId);
    salesTotal.setUpdateUserId(strUserId);
    
    String excelInfo = "";//excel 추출데이타
    List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
    List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타
    
    String errorMsgList ="";

    if (fileName != null) {
  	  
      List<MultipartFile> files = fileVO.getFiles();
      List fileNames = new ArrayList();
      String orgFileName = null;

      if ((files != null) && (files.size() > 0))
      {
        for (MultipartFile multipartFile : files)
        {
          orgFileName = multipartFile.getOriginalFilename();
          this.logger.debug("orgFileName 1 :" + orgFileName);
          orgFileName = t1 +"."+ extension;
          this.logger.debug("orgFileName 2 :" + orgFileName);
       		   
          boolean check=setDirectory(uploadFilePath);

          String filePath = uploadFilePath;

          File file = new File(filePath + orgFileName);
          multipartFile.transferTo(file);
          fileNames.add(orgFileName);
        }
   
      }

      fname = uploadFilePath + orgFileName;

      FileInputStream fileInput = null;

      fileInput = new FileInputStream(fname);

      XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
      XSSFSheet sheet = workbook.getSheetAt(0);//첫번째 sheet
 
      int TITLE_POINT =1;//타이틀 항목위치
      int ROW_START = 2;//data row 시작지점
      
      int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
      int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
      
      XSSFCell myCell = null;
    
      this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
      this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);
          
          try {

           for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS-3; rowcnt++) {
             
        	 SalesVO salesVO = new SalesVO();
             XSSFRow row = sheet.getRow(rowcnt);

             //cell type 구분하여 담기  
             String[] cellItemTmp = new String[TOTAL_CELLS]; 
             for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
	            myCell = row.getCell(cellcnt); 
	            
	            if(myCell!=null){
	          
		            if(myCell.getCellType()==0){ //cell type 이 숫자인경우
	
		            	String rawCell = String.valueOf(myCell.getNumericCellValue()); 
		            	int endChoice = rawCell.lastIndexOf("E");
		            	if(endChoice>0){
		            		rawCell= rawCell.substring(0, endChoice);
		            		rawCell= rawCell.replace(".", "");
		            	}
		            	cellItemTmp[cellcnt]=rawCell;
	    	
		            }else if(myCell.getCellType()==1){ //cell type 이 일반/문자 인경우
		            	cellItemTmp[cellcnt] = myCell.getStringCellValue(); 
		            }else{//그외 cell type
		            	cellItemTmp[cellcnt] = ""; 
		            }
		            this.logger.debug("row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt]);
		            excelInfo="row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt];
	            }else{
	            	cellItemTmp[cellcnt] = ""; 
	            }
	         }
         
	         if(cellItemTmp.length>0 && cellItemTmp[0] != ""){
	        	 
	        	 if(cellItemTmp.length>1){ salesVO.setProductCode(cellItemTmp[1]);}
	        	 if(cellItemTmp.length>4){ salesVO.setSalesCnt(cellItemTmp[4]);}
	        	 if(cellItemTmp.length>5){ salesVO.setProductPrice(cellItemTmp[5]);}
	        	 if(cellItemTmp.length>6){ salesVO.setSupplyPrice(cellItemTmp[6]);}
	        	 if(cellItemTmp.length>7){ salesVO.setVat(cellItemTmp[7]);}
	        	 if(cellItemTmp.length>8){ salesVO.setSalesPrice(cellItemTmp[8]);}
	        	 
	        	 salesVO.setUpdateUserId(strUserId);
	        	 salesVO.setSalesDate(salesDate);
	        	 salesVO.setGroupId(upload_groupId);
		
		         excelUploadList.add(salesVO);

		      }
		     	
		    }
          }catch (Exception e){
 
          excelInfo = excelInfo+"[error] : "+e.getMessage();
          SalesVO salesVO = new SalesVO();
          salesVO.setErrMsg(excelInfo);
   	 
          this.logger.info("["+logid+"] Controller getErrMsg : "+salesVO.getErrMsg());
        
          rtnErrorList.add(salesVO);
          errorMsgList=errorMsgList+salesVO.getErrMsg()+"\\^";

          mv.addObject("rtnErrorList", rtnErrorList);
          mv.addObject("rtnSuccessList", rtnSuccessList);
          
          mv.addObject("errorMsgList", errorMsgList);
          
          mv.setViewName("/master/uploadResult");
          
          //작업이력
     	  WorkVO work = new WorkVO();
     	  work.setWorkUserId(strUserId);
     	  work.setWorkCategory("SA");
     	  work.setWorkCode("SA002");
     	  work.setWorkKey3(fname);
     	  commonSvc.regiHistoryInsert(work);
   	 
          //log Controller execute time end
          long t2 = System.currentTimeMillis();
          logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 	 	
          return mv;
   	   
      	}
    }
    
    //DB처리
    Map rtmMap = this.salesSvc.regiExcelUpload(excelUploadList,salesTotal);

    rtnErrorList = (List)rtmMap.get("rtnErrorList");
    rtnSuccessList = (List)rtmMap.get("rtnSuccessList");
    
    errorMsgList = (String)rtmMap.get("errorMsgList");

    this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
 
    mv.addObject("rtnErrorList", rtnErrorList);
    mv.addObject("rtnSuccessList", rtnSuccessList);
    
    mv.addObject("errorMsgList", errorMsgList);
      
    mv.setViewName("/master/uploadResult");
    
    //작업이력
	  WorkVO work = new WorkVO();
	  work.setWorkUserId(strUserId);
	  work.setWorkCategory("SA");
	  work.setWorkCode("SA001");
	  work.setWorkKey3(fname);
	  commonSvc.regiHistoryInsert(work);

    //log Controller execute time end
    long t2 = System.currentTimeMillis();
    logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	
    return mv;
        
   }
  /**
   * 매출상세현황 관리화면
   *
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/salesdetailmanage")
  public ModelAndView salesDetailManage(HttpServletRequest request, 
  		                                 HttpServletResponse response,
  		                                 String salesDate, 
 		                                 String groupId ) throws BizException 
  {
      
	   //log Controller execute time start
	   String logid=logid();
	   long t1 = System.currentTimeMillis();
	   logger.info("["+logid+"] Controller start ");
	
	   ModelAndView mv = new ModelAndView();
	   
	// 사용자 세션정보
       HttpSession session = request.getSession();
       String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
       String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
       String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
       String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
       
       if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
       	
       	strIp = request.getRemoteAddr(); //로그인 상태처리		
   		UserVO userState =new UserVO();
   		userState.setUserId(strUserId);
   		userState.setLoginYn("N");
   		userState.setIp(strIp);
   		userState.setConnectIp(sClientIP);
   		userSvc.regiLoginYnUpdate(userState);
           
           //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
   		
       	mv.setViewName("/addys/loginForm");
      		return mv;
		}
	   
	   SalesVO salesDetailConVO = new SalesVO();
	   
	   salesDetailConVO.setSalesDate(salesDate);
	   salesDetailConVO.setGroupId(groupId);
	
	   // 조회조건저장
	   mv.addObject("salesDetailConVO", salesDetailConVO);
	  
	   mv.setViewName("/master/salesDetailManage");
	   
	  //log Controller execute time end
	  long t2 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	 	
	   return mv;
  }
  /**
   * 매출상세현황 목록조회
   * 
   * @param StockVO
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/salesdetailpagelist")
  public ModelAndView stockDetailPageList(@ModelAttribute("salesDetailConVO") SalesVO salesDetailConVO, 
  		                         HttpServletRequest request, 
  		                         HttpServletResponse response) throws BizException 
  {
      
  	//log Controller execute time start
	String logid=logid();
	long t1 = System.currentTimeMillis();
	logger.info("["+logid+"] Controller start : salesDetailConVO" + salesDetailConVO);

      ModelAndView mv = new ModelAndView();
      
   // 사용자 세션정보
      HttpSession session = request.getSession();
      String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
      String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
      String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
      String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
      
      if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
      	
      	strIp = request.getRemoteAddr(); //로그인 상태처리		
  		UserVO userState =new UserVO();
  		userState.setUserId(strUserId);
  		userState.setLoginYn("N");
  		userState.setIp(strIp);
  		userState.setConnectIp(sClientIP);
  		userSvc.regiLoginYnUpdate(userState);
          
          //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
  		
      	mv.setViewName("/addys/loginForm");
     		return mv;
		}
      
      List<SalesVO> salesDetailList = null;

      // 조회조건저장
      mv.addObject("salesDetailConVO", salesDetailConVO);

      // 페이징코드
      salesDetailConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(salesDetailConVO.getCurPage(), salesDetailConVO.getRowCount()));
      salesDetailConVO.setPage_limit_val2(StringUtil.nvl(salesDetailConVO.getRowCount(), "10"));
      
      // 재고상세현황목록조회
      salesDetailList = salesSvc.getSalesDetailPageList(salesDetailConVO);
      mv.addObject("salesDetailList", salesDetailList);

      // totalCount 조회
      String totalCount = String.valueOf(salesSvc.getSalesDetailCnt(salesDetailConVO));
      
      mv.addObject("totalCount", totalCount);

      mv.setViewName("/master/salesDetailPageList");
      
      //log Controller execute time end
     	long t2 = System.currentTimeMillis();
     	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
     	
      return mv;
  }
  /**
   * 품목조회화면
   *
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/productsearch")
  public ModelAndView productSearch(HttpServletRequest request, 
  		                       HttpServletResponse response) throws BizException 
  {
      
  	//log Controller execute time start
	String logid=logid();
	long t1 = System.currentTimeMillis();
	logger.info("["+logid+"] Controller start ");

    ModelAndView mv = new ModelAndView();
      
 // 사용자 세션정보
    HttpSession session = request.getSession();
    String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
    String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
    String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
    String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
    
    if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
    	
    	strIp = request.getRemoteAddr(); //로그인 상태처리		
		UserVO userState =new UserVO();
		userState.setUserId(strUserId);
		userState.setLoginYn("N");
		userState.setIp(strIp);
		userState.setConnectIp(sClientIP);
		userSvc.regiLoginYnUpdate(userState);
        
        //작업이력
 		WorkVO work = new WorkVO();
 		work.setWorkUserId(strUserId);
 		work.setWorkIp(strIp);
 		work.setWorkCategory("CM");
 		work.setWorkCode("CM004");
 		commonSvc.regiHistoryInsert(work);
		
    	mv.setViewName("/addys/loginForm");
   		return mv;
	}

    mv.setViewName("/common/productSearch");
      
     //log Controller execute time end
    long t2 = System.currentTimeMillis();
    logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
    	
    return mv;
  }
  /**
   * 품목 검색목록조회
   * 
   * @param UserManageVO
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/productsearchlist")
  public ModelAndView productSearchList(@ModelAttribute("productConVO") ProductMasterVO productConVO, 
  		                         HttpServletRequest request, 
  		                         HttpServletResponse response) throws BizException 
  {
      
  	//log Controller execute time start
	  	String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : productConVO" + productConVO);

      ModelAndView mv = new ModelAndView();
      
   // 사용자 세션정보
      HttpSession session = request.getSession();
      String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
      String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
      String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
      String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
      
      if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
      	
      	strIp = request.getRemoteAddr(); //로그인 상태처리		
  		UserVO userState =new UserVO();
  		userState.setUserId(strUserId);
  		userState.setLoginYn("N");
  		userState.setIp(strIp);
  		userState.setConnectIp(sClientIP);
  		userSvc.regiLoginYnUpdate(userState);
          
          //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
  		
      	mv.setViewName("/addys/loginForm");
     		return mv;
		}
      
      List<ProductMasterVO> productList = null;

      // 조회조건 null 일때 공백처리
      if (productConVO.getSearchGubun() == null) {
      	productConVO.setSearchGubun("01");
      }
      
      // 조회값 null 일때 공백처리
      if (productConVO.getSearchValue() == null) {
      	productConVO.setSearchValue("");
      }
      
      // 조회조건저장
      mv.addObject("productCon", productConVO);

      // 페이징코드
      productConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(productConVO.getCurPage(), productConVO.getRowCount()));
      productConVO.setPage_limit_val2(StringUtil.nvl(productConVO.getRowCount(), "10"));
      
      // 사용자목록조회
      productList = productMasterSvc.getProductList(productConVO);
      mv.addObject("productList", productList);

      // totalCount 조회
      String totalCount = String.valueOf(productMasterSvc.getProductCnt(productConVO));
      mv.addObject("totalCount", totalCount);

      mv.setViewName("/common/productSearchList");
      
      //log Controller execute time end
     	long t2 = System.currentTimeMillis();
     	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
     	
      return mv;
  }
  
  /**
   * 발주제한 관리화면
   *
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/orderlimitmanage")
  public ModelAndView orderLimitManage(HttpServletRequest request, 
  		                       HttpServletResponse response) throws BizException 
  {
      
  	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

      ModelAndView mv = new ModelAndView();
      
   // 사용자 세션정보
      HttpSession session = request.getSession();
      String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
      String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
      String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
      String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
      
      if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
      	
      	strIp = request.getRemoteAddr(); //로그인 상태처리		
  		UserVO userState =new UserVO();
  		userState.setUserId(strUserId);
  		userState.setLoginYn("N");
  		userState.setIp(strIp);
  		userState.setConnectIp(sClientIP);
  		userSvc.regiLoginYnUpdate(userState);
          
          //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
  		
      	mv.setViewName("/addys/loginForm");
     		return mv;
		}
      
      OrderLimitVO orderLimitConVO = new OrderLimitVO();
      
      orderLimitConVO.setGroupId(strGroupId);

      //오늘 날짜
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
      Date currentTime = new Date();
      Date deliveryTime = new Date();
      int movedate=-7;//(1:내일 ,-1:어제)
      
      deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
      
      String strToday = simpleDateFormat.format(currentTime);
      String strDeliveryDay = simpleDateFormat.format(deliveryTime);
      
      orderLimitConVO.setStart_limitDate(strDeliveryDay);
      orderLimitConVO.setEnd_limitDate(strToday);
      
      // 조회조건저장
      mv.addObject("orderLimitConVO", orderLimitConVO);

      //조직정보 조회
      GroupVO group = new GroupVO();
      group.setGroupId(strGroupId);
      List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
      mv.addObject("group_comboList", group_comboList);
     
      mv.setViewName("/master/orderLimitManage");
      
     //log Controller execute time end
    	long t2 = System.currentTimeMillis();
    	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
    	
      return mv;
  }
  /**
   * 발주제한 목록조회
   * 
   * @param RecoveryVO
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/orderlimitpagelist")
  public ModelAndView orderLimitPageList(@ModelAttribute("orderlimitConVO") OrderLimitVO orderlimitConVO, 
  		                         HttpServletRequest request, 
  		                         HttpServletResponse response) throws BizException 
  {
      
  	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderlimitConVO" + orderlimitConVO);

      ModelAndView mv = new ModelAndView();
      List<OrderLimitVO> orderLimitList = null;

      // 조회조건저장
      mv.addObject("orderlimitConVO", orderlimitConVO);

      // 페이징코드
      orderlimitConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(orderlimitConVO.getCurPage(), orderlimitConVO.getRowCount()));
      orderlimitConVO.setPage_limit_val2(StringUtil.nvl(orderlimitConVO.getRowCount(), "10"));
      
      // 발주제한목록조회
      orderLimitList = orderLimitSvc.getOrderLimitPageList(orderlimitConVO);
      mv.addObject("orderLimitList", orderLimitList);

     //  totalCount 조회
      String totalCount = String.valueOf(orderLimitSvc.getOrderLimitCnt(orderlimitConVO));
      mv.addObject("totalCount", totalCount);

      mv.setViewName("/master/orderLimitPageList");
      
      //log Controller execute time end
     	long t2 = System.currentTimeMillis();
     	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
     	
      return mv;
  }
  
  /**
   * 발주대상 등록 화면
   *
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/orderlimitregistform")
  public ModelAndView orderLimitRegistForm(HttpServletRequest request, 
  		                               HttpServletResponse response) throws BizException 
  {
      
  	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

      ModelAndView mv = new ModelAndView();
      
   // 사용자 세션정보
      HttpSession session = request.getSession();
      String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
      String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
      String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
      String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
      
      if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
      	
      	strIp = request.getRemoteAddr(); //로그인 상태처리		
  		UserVO userState =new UserVO();
  		userState.setUserId(strUserId);
  		userState.setLoginYn("N");
  		userState.setIp(strIp);
  		userState.setConnectIp(sClientIP);
  		userSvc.regiLoginYnUpdate(userState);
          
          //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
  		
      	mv.setViewName("/addys/loginForm");
     		return mv;
		}
     
      //오늘 날짜
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
      Date currentTime = new Date();
      Date deliveryTime = new Date();
      int movedate=0;//(1:내일 ,-1:어제)
      
      deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
      
      String strToday = simpleDateFormat.format(currentTime);
      String strDeliveryDay = simpleDateFormat.format(deliveryTime);
      
      //제한일자 세팅
      mv.addObject("limitStartDate", strToday);
      mv.addObject("limitEndDate", strToday);
      
      //조직정보 조회
      GroupVO group = new GroupVO();
      group.setGroupId("G00000");
      List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
      mv.addObject("group_comboList", group_comboList);
      
      mv.setViewName("/master/orderLimitRegistForm");
      
     //log Controller execute time end
    	long t2 = System.currentTimeMillis();
    	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
    	
      return mv;
  }
  /**
 	 * Simply selects the home view to render by returning its name.
 	 * @throws BizException
 	 */
  @RequestMapping(value = "/master/orderlimitregislist")
 	public ModelAndView orderLimitRegisList(HttpServletRequest request) throws BizException 
     {
  		//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : reproductList");
  			
 		ModelAndView mv = new ModelAndView();
 		
 	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
      
      	List companyList = new ArrayList(); //DB 성공 대상데이타
      
      	mv.addObject("excelTotal", "0");
	  	mv.addObject("companyList", companyList);
 		
	  	mv.setViewName("/master/orderLimitAttach");
 		
 	    //log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 		
 		return mv;
 	}
  /**
 	 * Simply selects the home view to render by returning its name.
 	 * @throws BizException
 	 */
  @RequestMapping(value = "/master/orderlimitexcelform")
 	public ModelAndView orderLimitExcelForm(HttpServletRequest request) throws BizException 
     {
  	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : limitcompanyexcelform");
  			
 		ModelAndView mv = new ModelAndView();
 		
 		mv.setViewName("/master/orderLimitExcelForm");
 		
 	    //log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 		
 		return mv;
 	}
  /**
	   * 제한업체 일괄등록
	   *
	   * @param MultipartFileVO
	   * @param request
	   * @param response
	   * @param model
	   * @param locale
	   * @return
	   * @throws BizException
	   */
	  @RequestMapping({"/master/orderlimitattach"})
	  public ModelAndView orderLimitAttach(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
	  		                            HttpServletRequest request, 
	  		                            HttpServletResponse response, 
	  		                            String fileName, 
	  		                            String extension) throws IOException, BizException
	  {
	    
		  //log Controller execute time start
		    String logid=logid();
		    long t1 = System.currentTimeMillis();
		    logger.info("["+logid+"] Controller start : fileVO" + fileVO);
		  			
		    ModelAndView mv = new ModelAndView();

		    HttpSession session = request.getSession();
		    String strUserId = (String)session.getAttribute("strUserId");

		    ResourceBundle rb = ResourceBundle.getBundle("config");
		    String uploadFilePath = rb.getString("offact.upload.path") + "excel/";
		    
		    this.logger.debug("파일정보:" + fileName + extension);
		    this.logger.debug("file:" + fileVO);

		    List excelUploadList = new ArrayList();//업로드 대상 데이타

		    String excelInfo = "";//excel 추출데이타
		    
		    List companyList = new ArrayList(); //DB 성공 대상데이타

		    if (fileName != null) {
		  	  
		      List<MultipartFile> files = fileVO.getFiles();
		      List fileNames = new ArrayList();
		      String orgFileName = null;

		      if ((files != null) && (files.size() > 0))
		      {
		        for (MultipartFile multipartFile : files)
		        {
		          orgFileName = multipartFile.getOriginalFilename();
		          String filePath = uploadFilePath;

		          File file = new File(filePath + orgFileName);
		          multipartFile.transferTo(file);
		          fileNames.add(orgFileName);
		        }
		   
		      }

		      String fname = uploadFilePath + orgFileName;

		      FileInputStream fileInput = null;

		      fileInput = new FileInputStream(fname);

		      XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		      XSSFSheet sheet = workbook.getSheetAt(0);//첫번째 sheet
		 
		      int TITLE_POINT =0;//타이틀 항목위치
		      int ROW_START = 1;//data row 시작지점
		      
		      int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
		      int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
		      
		      XSSFCell myCell = null;
		    
		      this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
		      this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);
		          
		          try {

		           for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS; rowcnt++) {
		             
		   		     CompanyVO companyVO = new CompanyVO();
		   			
		             XSSFRow row = sheet.getRow(rowcnt);

		             //cell type 구분하여 담기  
		             String[] cellItemTmp = new String[TOTAL_CELLS]; 
		             for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
			            myCell = row.getCell(cellcnt); 
			            
			            if(myCell!=null){
			            	
				            if(myCell.getCellType()==0){ //cell type 이 숫자인경우
	
				            	String rawCell = String.valueOf(myCell.getNumericCellValue()); 
				            	int endChoice = rawCell.lastIndexOf("E");
				            	if(endChoice>0){
				            		rawCell= rawCell.substring(0, endChoice);
				            		rawCell= rawCell.replace(".", "");
				            	}
				            	cellItemTmp[cellcnt]=rawCell;
			    	
				            }else if(myCell.getCellType()==1){ //cell type 이 일반/문자 인경우
				            	cellItemTmp[cellcnt] = myCell.getStringCellValue(); 
				            }else{//그외 cell type
				            	cellItemTmp[cellcnt] = ""; 
				            }
				            this.logger.debug("row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt]);
				            excelInfo="row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt];
			            }else{
			            	
			            	cellItemTmp[cellcnt]="";
			            	
			            }
			         }
		         
			         if(cellItemTmp.length>0 && cellItemTmp[0] != ""){
			        	 
			        	 companyVO.setCompanyCode(cellItemTmp[0]); 
				
				         excelUploadList.add(companyVO);

				      }
				     	
				    }
		          }catch (Exception e){
		 
		          excelInfo = excelInfo+"[error] : "+e.getMessage();
		          CompanyVO companyVO = new CompanyVO();
		          //limitCompanyVO.setErrMsg(excelInfo);
		   	 
		          //this.logger.info("["+logid+"] Controller getErrMsg : "+productMasterVO.getErrMsg());
		        
		          companyList.add(companyVO);

		          mv.addObject("companyList", companyList);

		          mv.setViewName("/master/uploadResult");
		   	 
		          //log Controller execute time end
		          long t2 = System.currentTimeMillis();
		          logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		 	 	
		          return mv;
		   	   
		      	}
		    }
		    
		    //DB처리
		    companyList = this.orderLimitSvc.getExcelAttach(excelUploadList);

		    mv.addObject("excelTotal", excelUploadList.size());
		    mv.addObject("companyList", companyList);
		    
		    mv.setViewName("/master/orderLimitAttach");

		    //log Controller execute time end
		    long t2 = System.currentTimeMillis();
		    logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			
		    return mv;
		        
	  }
	  
  /**
   * 발주대상 등록 처리
   *
   * @param TargetVO
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping({"/master/orderlimtregist"})
  public @ResponseBody
  String orderLimitRegist(@ModelAttribute("orderLimitVO") OrderLimitVO orderLimitVO,
  		              @RequestParam(value="arrCheckGroupId", required=false, defaultValue="") String arrCheckGroupId,
  		              @RequestParam(value="arrSelectCompanyCode", required=false, defaultValue="") String arrSelectCompanyCode,
  		              HttpServletRequest request) throws BizException
  {
    
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderLimitVO" + orderLimitVO);
			
	  String limitResult="orderlimit0000";
		
		// 사용자 세션정보
      HttpSession session = request.getSession();
      String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
      
      logger.info("@#@#@# arrCheckGroupId: " + arrCheckGroupId);
      logger.info("@#@#@# arrSelectCompanyCode : " + arrSelectCompanyCode);
	    
      //오늘 날짜
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
      Date currentTime = new Date();
      String strToday = simpleDateFormat.format(currentTime);
      
      orderLimitVO.setLimitUserId(strUserId);
      	   
      try{//01.발주제한처리
     
          int dbResult=orderLimitSvc.regiOrderLimitRegist(orderLimitVO , arrCheckGroupId ,arrSelectCompanyCode);
           
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "orderlimit0001";
		        
		    }

	
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "orderlimit0002\n[errorMsg] : "+errMsg;
	    	
	    }

	      //작업이력
	 	 WorkVO work = new WorkVO();
	 	 work.setWorkUserId(strUserId);
	 	 work.setWorkCategory("LM");
	 	 work.setWorkCode("LM001");
	 	 commonSvc.regiHistoryInsert(work);
 	 
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

  return limitResult;
  }
  /**
   * 품목 재고수량 수정처리
   *
   * @param stockVO
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/stockcntmodify", method = RequestMethod.POST)
  public @ResponseBody
  String stockCntModify(@ModelAttribute("stockVO") StockMasterVO stockVO, 
  		          HttpServletRequest request, 
  		          HttpServletResponse response) throws BizException
  {
  	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : stockVO" + stockVO);
		
		// 사용자 세션정보
	    HttpSession session = request.getSession();
	    String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
	      
	    stockVO.setUpdateUserId(strUserId);

		int retVal=this.stockMasterSvc.stockCntUpdateProc(stockVO);
		
	     //작업이력
		 WorkVO work = new WorkVO();
		 work.setWorkUserId(strUserId);
		 work.setWorkCategory("PD");
		 work.setWorkCode("PD004");
		 commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
     	long t2 = System.currentTimeMillis();
     	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return ""+retVal;
  } 
  
  /**
   * 발주제한 해제처리
   *
   * @param 
   * @param request
   * @param response
   * @param model
   * @param locale
   * @return
   * @throws BizException
   */
  @RequestMapping(value = "/master/limitcancel", method = RequestMethod.POST)
  public @ResponseBody
  String limitCancel(@ModelAttribute("orderLimitVO") OrderLimitVO orderLimitVO, 
  		          HttpServletRequest request, 
  		          HttpServletResponse response) throws BizException
  {
  	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : orderLimitVO" + orderLimitVO);
		
		// 사용자 세션정보
	    HttpSession session = request.getSession();
	    String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
	      
	    orderLimitVO.setDeletedUserId(strUserId);

		int retVal=this.orderLimitSvc.orderLimitCance(orderLimitVO);
		
	     //작업이력
		 WorkVO work = new WorkVO();
		 work.setWorkUserId(strUserId);
		 work.setWorkCategory("LM");
		 work.setWorkCode("LM002");
		 commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
     	long t2 = System.currentTimeMillis();
     	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return ""+retVal;
  } 
  
  /**
 	 * Simply selects the home view to render by returning its name.
 	 * @throws BizException
 	 */
  @RequestMapping(value = "/master/orderlimitalllist")
 	public ModelAndView orderLimitAllList(HttpServletRequest request) throws BizException 
     {
  		//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : reproductList");
  			
 		ModelAndView mv = new ModelAndView();
 		
 	// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
      
      	List companyList = new ArrayList(); //DB 성공 대상데이타
      	CompanyVO companyConVo = new CompanyVO();
      	
      	companyList=commonSvc.getCompanyList(companyConVo);
      
      	mv.addObject("excelTotal", "0");
	  	mv.addObject("companyList", companyList);
 		
	  	mv.setViewName("/master/orderLimitAttach");
 		
 	    //log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 		
 		return mv;
 	}
  
  /**
	 * 업로드 디렉토리 세팅
	 */
	private static boolean setDirectory( String directory) {
		File wantedDirectory = new File(directory);
		if (wantedDirectory.isDirectory())
			return true;
	    
		return wantedDirectory.mkdirs();
	}
}
