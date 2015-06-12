<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

//보류사유추가
function fcDefer_reasonadd(){

	if('${deferType}'=='D'){	
		fcDefer_cancel(document.barCodeForm.barcode_list.value);
	}else{
		fcDefer_reason(document.deferForm.defer_reason.value);
		
	}
}
function fcBarCode_close(){
	$("#barCodeDialog").dialog('close');
}

function fcBarCode_cancel(){
	
	if (confirm('바코드 스캐너를 통한 검수를 취소하시겠습니까?\n취소하실 경우 검수수량은 발주수량으로 기본세팅되며\n직접 수량 확인 하시기 바랍니다.')){ 
		
		var frm=document.orderDetailListForm;
		var amtCnt = frm.productCode.length;
		
		if(amtCnt==undefined){
			amtCnt=1;
		}
		
		if(amtCnt > 1){
			
	    	for(i=0;i<amtCnt;i++){
	    		frm.orderResultCnt[i].value=frm.orderCnt[i].value;
	    	}
	    	
		}else{
			
			frm.orderResultCnt.value=frm.orderCnt.value;
		}
		
		totalOrderAmt();

		$("#barCodeDialog").dialog('close');
	}
}

function EnterKey(e)
{ 
  if(e.keyCode == 13)    // KeyCode  가 아니고 keyCode 입니다 .. 소문자 k
  {
	var ibarCode=trim(document.barCodeForm.barcode_list.value);
	var barCodes=ibarCode.split('\n');
	
	var frm=document.orderDetailListForm;
	var amtCnt = frm.productCode.length;
	
	if(amtCnt==undefined){
		amtCnt=1;
	}
	
	var totCnt=0;
	var sCnt=0;
	var fCnt=0;

	if(amtCnt > 1){
		
    	for(i=0;i<amtCnt;i++){
    		frm.orderResultCnt[i].value=0;
    	}
    	
	}else{
		
		frm.orderResultCnt.value=0;
	}
	
	for(x=0;x<barCodes.length;x++){
		
		totCnt++;
		
		if(amtCnt > 1){
			
	    	for(i=0;i<amtCnt;i++){
	    		//alert(frm.barCode[i].value);
	    		if(frm.barCode[i].value==barCodes[x]){	
	    			//alert(frm.barCode[i].value+':'+barCodes[x]);
	    			frm.orderResultCnt[i].value=addCommaStr(''+(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt[i].value)))+1));
	    			document.all('barCodeView')[i].innerText=barCodes[x];
	    			sCnt++;
	    			break;
	    		}
	    	}
	    	
		}else{
			
			if(frm.barCode.value==barCodes[x]){	
				frm.orderResultCnt.value=addCommaStr(''+(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt.value)))+1));
				document.all('barCodeView').innerText=barCodes[x];
    			sCnt++;
    		}
		}

	}
    fCnt=totCnt-sCnt;

	totalOrderAmt();
	
	document.all('checkTCnt').innerText=totCnt+'건';
	document.all('checkSCnt').innerText=sCnt+'건';
	document.all('checkFCnt').innerText=fCnt+'건';

  } else {
      // 엔터가 아닌 경우
  }
}

function barCodeCheck(){
	
	var frm=document.orderDetailListForm;
	var amtCnt = frm.productCode.length;
	
	if(amtCnt==undefined){
		amtCnt=1;
	}
	
	var supplyamt=0;
	var vatamt=0;
	var totalamt=0;
	var totalcnt=0;
	
	if(amtCnt > 1){
		
    	for(i=0;i<amtCnt;i++){
    		
    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultPrice[i].value))));
    		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt[i].value))));
    		var vatAmt=frm.orderVatRate[i].value;
    		var sum_supplyAmt=productPrice*orderCnt;

    		var sum_supplyAmt=productPrice*orderCnt;
    		supplyamt=supplyamt+sum_supplyAmt;
    		
    		var sum_vatAmt=Math.floor(+vatAmt)*orderCnt;
    		vatamt=vatamt+sum_vatAmt;
    		totalcnt=totalcnt+orderCnt;

    		document.all('orderTotalPriceView')[i].innerText=addCommaStr(''+(productPrice*orderCnt));

    	}
    	
	}else{
		
		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultPrice.value))));
		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt.value))));
		var vatAmt=frm.orderVatRate.value;
		var sum_supplyAmt=productPrice*orderCnt;

		var sum_supplyAmt=productPrice*orderCnt;
		supplyamt=supplyamt+sum_supplyAmt;
		
		var sum_vatAmt=Math.floor(+vatAmt)*orderCnt;
		vatamt=vatamt+sum_vatAmt;
		totalcnt=totalcnt+orderCnt;

		document.all('orderTotalPriceView').innerText=addCommaStr(''+(productPrice*orderCnt));
	}

	  totalamt=supplyamt+vatamt;
	
	  document.all('totalOrderCnt').innerText=' '+addCommaStr(''+totalcnt)+' 건';
	  document.all('totalOrderAmt').innerText=' '+addCommaStr(''+totalamt)+' 원';//  [공급가] : '+addCommaStr(''+supplyamt)+' 원  [부가세] : '+addCommaStr(''+vatamt)+' 원';
}
</SCRIPT>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="barCodeVO" id="barCodeForm" name="barCodeForm" method="post" action="" >
<span style="color:blue">[검수]</span>
          <span id="checkTCnt" style="color:red">0건
          </span>&nbsp;
          <span style="color:blue">[일치]</span>
          <span id="checkSCnt" style="color:red">0건
          </span>&nbsp;
           <span style="color:blue">[미일치]</span>
          <span id="checkFCnt" style="color:red">0건
          </span>
          <br><br>
<p><textarea style='height:340px;ime-mode:active;' class="form-control" id="barcode_list" name="barcode_list"  value=""  placeholder="바코드를 스캔하세요" onkeyPress='javascript:EnterKey(event);' /></p>
<br>
<button id="deferpopclosebtn" type="button" class="btn btn-danger" onClick="fcBarCode_cancel()">바코드 검수취소</button>&nbsp;<button id="deferpopclosebtn" type="button" class="btn btn-default" onClick="fcBarCode_close()">닫기</button>  
</form:form>
</div>
</body>
<script>
$('#barcode_list').focus(1); 
</script>