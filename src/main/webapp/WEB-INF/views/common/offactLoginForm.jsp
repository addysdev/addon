<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
	<link href="<%= request.getContextPath() %>/css/issue_style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
	<script>

	//login 처리
	var goLogin =  function() {
	 
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		
		//alert('id:'+id);
		//alert('pw:'+pwd);
	
		//$('#loginForm').attr({action:"<%= request.getContextPath() %>/offact/login"});
		
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
	
	
	 function statusChangeCallback(response) {

		//alert(response.status);
	    console.log('statusChangeCallback');
	    console.log(response);
	    // The response object is returned with a status field that lets the
	    // app know the current login status of the person.
	    // Full docs on the response object can be found in the documentation
	    // for FB.getLoginStatus().
	    if (response.status === 'connected') {
	      // Logged into your app and Facebook.
	      
	         FB.api('/me', function(user) {  

		      fbname = user.name;
		      //alert(user.id); 
		      //alert(user.name); 
		      //alert(user.email); 

		    });  
	      
	      var frm=document.loginForm;
	      frm.id.value='system';
	      frm.pwd.value='1';

	      goLogin();
	      
	    } else if (response.status === 'not_authorized') {
	      // The person is logged into Facebook, but not your app.
	      document.getElementById('status').innerHTML = 'Please log ' +
	        'into this app.';
	    } else {
	      // The person is not logged into Facebook, so we're not sure if
	      // they are logged into this app or not.
	      document.getElementById('status').innerHTML = 'Please log ' +
	        'into Facebook.';
	    }
	  }

	  // This function is called when someone finishes with the Login
	  // Button.  See the onlogin handler attached to it in the sample
	  // code below.
	  function checkLoginState() {
	    FB.getLoginStatus(function(response) {
	      statusChangeCallback(response);
	    });
	  }

	  window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '528679613946518',
	      xfbml      : true,
	      version    : 'v2.3'
	    });
	  };

	  (function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "//connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));

	</script>
  </head>

  <body>
    <div class="container">
      <h2>offact login</h2>
      <form  id="loginForm" name="loginForm"  role="form" action="<%= request.getContextPath() %>/offact/login">
        <div class="form-group">
          <label for="email">Id:</label>
          <input type="id" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <div class="form-group">
          <label for="pwd">Password:</label>
          <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password">
        </div>
       <button type="submit" class="btn btn-default" onclick="goLogin()">Submit</button>
      </form>
      <div class="checkbox">
      <fb:login-button scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button>
	  <div id="status">
	  </div>
      </div>
      <br>
      <div
	  class="fb-like"
	  data-share="true"
	  data-width="450"
	  data-show-faces="true">
	</div>
    </div>
  </body>
</html>