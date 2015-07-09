<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

//보류사유추가
function fcDefer_reasonadd(){

	if('${deferType}'=='D'){	
		fcDefer_cancel(document.deferForm.defer_reason.value);
	}else if('${deferType}'=='R'){	
		fcDefer_reason(document.deferForm.defer_reason.value);
	}else if('${deferType}'=='C'){	
		fcOrder_cancel(document.deferForm.defer_reason.value);
	}else if('${deferType}'=='S'){	
		fcOrder_reMail(document.deferForm.defer_reason.value);
	}else  if('${deferType}'=='P'){	
		fcTargetDetail_print(document.deferForm.defer_reason.value);
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
<p><textarea style='height:82px;ime-mode:active;' row="3" class="form-control" id="defer_reason" maxlength="200" name="defer_reason"  value=""  placeholder="처리사유"/></p>
<br>
<button id="defersavebtn" type="button" class="btn btn-primary" onClick="fcDefer_reasonadd()">저장</button> <button id="deferpopclosebtn" type="button" class="btn btn-danger" onClick="fcDefer_close()">취소</button>
</form:form>
</div>
</body>
<script>
$('#defer_reason').focus(1); 
</script>