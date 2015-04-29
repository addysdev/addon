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
import com.offact.addys.service.manage.UserManageService;
import com.offact.addys.vo.UserMenuVO;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.UserConditionVO;
import com.offact.addys.vo.manage.UserManageVO;
import com.offact.addys.vo.manage.StockMasterVO;
import com.offact.addys.vo.manage.ProductMasterVO;
import com.offact.addys.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class ManageController {

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
    private UserMenuService userMenuSvc;

    @Autowired
    private UserManageService userManageSvc;
    
	 /**
     * 사용자관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/usermanage")
    public ModelAndView userManage(HttpServletRequest request, 
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
       
        mv.setViewName("/manage/userManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 사용자관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/userpagelist")
    public ModelAndView userPageList(@ModelAttribute("userConVO") UserManageVO userConVO, 
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
        mv.setViewName("/manage/userPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/manage/userregform")
	public ModelAndView userRegForm(HttpServletRequest request) throws BizException 
    {
		
		ModelAndView mv = new ModelAndView();
		
		UserManageVO userVO = new UserManageVO();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String createUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
		
		userVO.setCreateUserId(createUserId);
		mv.addObject("userVO", userVO);
		
		mv.setViewName("/manage/userRegForm");
		return mv;
	}
	 /**
     * 사용자관리 등록처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/userreg", method = RequestMethod.POST)
    public @ResponseBody
    String userReg(@ModelAttribute("userVO") UserManageVO userVO, 
    		       HttpServletRequest request, 
    		       HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userVO" + userVO);

		int retVal=this.userManageSvc.userInsertProc(userVO);
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }
	 /**
     * 사용자정 수정화면
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/usermodifyform")
	public ModelAndView userModifyForm(@RequestParam(value = "userId") String userId, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userId" + userId);
    			
		ModelAndView mv = new ModelAndView();
		
		UserManageVO userVO = new UserManageVO();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String updateuserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
        userVO = userManageSvc.getUserDetail(userId);
		
		userVO.setUpdateUserId(updateuserId);
		mv.addObject("userVO", userVO);
		
		mv.setViewName("/manage/userModifyForm");
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
		return mv;
	}
    /**
     * 사용자관리 수정처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/usermodify", method = RequestMethod.POST)
    public @ResponseBody
    String userModify(@ModelAttribute("userVO") UserManageVO userVO, 
    		          HttpServletRequest request, 
    		          HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userVO" + userVO);

		int retVal=this.userManageSvc.userUpdateProc(userVO);
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }
    /**
     * 사용자관리 삭제처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/manage/userdeletes"})
    public @ResponseBody
    String userDeletes(@ModelAttribute("userVO") UserManageVO userVO, 
    		           @RequestParam(value="arrDelUserId", required=false, defaultValue="") String arrDelUserId,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userVO" + userVO);
			
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String updateuserId = StringUtil.nvl((String) session.getAttribute("strUserId"));

	    logger.info("@#@#@# arrDelUserId : " + arrDelUserId);

        int retVal=this.userManageSvc.userDeleteProc(updateuserId , arrDelUserId);
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return ""+retVal;
    }
    /**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/manage/userexcelform")
	public ModelAndView userExcelForm(HttpServletRequest request) throws BizException 
    {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/manage/userExcelForm");
		
		return mv;
	}
    /**
     * 사용자관리 일괄등록
     *
     * @param MultipartFileVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/manage/userexcelimport"})
    public ModelAndView userExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
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
      
        this.logger.info("sheet.getPhysicalNumberOfRows():" + sheet.getPhysicalNumberOfRows());
        
        
        for (int i = DATA_START; i < sheet.getPhysicalNumberOfRows(); i++) {
          
          UserManageVO userManageVO = new UserManageVO();
          XSSFRow row = sheet.getRow(i);
          
          try {
        	userManageVO.setUserName(row.getCell(1).getStringCellValue()); } catch (NullPointerException e) { userManageVO.setUserName(""); } try {
        	userManageVO.setUserId(row.getCell(2).getStringCellValue()); } catch (NullPointerException e) { userManageVO.setUserId(""); } try {
            userManageVO.setMobliePhone(row.getCell(3).getStringCellValue()); } catch (NullPointerException e) { userManageVO.setMobliePhone("");} try {
            userManageVO.setGroupId(row.getCell(4).getStringCellValue()); } catch (NullPointerException e) { userManageVO.setGroupId("");  } try {
            userManageVO.setAuthId(row.getCell(4).getStringCellValue()); } catch (NullPointerException e) { userManageVO.setAuthId("");} try {
            userManageVO.setDeletedYn(row.getCell(5).getStringCellValue()); } catch (NullPointerException e) { userManageVO.setDeletedYn("N");} try {
            userManageVO.setPassword(row.getCell(6).getStringCellValue()); } catch (NullPointerException e) { userManageVO.setPassword("");
            
            }

          userManageVO.setCreateUserId(strUserId);
          userManageVO.setUpdateUserId(strUserId);

          userUploadList.add(userManageVO);

        }

        Map rtmMap = this.userManageSvc.regiExcelUpload(userUploadList);

        List rtnErrorUserVOList = (List)rtmMap.get("rtnErrorUserVOList");
        List rtnSuccessOUserVOList = (List)rtmMap.get("rtnSuccessUserVOList");

        System.out.println("@#@#@# rtnInfoIsVOList.size() : " + rtnErrorUserVOList.size());
   
        mv.addObject("rtnErrorUserVOList", rtnErrorUserVOList);
        mv.addObject("rtnSuccessOUserVOList", rtnSuccessOUserVOList);
      }

      mv.setViewName("/manage/uploadResultList");

      //log Controller execute time end
	  long t2 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	 	
      return mv;
    }
    /**
     * 재고 마스터 일괄등록
     *
     * @param MultipartFileVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/manage/stockmasterimport"})
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
        
        this.logger.info("sheet.getPhysicalNumberOfRows():" + sheet.getPhysicalNumberOfRows());
        this.logger.info("sheet.getPhysicalNumberOfCells():" + sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells());
        
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
        	this.logger.info("담긴 조직아이디:" + stockGroupVO.getGroupId());
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
        	this.logger.info("STOCK 품목아이디:" + stockMasterTestVO.getProductCode());
        	this.logger.info("STOCK 조직아이디:" + stockMasterTestVO.getGroupId());
        	this.logger.info("STOCK 안젖재고값:" + stockMasterTestVO.getSafeStock());
        	this.logger.info("STOCK 보유재고값:" + stockMasterTestVO.getHoldStock());
        }
    /*
        Map rtmMap = this.userManageSvc.regiExcelUpload(userUploadList);

        List rtnErrorUserVOList = (List)rtmMap.get("rtnErrorUserVOList");
        List rtnSuccessOUserVOList = (List)rtmMap.get("rtnSuccessUserVOList");

        System.out.println("@#@#@# rtnInfoIsVOList.size() : " + rtnErrorUserVOList.size());
   
        mv.addObject("rtnErrorUserVOList", rtnErrorUserVOList);
        mv.addObject("rtnSuccessOUserVOList", rtnSuccessOUserVOList);
  */
      }

      mv.setViewName("/manage/uploadResultList");

      //log Controller execute time end
	  long t2 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	 	
      return mv;
    }
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
    @RequestMapping(value = "/manage/productmanage")
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
       
        mv.setViewName("/manage/productManage");
        
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
    @RequestMapping(value = "/manage/productpagelist")
    public ModelAndView productPageList(@ModelAttribute("userConVO") UserManageVO userConVO, 
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
        mv.setViewName("/manage/productPageList");
        
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
    @RequestMapping(value = "/manage/stockmanage")
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
       
        mv.setViewName("/manage/stockManage");
        
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
    @RequestMapping(value = "/manage/stockpagelist")
    public ModelAndView stockPageList(@ModelAttribute("userConVO") UserManageVO userConVO, 
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
        mv.setViewName("/manage/stockPageList");
        
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
    @RequestMapping(value = "/manage/salesmanage")
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
       
        mv.setViewName("/manage/salesManage");
        
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
    @RequestMapping(value = "/manage/salespagelist")
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
        mv.setViewName("/manage/salesPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
}
