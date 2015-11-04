<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script language="javascript">
//초기세팅
$(function() {
    
    $( "#upload_stockDate_view" ).datepicker({
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
	 $('#upload_stockDate_view').datepicker("show");
	}
</script>
</head>
<body>
<iframe id="excel_import_result" name="excel_import_result" style="display: none" ></iframe>
 <!-- content -->
<div class="container-fluid">
 <!-- form_area -->
 <form:form class="form-inline" role="form" commandName="fileVO"  id="excel_form" method="post" target="excel_import_result"  name="excel_form"  enctype="multipart/form-data" >
  <fieldset>
  <div class="form-group" >
   <br>
   <h4><strong><font style="color:#428bca">재고 기준 선택</font></strong></h4>
   <br>
   <div class="form-inline">
	<label for="start_stockDate"><h6><strong><font style="color:#FF9900"> 조사일자 : </font></strong></h6></label>
    <!-- 재고일자-->
      <input type="hidden" name="upload_stockDate" id="upload_stockDate" value="${stockConVO.start_stockDate}"  >
      <input class="form-control" disabled style='width:135px' name="upload_stockDate_view" id="upload_stockDate_view" value="${stockConVO.start_stockDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
      <!-- 달력이미지 시작 -->
      <span class="icon_calendar"><img border="0" onclick="showCalendar2()" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
      <!-- 달력이미지 끝 -->
   </div>
    <br>
	<label for="con_groupId"><font style="color:#FF9900"> 지점선택 : </font></label>
		<select class="form-control" title="지점정보" id="upload_groupId" name="upload_groupId" value="${stockConVO.groupId}">
             <option value="">전체</option>
             <c:forEach var="groupVO" items="${group_comboList}" >
             	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
             </c:forEach>
        </select>
  </fieldset>
</form:form>
 <!-- //form_area --> 
 <br>
 <!-- button -->
 <div >
   <button type="button" id="barcodebtn" onClick="alert('waiting..');" class="btn btn-xs btn-info">바코드 조사</button>&nbsp;<button type="button" class="btn" onClick="alert('waiting...')">excel</button>
 </div>
  <!-- //button -->
</div>
<br>
	<table class="table table-bordered tbl_type" >
     <colgroup>
      <col width="70px" >
      <col width="60px" >
     </colgroup>
     <tr>
        <td style="background-color:#E6F3FF" class='text-center' >조사수량</td>
     	<td class='text-right'><span id="checkTCnt" style="color:red">0건</span></td>
     </tr>
    </table>  
 </div>
 <!-- //content -->
</body>
</html>
