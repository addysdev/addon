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

import com.offact.framework.util.StringUtil;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.addys.service.UserMenuService;
import com.offact.addys.service.UserService;
import com.offact.addys.service.manage.UserManageService;
import com.offact.addys.vo.UserMenuVO;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.UserConditionVO;
import com.offact.addys.vo.manage.UserManageVO;


/**
 * Handles requests for the application home page.
 */
@Controller

public class ManageController {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserService userSvc;
	
    @Autowired
    private UserMenuService userMenuSvc;

    @Autowired
    private UserManageService userManageSvc;
    	
	public String logid(){
		
		double id=Math.random();
		long t1 = System.currentTimeMillis ( ); 
		
		String logid=""+t1+id;
		
		return logid;
	}
	 /**
     * 사용자관리 화면 로딩
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
    @RequestMapping(value = "/manage/usermanage")
    public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response) throws BizException, IOException {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] ManageController.userManage start ");

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
      	logger.info("["+logid+"] ManageController.userManage end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
    @RequestMapping(value = "/manage/userpagelist")
    public ModelAndView userPageList(@ModelAttribute("userConVO") UserManageVO userConVO, HttpServletRequest request, HttpServletResponse response) throws BizException, IOException {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] ManageController.userPageList start User List info userConVO" + userConVO);

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
       	logger.info("["+logid+"] ManageController.userPageList end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/manage/userregform")
	public ModelAndView userRegForm(HttpServletRequest request) throws BizException {
		
		ModelAndView mv = new ModelAndView();
		
		UserManageVO userVO = new UserManageVO();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String userId = StringUtil.nvl((String) session.getAttribute("strUserId"));
		
		userVO.setUserId(userId);
		mv.addObject("userVO", userVO);
		
		mv.setViewName("/manage/userRegForm");
		return mv;
	}
	
    @RequestMapping(value = "/manage/userreg", method = RequestMethod.POST)
    public String userReg(@ModelAttribute("userVO") UserManageVO userVO, HttpServletRequest request)
      throws BizException, IOException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] ManageController.userReg start User List info userVO" + userVO);

		ModelAndView mv = new ModelAndView();
		
		mv.addObject("userVO", userVO);

		int retVal=this.userManageSvc.userInsertProc(userVO);
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] ManageController.userReg end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }


}
