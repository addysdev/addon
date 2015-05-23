<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <form:form commandName="productVO" name="recoveryProductListForm" method="post" action="" >
    <table class="table table-striped" id="contentId">
   		<colgroup>
  		 <col width="25%" />
      	 <col width="*" />
     	</colgroup>
	  	<thead>
		    <tr>
		       <th class="text-center">회수 대상품목</th>
		    </tr>
	  	</thead>
	  	<tbody>
	  		<c:if test="${!empty reProductList}">
	             <c:forEach items="${reProductList}" var="productMasterVO" varStatus="status">
	             	<c:if test="${productMasterVO.recoveryYn=='N'}"><tr><input type="hidden" id="selectProduct" name="selectProduct" value="${productMasterVO.productCode}">
	             	<td class="text-left">[${productMasterVO.productCode}] ${productMasterVO.productName}&nbsp;
	             	<button type="button" class="btn btn-xs btn-info" onClick="delFile(this)" >삭제</button></td></tr></c:if>
	             </c:forEach>
            </c:if>
	  	</tbody>
	</table>
</form:form>
<script>

var excelcnt='${excelTotal}';
var productcnt = '${reProductList.size()}';
var totalcnt=0;

var frm = document.recoveryProductListForm;

if(frm.selectProduct!=undefined ){
	if(frm.selectProduct.length!=undefined ){
		totalcnt=frm.selectProduct.length;
	}else{
		totalcnt=1;	
	}
}

var recovdrycnt=productcnt-totalcnt;

if(excelcnt!=0){
	
	alert('엑셀 업로드 요청건수: '+excelcnt+'건\n품목 일치건수: '+productcnt+'건\n회수중 품목건수: '+recovdrycnt+'건\n회수대상 적용건수: '+totalcnt+'건');
	
}

document.all('totalAttachCnt').innerText='선택품목 건수 :'+addCommaStr(''+totalcnt)+' 건';

</script>