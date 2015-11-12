<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageComunityPageList(page) {
        document.comunityConForm.curPage.value = page;
        var dataParam = $("#comunityConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/smart/comunitypagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#comunityPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>

     <form:form commandName="comunityVO" name="comunityPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th>핸드폰번호</th>
            <th>댓글수</th>
            <th>talk내용</th>
            <th>직원여부</th>
            <th>등록일시</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty comunityList}">
             <c:forEach items="${comunityList}" var="comunityListVO" varStatus="status">
             <tr id="select_tr_${comunityListVO.idx}">
             <c:choose>
	    		<c:when test="${comunityListVO.commentType == 'Y'}">
                 <td><c:out value="${comunityListVO.customerKey}"></c:out></td>
                 </c:when>
					<c:otherwise>
					   <td><a href="javascript:fcComunity_procForm('${comunityListVO.idx}','${comunityListVO.comment}')"><c:out value="${comunityListVO.customerKey}"></c:out></a></td>
					</c:otherwise>
				</c:choose>
                 <td><c:out value="${comunityListVO.commentCnt}"></c:out></td>
                 <td><c:out value="${comunityListVO.comment}"></c:out></td>
                 <td><c:out value="${comunityListVO.commentType}"></c:out></td>
                 <td><c:out value="${comunityListVO.commentDateTime}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty comunityList}">
           <tr>
               <td colspan='5' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageComunityPageList" totalCount="${totalCount}" curPage="${comunityConVO.curPage}" rowCount="${comunityConVO.rowCount}" />
     <!-- //페이징 -->

    