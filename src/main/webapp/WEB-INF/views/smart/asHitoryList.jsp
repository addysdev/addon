<%@ include file="/WEB-INF/views/addys/base.jsp" %>
      <form:form commandName="AsVO" name="asListForm" method="post" action="" >
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th>처리상태</th>
	        <th>처리일시</th>
	        <th>처리이력</th>
	        <th>센터접수번호</th>
	        <th>센터문서</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty asHistoryList}">
             <c:forEach items="${asHistoryList}" var="asHistoryVO" varStatus="status">
             <tr id="select_tr_${asHistoryVO.asNo}">
                 <td><a href="javascript:fcAs_procForm('${asHistoryVO.asSubState}')"><c:out value="${asHistoryVO.asSubStateTrans}"></c:out></a></td>
                 <td><c:out value="${asHistoryVO.asHistoryDateTime}"></c:out></td>
                 <td><c:out value="${asHistoryVO.asHistory}"></c:out></td>
                 <td><c:out value="${asHistoryVO.centerAsNo}"></c:out></td>
                 <td>
                 <c:if test="${asHistoryVO.centerImage!=null}">
                 <a href="javascript:AutoResize('${asHistoryVO.centerImage}')"><img src='${asVO.receiptImage}' width="20" height="20" /></a>
				 </c:if>
                 </td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty asHistoryList}">
           <tr>
               <td colspan='5' class='text-center'>처리 이력이 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

