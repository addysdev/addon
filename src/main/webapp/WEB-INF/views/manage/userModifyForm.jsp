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

		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/manage/usermodify",
		           data:$("#userModifyForm").serialize(),
		           success: function(result) {

						if(result=='1'){
							 alert('사용자정보 수정을 성공했습니다.');
						} else{
							 alert('사용자정보 수정에 실패했습니다.');
						}
						
						$('#userManageModify').dialog('close');
						fcUserManage_listSearch();
						
		           },
		           error:function(){
		        	   
		        	   alert('사용자 등록에 실패했습니다.');
		        	   $('#userManageModify').dialog('close');
		           }
		    });
		}
		
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form class="form-inline" role="form" commandName="userVO" id="userModifyForm" name="userModifyForm" method="post" action="">
      <input type="hidden" id="updateUserId" name="updateUserId" value="${userVO.updateUserId}" >
      <input type="hidden" id="pw_modifyYn" name="pw_modifyYn" value="N" >
	    <div class="form-group">
	    <th>
   		  <td>
   		    <label for="userId"><h5><strong><font style="color:#FF9900">사용자ID : </font></strong></h5></label>
			<input type="text" class="form-control"  tabindex="1" disabled value="${userVO.userId}">
			<input type="hidden" id="userId" name="userId" value="${userVO.userId}" >
			<br>
			<label for="password"><h5><strong><font style="color:#FF9900">PASSWORD : </font></strong></h5></label>
			<input type="password" class="form-control" id="password" name="password" tabindex="2" value="${userVO.password}" >
			<input type="hidden" id="regPassword" name="regPassword" value="${userVO.password}" > 
			<br>     
			<label for="userName"><h5><strong><font style="color:#FF9900">사용자명 : </font></strong></h5></label>
			<input  type="text" class="form-control" id="userName"  name="userName" tabindex="3" value="${userVO.userName}">
			<br>
	        <label for="groupId"><h5><strong><font style="color:#FF9900">조직 : </font></strong></h5></label>
	    	<select class="form-control" title="지점정보" id="groupId" name="groupId" value="${userVO.groupId}">
                <c:forEach var="groupVO" items="${group_comboList}" >
                	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                </c:forEach>
            </select>
            <input type="hidden" id="authId" name="authId" value="${userVO.authId}" >
            <br>
	    	<label for="auth"><h5><strong><font style="color:#FF9900">권한 : </font></strong></h5></label>
			<select class="form-control" title="관리권한" id="auth" name="auth" value="${userVO.auth}">
                <c:forEach var="codeVO" items="${code_comboList}" >
                	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                </c:forEach>
       		</select>
       		<br>
	    	<label for="email"><h5><strong><font style="color:#FF9900">email : </font></strong></h5></label>
	    	<input type="text" class="form-control" id="email" name="email" tabindex="7" value="${userVO.email}">
	    	<br>
	    	<label for="officePhone"><h5><strong><font style="color:#FF9900">officePhone : </font></strong></h5></label>           
	    	<input type="text" class="form-control" id="officePhone" name="officePhone" tabindex="8" value="${userVO.officePhone}">  
	    	<br> 
	    	<label for="mobliePhone"><h5><strong><font style="color:#FF9900">mobliePhone : </font></strong></h5></label>             
	    	<input type="text" class="form-control" id="mobliePhone" name="mobliePhone" tabindex="9" value="${userVO.mobliePhone}">    
	    	<br> 
	    	<label for="smsYn"><h5><strong><font style="color:#FF9900">sms 수신여부 : </font></strong></h5></label>
			<select class="form-control" title="smsYn" id="smsYn" name="smsYn" value="${userVO.smsYn}">
                <option value="N">미수신</option>
                <option value="Y">수신</option>
       		</select>                       
          </td>
		</th>
		<br><br>
        <td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_modify()">수정</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>
<script>

document.userModifyForm.groupId.value='${userVO.groupId}';
document.userModifyForm.auth.value='${userVO.auth}';
document.userModifyForm.smsYn.value='${userVO.smsYn}';

</script>

 

