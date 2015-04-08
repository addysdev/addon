<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery-ui-1.10.3.custom.css" />
	<!-- Latest compiled JavaScript -->
	<script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/jquery-1.9.1.js"></script>
	<script src="<%= request.getContextPath() %>/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script>

	//login 처리
	var goLogin =  function() {
	 
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		
		//alert('id:'+id);
		//alert('pw:'+pwd);
	
		$('#loginForm').attr({action:"<%= request.getContextPath() %>/addys/login"});
		
		try {
			loginForm.submit();
		} catch(e) {}
	};
	
	function doAjaxTest() {
		
		var id = $('#id').val();
		var pwd = $('#pwd').val();

		$.ajax({
			type : "GET",
			url : "<%= request.getContextPath() %>/addys/addysCheck",
			data : "id=" + id + "&pwd=" + pwd,
			success : function(response) {
				if(response !=""){
					alert(response);
					if(response=='1'){
						alert('true');
					}else{
						alert('false');
					}
				}
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	jQuery.ajaxSetup({
	    'headers' : {
	        'cache-control' : "no-cache"
	    },
	    'cache' : true
	});
	
	$.ajaxSetup({ cache: false });
	
	</script>
  </head>

  <body>
    <div class="container">
      <h2>addys login</h2>
      <form method="post" id="loginForm" name="loginForm"  role="form" >
        <div class="form-group">
          <label for="email">Id:</label>
          <input type="id" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password">
        </div>
        <div class="checkbox">
          <label><input type="checkbox" onChange="doAjaxTest()"> Remember me</label>
        </div>
        <button type="submit" class="btn btn-default" onclick="goLogin()">Submit</button>
      </form>
    </div>
  </body>
</html>