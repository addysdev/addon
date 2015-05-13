<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
 

function tmt_winLaunch(theURL,winName,targetName,features) {
	
	var targetRandom=Math.random();
	eval(winName+"=window.open('"+theURL+"','"+targetRandom+"','"+features+"')");

}
/*
 * print 화면 POPUP
 */
function fcTargetDetail_print(){
	
	var h=800;
	var s=950;

    tmt_winLaunch('<%= request.getContextPath()%>/order/targetdetailprint' , 'orderPrintObj', 'orderPrintObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
	
}
//발주처리
function fcOrder_process(){
	
	var frm=document.deferDetailForm;
	var emailCheckCnt = $('input:checkbox[ name="emailCheck"]:checked').length;
	var smsCheckCnt = $('input:checkbox[ name="smsCheck"]:checked').length;

	if(emailCheckCnt > 0){
		
		frm.emailKey.value='Y';
		
		if(frm.email.value==''){
			
			alert('발주 대상 이메일 주소가 없습니다.');
			return;
		}
		
		if(frm.email.value != 'pjh@addys.co.kr'){
			if(frm.email.value != 'toaduddlf@naver.com'){
				if(frm.email.value != 'ideal314@naver.com'){
					if(frm.email.value != 'kevin.jeon@offact.com'){
						if(frm.email.value != 'soyung.shin@offact.com'){
							if(frm.email.value != 'patrick.park@offact.com'){
								alert('테스트 기간에는 정해진 메일주소가 아닌 고객메일로는 발주[e-mail] 처리가 불가합니다.\n수신메일 주소를 본인 메일로 변경하여 테스트 하시기 바랍니다.');
								return;
							}
						}
					}
				}
			}
		}
		
		if(smsCheckCnt > 0){
			
			if(frm.mobilePhone.value==''){
				
				alert('발주 대상 sms 번호가 없습니다.');
				return;
			}
			
			if(!confirm("발주 처리 하시겠습니까?\n상품 주문서는 이메일["+frm.email.value+"] 과\nSMS ["+frm.mobilePhone.value+"] 로 전송됩니다."))
				return;
			
		}else{
			
			frm.smsKey.value='N';
			
			if(!confirm("발주 처리 하시겠습니까?\n상품 주문서는 이메일["+frm.email.value+"]로 전송됩니다."))
				return;
		}

    }
	
	
    var frm = document.deferDetailListForm;
    
   	if(frm.seqs.length>1){
   		for(i=0;i<frm.seqs.length;i++){
			frm.seqs[i].value=fillSpace(frm.productCode[i].value)+
   			'|'+fillSpace(frm.productName[i].value)+'|'+fillSpace(frm.productPrice[i].value)+'|'+fillSpace(frm.orderCnt[i].value)+
   			'|'+fillSpace(frm.addCnt[i].value)+'|'+fillSpace(frm.lossCnt[i].value)+'|'+fillSpace(frm.safeStock[i].value)+
   			'|'+fillSpace(frm.holdStock[i].value)+'|'+fillSpace(frm.stockCnt[i].value)+'|'+fillSpace(frm.etc[i].value)+'|'+fillSpace(frm.stockDate[i].value);

   		}
   	}else{
   		
		frm.seqs.value=fillSpace(frm.productCode.value)+
		'|'+fillSpace(frm.productName.value)+'|'+fillSpace(frm.productPrice.value)+'|'+fillSpace(frm.orderCnt.value)+
		'|'+fillSpace(frm.addCnt.value)+'|'+fillSpace(frm.lossCnt.value)+'|'+fillSpace(frm.safeStock.value)+
		'|'+fillSpace(frm.holdStock.value)+'|'+fillSpace(frm.stockCnt.value)+'|'+fillSpace(frm.etc.value)+'|'+fillSpace(frm.stockDate.value);


   	}
	
	
    $.ajax({
        type: "POST",
        async:false,
           url:  "<%= request.getContextPath() %>/order/orderProcess",
           data:$("#deferDetailForm").serialize()+'&'+$("#deferDetailListForm").serialize(),
           success: function(result) {
        	   
        	    resultMsg(result);
        	    
	            $('#targetDetailView').dialog('close');
				fcTarget_listSearch();
				
           },
           error:function(){
        	   
        	   alert('발주 처리 호출오류!');
        	   $('#targetDetailView').dialog('close');
        	   
           }
    });
}

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

function fcDefer_modify(){

	alert('개발중입니다.');
	return;
	
    	if($("#defer_reason_div").val()==''){
    		alert('보류사유를 입력하세요!');
    		return;
    	}else{
    		
    		var checkedCnt = $('input:checkbox[ name="deferCheck"]:checked').length;

        	if(checkedCnt <= 0){
            	alert("보류 대상을 선택해 주세요!");
            	return;
            }
            
            var arrDeferProductId = "";
            $('input:checkbox[name="deferCheck"]').each(function() {
                if ($(this).is(":checked")) {
                	arrDeferProductId += $(this).val() + "^";
                }   
            });
            
            var frm = document.deferDetailListForm;
       
           	if(frm.seqs.length>1){
           		for(i=0;i<frm.seqs.length;i++){
   					frm.seqs[i].value=fillSpace(frm.productCode[i].value)+
           			'|'+fillSpace(frm.productName[i].value)+'|'+fillSpace(frm.productPrice[i].value)+'|'+fillSpace(frm.orderCnt[i].value)+
           			'|'+fillSpace(frm.addCnt[i].value)+'|'+fillSpace(frm.lossCnt[i].value)+'|'+fillSpace(frm.safeStock[i].value)+
           			'|'+fillSpace(frm.holdStock[i].value)+'|'+fillSpace(frm.stockCnt[i].value)+'|'+fillSpace(frm.etc[i].value)+'|'+fillSpace(frm.stockDate[i].value);
     
           		}
           	}else{
           		
   				frm.seqs.value=fillSpace(frm.productCode.value)+
       			'|'+fillSpace(frm.productName.value)+'|'+fillSpace(frm.productPrice.value)+'|'+fillSpace(frm.orderCnt.value)+
       			'|'+fillSpace(frm.addCnt.value)+'|'+fillSpace(frm.lossCnt.value)+'|'+fillSpace(frm.safeStock.value)+
       			'|'+fillSpace(frm.holdStock.value)+'|'+fillSpace(frm.stockCnt.value)+'|'+fillSpace(frm.etc.value)+'|'+fillSpace(frm.stockDate.value);


           	}
            	
            document.deferDetailForm.deferReason.value=$("#defer_reason_div").val();
            var paramString = $("#targetDetailForm").serialize()+ "&arrDeferProductId="+arrDeferProductId+'&'+$("#targetDetailListForm").serialize();
 
	  		$.ajax({
		       type: "POST",
		       async:false,
		          url:  "<%= request.getContextPath() %>/order/defermodify",
		          data:paramString,
		          success: function(result) {
	
		        	resultMsg(result);

					$('#deferdialog').dialog('close');
					$('#targetDetailView').dialog('close');
					fcTarget_listSearch();
						
		          },
		          error:function(){

		          $('#deferdialog').dialog('close');
				  $('#targetDetailView').dialog('close');
			      fcTarget_listSearch();
		          }
		    });
    	}	
	}
    function fcDefer_cancel(){
    	

    	alert('개발중입니다.');
    	return;
    	
    }
    
    function fcDefer_list(){
    	

    	alert('개발중입니다.');
    	return;
    	
    }
    function totalOrderAmt(){
    	
    	var frm=document.deferDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	var supplyamt=0;
    	var vatamt=0;
    	var totalamt=0;
    	
    	if(amtCnt>1){
	    	for(i=0;i<amtCnt;i++){
	    		
	    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice[i].value))));
	    		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCnt[i].value))));
	    		var vatRate=frm.vatRate[i].value;
	    		var sum_supplyAmt=productPrice*orderCnt;
	
	    		supplyamt=supplyamt+sum_supplyAmt;
	    		var sum_vatAmt=Math.round(sum_supplyAmt*vatRate);
	
	    		vatamt=vatamt+sum_vatAmt;
	    	}
    	}else{
    		
    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice.value))));
    		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCnt.value))));
    		var vatRate=frm.vatRate.value;
    		var sum_supplyAmt=productPrice*orderCnt;

    		supplyamt=supplyamt+sum_supplyAmt;
    		var sum_vatAmt=Math.round(sum_supplyAmt*vatRate);

    		vatamt=vatamt+sum_vatAmt;
    		
    	}

    	  totalamt=supplyamt+vatamt;
    	
    	  document.all('totalOrderAmt').innerText='[합계] : '+addCommaStr(''+totalamt)+' 원  [공급가] : '+addCommaStr(''+supplyamt)+' 원  [부가세] : '+addCommaStr(''+vatamt)+' 원';
    }
    
    function fcAdd_Cnt(index){
    	
    	var frm=document.deferDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	
    	if(amtCnt > 1){
    		
    		frm.lossCnt[index-1].value=0;
    	    
    		var holdStock=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.holdStock[index-1].value))));
    		var orderCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCntRaw[index-1].value))));
			var addCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.addCnt[index-1].value))));

			var orderCnt=(orderCntRaw-addCnt);
	
			if(orderCntRaw<orderCnt || 0>orderCnt){
				alert('재고수량 추가는 보유재고범위를 넘을수 없습니다.');
				frm.orderCnt[index-1].value=orderCntRaw;
				document.all('orderCntView')[index-1].innerText=orderCntRaw;
				frm.addCnt[index-1].value=0;
				return;
			}else{
				frm.orderCnt[index-1].value=orderCnt;
				document.all('orderCntView')[index-1].innerText=orderCnt;
			}
			

    	}else{
    		
			frm.lossCnt.value=0;
    	    
    		var holdStock=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.holdStock.value))));
    		var orderCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCntRaw.value))));
			var addCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.addCnt.value))));
	
			var orderCnt=(orderCntRaw-addCnt);
		
			if(orderCntRaw<orderCnt || 0>orderCnt){
				alert('재고수량 추가는 보유재고범위를 넘을수 없습니다.');
				frm.orderCnt.value=orderCntRaw;
				document.all('orderCntView').innerText=orderCntRaw;
				frm.addCnt.value=0;
				return;
			}else{
				frm.orderCnt.value=orderCnt;
				document.all('orderCntView').innerText=orderCnt;
			}
			
    	}
		
    	
    	totalOrderAmt();
    }
 
	function fcLoss_Cnt(index){
    	
    	var frm=document.deferDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	
    	if(amtCnt > 1){
    		
    		frm.addCnt[index-1].value=0;
    	    
    		var holdStock=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.holdStock[index-1].value))));
    		var orderCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCntRaw[index-1].value))));
			var lossCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.lossCnt[index-1].value))));

			var orderCnt=(orderCntRaw+lossCnt);
	
			if(holdStock<orderCnt){
				alert('재고수량은 보유재고수량을 넘을수 없습니다.');
				frm.orderCnt[index-1].value=orderCntRaw;
				document.all('orderCntView')[index-1].innerText=orderCntRaw;
				frm.lossCnt[index-1].value=0;
				return;
			}else{
				frm.orderCnt[index-1].value=orderCnt;
				document.all('orderCntView')[index-1].innerText=orderCnt;
			}

    	}else{
    		
			frm.addCnt.value=0;
    	    
    		var holdStock=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.holdStock.value))));
    		var orderCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCntRaw.value))));
			var lossCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.lossCnt.value))));

			var orderCnt=(orderCntRaw+lossCnt);
	
			if(holdStock<orderCnt){
				alert('재고수량은 보유재고수량을 넘을수 없습니다.');
				frm.orderCnt.value=orderCntRaw;
				document.all('orderCntView').innerText=orderCntRaw;
				frm.lossCnt.value=0;
				return;
			}else{
				frm.orderCnt.value=orderCnt;
				document.all('orderCntView').innerText=orderCnt;
			}
			
    	}
		
    	totalOrderAmt();
    }
    
	//체크박스 전체선택
    function fcDefer_checkAll(){
		
    	$("input:checkbox[id='deferCheck']").prop("checked", $("#deferCheckAll").is(":checked"));
    }
    
