<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
// 리스트 조회
function fcEtc_listSearch(){

	commonDim(true);
	
    $.ajax({
        type: "POST",
           url:  "<%= request.getContextPath() %>/order/etclist",
                data:$("#etcForm").serialize(),
           success: function(result) {
               commonDim(false);
               $("#etcList").html(result);
           },
           error:function() {
               commonDim(false);
           }
    });
}
//메모추가
function fcEtc_add(){

	if($("#comment").val()==''){
		alert('추가할 비고 내용을 입력하세요!');
		$("#comment").focus();
		return;
	}

	if (confirm('비고 내용을 추가 하시겠습니까?')){ 
	
	commonDim(true);
	
	    $.ajax({
	        type: "POST",
	           url:  "<%= request.getContextPath() %>/order/etcaddlist",
	                data:$("#etcForm").serialize(),
	           success: function(result) {
	               commonDim(false);
	               $("#etcList").html(result);
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
	<h5><strong><font style="color:#428bca"><span class="glyphicon glyphicon-book"></span>${productName} &nbsp; 
   				<button id="etcaddbtn" type="button" class="btn btn-xs btn-info" onClick="fcEtc_add()" >추가</button>
    			</font></strong></h5>
	  <form:form commandName="commentVO" id="etcForm" name="etcForm" method="post" action="" >
	  <input type="hidden" name="orderCode"          id="orderCode"         value="${orderCode}"  />
	  <input type="hidden" name="productCode"          id="productCode"         value="${productCode}"  />
	  <input type="hidden" name="commentCategory"          id="commentCategory"         value="04"  />
	  <br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:120px" >발주서 비고</th>
          <th><input type="text" class="form-control" value="${etc}" placeholder="비고" disabled /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >추가 비고</th>
          <th><input type="text" class="form-control" id="comment" name="comment"  value="" placeholder="비고"  /></th>
      	</tr>
	  </table>
	  </form:form>
  <!-- //조회 -->
  <!-- 조회결과리스트 -->
  <div id=etcList>
  </div>
  <!-- //조회결과리스트 -->
</div>
<script>
fcEtc_listSearch();
</script>
</body>