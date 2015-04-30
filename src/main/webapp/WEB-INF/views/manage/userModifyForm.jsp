<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//사용자 수정
		function fcUserManage_modify(){

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
   <div class="container">
      <form:form commandName="userVO" id="userModifyForm" name="userModifyForm" method="post" action="">
      <input type="hidden" id="updateUserId" name="updateUserId" value="${userVO.updateUserId}" >
	    <div class="form-group">
	    <th>
   		  <td>
			  아이디 : <input type="text" class="form-control" id="userId" name="userId" tabindex="1" value="${userVO.userId}">
			  패스워드: <input type="password" class="form-control" id="password" name="password" tabindex="2" value="${userVO.password}" >
	      </td>
          <td>
                              사용자명: <input  type="text" class="form-control" id="userName"  name="userName" tabindex="3" value="${userVO.userName}">
	    	  조직: <input  type="text" class="form-control" id="groupId"  name="groupId" tabindex="4" value="${userVO.groupId}">
	    	  권한 : <input type="text" class="form-control" id="authId" name="authId" tabindex="5" value="${userVO.authId}">
	    	 excel 권한 : <input type="text" class="form-control" id="excelAuth" name="excelAuth" tabindex="6" value="${userVO.excelAuth}"> 
	    	 email : <input type="text" class="form-control" id="email" name="email" tabindex="7" value="${userVO.email}">           
	    	 officePhone : <input type="text" class="form-control" id="officePhone" name="officePhone" tabindex="8" value="${userVO.officePhone}">           
	    	 mobliePhone : <input type="text" class="form-control" id="mobliePhone" name="mobliePhone" tabindex="9" value="${userVO.mobliePhone}">                            
          </td>
			</th>
			<br>
            <td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_modify()">수정</button></td>
	    </div>
	  </form:form>
	</div>
  </body>
</html>

 