</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="deferVO" id="deferDetailForm"  name="targetDetailForm" method="post" action="" >
	   <input type="hidden" name="emailKey"             id="emailKey"            value="Y" />
	   <input type="hidden" name="smsKey"               id="smsKey"            value="N" />
	   <input type="hidden" name="faxKey"               id="faxKey"            value="N" />
	   <input type="hidden" name="deferReason"               id="deferReason"            value="" />
	   <input type="hidden" name="groupId"               id="groupId"            value="${deferVO.groupId}" />
	   <input type="hidden" name="companyCode"               id="companyCode"            value="${deferVO.companyCode}" />
	      <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span> 발주방법 : </font></strong>
	          <input type="checkbox" id="emailCheck" name="emailCheck" value="" title="선택" checked disabled />e-mail
	          <input type="checkbox" id="smsCheck" name="smsCheck" value="" title="선택" disabled />sms
	      </h4>
	      <tr>
	      <div style="position:absolute; left:30px" >
	      <button id="defermodifybtn"  type="button" class="btn btn-primary">보류수정</button>
	      <button id="defercancelbtn"  type="button" class="btn btn-danger" >보류폐기</button>
	      <button id="deferlistbtn"  type="button" class="btn btn-info" onClick="fcDefer_list()">보류사유</button>
          </div >
          <div id="defermodifydialog" class="form-group" title="보류수정사유를 입력하세요">
			<p><input type="text" class="form-control" id="defer_modify_reason_div" name="defer_reason_div"  value=""  placeholder="보류수정사유"/></p>
			<button id="defermodifysavebtn" type="button" class="btn btn-primary" onClick="fcDefer_modify()">save</button> <button id="defermodifypopclosebtn" type="button" class="btn btn-danger">cancel</button>
          </div>
          <div id="defercanceldialog" class="form-group" title="보류폐기사유를 입력하세요">
			<p><input type="text" class="form-control" id="defer_cancel_reason_div" name="defer_reason_div"  value=""  placeholder="보류수정사유"/></p>
			<button id="defercancelsavebtn" type="button" class="btn btn-primary" onClick="fcDefer_cancel()">save</button> <button id="defercancelpopclosebtn" type="button" class="btn btn-danger">cancel</button>
          </div>
          <div style="position:absolute; right:30px" > 
          <button type="button" class="btn btn-success" onClick="fcTargetDetail_print()">출력</button>
          <button type="button" class="btn btn-primary" onClick="fcOrder_process()">발주</button>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th rowspan='9' class='text-center' style="background-color:#E6F3FF">수신</th>
          <th class='text-center'  style="background-color:#E6F3FF" >수신</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryCharge" name="deliveryCharge"  value="${deferVO.deliveryCharge}" placeholder="수신" /></th>
          <th rowspan='9' class='text-center'  style="background-color:#E6F3FF">발신</th>
          <th class='text-center' style="background-color:#E6F3FF">발신</th>
          <th class='text-center'><input type="text" class="form-control" id="orderCharge" name="orderCharge"  value="${deferVO.orderCharge}" placeholder="발신"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="deleveryEtc" name="deleveryEtc"  value="${deferVO.deliveryEtc}" placeholder="참조" /></th>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="orderUserName" name="orderUserName"  value="${deferVO.orderEtc}" placeholder="참조" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="mobilePhone" name="mobilePhone"  value="${deferVO.mobilePhone}"  placeholder="핸드폰"/></th>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="orderMobilePhone" name="orderMobilePhone"  value="${deferVO.orderMobilePhone}"  placeholder="핸드폰"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="email" name="email"  value="${deferVO.email}" placeholder="e-mail" /></th>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="orderEmail" name="orderEmail"  value="${deferVO.orderEmail}" placeholder="e-mail" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="telNumber" name="telNumber"  value="${deferVO.telNumber}" placeholder="tel" /></th>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="orderTelNumber" name="orderTelNumber"  value="${deferVO.orderTelNumber}" placeholder="tel" /></th>
      	</tr>
      	<th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="faxNumber" name="faxNumber"  value="${deferVO.faxNumber}" placeholder="fax" /></th>
          <th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="orderFaxNumber" name="orderFaxNumber"  value="${deferVO.orderFaxNumber}" placeholder="fax" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">발주일자</th>
          <th class='text-center'>
          	
          		<div style='width:150px' class='input-group date ' id='datetimepicker1' data-link-field="orderDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${deferVO.orderDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="orderDate" name="orderDate" value="${deferVO.orderDate}" />
	            </div>
	         
          </th>
          <th rowspan='2' class='text-center' style="background-color:#E6F3FF">배송주소</th>
          <th rowspan='2' class='text-center'><textarea style='height:82px'  class="form-control" row="2" id="orderAddress" name="orderAddress" >서울특별시 영등포구 여의도동 54-6 영창빌딩 6층 물류팀</textarea></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품일자</th>
          <th class='text-center'>
          		
          		<div  style='width:150px' class='input-group date ' id='datetimepicker2' data-link-field="deliveryDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${deferVO.deliveryDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="deliveryDate" name="deliveryDate" value="${deferVO.deliveryDate}" />
	            </div>
             
          </th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품방법</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryMethod" name="deliveryMethod"  value="${deferVO.deliveryMethod}" placeholder="납품방버" /></th>
          <th class='text-center' style="background-color:#E6F3FF">결재방법</th>
          <th class='text-center'><input type="text" class="form-control" id="payMethod" name="payMethod"  value="${deferVO.payMethod}" placeholder="결재방법" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">SMS내용</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="sms" name="sms"  value="${deferVO.sms}" placeholder="SMS" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">메모</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${deferVO.memo}" placeholder="메모" /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="deferListVO" id="deferDetailListForm" name="deferDetailListForm" method="post" action="" >
      <p> <span class="glyphicon glyphicon-asterisk"></span> 
          <span id="totalOrderAmt" style="color:red">
        </span>
      </p>       
	  <table class="table table-bordered" >
      	<tr style="background-color:#E6F3FF">
          <th rowspan='2' class='text-center' >보류<br><input type="checkbox"  id="deferCheckAll"  name="deferCheckAll" onchange="fcDefer_checkAll();" title="전체선택" /></th>
          <th rowspan='2' class='text-center'>품목코드</th>
          <th rowspan='2' class='text-center'>상품명</th>
          <th colspan='2' class='text-center'>발주</th>
          <th colspan='5' class='text-center'>재고</th>
          <th rowspan='2' class='text-center'>비고</th>
      	</tr>
      	<tr style="background-color:#E6F3FF">
          <th class='text-center'>기준단가</th>
          <th class='text-center'>수량</th>
          <th class='text-center'>+</th>
          <th class='text-center'>-</th>
          <th class='text-center'>안전</th>
          <th class='text-center'>보유</th>
          <th class='text-center'>전산</th>
      	</tr>
	    	<c:if test="${!empty deferDetailList}">
             <c:forEach items="${deferDetailList}" var="deferVO" varStatus="status">
             	 <input type="hidden" id="seqs" name="seqs" >
	             <c:choose>
		    		<c:when test="${deferVO.stockCnt<deferVO.safeStock}">
						<tr id="select_tr_${deferVO.productCode}" style="color:red">
					</c:when>
					<c:otherwise>
						<tr id="select_tr_${deferVO.productCode}">
					</c:otherwise>
				</c:choose>
				 <input type="hidden" name="productCode" value="${deferVO.productCode}">
				 <input type="hidden" name="productName" value="${deferVO.productName}">
				 <input type="hidden" name="safeStock" value="${deferVO.safeStock}">
				 <input type="hidden" name="stockCnt" value="${deferVO.stockCnt}">
				 <input type="hidden" name="etc" value="${deferVO.etc}">
				 <input type="hidden" name="stockDate" value="${deferVO.stockDate}">
                 <c:choose>
		    		<c:when test="${deferVO.deferCheck=='Y'}">
						<td class='text-center'><input type="checkbox" id="deferCheck" name="deferCheck" value="${targetVO.productCode}" title="선택" checked/></td>
					</c:when>
					<c:otherwise>
						<td class='text-center'><input type="checkbox" id="deferCheck" name="deferCheck" value="${targetVO.productCode}" title="선택" /></td>
					</c:otherwise>
				</c:choose>
                 <td class='text-center'><c:out value="${deferVO.productCode}"></c:out></td>
                 <td class='text-left'><c:out value="${deferVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${deferVO.productPrice}" /></td>
                 <input type="hidden" id="productPrice" name="productPrice" value="${deferVO.productPrice}" >
                 <td class='text-right' id='orderCntView' name='orderCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${deferVO.orderCnt}"/></td>
                 <input type="hidden" id="orderCnt" name="orderCnt" value="${deferVO.orderCnt}" >
                 <input type="hidden" id="orderCntRaw" name="orderCntRaw" value="${deferVO.orderCnt}" >
                 <input type="hidden" id="vatRate" name="vatRate" value="${deferVO.vatRate}" >
                 <input type="hidden" id="holdStock" name="holdStock" value="${deferVO.holdStock}" >
                 <td class='text-right'><input style="width:35px" type="text" class="form-control" id="addCnt" name="addCnt" onKeyup="fcAdd_Cnt('${status.count}')" value="0"></td>
                 <td class='text-right'><input style="width:35px" type="text" class="form-control" id="lossCnt" name="lossCnt" onKeyup="fcLoss_Cnt('${status.count}')" value="0"></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${deferVO.safeStock}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${deferVO.holdStock}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${deferVO.stockCnt}"/></td>
                 <td class='text-right'><c:out value=""></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty deferDetailList}">
           <tr>
           	<td colspan='11' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	  </table>
	 </form:form>
	</div>
	<script type="text/javascript">

    $(function () {
        $('#datetimepicker1').datetimepicker(
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
        $('#datetimepicker2').datetimepicker(
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
</script>