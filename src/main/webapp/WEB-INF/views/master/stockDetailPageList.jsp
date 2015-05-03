<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageStockDetailPageList(page) {
        document.stockDetailConForm.curPage.value = page;
        var dataParam = $("#stockDetailConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/stockdetailpagelist",
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
	<div class="container">
     <form:form commandName="stockVO" name="stockDetailPageListForm" method="post" action="" >
      <p><span>총 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <colgroup>
	     <col width="25%" />
         <col width="25%" />
        </colgroup>
	    <thead>
	      <tr>
	        <th>품목코드</th>
            <th>품목명</th>
         </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty stockDetailList}">
             <c:forEach items="${stockDetailList}" var="stockVO" varStatus="status">
             <tr id="select_tr_${stockVO.productCode}">
                 <td><c:out value="${stockVO.productCode}"></c:out></td>
                 <td><c:out value="${stockVO.productName}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty stockDetailList}">
              <tr>
                  <td colspan='7' class='text_c'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>
	</div>
	<div class="container">
	    <!-- 페이징 -->
        <taglib:paging cbFnc="goPageStockDetailPageList" totalCount="${totalCount}" curPage="${stockDetailConVO.curPage}" rowCount="${stockDetailConVO.rowCount}" />
        <!-- //페이징 -->
	</div>
    