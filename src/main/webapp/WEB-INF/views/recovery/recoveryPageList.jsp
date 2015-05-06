<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageRecoveryPageList(page) {
        document.recoveryConForm.curPage.value = page;
        var dataParam = $("#recoveryConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/recovery/recoverypagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#recoveryPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>
	<div class="container">
     <form:form commandName="recoveryVO" name="recoveryPageListForm" method="post" action="" >
      <p><span>총 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th>회수상태</th>
            <th>회수번호</th>
            <th>회수일자</th>
            <th>화수자</th>
            <th>매장명</th>
            <th>회수대상수량</th>
            <th>회수금액</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty recoveryList}">
             <c:forEach items="${recoveryList}" var="userListVO" varStatus="status">
             <tr id="select_tr_${recoveryVO.recoveryCode}">
                 <td><c:out value=""></c:out></td>
                 <td><a href="javascript:fcUserManage_detailSearch('${recoveryVO.recoveryCode}')"><c:out value="${recoveryVO.recoveryCode}"></c:out></a></td>
                 <td><c:out value=""></c:out></td>
                 <td><c:out value=""></c:out></td>
                 <td><c:out value=""></c:out></td>
                 <td><c:out value="10"></c:out></td>
                 <td><c:out value="50000"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty recoveryList}">
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
        <taglib:paging cbFnc="goPageRecoveryPageList" totalCount="${totalCount}" curPage="${recoveryConVO.curPage}" rowCount="${recoveryConVO.rowCount}" />
        <!-- //페이징 -->
	</div>
    