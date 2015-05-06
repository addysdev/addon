<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
   

</SCRIPT>
	<div class="container">
     <form:form commandName="targetVO" name="targetDetailListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>품목코드</th>
            <th class='text-center'>품목명</th>
            <th class='text-center'>재고수량</th>
            <th class='text-center'>안전재고</th>
            <th class='text-center'>보유재고</th>
            <th class='text-center'>재고기준일</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty targetDetailList}">
             <c:forEach items="${targetDetailList}" var="targetVO" varStatus="status">
             <tr id="select_tr_${targetVO.productCode}">
                 <td class='text-center'><c:out value="${targetVO.productCode}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.productName}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.stockCnt}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.safeStock}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.holdStock}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.stockDate}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty targetDetailList}">
           <tr>
           	<td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>
	</div>