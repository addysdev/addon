<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageSalesDetailPageList(page) {
        document.salesDetailConForm.curPage.value = page;
        var dataParam = $("#salesDetailConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/salesdetailpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#salesDetailPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
</SCRIPT>
     <form:form commandName="salesVO" name="salesDetailPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>품목코드</th>
            <th class='text-center'>품목명</th>
            <th class='text-center'>수량</th>
            <th class='text-center'>입고단가</th>
            <th class='text-center'>판매금액</th>
         </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty salesDetailList}">
             <c:forEach items="${salesDetailList}" var="salesVO" varStatus="status">
                  <tr id="select_tr_${salesVO.productCode}">
	              <td><c:out value="${salesVO.productCode}"></c:out></td>
	              <td><c:out value="${salesVO.productName}"></c:out></td>
	              <td><c:out value="${salesVO.salesCnt}"></c:out></td>
	              <td><c:out value="${salesVO.productPrice}"></c:out></td>
	              <td><c:out value="${salesVO.salesPrice}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty salesDetailList}">
              <tr>
                  <td colspan='5' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

    <!-- 페이징 -->
    <taglib:paging cbFnc="goPageSalesDetailPageList" totalCount="${totalCount}" curPage="${salesDetailConVO.curPage}" rowCount="${salesDetailConVO.rowCount}" />
    <!-- //페이징 -->
	