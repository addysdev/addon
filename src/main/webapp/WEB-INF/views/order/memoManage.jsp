<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
// 리스트 조회
function fcMemo_listSearch(){

	commonDim(true);
	
    $.ajax({
        type: "POST",
           url:  "<%= request.getContextPath() %>/order/memolist",
                data:$("#memoForm").serialize(),
           success: function(result) {
               commonDim(false);
               alert($("#memoList"));
               alert(result);
               $("#memoList").html(result);
           },
           error:function() {
               commonDim(false);
           }
    });
}
//메모추가
function fcMemo_add(){

	if($("#comment").val()==''){
		alert('추가할 메모 내용을 입력하세요!');
		$("#comment").focus();
		return;
	}

	if (confirm('메모를 추가 하시겠습니까?')){ 
	
	commonDim(true);
	
	    $.ajax({
	        type: "POST",
	           url:  "<%= request.getContextPath() %>/order/memoaddlist",
	                data:$("#memoForm").serialize(),
	           success: function(result) {
	               commonDim(false);
	               $("#memoManage").html(result);
	            //   alert(document.all('memoCnt').innerText);
	           },
	           error:function() {
	               commonDim(false);
	           }
	    });
	}
}

</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
	<h5><strong><font style="color:#428bca"><span class="glyphicon glyphicon-book"></span>메모추가 &nbsp; 
   				<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcMemo_add()" >추가</button>
    			</font></strong></h5>
	  <form:form commandName="commentVO" id="memoForm" name="memoForm" method="post" action="" >
	  <input type="hidden" name="orderCode"          id="orderCode"         value="${orderCode}"  />
	  <input type="hidden" name="commentCategory"          id="commentCategory"         value="${category}"  />
	  <br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:120px" >메모</th>
          <th><input type="text" class="form-control" value="${memo}" placeholder="메모" disabled /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >추가 메모</th>
          <th><input type="text" class="form-control" id="comment" name="comment"  value="" placeholder="메모"  /></th>
      	</tr>
	  </table>
	  </form:form>
  <!-- //조회 -->
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
  
  <!-- 조회결과리스트 -->
  <div id=memoList>
  </div>
  <!-- //조회결과리스트 -->
</div>
<script>
//fcMemo_listSearch();
</script>
</body>