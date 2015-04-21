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
		<h2><img src="<%= request.getContextPath() %>/images/layout/txt_error_01.gif" alt="íì´ì§ì¤ë¥ìëë¤." /></h2>
		<ul>
			<li><img src="<%= request.getContextPath() %>/images/common/bu_square.gif" alt="" /> ìì²­íì  íì´ì§ë¥¼ ì°¾ì ì ììµëë¤.</li>
			<li><img src="<%= request.getContextPath() %>/images/common/bu_square.gif" alt="" /> ì´ íì´ì§ë ë¤ìì ì´ì ë¡ë íìë  ì ììµëë¤.
				<ul>
					<li>ì ê³µëë ìë¹ì¤ê° ì¤ë¨ë ê²½ì°</li>
			       	<li>URLì£¼ìì ë°±ì¬ëìë ë¤ì¤ ë°ì´íë±ì í¬ëí¹ ìëê° ê°ì§ë ê²½ì°</li>
			        <li>ê¸°í ë¶ì ì ê·¼ìëê° ê°ì§ë ê²½ì°</li>
				</ul>
			</li>
		</ul>
		<p><button type="button" class="img_button" onclick="location.href='<%= request.getContextPath() %>/index'"><img src="<%= request.getContextPath() %>/images/common/btn_back.gif" alt="ëìê°ê¸°" /></button></p>
	</div>
</div>
</body>
</html>