<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageUserManagePageList(page) {
        document.userManageConForm.curPage.value = page;
        var dataParam = $("#userManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/userpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#userManagePageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>
	<div class="container">
     <form:form commandName="userlistVO" name="userManagePageListForm" method="post" action="" >
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
	        <th>발주상태</th>
            <th>매장ID</th>
            <th>매장명</th>
            <th>업체코드</th>
            <th>업체명</th>
            <th>수량</th>
            <th>기준금액</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty userList}">
             <c:forEach items="${userList}" var="userListVO" varStatus="status">
             <tr id="select_tr_${userListVO.userId}">
                 <td><a href="javascript:fcUserManage_detailSearch('${userListVO.userId}')"><c:out value=""></c:out></a></td>
                 <td><c:out value=""></c:out></td>
                 <td><c:out value=""></c:out></td>
                 <td><c:out value=""></c:out></td>
                 <td><c:out value=""></c:out></td>
                 <td><c:out value="22"></c:out></td>
                 <td><c:out value="5000"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty userList}">
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
        <taglib:paging cbFnc="goPageUserManagePageList" totalCount="${totalCount}" curPage="${userCon.curPage}" rowCount="${userCon.rowCount}" />
        <!-- //페이징 -->
	</div>
    