<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageGmroiPageList(page) {
        document.gmroiConForm.curPage.value = page;
        var dataParam = $("#gmroiConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/analysis/gmroipagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#gmroiPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
</SCRIPT>

     <form:form commandName="gmroiVO" name="gmroiPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	  	<colgroup>
	     <col width="10%" />
	     <col width="*" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="6%" />
         <col width="6%" />
        </colgroup>
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th rowspan="2" class='text-center'>품목코드</th>
            <th rowspan="2" class='text-center'>품목명</th>
            <th colspan="2" class='text-center'>재고</th>
            <th colspan="4" class='text-center'>매출</th>
            <th rowspan="2" class='text-center'>재고금액<br>회전율</th>
            <th rowspan="2" class='text-center'>GMROI</th>
	      </tr>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>평균 재고수량</th>
            <th class='text-center'>평균 재고금액</th>
            <th class='text-center'>총 매출수량</th>
            <th class='text-center'>총 매출금액</th>
            <th class='text-center'>총 이익금액</th>
            <th class='text-center'>총 이익율</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty gmroiList}">
             <c:forEach items="${gmroiList}" var="gmroiVO" varStatus="status">
             <tr id="select_tr_${gmroiVO.productCode}">
                 <td class='text-center'><c:out value="${gmroiVO.productCode}"></c:out></td>
                 <td><c:out value="${gmroiVO.productName}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.avgStockCnt}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.avgStockAmt}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.totalSaleCnt}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.totalSaleAmt}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.profitSaleAmt}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.avgSaleRate}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.stockCycleRate}"></c:out></td>
                 <td class='text-center'><c:out value="${gmroiVO.gmroiRate}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty gmroiList}">
           <tr>
           	<td colspan='10' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageGmRoiPageList" totalCount="${totalCount}" curPage="${gmroiCon.curPage}" rowCount="${gmroiCon.rowCount}" />
     <!-- //페이징 -->

