<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//AS 등록
		function fAs_proc(){
			
			var frm=document.asProcForm;
			
			if(frm.asResult.value==''){
				alert('AS처리 내용을 입력하세요');
				frm.asResult.focus();
				return;
			}

			if (confirm('AS 처리를 진행 하시겠습니까?\n처리된 내용은 SMS로 고객 문자 발송됩니다.')){ 
			
			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/asprocess",
			           data:$("#asProcForm").serialize(),
			           success: function(result) {
	
							if(result>0){
								 alert('AS처리를 완료했습니다.');
							} else{
								 alert('AS처리를 실패했습니다.');
							}
							
							$('#asProcessForm').dialog('close');
							fcAs_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('AS처리를 실패했습니다.');
			        	   $('#asProcessForm').dialog('close');
			           }
			    });
			}
		}
		
		function asStateChekc(){

			var idx='${asVO.idx}';
			
			if('${asVO.asState}'!='03' && '${strUserId}'=='${asVO.asStartUserId}'){
			
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/asstateupdate?asState=01&idx="+idx,
			           success: function(result) {
	
							if(result=='1'){
								//성공
							} else{
								 alert('AS상태 변경을 실패했습니다.\n관리자에게 문의하세요');
								 $('#asProcessForm').dialog('close');
								 fcAs_listSearch();
							}
	
			           },
			           error:function(){
			        	   
			        	   alert('AS상태 변경을 실패했습니다.\n관리자에게 문의하세요');
			        	   $('#asProcessForm').dialog('close');
			        	   fcAs_listSearch();
			           }
			    });
			
			}
			
		}
		function AutoResize(img){
	    	   foto1= new Image();
	    	   foto1.src=(img);
	    	   Controlla(img);
	    	 }
	  	 function Controlla(img){
	  	   if((foto1.width!=0)&&(foto1.height!=0)){
	  	     viewFoto(img);
	  	   }
	  	   else{
	  	     funzione="Controlla('"+img+"')";
	  	     intervallo=setTimeout(funzione,20);
	  	   }
	  	 }
	   	 function viewFoto(img){
	   	   largh=foto1.width-20;
	   	   altez=foto1.height-20;
	   	   stringa="width="+largh+",height="+altez;
	   	  // finestra=window.open(img,"",stringa);
	   	  
		   	var h=screen.height-(screen.height*(8.5/100));
			var s=screen.width;
			
			if(h<s){
				s=h;
			}
			
			if(s<largh){
				largh=s;
			}

	   	  	var url='<%= request.getContextPath() %>/smart/imageview';
	   	   
		   	$('#imageView').dialog({
		        resizable : false, //사이즈 변경 불가능
		        draggable : true, //드래그 불가능
		        closeOnEscape : true, //ESC 버튼 눌렀을때 종료
		        position : 'center',
		        width : largh,
		        modal : true, //주위를 어둡게
		
		        open:function(){
		            //팝업 가져올 url
		        	 $(this).load(url+'?imageurl='+img);
		
		        }
		        ,close:function(){
		            $('#imageView').empty();
		        }
		    });
	   	   
	   	 }
	   	 
	  // 리스트 조회
	     function fcAs_HistoryList(){
		
		  var asNo='${asVO.asNo}';
		  
		     commonDim(true);
		     
	         $.ajax({
	             type: "POST",
	                url:  "<%= request.getContextPath() %>/smart/ashitorylist?asNo="+asNo,
	                success: function(result) {
	                    commonDim(false);
	                    $("#asHistoryList").html(result);
	                },
	                error:function() {
	                    commonDim(false);
	                }
	         });
	     }
	  
	  function fcAs_MainTransfer(){
		  
	  }
	</script>
  </head>
  <body>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="asVO"  id="asProcForm" name="asProcForm" method="post" target="file_result" >
       <input type="hidden" name="asNo"             id="asNo"            value="${asVO.asNo}" />
	   <input type="hidden" name="groupId"             id="groupId"            value="${strGroupId}" />
	   <input type="hidden" name="asStartUserId"             id="asStartUserId"            value="${strUserId}" />
	   <input type="hidden" name="productCode"             id="productCode"            value="${productCode}" />
	      <tr>
	      <div style="position:absolute; left:30px" > 
	                      ※ A/S 접수 기본정보
          </div >
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	  	<tr>
	      <th class='text-center' style="background-color:#E6F3FF">접수번호</th>
          <th class='text-center'>${asVO.asNo}</th>
          <th class='text-center' style="background-color:#E6F3FF">접수일</th>
          <th class='text-center'>${asVO.asStartDateTime}</th>
          <th class='text-center' style="background-color:#E6F3FF" >접수지점</th>
          <th class='text-center'>${asVO.groupName}</th>  
          <th class='text-center' style="background-color:#E6F3FF">담당자</th>
          <th class='text-center'>${asVO.asStartUserName}</th>  
      	</tr>
      	</table>
      	<table class="table table-bordered" >
	 	<tr>
          <th rowspan='3' class='text-center' style="background-color:#E6F3FF">고객정보</th>
          <th class='text-center' style="background-color:#E6F3FF" >의뢰인</th>
          <th class='text-left' colspan="2" >${asVO.customerName}</th>
          <th class='text-center'  style="background-color:#E6F3FF;width:140px" >의뢰인 연락처</th>
          <th class='text-left' colspan="2" >${asVO.customerKey}</th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >수령인</th>
          <th class=''text-left'' colspan="2" >${asVO.receiveName}</th>
          <th class='text-center'  style="background-color:#E6F3FF" >수령인 연락처</th>
          <th class='text-left' >${asVO.receiveTelNo}</th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >수령방법</th>
          <th colspan="4" class='text-left'>
          
          <c:choose>
    		  	<c:when test="${asVO.receiveType=='02'}">
    		  		택배(퀵) 수령  [수령주소] : ${asVO.receiveAddress} ${asVO.receiveAddressDetail}
            	</c:when>
				<c:otherwise>
					매장 수령
				</c:otherwise>
		  </c:choose>

          </th>
      	</tr>
      	<tr>
          <th rowspan='5' class='text-center' style="background-color:#E6F3FF">상품정보</th>
          <th class='text-center'  style="background-color:#E6F3FF" >브랜드명</th>
          <th class='text-left'>${asVO.group1Name}</th>
      	  <th class='text-center'  style="background-color:#E6F3FF" >모델명</th>
          <th class='text-left' colspan="2" >${asVO.productName}</th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >A/S정책</th>
          <th colspan="4" class='text-left'>
          ${asVO.asPolicy}</th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >증상</th>
          <th colspan="3" class='text-left' style="width:400px">
          ${asVO.asDetail}
          </th>
          <th class='text-center'><a href="javascript:AutoResize('${asVO.asImage}')"><img src='${asVO.asImage}' width="80" height="80" /></a></th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >의뢰인<br>요청사항</th>
          <th colspan="4" class='text-left'>
          ${asVO.customerRequest}
          </th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >구입일</th>
          <th class='text-left' colspan="2">
		  ${asVO.purchaseDate}
		  </th>
          <th class='text-center'  style="background-color:#E6F3FF" >영수증</th>
          <th class='text-center'><a href="javascript:AutoResize('${asVO.receiptImage}')"><img src='${asVO.receiptImage}' width="30" height="30" /></a></th>
      	</tr>
      	</table>
      	<table class="table table-bordered" >
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:130px">완료예정일</th>
          <th class='text-left' style="width:90px">
          ${asVO.asTargetDate}
          </th>
          <th class='text-center' style="background-color:#E6F3FF;width:130px">담당자<br>의견</th>
          <th class='text-left'>${asVO.memo}</th>
      	</tr>
	  </table>
	  </form:form>
        <c:choose>
   		  	<c:when test="${asVO.asState=='01' || asVO.asState=='02' || asVO.asState=='03'}">
   		  	  <tr>
		      	<div style="position:absolute; left:30px" > 
		         	※A/S처리 결과
		        </div >
		      </tr>
		      <br><br>
   		  		<table class="table table-bordered" >
			      	<tr>
			          <th class='text-center' style="background-color:#E6F3FF;width:130px">처리완료일</th>
			          <th class='text-left' style="width:90px">
			          ${asVO.asCompleteDateTime}
			          </th>
			          <th class='text-center' style="background-color:#E6F3FF;width:130px">처리결과</th>
			          <th class='text-left' style="width:90px">${asVO.asStateTrans}</th>
			          <th class='text-left'>${asVO.asResult}</th>
			      	</tr>
				</table>
           	</c:when>
			<c:otherwise>
			  <tr>
		      	<div style="position:absolute; left:30px" > 
		         	※A/S처리 및 이력
		        </div >
		        <div style="position:absolute; right:30px" > 
		       		<button type="button" class="btn btn-success" onClick="fcAs_MainTransfer()">배송</button>
		        </div>
		      </tr>
		      <br><br>
		        <div id="asHistoryList"></div>
				<script>
				 fcAs_HistoryList();
				</script>
			</c:otherwise>
	    </c:choose>

	 </div>
  </body>
</html>


