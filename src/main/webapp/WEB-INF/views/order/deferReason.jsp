<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

//보류사유추가
function fcDefer_reasonadd(){

	if('${deferType}'=='D'){	
		fcDefer_cancel(document.deferForm.defer_reason.value);
	}else{
		fcDefer_reason(document.deferForm.defer_reason.value);
		
	}
}
function fcDefer_close(){
	$("#deferDialog").dialog('close');
}
</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="commentVO" id="deferForm" name="deferForm" method="post" action="" >
<p><textarea style='height:82px' row="3" class="form-control" id="defer_reason" name="defer_reason"  value=""  placeholder="보류사유"/></p>
<button id="defersavebtn" type="button" class="btn btn-primary" onClick="fcDefer_reasonadd()">저장</button> <button id="deferpopclosebtn" type="button" class="btn btn-danger" onClick="fcDefer_close()">취소</button>
</form:form>
</div>
</body>