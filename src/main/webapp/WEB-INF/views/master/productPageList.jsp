<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageProductMasterPageList(page) {
        document.productManageConForm.curPage.value = page;
        var dataParam = $("#productMasterConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/productpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#productMasterPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>
	<div class="container">
     <form:form commandName="productMasterVO" name="productMasterPageListForm" method="post" action="" >
      <p><span>총 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <colgroup>
	     <col width="15%" />
         <col width="15%" />
         <col width="*" />
         <col width="10%" />
         <col width="10%" />
         <col width="10%" />
         <col width="15%" />
        </colgroup>
	    <thead>
	      <tr>
	        <th>품목코드</th>
            <th>바코드</th>
            <th>품목명</th>
            <th>구매처</th>
            <th>최종업데이트UserID</th>
            <th>최종업데이트UserName</th>
            <th>최종업데이트</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty productList}">
             <c:forEach items="${productList}" var="productMasterVO" varStatus="status">
             <tr id="select_tr_${productMasterVO.productCode}">
                 <td><a href="javascript:fcUserMaster_detailSearch('${productMasterVO.productCode}')"><c:out value="${productMasterVO.productCode}"></c:out></a></td>
                 <td><c:out value="${productMasterVO.barCode}"></c:out></td>
                 <td><c:out value="${productMasterVO.productName}"></c:out></td>
                 <td><c:out value="${productMasterVO.companyName}"></c:out></td>
                 <td><c:out value="${productMasterVO.updateUserId}"></c:out></td>
                 <td><c:out value="${productMasterVO.updateUserName}"></c:out></td>
                 <td><c:out value="${productMasterVO.updateDateTime}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty productList}">
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
        <taglib:paging cbFnc="goPageProductMasterPageList" totalCount="${totalCount}" curPage="${productCon.curPage}" rowCount="${productCon.rowCount}" />
        <!-- //페이징 -->
	</div>
    