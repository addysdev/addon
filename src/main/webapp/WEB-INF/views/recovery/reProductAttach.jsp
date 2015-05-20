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
	             	<tr><input type="hidden" id="selectProduct" name="selectProduct" value="${productMasterVO.productCode}">
	             	<td class="text-left">[${productMasterVO.productCode}] ${productMasterVO.productName}&nbsp;
	             	<button type="button" class="btn btn-xs btn-info" onClick="delFile(this)" >삭제</button></td></tr>
	             </c:forEach>
            </c:if>
	  	</tbody>
	</table>
</form:form>