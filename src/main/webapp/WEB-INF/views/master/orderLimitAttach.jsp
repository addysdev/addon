<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <form:form commandName="companyVO" name="limitCompanyListForm" method="post" action="" >
    <table class="table table-striped" id="contentId">
   		<colgroup>
  		 <col width="25%" />
      	 <col width="*" />
     	</colgroup>
	  	<thead>
		    <tr>
		       <th class="text-center">발주 제한업체</th>
		    </tr>
	  	</thead>
	  	<tbody>
	  		<c:if test="${!empty companyList}">
	             <c:forEach items="${companyList}" var="companyVO" varStatus="status">
	             	<tr><input type="hidden" id="selectCompany" name="selectCompany" value="${companyVO.companyCode}">
	             	<td class="text-left">[${companyVO.companyCode}] ${companyVO.companyName}&nbsp;
	             	<button type="button" class="btn btn-xs btn-info" onClick="delFile(this)" >삭제</button></td></tr>
	             </c:forEach>
            </c:if>
	  	</tbody>
	</table>
</form:form>
<script>

var excelcnt='${excelTotal}';
var companycnt = '${companyList.size()}';
var totalcnt=0;

var frm = document.limitCompanyListForm;

if(frm.selectCompany!=undefined ){
	if(frm.selectCompany.length!=undefined ){
		totalcnt=frm.selectCompany.length;
	}else{
		totalcnt=1;	
	}
}

var orderlimtcnt=companycnt-totalcnt;

if(excelcnt!=0){
	
	alert('엑셀 업로드 요청건수: '+excelcnt+'건\n업체 일치건수: '+companycnt+'건\n발주제한 적용건수: '+totalcnt+'건');
	
}

document.all('totalAttachCnt').innerText='선택업체 건수 :'+addCommaStr(''+totalcnt)+' 건';

</script>