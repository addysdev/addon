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
	
	String strUserDeptCode= (String)session.getAttribute("strUserDeptCode");	
	String strUserGroupCode= (String)session.getAttribute("strUserGroupCode");
	String strCompanyGbn= (String)session.getAttribute("strCompanyGbn");

	String strUserDeptName= (String)session.getAttribute("strUserDeptName");	
	String strUserGroupName= (String)session.getAttribute("strUserGroupName");
	String strCompanyGbnName= (String)session.getAttribute("strCompanyGbnName");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery-ui-1.10.3.custom.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
	<!-- Latest compiled JavaScript -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
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
	
	<c:set var="strUserDeptCode" value="<%=strUserDeptCode %>" />
	<c:set var="strUserGroupCode" value="<%=strUserGroupCode %>" />
	<c:set var="strCompanyGbn" value="<%=strCompanyGbn %>" />
	
	<c:set var="strUserDeptName" value="<%=strUserDeptName %>" />
	<c:set var="strUserGroupName" value="<%=strUserGroupName %>" />
	<c:set var="strCompanyGbnName" value="<%=strCompanyGbnName %>" />
	
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
            <li><a href="#">재고현황관리</a></li>
            <li><a href="#">발주리스트</a></li>
            <li><a href="#">검수리스트</a></li>                        
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">회수 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">회수리스트</a></li>
            <li><a href="#">회수완료리스트</a></li>                       
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">관리 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%= request.getContextPath() %>/addys/usermanage">계정관리</a></li>
            <li><a href="#">조직관리</a></li>
            <li><a href="#">메뉴권한관리</a></li> 
            <li><a href="#">Master정보관리</a></li>                         
          </ul>
        </li>
      </ul>
    </div>
  </body>
</html>
