<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
 <style>

 .thead { height:32px;  overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:300px; .height:290px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
	<script>

	</script>
</head>
<body>
<div class="container-fluid">
 <form:form commandName="commentVO" name="commentListForm" method="post" action="" >
		<div class="thead">
		   <table cellspacing="0" border="0" summary="" class="table table-bordered tbl_type" style="table-layout: fixed">
		    <caption>발주대상리스트</caption>
	 		<colgroup>
		     <col width="50px" />
	         <col width="80px" />
	         <col width="150px" />
	         <col width="80px" />
	         <col width="*" />
	        </colgroup>
		    <thead>
		      <tr style="background-color:#E6F3FF">
		        <th class='text-center'>no</th>
	            <th class='text-center'>작성자</th>
	            <th class='text-center'>작성일시</th>
	            <th class='text-center'>구분</th>
	            <th class='text-center'>사유</th>
		      </tr>
		    </thead>
		  </table>
		  </div>
		  <div class="tbody">
		    <table cellspacing="0" border="0" summary="" class="table table-bordered tbl_type" style="table-layout: fixed"> 
		      <caption></caption>
		      <colgroup>
		      <col width="50px" />
	          <col width="80px" />
	          <col width="150px" />
	          <col width="80px" />
	          <col width="*" />
		      </colgroup>
		       <!-- :: loop :: -->
		                <!--리스트---------------->
		      <tbody>
		       <c:if test="${!empty commentList}">
	             <c:forEach items="${commentList}" var="commentVO" varStatus="status">
	             <tr id="select_tr_${commentVO.idx}">
	                 <td class='text-left'><c:out value="${commentList.size()-(status.count-1)}"></c:out></td>
	                 <td class='text-center'><c:out value="${commentVO.commentUserName}"></c:out></td>
	                 <td class='text-center'><c:out value="${commentVO.commentDateTime}"></c:out></td>
	                 <td class='text-left'><c:out value="${commentVO.commentTypeView}"></c:out></td>
	                 <td class='text-left'><c:out value="${commentVO.comment}"></c:out></td>
	                 </tr>
	             </c:forEach>
	            </c:if>
	           <c:if test="${empty commentList}">
	              <tr>
	                  <td colspan='5' class='text-center'>조회된 데이터가 없습니다.</td>
	              </tr>
	          </c:if>
		    </tbody>
		   </table>
		  </div>
	
</form:form>
</div>
</body>
</html>

    
