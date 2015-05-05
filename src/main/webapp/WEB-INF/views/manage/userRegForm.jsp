<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//사용자 등록
		function fcUserManage_regist(){

		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/manage/userreg",
		           data:$("#userRegistForm").serialize(),
		           success: function(result) {

						if(result=='1'){
							 alert('사용자 등록을 성공했습니다.');
						} else{
							 alert('사용자 등록에 실패했습니다.');
						}
						
						$('#userManageRegist').dialog('close');
						fcUserManage_listSearch();
						
		           },
		           error:function(){
		        	   
		        	   alert('사용자 등록에 실패했습니다.');
		        	   $('#userManageRegist').dialog('close');
		           }
		    });
		}
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form commandName="userVO" id="userRegistForm" name="userRegistForm" method="post" action="">
      <input type="hidden" id="createUserId" name="createUserId" value="${userVO.createUserId}" >
	    <div class="form-group">
	    <th>
   		  <td>
			  아이디 : <input type="text" class="form-control" id="userId" name="userId" tabindex="1">
			  패스워드: <input type="password" class="form-control" id="password" name="password" tabindex="2" >
                       사용자명: <input  type="text" class="form-control" id="userName"  name="userName" tabindex="3">
	    	  조직: <input  type="text" class="form-control" id="groupId"  name="groupId" tabindex="4">
	    	  권한 : <input type="text" class="form-control" id="authId" name="authId" tabindex="5">
	    	 excel 권한 : <input type="text" class="form-control" id="excelAuth" name="excelAuth" tabindex="6"> 
	    	 email : <input type="text" class="form-control" id="email" name="email" tabindex="7">           
	    	 officePhone : <input type="text" class="form-control" id="officePhone" name="officePhone" tabindex="8">           
	    	 mobliePhone : <input type="text" class="form-control" id="mobliePhone" name="mobliePhone" tabindex="9">                            
          </td>
			</th>
			<br>
            <td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_regist()">저장</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>

 

