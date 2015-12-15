<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>

		//상담 등록
		function fCounsel_proc(){
			
			var frm=document.counselProcForm;
			
			if(frm.counselResult.value==''){
				alert('상담처리 내용을 입력하세요');
				frm.counselResult.focus();
				return;
			}

			if (confirm('상담 처리를 진행 하시겠습니까?\n처리된 내용은 SMS로 고객 문자 발송됩니다.')){ 
			
			    $.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/counselprocess",
			           data:$("#counselProcForm").serialize(),
			           success: function(result) {
	
							if(result>0){
								 alert('상담처리를 완료했습니다.');
							} else{
								 alert('상담처리를 실패했습니다.');
							}
							
							$('#counselProcessForm').dialog('close');
							fcCounsel_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('상담처리를 실패했습니다.');
			        	   $('#counselProcessForm').dialog('close');
			           }
			    });
			}
		}
		
		function fcCounsel_close(){
			
			commonDim(false);
			
			alert('처리가 완료되었습니다.');
			$('#counselProcessForm').dialog('close');
			fcCounsel_listSearch();

		}
		
		function fCounsel_proc_multiRegist(){
			
		    var url;
			var frm=document.counselProcForm;
		    var fileName = '';
		    var pos = '';
		    var ln = '';
		    var gap = '';
		    var gap1 = '';
		    
		    var counselResult=frm.counselResult.value;
		    var customerKey='${counselVO.customerKey}';
		    var idx='${counselVO.idx}';
			
		    url="<%= request.getContextPath() %>/smart/counselprocessmulti?counselResult="+counselResult+"&customerKey="+customerKey+"&idx="+idx;
			
		    if($("#files").val() != ''){
		    
		        fileName = document.all.files.value;
		   
		        pos = fileName.lastIndexOf("\\");
		        ln = fileName.lastIndexOf("\.");
		        gap = fileName.substring(pos + 1, ln);
		        gap1 = fileName.substring(ln+1);
		        
		        if(gap1=="jpg" || gap1=="JPG" || gap1=="gif" || gap1=="GIF" || gap1=="png" || gap1=="PNG"){//
		            url="<%= request.getContextPath() %>/smart/counselprocessmulti?fileName="+gap+"&extension="+gap1+"&counselResult="+counselResult+"&customerKey="+customerKey+"&idx="+idx;
		        }else {
		        	alert("이미지 파일만 등록 부탁드립니다.");
		            return;
		        }
		        
		    }
		    
		    if(frm.counselResult.value==''){
				alert('상담처리결과 내용을 입력하세요');
				frm.counselResult.focus();
				return;
			}

		    if (confirm('상담 처리를 진행 하시겠습니까?\n처리된 내용은 SMS로 고객 문자 발송됩니다.')){ 
			    commonDim(true);
			    frm.action = url;
			    frm.target="file_result";
	
			    frm.submit();   
		    }
		}
		
		function counsetStateChekc(){
			
			return;
			var idx='${counselVO.idx}';
			
			if('${counselVO.counselState}'!='03' && '${strUserId}'=='${counselVO.stateUpdateUserId}'){
			
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/counselstateupdate?counselState=01&idx="+idx,
			           success: function(result) {
	
							if(result=='1'){
								//성공
							} else{
								 alert('상담상태 변경을 실패했습니다.\n관리자에게 문의하세요');
								 $('#counselProcessForm').dialog('close');
								 fcCounsel_listSearch();
							}
	
			           },
			           error:function(){
			        	   
			        	   alert('상담상태 변경을 실패했습니다.\n관리자에게 문의하세요');
			        	   $('#counselProcessForm').dialog('close');
			        	   fcCounsel_listSearch();
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

	   	  	var url='<%= request.getContextPath() %>/smart/imageview';
	   
		   	$('#imageView').dialog({
		        resizable : false, //사이즈 변경 불가능
		        draggable : true, //드래그 불가능
		        closeOnEscape : false, //ESC 버튼 눌렀을때 종료
		        ////position : 'center',
		        width : largh,
		        height : altez,
		        modal : true, //주위를 어둡게
		        istitle : false,
		
		        open:function(){
		            //팝업 가져올 url
		        	 $(this).load(url+'?imageurl='+img);
		
		        }
		        ,close:function(){
		            $('#imageView').empty();
		        }
		    });
	   	   
	   	 }
	</script>
  </head>
  <body>
	<div class="container-fluid">
	  <iframe id="file_result" name="file_result" style="display: none" ></iframe>
      <form:form class="form-inline" commandName="counselVO" id="counselProcForm" name="counselProcForm"  method="post" target="file_result" enctype="multipart/form-data">
      	<input type="hidden" id="idx" name="idx" value="${counselVO.idx}" >
      	<input type="hidden" id="counselState" name="counselState" value="03" >
      	<input type="hidden" id="customerKey" name="customerKey" value="${counselVO.customerKey}" >
	    <div class="form-group">
		    <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >고객키</th>
	          <th class='text-left'  width="250px"  >
	          <div class="form-inline">
	          ${counselVO.customerKey}
    	      </div> 
	          </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >고객명</th>
	          <th class='text-left'>${counselVO.customerName}</th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >상담요청일자</th>
	          <th class='text-left'>${counselVO.counselDateTime}</th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >상담내용</th>
				<th class='text-left'>${counselVO.counsel}
				<c:if test="${counselVO.counselImage!=null}">
				    <br><br>
					<a href="javascript:AutoResize('${counselVO.counselImage}')"><img src="${counselVO.counselImage}" width="50px" hight="50px" alt="상품이미지"></a>
				 </c:if>
				</th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" ><span class="glyphicon glyphicon-asterisk"></span>상담처리</th>
	            <c:choose>
		    		<c:when test="${counselVO.counselState=='03'}">
                		 <th class='text-left'>${counselVO.counselResult}</th>
                	</c:when>
					<c:otherwise>
					     <th class='text-left'><textarea style='width:210px;height:110px;ime-mode:active;' row="6" class="form-control" id="counselResult" maxlength="200" name="counselResult"  value="${counselVO.counselResult}" >${counselVO.counselResult}</textarea></th>
                   </c:otherwise>
				</c:choose>
	      	</tr>
	      
	           <c:choose>
		    		<c:when test="${counselVO.counselState!='03'}">
		    			<tr>
				          <th class='text-center' style="background-color:#E6F3FF" >파일첨부</th>
			              <th class='text-left'><input type="file"  id="files" name="files" ></th>     		 	         
				      	</tr>
                	</c:when>
					<c:otherwise>
					       <c:if test="${counselVO.counselResultImage!=null}">
						    <tr>
				              <th class='text-center' style="background-color:#E6F3FF" >답변이미지</th>
							   <th class='text-left'><a href="javascript:AutoResize('${counselVO.counselResultImage}')"><img src="${counselVO.counselResultImage}" width="50px" hight="50px" alt="상품이미지"></a>
							   </th>
						    </tr>
						   </c:if>
                   </c:otherwise>
				</c:choose>

		  </table>
		   <c:if test="${counselVO.counselState!='03'}">
          <td><button type="button" class="btn btn-primary" onClick="javascript:fCounsel_proc_multiRegist()">처리</button></td>
           </c:if>
	    </div>
	  </form:form>
	</div>
  </body>
</html>
<script>

document.counselProcForm.counselResult.focus();
</script>
 

