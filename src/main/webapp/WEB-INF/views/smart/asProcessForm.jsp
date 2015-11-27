<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//AS 등록
		function fAs_proc(){
			
			var frm=document.asProcForm;
			
			if(frm.asResult.value==''){
				alert('AS처리 내용을 입력하세요');
				frm.asResult.focus();
				return;
			}

			if (confirm('AS 처리를 진행 하시겠습니까?\n처리된 내용은 SMS로 고객 문자 발송됩니다.')){ 
			
			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/asprocess",
			           data:$("#asProcForm").serialize(),
			           success: function(result) {
	
							if(result>0){
								 alert('AS처리를 완료했습니다.');
							} else{
								 alert('AS처리를 실패했습니다.');
							}
							
							$('#asProcessForm').dialog('close');
							fcAs_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('AS처리를 실패했습니다.');
			        	   $('#asProcessForm').dialog('close');
			           }
			    });
			}
		}
		
		function asStateChekc(){

			var idx='${asVO.idx}';
			
			if('${asVO.asState}'!='03' && '${strUserId}'=='${asVO.asStartUserId}'){
			
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/asstateupdate?asState=01&idx="+idx,
			           success: function(result) {
	
							if(result=='1'){
								//성공
							} else{
								 alert('AS상태 변경을 실패했습니다.\n관리자에게 문의하세요');
								 $('#asProcessForm').dialog('close');
								 fcAs_listSearch();
							}
	
			           },
			           error:function(){
			        	   
			        	   alert('AS상태 변경을 실패했습니다.\n관리자에게 문의하세요');
			        	   $('#asProcessForm').dialog('close');
			        	   fcAs_listSearch();
			           }
			    });
			
			}
			
		}
		
	</script>
  </head>
  <body>
	<div class="container-fluid">
      <form:form class="form-inline" commandName="asVO" id="asProcForm" name="asProcForm" method="post" action="">
      	<input type="hidden" id="idx" name="idx" value="${asVO.idx}" >
      	<input type="hidden" id="asState" name="asState" value="03" >
      	<input type="hidden" id="customerKey" name="customerKey" value="${asVO.customerKey}" >
	    <div class="form-group">
		    <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >고객 핸드폰번호</th>
	          <th class='text-left'  width="250px"  >
	          <div class="form-inline">
	          <input type="text" class="form-control"  maxlength="10"  tabindex="1" value="${asVO.customerKey}" disabled>
    	      </div> 
	          </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >고객명</th>
	          <th class='text-left'><input type="text" class="form-control" id="customerName" name="customerName" maxlength="50"  tabindex="2" value="${asVO.customerName}" disabled></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >AS요청일자</th>
	          <th class='text-left'><input  type="text" class="form-control" id="asDateTime"  name="asDateTime" maxlength="25" tabindex="3" value="${asVO.asStartDateTime}" disabled></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >AS내용</th>
				<th class='text-left'><textarea style='width:210px;height:110px;ime-mode:active;' row="6" class="form-control" id="as" maxlength="200" name="as"  value="${asVO.asDetail}"  disabled >${asVO.asDetail}</textarea></th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>AS답변</th>
	            <c:choose>
		    		<c:when test="${asVO.asState=='03'}">
                		 <th class='text-left'><textarea style='width:210px;height:110px;ime-mode:active;' row="6" class="form-control" id="asResult" maxlength="200" name="asResult"  value="${asVO.asResult}" disabled >${asVO.asResult}</textarea></th>
                	</c:when>
					<c:otherwise>
					     <th class='text-left'><textarea style='width:210px;height:110px;ime-mode:active;' row="6" class="form-control" id="asResult" maxlength="200" name="asResult"  value="${asVO.asResult}" >${asVO.asResult}</textarea></th>
                   </c:otherwise>
				</c:choose>
	      	</tr>
		  </table>
		   <c:if test="${asVO.asState!='03'}">
          <td><button type="button" class="btn btn-primary" onClick="javascript:fAs_proc()">처리</button></td>
           </c:if>
	    </div>
	  </form:form>
	</div>
  </body>
</html>
<script>
/*	
if('${asVO.asState}'=='02' && '${strUserId}'!='${asVO.asStartUserId}'){
	
	alert('본 AS건은 현재 ${asVO.asStartUserName}(${asVO.asStartUserId})님이 접수중인 상태입니다.\n처리완료후 확인하세요.');
	$('#asProcessForm').dialog('close');
	fcAs_listSearch();
	
}else if('${asVO.asState}'=='01'){
	
	var asNo='${asVO.asNo}';

	$.ajax({
        type: "POST",
        async:false,
           url:  "<%= request.getContextPath() %>/smart/asstateupdate?asState=02&asNo="+asNo,
           success: function(result) {

				if(result=='1'){
					//성공
				} else{
					 alert('AS상태 변경을 실패했습니다.\n관리자에게 문의하세요');
					 $('#asProcessForm').dialog('close');
					 fcAs_listSearch();
				}

           },
           error:function(){
        	   
        	   alert('AS상태 변경을 실패했습니다.\n관리자에게 문의하세요');
        	   $('#asProcessForm').dialog('close');
        	   fcAs_listSearch();
           }
    });

	
}

document.asProcForm.asResult.focus();

*/	
</script>
 

