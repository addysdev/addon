<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

//보류사유추가
function fcReplace_submit(){

	 if (confirm('1:1교환 처리로 저장 하시겠습니까?')){ 
		 
		 document.asRegistForm.asResult.value=document.replaceForm.replace_reason;
		 document.asRegistForm.submit();        
	 }
	
	 $("#ReplaceResult").dialog('close');
	 
}
function fcReplace_close(){
	$("#ReplaceResult").dialog('close');
}
</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="asVO" id="replaceForm" name="replaceForm" method="post" action="" >
<p><textarea style='height:82px;ime-mode:active;' row="3" class="form-control" id="defer_reason" maxlength="200" name="replace_reason"  value=""  placeholder="처리사유"/></p>
<br>
<button id="defersavebtn" type="button" class="btn btn-primary" onClick="fcReplace_submit()">저장</button> <button id="deferpopclosebtn" type="button" class="btn btn-danger" onClick="fcReplace_close()">취소</button>
</form:form>
</div>
</body>
<script>
$('#replace_reason').focus(1); 
</script>