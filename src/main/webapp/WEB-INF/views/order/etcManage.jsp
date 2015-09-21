<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <style>

 .thead { height:32px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:200px; .height:190px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

/* 서브타이틀 영역 (.sub_title) */
	.sub_title { margin-bottom:20px; width:100%; height:43px; border-bottom:1px solid #ccc; }
	.sub_title .titleP { position:relative; left:0; float:left; margin-left:-7px; padding-left:31px; _padding-top:3px; height:28px; _height:25px; background:url(../images/sub/icon_title.gif) no-repeat; font-family:Malgun Gothic, "맑은 고딕", "돋움", dotum, verdana, Tahoma, sans-serif; font-size:16px; color:#2c3546; font-weight:bold; letter-spacing:-1px; }
	.seachNm { float:left; margin:1px 0 0 20px; padding:4px 0 0 20px; padding-top:5px\9; height:16px; height:15px\9; border:1px solid #cbcbcb; background:#eef0f4; }
	.sub_title .seachNm li { display:inline; padding:0 15px 0 8px; background:url(../images/sub/icon_01.gif) no-repeat 0 2px; }

    /* 공통부분  */

	body { margin:0; padding:0; } 
		
	div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,form,fieldset,p,button{margin:0;padding:0;}
	body,h1,h2,h3,h4,th,td,input, select, textarea {color:#666; font-family:"돋움", dotum, verdana, Tahoma, sans-serif; font-size:11px; font-weight:normal; padding:0px; margin:0px}
	html { scrollbar-arrow-color: #999; scrollbar-3dlight-color: #e6e6e6; scrollbar-darkshadow-color: #e9e9e9; scrollbar-face-color: #f4f4f0; scrollbar-highlight-color:#FFFFFF; scrollbar-shadow-color: #d0d0d0; scrollbar-track-color: #F2F2F2; }
	hr{display:none;}
	img,fieldset,iframe {border:0;}
	ul,ol,li{list-style:none; margin:0; padding:0;}
	img,input,select,textarea{
		vertical-align:text-top;
	}
	input, label { vertical-align:middle;}
	caption, legend { display: none; }
	em, address { margin:0; padding:0; font-style:normal }
	.vm{vertical-align:middle !important;}
	.bn{border:none !important}
	.chk,.rdo{widows:13px;height:13px;margin:0 !important;padding:0 !important;vertical-align:middle}
	.chk_label{position:relative;top:1px;*top:2px;left:0px}
	.blind{overflow:hidden;position:absolute;visibility:hidden;width:0;height:0;font-size:0;line-height:0}
	
</style>
<!-- Latest compiled and minified CSS-->
<link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">

<!-- Latest compiled JavaScript--> 
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/locales/bootstrap-datetimepicker.kr.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/errorMsg.js"></script>
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
	              // $("#etcManage").html(result);
	               $("#etcList").html(result);

	               if('${idx}'==0){
	            	   
	            	   var ecnt=opener.document.all('etcCnt').innerText;
		               ecnt++;
		               opener.document.all('etcCnt').innerText=ecnt;
		               //alert(document.all('etcAdd').style.backgroundColor);
		               opener.document.all('etcAdd').style.backgroundColor='#FEE2B4';
	
	               }else{
	            	   
	            	   var cnt='${idx}'-1;

	            	   var ecnt=opener.document.all('etcCnt')[cnt].innerText;
		               ecnt++;
		               opener.document.all('etcCnt')[cnt].innerText=ecnt; 

		              // alert(document.all('etcAdd')[cnt]);
		       		  // alert(document.all('etcAdd')[cnt].style);
		       		   
		               opener.document.all('etcAdd')[cnt].style.backgroundColor='#FEE2B4';
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
	<h5><strong><font style="color:#428bca"><span class="glyphicon glyphicon-book"></span>${productName}
    			</font></strong></h5>
	  <form:form commandName="commentVO" id="etcForm" name="etcForm" method="post" action="" >
	  <input type="hidden" name="orderCode"          id="orderCode"         value="${orderCode}"  />
	  <input type="hidden" name="productCode"          id="productCode"         value="${productCode}"  />
	  <input type="hidden" name="companyCode"          id="companyCode"         value="${companyCode}"  />
	  <input type="hidden" name="commentCategory"          id="commentCategory"         value="${category}"  />
	  <input type="hidden" name="title"          id="title"         value="${etc}"  />
	  <br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:120px" >비고</th>
          <th><input type="text" class="form-control" value="${etc}" placeholder="비고" disabled /></th>
      	</tr>
      	<tr>       
          <th class='text-center' style="background-color:#E6F3FF" >추가 비고</th>
          <th>
          <div class="form-inline">
          <input type="text" class="form-control" id="comment" style="width:520px"  name="comment" style='ime-mode:active;' maxlength="200" value="" placeholder="비고"  />
          <button id="etcaddbtn" type="button" class="btn btn-info" onClick="fcEtc_add()" >추가</button>
    	  </div> 
          </th>     
      	</tr>
	  </table>
	  </form:form>
  <!-- //조회 -->
  <!-- 
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

		     <!--  
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
 -->
  <!-- 조회결과리스트 -->
  <div id=etcList></div>
  <!-- //조회결과리스트 -->
</div>
<script>
$('#comment').focus(1); 
fcEtc_listSearch();
</script>
</body>