<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<%
    //session 정보
	String strUserId= (String)session.getAttribute("strUserId");
	String strUserName= (String)session.getAttribute("strUserName");
	String strGroupId= (String)session.getAttribute("strGroupId");	
	String strGroupName= (String)session.getAttribute("strGroupName");
	String strAuthId= (String)session.getAttribute("strAuthId");
	String strAuthName= (String)session.getAttribute("strAuthName");	
	String strExcelAuth= (String)session.getAttribute("strExcelAuth");
	String strOfficePhone= (String)session.getAttribute("strOfficePhone");
	String strOfficePhoneFormat= (String)session.getAttribute("strOfficePhoneFormat");
	String strMobliePhone= (String)session.getAttribute("strMobliePhone");
	String strMobliePhoneFormat= (String)session.getAttribute("strMobliePhoneFormat");
	String strEmail= (String)session.getAttribute("strEmail");
	String strIp= (String)session.getAttribute("strIp");
	String strAuth= (String)session.getAttribute("strAuth");
	String pwdChangeDateTime= ((String)session.getAttribute("pwdChangeDateTime")).replaceAll("-", "");
	String pwCycleDate= ((String)session.getAttribute("pwCycleDate")).replaceAll("-", "");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>addys</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Latest compiled and minified CSS-->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">
	
	<!-- Latest compiled JavaScript--> 
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

	<!-- lastest ver -->
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>-->
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/locales/bootstrap-datetimepicker.kr.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/errorMsg.js"></script>

	<style>
	/* paging */
	.paging {width:100%;margin:10px 0 0;text-align:center;}
	.paging a, .paging strong {padding:4px 4px 1px;vertical-align:bottom;}
	.paging strong {color:#f15a22;}
	.paging .btn {display:inline-block;padding:0;width:21px;height:20px;background:url(../images/common/btn.gif) no-repeat;}
	.paging .btn_first {background:url(../images/main/btn_pre02.gif)}
	.paging .btn_prev {background:url(../images/main/btn_pre01.gif)}
	.paging .btn_next {background:url(../images/main/btn_next01.gif)}
	.paging .btn_last {background:url(../images/main/btn_next02.gif)}

	/* 서브타이틀 영역 (.sub_title) */
	.sub_title { margin-bottom:20px; width:100%; height:43px; border-bottom:1px solid #ccc; }
	.sub_title .titleP { position:relative; left:0; float:left; margin-left:-7px; padding-left:31px; _padding-top:3px; height:28px; _height:25px; background:url(../images/sub/icon_title.gif) no-repeat; font-family:Malgun Gothic, "맑은 고딕", "돋움", dotum, verdana, Tahoma, sans-serif; font-size:16px; color:#2c3546; font-weight:bold; letter-spacing:-1px; }
	.seachNm { float:left; margin:1px 0 0 20px; padding:4px 0 0 20px; padding-top:5px\9; height:16px; height:15px\9; border:1px solid #cbcbcb; background:#eef0f4; }
	.sub_title .seachNm li { display:inline; padding:0 15px 0 8px; background:url(../images/sub/icon_01.gif) no-repeat 0 2px; }

	/* 공통부분  */

	body { margin:0; padding:0; } 
		
	div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,form,fieldset,p,button{margin:0;padding:0;}
	body,h1,h2,h3,h4,th,td,input, select, textarea {color:#666; font-family:"돋움", dotum, verdana, Tahoma, sans-serif; font-size:11px; font-weight:normal; padding:0px; margin:0px}
	html { scrollbar-arrow-color: #999; scrollbar-3dlight-color: #e6e6e6; scrollbar-darkshadow-color: #e9e9e9; scrollbar-face-color: #f4f4f0; scrollbar-highlight-color:#FFFFFF; scrollbar-shadow-color: #d0d0d0; scrollbar-track-color: #F2F2F2; }
	hr{display:none;}
	img,fieldset,iframe {border:0;}
	ul,ol,li{list-style:none; margin:0; padding:0;}
	img,input,select,textarea{
		vertical-align:text-top;
	}
	input, label { vertical-align:middle;}
	caption, legend { display: none; }
	em, address { margin:0; padding:0; font-style:normal }
	.vm{vertical-align:middle !important;}
	.bn{border:none !important}
	.chk,.rdo{widows:13px;height:13px;margin:0 !important;padding:0 !important;vertical-align:middle}
	.chk_label{position:relative;top:1px;*top:2px;left:0px}
	.blind{overflow:hidden;position:absolute;visibility:hidden;width:0;height:0;font-size:0;line-height:0}

	/* 2차 버전 - 20130801 */
	/* gnb 속성 */
	#gnb_wrap { width:100%; min-width:1000px; height:174px; background:url(../images/top/bg_top.gif) repeat-x; }
	#gnb_wrapL { position:relative; width:100%; min-width:1000px; height:174px; background:url(../images/top/bg_topleft.gif) no-repeat;  z-index:200;}
	#gnb_logo {
		margin-left:20px;
		width:352px;
		height:119px;
		background:url(../images/top/bg_logo.jpg) no-repeat left bottom;
		text-align:left;
	}
	#gnb_logo h1 { margin:0 auto; padding-top:20px; width:212px; height:68px; }
	#top_set { position:absolute; right:18px; top:0; padding:2px 3px 0 0; height:28px; line-height:28px; color:#ffffff; letter-spacing:-0.5px; background:; }
	#top_set a { color:#3b3b3b; }
	#top_set ul { float:right; }
	#top_set li { float:left; padding-left:7px; }
	#top_set .bar { padding-right:9px; background:url(../images/top/bar_top_set.gif) no-repeat right top; }
	#top_set .addr { padding-left:15px; background:url(../images/top/icon_topaddr.gif) no-repeat 1px 0; }
	#top_set li strong { color:#fff000; font-weight:normal; letter-spacing:0; }
	#top_set li alarm { color:red; font-weight:bold; letter-spacing:0; }
	#gnb_btn { position:absolute; right:18px; top:30px; }
	#gnb_btn li { float:left; padding-right:2px; }
	#gnb_inform { margin-left:70px; width:252px; height:24px; text-align:center; background: url(../images/top/inform.gif) no-repeat; color:#fff; }
	#gnb_inform li { display:inline; line-height:26px; }
	#gnb_inform li.bar { padding-right:7px; background: url(../images/top/bar_gnb_inform.gif) no-repeat right top; }
	#gnb_menu_wrap { position:absolute; right:20px; top:67px; *top:71px; _top:72px; z-index:100; }
	#gnb_menu_wrap .dep1 { position:relative; float:left; width:100px; }
	#gnb_menu_wrap .sub_menu { *margin-top:1px; _margin-top:0; padding:18px 0 10px; width:118px; border-left:1px solid #fff; border-bottom:1px solid #fff; border-right:1px solid #fff; background:#4988bd url(../images/top/bg_sub_menu.gif) no-repeat 50% 3px; text-align:left; z-index:1000; }
	#gnb_menu_wrap .sub_menu li { display:block; margin:1px 0; }
	#gnb_menu_wrap .sub_menu a { display:block; padding:3px 10px 1px 17px; line-height:16px; border-top:1px solid #4988bd; border-bottom:1px solid #4988bd; background:url(../images/top/icon_sub_menu.gif) no-repeat 10px 8px; color:#fff !important; }
	#gnb_menu_wrap .sub_menu a:hover, #gnb_menu_wrap .sub_menu a:active { border-top:1px solid #007eb5; border-bottom:1px solid #007eb5; background-color:#00a8f2; color:#fff !important; }

	
	</style>

	<script type="text/javascript">
	
	//logout 처리
		var goLogout =  function() {
	
			//alert('logout');
			
			$('#logoutForm').attr({action:"<%= request.getContextPath() %>/addys/logout"});
			
			try {
				logoutForm.submit();
			} catch(e) {}
		};
		
		//레이어팝업 : 사용자수정 Layer 팝업
	    function goMyInfo(userId){

	    	$('#passwordModify').dialog({
	            resizable : false, //사이즈 변경 불가능
	            draggable : true, //드래그 불가능
	            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

	            width : 400,
	            height : 415,
	            modal : true, //주위를 어둡게

	            open:function(){
	                //팝업 가져올 url
	                $(this).load('<%= request.getContextPath() %>/addys/usermodifyform',{
	    				'userId' : userId
	    			});
	                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                    $("#passwordModify").dialog('close');

	                    });
	            }
	            ,close:function(){
	                $('#passwordModify').empty();
	            }
	        });

	    };
	
	</script>
    <c:set var="strUserId" value="<%=strUserId %>" />
	<c:set var="strUserName" value="<%=strUserName %>" />
	<c:set var="strGroupId" value="<%=strGroupId %>" />
	<c:set var="strGroupName" value="<%=strGroupName %>" />
	<c:set var="strAuthId" value="<%=strAuthId %>" />
	<c:set var="strAuthName" value="<%=strAuthName %>" />
	<c:set var="strExcelAuth" value="<%=strExcelAuth %>" />
	<c:set var="strAuth" value="<%=strAuth %>" />
</head>
<body id="gnb" name = "CommTop" onunload="browser_Event()" onload="MM_preloadImages('<%= request.getContextPath() %>/images/top/menu_01_on.jpg','<%= request.getContextPath() %>/images/top/menu_01_on.jpg')">
  <form method="post" id="logoutForm" name="logoutForm"  role="form" >
  </form>
<!-- 레이아웃 시작 -->
<div id="gnb_wrap">
<div><!-- 왼쪽 배경 -->
	<!-- 로고 시작 -->
	<div id="gnb_logo">
	<h1><img src="<%= request.getContextPath() %>/images/top/addys_logo.gif" width="296" height="40" /></h1>
	<!--  <h1><img src="<%= request.getContextPath() %>/images/top/offact_logo.jpg" width="296" height="40" />-->	
	</div>
	<!-- 로고 끝 -->
	<!-- 최상단메뉴 시작 -->
	<div id="top_set">
		<ul>
		    <c:if test="${smsAlarmYn == 'Y'}">
		    <li><span class="bar"><alarm>※ SMS 잔여포인트가 <strong>${smsAlarmPoint}(P)</strong> 이하로 남았습니다. 관리자를 통해 충전 부탁드립니다!!</alarm></span></li>
		    </c:if>
			<li>사용자 : <span class="bar"><strong> <%=strUserName %>(<%=strUserId %>)</strong></span></li>
			<li>지점 : <span class="bar"><strong> <%=strGroupName %>(<%=strGroupId %>)</strong></span></li>
			<li><span class="bar"><a href="javascript:goMyInfo('<%=strUserId %>');">비밀번호 변경</a></span></li>
			<li><span class="whiteTxt bar"><a href="javascript:goLogout();">Logout</a></span></li>
		</ul>
	</div>
	<!-- 최상단 메뉴 끝 -->

	<!-- gnb 메인메뉴 랩 시작 -->
	<div id="gnb_menu_wrap">
		<!-- gnb 메인메뉴 시작 -->
		<div id="gnb_menu">
			<!-- 메뉴시작 -->
			<div id="menu">
				<ul class="navbar-nav" role="tablist">
					<!-- 발주관리 시작 -->
					<li>
					<a class="dropdown-toggle" data-toggle="dropdown" href="#" onmouseover="MM_nbGroup('over','menu_01','<%= request.getContextPath() %>/images/top/addys-menu_01_on.jpg','<%= request.getContextPath() %>/images/top/addys-menu_01_on.jpg',1)" onmouseout="MM_nbGroup('out')">
					   <img src="<%= request.getContextPath() %>/images/top/addys-menu_01.jpg" name="menu_01" border="0" id="menu_01" />
					</a> 
			          <ul class="dropdown-menu" role="menu">
			          	<c:choose>
	    					<c:when test="${strAuthId == 'STAFF'}">
	    						<!--  STAFF 미사용  -->
							</c:when>
					    <c:otherwise>
			            <li><a href="<%= request.getContextPath() %>/order/targetmanage" >발주 리스트</a></li>
			            </c:otherwise>
						</c:choose>
			            <li><a href="<%= request.getContextPath() %>/order/ordermanage" >검수 리스트</a></li>                        
			          </ul>
			        </li>
					<!-- 발주관리 끝 -->
					<c:choose>
    					<c:when test="${strAuthId == 'STAFF'}">
    						<!--  STAFF 미사용  -->
						</c:when>
				    <c:otherwise>
						<!-- 회수관리 시작 -->
						<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#"   onmouseover="MM_nbGroup('over','menu_02','<%= request.getContextPath() %>/images/top/addys-menu_02_on.jpg','<%= request.getContextPath() %>/images/top/addys-menu_02_on.jpg',1)" onmouseout="MM_nbGroup('out')">
						   <img src="<%= request.getContextPath() %>/images/top/addys-menu_02.jpg"   name="menu_02" border="0" id="menu_02"  /></a>
						</a> 
				          <ul class="dropdown-menu" role="menu">
			            	<li><a href="<%= request.getContextPath() %>/recovery/collectmanage">회수 리스트</a></li>                    
			          	</ul>
				        </li>
				        <!-- 분석 시작 -->
						<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#"   onmouseover="MM_nbGroup('over','menu_06','<%= request.getContextPath() %>/images/top/addys-menu_06_on.jpg','<%= request.getContextPath() %>/images/top/addys-menu_06_on.jpg',1)" onmouseout="MM_nbGroup('out')">
						   <img src="<%= request.getContextPath() %>/images/top/addys-menu_06.jpg"   name="menu_06" border="0" id="menu_06"  /></a>
						</a> 
				          <ul class="dropdown-menu" role="menu">
			            	<li><a href="<%= request.getContextPath() %>/analysis/holdstockmanage">보유재고 분석/추천</a></li> 
			            	<li><a href="<%= request.getContextPath() %>/analysis/gmroimanage">GMROI 분석</a></li>                           
			          	</ul>
				        </li>
				        <c:if test="${strAuth != '03'}">
				        <!-- 이력 시작 -->
						<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#"   onmouseover="MM_nbGroup('over','menu_05','<%= request.getContextPath() %>/images/top/addys-menu_05_on.jpg','<%= request.getContextPath() %>/images/top/addys-menu_05_on.jpg',1)" onmouseout="MM_nbGroup('out')">
						   <img src="<%= request.getContextPath() %>/images/top/addys-menu_05.jpg"   name="menu_05" border="0" id="menu_05"  /></a>
						</a> 
				          <ul class="dropdown-menu" role="menu">
				            <li><a href="<%= request.getContextPath() %>/history/smshistorymanage">SMS 전송이력</a></li>
				            <li><a href="<%= request.getContextPath() %>/history/workhistorymanage">업무 처리이력</a></li>   
			          	</ul>
				        </li>
				        </c:if>
				        <!-- Master 관리시작 -->
				        <li class="dropdown">
				         <a class="dropdown-toggle" data-toggle="dropdown" href="#"   onmouseover="MM_nbGroup('over','menu_03','<%= request.getContextPath() %>/images/top/addys-menu_03_on.jpg','<%= request.getContextPath() %>/images/top/addys-menu_03_on.jpg',1)" onmouseout="MM_nbGroup('out')">
							<img src="<%= request.getContextPath() %>/images/top/addys-menu_03.jpg"   name="menu_03" border="0" id="menu_03"  /></a>
				          <ul class="dropdown-menu" role="menu">
				            <c:if test="${strAuth != '03'}">
				            <li><a href="<%= request.getContextPath() %>/master/productmanage">품목 관리</a></li>
				            <li><a href="<%= request.getContextPath() %>/master/orderlimitmanage">발주제한 관리</a></li>
							<li><a href="<%= request.getContextPath() %>/master/orderaddmanage">발주추가 관리</a></li>
				            </c:if>
				            <li><a href="<%= request.getContextPath() %>/master/stockmanage">재고현황 관리</a></li>   
				            <li><a href="<%= request.getContextPath() %>/master/salesmanage">매출현황 관리</a></li>                            
				          </ul>
				        </li>
			        </c:otherwise>
					</c:choose>
			        <!-- 관리시작 -->
			        <c:if test="${strAuth != '03'}">
			        <c:if test="${strAuth == '01'}">
			        <li class="dropdown">
			          <a class="dropdown-toggle" data-toggle="dropdown" href="#"  onmouseover="MM_nbGroup('over','menu_07','<%= request.getContextPath() %>/images/top/addys-menu_07_on.jpg','<%= request.getContextPath() %>/images/top/addys-menu_07_on.jpg',1)" onmouseout="MM_nbGroup('out')">
						<img src="<%= request.getContextPath() %>/images/top/addys-menu_07.jpg"   name="menu_07" border="0" id="menu_07"  /></a>
			          <ul class="dropdown-menu" role="menu">
 						<li><a href="<%= request.getContextPath() %>/smart/asmanage">AS 관리</a></li>
			            <li><a href="<%= request.getContextPath() %>/smart/counselmanage">문의 관리</a></li>
			            <li><a href="<%= request.getContextPath() %>/smart/comunitymanage">매장과talk 관리</a></li>
			            
			            <!-- li><a href="#">조직관리</a></li>
			            <li><a href="#">메뉴권한관리</a></li -->                        
			          </ul>
			        </li>
			        </c:if>
			        <li class="dropdown">
			          <a class="dropdown-toggle" data-toggle="dropdown" href="#"  onmouseover="MM_nbGroup('over','menu_04','<%= request.getContextPath() %>/images/top/addys-menu_04_on.jpg','<%= request.getContextPath() %>/images/top/addys-menu_04_on.jpg',1)" onmouseout="MM_nbGroup('out')">
						<img src="<%= request.getContextPath() %>/images/top/addys-menu_04.jpg"   name="menu_04" border="0" id="menu_04"  /></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="<%= request.getContextPath() %>/manage/usermanage">계정 관리</a></li>
			            <li><a href="<%= request.getContextPath() %>/manage/companymanage">업체 관리</a></li>
			            <!-- li><a href="#">조직관리</a></li>
			            <li><a href="#">메뉴권한관리</a></li -->                        
			          </ul>
			        </li>
			        </c:if>
				</ul>
			</div>
			<!-- 메뉴 끝 -->
		</div>
		<!-- gnb 메인메뉴 끝 -->
	</div>
	<!-- gnb 메인메뉴 랩 끝 -->
</div><!-- 왼쪽 배경 -->
</div>
<!-- 레이아웃 끝 -->
  <!-- 사용자 수정-->
  <div id="passwordModify"  title="비밀번호 수정"></div>
</body>
</html>
<script>
//alert('${strUserId}');
if('${strUserId}'==null || '${strUserId}'=='null' ){
	goLogout();
}

if('<%=pwCycleDate %>'>'<%=pwdChangeDateTime %>'){
	goMyInfo('<%=strUserId %>');
}


</script>