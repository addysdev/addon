<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//상담 등록
		function fCounsel_proc(){
			
			var frm=document.counselProcForm;
			
			if(frm.counselResult.value==''){
				alert('상담처리 내용을 입력하세요');
				frm.counselResult.focus();
				return;
			}

			if (confirm('상담 처리를 진행 하시겠습니까?\n처리된 내용은 SMS로 고객 문자 발송됩니다.')){ 
			
			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/counselprocess",
			           data:$("#counselProcForm").serialize(),
			           success: function(result) {
	
							if(result>0){
								 alert('상담처리를 완료했습니다.');
							} else{
								 alert('상담처리를 실패했습니다.');
							}
							
							$('#counselProcessForm').dialog('close');
							fcCounsel_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('상담처리를 실패했습니다.');
			        	   $('#counselProcessForm').dialog('close');
			           }
			    });
			}
		}
		
		function counsetStateChekc(){

			var idx='${counselVO.idx}';
			
			if('${counselVO.counselState}'!='03' && '${strUserId}'=='${counselVO.stateUpdateUserId}'){
			
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/counselstateupdate?counselState=01&idx="+idx,
			           success: function(result) {
	
							if(result=='1'){
								//성공
							} else{
								 alert('상담상태 변경을 실패했습니다.\n관리자에게 문의하세요');
								 $('#counselProcessForm').dialog('close');
								 fcCounsel_listSearch();
							}
	
			           },
			           error:function(){
			        	   
			        	   alert('상담상태 변경을 실패했습니다.\n관리자에게 문의하세요');
			        	   $('#counselProcessForm').dialog('close');
			        	   fcCounsel_listSearch();
			           }
			    });
			
			}
			
		}
		
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form class="form-inline" commandName="counselVO" id="counselProcForm" name="counselProcForm" method="post" action="">
      	<input type="hidden" id="idx" name="idx" value="${counselVO.idx}" >
      	<input type="hidden" id="counselState" name="counselState" value="03" >
      	<input type="hidden" id="customerKey" name="customerKey" value="${counselVO.customerKey}" >
	    <div class="form-group">
		    <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >고객 핸드폰번호</th>
	          <th class='text-left'  width="250px"  >
	          <div class="form-inline">
	          <input type="text" class="form-control"  maxlength="10"  tabindex="1" value="${counselVO.customerKey}" disabled>
    	      </div> 
	          </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >고객명</th>
	          <th class='text-left'><input type="text" class="form-control" id="customerName" name="customerName" maxlength="50"  tabindex="2" value="${counselVO.customerName}" disabled></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >고객 ID</th>
	          <th class='text-left'><input  type="text" class="form-control" id="customerId"  name="customerId" maxlength="25" tabindex="3" value="${counselVO.customerId}" disabled></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >상담요청일자</th>
	          <th class='text-left'><input  type="text" class="form-control" id="counselDateTime"  name="counselDateTime" maxlength="25" tabindex="3" value="${counselVO.counselDateTime}" disabled></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >상담내용</th>
				<th class='text-left'><textarea style='width:210px;height:110px;ime-mode:active;' row="6" class="form-control" id="counsel" maxlength="200" name="counsel"  value="${counselVO.counsel}"  disabled >${counselVO.counsel}</textarea>
				<c:if test="${counselVO.counselImage!=null}">
					<br><a href="javascript:imageView('${counselVO.counselImage}')"><font style="color:blue">[image view]</font></a>
				 </c:if>
				</th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>상담처리</th>
	            <c:choose>
		    		<c:when test="${counselVO.counselState=='03'}">
                		 <th class='text-left'><textarea style='width:210px;height:110px;ime-mode:active;' row="6" class="form-control" id="counselResult" maxlength="200" name="counselResult"  value="${counselVO.counselResult}" disabled >${counselVO.counselResult}</textarea></th>
                	</c:when>
					<c:otherwise>
					     <th class='text-left'><textarea style='width:210px;height:110px;ime-mode:active;' row="6" class="form-control" id="counselResult" maxlength="200" name="counselResult"  value="${counselVO.counselResult}" >${counselVO.counselResult}</textarea></th>
                   </c:otherwise>
				</c:choose>
	      	</tr>
		  </table>
		   <c:if test="${counselVO.counselState!='03'}">
          <td><button type="button" class="btn btn-primary" onClick="javascript:fCounsel_proc()">처리</button></td>
           </c:if>
	    </div>
	  </form:form>
	</div>
  </body>
</html>
<script>
//alert('${strUserId}');
if('${counselVO.counselState}'=='02' && '${strUserId}'!='${counselVO.stateUpdateUserId}'){
	
	alert('본 상담건은 현재 ${counselVO.stateUpdateUserName}(${counselVO.stateUpdateUserId})님이 처리중인 상태입니다.\n처리완료후 확인하세요.');
	$('#counselProcessForm').dialog('close');
	fcCounsel_listSearch();
	
}else if('${counselVO.counselState}'=='01'){
	
	var idx='${counselVO.idx}';
	
	$.ajax({
        type: "POST",
        async:false,
           url:  "<%= request.getContextPath() %>/smart/counselstateupdate?counselState=02&idx="+idx,
           success: function(result) {

				if(result=='1'){
					//성공
				} else{
					 alert('상담상태 변경을 실패했습니다.\n관리자에게 문의하세요');
					 $('#counselProcessForm').dialog('close');
					 fcCounsel_listSearch();
				}

           },
           error:function(){
        	   
        	   alert('상담상태 변경을 실패했습니다.\n관리자에게 문의하세요');
        	   $('#counselProcessForm').dialog('close');
        	   fcCounsel_listSearch();
           }
    });
	
	
}

document.counselProcForm.counselResult.focus();
</script>
 

