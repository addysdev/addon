<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.net.*" %>
<%@ page import="com.offact.framework.util.StringUtil"%>
<%
	String ipaddress = InetAddress.getLocalHost().getHostAddress();
	if (ipaddress == null) ipaddress = InetAddress.getLocalHost().getHostAddress();
	
	String sClientIP = request.getRemoteAddr();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>addys</title>
<link href="<%= request.getContextPath() %>/css/issue_style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}

img {margin:0; padding:0; vertical-align:top; line-height:0; border:0;}
-->
</style>
<script>

//login 처리
function goLogin() {
 
	var id = $('#id').val();
	var pwd = $('#pwd').val();
	
	if(id==''){
		alert('ID를 입력하세요');
		$('#id').focus();
		return;
	}
	
	
	if(pwd==''){
		alert('Password 를 입력하세요');
		$('#pwd').focus();
		return;
	}
	
    //alert('id:'+id);
	//alert('pw:'+pwd);
	
	//alert( $("input[name=saveId]:checkbox:checked").val());
	var chkCnt = $("input[name=saveId]:checkbox:checked").length;

	if( $("input[name=saveId]:checkbox:checked").val()=='on'){
		//alert($('#id').val());
		setCookie("offact_UserId", $('#id').val());
	} else {
		setCookie("offact_UserId", "");
	}
	
	//alert(chkCnt);
	
	//$('#loginForm').attr({action:"<%= request.getContextPath() %>/addys/login"});

	try {
		loginForm.submit();
	} catch(e) {}

}

document.onkeypress = keyPress ;

function keyPress(){
	var ieKey = window.event.keyCode ;
	if( ieKey == 13 ){
		goLogin();
	}
}



function loginInit(){

	var frm=document.loginForm;

	var cUserId = getCookie("offact_UserId");

	if( cUserId != null && trim(cUserId) != '' && cUserId != 'null' ){
		frm.id.value = getCookie("offact_UserId");
		frm.saveId.checked = true;
	}

	if(frm.id.value == "" ) {
		frm.id.focus();
	} else {
		frm.pwd.focus();
	}

}
</script>
</head>
<body onload="loginInit()">
<form method="post" id="loginForm" name="loginForm"  role="form" action="<%= request.getContextPath() %>/addys/login">
<input type="hidden" id="sClientIP" name="sClientIP" value="<%=sClientIP%>" >
<table width="100%" height="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" valign="middle"><table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td height="537" align="center" valign="top" background="<%= request.getContextPath() %>/images/login/bg.gif" style="padding:36px 0 0 0"><table width="605" cellspacing="0" cellpadding="0">
					<tr>
						<td><img src="<%= request.getContextPath() %>/images/login/login_img01.png" width="605" height="97"></td>
				  </tr>
					<tr>
						<td height="82"><img src="<%= request.getContextPath() %>/images/login/login_img02.png" width="605" height="82"></td>
				   </tr>
					<tr>
						<td><table width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td width="263" height="112"><img src="<%= request.getContextPath() %>/images/login/login_img03.png" width="263" height="112"></td>
				                <td valign="top" background="<%= request.getContextPath() %>/images/login/login_img04.png"><table width="300" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" cellspacing="0" cellpadding="0">
											<tr>
												<td width="77" height="25" class="tit_white">ID</td>
												<td><span class="tit_black_b">
													<input name="id" id="id" type="text" class="input03" style="width:140px" />
												</span></td>
											</tr>
											<tr>
											<td height="25" class="tit_white">Password</td>
											<td><span class="tit_black_b">
											<input  name="pwd" id="pwd" type="password" class="input03" style="width:140px" />
									</span></td>
									</tr>
									</table></td>
									<td width="85" align="right"><a href="javascript:goLogin();"><img src="<%= request.getContextPath() %>/images/login/btn_signin.png" ></a></td>
						 			</tr>
										</table>
											<table width="300" cellspacing="0" cellpadding="0">
												<tr>
												<td height="16"></td>
												</tr>
												<tr>
												<td height="1px" bgcolor="707070"></td>
												</tr>
												<tr>
													<td align="right" style="padding:11px 0 0 0"><input type="checkbox" name="saveId" id="saveId" style="margin-bottom:3px">
		                                            <img src="<%= request.getContextPath() %>/images/login/text_rememberme.png" align="absmiddle"></a></td>
											  </tr>
									  </table></td>
									</tr>
						</table></td>
					</tr>
					<tr>
						<td><img src="<%= request.getContextPath() %>/images/login/login_img05.png" width="605" height="62"></td>
				  </tr>
			  </table></td>
			</tr>
		</table></td>
	</tr>
</table>
 </form>
</body>
</html>
