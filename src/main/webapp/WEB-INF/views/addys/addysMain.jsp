<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<!DOCTYPE html>
<html>
 <head>
    <script>
    //onload
	$(document).ready(function () {
		var strUserId = $('#strUserId').val();
	 	//alert("[strUserId]"+strUserId);
	 	
	 	var strUserName = '${strUserName}';
	 	//alert("[strUserName]"+strUserName);
	 	
	});
    </script>
  </head>
  <body>
  	<form method="post" id="mainForm" name="mainForm"  role="form" >
  	<input type="hidden" id="strUserId" name="strUserId" value="${strUserId}">
    </form>
   <div class="container">
	  <h2>메인화면</h2>
	  <p>메인 테이블 데이타</p>            
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th>Firstname</th>
	        <th>Lastname</th>
	        <th>Email</th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td>John</td>
	        <td>Doe</td>
	        <td>john@example.com</td>
	      </tr>
	      <tr>
	        <td>Mary</td>
	        <td>Moe</td>
	        <td>mary@example.com</td>
	      </tr>
	      <tr>
	        <td>July</td>
	        <td>Dooley</td>
	        <td>july@example.com</td>
	      </tr>
	    </tbody>
	  </table>
	</div>
	<div class="container">
	  <ul class="pagination">
	    <li><a href="#">1</a></li>
	    <li class="active"><a href="#">2</a></li>
	    <li><a href="#">3</a></li>
	    <li><a href="#">4</a></li>
	    <li><a href="#">5</a></li>
	  </ul>
	</div>

  </body>
</html>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
