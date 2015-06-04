<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <style>

 .thead { height:32px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:200px; .height:190px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
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
	               $("#etcManage").html(result);

	               if('${idx}'==0){
	            	   
	            	   var ecnt=document.all('etcCnt').innerText;
		               ecnt++;
		               document.all('etcCnt').innerText=ecnt;
		               //alert(document.all('etcAdd').style.backgroundColor);
		               document.all('etcAdd').style.backgroundColor='#FEE2B4';
	
	               }else{
	            	   
	            	   var cnt='${idx}'-1;

	            	   var ecnt=document.all('etcCnt')[cnt].innerText;
		               ecnt++;
		               document.all('etcCnt')[cnt].innerText=ecnt; 

		              // alert(document.all('etcAdd')[cnt]);
		       		  // alert(document.all('etcAdd')[cnt].style);
		       		   
		               document.all('etcAdd')[cnt].style.backgroundColor='#FEE2B4';
		              // alert(document.all('etcAdd')[cnt].style.backgroundColor);
			             
	            	   
	               }
	               
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
	  <input type="hidden" name="commentCategory"          id="commentCategory"         value="${category}"  />
	  <br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:120px" >비고</th>
          <th><input type="text" class="form-control" value="${etc}" placeholder="비고" disabled /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >추가 비고</th>
          <th><input type="text" class="form-control" id="comment"  name="comment" style='ime-mode:active;' maxlength="200" value="" placeholder="비고"  /></th>
      	</tr>
	  </table>
	  </form:form>
  <!-- //조회 -->
  
  <form:form commandName="commentVO" name="commentListForm" method="post" action="" >
  		<div class="thead">
		   <table cellspacing="0" border="0" summary="" class="table table-bordered tbl_type" style="table-layout: fixed">
		    <caption></caption>
	 		<colgroup>
		     <col width="50px" />
	         <col width="80px" />
	         <col width="150px" />
	         <col width="*" />
	        </colgroup>
		    <thead>
		      <tr style="background-color:#E6F3FF">
		        <th class='text-center'>no</th>
	            <th class='text-center'>작성자</th>
	            <th class='text-center'>작성일시</th>
	            <th class='text-center'>비고</th>
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
		
  <!-- 조회결과리스트 -->
  <div id=etcList></div>
  <!-- //조회결과리스트 -->
</div>
<script>
$('#comment').focus(1); 
</script>
</body>