<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<head>
<title>바코드 검수</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<style>
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
	
</style>
<!-- Latest compiled and minified CSS-->
<link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">

<!-- Latest compiled JavaScript--> 
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/locales/bootstrap-datetimepicker.kr.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/errorMsg.js"></script>
<SCRIPT>
function fcfCode_close(){

	this.close();
}

</SCRIPT>
</head>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="barCodeVO" id="barCodeForm" name="barCodeForm" method="post" action="" >
<br>
		<table class="table table-bordered tbl_type" >
	     <colgroup>
	      <col width="70px" >
	      <col width="70px" >
	     </colgroup>
	     <tr>
	     	<td style="background-color:#E6F3FF" class='text-center'>미일치 바코드 리스트</td>
	     	<td class='text-right'><span id="checkTCnt" style="color:red">${fCnt} 건</span></td>
	     </tr>
     </table>  
<p><textarea style='height:220px;ime-mode:active;' class="form-control" id="barcode_list" name="barcode_list"  value=""  placeholder=""  />
${totalFMsg}</textarea></p>
<button id="deferpopclosebtn" type="button" class="btn btn-default" onClick="fcfCode_close()">닫기</button> 
</form:form>
</div>
</body>
