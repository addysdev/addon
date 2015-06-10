<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageWorkManagePageList(page) {
        document.workManageConForm.curPage.value = page;
        var dataParam = $("#workManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/workhisotrypagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#workHistoryPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="workHistoryVO" name="workHistoryPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>작업일시</th>
            <th class='text-center'>작업자</th>
            <th class='text-center'>작업구분</th>
            <th class='text-center'>작업명</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty workList}">
             <c:forEach items="${workList}" var="workVO" varStatus="status">
             <tr id="select_tr_${workVO.idx}">
                 <td class='text-center'><c:out value="${workVO.workDateTime}"></c:out></td>
                 <td class='text-center'><c:out value="${workVO.workUserName}(${companyManageVO.workUserId})"></c:out></td>
                 <td class='text-center'><c:out value="${workVO.workCategoryName}"></c:out></td>
                 <td class='text-center'><c:out value="${workVO.workCodeName}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty workList}">
           <tr>
           	<td colspan='4' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageWorkManagePageList" totalCount="${totalCount}" curPage="${workConVO.curPage}" rowCount="${workConVO.rowCount}" />
     <!-- //페이징 -->

    