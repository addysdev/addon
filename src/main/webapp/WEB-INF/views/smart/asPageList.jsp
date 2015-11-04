<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageAsPageList(page) {
        document.asConForm.curPage.value = page;
        var dataParam = $("#asConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/smart/aspagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#asPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="asVO" name="asPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th>핸드폰번호</th>
            <th>고객명</th>
            <th>고객ID</th>
            <th>AS일시</th>
            <th>AS내용</th>
            <th>AS상태</th>
            <th>처리일시</th>
            <th>처리자</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty asList}">
             <c:forEach items="${asList}" var="asListVO" varStatus="status">
             <tr id="select_tr_${asListVO.idx}">
                 <td><a href="javascript:fcAs_procForm('${asListVO.idx}')"><c:out value="${asListVO.customerKey}"></c:out></a>
                 <img id="hisbtn" onClick="fcHis_detail('${asListVO.customerKey}','${asListVO.idx}','${asListVO.as}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="이력">
                 </td>
                 <td><c:out value="${asListVO.customerName}"></c:out></td>
                 <td><c:out value="${asListVO.customerId}"></c:out></td>
                 <td><c:out value="${asListVO.asDateTime}"></c:out></td>
                 <td><c:out value="${asListVO.as}"></c:out></td>
                 <td><c:out value="${asListVO.asState}"></c:out></td>
                 <td><c:out value="${asListVO.asResultDateTime}"></c:out></td>
                 <td><c:out value="${asListVO.userName}(${asListVO.userId})"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty asList}">
           <tr>
               <td colspan='8' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageAsPageList" totalCount="${totalCount}" curPage="${asConVO.curPage}" rowCount="${asConVO.rowCount}" />
     <!-- //페이징 -->

    