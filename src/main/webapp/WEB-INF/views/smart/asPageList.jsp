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
	        <th>접수일자</th>
            <th>접수자</th>
            <th>완료일자</th>
            <th>상품명</th>
            <th>처리상태</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty asList}">
             <c:forEach items="${asList}" var="asListVO" varStatus="status">
             <tr id="select_tr_${asListVO.asNo}">
                 <td><a href="javascript:fcAs_procForm('${asListVO.asNo}')"><c:out value="${asListVO.customerKey}"></c:out></a>
                 <img id="hisbtn" onClick="fcHis_detail('${asListVO.customerKey}','${asListVO.asNo}','${asListVO.asDetail}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="이력">
                 </td>
                 <td><c:out value="${asListVO.asStartDateTime}"></c:out></td>
                 <td><c:out value="${asListVO.userName}(${asListVO.userId})"></c:out></td>
                 <td><c:out value="${asListVO.asTargetDate}"></c:out></td>
                 <td><c:out value="${asListVO.productName}"></c:out></td>
                 <td><c:out value="${asListVO.asState}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty asList}">
           <tr>
               <td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageAsPageList" totalCount="${totalCount}" curPage="${asConVO.curPage}" rowCount="${asConVO.rowCount}" />
     <!-- //페이징 -->

    