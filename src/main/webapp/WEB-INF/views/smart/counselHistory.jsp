<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <style>

 .thead { height:32px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:200px; .height:190px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
<SCRIPT>

//이력추가
function fcReply_add(){

	if($("#counsel").val()==''){
		alert('추가할 이력내용을 입력하세요!');
		$("#counsel").focus();
		return;
	}

	if (confirm('상담이력을 추가 하시겠습니까?')){ 
	
	commonDim(true);
	
	    $.ajax({
	        type: "POST",
	           url:  "<%= request.getContextPath() %>/smart/counselreplyddlist",
	                data:$("#replyForm").serialize(),
	           success: function(result) {
	               commonDim(false);
	               $("#counselHistory").html(result);

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
	<h5><strong><font style="color:#428bca"><span class="glyphicon glyphicon-book"></span>이력추가 &nbsp; 
   				</font></strong></h5>
	  <form:form commandName="counselVO" id="replyForm" name="replyForm" method="post" action="" >
	  <input type="hidden" name="upidx"          id="upidx"         value="${idx}"  />
	  <br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:120px" >상담 글</th>
          <th><input type="text" class="form-control" value="${counsel}" placeholder="" disabled /></th>
          <input type="hidden" id="counsel" name="counsel" value="${counsel}">
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >상담 이력</th>
          <th>
          <div class="form-inline">
          <input type="text" class="form-control" id="counselHistory" style="width:520px"  name="counselHistory" style='ime-mode:active;' maxlength="200" value="" placeholder="상담이력"  />
          <button id="memoinfobtn" type="button" class="btn btn-info" onClick="fcReply_add()" >추가</button>
    	  </div>
          </th>
      	</tr>
	  </table>
	  </form:form>
  <!-- //조회 -->
	   <form:form commandName="comunityVO" name="replyListForm" method="post" action="" >
	      <div class="thead">
		   <table cellspacing="0" border="0" summary="이력리스트" class="table table-bordered tbl_type" style="table-layout: fixed">
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
	            <th class='text-center'>이력</th>
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
		        <c:if test="${!empty counselReply}">
		             <c:forEach items="${counselReply}" var="counselVO" varStatus="status">
		             <tr id="select_tr_${counselVO.idx}">
		                 <td class='text-left'><c:out value="${counselReply.size()-(status.count-1)}"></c:out></td>
		                 <td class='text-center'><c:out value="${counselVO.userName}(${counselVO.userId})"></c:out></td>
		                 <td class='text-center'><c:out value="${counselVO.counselHistoryDateTime}"></c:out></td>
		                 <td class='text-left'><c:out value="${counselVO.counselHistory}"></c:out></td>
		                 </tr>
		             </c:forEach>
		            </c:if>
		           <c:if test="${empty counselReply}">
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
$('#counsel').focus(1); 
</script>
</body>