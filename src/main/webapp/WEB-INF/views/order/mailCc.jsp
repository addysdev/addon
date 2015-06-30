<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

//메일참조추가
function fcCC_add(){

	fcEmail_ccAdd(document.mailccForm.email_cc.value);
	$("#ccDialog").dialog('close');
}
function fcCc_close(){
	$("#ccDialog").dialog('close');
}
</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="commentVO" id="mailccForm" name="mailccForm" method="post" action="" >
<p><textarea style='height:82px;ime-mode:active;' row="3" class="form-control" id="email_cc" maxlength="200" name="email_cc"  value=""  placeholder="cc"/>
</p>
<br>
<button id="mailccsavebtn" type="button" class="btn btn-primary" onClick="fcCC_add()">저장</button> <button id="mailccclosebtn" type="button" class="btn btn-danger" onClick="fcCc_close()">취소</button>
</form:form>
</div>
</body>
<script>

document.mailccForm.email_cc.value='${cc}';
$('#email_cc').focus(1); 

</script>