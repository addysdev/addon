<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<html>
<head>
<script language="javascript">
//초기세팅
<%-- function init() {

	closeWaiting(); //처리중 메세지 비활성화

	if('<%=importResult%>'!=''){
		alert('<%=importResult%>');
	}

} --%>
function fcStock_excelimport(){

    if($("#files").val() == ''){
    	
        alert('등록 할 파일이 없습니다.');
        return;
    }
    
    var url;
    var frm = document.excel_form;
    var fileName = document.all.files.value;
    var pos = fileName.lastIndexOf("\\");
    var ln = fileName.lastIndexOf("\.");
    var gap = fileName.substring(pos + 1, ln);
    var gap1 = fileName.substring(ln+1);

    if(gap1=="xls"){
       url="<%= request.getContextPath() %>/master/stockexcelimport?fileName="+gap+"&extension="+gap1;
    }else if(gap1=="xlsx"){
       url="<%= request.getContextPath() %>/master/stockexcelimport?fileName="+gap+"&extension="+gap1;
    }else{
        alert("엑셀파일만 올려주세요");
        return;
    }

    frm.action = url;
    frm.target="excel_import_result";

    frm.submit();        
}


$('.form_date').datetimepicker({
    language:  'kr',
    format: 'yyyy-mm-dd',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	minView: 2,
	forceParse: 0
});

</script>
</head>
<body style="background:none">
<iframe id="excel_import_result" name="excel_import_result" style="display: none" ></iframe>
<div id="wrap">
  <div id="contents">
    <!-- form_area -->
	<div>
    <fieldset>
      <h4>등록파일 선택</h4>
      <form:form commandName="fileVO"  id="excel_form" method="post" target="excel_import_result"  name="excel_form"  enctype="multipart/form-data" >
        <h5><span>업로드 할 <em class="bold">엑셀파일</em></span><input type="file"  id="files" name="files" /></h5>
      </form:form>
    </fieldset>
	</div>
    <!-- //form_area -->
        <!-- form_area -->
	<div>
    <fieldset>
      <h4>재고일자</h4>
      <div class="form-group">
                <label for="dtp_input2" class="col-md-2 control-label">Date Picking</label>
                <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
				<input type="hidden" id="dtp_input2" value="" /><br/>
            </div>
      </div>
        <h5></h5>
     <h4>대상지점</h4>
                      <td>
                            <select class="form-control" title="계정정보" id="searchGubun" name="searchGubun" >
                                <option value="01" >영등포영풍</option>
                                <option value="02" >본사</option>
                            </select>
                        </td>
        <h5></h5>
    </fieldset>
	</div>
    <!-- //form_area -->
    <!-- caution_area -->
    <div>
      <h5>업로드시 주의사항</h5>
      <ul>
      <h6>-업로드 대상의 재고현황 일자와 지점을 꼭 선택해야 합니다.
          <br />
        <h6>-엑셀파일로 재고현황을 일괄 업데이트 할 수 있습니다.
          <br />
          <span> (확장자가 xls 인 엑셀 파일만 업로드 가능합니다.)</span> </h6>
        <h6>-엑셀파일 업로드 양식을 다운로드 합니다. <a href="<%= request.getContextPath() %>/fileDownServlet?rFileName=UserUploadFormat.xls&sFileName=UserUploadFormat.xls&filePath=/down"><strong class="blueTxt">[양식다운로드]</strong></a>
          <br />
          <span> (다운받으신 양식의 첫번째 타이틀에 맞춰 재고현황를 저장합니다.)</span> </h6>
        <h6>-파일 업로드 결과는 서버의 log 경로에서 확인이 가능합니다.</h6>
        <br />
      </ul>
    </div>
    <!-- //caution_area -->
  </div>
  <!-- //contents -->
  <!-- button -->
  <div class="ly_foot">
    <button type="button" class="btn btn-primary" onClick="javascript:fcStock_excelimport()">import</button>
  </div>
  <!-- //button -->
</div>
</body>
</html>
