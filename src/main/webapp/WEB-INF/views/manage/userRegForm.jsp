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
	    <th>
   		  <td>
			<label for="userId"><h5><strong><font style="color:#FF9900">사용자ID : </font></strong></h5></label>
			<input type="text" class="form-control" id="userId" name="userId"  tabindex="1" value="">
			<br>
			<label for="password"><h5><strong><font style="color:#FF9900">PASSWORD : </font></strong></h5></label>
			<input type="password" class="form-control" id="password" name="password" tabindex="2" value="" >
			<br>
			<input type="hidden" id="regPassword" name="regPassword" value="" >      
			<label for="userName"><h5><strong><font style="color:#FF9900">사용자명 : </font></strong></h5></label>
			<input  type="text" class="form-control" id="userName"  name="userName" tabindex="3" value="">
			<br>
	        <label for="groupId"><h5><strong><font style="color:#FF9900">조직 : </font></strong></h5></label>
	    	<select class="form-control" title="지점정보" id="groupId" name="groupId" value="">
                <c:forEach var="groupVO" items="${group_comboList}" >
                	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                </c:forEach>
            </select>
            <input type="hidden" id="authId" name="authId" value="G00000" >
            <br>
	    	<label for="auth"><h5><strong><font style="color:#FF9900">권한 : </font></strong></h5></label>
			<select class="form-control" title="관리권한" id="auth" name="auth" value="">
                <c:forEach var="codeVO" items="${code_comboList}" >
                	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                </c:forEach>
       		</select>
       		<br>
	    	<label for="email"><h5><strong><font style="color:#FF9900">email : </font></strong></h5></label>
	    	<input type="text" class="form-control" id="email" name="email" tabindex="7" value="">
	    	<br>
	    	<label for="officePhone"><h5><strong><font style="color:#FF9900">officePhone : </font></strong></h5></label>           
	    	<input type="text" class="form-control" id="officePhone" name="officePhone" tabindex="8" value=""> 
	    	<br>  
	    	<label for="mobliePhone"><h5><strong><font style="color:#FF9900">mobliePhone : </font></strong></h5></label>             
	    	<input type="text" class="form-control" id="mobliePhone" name="mobliePhone" tabindex="9" value="">                            
          </td>
			</th>
			<br>
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
 

