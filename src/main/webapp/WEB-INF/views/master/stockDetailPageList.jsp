<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageStockDetailPageList(page) {
        document.stockDetailConForm.curPage.value = page;
        var dataParam = $("#stockDetailConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/stockdetailpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#stockDetailPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
</SCRIPT>
     <form:form commandName="stockVO" name="stockDetailPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>품목코드</th>
            <th class='text-center'>품목명</th>
            <th class='text-center'>수량</th>
            <th class='text-center'>입고단가</th>
            <th class='text-center'>금액</th>
         </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty stockDetailList}">
             <c:forEach items="${stockDetailList}" var="stockVO" varStatus="status">
             <tr id="select_tr_${stockVO.productCode}">
	              <td><c:out value="${stockVO.productCode}"></c:out></td>
	              <td><c:out value="${stockVO.productName}"></c:out></td>
	              <td><c:out value="${stockVO.stockCnt}"></c:out></td>
	              <td><c:out value="${stockVO.productPrice}"></c:out></td>
	              <td><c:out value="${stockVO.stockPrice}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty stockDetailList}">
              <tr>
                  <td colspan='2' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

    <!-- 페이징 -->
    <taglib:paging cbFnc="goPageStockDetailPageList" totalCount="${totalCount}" curPage="${stockDetailConVO.curPage}" rowCount="${stockDetailConVO.rowCount}" />
    <!-- //페이징 -->
	