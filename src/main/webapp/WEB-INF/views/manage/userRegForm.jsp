<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//사용자 등록
		function fcUserManage_regist(){
			
			var frm=document.userRegistForm;
			
			if(frm.userId.value==''){
				alert('사용자 아이디를 입력하세요');
				return;
			}
			
			if(frm.password.value==''){
				alert('사용자 패스워드를 입력하세요');
				return;
			}
			
			if(frm.userName.value==''){
				alert('사용자명을 입력하세요');
				return;
			}
			
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
      <form:form class="form-inline" commandName="userVO" id="userRegistForm" name="userRegistForm" method="post" action="">
      <input type="hidden" id="createUserId" name="createUserId" value="${userVO.createUserId}" >
	    <div class="form-group">
		    <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >사용자ID</th>
	          <th class='text-left'  width="250px"  ><input type="text" class="form-control" id="userId" name="userId" maxlength="10"  tabindex="1" value=""></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >PASSWORD</th>
	          <th class='text-left'><input type="password" class="form-control" id="password" name="password" maxlength="50"  tabindex="2" value="" ></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >사용자명</th>
	          <th class='text-left'><input  type="text" class="form-control" id="userName"  name="userName" maxlength="25" tabindex="3" value=""></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >조직</th>
	          <th class='text-left'>	    	
	          	<select class="form-control" title="지점정보" id="groupId" name="groupId" value="" tabindex="4">
	                <c:forEach var="groupVO" items="${group_comboList}" >
	                	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
	                </c:forEach>
	            </select>
	            <input type="hidden" id="authId" name="authId" value="G00000" ></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >권한</th>
	          <th class='text-left'>
	          	<select class="form-control" title="관리권한" id="auth" name="auth" value="" tabindex="5">
	                <c:forEach var="codeVO" items="${code_comboList}" >
	                	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
	                </c:forEach>
	       		</select></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >email</th>
	          <th class='text-left'><input type="text" class="form-control" id="email" name="email" maxlength="25"  tabindex="6" value=""></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >officePhone</th>
	          <th class='text-left'><input type="text" class="form-control" id="officePhone" name="officePhone" tabindex="20" tabindex="7" value=""> </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >mobilePhone</th>
	          <th class='text-left'><input type="text" class="form-control" id="mobliePhone" name="mobliePhone" tabindex="20" tabindex="8" value="">   </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >sms수신여부</th>
	          <th class='text-left'>
	          	<select class="form-control" title="smsYn" id="smsYn" name="smsYn" value="N" tabindex="9">
	                <option value="N">미수신</option>
	                <option value="Y">수신</option>
	       		</select>    </th>
	      	</tr>
		  </table>
          <td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_regist()">저장</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>
<script>

//$('#userId').focus(1); 
document.userRegistForm.userId.focus();
</script>
 

