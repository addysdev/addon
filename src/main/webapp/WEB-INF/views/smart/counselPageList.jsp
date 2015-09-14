<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageCounselPageList(page) {
        document.counselConForm.curPage.value = page;
        var dataParam = $("#counselConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/smart/counselpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#counselPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="counselVO" name="counselPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th>핸드폰번호</th>
            <th>고객명</th>
            <th>고객ID</th>
            <th>상담일시</th>
            <th>상담내용</th>
            <th>상담상태</th>
            <th>처리일시</th>
            <th>처리자</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty counselList}">
             <c:forEach items="${counselList}" var="counselListVO" varStatus="status">
             <tr id="select_tr_${counselListVO.idx}">
                 <td><a href="javascript:fcCounsel_procForm('${counselListVO.idx}')"><c:out value="${counselListVO.customerKey}"></c:out></a></td>
                 <td><c:out value="${counselListVO.customerName}"></c:out></td>
                 <td><c:out value="${counselListVO.customerId}"></c:out></td>
                 <td><c:out value="${counselListVO.counselDateTime}"></c:out></td>
                 <td><c:out value="${counselListVO.counsel}"></c:out></td>
                 <td><c:out value="${counselListVO.counselState}"></c:out></td>
                 <td><c:out value="${counselListVO.counselResultDateTime}"></c:out></td>
                 <td><c:out value="${counselListVO.userName}(${counselListVO.userId})"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty counselList}">
           <tr>
               <td colspan='8' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageCounselPageList" totalCount="${totalCount}" curPage="${counselConVO.curPage}" rowCount="${counselConVO.rowCount}" />
     <!-- //페이징 -->

    