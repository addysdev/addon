<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//사용자 수정
		function fcUserManage_modify(){

			var frm=document.userModifyForm;

			if(frm.password.value!=frm.regPassword.value){
				frm.pw_modifyYn.value='Y';
			}else{
				frm.pw_modifyYn.value='N';
			}

	    	if (confirm('비밀번호를 변경 하시겠습니까?')){ 

		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/manage/usermodify",
		           data:$("#userModifyForm").serialize()+"&workCode=CM003",
		           success: function(result) {

						if(result=='1'){
							 alert('비밀번호 변경을 성공했습니다.');
						} else{
							 alert('비밀번호 변경을 실패했습니다.');
						}
						
						$('#passwordModify').dialog('close');
						
						
		           },
		           error:function(){
		        	   
		        	   alert('비밀번호 변경을 실패했습니다.');
		        	   $('#passwordModify').dialog('close');
		           }
		    });
		    
	    	}
		}
		
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form class="form-inline" role="form" commandName="userVO" id="userModifyForm" name="userModifyForm" method="post" action="">
      <input type="hidden" id="updateUserId" name="updateUserId" value="${userVO.updateUserId}" >
      <input type="hidden" id="pw_modifyYn" name="pw_modifyYn" value="N" >
	    <div class="form-group">
		    <table class="table table-bordered" >
			 	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >사용자ID</th>
		          <th class='text-left'  width="250px"  ><c:out value="${userVO.userId}"></c:out></th>
		          <input type="hidden" id="userId" name="userId" value="${userVO.userId}" >
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >사용자명</th>
		          <th class='text-left'><c:out value="${userVO.userName}"></c:out></th>
		          <input type="hidden" id="userName" name="userName" value="${userVO.userName}" >
		      	</tr>
		      	<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >PASSWORD</th>
		          <th class='text-left'><input type="password" class="form-control" id="password" name="password" maxlength="50"  tabindex="2" value="${userVO.password}" ></th>
		          <input type="hidden" id="regPassword" name="regPassword" value="${userVO.password}" > 
		      	</tr>
		      	<input type="hidden" id="groupId" name="groupId" value="${userVO.groupId}" ></th>
		      	<input type="hidden" id="authId" name="authId" value="${userVO.authId}" ></th>
		      	<input type="hidden" id="auth" name="auth" value="${userVO.auth}" ></th>
		      	<input type="hidden" id="email" name="email" value="${userVO.email}" ></th>
		      	<input type="hidden" id="officePhone" name="officePhone" value="${userVO.officePhone}" ></th>
		      	<input type="hidden" id="mobliePhone" name="mobliePhone" value="${userVO.mobliePhone}" ></th>
		      	<input type="hidden" id="smsYn" name="smsYn" value="${userVO.smsYn}" ></th>
			  </table>
        	<td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_modify()">수정</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>

 

