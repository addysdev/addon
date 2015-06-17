<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<head>
<title>바코드 검수</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
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

//보류사유추가
function fcDefer_reasonadd(){

	if('${deferType}'=='D'){	
		fcDefer_cancel(document.barCodeForm.barcode_list.value);
	}else{
		fcDefer_reason(document.deferForm.defer_reason.value);
		
	}
}
function fcBarCode_close(){
	//$("#barCodeDialog").dialog('close');
	this.close();
}

function fcBarCode_cancel(){
	
	if (confirm('바코드 스캐너를 통한 검수를 취소하시겠습니까?\n취소하실 경우 검수수량은 발주수량으로 기본세팅되며\n직접 수량 확인 하시기 바랍니다.')){ 
		

		var frm=opener.document.orderDetailListForm;
		var amtCnt = frm.productCode.length;

		if(amtCnt==undefined){
			amtCnt=1;
		}
		
		if(amtCnt > 1){
			
	    	for(i=0;i<amtCnt;i++){
	    		frm.orderResultCnt[i].value=frm.orderCnt[i].value;
	    		opener.document.all('barCodeView')[i].innerText='';
	    		opener.document.all('barCodeCheckColor')[i].style.backgroundColor='';
	    		frm.orderCheck[i].checked=false;
	    	}
	    	
		}else{
			
			frm.orderResultCnt.value=frm.orderCnt.value;
			opener.document.all('barCodeView').innerText='';
			opener.document.all('barCodeCheckColor')[i].style.backgroundColor='';
			frm.orderCheck.checked=false;
		}

		opener.totalOrderAmt();
		opener.totalCheck();

		//$("#barCodeDialog").dialog('close');
		this.close();
	}
}

var cCnt=0;

function EnterKey(e)
{ 
  if(e.keyCode == 13)    // KeyCode  가 아니고 keyCode 입니다 .. 소문자 k
  {
	var ibarCode=trim(document.barCodeForm.barcode_list.value);

	var barCodes=ibarCode.split('\n');
	
	var frm=opener.document.orderDetailListForm;
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
    		opener.document.all('barCodeCheckColor')[i].style.backgroundColor='#FEE2B4';
    	}
    	
	}else{
		
		frm.orderResultCnt.value=0;
		opener.document.all('barCodeCheckColor').style.backgroundColor='#FEE2B4';
	}

	for(x=0;x<barCodes.length;x++){
		
		totCnt++;
		
		if(amtCnt > 1){

	    	for(i=0;i<amtCnt;i++){

	    		if(frm.barCode[i].value==barCodes[x]){	
	    
	    			frm.orderResultCnt[i].value=addCommaStr(''+(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt[i].value)))+1));
	    			opener.document.all('barCodeView')[i].innerText=barCodes[x];
	    			opener.document.all('barCodeCheckColor')[i].style.backgroundColor='';
	    			if(frm.orderCnt[i].value==frm.orderResultCnt[i].value){
		    			frm.orderCheck[i].checked=true;
	    			}else{
	    				frm.orderCheck[i].checked=false;
	    			}

	    			sCnt++;
	    			
	    			break;
	    		}
	    		
	    	}
	    	
		}else{

			if(frm.barCode.value==barCodes[x]){	
				
				frm.orderResultCnt.value=addCommaStr(''+(parseInt(isnullStr(deleteCommaStr(frm.orderResultCnt.value)))+1));
				opener.document.all('barCodeView').innerText=barCodes[x];
				opener.document.all('barCodeCheckColor').style.backgroundColor='';
				if(frm.orderCnt.value==frm.orderResultCnt.value){
	    			frm.orderCheck.checked=true;
    			}else{
    				frm.orderCheck.checked=false;
    			}
				
    			sCnt++;
    		

    		}
		}
		
	}
    fCnt=totCnt-sCnt;
    
    if(fCnt>cCnt){
    	cCnt++;
    	alert('바코드 미일치 품목건수 가 발생했습니다.\n검수품목을 확인 하시기 바랍니다.');
    }

	opener.totalOrderAmt();
	opener.totalCheck();

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
</head>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
<form:form commandName="barCodeVO" id="barCodeForm" name="barCodeForm" method="post" action="" >
<br>
		<table class="table table-bordered tbl_type" >
	     <colgroup>
	      <col width="70px" >
	      <col width="50px" >
	      <col width="70px" >
	      <col width="50px" >
	     </colgroup>
	     <tr>
	     	<td style="background-color:#E6F3FF" class='text-center'>검수대상</td>
	     	<td class='text-right'><span style="color:gray">
	          ${orderCnt}
	          </span></td>
	        <td style="background-color:#E6F3FF" class='text-center' >검수대기</td>
	     	<td class='text-right'><span id="checkTCnt" style="color:red">0건</span></td>
	     </tr>
	     <tr>
	     	<td style="background-color:#E6F3FF" class='text-center' >일치</td>
	     	<td class='text-right'><span id="checkSCnt" style="color:red">0건</span></td>
	     	<td style="background-color:#E6F3FF" class='text-center' >미일치</td>
	     	<td class='text-right'><span id="checkFCnt" style="color:red">0건</span></td>	
	     </tr>
     </table>  
<p><textarea style='height:340px;ime-mode:active;' class="form-control" id="barcode_list" name="barcode_list"  value=""  placeholder="바코드를 스캔하세요"  onkeyPress='javascript:EnterKey(event);'/></textarea></p>
<br>
<button id="deferpopclosebtn" type="button" class="btn btn-danger" onClick="fcBarCode_cancel()">바코드 검수취소</button>&nbsp;<button id="deferpopclosebtn" type="button" class="btn btn-default" onClick="fcBarCode_close()">닫기</button>  
</form:form>
</div>
</body>
<script>
$('#barcode_list').focus(1); 
</script>