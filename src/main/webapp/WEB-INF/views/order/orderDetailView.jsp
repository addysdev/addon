<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
 

function tmt_winLaunch(theURL,winName,targetName,features) {
	
	var targetRandom=Math.random();
	eval(winName+"=window.open('"+theURL+"','"+targetRandom+"','"+features+"')");

}
/*
 * print 화면 POPUP
 */
function fcOrderDetail_print(){
	
	var h=800;
	var s=950;

    tmt_winLaunch('<%= request.getContextPath()%>/order/targetdetailprint' , 'orderPrintObj', 'orderPrintObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
	
}

//보류수정
$(function() {
    $( "#defermodifydialog" ).dialog({
      modal : true, //주위를 어둡게
      autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      }
    });

    $( "#defermodifybtn" ).click(function() {
      $( "#defermodifydialog" ).dialog( "open" );
    });
  });

   $(function() {
      $( "#defermodifypopclosebtn" ).click(function() {
        $( "#defermodifydialog" ).dialog( "close" );
      });
    });
//보류취소
   $(function() {
	    $( "#defercanceldialog" ).dialog({
	      modal : true, //주위를 어둡게
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      }
	    });

	    $( "#defercancelbtn" ).click(function() {
	      $( "#defercanceldialog" ).dialog( "open" );
	    });
	  });

	   $(function() {
	      $( "#defercancelpopclosebtn" ).click(function() {
	        $( "#defercanceldialog" ).dialog( "close" );
	      });
	    });
