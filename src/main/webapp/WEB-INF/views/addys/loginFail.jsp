<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS-->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
    <!-- Latest compiled JavaScript--> 
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.js"></script>
  </head>

  <body>
    <div class="container">
      <h1>Login Fail (등록된 사용자가 아닙니다.)</h1>
      <h4><a href="<%= request.getContextPath() %>/addys/loginform" ><-뒤로가기</a></h4>
    </div>
  </body>
</html>
