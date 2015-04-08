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

import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.addys.service.UserMenuService;
import com.offact.addys.service.common.CmmService;
import com.offact.addys.vo.UserMenuVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {

	@Autowired
	private CmmService cmmService;
	
	@Autowired
	private UserMenuService userMenuSvc;

	private final Logger logger = Logger.getLogger(getClass());
	
	
	@Value("#{config['cs.host.url']}")
	private String host_url;
	
	@Value("#{config['sso.login.url']}")
	private String sso_url;
	
	@Value("#{config['sso.login.pin']}")
	private String sso_pin;
	
	@Value("#{config['sso.login.t']}")
	private String sso_t;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage(Locale locale, Model model) {
		logger.info("Welcome homepage! The client locale is {0}." + locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home/index";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/mobile", method = RequestMethod.GET)
	public ModelAndView mobile(HttpServletRequest request,HttpServletResponse response,  Model model, Locale locale) throws BizException {

		logger.info("Welcome mobile! The client locale is {}" + locale);
		ModelAndView  mv = new ModelAndView();
		
		String redirectUrl=host_url;
		
		mv.addObject("redirectUrl", redirectUrl);
		mv.setViewName("mobile/index");

		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {0}." + locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "test/index";
	}

	@RequestMapping(value = "/testindex", method = RequestMethod.GET)
	public String testindex(HttpServletRequest request,HttpServletResponse response,  Model model, Locale locale) throws BizException {

		logger.info("Welcome cs! The client locale is {}" + locale);

		return "test/index";
	}


	@RequestMapping("/hello")
	 public @ResponseBody
	 String hello(@RequestParam(value = "name") String name,
				   @RequestParam(value = "gender") String gender,
				   @RequestParam(value = "email") String email,
				   @RequestParam(value = "phone") String phone,
				   @RequestParam(value = "city") String city) {

		  System.out.println(name);
		  System.out.println(gender);
		  System.out.println(email);
		  System.out.println(phone);
		  System.out.println(city);

//		  String str = "{\"user\": { \"name\": \"" + name + "\",\"gender\": \""
//		    + gender + "\",\"email\": \"" + email + "\",\"phone\": \""
//		    + phone + "\",\"city\": \"" + city + "\"}}";

		  String str = name;

		  return str;

	 }

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/jsonrpcTest", method = RequestMethod.GET)
	public String jsonrpcTest(HttpServletRequest request,HttpServletResponse response) throws BizException {
		logger.info("jsonrpcTest 1");

		return "test/jsonrpcTest";
	}

	/**
	 * JSON-RPC CALL TEST
	 * @param request
	 * @param response
	 * @return
	 * @throws BizException
	 */
	@RequestMapping(value = "/jsonrpcCall", method = RequestMethod.GET)
	public ModelAndView jsonrpcCall(HttpServletRequest request,HttpServletResponse response) throws BizException{
		ModelAndView mv = new ModelAndView();

		String key = "member_srl";
		String param = "30500625";

		JSONRpcService rpcService = new JSONRpcService();

		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put(key, param);

		logger.info("jsonrpcCall dataMap==>" + dataMap);

		String callServiceRpc = "";
		callServiceRpc = rpcService.callServiceRpc(dataMap, CodeConstant.RPC_CLASS_01, CodeConstant.RPC_METHOD_01);

		logger.debug("callServiceRpc ==>" + callServiceRpc);

		Object obj = JSONValue.parse(callServiceRpc);
		JSONObject object = (JSONObject)obj;

		logger.debug(object.get("member_srl"));

//		{"member_srl":1,"id":"admin","name":"admin","use_ipin_yn":"N","cert_method":"N","birth_date":"1911-11-11","mobile_phone":"","allow_sms_yn":"Y","allow_sms_remind_yn":"Y","email":"dev@tmon.co.kr","allow_email_yn":"N","address_zipcode":"","address_base":"","address_detail":"","favorate_area":106109,"favorate_area_name":"압구정/신사/가로수","register_date":"2010-05-10 00:00:00","last_login_date":"2012-06-01 23:53:38","login_count":1115,"recommender_srl":0,"recommender_name":"","recommender_id":"","reseller_rate":0,"level":8,"leave_date":"","memo":"","blacklist_note":"","gender":"F"}

		mv.addObject("JSONObject", object);
		mv.setViewName("test/jsonrpcCall");

		return mv;
	}

	/**
	 * JSON-RPC CALL TEST
	 * @param request
	 * @param response
	 * @return
	 * @throws BizException
	 */
	@RequestMapping(value = "/jsonrpcCall_List", method = RequestMethod.GET)
	public ModelAndView jsonrpcCall_List(HttpServletRequest request,HttpServletResponse response) throws BizException{

		ModelAndView mv = new ModelAndView();

		String key = "query";
		String param = "나상욱";
		JSONRpcService rpcService = new JSONRpcService();

		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put(key, param);

		logger.info("jsonrpcCall dataMap==>" + dataMap);

		String callServiceRpc = "";
		callServiceRpc = rpcService.callServiceRpc(dataMap, CodeConstant.RPC_CLASS_02, CodeConstant.RPC_METHOD_02);

		logger.debug("callServiceRpc ==>" + callServiceRpc);




		Object obj = JSONValue.parse(callServiceRpc);


		JSONObject object = (JSONObject)obj;


		logger.debug("callServiceRpc ==>"+object);

//		{"member_srl":1,"id":"admin","name":"admin","use_ipin_yn":"N","cert_method":"N","birth_date":"1911-11-11","mobile_phone":"","allow_sms_yn":"Y","allow_sms_remind_yn":"Y","email":"dev@tmon.co.kr","allow_email_yn":"N","address_zipcode":"","address_base":"","address_detail":"","favorate_area":106109,"favorate_area_name":"압구정/신사/가로수","register_date":"2010-05-10 00:00:00","last_login_date":"2012-06-01 23:53:38","login_count":1115,"recommender_srl":0,"recommender_name":"","recommender_id":"","reseller_rate":0,"level":8,"leave_date":"","memo":"","blacklist_note":"","gender":"F"}

		// 목록 조회
		Object obj_list = JSONValue.parse(object.get("member_list").toString());
		JSONArray array =  (JSONArray)obj_list;

		List<JSONObject> jasonList=new ArrayList<JSONObject>();
		JSONObject arryObject;
		Object arryObj=null;

		for (int i=0;i<array.size();i++){

			arryObj = JSONValue.parse(array.get(i).toString());
			arryObject= (JSONObject)arryObj;
			jasonList.add(arryObject);
		}
    	mv.addObject("jasonList", jasonList);

		mv.addObject("JSONObject", object);
		mv.setViewName("test/jsonrpcCall_list");

		return mv;
	}

	/**
	 * JSON-RPC CALL TEST
	 * @param request
	 * @param response
	 * @return
	 * @throws BizException
	 * @throws IOException
	 */
	@RequestMapping(value = "/jsonrpcCall_URL", method = RequestMethod.GET)
	public ModelAndView jsonrpcCall_URL(HttpServletRequest request,HttpServletResponse response) throws BizException, IOException{

		ModelAndView mv = new ModelAndView();

		String key ="user_id";
		String value="sktkddnro0o";





		String requesturl="http://billgate.tmon.co.kr/cti/member/search?api_key=AsbNm1VM1l1ORr5qAOFb8uGTRHVHVwn9&secret=N1EB&t="+key+"&q="+value+"";
		BufferedReader input = null;
		String line="";

		URL url = new URL(requesturl);
		input = new BufferedReader(new InputStreamReader(url.openStream()));

		line=input.readLine();

		Object obj = JSONValue.parse(line);

		// 목록 조회
		JSONArray array =  (JSONArray)obj;
		logger.debug("array ==>" + array);
		List<JSONObject> jasonList=new ArrayList<JSONObject>();
		JSONObject arryObject;
		Object arryObj=null;

		for (int i=0;i<array.size();i++){

			arryObj = JSONValue.parse(array.get(i).toString());
			arryObject= (JSONObject)arryObj;
			jasonList.add(arryObject);
		}
    	mv.addObject("jasonList", jasonList);
    	
    	url = new URL("http://127.0.0.1:8080/cs/RefferLog?UserId=ipcc01ps&ConnectIP=10.1.101.1&RefferUserSrl=405050&ActionId=G5000&ResultCnt=20");
    	url.openStream();
    	

    	logger.debug("url ==>" + url);

		//전체 카운트 조회
		//String totalCount = object.get("total_count").toString();
		//mv.addObject("totalCount", totalCount);

		//mv.addObject("JSONObject", object);
		mv.setViewName("test/jsonrpcCall_URL");

		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/errors404", method = RequestMethod.GET)
	public ModelAndView errors404(HttpServletRequest request,HttpServletResponse response) throws BizException {
		logger.info("errors404");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/404");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/errors500", method = RequestMethod.GET)
	public ModelAndView errors500(HttpServletRequest request,HttpServletResponse response) throws BizException {
		logger.info("errors500");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/500");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/errors", method = RequestMethod.GET)
	public ModelAndView errors(HttpServletRequest request,HttpServletResponse response) throws BizException {
		logger.info("errors");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/errors");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/warning", method = RequestMethod.GET)
	public ModelAndView warning(HttpServletRequest request,HttpServletResponse response) throws BizException {
		logger.info("warning");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errors/warning");
		return mv;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/loginFail", method = RequestMethod.GET)
	public ModelAndView loginFail(HttpServletRequest request,HttpServletResponse response) throws BizException {
		logger.info("loginFail");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("test/loginFail");
		return mv;
	}
	//동작
	@RequestMapping(value = "/redirectUrl2", method = RequestMethod.GET)
	public ModelAndView redirectUrl2(){
		return new ModelAndView("redirect:/index");
	}

	//동작
	@RequestMapping(value = "/redirectUrl3", method = RequestMethod.GET)
	public ModelAndView redirectUrl3(){
		RedirectView redirectView = new RedirectView("/index");
		redirectView.setContextRelative(true);

		Map<String, ?> map = null;
		ModelAndView mv = new ModelAndView(redirectView, map);
		return mv;
	}

	//동작
	@RequestMapping(value = "/redirectUrl4", method = RequestMethod.GET)
	public ModelAndView redirectUrl4(){
		ModelAndView mv = new ModelAndView();

		mv.setView(new RedirectView("/cs/index"));
		Object params = null;
		mv.addObject("parameter",params);
		return mv;
	}

	//동작
	@RequestMapping(value = "/redirectUrl5", method = RequestMethod.POST)
	public ModelAndView redirectUrl5(){
		ModelAndView mv = new ModelAndView();

		mv.setView(new RedirectView("/cs/index"));
		Object params = null;
		mv.addObject("parameter",params);
		return mv;
	}
	
	 /**
     * 메뉴권한을 가져온다
     * 
     * @param request URID 녹취Key 값
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/authMenuList")
    public ModelAndView authMenuList(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale, HttpSession session) throws BizException, IOException, Exception {
      
    	ModelAndView mv = new ModelAndView();
    	
    	// # Session 
		String strUserId= (String)session.getAttribute("strUserId");
		String strUserDeptCode= (String)session.getAttribute("strUserDeptCode");
		String strUserGroupCode= (String)session.getAttribute("strUserGroupCode");

    	// # 세션 아이디로 기능별 권한리스트를 조회한다.
		UserMenuVO authListVo = new UserMenuVO();
		authListVo.setUSER_ID(strUserId);
		authListVo.setUSER_DEPT_CODE(strUserDeptCode);
		authListVo.setMENU_YN("Y");
		
		List<UserMenuVO> authList = null;
		
		logger.info(">>> authList strUserGroupCode::: " + strUserGroupCode);
		
		if(strUserGroupCode.equals("LV0")){
			authList=userMenuSvc.getAuthPerSuper(authListVo);
		}else{
			authList=userMenuSvc.getAuthPerMenu(authListVo);
		}
		
		//logger.info(">>> authList ::: " + authList);
		
		StringBuilder authBuilder = new StringBuilder();
		
		if(authList.size()>0){
			
			for (int i=0 ; i<authList.size();i++){
				
				if(i==authList.size()-1){
					authBuilder.append(authList.get(i).getFUNC_AUTH_CODE());
				}else{
					authBuilder.append(authList.get(i).getFUNC_AUTH_CODE());
					authBuilder.append("|");
				}
				
			}
		
		}
		
		mv.addObject("authList", authBuilder);

        mv.setViewName("jsonView"); // ajax로 데이타 직접 전송(json형식으로)
        return mv;
    }
    
    /**
     * 기능권한을 가져온다
     * 
     * @param request URID 녹취Key 값
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/authFuncList")
    public ModelAndView authFuncList(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale, HttpSession session) throws BizException, IOException, Exception {
      
    	ModelAndView mv = new ModelAndView();
    	
    	// # Session 
		String strUserId= (String)session.getAttribute("strUserId");
		String strUserDeptCode= (String)session.getAttribute("strUserDeptCode");
		String strUserGroupCode= (String)session.getAttribute("strUserGroupCode");

    	// # 세션 아이디로 기능별 권한리스트를 조회한다.
		UserMenuVO funcListVo = new UserMenuVO();
		funcListVo.setUSER_ID(strUserId);
		funcListVo.setUSER_DEPT_CODE(strUserDeptCode);
		funcListVo.setMENU_YN("N");
		
		List<UserMenuVO> funcList = null;
		
		if(strUserGroupCode.equals("LV0")){
			funcList=userMenuSvc.getAuthPerSuper(funcListVo);
		}else{
			funcList=userMenuSvc.getAuthPerFunction(funcListVo);
		}
		
		//logger.info(">>> funcList ::: " + funcList);
		
		StringBuilder funcBuilder = new StringBuilder();
		
		if(funcList.size()>0){
			
			for (int i=0 ; i<funcList.size();i++){
				
				if(i==funcList.size()-1){
					funcBuilder.append(funcList.get(i).getFUNC_AUTH_CODE());
				}else{
					funcBuilder.append(funcList.get(i).getFUNC_AUTH_CODE());
					funcBuilder.append("|");
				}
				
			}
		
		}
		
		mv.addObject("funcList", funcBuilder);

        mv.setViewName("jsonView"); // ajax로 데이타 직접 전송(json형식으로)
        return mv;
    }

}
