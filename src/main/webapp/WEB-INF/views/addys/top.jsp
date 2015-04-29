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
	
%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS-->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<!-- lastest ver -->
	<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css" rel="stylesheet">-->
	<script>
	
	//logout 처리
	var goLogout =  function() {

		//alert('logout');
		
		$('#logoutForm').attr({action:"<%= request.getContextPath() %>/addys/logout"});
		
		try {
			logoutForm.submit();
		} catch(e) {}
	};

	</script>
	<c:set var="strUserId" value="<%=strUserId %>" />
	<c:set var="strUserName" value="<%=strUserName %>" />
	<c:set var="strGroupId" value="<%=strGroupId %>" />
	<c:set var="strGroupName" value="<%=strGroupName %>" />
	<c:set var="strAuthId" value="<%=strAuthId %>" />
	<c:set var="strAuthName" value="<%=strAuthName %>" />
	<c:set var="strExcelAuth" value="<%=strExcelAuth %>" />
	
  </head>
  <body>
  <form method="post" id="logoutForm" name="logoutForm"  role="form" >
  </form>
    <div class="container">
      <h2>addys</h2>
      <p>
        <button type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-user"></span> <%=strUserName %>(<%=strUserId %>) 
        </button>  <button type="button" class="btn btn-primary" onClick="goLogout()">logout</button>
      </p>
      <ul class="nav nav-pills" role="tablist">
        <li class="active"><a href="#">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">발주 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%= request.getContextPath() %>/order/targetmanage" >발주리스트</a></li>
            <li><a href="<%= request.getContextPath() %>/order/ordermanage" >검수리스트</a></li>                        
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">회수 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%= request.getContextPath() %>/recovery/recoverymanage">회수리스트</a></li>                    
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">관리 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%= request.getContextPath() %>/manage/usermanage">계정관리</a></li>
            <li><a href="#">조직관리</a></li>
            <li><a href="#">메뉴권한관리</a></li> 
            <li><a href="<%= request.getContextPath() %>/manage/productmanage">품목관리</a></li>
            <li><a href="<%= request.getContextPath() %>/manage/stockmanage">재고현황 관리</a></li>   
            <li><a href="<%= request.getContextPath() %>/manage/salesmanage">매출현황 관리</a></li>                            
          </ul>
        </li>
      </ul>
    </div>
  </body>
</html>