//보류등록
	    $(function() {
	    $( "#deferregdialog" ).dialog({
	      modal : true, //주위를 어둡게
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      }
	    });

	    $( "#deferbtn" ).click(function() {
	      $( "#deferregdialog" ).dialog( "open" );
	    });
	  });

	   $(function() {
	      $( "#deferregpopclosebtn" ).click(function() {
	        $( "#deferregdialog" ).dialog( "close" );
	      });
	    });
	 //메모등록
	    $(function() {
	    $( "#memoregdialog" ).dialog({
	      modal : true, //주위를 어둡게
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      }
	    });

	    $( "#memoregbtn" ).click(function() {
	      $( "#memoregdialog" ).dialog( "open" );
	    });
	  });

	   $(function() {
	      $( "#memoregpopclosebtn" ).click(function() {
	        $( "#memoregdialog" ).dialog( "close" );
	      });
	    });
	   
	   function fcDefer_process(defer_type){

		   var defer_reason;
		   
		   if('R'==defer_type){
			   
			   defer_reason=$("#defer_reg_reason_div").val();
			   
		   }else{
			   
			   defer_reason=$("#defer_modify_reason_div").val();
		   }
		   
	    	if(defer_reason==''){
	    		alert('보류사유를 입력하세요!');
	    		return;
	    	}else{
	    	
	    		 var arrCheckProductId = "";
	             $('input:checkbox[name="orderCheck"]').each(function() {
	                 if ($(this).is(":checked")) {
	                	 arrCheckProductId += $(this).val() + "^";
	                 }   
	             });
	             
	             if(arrCheckProductId==''){
	            	 arrCheckProductId="^";
	             }
	    
	            var frm = document.orderDetailListForm;
	    
	           	if(frm.seqs.length>1){
	           		for(i=0;i<frm.seqs.length;i++){
	   					frm.seqs[i].value=fillSpace(frm.productCode[i].value)+
	           			'|'+fillSpace(frm.barCode[i].value)+'|'+fillSpace(frm.orderResultPrice[i].value)+'|'+fillSpace(frm.orderResultCnt[i].value)+
	           			'|'+fillSpace(frm.orderVatRate[i].value)+'|'+fillSpace(frm.etc[i].value);
	     
	           		}
	           	}else{
	           		
   					frm.seqs.value=fillSpace(frm.productCode.value)+
           			'|'+fillSpace(frm.barCode.value)+'|'+fillSpace(frm.orderResultPrice.value)+'|'+fillSpace(frm.orderResultCnt.value)+
           			'|'+fillSpace(frm.orderVatRate.value)+'|'+fillSpace(frm.etc.value);

	           	}
	
	            if (confirm('검수내용을 보류처리 하시겠습니까?')){ 
	            	
	            document.orderDetailForm.deferReason.value=defer_reason;
	            document.orderDetailForm.deferType.value=defer_type;
	            var paramString = $("#orderDetailForm").serialize()+ "&arrCheckProductId="+arrCheckProductId+'&'+$("#orderDetailListForm").serialize();

			  		$.ajax({
				       type: "POST",
				       async:false,
				          url:  "<%= request.getContextPath() %>/order/orderdeferprocess",
				          data:paramString,
				          success: function(result) {
			
				        	resultMsg(result);
							
				        	if(defer_type=='R'){
				        		$('#deferregdialog').dialog('close');
				        	}else{
				        		$('#defermodifydialog').dialog('close');
				        	}
							$('#orderDetailView').dialog('close');
							fcOrder_listSearch();
								
				          },
				          error:function(){
				          
				          alert('보류 처리 호출오류!');
				          if(defer_type=='R'){
				        		$('#deferregdialog').dialog('close');
				        	}else{
				        		$('#defermodifydialog').dialog('close');
				        	}
						  $('#orderDetailView').dialog('close');
						  fcOrder_listSearch();
				          }
				    });
            	
	            }
	    	}	
		}
	
    function fcDefer_cancel(){
    	
    	if($("#defer_cancel_reason_div").val()==''){
    		alert('보류폐기 사유를 입력하세요!');
    		return;
    	}

    	 if (confirm('보류내용을 폐기 하시겠습니까?')){ 
        	 
    		 document.orderDetailForm.deferReason.value=$("#defer_cancel_reason_div").val();
    		 document.orderDetailForm.deferType.value='D';
        	 var paramString = $("#orderDetailForm").serialize();
        	 
	 		$.ajax({
	       type: "POST",
	       async:false,
	          url:  "<%= request.getContextPath() %>/order/orderdefercancel",
	          data:paramString,
	          success: function(result) {
	
	        	resultMsg(result);
				
	        	$('#defercanceldialog').dialog('close');
				$('#orderDetailView').dialog('close');
				fcOrder_listSearch();
					
	          },
	          error:function(){
	          
	          alert('보류 처리 호출오류!');
	          $('#defercanceldialog').dialog('close');
			  $('#orderDetailView').dialog('close');
			  fcOrder_listSearch();
	          }
	    	
	 		});
	 		
    	 } 

    }
    
 // 보류 상세 페이지 리스트 Layup
    function fcDefer_list(orderCode) {
    	
    	//$('#targetEtcView').attr('title',productName);
    	var url='<%= request.getContextPath() %>/order/deferreasonlist';

    	$('#deferReasonList').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 800,
            height : 400,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
              //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
                $(this).load(url+'?orderCode='+orderCode+'&category=02');
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#deferReasonList").dialog('close');

                    });
            }
            ,close:function(){
                $('#deferReasonList').empty();
            }
        });
    };
    
    function fcOrder_cancel(){
    	
    	 if (confirm('발주 내용을 취소 하시겠습니까?')){ 
        	 
           var paramString = $("#orderDetailForm").serialize();
        	 
	 		$.ajax({
	       type: "POST",
	       async:false,
	          url:  "<%= request.getContextPath() %>/order/ordercancel",
	          data:paramString,
	          success: function(result) {
	
	        	resultMsg(result);
				
				$('#orderDetailView').dialog('close');
				fcOrder_listSearch();
					
	          },
	          error:function(){
	          
	          alert('취소 처리 호출오류!');
			  $('#orderDetailView').dialog('close');
			  fcOrder_listSearch();
	          }
	    	
	 		});
	 		
    	 } 

    }
    function totalOrderAmt(){
    	
    	var frm=document.orderDetailListForm;
    	var amtCnt = frm.productCode.length;
    	
    	var supplyamt=0;
    	var vatamt=0;
    	var totalamt=0;
    	
    	if(amtCnt > 1){
    		
	    	for(i=0;i<amtCnt;i++){
	    		
	    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultPrice[i].value))));
	    		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt[i].value))));
	    		var vatRate=frm.orderVatRate[i].value;
	    		var sum_supplyAmt=productPrice*orderCnt;
	
	    		supplyamt=supplyamt+sum_supplyAmt;
	    		var sum_vatAmt=Math.round(sum_supplyAmt*vatRate);
	
	    		document.all('orderTotalPriceView')[i].innerText=addCommaStr(''+(productPrice*orderCnt));
	
	    		vatamt=vatamt+sum_vatAmt;
	    	}
	    	
    	}else{
    		
    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultPrice.value))));
    		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt.value))));
    		var vatRate=frm.orderVatRate.value;
    		var sum_supplyAmt=productPrice*orderCnt;

    		supplyamt=supplyamt+sum_supplyAmt;
    		var sum_vatAmt=Math.round(sum_supplyAmt*vatRate);

    		document.all('orderTotalPriceView').innerText=addCommaStr(''+(productPrice*orderCnt));

    		vatamt=vatamt+sum_vatAmt;
    	}

    	  totalamt=supplyamt+vatamt;
    	
    	  document.all('totalOrderAmt').innerText='[합계] : '+addCommaStr(''+totalamt)+' 원  [공급가] : '+addCommaStr(''+supplyamt)+' 원  [부가세] : '+addCommaStr(''+vatamt)+' 원';
    }
    
    function totalCheck(){
    	
    	var frm=document.orderDetailListForm;
    	var amtCnt = frm.productCode.length;
    	
    	var chkCnt=0;
    	
    	if(amtCnt > 1){
			for(i=0;i<amtCnt;i++){
	    		
	    		if(frm.orderCheck[i].checked==true){
	    			frm.orderResultPrice[i].disabled=true;
	    			frm.orderResultCnt[i].disabled=true;
	    			frm.orderVatRate[i].disabled=true;
	    			chkCnt++;
	    		}else{
	    			frm.orderResultPrice[i].disabled=false;
	    			frm.orderResultCnt[i].disabled=false;
	    			frm.orderVatRate[i].disabled=false;
	    		}
	    	}
    	}else{

    		if(frm.orderCheck.checked==true){
    			frm.orderResultPrice.disabled=true;
    			frm.orderResultCnt.disabled=true;
    			frm.orderVatRate.disabled=true;
    			chkCnt++;
	   		}else{
	   			frm.orderResultPrice.disabled=false;
    			frm.orderResultCnt.disabled=false;
    			frm.orderVatRate.disabled=false;
	   		}
	  	}
    	
    	if(amtCnt==chkCnt){//검수버튼 활성화
    		
    		document.all('checkbtn').disabled=false;
    		
    	}else{
    		document.all('checkbtn').disabled=true;
    	}

    }
   
	//체크박스 전체선택
    function fcOrder_checkAll(){
		
    	$("input:checkbox[id='orderCheck']").prop("checked", $("#orderCheckAll").is(":checked"));
    	totalCheck();
    }
	
 // 보류 상세 페이지 리스트 Layup
    function fcMemo_detail(orderCode,memo) {
    	
    	//$('#targetEtcView').attr('title',productName);
    	var url='<%= request.getContextPath() %>/order/memomanage';

    	$('#memoManage').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 800,
            height : 500,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
              //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
                $(this).load(url+'?orderCode='+orderCode+'&category=03'+'&memo='+encodeURIComponent(memo));
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#memoManage").dialog('close');

                    });
            }
            ,close:function(){
                $('#memoManage').empty();
            }
        });
    };
    // 품목 상세 페이지 리스트 Layup
    function  fcEtc_detail(orderCode,productCode,productName,etc) {
    
    	//$('#targetEtcView').attr('title',productName);
    	var url='<%= request.getContextPath() %>/order/etcmanage';

    	$('#etcManage').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 800,
            height : 500,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
              //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
                $(this).load(url+'?orderCode='+orderCode+'&category=04'+'&productCode='+productCode+'&productName='+encodeURIComponent(productName)+'&etc='+encodeURIComponent(etc));
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#etcManage").dialog('close');

                    });
            }
            ,close:function(){
                $('#etcManage').empty();
            }
        });
    };
 //검수완료   
 function fcOrder_complete(){

  		 var arrCheckProductId = "";
           $('input:checkbox[name="orderCheck"]').each(function() {
               if ($(this).is(":checked")) {
              	 arrCheckProductId += $(this).val() + "^";
               }   
           });
           
           if(arrCheckProductId==''){
          	 arrCheckProductId="^";
           }
  
          var frm = document.orderDetailListForm;
  
         	if(frm.seqs.length>1){
         		for(i=0;i<frm.seqs.length;i++){
 					frm.seqs[i].value=fillSpace(frm.productCode[i].value)+
         			'|'+fillSpace(frm.barCode[i].value)+'|'+fillSpace(frm.orderResultPrice[i].value)+'|'+fillSpace(frm.orderResultCnt[i].value)+
         			'|'+fillSpace(frm.orderVatRate[i].value)+'|'+fillSpace(frm.etc[i].value);
   
         		}
         	}else{
         		
					frm.seqs.value=fillSpace(frm.productCode.value)+
     			'|'+fillSpace(frm.barCode.value)+'|'+fillSpace(frm.orderResultPrice.value)+'|'+fillSpace(frm.orderResultCnt.value)+
     			'|'+fillSpace(frm.orderVatRate.value)+'|'+fillSpace(frm.etc.value);

         	}

          if (confirm('검수 내용을 완료 처리 하시겠습니까?')){ 
          	
          var paramString = $("#orderDetailForm").serialize()+ "&arrCheckProductId="+arrCheckProductId+'&'+$("#orderDetailListForm").serialize();

		  		$.ajax({
			       type: "POST",
			       async:false,
			          url:  "<%= request.getContextPath() %>/order/ordercomplete",
			          data:paramString,
			          success: function(result) {
		
			        	resultMsg(result);
						
						$('#orderDetailView').dialog('close');
						fcOrder_listSearch();
							
			          },
			          error:function(){
			          
			          alert('검수 처리 호출오류!');

					  $('#orderDetailView').dialog('close');
					  fcOrder_listSearch();
			          }
			    });
      	
          }
  	}	

