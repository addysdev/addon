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
		function asImageResize(){
			
			var frm=document.asProcForm;
			var img=frm.asImageSrc.value;
			
	    	   foto1= new Image();
	    	   foto1.src=(img);
	    	   Controlla(img);
	    	 }
		function receiptImageResize(){
			
			var frm=document.asProcForm;
			var img=frm.receiptImageSrc.value;
			
	    	   foto1= new Image();
	    	   foto1.src=(img);
	    	   Controlla(img);
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
		  /*  
		    if(pintYN==false){
		    	
		    	alert('발신 처리시 접수번호를 프린트 하신후\n발신대상 BOX에 첨부하여 보내시기 바랍니다.\n인쇄버튼을 클릭하여 접수번호를 인쇄 하신 후\n다시 시도하시기 바랍니다.');
		    	return;
		    	
		    }
		    */
		    dfrm.asTransport.value=$("#asTransportCode option:selected").text();
		    
		    if (confirm('A/S 처리를 매장발신상태로 저장 하시겠습니까?\n저장시 A/S대행 접수안내 SMS가 고객님께 발송됩니다.')){
				
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
	  
	  function fcAs_StateProcess(asNo,asState,asSubState,asHistory,customerKey){
		  
		  var msg='A/S 처리상태를 변경 하시겠습니까?';
		  
		  if(asSubState=='08'){
			  msg='A/S 처리상태를 변경 하시겠습니까?\저장시 A/S 제품수령 안내 SMS가 고객님께 발송됩니다.'
		  }else{
			  
			  msg='A/S 처리상태를 변경 하시겠습니까?';
		  }
		  
		  
		  if (confirm(msg)){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/asstateprocess?asNo="+asNo+"&customerKey="+customerKey+"&asState="+asState+"&asSubState="+asSubState+"&asHistory="+encodeURIComponent(asHistory),			  
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

	  function fcAs_HistoryMemoPop(asNo,customerKey){


	    	var url='<%= request.getContextPath() %>/smart/ashistorymemopop?asNo='+asNo+'&customerKey='+customerKey;
	    	
	    	$('#memoDialog').dialog({
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
	                    $("#memoDialog").dialog('close');
	                    });
	            }
	            ,close:function(){
	            	$("#memoDialog").dialog('close');
	            }
	        });
	    };
	  function fcAs_HistoryMemo(asNo,customerKey,asHistory){
		  
		  if (confirm('처리이력에 메모내용을 추가 하시겠습니까?')){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/ashistorymemo?asNo="+asNo+"&customerKey="+customerKey+"&asHistory="+encodeURIComponent(asHistory),			  
			           success: function(result) {

							if(result=='1'){
								 alert('메모추가를 성공했습니다.');
							} else{
								 alert('메모추가를 실패했습니다.');
							}
							$("#memoDialog").dialog('close');
							fcAs_HistoryList();
							
			           },
			           error:function(){
			        	   
			        	   alert('메모추가를 실패했습니다.');
			        	   $("#memoDialog").dialog('close');
			        	   fcAs_HistoryList();
			           }
			    });
				
			}
	  }
