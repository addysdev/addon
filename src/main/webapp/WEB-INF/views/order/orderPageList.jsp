<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageOrderPageList(page) {
        document.orderConForm.curPage.value = page;
        var dataParam = $("#orderConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/order/orderpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#orderPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>
	<div class="container">
     <form:form commandName="orderVO" name="orderPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>검수상태</th>
            <th class='text-center'>발주번호</th>
            <th class='text-center'>발주일자</th>
            <th class='text-center'>발주자</th>
            <th class='text-center'>매장명</th>
            <th class='text-center'>업체명</th>
            <th class='text-center'>공급가</th>
            <th class='text-center'>부가세</th>
            <th class='text-center'>합계</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty orderList}">
             <c:forEach items="${orderList}" var="orderVO" varStatus="status">
             <tr id="select_tr_${orderVO.orderCode}">
                 <td class='text-center'><c:out value="${orderVO.buyResult}"></c:out></td>
                 <td class='text-center'><a href="javascript:fcOrder_detail('${orderVO.orderCode}')"><c:out value="${orderVO.orderCode}"></c:out></a></td>
                 <td><c:out value="${orderVO.orderDateTime}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.orderUserName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.orderAmt}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.orderAmt}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.orderAmt}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty orderList}">
           <tr>
           	<td colspan='9' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>
	</div>
	<div class="container">
	    <!-- 페이징 -->
        <taglib:paging cbFnc="goPageProductMasterPageList" totalCount="${totalCount}" curPage="${orderCon.curPage}" rowCount="${orderCon.rowCount}" />
        <!-- //페이징 -->
	</div>
    