</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="orderVO" id="orderDetailForm"  name="orderDetailForm" method="post" action="" >
	   <input type="hidden" name="emailKey"             id="emailKey"            value="Y" />
	   <input type="hidden" name="smsKey"               id="smsKey"            value="N" />
	   <input type="hidden" name="faxKey"               id="faxKey"            value="N" />
	   <input type="hidden" name="deferReason"               id="deferReason"            value="" />
	   <input type="hidden" name="deferType"               id="deferType"            value="" />
	   <input type="hidden" name="groupId"               id="groupId"            value="${orderVO.groupId}" />
	   <input type="hidden" name="companyCode"               id="companyCode"            value="${orderVO.companyCode}" />
	   <input type="hidden" name="orderCode"               id="orderCode"            value="${orderVO.orderCode}" />
	      <tr>
	      <div style="position:absolute; left:30px" >
	      <c:if test="${orderVO.orderState=='03'}"><button id="deferbtn" type="button" class="btn btn-primary" >보류</button></c:if>
	      <c:if test="${orderVO.orderState=='04'}"><button id="defermodifybtn"  type="button" class="btn btn-primary">보류수정</button></c:if>
	      <c:if test="${orderVO.orderState=='04'}"><button id="defercancelbtn"  type="button" class="btn btn-danger" >보류폐기</button></c:if>
	      <c:if test="${orderVO.orderState=='04'}"><button type="button" class="btn btn-info" onClick="fcDefer_list('${orderVO.orderCode}')">보류사유</button></c:if>
	      <c:if test="${orderVO.orderState=='03'}"><button type="button" id="checkbtn"  name="checkbtn" disabled class="btn btn-primary" onClick="fcOrder_complete()">검수완료</button></c:if>
	      <c:if test="${orderVO.orderState=='06'}"><button type="button" class="btn btn-default">엑셀변환</button></c:if>
	      <c:if test="${orderVO.orderState=='06'}"><button type="button" class="btn btn-primary">등록완료</button></c:if>
          </div>
          <div id="deferregdialog" class="form-group" title="보류사유를 입력하세요">
			<p><textarea style='height:82px' row="3" class="form-control" id="defer_reg_reason_div" name="defer_reg_reason_div"  value=""  placeholder="보류사유"/></p>
			<button id="deferregsavebtn" type="button" class="btn btn-primary" onClick="fcDefer_process('R')">save</button> <button id="deferregpopclosebtn" type="button" class="btn btn-danger">cancel</button>
          </div>
          <div id="defermodifydialog" class="form-group" title="보류수정사유를 입력하세요">
			<p><textarea  style='height:82px' row="3" class="form-control" id="defer_modify_reason_div" name="defer_modify_reason_div"  value=""  placeholder="보류수정사유"/></p>
			<button id="defermodifysavebtn" type="button" class="btn btn-primary" onClick="fcDefer_process('M')">save</button> <button id="defermodifypopclosebtn" type="button" class="btn btn-danger">cancel</button>
          </div>
          <div id="defercanceldialog" class="form-group" title="보류폐기사유를 입력하세요">
			<p><textarea style='height:82px' row="3" class="form-control" id="defer_cancel_reason_div" name="defer_cancel_reason_div"  value=""  placeholder="보류폐기사유"/></p>
			<button id="defercancelsavebtn" type="button" class="btn btn-primary" onClick="fcDefer_cancel()">save</button> <button id="defercancelpopclosebtn" type="button" class="btn btn-danger">cancel</button>
          </div>
          <div id="memoregdialog" class="form-group" title="메모내용을 입력하세요">
			<p><textarea style='height:82px' row="3" class="form-control" id="memo_reg_div" name="memo_reg_div"  value=""  placeholder="메모내용"/></p>
			<button id="memoregsavebtn" type="button" class="btn btn-primary" onClick="fcMemo_reg()">save</button> <button id="memoregpopclosebtn" type="button" class="btn btn-danger">cancel</button>
          </div>
          <div style="position:absolute; right:30px" > 
          <button type="button" class="btn btn-success" onClick="fcOrderDetail_print()">출력</button>
          <c:if test="${orderVO.orderState!='06' && orderVO.orderState!='07'}"><button type="button" class="btn btn-warning" onClick="fcOrder_cancel()">취소</button></c:if>
          <c:if test="${orderVO.orderState!='06' && orderVO.orderState!='07'}"><button type="button" class="btn btn-primary" onClick="alert('개발중입니다.')">재송부</button></c:if>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th rowspan='9' class='text-center' style="background-color:#E6F3FF">수신</th>
          <th class='text-center'  style="background-color:#E6F3FF" >수신</th>
          <th class='text-center'><input disabled type="text" class="form-control" id="deliveryCharge" name="deliveryCharge"  value="${orderVO.deliveryCharge}" placeholder="수신" /></th>
          <th rowspan='9' class='text-center'  style="background-color:#E6F3FF">발신</th>
          <th class='text-center' style="background-color:#E6F3FF">발신</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="orderCharge" name="orderCharge"  value="${orderVO.orderCharge}" placeholder="발신"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="deleveryEtc" name="deleveryEtc"  value="${orderVO.deliveryEtc}" placeholder="참조" /></th>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="orderUserName" name="orderUserName"  value="${orderVO.orderEtc}" placeholder="참조" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="mobilePhone" name="mobilePhone"  value="${orderVO.mobilePhone}"  placeholder="핸드폰"/></th>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="orderMobilePhone" name="orderMobilePhone"  value="${orderVO.orderMobilePhone}"  placeholder="핸드폰"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="email" name="email"  value="${orderVO.email}" placeholder="e-mail" /></th>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="orderEmail" name="orderEmail"  value="${orderVO.orderEmail}" placeholder="e-mail" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="telNumber" name="telNumber"  value="${orderVO.telNumber}" placeholder="tel" /></th>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="orderTelNumber" name="orderTelNumber"  value="${orderVO.orderTelNumber}" placeholder="tel" /></th>
      	</tr>
      	<th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="faxNumber" name="faxNumber"  value="${orderVO.faxNumber}" placeholder="fax" /></th>
          <th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="orderFaxNumber" name="orderFaxNumber"  value="${orderVO.orderFaxNumber}" placeholder="fax" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">발주일자</th>
          <th class='text-center'>
          <input  disabled type="text" class="form-control" id="orderDate" name="orderDate"  value="${orderVO.orderDate}" placeholder="SMS" />
          </th>
          <th rowspan='2' class='text-center' style="background-color:#E6F3FF">배송주소</th>
          <th rowspan='2' class='text-center'><textarea disabled style='height:82px'  class="form-control" row="2" id="orderAddress" name="orderAddress" >${orderVO.orderAddress}</textarea></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품일자</th>
          <th class='text-center'>
          <input  disabled type="text" class="form-control" id="deliveryDate" name="deliveryDate"  value="${orderVO.deliveryDate}" placeholder="SMS" />
          </th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품방법</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="deliveryMethod" name="deliveryMethod"  value="${orderVO.deliveryMethod}" placeholder="납품방버" /></th>
          <th class='text-center' style="background-color:#E6F3FF">결재방법</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="payMethod" name="payMethod"  value="${orderVO.payMethod}" placeholder="결재방법" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">SMS내용</th>
          <th colspan='4' class='text-center'><input  disabled type="text" class="form-control" id="sms" name="sms"  value="${orderVO.sms}" placeholder="SMS" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">메모&nbsp;<img id="etcbtn" onClick="fcMemo_detail('${orderVO.orderCode}','${orderVO.memo}')" src="<%= request.getContextPath()%>/images/common/icon_memo.gif" width="16" height="16" align="absmiddle" title="메모">
          <button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcMemo_detail('${orderVO.orderCode}','${orderVO.memo}')" >관리</button></th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${orderVO.memo}" placeholder="메모" disabled /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="orderListVO" id="orderDetailListForm" name="orderDetailListForm" method="post" action="" >
      <p> <span class="glyphicon glyphicon-asterisk"></span> 
          <span id="totalOrderAmt" style="color:red">
        </span>
      </p>       
	  <table class="table table-bordered" >
      	<tr style="background-color:#E6F3FF">
          <th rowspan='2' class='text-center' >검수<br>
          <c:if test="${orderVO.orderState!='06' && orderVO.orderState!='07'}">
          <input type="checkbox"  id="orderCheckAll"  name="orderCheckAll" onchange="fcOrder_checkAll();" title="전체선택" />
          </c:if>
          </th>
          <th rowspan='2' class='text-center'>품목코드</th>
          <th rowspan='2' class='text-center'>바코드</th>
          <th rowspan='2' class='text-center'>상품명</th>
          <th colspan='4' class='text-center'>단가</th>
          <th colspan='2' class='text-center'>수량</th>
          <th rowspan='2' class='text-center'>비고</th>
      	</tr>
      	<tr style="background-color:#E6F3FF">
          <th class='text-center'>기준단가</th>
          <th class='text-center'>부가세</th>
          <th class='text-center'>구매단가</th>
          <th class='text-center'>구매합계</th>
          <th class='text-center'>발주수량</th>
          <th class='text-center'>구매수량</th>
      	</tr>
	    	<c:if test="${!empty orderDetailList}">
             <c:forEach items="${orderDetailList}" var="orderVO" varStatus="status">
             	 <input type="hidden" id="seqs" name="seqs" >
	             <tr id="select_tr_${orderVO.productCode}">
				 <input type="hidden" name="productCode" value="${orderVO.productCode}">
				 <input type="hidden" name="barCode" value="${orderVO.barCode}">
				 <input type="hidden" name="productName" value="${orderVO.productName}">
				 <input type="hidden" name="stockDate" value="${orderVO.stockDate}">
				 <c:choose>
		    		<c:when test="${orderVO.orderState!='06' && orderVO.orderState!='07'}">
	                 <c:choose>
			    		<c:when test="${orderVO.orderCheck=='Y'}">
							<td class='text-center'><input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" checked onChange="totalCheck()" /></td>
						</c:when>
						<c:otherwise>
							<td class='text-center'><input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" onChange="totalCheck()" /></td>
						</c:otherwise>
					</c:choose>
					</c:when>
					<c:otherwise>
						<td class='text-center'><input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" checked disabled  /></td>
					</c:otherwise>
				</c:choose>
                 <td class='text-center'><c:out value="${orderVO.productCode}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.barCode}"></c:out></td>
                 <td class='text-left'><c:out value="${orderVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.orderPrice}" /></td>
                 <td class='text-right'><input style="width:45px" type="text" class="form-control" id="orderVatRate" name="orderVatRate" onKeyup="totalOrderAmt()" value="${orderVO.orderVatRate}"></td>
                 <td class='text-right'><input style="width:80px" type="text" class="form-control" id="orderResultPrice" name="orderResultPrice" onKeyup="totalOrderAmt()" value="${orderVO.orderResultPrice}"></td>
                 <td class='text-right' id='orderTotalPriceView' name='orderTotalPriceView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="0"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.orderCnt}"/></td>
                 <td class='text-right'><input style="width:35px" type="text" class="form-control" id="orderResultCnt" name="orderResultCnt" onKeyup="totalOrderAmt()" value="${orderVO.orderResultCnt}"></td>
                 <td class='text-right'><img id="etcbtn" onClick="fcEtc_detail('${orderVO.orderCode}','${orderVO.productCode}','${orderVO.productName}','${orderVO.etc}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고"></td>
                  <tr>
	             	<td colspan='11' class='text-center'><input type="text" class="form-control" id="etc" name="etc"  value="${orderVO.etc}" placeholder="비고" disabled /></td>
	             </tr>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty orderDetailList}">
           <tr>
           	<td colspan='11' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	  </table>
	 </form:form>
	</div>
	<div id="deferReasonList"  title="보류사유"></div>
    <!-- //보류 상세화면 -->
    <div id="memoManage"  title="메모관리"></div>
    <!-- //보류 상세화면 -->
    <div id="etcManage"  title="비고"></div>
    <!-- //보류 상세화면 -->
	<script type="text/javascript">

    $(function () {
        $('#datetimepicker3').datetimepicker(
        		{
                	language:  'kr',
                    format: 'yyyy-mm-dd',
                    weekStart: 1,
                    todayBtn:  1,
            		autoclose: 1,
            		todayHighlight: 1,
            		startView: 2,
            		minView: 2,
            		forceParse: 0
                });
        $('#datetimepicker4').datetimepicker(
        		{
                	language:  'kr',
                    format: 'yyyy-mm-dd',
                    weekStart: 1,
                    todayBtn:  1,
            		autoclose: 1,
            		todayHighlight: 1,
            		startView: 2,
            		minView: 2,
            		forceParse: 0
                });
    });
    
    totalOrderAmt();
    totalCheck();
    
</script>