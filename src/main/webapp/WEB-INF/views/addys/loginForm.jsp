<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS-->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">

	<script>

	//login 처리
	var goLogin =  function() {
	 
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		
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
		
		$('#loginForm').attr({action:"<%= request.getContextPath() %>/addys/login"});
		/*
		try {
			loginForm.submit();
		} catch(e) {}
		*/
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
	
	function init(){
		
		$('#id').focus(1); 
		
		var cUserId = getCookie("offact_UserId");
		
		//alert(cUserId);

		if( cUserId != null && trim(cUserId) != '' && cUserId != 'null' ){
			//$('#id').val=cUserId;
			//$("input[name=saveId]:checkbox:checked").val==on;
			document.loginForm.id.value=cUserId;
			document.loginForm.saveId.checked=true;
			//$("#saveId").is(":checked");
			//$("input:checkbox[id='saveId']").prop("checked",'');
		}
	}
	</script>
  </head>

  <body onload="init()">
    <div class="container">
      <h3><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-plus-sign"></span> Addys Purchase Management System</font></strong></h3>
      <form method="post" id="loginForm" name="loginForm"  role="form" >
        <div class="form-group">
          <label for="id">Id:</label>
          <input type="id" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password">
        </div>
        <div class="checkbox">
          <label><input type="checkbox" id="saveId" name="saveId"> Remember me</label>
        </div>
       <button type="submit" class="btn btn-default" onclick="goLogin()">Submit</button>
      </form>
    </div>
  </body>
</html>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>