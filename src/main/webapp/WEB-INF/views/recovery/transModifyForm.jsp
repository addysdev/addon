<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <style>

 .thead { height:32px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:200px; .height:190px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
<SCRIPT>
//운송정보 수정
function fcTrans_update(){

	var dm='${deliveryMethod}';
	
	if(dm=='01'){
	
		if($("#transportNo").val()==''){
			alert('운송장 번호를 입력하세요!');
			$("#transportNo").focus();
			return;
		}
	
		if (confirm('운송정보를 수정 하시겠습니까?')){ 
		
		commonDim(true);
		
		    $.ajax({
		        type: "POST",
		           url:  "<%= request.getContextPath() %>/recover/transupdate",
		                data:$("#transForm").serialize(),
		           success: function(result) {
		               commonDim(false);
		               
		               if(result!='-1'){
		            	   
		            	   //var transArr=result.split('^');
		            	   
		            	   //document.all('transCompanyId').innerText=encodeURIComponent(transArr[0]);
		            	   //document.all('transNoId').innerText=transArr[2];
		            	   		               
			               //document.recoveryDetailForm.transurl_Modify.value=transArr[1];
			       		   //document.recoveryDetailForm.transportNo_Modify.value=transArr[2];
 
		            	   document.all('transCompanyId').innerText=document.transForm.transportCode.options[document.transForm.transportCode.selectedIndex].text;
		            	   document.all('transNoId').innerText=document.transForm.transportNo.value;
	
		               }else{
		            	   
		            	   alert('운송정보 수정에 실패했습니다.');
		               }
		               
		               
		               $("#transManage").dialog('close');
		            
		           },
		           error:function() {
		               commonDim(false);
		               
		               alert('운송정보 수정에 실패했습니다.');
		               
		               $("#transManage").dialog('close');
		           }
		    });
		}
	
	}else{
		
		if($("#quickCharge").val()==''){
			alert('담당자 정보를 입력하세요!');
			$("#quickCharge").focus();
			return;
		}
		
		if($("#quickTel").val()==''){
			alert('담당자 연락처를 입력하세요!');
			$("#quickTel").focus();
			return;
		}
		
		if (confirm('운송정보를 수정 하시겠습니까?')){ 
		
		commonDim(true);
		
		    $.ajax({
		        type: "POST",
		           url:  "<%= request.getContextPath() %>/recover/quickupdate",
		                data:$("#transForm").serialize(),
		           success: function(result) {
		               commonDim(false);
		              
		               if(result!='-1'){
		            	   
		            	   //var quickArr=result.split('^');
		            	   
		            	   //document.all('quickId').innerText=encodeURIComponent(quickArr[0]);
		            	   //document.all('quicktelId').innerText=quickArr[1];
		            	   document.all('quickId').innerText=document.transForm.quickCharge.value;
			               document.all('quicktelId').innerText=document.transForm.quickTel.value;
		               }else{
		            	   alert('퀵정보 수정에 실패했습니다.');
		               }
		             

		               $("#transManage").dialog('close');
		            
		           },
		           error:function() {
		               commonDim(false);

		               alert('퀵정보 수정에 실패했습니다.');
		               
		               $("#transManage").dialog('close');
		           }
		    });
		}
		
		
	}
}

</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
	<h5><strong><font style="color:#428bca"><span class="glyphicon glyphicon-book"></span>운송정보 수정 &nbsp; 
   				</font></strong></h5>
	  <form:form commandName="recoveryVO" id="transForm" name="transForm" method="post" action="" >
	  <input type="hidden" name="recoveryCode"          id="recoveryCode"         value="${recoveryCode}"  />
	  <input type="hidden" name="deliveryMethod"          id="deliveryMethod"         value="${deliveryMethod}"  />
	  <br>
	  <table class="table table-bordered" >
      	<tr>
      	<div class="form-inline">
          <c:choose>
  			<c:when test="${deliveryMethod=='01'}">
  			    <th class='text-center' style="background-color:#E6F3FF" >운송방법</th>
  			    <th class='text-center' >&nbsp;택배 </th>
         	 	<th class='text-center' style="background-color:#E6F3FF">운송회사
		          <!-- >button id="downbtn" type="button" class="btn btn-xs btn-success" onClick="" >직접입력</button --></th>
		          <th class='text-center' colspan="2" >
				  <select class="form-control" title="운송업체" id="transportCode" name="transportCode" value="">
                	<option value="">없음</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		 </select>
		   		  <input type="hidden" id="transport" name="transport" >
		          </th>
		      	  <th class='text-center'  style="background-color:#E6F3FF">운송장번호</th>
		          <th class='text-center'><input type="text" class="form-control" id="transportNo" name="transportNo" maxlength="30"   value="" placeholder="운송장번호"  /></th>	
		          <th class='text-center' >&nbsp;<button id="memoinfobtn" type="button" class="btn btn-info" onClick="fcTrans_update()" >수정</button></th>
            </c:when>
	        <c:otherwise>	
	       	  	  <th class='text-center' style="background-color:#E6F3FF" >운송방법</th>
  			      <th class='text-center' >&nbsp;퀵 </th>
		      	  <th class='text-center' style="background-color:#E6F3FF">담당자</th>
		          <th class='text-center' colspan="2" >
		          <input type="text" class="form-control" id="quickCharge" name="quickCharge"   maxlength="10"  value="" placeholder="담당자"  />
		          </th>
		      	  <th class='text-center' style="background-color:#E6F3FF">연락처</th>
		          <th class='text-center'>
		          <input type="text" class="form-control" id="quicktel" name="quickTel" value=""  maxlength="14" placeholder="연락처"  />
		          </th>
		          <th class='text-center' >&nbsp;<button id="memoinfobtn" type="button" class="btn btn-info" onClick="fcTrans_update()" >수정</button></th>
            </c:otherwise>
 		   </c:choose>
    	  </div>
      	</tr>
	  </table>
	  </form:form>
</div>
</body>