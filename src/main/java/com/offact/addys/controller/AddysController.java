package com.offact.addys.controller;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.offact.common.StringUtil;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.addys.service.ADService;
import com.offact.addys.service.UserMenuService;
import com.offact.addys.service.UserService;
import com.offact.addys.service.ad.ADUserListManageService;
import com.offact.addys.service.ad.ADUserSearchService;
import com.offact.addys.service.common.CmmService;
import com.offact.addys.vo.ADCodeManageVO;
import com.offact.addys.vo.UserMenuVO;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.UserConditionVO;
import com.offact.addys.vo.ad.ADUserListManageVO;
import com.offact.addys.vo.ad.ADUserSearchVO;



/**
 * Handles requests for the application home page.
 */
@Controller

public class AddysController {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private CmmService cmmService;

	@Autowired
	private ADService codeService;
	
    @Autowired
    private ADUserSearchService adUserSearchService;

    @Autowired
    private ADUserListManageService userListService;
    
    @Autowired
    private UserMenuService         userMenuSvc;
    	
    
    /**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/erp", method = RequestMethod.GET)
	public String homepage(Locale locale, Model model) {
		logger.info("Welcome addys erp! The client locale is {0}." + locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "addys/index";
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addys/loginform", method = RequestMethod.GET)
	public ModelAndView addysloginform(HttpServletRequest request) throws BizException, IOException {
		
		logger.info("Welcome addys loginForm! ");
		
		HttpSession session = request.getSession(false);
		ModelAndView mv = new ModelAndView();
		String strMainUrl = "addys/loginForm";
		
		try{
			String userId = StringUtil.nvl((String) session.getAttribute("strUserId"),"");

			if(!"".equals(userId)){
				strMainUrl="addys/addysMain";
			}
			
		    mv.setViewName(strMainUrl);
	
			return mv;
			
		}catch(Exception e){
			
			mv.setViewName(strMainUrl);
			return mv;
		}

	}
	@RequestMapping("/addys/addyscheck")
	 public @ResponseBody
	 String addysCheck(@RequestParam(value = "id") String id,
				   @RequestParam(value = "pwd") String pwd) {

		logger.info("[id]"+id);
		logger.info("[pwd]" + pwd);
		
		String chkresult="0";
		
		if(id.equals(pwd)){
			chkresult="1";
		}
		
		return chkresult;

	 }

	/**
	 * Login 처리
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/addys/login", method = RequestMethod.POST)
	public ModelAndView addyslogin(HttpServletRequest request,HttpServletResponse response)
			throws Exception
	{
		
		logger.info(">>>> Start Login ");
		
		ModelAndView  mv = new ModelAndView();

		String strUserId = StringUtil.nvl(request.getParameter("id"));
		String strUserPw = StringUtil.nvl(request.getParameter("pw"));
		
		logger.info(">>>> userId :"+strUserId);
		logger.info(">>>> userPw :"+strUserPw);
		
		String strMainUrl = "";
		
		// # 2. 넘겨받은 아이디로 데이터베이스를 조회하여 사용자가 있는지를 체크한다.
		UserVO userChkVo = new UserVO();
		userChkVo.setUserId(strUserId);
		UserVO userChk = userSvc.getUser(userChkVo);		
			
		String strUserName = "";	
		String strUserDeptCode = "";
		String strUserGroupCode = "";
		String strCompanyGbn = "";
			
		String strUserDeptName = "";
		String strUserGroupName = "";
		String strCompanyGbnName = "";
		String strManageYn = "";
		String strWorkYn = "";
		String strLoginYn = "";
			
		String userIp = request.getRemoteAddr();

		logger.info(">>>> userIp :"+userIp);
	
		if(userChk != null)
		{
			strUserDeptCode = userChk.getUserDeptCode();
			strUserGroupCode = userChk.getUserGroupCode();
			strCompanyGbn = userChk.getCompanyGbn();
			strUserName = userChk.getUserNm();	
			
			strUserDeptName = userChk.getUserDeptName();	
			strUserGroupName = userChk.getUserGroupName();
			strCompanyGbnName =userChk.getCompanyGbnName();
			
			strManageYn = userChk.getManagerYn();
			strWorkYn = userChk.getWorkYn();
			strLoginYn = userChk.getLoginYn();
				
			// # 3. Session 객체에 셋팅
			
			HttpSession session = request.getSession(false);
			
			if(session != null)
			{
				session.invalidate();
			}
				
				session = request.getSession(true);
				session.setAttribute("strUserId", strUserId);
				session.setAttribute("strUserName", strUserName);
				session.setAttribute("strUserDeptCode", strUserDeptCode);
				session.setAttribute("strUserGroupCode", strUserGroupCode);
				session.setAttribute("strCompanyGbn", strCompanyGbn);
				
				session.setAttribute("strUserDeptName", strUserDeptName);
				session.setAttribute("strUserGroupName", strUserGroupName);
				session.setAttribute("strCompanyGbnName", strCompanyGbnName);
				
				session.setAttribute("strManageYn", strManageYn);
				session.setAttribute("strWorkYn", strWorkYn);
				session.setAttribute("strLoginYn", strLoginYn);
				
				session.setAttribute("strUserIp", userIp);
				
				//로그인 이력처리		
				userChk.setLoginYn("Y");
				userChk.setConnectIp(userIp);
				try{
					//cmmService.setUserState(userChk);
					//cmmService.setInsertUserState(userChk);
					//cmmService.setUpdateUserState(userChk);
				}catch(Exception e){
					logger.debug("[Error]USER SQL lock 오류");
				}
		
				mv.addObject("ID", strUserId);
				
				strMainUrl = "addys/addysMain";
				
			} else {//app 상요자 정보가 없는경우
	
				logger.info(">>> 상담App 사용자 정보 없음");
				strMainUrl = "addys/loginFail";
			}
			
			mv.addObject("ID", strUserId);
			
			mv.setViewName(strMainUrl);
			
			return mv;
		}
	
	/**
	 * Logout 처리
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addys/logout")
	 public ModelAndView addyslogout(HttpServletRequest request) throws BizException, IOException {
		
		logger.info("Good bye addys! ");
		
		HttpSession session = request.getSession(false);
	 	session.removeAttribute("strUserId");
        session.removeAttribute("strUserName");
        session.removeAttribute("strUserDeptCode");
        session.removeAttribute("strUserGroupCode");
        session.removeAttribute("strCompanyGbn");
        session.removeAttribute("strUserDeptName");
        session.removeAttribute("strUserGroupName");
        session.removeAttribute("strCompanyGbnName");
        session.removeAttribute("strUserIp");
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addys/loginForm");

		return mv;
	}
	@RequestMapping("/addysmain")
    public String addysmain(@RequestParam(value = "addys1") String addys1) {
		
		logger.info("[addys1]"+addys1);


		return "addys/addysMain";
	}
	
	 /**
     * 사용자조회 화면 로딩
     *
     * @param userVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     * @throws IOException
     */
    @RequestMapping(value = "/addys/usermanage")
    public ModelAndView viewUserListManage(@ModelAttribute("UserConVO") ADUserListManageVO userConVO, HttpServletRequest request, HttpServletResponse response) throws BizException, IOException {
        logger.info("====================start User List info userConVO" + userConVO);

        ModelAndView mv = new ModelAndView();

        // 부서정보
        HttpSession session = request.getSession();
        String userDeptcode = StringUtil.nvl((String) session.getAttribute("strUserDeptCode"));
        String userGroupcode = StringUtil.nvl((String) session.getAttribute("strUserGroupCode"));
        userConVO.setUserDeptCode(userDeptcode);
        userConVO.setUserGroupCode(userGroupcode);

        // 조회조건저장
        mv.addObject("userCon", userConVO);

        // 공통코드 조회 (사용자그룹코드)
        ADCodeManageVO code = new ADCodeManageVO();
        code.setCodeId("IG11");
        List<ADCodeManageVO> searchCondition1 = codeService.getCodeComboList(code);
        mv.addObject("searchCondition1", searchCondition1);

        // 공통코드 조회 (업체구분코드)
        ADCodeManageVO code1 = new ADCodeManageVO();
        code1.setCodeId("IG14");
        List<ADCodeManageVO> searchCondition2 = codeService.getCodeComboList(code1);
        mv.addObject("searchCondition2", searchCondition2);

        // 공통코드 조회 (사용자부서코드)
        ADCodeManageVO code2 = new ADCodeManageVO();
        code2.setCodeId("IG10");
        List<ADCodeManageVO> searchCondition3 = codeService.getCodeComboList(code2);
        mv.addObject("searchCondition3", searchCondition3);

        mv.setViewName("/addys/userManage");
        return mv;
    }
    /**
     * 사용자관리 목록조회
     * 
     * @param userDetailVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/addys/userlist")
    public ModelAndView viewAdUserListManageList(@ModelAttribute("UserConVO") ADUserListManageVO userConVO, HttpServletRequest request, HttpServletResponse response) throws BizException, IOException {
        logger.info("====================start User List info userConVO" + userConVO);

        ModelAndView mv = new ModelAndView();
        List<ADUserListManageVO> userList = null;
        ADUserListManageVO userDetail = null;

        // 사용여부 값 null 일때 공백처리
        if (userConVO.getCon_usingYn() == null) {
            userConVO.setCon_usingYn("");
        }

        // 그룹 값 null 일때 공백처리
        if (userConVO.getCon_userGroupCode() == null) {
            userConVO.setCon_userGroupCode("");
        }

        // 조회조건저장
        mv.addObject("userCon", userConVO);

        // 페이징코드
        userConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(userConVO.getCurPage(), userConVO.getRowCount()));
        userConVO.setPage_limit_val2(StringUtil.nvl(userConVO.getRowCount(), "10"));
        // 사용자목록조회
        userList = userListService.getUserList(userConVO);
        mv.addObject("userList", userList);

        // totalCount 조회
        String totalCount = String.valueOf(userListService.getUserCnt(userConVO));
        mv.addObject("totalCount", totalCount);

        // 기능 권한 리스트
        HttpSession session = request.getSession();
        UserMenuVO authListVo = new UserMenuVO();
        authListVo.setUSER_ID((String) session.getAttribute("strUserId"));
        List<UserMenuVO> funcList = userMenuSvc.getAuthPerFunction(authListVo);
        mv.addObject("funcList", funcList);

        mv.setViewName("/addys/userList");
        return mv;
    }

}
