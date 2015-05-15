<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

	</script>
</head>
<body>
	   <form:form commandName="commentVO" name="commentListForm" method="post" action="" >
	      <table class="table table-striped">
	      	<colgroup>
		     <col width="3%" />
	         <col width="15%" />
	         <col width="20%" />
	         <col width="*" />
	        </colgroup>
		    <thead>
		      <tr>
		        <th class='text-center'>no</th>
	            <th class='text-center'>작성자</th>
	            <th class='text-center'>작성일시</th>
	            <th class='text-center'>메모</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<c:if test="${!empty commentList}">
	             <c:forEach items="${commentList}" var="commentVO" varStatus="status">
	             <tr id="select_tr_${commentVO.idx}">
	                 <td class='text-left'><c:out value="${status.count}"></c:out></td>
	                 <td class='text-center'><c:out value="${commentVO.commentUserName}"></c:out></td>
	                 <td class='text-center'><c:out value="${commentVO.commentDateTime}"></c:out></td>
	                 <td class='text-left'><c:out value="${commentVO.comment}"></c:out></td>
	                 </tr>
	             </c:forEach>
	            </c:if>
	           <c:if test="${empty commentList}">
	              <tr>
	                  <td colspan='4' class='text-center'>조회된 데이터가 없습니다.</td>
	              </tr>
	          </c:if>
		    </tbody>
		  </table>
		</form:form>
</body>
</html>

    
