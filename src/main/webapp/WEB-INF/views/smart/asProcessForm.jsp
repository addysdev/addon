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
	  
	  var pintYN=false;
	  /*
	   * 화면 POPUP
	   */
	  function tmt_winLaunch(theURL,winName,targetName,features) {
	  	
	  	var targetRandom=Math.random();
	  	eval(winName+"=window.open('"+theURL+"','"+targetRandom+"','"+features+"')");

	  }
	  
	  /*
	   * print 화면 POPUP
	   */
	  function fcAsNo_print(asNo){

	  	pintYN=true;

	  	var frm = document.asProcForm;
	  	var groupId='${strGroupId}';
	  	var groupname=encodeURIComponent('${asVO.groupName}');

	  	var url="<%= request.getContextPath() %>/smart/asnoprint";

	  	frm.action =url; 
	  	frm.method="post";
	  	frm.target='printObj';
	  	frm.submit();
	  }
	  
	  function fcAs_MainTransfer(){
		  
		  var dfrm=document.asProcForm;
		  
	    	if(dfrm.asDeliveryRadio[0].checked==true){
				
				if(dfrm.asTransportCode.value==''){
					alert('택배로 운송처리시 운송회사를 입력하셔야 합니다.');
					return;
				}
				
				if(dfrm.asTransportNo.value==''){
					alert('택배로 운송처리시 운송장 번호를 입력하셔야 합니다.');
					return;
				}
				
			}else{
				
				if(dfrm.asQuickCharge.value==''){
					alert('퀵 운송처리시 퀵 운송 담당자를 입력하셔야 합니다.');
					return;
				}
				
				if(dfrm.asQuickTel.value==''){
					alert('퀵 운송처리시 퀵 운송 연락처를 입력하셔야 합니다.');
					return;
				}
			}
		    
		    if(pintYN==false){
		    	
		    	alert('배송 처리시 접수번호를 프린트 하신후\n배송대상 BOX에 첨부하여 보내시기 바랍니다.\n인쇄버튼을 클릭하여 접수번호를 인쇄 하신 후\n다시 시도하시기 바랍니다.');
		    	return;
		    	
		    }
		    
		    dfrm.asTransport.value=$("#asTransportCode option:selected").text();
		    
		    if (confirm('A/S 처리를 배송상태로 저장 하시겠습니까?\n저장시 A/S대행 접수안내 SMS가 고객님께 발송됩니다.')){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/astransupdate",
			           data:$("#asProcForm").serialize(),
			           success: function(result) {

							if(result=='1'){
								 alert('배송상태로 변경을 성공했습니다.');
							} else{
								 alert('배송상태로 변경을 실패했습니다.');
							}
							
							$('#asProcessForm').dialog('close');
							fcAs_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('배송상태로 변경을 실패했습니다.');
			        	   $('#asProcessForm').dialog('close');
			           }
			    });
				
			}
		  
	  }
	  
	  function fcAs_StateProcess(asNo,asSubState,asHistory){
		  
		  if (confirm('A/S 처리상태를 변경 하시겠습니까?')){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/asstateprocess?asNo="+asNo+"&asSubState="+asSubState+"&asHistory="+encodeURIComponent(asHistory),			  
			           success: function(result) {

							if(result=='1'){
								 alert('A/S 처리상태 변경을 성공했습니다.');
							} else{
								 alert('A/S 처리상태 변경을 실패했습니다.');
							}
							
							$('#asProcessForm').dialog('close');
							fcAs_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('A/S 처리상태 변경을 실패했습니다.');
			        	   $('#asProcessForm').dialog('close');
			           }
			    });
				
			}
		  
		  
	  }
	  function fcAs_CenterStart(asNo){
		  
		  frm=document.asProcForm;
	
		  var centerAsNo=frm.centerAsNo.value;
		  var asHistory='센터접수 확인';
		  
		  if (confirm('센터 접수상태를 저장 하시겠습니까?\n저장시 센터 접수안내 SMS가 고객님께 발송됩니다.')){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/ascenterstart?asNo="+asNo+"&centerAsNo="+centerAsNo+"&asHistory="+encodeURIComponent(asHistory),			  
			           success: function(result) {

							if(result=='1'){
								 alert('A/S 처리상태 변경을 성공했습니다.');
							} else{
								 alert('A/S 처리상태 변경을 실패했습니다.');
							}
							
							$('#asProcessForm').dialog('close');
							fcAs_listSearch();
							
			           },
			           error:function(){
			        	   
			        	   alert('A/S 처리상태 변경을 실패했습니다.');
			        	   $('#asProcessForm').dialog('close');
			           }
			    });
				
			}
	  }
	  
	  function fcAs_CenterEnd(){
		  
		    var url;
		    var frm=document.asProcForm;
		    var files;
		    var fileName ='';
		    var pos = '';
		    var ln = '';
		    var gap = '';
		    var gap1 = '';
		    
		    url="<%= request.getContextPath() %>/smart/ascenterend?fileAttach=N";
			  
		    files = document.all("files");
		    
		    if(files.value != ''){
	
		        fileName = files.value;
		        
		        pos = fileName.lastIndexOf("\\");
		        ln = fileName.lastIndexOf("\.");
		        gap = fileName.substring(pos + 1, ln);
		        gap1 = fileName.substring(ln+1);
	
		        if(gap1=="jpg" || gap1=="JPG" || gap1=="gif" || gap1=="GIF" || gap1=="png" || gap1=="PNG"){//
		            url="<%= request.getContextPath() %>/smart/ascenterend?fileAttach=Y";
		        }else {
		        	alert("이미지 파일만 등록 부탁드립니다.");
		            return;
		        }
		        
		    }

		  if (confirm('센터 처리 내용을 저장 하시겠습니까?')){
				
			  frm.action = url;
			  frm.target="file_result";
			  frm.submit();   
				
			}
		    
	  }
	  
	  function fcAsRegist_close(retVal,asState){
			
			commonDim(false);
			
			if(retVal=='1'){
				alert('센터이력 저장이 성공되었습니다.');
			}else{
				alert('센터이력 저장을 실패했습니다.'); 
			}
			
			$('#asProcessForm').dialog('close');
			fcAs_listSearch();
		}

	  function fcAsDelivery_method(){

	  	if(document.asProcForm.asDeliveryRadio[0].checked==true){
	  		document.asProcForm.asTransportCode.disabled=false;
	  		document.asProcForm.asTransportNo.disabled=false;
	  		document.asProcForm.asQuickCharge.disabled=true;
	  		document.asProcForm.asQuickTel.disabled=true;
	  		document.asProcForm.asQuickCharge.value='';
	  		document.asProcForm.asQuickTel.value='';
	  		document.asProcForm.asDeliveryMethod.value='01';
	  	}else{
	  		document.asProcForm.asTransportCode.disabled=true;
	  		document.asProcForm.asTransportNo.disabled=true;
	  		document.asProcForm.asTransportCode.value='';
	  		document.asProcForm.asTransportNo.value='';
	  		document.asProcForm.asQuickCharge.disabled=false;
	  		document.asProcForm.asQuickTel.disabled=false;
	  		document.asProcForm.asDeliveryMethod.value='02';
	  	}
	  }
	  
	   function fcAs_asTranspath(){
			
			var url=document.asProcForm.asTransurl_Modify.value;
			var transno=document.asProcForm.asTransportNo_Modify.value;
			
			var theURL=url+transno;
			
			var h=700;
			var s=800;

		    tmt_winLaunch(theURL, 'transObj', 'transObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
		
		}
	   
	   function fcModify_astrans(asNo,asDeliveryMethod){

	    	var url='<%= request.getContextPath() %>/smart/astransmodifyform';

	    	$('#asTransManage').dialog({
	            resizable : false, //사이즈 변경 불가능
	            draggable : true, //드래그 불가능
	            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

	            width : 800,
	            height : 180,
	            modal : true, //주위를 어둡게

	            open:function(){
	                //팝업 가져올 url
	                $(this).load(url+'?asNo='+asNo+'&asDeliveryMethod='+asDeliveryMethod);
	               
	                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                    $("#asTransManage").dialog('close');

	                    });
	            }
	            ,close:function(){
	            	$('#asTransManage').empty();
	            }
	        });
	    };
	</script>
  </head>
  <body>
	<div class="container-fluid">
	 <div class="form-group" >
	 <iframe id="file_result" name="file_result" style="display: none" ></iframe>
	 <form:form commandName="asVO"  id="asProcForm" name="asProcForm" method="post" target="file_result" enctype="multipart/form-data">
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
		        
	    		<c:if test="${asVO.asState=='04'}">
		            <button type="button" class="btn btn-default" onClick="fcAsNo_print('${asVO.asNo}')" >접수번호 인쇄</button>
		       		<button type="button" class="btn btn-success" onClick="fcAs_MainTransfer()">배송</button>
		       	</c:if>
		     
		       	<c:if test="${asVO.asSubState=='01' && (strAuth!='03' || strAuthId=='AD001')}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','02','점포->본사수령')">수령</button>
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='02' && (strAuth!='03' || strAuthId=='AD001')}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','03','본사->센터배송')">센터배송</button>
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='03' && (strAuth!='03' || strAuthId=='AD001')}">
       	          <div class="form-inline">
       	                  센터 접수번호 : 
		          <input type="text" class="form-control" id="centerAsNo" style="width:120px"  name="centerAsNo" maxlength="30" value="" placeholder="센터접수번호"  />
		          <button type="button" class="btn btn-success" onClick="fcAs_CenterStart('${asVO.asNo}')">센터접수</button>
		    	  </div> 
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='04' && (strAuth!='03' || strAuthId=='AD001')}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','05','센터->본사수령')">본사수령</button>
		       	</c:if>

		       	<c:if test="${asVO.asSubState=='06' && (strAuth!='03' || strAuthId=='AD001')}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_ReceiveTrans('07')">점포배송</button>
		       		<button type="button" class="btn btn-success" onClick="fcAs_ReceiveTrans('09')">고객배송</button>
		       	</c:if>
		       	
		        <c:if test="${asVO.asSubState=='07'}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','08','본사->점포수령')">점포수령</button>
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='09' && (strAuth!='03' || strAuthId=='AD001')}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','11','고객통화 수령확인')">고객수령확인</button>
		       	</c:if>
				
		        </div>
		      </tr>
		      <br><br>
		       <input type="hidden" name="asDeliveryMethod"               id="asDeliveryMethod"            value="01" />
		       <c:choose>
	    		<c:when test="${asVO.asState=='04'}">
	    		<table class="table table-bordered" >
					<tr>
			          <th class='text-center' rowspan="2"  style="background-color:#E6F3FF" >운송방법선택<br>(점포->본사)</th>
			          <th class='text-left' >
			          <input type="radio" name="asDeliveryRadio" id="asDeliveryRadio" value="01" checked onChange="fcAsDelivery_method()")/>&nbsp;택배
			          </th>
			          <th class='text-center' style="background-color:#E6F3FF">운송회사
			          <th class='text-center' colspan="2" >
					  <select class="form-control" title="운송업체" id="asTransportCode" name="asTransportCode" value="">
	                	<option value="">없음</option>
	                    <c:forEach var="codeVO" items="${code_comboList}" >
	                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
	                    </c:forEach>
	           		 </select>
			   		  <input type="hidden" id="asTransport" name="asTransport" >
			          </th>
			      	  <th class='text-center'  style="background-color:#E6F3FF">운송장번호</th>
			          <th class='text-center'><input type="text" class="form-control" id="asTransportNo" name="asTransportNo" maxlength="30"   value="" placeholder="운송장번호"  /></th>	
			      	</tr>
			      	<tr>
			      	  <th class='text-left' >
			          <input type="radio" name="asDeliveryRadio" id="asDeliveryRadio" value="02"  onChange="fcAsDelivery_method()")/>&nbsp;퀵
			          </th>
			      	  <th class='text-center' style="background-color:#E6F3FF">담당자</th>
			          <th class='text-center' colspan="2" >
			          <input type="text" class="form-control" id="asQuickCharge" name="asQuickCharge" disabled  maxlength="10"  value="" placeholder="담당자"  />
			          </th>
			      	  <th class='text-center' style="background-color:#E6F3FF">연락처</th>
			          <th class='text-center'>
			          <input type="text" class="form-control" id="asQuicktel" name="asQuickTel" disabled value=""  maxlength="14" placeholder="연락처"  />
			          </th>
			      	</tr>
			     </table>
				</c:when>
				<c:otherwise>
				 <table class="table table-bordered" >
				 <c:if test="${asVO.asSubState=='05' && (strAuth!='03' || strAuthId=='AD001')}">
				   <tr>
			       	   <div class="form-inline">
		       	          <th class='text-center'  style="background-color:#E6F3FF">&nbsp;센터 처리내용  </th>
				          <th class='text-center' colspan="3" ><input type="text" class="form-control" id="asHistory" style="width:400px"  name="asHistory" maxlength="30" value="" placeholder="센터 처리내용"  /></th>
				          <th class='text-center' ><font style="color:red">이미지 첨부 : </font><input type="file" id="files" name="files" /></th>
				          <th class='text-center' ><button type="button" class="btn btn-success" onClick="fcAs_CenterEnd()">센터처리</button></th>
				       </div>
			       </tr> 
		       	 </c:if>
					<tr>
			          <th class='text-center' style="background-color:#E6F3FF" >운송방법(점포->본사)</th>
			          <c:choose>
	    				<c:when test="${asVO.asDeliveryMethod=='01'}">
			         	  <th class='text-center' >&nbsp;택배 </th>
			              <th class='text-center' style="background-color:#E6F3FF">운송회사&nbsp;<button id="modifyastrans" type="button" class="btn btn-xs btn-success" onClick="fcModify_astrans('${asVO.asNo}','${asVO.asDeliveryMethod}')" >수정</button></th>
			              <th class='text-center' id="asTransCompanyId" >${asVO.asTransport}</th>
	                      <th class='text-center'  style="background-color:#E6F3FF">운송장번호</th>
			               <c:choose>
	    						 <c:when test="${asVO.asTransurl!='N'}">
	    						    <input type="hidden" name="asTransurl_Modify" id="asTransurl_Modify" value="${asVO.asTransurl}" >
	    						    <input type="hidden" name="asTransportNo_Modify" id="asTransportNo_Modify" value="${asVO.asTransportNo}" >
	    						  	<th class='text-center'><a href="javascript:fcAs_asTranspath();"><span id="asTransNoId">${asVO.asTransportNo}</span></a></th>
			             	 	 </c:when>
								 <c:otherwise>
								    <input type="hidden" name="asTransurl_Modify" id="asTransurl_Modify" value="${asVO.asTransurl}" >
	    						    <input type="hidden" name="asTransportNo_Modify" id="asTransportNo_Modify" value="${asVO.asTransportNo}" >
								  	<th class='text-center' id="asTransNoId" >${asVO.asTransportNo}</th>
								 </c:otherwise>
			 			  </c:choose>
			            </c:when>
				        <c:otherwise>	
				       	  <th class='text-center' >&nbsp;퀵 </th>
			              <th class='text-center' style="background-color:#E6F3FF">담당자&nbsp;<button id="asmodifytrans" type="button" class="btn btn-xs btn-success" onClick="fcModify_astrans('${asVO.asNo}','${asVO.asDeliveryMethod}')" >수정</button></th>
			              <th class='text-center' colspan="2" id="asQuickId" >${asVO.asQuickCharge}</th>
	                      <th class='text-center'  style="background-color:#E6F3FF">연락처</th>
			              <th class='text-center' id="asQuicktelId">${asVO.asQuickTel}</th>
			            </c:otherwise>
			 		   </c:choose>
			      	</tr>
			     </table>
				</c:otherwise>
			  </c:choose>
			  
		        <div id="asHistoryList"></div>
				<script>
				 fcAs_HistoryList();
				</script>
			</c:otherwise>
	    </c:choose>
	  </form:form>
	 </div>
  </body>
</html>


