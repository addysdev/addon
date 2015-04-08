<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
    <script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/jquery-1.9.1.js"></script>
	<script src="<%= request.getContextPath() %>/js/jquery-ui-1.10.3.custom.min.js"></script>
  </head>

  <body>
    <div class="container">
      <h1>Login Fail (등록된 사용자가 아닙니다.)</h1>
      <h4><a href="<%= request.getContextPath() %>/addys/loginform" ><-뒤로가기</a></h4>
    </div>
  </body>
</html>
