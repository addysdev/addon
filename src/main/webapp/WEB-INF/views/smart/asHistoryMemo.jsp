<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

//보류사유추가
function fcMemo_add(){

	var asNo='${asNo}';
	var customerKey='${customerKey}';
	
	fcAs_HistoryMemo(asNo,customerKey,document.memoForm.memo.value);
}
function fcMemo_close(){
	$("#memoDialog").dialog('close');
}
</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="commentVO" id="memoForm" name="memoForm" method="post" action="" >
<p><textarea style='height:82px;ime-mode:active;' row="3" class="form-control" id="memo" maxlength="200" name="memo"  value=""  placeholder="처리메모"/></p>
<br>
<button id="defersavebtn" type="button" class="btn btn-primary" onClick="fcMemo_add()">저장</button> <button id="deferpopclosebtn" type="button" class="btn btn-danger" onClick="fcMemo_close()">취소</button>
</form:form>
</div>
</body>
<script>
$('#memo').focus(1); 
</script>