function fcAs_CenterStart(asNo,customerKey){
		  
		  frm=document.asProcForm;
	
		  var centerAsNo=frm.centerAsNo.value;
		  var asHistory='센터접수 확인';
		  
		  if (confirm('센터 접수상태를 저장 하시겠습니까?\n저장시 센터 접수안내 SMS가 고객님께 발송됩니다.')){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/ascenterstart?asNo="+asNo+"&customerKey="+customerKey+"&centerAsNo="+centerAsNo+"&asHistory="+encodeURIComponent(asHistory),			  
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
	  function fcAs_ReceiveTrans(asNo){
		  
		  	frm=document.asProcForm;
		  
	    	if(frm.reDeliveryRadio[0].checked==true){
				
				if(frm.reTransportCode.value==''){
					alert('택배로 운송처리시 운송회사를 입력하셔야 합니다.');
					return;
				}
				
				if(frm.reTransportNo.value==''){
					alert('택배로 운송처리시 운송장 번호를 입력하셔야 합니다.');
					return;
				}
				
			}else{
				
				if(frm.reQuickCharge.value==''){
					alert('퀵 운송처리시 퀵 운송 담당자를 입력하셔야 합니다.');
					return;
				}
				
				if(frm.reQuickTel.value==''){
					alert('퀵 운송처리시 퀵 운송 연락처를 입력하셔야 합니다.');
					return;
				}
			}
		    
		  frm.reTransport.value=$("#reTransportCode option:selected").text();
		  
	
		  var asHistory='본사->매장배송';
		  
		  if (confirm('매장 배송상태로 저장 하시겠습니까?')){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/asreceivetrans?asHistory="+encodeURIComponent(asHistory),		
			           data:$("#asProcForm").serialize(),
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
	  
	function fcAs_CustomerReceive(asNo){
		  
		  	frm=document.asProcForm;
			  
	    	if(frm.reDeliveryRadio[0].checked==true){
				
				if(frm.reTransportCode.value==''){
					alert('택배로 운송처리시 운송회사를 입력하셔야 합니다.');
					return;
				}
				
				if(frm.reTransportNo.value==''){
					alert('택배로 운송처리시 운송장 번호를 입력하셔야 합니다.');
					return;
				}
				
			}else{
				
				if(frm.reQuickCharge.value==''){
					alert('퀵 운송처리시 퀵 운송 담당자를 입력하셔야 합니다.');
					return;
				}
				
				if(frm.reQuickTel.value==''){
					alert('퀵 운송처리시 퀵 운송 연락처를 입력하셔야 합니다.');
					return;
				}
			}
		    
		  frm.reTransport.value=$("#reTransportCode option:selected").text();
		  
	
		  var asHistory='본사->고객배송';
		  
		  if (confirm('고객 배송상태로 저장 하시겠습니까?\n저장시 고객 수령안내 SMS가 고객님께 발송됩니다.')){
				
				$.ajax({
			        type: "POST",
			        async:false,
			           url:  "<%= request.getContextPath() %>/smart/ascustomerreceive?asHistory="+encodeURIComponent(asHistory),		
			           data:$("#asProcForm").serialize(),	   
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
			  
		    files = document.all("bfiles");
		    
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
	    
	    /////AS완료배송
	    
	    function fcReDelivery_method(){

	  	if(document.asProcForm.reDeliveryRadio[0].checked==true){
	  		document.asProcForm.reTransportCode.disabled=false;
	  		document.asProcForm.reTransportNo.disabled=false;
	  		document.asProcForm.reQuickCharge.disabled=true;
	  		document.asProcForm.reQuickTel.disabled=true;
	  		document.asProcForm.reQuickCharge.value='';
	  		document.asProcForm.reQuickTel.value='';
	  		document.asProcForm.reDeliveryMethod.value='01';
	  	}else{
	  		document.asProcForm.reTransportCode.disabled=true;
	  		document.asProcForm.reTransportNo.disabled=true;
	  		document.asProcForm.reTransportCode.value='';
	  		document.asProcForm.reTransportNo.value='';
	  		document.asProcForm.reQuickCharge.disabled=false;
	  		document.asProcForm.reQuickTel.disabled=false;
	  		document.asProcForm.reDeliveryMethod.value='02';
	  	}
	  }
	  
	   function fcAs_reTranspath(){
			
			var url=document.asProcForm.reTransurl_Modify.value;
			var transno=document.asProcForm.reTransportNo_Modify.value;
			
			var theURL=url+transno;
			
			var h=700;
			var s=800;

		    tmt_winLaunch(theURL, 'transObj', 'transObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
		
		}
	   
	   function fcModify_retrans(asNo,reDeliveryMethod){

	    	var url='<%= request.getContextPath() %>/smart/retransmodifyform';

	    	$('#asTransManage').dialog({
	            resizable : false, //사이즈 변경 불가능
	            draggable : true, //드래그 불가능
	            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

	            width : 800,
	            height : 180,
	            modal : true, //주위를 어둡게

	            open:function(){
	                //팝업 가져올 url
	                $(this).load(url+'?asNo='+asNo+'&reDeliveryMethod='+reDeliveryMethod);
	               
	                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                    $("#asTransManage").dialog('close');

	                    });
	            }
	            ,close:function(){
	            	$('#asTransManage').empty();
	            }
	        });
	    };
	    
	    function radioSelect(){
	    	
	    	var frm=document.asProcForm;

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
	    
	function fcAs_Modify(){
			
			var url;
		    var frm = document.asProcForm;
		    var files;
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
		    
		    url="<%= request.getContextPath() %>/smart/asmodify?fileAttach="+fileAttach+"&cfileAttach="+cfileAttach;

		    frm.action = url;
		    frm.target="file_result";

	    	 if (confirm('A/S 접수내용을 수정하시겠습니까?')){ 
	    		
	    		 commonDim(true);
	    		    
			   	 frm.submit();        
			 }

			
		}
		
		function fcAsModify_close(retVal,asImage,receiptImage){
			
			commonDim(false);
			
			if(asImage!='N'){
			
				document.all('asImageId').src=asImage;
				document.asProcForm.asImageSrc.value=asImage;
			}
			
			if(receiptImage!='N'){
				document.all('receiptImageId').src=receiptImage;
				document.asProcForm.receiptImageSrc.value=receiptImage;
			}
			
			alert('A/S 접수내용이 수정되었습니다.'); 

		}
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
	   <input type="hidden" name="customerKey"             id="customerKey"            value="${asVO.customerKey}" />
	   <input type="hidden" name="receiveType"             id="receiveType"            value="${asVO.receiveType}" />
	   <input type="hidden" name="asImageSrc"             id="asImageSrc"            value="${asVO.asImage}" />
	   <input type="hidden" name="receiptImageSrc"             id="receiptImageSrc"            value="${asVO.receiptImage}" />
	      <tr>
	      <div style="position:absolute; left:30px" > 
	                      ※ A/S 접수 기본정보
          </div >
          <div style="position:absolute; right:30px" > 		        
    		<c:if test="${asVO.asState!='01' && asVO.asState!='02' && asVO.asState!='03'}">
	            <button type="button" class="btn btn-default" onClick="fcAsNo_print('${asVO.asNo}')" >접수번호 인쇄</button>
	       	</c:if>
			<button type="button" class="btn btn-success" onClick="fcAs_Modify()">수정</button>
		  </div>
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
          <th class='text-left' colspan="2" ><input type="text" class="form-control" id="customerName" style='width:135px' maxlength="20"  name="customerName"  value="${asVO.customerName}" placeholder="의뢰인" /></th>
          <th class='text-center'  style="background-color:#E6F3FF;width:140px" >의뢰인 연락처</th>
          <th class='text-left' colspan="2" >${asVO.customerKey}</th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >수령인</th>
          <th class=''text-left'' colspan="2" ><input type="text" class="form-control" id="receiveName" style='width:135px' maxlength="20"  name="receiveName"  value="${asVO.receiveName}" placeholder="수령인" /></th>
          <th class='text-center'  style="background-color:#E6F3FF" >수령인 연락처</th>
          <th class='text-left' ><input type="text" class="form-control" id="receiveTelNo" style='width:135px'  maxlength="20"  name="receiveTelNo"  value="${asVO.receiveTelNo}" placeholder="수령인 연락처" /></th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >수령방법</th>
          <th colspan="4" class='text-left'>
          
          <c:choose>
    		  	<c:when test="${asVO.receiveType=='02'}">
    		  	  <div class="form-inline text-left">
		          <input type="radio" name="receiveRadio" id="receiveRadio" value="01"  onChange="radioSelect()">매장 <input type="radio" name="receiveRadio" id="receiveRadio" value="02" checked onChange="radioSelect()">택배(퀵)
		          <input type="text" class="form-control" id="receiveAddress"  maxlength="500" style='width:200px'  name="receiveAddress"  value="${asVO.receiveAddress}" placeholder="배송주소"  />
		          <input type="text" class="form-control" id="receiveAddressDetail"  maxlength="500"  style='width:405px' name="receiveAddressDetail"  value="${asVO.receiveAddressDetail}" placeholder="배송상세주소"  />
		          </div>
            	</c:when>
				<c:otherwise>
				  <div class="form-inline text-left">
		          <input type="radio" name="receiveRadio" id="receiveRadio" value="01" checked onChange="radioSelect()">매장 <input type="radio" name="receiveRadio" id="receiveRadio" value="02" onChange="radioSelect()">택배(퀵)
		          <input type="text" class="form-control" id="receiveAddress"  maxlength="500" style='width:200px'  name="receiveAddress"  value="" placeholder="배송주소" disabled />
		          <input type="text" class="form-control" id="receiveAddressDetail"  maxlength="500"  style='width:405px' name="receiveAddressDetail"  value="" placeholder="배송상세주소" disabled />
		          </div>
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
          <textarea style='width:495px;height:100px;ime-mode:active;' row="5" class="form-control" id="asDetail" maxlength="1000" name="asDetail"  value=" ${asVO.asDetail}" placeholder="증상" > ${asVO.asDetail}</textarea>
          </th>
          <th class='text-center'><a href="javascript:asImageResize()"><img id="asImageId" src='${asVO.asImage}' width="80" height="80" /></a>
          <input type="file" id="files" name="files" />
          </th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >의뢰인<br>요청사항</th>
          <th colspan="4" class='text-left'>
           <textarea style='width:705px;height:50px;ime-mode:active;' row="4" class="form-control" id="customerRequest" maxlength="1000" name="customerRequest"  value="${asVO.customerRequest}"  placeholder="의뢰인 요청사항" >${asVO.customerRequest}</textarea>
          </th>
      	</tr>
      	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >구입일</th>
          <th class='text-left' colspan="2">
          <div class="form-inline" >
          	  <!-- 구매일자-->
		      <input  class="form-control" style='width:135px' name="purchaseDate" id="purchaseDate" value="${asVO.purchaseDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
		      <!-- 달력이미지 시작 -->
		      <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
		      <!-- 달력이미지 끝 -->
		  </div>
		  </th>
          <th class='text-center'  style="background-color:#E6F3FF" >영수증</th>
          <th class='text-center'><a href="javascript:receiptImageResize()"><img id="receiptImageId" src='${asVO.receiptImage}' width="30" height="30" /></a>
          <input type="file" id="cfiles" name="cfiles" /></th>
      	</tr>
      	</table>
      	<table class="table table-bordered" >
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF;width:130px">완료예정일</th>
          <th class='text-left' style="width:130px">
            <div class="form-inline">
          	  <!-- 완료일자-->
		      <input  class="form-control" style='width:115px' name="asTargetDate" id="asTargetDate" value="${asVO.asTargetDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
		      <!-- 달력이미지 시작 -->
		      <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
		      <!-- 달력이미지 끝 -->
		  </div>
          </th>
          <th class='text-center' style="background-color:#E6F3FF;width:100px">담당자<br>의견</th>
          <th class='text-left'><textarea style='width:505px;height:50px;ime-mode:active;' row="10" class="form-control" id="memo" maxlength="1000" name="memo"  value="${asVO.memo}" placeholder="담당자 의견" >${asVO.memo}</textarea></th>
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
		         	※A/S처리 및 이력 <button type="button" class="btn btn-info" onClick="fcAs_HistoryMemoPop('${asVO.asNo}','${asVO.customerKey}')">처리이력 메모</button>
		        </div >
		        <div style="position:absolute; right:30px" > 
		        
	    		<c:if test="${asVO.asState=='04'}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_MainTransfer()">매장발신</button>
		       	</c:if>
		     
		       	<c:if test="${asVO.asSubState=='01'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','05','02','매장->본사수신','${asVO.customerKey}')">본사수신</button>
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='02'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','05','03','본사->센터발송','${asVO.customerKey}')">센터발송</button>
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='03'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
       	          <div class="form-inline">
       	                  센터 접수번호 : 
		          <input type="text" class="form-control" id="centerAsNo" style="width:120px"  name="centerAsNo" maxlength="30" value="" placeholder="센터접수번호"  />
		          <button type="button" class="btn btn-success" onClick="fcAs_CenterStart('${asVO.asNo}','${asVO.customerKey}')">센터접수</button>
		    	  </div> 
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='04'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','06','05','센터->본사수신','${asVO.customerKey}')">센터회수</button>
		       	</c:if>

		       	<c:if test="${asVO.asSubState=='06'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
		       	 <c:choose>
   						 <c:when test="${asVO.receiveType=='02'}">
   						   <button type="button" class="btn btn-success" onClick="fcAs_CustomerReceive('${asVO.asNo}')">고객배송</button>
	             	 	 </c:when>
						 <c:otherwise>
						   <button type="button" class="btn btn-success" onClick="fcAs_ReceiveTrans('${asVO.asNo}')">본사발신</button>  
						 </c:otherwise>
	 			  </c:choose>
	 			</c:if>
	 			
		        <c:if test="${asVO.asSubState=='07'}">
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','07','08','본사->매장수신','${asVO.customerKey}')">매장수신</button>
		       	</c:if>
		       	
		       	<c:if test="${asVO.asSubState=='09'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
		       		<button type="button" class="btn btn-success" onClick="fcAs_StateProcess('${asVO.asNo}','09','11','고객통화 수령확인','${asVO.customerKey}')">고객확인</button>
		       	</c:if>
				
		        </div>
		      </tr>
		      <br><br>
		       <input type="hidden" name="reDeliveryMethod"               id="reDeliveryMethod"            value="01" />
		       <c:choose>
	    		<c:when test="${asVO.asSubState=='06'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
	    		<table class="table table-bordered" >
					<tr>
			          <th class='text-center' rowspan="2"  style="background-color:#E6F3FF" >운송방법선택<br>(본사->(고객/매장))</th>
			          <th class='text-left' >
			          <input type="radio" name="reDeliveryRadio" id="reDeliveryRadio" value="01" checked onChange="fcReDelivery_method()")/>&nbsp;택배
			          </th>
			          <th class='text-center' style="background-color:#E6F3FF">운송회사
			          <th class='text-center' colspan="2" >
					  <select class="form-control" title="운송업체" id="reTransportCode" name="reTransportCode" value="">
	                	<option value="">없음</option>
	                    <c:forEach var="codeVO" items="${code_comboList}" >
	                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
	                    </c:forEach>
	           		 </select>
			   		  <input type="hidden" id="reTransport" name="reTransport" >
			          </th>
			      	  <th class='text-center'  style="background-color:#E6F3FF">운송장번호</th>
			          <th class='text-center'><input type="text" class="form-control" id="reTransportNo" name="reTransportNo" maxlength="30"   value="" placeholder="운송장번호"  /></th>	
			      	</tr>
			      	<tr>
			      	  <th class='text-left' >
			          <input type="radio" name="reDeliveryRadio" id="reDeliveryRadio" value="02"  onChange="fcReDelivery_method()")/>&nbsp;퀵
			          </th>
			      	  <th class='text-center' style="background-color:#E6F3FF">담당자</th>
			          <th class='text-center' colspan="2" >
			          <input type="text" class="form-control" id="reQuickCharge" name="reQuickCharge" disabled  maxlength="10"  value="" placeholder="담당자"  />
			          </th>
			      	  <th class='text-center' style="background-color:#E6F3FF">연락처</th>
			          <th class='text-center'>
			          <input type="text" class="form-control" id="reQuicktel" name="reQuickTel" disabled value=""  maxlength="14" placeholder="연락처"  />
			          </th>
			      	</tr>
			     </table>
				</c:when>
				<c:otherwise>
				 <c:if test="${asVO.asSubState=='07' || asVO.asSubState=='08' || asVO.asSubState=='09' || asVO.asSubState=='10' || asVO.asSubState=='11' }">
				 <table class="table table-bordered" >
					<tr>
			          <th class='text-center' style="background-color:#E6F3FF" >운송방법(본사->(고객/매장))</th>
			          <c:choose>
	    				<c:when test="${asVO.reDeliveryMethod=='01'}">
			         	  <th class='text-center' >&nbsp;택배 </th>
			              <th class='text-center' style="background-color:#E6F3FF">운송회사&nbsp;<button id="modifyastrans" type="button" class="btn btn-xs btn-success" onClick="fcModify_retrans('${asVO.asNo}','${asVO.reDeliveryMethod}')" >수정</button></th>
			              <th class='text-center' id="reTransCompanyId" >${asVO.reTransport}</th>
	                      <th class='text-center'  style="background-color:#E6F3FF">운송장번호</th>
			               <c:choose>
	    						 <c:when test="${asVO.reTransurl!='N'}">
	    						    <input type="hidden" name="reTransurl_Modify" id="reTransurl_Modify" value="${asVO.reTransurl}" >
	    						    <input type="hidden" name="reTransportNo_Modify" id="reTransportNo_Modify" value="${asVO.reTransportNo}" >
	    						  	<th class='text-center'><a href="javascript:fcAs_reTranspath();"><span id="reTransNoId">${asVO.reTransportNo}</span></a></th>
			             	 	 </c:when>
								 <c:otherwise>
								    <input type="hidden" name="reTransurl_Modify" id="reTransurl_Modify" value="${asVO.reTransurl}" >
	    						    <input type="hidden" name="reTransportNo_Modify" id="reTransportNo_Modify" value="${asVO.reTransportNo}" >
								  	<th class='text-center' id="reTransNoId" >${asVO.reTransportNo}</th>
								 </c:otherwise>
			 			  </c:choose>
			            </c:when>
				        <c:otherwise>	
				       	  <th class='text-center' >&nbsp;퀵 </th>
			              <th class='text-center' style="background-color:#E6F3FF">담당자&nbsp;<button id="remodifytrans" type="button" class="btn btn-xs btn-success" onClick="fcModify_retrans('${asVO.asNo}','${asVO.reDeliveryMethod}')" >수정</button></th>
			              <th class='text-center' colspan="2" id="reQuickId" >${asVO.reQuickCharge}</th>
	                      <th class='text-center'  style="background-color:#E6F3FF">연락처</th>
			              <th class='text-center' id="reQuicktelId">${asVO.reQuickTel}</th>
			            </c:otherwise>
			 		   </c:choose>
			      	</tr>
			     </table>
			     </c:if>
				</c:otherwise>
			  </c:choose>
			  
		       <input type="hidden" name="asDeliveryMethod"               id="asDeliveryMethod"            value="01" />
		       <c:choose>
	    		<c:when test="${asVO.asState=='04'}">
	    		<table class="table table-bordered" >
					<tr>
			          <th class='text-center' rowspan="2"  style="background-color:#E6F3FF" >운송방법선택<br>(매장->본사)</th>
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
				 <c:if test="${asVO.asSubState=='05'}"><!-- 권한추가시  && (strAuth!='03' || strAuthId=='AD001')-->
				   <tr>
			       	   <div class="form-inline">
		       	          <th class='text-center'  style="background-color:#E6F3FF">&nbsp;센터 처리내용  </th>
				          <th class='text-center' colspan="3" ><input type="text" class="form-control" id="asHistory" style="width:400px"  name="asHistory" maxlength="30" value="" placeholder="센터 처리내용"  /></th>
				          <th class='text-center' ><font style="color:red">이미지 첨부 : </font><input type="file" id="bfiles" name="bfiles" /></th>
				          <th class='text-center' ><button type="button" class="btn btn-success" onClick="fcAs_CenterEnd()">센터처리</button></th>
				       </div>
			       </tr> 
		       	 </c:if>
					<tr>
			          <th class='text-center' style="background-color:#E6F3FF" >운송방법(매장->본사)</th>
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


