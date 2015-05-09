<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script language="javascript">
//초기세팅

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
  <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 업로드 파일 선택</font></strong></h4>
  <h5><strong><font style="color:#FF9900"> <span class="glyphicon glyphicon-bookmark"></span> 업로드 할 <em class="bold"> excel파일</em></font></strong></h5>
  <input type="file"  id="files" name="files" />
  <br><br> 
   <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 재고 기준 선택</font></strong></h4>
	<label for="stockDate"><h6><strong><font style="color:#FF9900"> 재고일자 : </font></strong></h6></label>
	<div style='width:150px' class='input-group date ' id='datetimepicker3' data-link-field="start_stockDate" data-link-format="yyyy-mm-dd">
        <input type='text' class="form-control" value="${stockConVO.start_stockDate}" />
        <span class="input-group-addon">
            <span class="glyphicon glyphicon-calendar"></span>
        </span>
        <input type="hidden" id="start_stockDate" name="start_stockDate" value="${stockConVO.start_stockDate}" />
    </div>
    <br><br>
	<label for="con_groupId"><h6><strong><font style="color:#FF9900">  지점선택 : </font></strong></h6></label>
	<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${stockConVO.groupId}">
        <option value="">전체</option>
        <c:forEach var="groupVO" items="${group_comboList}" >
        	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
        </c:forEach>
    </select>
  <br><br> 
  <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 업로드 시 주의사항</font></strong></h4>
  <h6><strong><font id="avgStockAmt" style="color:#FF9900"> <span class="glyphicon glyphicon-tags"></span> 업로드 대상의 재고현황 일자와 지점을 꼭 선택해야 합니다.</font></strong></h6>
  <h6><strong><font style="color:#FF9900"> <span class="glyphicon glyphicon-tags"></span> 엑셀파일 업로드 양식을 다운로드 합니다. 
  <a href="<%= request.getContextPath() %>/fileDownServlet?rFileName=UserUploadFormat.xls&sFileName=UserUploadFormat.xls&filePath=/down"><strong><font style="color:#428bca">[양식다운로드]</font></strong></a></font></strong></h6>
  <h6><strong><font style="color:#FF9900"> <span class="glyphicon glyphicon-tags"></span> 파일 업로드 결과는 서버의 log 경로에서 확인이 가능합니다.</font></strong></h6>
  </div>
  </fieldset>
</form:form>
 <!-- //form_area --> 
 <br>
 <!-- button -->
 <div >
  <button type="button" class="btn btn-primary" onClick="javascript:fcStock_excelimport()">upload</button>
 </div>
  <!-- //button -->
</div>
 </div>
 <!-- //content -->
</body>
</html>
<script type="text/javascript">


    $(function () {
        $('#datetimepicker3').datetimepicker(
        		{
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
    });
</script>