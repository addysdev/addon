<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageProductMasterPageList(page) {
        document.productMasterConForm.curPage.value = page;
        var dataParam = $("#productMasterConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/productpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#productMasterPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="productMasterVO" name="productMasterPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>품목코드</th>
            <th class='text-center'>바코드</th>
            <th class='text-center'>품목명</th>
            <th class='text-center'>구매처</th>
            <th class='text-center'>업데이트사용자ID</th>
            <th class='text-center'>업데이트 사용자명</th>
            <th class='text-center'>업데이트 일시</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty productList}">
             <c:forEach items="${productList}" var="productMasterVO" varStatus="status">
             <tr id="select_tr_${productMasterVO.productCode}">
                 <td class='text-center'><a href="javascript:fcUserMaster_detailSearch('${productMasterVO.productCode}')"><c:out value="${productMasterVO.productCode}"></c:out></a></td>
                 <td class='text-center'><c:out value="${productMasterVO.barCode}"></c:out></td>
                 <td><c:out value="${productMasterVO.productName}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.updateUserId}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.updateUserName}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.updateDateTime}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty productList}">
           <tr>
           	<td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageProductMasterPageList" totalCount="${totalCount}" curPage="${productCon.curPage}" rowCount="${productCon.rowCount}" />
     <!-- //페이징 -->
