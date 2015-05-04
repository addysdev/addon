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
import com.offact.addys.service.UserMenuService;
import com.offact.addys.service.manage.UserManageService;
import com.offact.addys.service.master.ProductMasterService;
import com.offact.addys.service.master.StockMasterService;
import com.offact.addys.service.master.StockService;
import com.offact.addys.vo.UserMenuVO;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.UserConditionVO;
import com.offact.addys.vo.manage.UserManageVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.master.ProductMasterVO;
import com.offact.addys.vo.master.StockVO;
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
    private UserManageService userManageSvc;
    
    @Autowired
    private ProductMasterService productMasterSvc;
    
    @Autowired
    private StockMasterService stockMasterSvc;
    
    @Autowired
    private StockService stockSvc;
    
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
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        UserManageVO userConVO = new UserManageVO();
        
        userConVO.setUserId(userId);
        userConVO.setGroupId(groupId);

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

     HttpSession session = request.getSession();
     String strUserId = (String)session.getAttribute("strUserId");

     ResourceBundle rb = ResourceBundle.getBundle("config");
     String uploadFilePath = rb.getString("offact.upload.path") + "excel/";
     
     this.logger.debug("파일정보:" + fileName + extension);
     this.logger.debug("file:" + fileVO);

     List excelUploadList = new ArrayList();//업로드 대상 데이타
     
     String excelInfo = "";//excel 추출데이타
     List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
     List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타

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
	             
	             ProductMasterVO productMasterVO = new ProductMasterVO();
	             XSSFRow row = sheet.getRow(rowcnt);

	             //cell type 구분하여 담기  
             String[] cellItemTmp = new String[TOTAL_CELLS]; 
	         for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
	            myCell = row.getCell(cellcnt); 
	            if(myCell.getCellType()==0){ //cell type 이 숫자인경우
	            	cellItemTmp[cellcnt] = String.valueOf(myCell.getNumericCellValue()); 
	            }else if(myCell.getCellType()==1){ //cell type 이 일반/문자 인경우
	            	cellItemTmp[cellcnt] = myCell.getStringCellValue(); 
	            }else{//그외 cell type
	            	cellItemTmp[cellcnt] = ""; 
	            }
	            this.logger.debug("row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt]);
	            excelInfo="row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt];
	         }
	         
	         if(cellItemTmp[0] != ""){
	        	 
	        	 productMasterVO.setProductCode(cellItemTmp[0]); 
            	 productMasterVO.setBarCode(cellItemTmp[1]);
            	 productMasterVO.setProductName(cellItemTmp[2]);
            	 productMasterVO.setProductPrice(cellItemTmp[3]); 
            	 productMasterVO.setVatRate(cellItemTmp[4]); 
            	 productMasterVO.setCompanyCode(cellItemTmp[5]); 
            	 productMasterVO.setGroup1(cellItemTmp[6]); 
            	 productMasterVO.setGroup1Name(cellItemTmp[7]);
            	 productMasterVO.setGroup2(cellItemTmp[8]);
            	 productMasterVO.setGroup2Name(cellItemTmp[9]); 
            	 productMasterVO.setGroup3(cellItemTmp[10]);
            	 productMasterVO.setGroup3Name(cellItemTmp[11]); 

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

     this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
  
     mv.addObject("rtnErrorList", rtnErrorList);
     mv.addObject("rtnSuccessList", rtnSuccessList);
       
     mv.setViewName("/master/uploadResult");

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
	
	    HttpSession session = request.getSession();
	    String strUserId = (String)session.getAttribute("strUserId");
	
	    ResourceBundle rb = ResourceBundle.getBundle("config");
	    String uploadFilePath = rb.getString("offact.upload.path") + "excel/";
	    
	    this.logger.debug("파일정보:" + fileName + extension);
	    this.logger.debug("file:" + fileVO);
	    
	    List stockGroupList = new ArrayList(); //타이틀을 통한 재고값 대상 지점을 담아둔다.
	    List stockMasterListResult = new ArrayList(); //지점별 품목별 재고마스터(안전재고) 데이타를 담는다.
	    
	    String excelInfo = "";//excel 추출데이타
	    List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
	    List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타
	
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
		              if(productCodeCell.getCellType()==0){ //cell type 이 숫자인경우
		            	 excelProductCode=String.valueOf(productCodeCell.getNumericCellValue());
		              }else if(productCodeCell.getCellType()==1){ //cell type 이 일반/문자 인경우
		            	 excelProductCode=productCodeCell.getStringCellValue();
		              }else{//그외 cell type
		            	stockMasterResultVO.setProductCode(""); 
		            	excelProductCode="";
		              }
	        		  
		              //그룹아이디 담기
	        		  stockGroupVO= (StockMasterVO)stockGroupList.get(groupcnt);
	        		  excelGroupId=stockGroupVO.getGroupId();
	        		  
	        		  //재고 담기
	        		  safeStockCell = row.getCell(MASTER_RE_START++); 
		              if(safeStockCell.getCellType()==0){ //cell type 이 숫자인경우
		            	  excelStock=String.valueOf(safeStockCell.getNumericCellValue());
		              }else if(safeStockCell.getCellType()==1){ //cell type 이 일반/문자 인경우
		            	  excelStock=String.valueOf(safeStockCell.getStringCellValue());
		              }else{//그외 cell type
		            	  excelStock="";
		              }
		              
		              stockMasterResultVO.setProductCode(excelProductCode);
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
	  	    	 
	  	          //log Controller execute time end
	  	          long t2 = System.currentTimeMillis();
	  	          logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	     	 	
	  	          return mv;
	          }
	
	        }
	      
	    }
	
	    //DB처리
	    
	     Map rtmMap=null;
	    
	     if("hold".equals(importType)){
	    	 this.logger.info("보유재고 DB Insert");
	    	 rtmMap = this.stockMasterSvc.holdRegiExcelUpload(stockMasterListResult);
	     }else{
	    	 this.logger.info("안전재고 DB Insert");
	    	 rtmMap = this.stockMasterSvc.safeRegiExcelUpload(stockMasterListResult); 
	     }
	
	     rtnErrorList = (List)rtmMap.get("rtnErrorList");
	     rtnSuccessList = (List)rtmMap.get("rtnSuccessList");
	
	     this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
	  
	     mv.addObject("rtnErrorList", rtnErrorList);
	     mv.addObject("rtnSuccessList", rtnSuccessList);
	    
	     mv.setViewName("/master/uploadResult");
	
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

      HttpSession session = request.getSession();
      String strUserId = (String)session.getAttribute("strUserId");

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
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        StockVO stockConVO = new StockVO();
        
        stockConVO.setStart_stockDate(strToday);
        stockConVO.setEnd_stockDate(strToday);

        // 조회조건저장
        mv.addObject("stockConVO", stockConVO);
       
        mv.setViewName("/master/stockManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 재고현황 목록조회
     * 
     * @param UserManageVO
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
   		
   		mv.setViewName("/master/stockExcelForm");
   		
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
    * @param UserManageVO
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
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        
        UserManageVO userConVO = new UserManageVO();
        
        userConVO.setUserId(userId);
        userConVO.setGroupId(groupId);

        // 조회조건저장
        mv.addObject("userConVO", userConVO);

        // 공통코드 조회 (사용자그룹코드)
        /*
        ADCodeManageVO code = new ADCodeManageVO();
        code.setCodeId("IG11");
        List<ADCodeManageVO> searchCondition1 = codeService.getCodeComboList(code);
        mv.addObject("searchCondition1", searchCondition1);
       */
       
        mv.setViewName("/master/salesManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 매출현황 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/master/salespagelist")
    public ModelAndView salesPageList(@ModelAttribute("userConVO") UserManageVO userConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userConVO" + userConVO);

        ModelAndView mv = new ModelAndView();
        List<UserManageVO> userList = null;
        UserManageVO userDetail = null;

        // 사용여부 값 null 일때 공백처리
        if (userConVO.getCon_useYn() == null) {
            userConVO.setCon_useYn("Y");
        }

        // 그룹 값 null 일때 공백처리
        if (userConVO.getCon_groupId() == null) {
            userConVO.setCon_groupId("G00000");
        }
        
        // 조회조건 null 일때 공백처리
        if (userConVO.getSearchGubun() == null) {
            userConVO.setSearchGubun("02");
        }
        
        // 조회값 null 일때 공백처리
        if (userConVO.getSearchValue() == null) {
            userConVO.setSearchValue("system");
        }
        
        // 조회조건저장
        mv.addObject("userCon", userConVO);

        // 페이징코드
        userConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(userConVO.getCurPage(), userConVO.getRowCount()));
        userConVO.setPage_limit_val2(StringUtil.nvl(userConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        userList = userManageSvc.getUserList(userConVO);
        mv.addObject("userList", userList);

        // totalCount 조회
        String totalCount = String.valueOf(userManageSvc.getUserCnt(userConVO));
        mv.addObject("totalCount", totalCount);

        // 기능 권한 리스트
        /*
        HttpSession session = request.getSession();
        UserMenuVO authListVo = new UserMenuVO();
        authListVo.setUSER_ID((String) session.getAttribute("strUserId"));
        List<UserMenuVO> funcList = userMenuSvc.getAuthPerFunction(authListVo);
        mv.addObject("funcList", funcList);
        */
        mv.setViewName("/master/salesPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
}
