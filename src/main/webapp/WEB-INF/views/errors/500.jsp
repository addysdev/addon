<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="title" content="" />
<meta name="author" content=""/>
<meta name="keywords" content=""/>
<meta name="description" content="" />
<link type="text/css" href="<%= request.getContextPath() %>/css/common.css" rel="stylesheet"  charset="utf-8" />
<link type="text/css" href="<%= request.getContextPath() %>/css/layout.css" rel="stylesheet"  charset="utf-8" />
<link type="text/css" href="<%= request.getContextPath() %>/css/sub.css" rel="stylesheet"  charset="utf-8" />
<link type="text/css" href="<%= request.getContextPath() %>/css/default.css" rel="stylesheet"  charset="utf-8" />
<link type="text/css" href="<%= request.getContextPath() %>/css/content.css" rel="stylesheet"  charset="utf-8" />
<script language="javascript" src='<%= request.getContextPath() %>/js/jquery-1.9.0.js'></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/all.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	
});
</script>

</head>
<body class="bgcolor">
<div id="popup_wrap"  class="error_area">
	<div id="popup_area" class="error_con">
		<h1><img src="<%= request.getContextPath() %>/images/layout/txt_error.gif" alt="ERROR" /></h1>
		<h2><img src="<%= request.getContextPath() %>/images/layout/txt_error_02.gif" alt="시스템 오류가 발생했습니다." /></h2>
		<ul>
			<li><img src="<%= request.getContextPath() %>/images/common/bu_square.gif" alt="" /> 일시적인 장애로 시스템 오류가 발생했습니다.</li>
			<li><img src="<%= request.getContextPath() %>/images/common/bu_square.gif" alt="" /> 관리자에게 문의해주세요.
				<!-- ul>
			        <li>제공되던 서비스가 중단된 경우</li>
			        <li>URL주소에 백슬래시나 다중 따옴표등의 크래킹 시도가 감지된 경우</li>
			        <li>기타 부정접근시도가 감지된 경우</li>
		        </ul -->
			</li>
		</ul>
		<p><button type="button" class="img_button" onclick="location.href='<%= request.getContextPath() %>/index'"><img src="<%= request.getContextPath() %>/images/common/btn_back.gif" alt="돌아가기" /></button></p>
	</div>
</div>
</body>
</html>