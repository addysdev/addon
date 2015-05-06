<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageTargetPageList(page) {
        document.targetConForm.curPage.value = page;
        var dataParam = $("#targetConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/order/targetpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#targetPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>
	<div class="container">
     <form:form commandName="targetVO" name="productMasterPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>발주상태</th>
            <th class='text-center'>매장ID</th>
            <th class='text-center'>매장명</th>
            <th class='text-center'>업체코드</th>
            <th class='text-center'>업체명</th>
            <th class='text-center'>수량</th>
            <th class='text-center'>기준금액</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty targetList}">
             <c:forEach items="${targetList}" var="targetVO" varStatus="status">
             <tr id="select_tr_${targetVO.groupId}_${targetVO.companyCode}">
                 <td class='text-center'><c:out value="${targetVO.buyResult}"></c:out></td>
                 <td class='text-center'><a href="javascript:fcTarget_detail('${targetVO.groupId}','${targetVO.companyCode}')"><c:out value="${targetVO.groupId}"></c:out></a></td>
                 <td><c:out value="${targetVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.companyCode}"></c:out></td>
                 <td><c:out value="${targetVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.orderCnt}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.orderAmt}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty targetList}">
           <tr>
           	<td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>
	</div>
	<div class="container">
	    <!-- 페이징 -->
        <taglib:paging cbFnc="goPageTargetPageList" totalCount="${totalCount}" curPage="${targetConVO.curPage}" rowCount="${targetConVO.rowCount}" />
        <!-- //페이징 -->
	</div>
    