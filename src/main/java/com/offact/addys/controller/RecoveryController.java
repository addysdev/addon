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
import com.offact.addys.service.recovery.RecoveryService;
import com.offact.addys.vo.common.GroupVO;
import com.offact.addys.vo.common.CodeVO;
import com.offact.addys.vo.master.SalesVO;
import com.offact.addys.vo.master.StockMasterVO;
import com.offact.addys.vo.master.ProductMasterVO;
import com.offact.addys.vo.order.TargetVO;
import com.offact.addys.vo.recovery.RecoveryVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class RecoveryController {

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
    private RecoveryService recoverySvc;
    
	 /**
     * 회수대상 화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/recovery/recoverymanage")
    public ModelAndView recoveryManage(HttpServletRequest request, 
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
        
        RecoveryVO recoveryConVO = new RecoveryVO();
        
        recoveryConVO.setGroupId(groupId);

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        Date deliveryTime = new Date();
        int movedate=-7;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
        
        recoveryConVO.setStart_recoveryDate(strDeliveryDay);
        recoveryConVO.setEnd_recoveryDate(strToday);
        
        // 조회조건저장
        mv.addObject("recoveryConVO", recoveryConVO);

        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(groupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (발주상태코드)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("RE01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);
       
        mv.setViewName("/recovery/recoveryManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 회수대상 목록조회
     * 
     * @param RecoveryVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/recovery/recoverypagelist")
    public ModelAndView recoveryPageList(@ModelAttribute("recoveryConVO") RecoveryVO recoveryConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : recoveryConVO" + recoveryConVO);

        ModelAndView mv = new ModelAndView();
        List<RecoveryVO> recoveryList = null;

        // 조직값 null 일때 공백처리
        if (recoveryConVO.getCon_groupId() == null) {
        	recoveryConVO.setCon_groupId("");
        }

        // 상태 값 null 일때 공백처리
        if (recoveryConVO.getCon_recoveryState() == null) {
        	recoveryConVO.setCon_recoveryState("");
        }

        // 조회조건저장
        mv.addObject("recoveryConVO", recoveryConVO);

        // 페이징코드
        recoveryConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(recoveryConVO.getCurPage(), recoveryConVO.getRowCount()));
        recoveryConVO.setPage_limit_val2(StringUtil.nvl(recoveryConVO.getRowCount(), "10"));
        
        // 발주대상목록조회
        recoveryList = recoverySvc.getRecoveryPageList(recoveryConVO);
        mv.addObject("recoveryList", recoveryList);

        // totalCount 조회
        String totalCount = String.valueOf(recoverySvc.getRecoveryCnt(recoveryConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/recovery/recoveryPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	 /**
     * 회수등록 화면
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/recovery/recoveryregistform")
    public ModelAndView recoveryRegistForm(HttpServletRequest request, 
    		                               HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
       
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        Date deliveryTime = new Date();
        int movedate=3;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
        
        //회수 마감일 세팅
        mv.addObject("recoveryClosingDate", strDeliveryDay);
        
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId("G00000");
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        mv.setViewName("/recovery/recoveryRegistForm");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 회수 등록 처리
     *
     * @param TargetVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/recovery/recoveryregist"})
    public @ResponseBody
    String recoveryRegist(@ModelAttribute("recoveryVO") RecoveryVO recoveryVO,
    		              @RequestParam(value="arrCheckGroupId", required=false, defaultValue="") String arrCheckGroupId,
    		              @RequestParam(value="arrSelectProductId", required=false, defaultValue="") String arrSelectProductId,
    		              HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : recoveryVO" + recoveryVO);
			
		String deferResult="recovery0000";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        logger.info("@#@#@# arrCheckGroupId: " + arrCheckGroupId);
        logger.info("@#@#@# arrSelectProductId : " + arrSelectProductId);
	    

	    recoveryVO.setRecoveryState("01");
	    recoveryVO.setRegUserId(strUserId);
	    recoveryVO.setCreateUserId(strUserId);
	   
        try{//01.회수처리
       
            int dbResult=recoverySvc.regiRecoveryRegist(recoveryVO , arrCheckGroupId ,arrSelectProductId);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "recovery0001";
		        
	    	}
	
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "recoveryr0002\n[errorMsg] : "+errMsg;
	    	
	    }

		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return deferResult;
    }
    /**
     * 보류대상 상세조회
     * 
     * @param targetConVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/recovery/recoverydetailview")
    public ModelAndView recoveryDetailView( HttpServletRequest request, 
    		                              String recoveryCode) throws BizException 
    {   	
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : recoveryCode : [" + recoveryCode+"]");

        ModelAndView mv = new ModelAndView();
        List<RecoveryVO> recoveryDetailList = null;
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));

        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
         
        String strToday = simpleDateFormat.format(currentTime);
        
        RecoveryVO recoveryConVO = new RecoveryVO();
        RecoveryVO recoveryVO = new RecoveryVO();

        recoveryConVO.setRecoveryCode(recoveryCode);

        recoveryVO=recoverySvc.getRecoveryDetail(recoveryConVO);

        mv.addObject("recoveryVO", recoveryVO);

        
        //회수대상 상세정보
        recoveryDetailList=recoverySvc.getRecoveryDetailList(recoveryConVO);

        mv.addObject("recoveryDetailList", recoveryDetailList);
   
        mv.setViewName("/recovery/recoveryDetailView");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 회수 처리
     *
     * @param RecoveryVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/recovery/recoveryprocess"})
    public @ResponseBody
    String deferProcess(@ModelAttribute("recoveryVO") RecoveryVO recoveryVO,
    		            HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : recoveryVO" + recoveryVO);
			
		String recoveryResult="recovery0010";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
       
	    String[] recoverys = request.getParameterValues("seqs");
	    
	    recoveryVO.setRecoveryState("02");
	    recoveryVO.setUpdateUserId(strUserId);
	    recoveryVO.setRecoveryUserId(strUserId);
	  
  
        try{//01.보류처리
    	    
        	int dbResult=recoverySvc.regiRecoveryProcess(recoverys , recoveryVO);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "recovery0011";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "recovery0012\n[errorMsg] : "+errMsg;
	    	
	    }
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return recoveryResult;
    }
    /**
     * 검수완료
     *
     * @param RecoveryVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/recovery/recoverycomplete"})
    public @ResponseBody
    String recoveryComplete(@ModelAttribute("recoveryVO") RecoveryVO recoveryVO,
    		            HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : recoveryVO" + recoveryVO);
			
		String recoveryResult="recovery0020";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
       
	    String[] recoverys = request.getParameterValues("seqs");
	    
	    recoveryVO.setRecoveryState("03");
	    recoveryVO.setUpdateUserId(strUserId);
	    recoveryVO.setCompleteUserId(strUserId);
	  
  
        try{//01.보류처리
    	    
        	int dbResult=recoverySvc.regiRecoveryComplete(recoverys , recoveryVO);
             
	    	if(dbResult<1){//처리내역이 없을경우
	    		
	    		//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "recovery0021";
		        
	    	}
	   
	    }catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	        return "recovery0022\n[errorMsg] : "+errMsg;
	    	
	    }
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return recoveryResult;
    }
    
    /**
   	 * Simply selects the home view to render by returning its name.
   	 * @throws BizException
   	 */
    @RequestMapping(value = "/recovery/reproductexcelform")
   	public ModelAndView reProductExcelForm(HttpServletRequest request) throws BizException 
       {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : reproductexcelform");
    			
   		ModelAndView mv = new ModelAndView();
   		
   		mv.setViewName("/recovery/reProductExcelForm");
   		
   	    //log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
   		
   		return mv;
   	}
    /**
	   * 회수품목 일괄등록
	   *
	   * @param MultipartFileVO
	   * @param request
	   * @param response
	   * @param model
	   * @param locale
	   * @return
	   * @throws BizException
	   */
	  @RequestMapping({"/recovery/reproductexcelattach"})
	  public ModelAndView stockMasterExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
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
		    
		    List reProductList = new ArrayList(); //DB 성공 대상데이타

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
		 
		      int TITLE_POINT =1;//타이틀 항목위치
		      int ROW_START = 2;//data row 시작지점
		      
		      int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
		      int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
		      
		      XSSFCell myCell = null;
		    
		      this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
		      this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);
		          
		          try {

		           for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS-3; rowcnt++) {
		             
		   		     ProductMasterVO productMasterVO = new ProductMasterVO();
		   			
		             XSSFRow row = sheet.getRow(rowcnt);

		             //cell type 구분하여 담기  
		             String[] cellItemTmp = new String[TOTAL_CELLS]; 
		             for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
			            myCell = row.getCell(cellcnt); 
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
			         }
		         
			         if(cellItemTmp[0] != ""){
			        	 
			        	 productMasterVO.setProductCode(cellItemTmp[0]); 
				
				         excelUploadList.add(productMasterVO);

				      }
				     	
				    }
		          }catch (Exception e){
		 
		          excelInfo = excelInfo+"[error] : "+e.getMessage();
		          ProductMasterVO productMasterVO = new ProductMasterVO();
		          productMasterVO.setErrMsg(excelInfo);
		   	 
		          this.logger.info("["+logid+"] Controller getErrMsg : "+productMasterVO.getErrMsg());
		        
		          reProductList.add(productMasterVO);

		          mv.addObject("reProductList", reProductList);

		          mv.setViewName("/master/uploadResult");
		   	 
		          //log Controller execute time end
		          long t2 = System.currentTimeMillis();
		          logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		 	 	
		          return mv;
		   	   
		      	}
		    }
		    
		    //DB처리
		    reProductList = this.recoverySvc.getExcelAttach(excelUploadList);
 
		    mv.addObject("reProductList", reProductList);
		    
		    mv.setViewName("/recovery/reProductAttach");

		    //log Controller execute time end
		    long t2 = System.currentTimeMillis();
		    logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			
		    return mv;
		        
	  }
	  
	  /**
	   	 * Simply selects the home view to render by returning its name.
	   	 * @throws BizException
	   	 */
	    @RequestMapping(value = "/recovery/recoveryregislist")
	   	public ModelAndView recoveryRegisList(HttpServletRequest request) throws BizException 
	       {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : reproductList");
	    			
	   		ModelAndView mv = new ModelAndView();
	   		
	   		mv.setViewName("/recovery/reProductAttach");
	   		
	   	    //log Controller execute time end
		 	long t2 = System.currentTimeMillis();
		 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	   		
	   		return mv;
	   	}
	    /**
	   	 * Simply selects the home view to render by returning its name.
	   	 * @throws BizException
	   	 */
	    @RequestMapping(value = "/recovery/recoverycodeprint")
	   	public ModelAndView recoveryCodePrint(HttpServletRequest request,
                                              String recoveryCode ) throws BizException 
	       {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();


	   		ModelAndView mv = new ModelAndView();
	   		
	   	    mv.addObject("recoveryCode", recoveryCode);
	   		
	   		mv.setViewName("/recovery/recoveryCodePrint");
	   		
	   		return mv;
	   	}
}
