<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageOrderLimitPageList(page) {
        document.orderLimitConForm.curPage.value = page;
        var dataParam = $("#orderLimitConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/orderlimitpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#orderLimitPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    function fcLimit_cancel(limitcode){
    	
    	alert('개발진행중입니다.');
    	return;
    	
    }
	
</SCRIPT>
     <form:form commandName="orderLimitVO" name="orderLimitPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span>전체건수 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table  class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>제한코드</th>
	        <th class='text-center'>제한대상지점</th>
            <th class='text-center'>제한대상업체</th>
            <th class='text-center'>제한시작일</th>
            <th class='text-center'>제한종료일</th>
            <th class='text-center'>제한해제</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty orderLimitList}">
             <c:forEach items="${orderLimitList}" var="orderLimitVO" varStatus="status">
             <tr id="select_tr_${orderLimitVO.limitCode}">
                 <td class='text-center'><c:out value="${orderLimitVO.limitCode}"></c:out></td>
                 <td class='text-center'><c:out value="${orderLimitVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderLimitVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderLimitVO.limitStartDate}"></c:out></td>
                 <td class='text-center'><c:out value="${orderLimitVO.limitEndDate}"></c:out></td>
                 <td class='text-center'><button type="button" id="receivebtn" class="btn btn-xs btn-success" onClick="fcLimit_cancel('${orderLimitVO.limitCode}');">해제</button></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty orderLimitList}">
              <tr>
                  <td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageorderLimitPageList" totalCount="${totalCount}" curPage="${orderLimitConVO.curPage}" rowCount="${orderLimitConVO.rowCount}" />
     <!-- //페이징 -->

    