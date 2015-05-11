<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<html>
<head>
<script language="javascript">
//초기세팅

function fcSales_excelimport(){

    if($("#files").val() == ''){
    	
        alert('등록 할 파일이 없습니다.');
        return;
    }
    
    if($("#upload_salesDate").val() == ''){
    	
        alert('매출기준일을 선택하셔야 합니다.');
        return;
    }
  
    if($("#upload_groupId").val() == ''){
  	
        alert('재고 업로드 대상 지점을 선택하셔야 합니다.');
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
       url="<%= request.getContextPath() %>/master/salesexcelimport?fileName="+gap+"&extension="+gap1+"&upload_salesDate="+$("#upload_salesDate").val()+"&upload_groupId="+$("#upload_groupId").val();
    }else if(gap1=="xlsx"){
       url="<%= request.getContextPath() %>/master/salesexcelimport?fileName="+gap+"&extension="+gap1+"&upload_salesDate="+$("#upload_salesDate").val()+"&upload_groupId="+$("#upload_groupId").val();
    }else{
        alert("엑셀파일만 올려주세요");
        return;
    }
    commonDim(true);
    frm.action = url;
    frm.target="excel_import_result";

    frm.submit();        
}
function uploadClose(msg){
	
	 commonDim(false);
	  
	 alert(msg);
	 
	 $('#salesExcelForm').dialog('close');
	 fcSales_listSearch();
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
   <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 매출 기준 선택</font></strong></h4>
	<label for="start_salesDate"><h6><strong><font style="color:#FF9900"> 매출일자 : </font></strong></h6></label>
	<div style='width:150px' class='input-group date ' id='datetimepicker3' data-link-field="upload_salesDate" data-link-format="yyyy-mm-dd">
        <input type='text' class="form-control" value="${salesConVO.start_salesDate}" />
        <span class="input-group-addon">
            <span class="glyphicon glyphicon-calendar"></span>
        </span>
        <input type="hidden" id="upload_salesDate" name="upload_salesDate" value="${salesConVO.start_salesDate}" />
    </div>
    <br><br>
	<c:choose>
  		<c:when test="${strAuth == '03'}">
		<input type="hidden" id="upload_groupId" name="upload_groupId" value="${salesConVO.groupId}">
		</c:when>
		<c:otherwise>
			<label for="con_groupId"><font style="color:#FF9900"> 지점선택 : </font></label>
			<select class="form-control" title="지점정보" id="upload_groupId" name="upload_groupId" value="${salesConVO.groupId}">
                   <option value="">전체</option>
                   <c:forEach var="groupVO" items="${group_comboList}" >
                   	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                   </c:forEach>
               </select>
		</c:otherwise>
	</c:choose>
  <br><br> 
  <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 업로드 시 주의사항</font></strong></h4>
  <h6><strong><font id="avgStockAmt" style="color:#FF9900"> <span class="glyphicon glyphicon-tags"></span> 업로드 대상의 매출현황 일자와 지점을 꼭 선택해야 합니다.</font></strong></h6>
  <h6><strong><font style="color:#FF9900"> <span class="glyphicon glyphicon-tags"></span> 엑셀파일 업로드 양식을 다운로드 합니다. 
  <a href="#"><strong><font style="color:#428bca">[양식다운로드]</font></strong></a></font></strong></h6>
  <h6><strong><font style="color:#FF9900"> <span class="glyphicon glyphicon-tags"></span> 파일 업로드 결과는 서버의 log 경로에서 확인이 가능합니다.</font></strong></h6>
  </div>
  </fieldset>
</form:form>
 <!-- //form_area --> 
 <br>
 <!-- button -->
 <div >
  <button type="button" class="btn btn-primary" onClick="javascript:fcSales_excelimport()">upload</button>
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