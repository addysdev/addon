<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
//초기세팅
$(function() {
	    
	    $( "#transDate_view" ).datepicker({
	        dateFormat: "yy-mm-dd",
	        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
	        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
	        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        maxDate : "+0D"
	    });

	});
function showCalendar2(){	
  $('#transDate_view').datepicker("show");  
}
function fcComplete_close(){
	$("#completeDialog").dialog('close');
}
//보류사유추가
function fcOrder_CompleteAdd(){

	fcOrder_complete(document.transDateForm.transDate_view.value);
	
}
</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="commentVO" id="transDateForm" name="transDateForm" method="post" action="" >
<br>
<h4><strong><font style="color:#FF9900">※ 검수완료 시 거래명세일자 를 <br>&nbsp;&nbsp;&nbsp;선택 하시기 바랍니다.</font></strong></h4>
<br>
   <div class="form-inline">
	<label for="transDate_view"><h6><strong><font style="color:#428bca"> 거래명세일자 : </font></strong></h6></label>
    <!-- 재고일자-->
      <input  disabled class="form-control" style='width:135px' name="transDate_view" id="transDate_view" value="" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
      <!-- 달력이미지 시작 -->
      <span class="icon_calendar"><img border="0" onclick="showCalendar2()" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
      <!-- 달력이미지 끝 -->
   </div>
<br>
<button id="defersavebtn" type="button" class="btn btn-primary" onClick="fcOrder_CompleteAdd()">검수완료</button> <button id="deferpopclosebtn" type="button" class="btn btn-danger" onClick="fcComplete_close()">닫기</button>
</form:form>
</div>
</body>
<script>
$('#defer_reason').focus(1); 
</script>