<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
 <style>

 .thead { overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:685px; .height:675px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
	<script>

	</script>
</head>
<body>
	   <form:form commandName="commentVO" name="commentListForm" method="post" action="" >
	   
	   
	   <div class="thead">
	   <table cellspacing="0" border="0" summary="메모리스트" class="table table-striped tbl_type" style="table-layout: fixed">
	    <caption>발주대상리스트</caption>
 		<colgroup>
	     <col width="3%" />
         <col width="15%" />
         <col width="20%" />
         <col width="*" />
        </colgroup>
	    <thead>
	      <tr>
	        <th class='text-center'>no</th>
            <th class='text-center'>작성자2</th>
            <th class='text-center'>작성일시</th>
            <th class='text-center'>메모</th>
	      </tr>
	    </thead>
	  </table>
	  </div>
	  <div class="tbody">
	    <table cellspacing="0" border="0" summary="발주대상리스트" class="table table-striped tbl_type" style="table-layout: fixed"> 
	      <caption>발주대상리스트</caption>
	      <colgroup>
	      <col width="3%" />
          <col width="15%" />
          <col width="20%" />
          <col width="*" />
	      </colgroup>
	       <!-- :: loop :: -->
	                <!--리스트---------------->
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
	  </div>

	</form:form>
</body>
</html>

    
