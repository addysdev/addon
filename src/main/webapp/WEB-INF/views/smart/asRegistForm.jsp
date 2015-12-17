<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<!DOCTYPE html>
<html>
 <head>
	<script>
	$(function() {
	    // 기간 설정 타입 1 
	    // start Date 설정시 end Date의 min Date 지정
	    $( "#purchaseDate" ).datepicker({
	        dateFormat: "yy-mm-dd",
	        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
	        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
	        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            $( "#asTargetDate" ).datepicker( "option", "minDate", selectedDate );
	        }
	    }); 
	     // end Date 설정시 start Date max Date 지정
	    $( "#asTargetDate" ).datepicker({
	        dateFormat: "yy-mm-dd",
	        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
	        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
	        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            $( "#purchaseDate" ).datepicker( "option", "maxDate", selectedDate );
	        }
	    });

	    // 기간 설정 타입 2 
	    // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
	    $("#purchaseDate").datepicker({
	        dateFormat: "yy-mm-dd",
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            if ($( "#purchaseDate" ).val() < selectedDate)
	            {
	                $( "#asTargetDate" ).val(selectedDate);
	            }
	        }
	    }); 
	    // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
	    $( "#asTargetDate" ).datepicker({
	        dateFormat: "yy-mm-dd",
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            if ($("#purchaseDate" ).val() > selectedDate)
	            {
	                $("#purchaseDate" ).val(selectedDate);
	            }
	        }
	    });
	   
	});
	function showCalendar(div){

		   if(div == "1"){
		   	   $('#purchaseDate').datepicker("show");
		   } else if(div == "2"){
			   $('#asTargetDate').datepicker("show");
		   }  
		}
	
	function fcSameSet(){
		
		frm=document.asRegistForm;
		
		if(frm.sameSet.checked==true){
			
			frm.receiveName.value=frm.customerName.value;
			frm.receiveTelNo.value=frm.customerKey.value;
			
		}else{
			
			frm.receiveName.value='';
			frm.receiveTelNo.value='';
			
		}
		
	}

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
		function fcAs_replace(){
			
			
			
		}
		function fcAs_process(asState){
			
			var url;
		    var frm = document.asRegistForm;
		    var fileName ='';
		    var pos = '';
		    var ln = '';
		    var gap = '';
		    var gap1 = '';
		    var cfileName ='';
		    var cpos = '';
		    var cln = '';
		    var cgap = '';
		    var cgap1 = '';
		    
		    var fileAttach='N';
		    var cfileAttach='N';

		    if(frm.customerName.value==''){
		    	alert('의뢰인 정보는 필수입력 사항입니다.');
		    	frm.customerName.focus(1);
		    	return;
		    }
		    
		    if(frm.asDetail.value==''){
		    	alert('A/S제품 증상을 입력해 주시기 바랍니다.');
		    	frm.asDetail.focus(1);
		    	return;
		    }
		    
		    if($("#files").val() != ''){

		        fileName = document.all.files.value;
		        
		        pos = fileName.lastIndexOf("\\");
		        ln = fileName.lastIndexOf("\.");
		        gap = fileName.substring(pos + 1, ln);
		        gap1 = fileName.substring(ln+1);
	
		        if(gap1=="jpg" || gap1=="JPG" || gap1=="gif" || gap1=="GIF" || gap1=="png" || gap1=="PNG"){//
		        	fileAttach='Y';
		        }else {
		        	alert("이미지 파일만 등록 부탁드립니다.");
		            return;
		        }
		        
		    }else{
		    	fileAttach='N';
		    	alert('A/S제품 증상 이미지는 필수 첨부사항입니다.');
		    	return;
		    	
		    }

		    if($("#cfiles").val() != ''){

		    	cfileName = document.all.cfiles.value;
		        
		        cpos = cfileName.lastIndexOf("\\");
		        cln = cfileName.lastIndexOf("\.");
		        cgap = cfileName.substring(cpos + 1, cln);
		        cgap1 = cfileName.substring(cln+1);
	
		        if(cgap1=="jpg" || cgap1=="JPG" || cgap1=="gif" || cgap1=="GIF" || cgap1=="png" || cgap1=="PNG"){//
		        	cfileAttach='Y';
		        }else {
		        	alert("이미지 파일만 등록 부탁드립니다.");
		            return;
		        }
		        
		    }else{
		    	
		    	cfileAttach='N';
		    }

		    url="<%= request.getContextPath() %>/smart/asregist?fileAttach="+fileAttach+"&cfileAttach="+cfileAttach;
		    
		    frm.asState.value=asState;
		    
		    frm.action = url;
		    frm.target="file_result";

		    if(asState=='03'){
		    	fcReplace_reasonpop();
		    }else{
		    	
	    	 if (confirm('A/S 접수를 진행하시겠습니까?')){ 
	    		
	    		 commonDim(true);
	    		    
			   	 frm.submit();        
			 }
		    }
		    
		   
			
		}
		
		function fcAsRegist_close(retVal,asState,asNo){
			
			commonDim(false);
			
			if(retVal=='1'){
				if(asState=='03'){
					alert('1:1교환 처리가 완료되었습니다.'); 
					$("#asRegForm").dialog('close');
					fcAs_listSearch();
				}else{
					alert('A/S 접수가 완료되었습니다.'); 
					$("#asRegForm").dialog('close');
					fcAs_listSearch();
					fcAs_procForm(asNo);
				}
			}else{
				
				if(asState=='03'){
					alert('1:1교환 처리가 저장을 실패했습니다.'); 
					$("#asRegForm").dialog('close');
					fcAs_listSearch();
				}else{
					alert('A/S 접수를 실패했습니다.'); 
					$("#asRegForm").dialog('close');
					fcAs_listSearch();
				}
			}
			

		}
		
		function fcReplace_reasonpop(){

	    	var url='<%= request.getContextPath() %>/smart/replceresult';

	    	$('#ReplaceResult').dialog({
	            resizable : false, //사이즈 변경 불가능
	            draggable : true, //드래그 불가능
	            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

	            width : 300,
	            height : 200,
	            modal : true, //주위를 어둡게

	            open:function(){
	                //팝업 가져올 url
	              //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
	                $(this).load(url);
	               
	                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                    $("#ReplaceResult").dialog('close');

	                    });
	            }
	            ,close:function(){
	            	$('#ReplaceResult').empty();
	            }
	        });
	    };
	    
	    function radioSelect(){
	    	
	    	var frm=document.asRegistForm;

	    	if(frm.receiveRadio[0].checked==true){
	    		frm.receiveAddress.disabled=true;
	    		frm.receiveAddressDetail.disabled=true;
	    		frm.receiveAddress.value='';
	    		frm.receiveAddressDetail.value='';
	    		frm.receiveType.value='01';
	    	}else if(frm.receiveRadio[1].checked==true){
	    		frm.receiveAddress.disabled=false;
	    		frm.receiveAddressDetail.disabled=false;
	    		frm.receiveType.value='02';
	    	}

	    }
	</script>
  </head>
  <body>
	<div class="container-fluid">
	 <div class="form-group" >
	 <iframe id="file_result" name="file_result" style="display: none" ></iframe>
     <form:form commandName="asVO"  id="asRegistForm" name="asRegistForm" method="post" target="file_result" enctype="multipart/form-data" >
       <input type="hidden" name="customerKey"             id="customerKey"            value="${customerKey}" />
	   <input type="hidden" name="groupId"             id="groupId"            value="${strGroupId}" />
	   <input type="hidden" name="asStartUserId"             id="asStartUserId"            value="${strUserId}" />
	   <input type="hidden" name="productCode"             id="productCode"            value="${productCode}" />
	   <input type="hidden" name="asResult"             id="asResult"            value="" />
	   <input type="hidden" name="asState"             id="asState"            value="" />
	   <input type="hidden" name="receiveType"             id="receiveType"            value="" />
	      <tr>
	      <div style="position:absolute; left:30px" > 
	                      ※ A/S 접수 기본정보 입력
          </div >
          <div style="position:absolute; right:30px" > 
          <button type="button" class="btn btn-primary" onClick="fcAs_process('03')">1:1교환</button>
          <button type="button" class="btn btn-success" onClick="fcAs_process('04')">접수</button>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	  	<tr>
          <th class='text-center' style="background-color:#E6F3FF">접수일</th>
          <th class='text-center'>${strToday}</th>
          <th class='text-center' style="background-color:#E6F3FF" >접수지점</th>
          <th class='text-center'>${strGroupName}</th>  
          <th class='text-center' style="background-color:#E6F3FF">담당자</th>
          <th class='text-center'>${strUserName}</th>  
      	</tr>
      	</table>
      	<table class="table table-bordered" >
	 	<tr>
          <th rowspan='3' class='text-center' style="background-color:#E6F3FF">고객정보</th>
          <th class='text-center' style="background-color:#E6F3FF" >의뢰인<br><font style="color:red">(필수)</font></th>
          <th class='text-center' colspan="2" ><input type="text" class="form-control" id="customerName" style='width:135px' maxlength="20"  name="customerName"  value="" placeholder="의뢰인" /></th>
          <th class='text-center'  style="background-color:#E6F3FF" >의뢰인 연락처</th>
          <th class='text-left' colspan="2" >${customerKey}</th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >수령인<br><input type="checkbox"  id="sameSet"  name="sameSet" onchange="fcSameSet();" title="상동" />(상동)</th>
          <th class='text-center' colspan="2" ><input type="text" class="form-control" id="receiveName" style='width:135px' maxlength="20"  name="receiveName"  value="" placeholder="수령인" /></th>
          <th class='text-center'  style="background-color:#E6F3FF" >수령인 연락처</th>
          <th class='text-center' ><input type="text" class="form-control" id="receiveTelNo" style='width:135px'  maxlength="20"  name="receiveTelNo"  value="" placeholder="수령인 연락처" /></th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >수령방법</th>
          <th colspan="4" class='text-center'>
          <div class="form-inline text-left">
          <input type="radio" name="receiveRadio" id="receiveRadio" value="01" checked onChange="radioSelect()">매장 <input type="radio" name="receiveRadio" id="receiveRadio" value="02" onChange="radioSelect()">택배(퀵)
          <input type="text" class="form-control" id="receiveAddress"  maxlength="500" style='width:200px'  name="receiveAddress"  value="" placeholder="배송주소" disabled />
          <input type="text" class="form-control" id="receiveAddressDetail"  maxlength="500"  style='width:405px' name="receiveAddressDetail"  value="" placeholder="배송상세주소" disabled />
          </div>
          </th>
      	</tr>
      	<tr>
          <th rowspan='5' class='text-center' style="background-color:#E6F3FF">상품정보</th>
          <th class='text-center'  style="background-color:#E6F3FF" >브랜드명</th>
          <th class='text-left'>${brandName}</th>
      	  <th class='text-center'  style="background-color:#E6F3FF" >모델명</th>
          <th class='text-left' colspan="2" >${productName}</th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >A/S정책</th>
          <th colspan="4" class='text-left'>
          ${asPolicy}
          </th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >증상<br><font style="color:red">(필수)</font></th>
          <th colspan="3" class='text-center'>
          <textarea style='width:455px;height:80px;ime-mode:active;' row="4" class="form-control" id="asDetail" maxlength="1000" name="asDetail"  value="" placeholder="증상" ></textarea>
          </th>
          <th class='text-center'><font style="color:red">이미지 첨부 : </font><input type="file" id="files" name="files" /></th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >의뢰인<br>요청사항</th>
          <th colspan="4" class='text-center'>
          <textarea style='width:705px;height:50px;ime-mode:active;' row="4" class="form-control" id="customerRequest" maxlength="1000" name="customerRequest"  value=""  placeholder="의뢰인 요청사항" ></textarea>
          </th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >구입일</th>
          <th class='text-left' colspan="2">
          <div class="form-inline" >
          	  <!-- 구매일자-->
		      <input  class="form-control" style='width:135px' name="purchaseDate" id="purchaseDate" value="" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
		      <!-- 달력이미지 시작 -->
		      <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
		      <!-- 달력이미지 끝 -->
		  </div>
          </th>
          <th class='text-center'  style="background-color:#E6F3FF" >영수증<br>(첨부)</th>
          <th class='text-center'><input type="file" id="cfiles" name="cfiles" /></th>
      	</tr>
      	</table>
      	<table class="table table-bordered" >
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">완료예정일</th>
          <th class='text-left'>
          <div class="form-inline">
          	  <!-- 완료일자-->
		      <input  class="form-control" style='width:135px' name="asTargetDate" id="asTargetDate" value="${strDeliveryDay}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
		      <!-- 달력이미지 시작 -->
		      <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
		      <!-- 달력이미지 끝 -->
		  </div>
          </th>
          <th class='text-center' style="background-color:#E6F3FF">담당자<br>의견</th>
          <th class='text-center'><textarea style='width:505px;height:50px;ime-mode:active;' row="10" class="form-control" id="memo" maxlength="1000" name="memo"  value="" placeholder="담당자 의견" ></textarea></th>
      	</tr>
	  </table>
	  </form:form>
	  <tr>
      	<div style="position:absolute; left:30px" > 
         	※A/S처리 및 이력
        </div >
      </tr>
	 </div>
  </body>
</html>

 

