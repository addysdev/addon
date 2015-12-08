<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageProductPageList(page) {
        document.productConForm.curPage.value = page;
        var dataParam = $("#productConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/smart/productsearchlist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#productSearchList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
</SCRIPT>

     <form:form commandName="productVO" name="productPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>품목코드</th>
            <th width="50" class='text-center'>브랜드</th>
            <th class='text-center'>품목명</th>
            <th class='text-center'>구매처</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty productList}">
             <c:forEach items="${productList}" var="productMasterVO" varStatus="status">
              <c:choose>
	    		<c:when test="${productMasterVO.recoveryYn=='Y'}">
					<tr id="select_tr_${productMasterVO.productCode}" style="color:red">
				</c:when>
				<c:otherwise>
					<tr id="select_tr_${productMasterVO.productCode}">
				</c:otherwise>
			 </c:choose>
                 <td class='text-center'><c:out value="${productMasterVO.productCode}"></c:out></td>
                 <td class='text-center'><c:out value="${productMasterVO.group1Name}"></c:out></td>
                 <td><a href="javascript:fcBrand_Select('${productMasterVO.group1}','${productMasterVO.productCode}','${productMasterVO.productName}')"><c:out value="${productMasterVO.productName}"></c:out></a></td>
                 <td class='text-center'><c:out value="${productMasterVO.companyName}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty productList}">
           <tr>
           	<td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageProductPageList" totalCount="${totalCount}" curPage="${productCon.curPage}" rowCount="${productCon.rowCount}" />
     <!-- //페이징 -->
