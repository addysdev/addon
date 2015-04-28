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
function fcUserManage_excelimport(){

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
       url="<%= request.getContextPath() %>/manage/userexcelimport?fileName="+gap+"&extension="+gap1;
    }else if(gap1=="xlsx"){
       url="<%= request.getContextPath() %>/manage/userexcelimport?fileName="+gap+"&extension="+gap1;
    }else{
        alert("엑셀파일만 올려주세요");
        return;
    }

    frm.action = url;
    frm.target="excel_import_result";

    frm.submit();        
}

function fcUserManage_excelUpload(){

	var frm=document.userRegistFrm; //form객체세팅

	/* if(frm.userFile.value == ""){
		
		alert('업로드할 xls파일을 첨부하세요');
		return;
		
	}else if(!isImageFile(frm.userFile.value)){

		return; 				
    } */
	
	openWaiting();
	frm.action = "<%=request.getContextPath() %>/H_User.do?cmd=userExcelImport";
	frm.submit();
}
/**
 *  파일 확장자명 체크
 *
 **/
function isImageFile( obj ) {
	var strIdx = obj.lastIndexOf( '.' ) + 1;
	if ( strIdx == 0 ) {
		return false;
	} else {
		var ext = obj.substr( strIdx ).toLowerCase();
		if ( ext == "xls") {
			return true;
		} else {
			alert(ext+'파일은 전송이 불가능합니다.');
			return false;
		}
	}
}

</script>
</head>
<%-- <div id="waitwindow" style=" position:absolute; left:0">
  <div class="wait" style="" >
    <img src="<%= request.getContextPath()%>/images/loading.gif" width="32" height="32" style="left:0;" >
  </div>
</div> --%>
<body onLoad="init()" style="background:none">
<iframe id="excel_import_result" name="excel_import_result" style="display: none" ></iframe>
<div id="wrap">
  <!-- title -->
  <!-- <div class="title">
    <h1 class="title_lft"><span class="title_rgt">사용자정보 일괄등록</span></h1>
  </div> -->
  <!-- //title -->
  <!-- contents -->
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
    <!-- caution_area -->
    <div>
      <h5>업로드시 주의사항</h5>
      <ul>
        <h6>-엑셀파일로 사용자 정보를 일괄 업데이트 할 수 있습니다.
          <br />
          <span> (확장자가 xls 인 엑셀 파일만 업로드 가능합니다.)</span> </h6>
        <h6>-엑셀파일 업로드 양식을 다운로드 합니다. <a href="<%= request.getContextPath() %>/fileDownServlet?rFileName=UserUploadFormat.xls&sFileName=UserUploadFormat.xls&filePath=/down"><strong class="blueTxt">[양식다운로드]</strong></a>
          <br />
          <span> (다운받으신 양식의 첫번째 타이틀에 맞춰 사용자정보를 저장합니다.)</span> </h6>
        <h6>-사용자정보 입력시 사용자ID가 중복 될 경우 등록실패 됩니다.</h6>
        <h6>-파일 업로드 결과는 서버의 log 경로에서 확인이 가능합니다.</h6>
        <br />
      </ul>
    </div>
    <!-- //caution_area -->
  </div>
  <!-- //contents -->
  <!-- button -->
  <div class="ly_foot">
    <button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_excelimport()">import</button>
  </div>
  <!-- //button -->
</div>
</body>
</html>
