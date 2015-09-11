<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <style>

 .thead { height:32px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:200px; .height:190px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
<SCRIPT>

//답글추가
function fcReply_add(){

	if($("#comment").val()==''){
		alert('추가할 답글내용을 입력하세요!');
		$("#comment").focus();
		return;
	}

	if (confirm('답글을 추가 하시겠습니까?')){ 
	
	commonDim(true);
	
	    $.ajax({
	        type: "POST",
	           url:  "<%= request.getContextPath() %>/smart/replyddlist",
	                data:$("#replyForm").serialize(),
	           success: function(result) {
	               commonDim(false);
	               $("#comunityProcessForm").html(result);

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
	<h5><strong><font style="color:#428bca"><span class="glyphicon glyphicon-book"></span>답글추가 &nbsp; 
   				</font></strong></h5>
	  <form:form commandName="comunityVO" id="replyForm" name="replyForm" method="post" action="" >
	  <input type="hidden" name="upidx"          id="upidx"         value="${idx}"  />
	  <br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:120px" >커뮤니티 글</th>
          <th><input type="text" class="form-control" value="${comment}" placeholder="" disabled /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >추가 커뮤니티</th>
          <th>
          <div class="form-inline">
          <input type="text" class="form-control" id="comment" style="width:520px"  name="comment" style='ime-mode:active;' maxlength="200" value="" placeholder="커뮤니티"  />
          <button id="memoinfobtn" type="button" class="btn btn-info" onClick="fcReply_add()" >추가</button>
    	  </div>
          </th>
      	</tr>
	  </table>
	  </form:form>
  <!-- //조회 -->
	   <form:form commandName="comunityVO" name="replyListForm" method="post" action="" >
	      <div class="thead">
		   <table cellspacing="0" border="0" summary="답글리스트" class="table table-bordered tbl_type" style="table-layout: fixed">
		    <caption></caption>
	 		<colgroup>
		     <col width="50px" />
	         <col width="100px" />
	         <col width="150px" />
	         <col width="*" />
	        </colgroup>
		    <thead>
		      <tr style="background-color:#E6F3FF">
		        <th class='text-center'>no</th>
	            <th class='text-center'>작성자</th>
	            <th class='text-center'>작성일시</th>
	            <th class='text-center'>답글</th>
		      </tr>
		    </thead>
		  </table>
		  </div>
		  <div class="tbody">
		    <table cellspacing="0" border="0" summary="" class="table table-bordered tbl_type" style="table-layout: fixed"> 
		      <caption></caption>
		      <colgroup>
		      <col width="50px" />
	          <col width="100px" />
	          <col width="150px" />
	          <col width="*" />
		      </colgroup>
		       <!-- :: loop :: -->
		                <!--리스트---------------->
		      <tbody>
		        <c:if test="${!empty comunityReply}">
		             <c:forEach items="${comunityReply}" var="comunityVO" varStatus="status">
		             <tr id="select_tr_${comunityVO.idx}">
		                 <td class='text-left'><c:out value="${comunityReply.size()-(status.count-1)}"></c:out></td>
		                 <td class='text-center'><c:out value="${comunityVO.customerKey}"></c:out></td>
		                 <td class='text-center'><c:out value="${comunityVO.commentDateTime}"></c:out></td>
		                 <td class='text-left'><c:out value="${comunityVO.comment}"></c:out></td>
		                 </tr>
		             </c:forEach>
		            </c:if>
		           <c:if test="${empty comunityReply}">
		              <tr>
		                  <td colspan='4' class='text-center'>조회된 데이터가 없습니다.</td>
		              </tr>
		          </c:if>
		    </tbody>
		   </table>
		  </div>
			</form:form> 
</div>
<script>
$('#comment').focus(1); 
</script>
</body>