<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<style>

 .thead { height:30px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:680px; .height:670px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
<SCRIPT>

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
function fcRecovery_print(recoveryCode){
	
	
	pintYN=true;
	
	var h=800;
	var s=950;
	var frm = document.recoveryDetailListForm;
	var groupId='${recoveryConVO.groupId}';
	var groupname=encodeURIComponent('${recoveryConVO.groupName}');

	var collectDateTime='${recoveryConVO.collectDateTime}';
	var recoveryClosingDate='${recoveryConVO.recoveryClosingDate}';
	
	var url="<%= request.getContextPath() %>/recovery/recoverycodeprint?recoveryCode="+recoveryCode+"&groupId="+groupId+"&groupName="+groupname+"&collectDateTime="+collectDateTime+"&recoveryClosingDate="+recoveryClosingDate;

   // tmt_winLaunch(url, 'printObj', 'printObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
	 frm.action =url; 
	 frm.method="post";
	 frm.target='printObj';
	 frm.submit();
}

//회수처리
function fcRecovery_process(){
        
	    var dfrm = document.recoveryDetailForm;
	    
		var frm=document.recoveryDetailListForm;
		var amtCnt = frm.productPrice.length;
	    
		if(amtCnt==undefined){
			amtCnt=1;
		}

		var totalcnt=0;

		if(amtCnt>1){
	    	for(i=0;i<amtCnt;i++){	
	    		var recoveryCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCnt[i].value))));
	    		totalcnt=totalcnt+recoveryCnt;
	    	}
		}else{

			var recoveryCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCnt.value))));
    		totalcnt=totalcnt+recoveryCnt;
			
		}

	    if(totalcnt==0){
	    	
	    	if (confirm('회수 대상 수량이 없습니다.\n회수수량을 0으로  상태값만  발신으로 업데이트 하시겠습니까?')){ 
	    		
	    	}else{
	    		return;
	    	}
	    	
	    }else{
	    	
	    	if(dfrm.deliveryRadio[0].checked==true){
				
				if(dfrm.transportCode.value==''){
					alert('택배로 운송처리시 운송회사를 입력하셔야 합니다.');
					return;
				}
				
				if(dfrm.transportNo.value==''){
					alert('택배로 운송처리시 운송장 번호를 입력하셔야 합니다.');
					return;
				}
				
			}else{
				
				if(dfrm.quickCharge.value==''){
					alert('퀵 운송처리시 퀵 운송 담당자를 입력하셔야 합니다.');
					return;
				}
				
				if(dfrm.quickTel.value==''){
					alert('퀵 운송처리시 퀵 운송 연락처를 입력하셔야 합니다.');
					return;
				}
			}
		    
		    if(pintYN==false){
		    	
		    	alert('발신 처리시 회수번호를 프린트 하신후\n회수대상 BOX에 첨부하여 보내시기 바랍니다.\n인쇄버튼을 클릭하여 회수번호를 인쇄 하신 후\n다시 시도하시기 바랍니다.');
		    	return;
		    	
		    }
	    
	    }

		//alert($("#transportCode option:selected").val());
		//alert($("#transportCode option:selected").text());

		dfrm.transport.value=$("#transportCode option:selected").text();

    	if(frm.seqs.length>1){
       		for(i=0;i<frm.seqs.length;i++){
				frm.seqs[i].value=fillSpace(frm.productCode[i].value)+'|'+fillSpace(frm.stockDate[i].value)+'|'+fillSpace(frm.stockCnt[i].value)+'|'+fillSpace(frm.recoveryCnt[i].value)+
       			'|'+fillSpace(frm.addCnt[i].value)+'|'+fillSpace(frm.lossCnt[i].value)+'|'+fillSpace(frm.etc[i].value);
 
       		}
       	}else{
       		
			frm.seqs.value=fillSpace(frm.productCode.value)+'|'+fillSpace(frm.stockDate.value)+'|'+fillSpace(frm.stockCnt.value)+'|'+fillSpace(frm.recoveryCnt.value)+
   			'|'+fillSpace(frm.addCnt.value)+'|'+fillSpace(frm.lossCnt.value)+'|'+fillSpace(frm.etc.value);

       	}

        if (confirm('회수 요청건을 발신처리합니다\n계속 진행 하시겠습니까?')){ 
        	
            var paramString = $("#recoveryDetailForm").serialize()+'&'+$("#recoveryDetailListForm").serialize();
 	
	 		$.ajax({
		       type: "POST",
		       async:false,
		          url:  "<%= request.getContextPath() %>/recovery/recoveryprocess",
		          data:paramString,
		          success: function(result) {
		
		        	resultMsg(result);
		
					$('#recoveryDetailView').dialog('close');
					fcRecovery_listSearch();
						
		          },
		          error:function(){
		
		          alert('호출오류!');
		          $('#recoveryDetailView').dialog('close');
				  fcRecovery_listSearch();
			     
		          }
		    });
	   }
	
	}
//회수 검수처리
function fcRecovery_complete(){

	 var frm = document.recoveryDetailListForm;
	 var seqsCnt=frm.seqs.length;
	 
	 if(seqsCnt==undefined){
		 seqsCnt=1;
 	}
	 
 	if(seqsCnt>1){
    		for(i=0;i<frm.seqs.length;i++){
				frm.seqs[i].value=fillSpace(frm.productCode[i].value)+'|'+fillSpace(frm.recoveryResultCnt[i].value);

    		}
    	}else{
    		
    		frm.seqs.value=fillSpace(frm.productCode.value)+'|'+fillSpace(frm.recoveryResultCnt.value);

    	}

     if (confirm('회수품목을 검수 완료처리 하시겠습니까?')){ 
     	
         var paramString = $("#recoveryDetailForm").serialize()+'&'+$("#recoveryDetailListForm").serialize();
	
	 		$.ajax({
		       type: "POST",
		       async:false,
		          url:  "<%= request.getContextPath() %>/recovery/recoverycomplete",
		          data:paramString,
		          success: function(result) {
		
		        	resultMsg(result);
		
					$('#recoveryDetailView').dialog('close');
					fcRecovery_listSearch();
						
		          },
		          error:function(){
		
		          alert('호출오류!');
		          $('#recoveryDetailView').dialog('close');
				  fcRecovery_listSearch();
			     
		          }
		    });
	   }

}
//회수결과 합계
    function totalRecoveryAmt(){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}

    	var totalamt=0;
    	
    	if(amtCnt>1){
	    	for(i=0;i<amtCnt;i++){
	    		
	    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice[i].value))));
	    		var recoveryCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCnt[i].value))));
	    		var sum_supplyAmt=productPrice*recoveryCnt;
	
	    		totalamt=totalamt+sum_supplyAmt;

	    	}
    	}else{
    		
    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice.value))));
    		var recoveryCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCnt.value))));
    		var sum_supplyAmt=productPrice*recoveryCnt;

    		totalamt=totalamt+sum_supplyAmt;
    		
    	}

    	  document.all('totalRecoveryAmt').innerText=addCommaStr(''+totalamt)+' 원  ';
    }
    //검수결과합계
    function totalRecoveryResultAmt(){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}

    	var totalamt=0;
    	
    	if(amtCnt>1){
	    	for(i=0;i<amtCnt;i++){
	    		
	    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice[i].value))));
	    		var recoveryResultCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCnt[i].value))));
	    		var sum_supplyAmt=productPrice*recoveryResultCnt;
	
	    		totalamt=totalamt+sum_supplyAmt;

	    	}
    	}else{
    		
    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice.value))));
    		var recoveryResultCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCnt.value))));
    		var sum_supplyAmt=productPrice*recoveryResultCnt;

    		totalamt=totalamt+sum_supplyAmt;
    		
    	}

    	  document.all('totalRecoveryResultAmt').innerText=addCommaStr(''+totalamt)+' 원  ';
    }
    
    function fcAdd_Cnt(index){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}
    	
    	if(amtCnt > 1){
    		
    		frm.lossCnt[index-1].value=0;
    	    
    		var recoveryCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCntRaw[index-1].value))));
			var addCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.addCnt[index-1].value))));

			var recoveryCnt=(recoveryCntRaw+addCnt);
	
			frm.recoveryCnt[index-1].value=recoveryCnt;
			document.all('recoveryCntView')[index-1].innerText=recoveryCnt;
			

    	}else{
    		
			frm.lossCnt.value=0;
    	    
			var recoveryCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCntRaw.value))));
			var addCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.addCnt.value))));

			var recoveryCnt=(recoveryCntRaw+addCnt);
	
			frm.recoveryCnt.value=recoveryCnt;
			document.all('recoveryCntView').innerText=recoveryCnt;
			
    	}

    	totalRecoveryAmt();
    }
 
	function fcLoss_Cnt(index){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}
    
    	if(amtCnt > 1){
    		
    		frm.addCnt[index-1].value=0;
    	    
    		var recoveryCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCntRaw[index-1].value))));
			var lossCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.lossCnt[index-1].value))));

			var recoveryCnt=(recoveryCntRaw-lossCnt);
	
			frm.recoveryCnt[index-1].value=recoveryCnt;
			document.all('recoveryCntView')[index-1].innerText=recoveryCnt;

    	}else{
    		
			frm.addCnt.value=0;
    	    
    		var recoveryCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCntRaw.value))));
			var lossCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.lossCnt.value))));

			var recoveryCnt=(recoveryCntRaw-lossCnt);
	
			frm.recoveryCnt.value=recoveryCnt;
			document.all('recoveryCntView').innerText=recoveryCnt;
			
    	}
		
    	totalRecoveryAmt();
    }
	function fcResult_Cnt(index){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}
    	
    	if(amtCnt > 1){
    		
    		frm.recoveryResultCnt[index-1].value=frm.recoveryResultCntView[index-1].value;

    	}else{
    		
    		frm.recoveryResultCnt.value=frm.recoveryResultCntView.value;
			
    	}
		
    	totalRecoveryResultAmt();
    }
	//체크박스 전체선택
    function fcRecovery_checkAll(){
		
    	$("input:checkbox[id='recoveryCheck']").prop("checked", $("#recoveryCheckAll").is(":checked"));
    	totalCheck();
    }
	
 // 메모 페이지 리스트 Layup
    function fcMemo_detail(orderCode,memo,groupId) {
    	
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
                $(this).load(url+'?orderCode='+orderCode+'&category=05'+'&companyCode='+groupId+'&memo='+encodeURIComponent(memo));
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#memoManage").dialog('close');

                    });
            }
            ,close:function(){
            	$('#memoList').empty();
                $('#memoManage').empty();
            }
        });
    };
 // 리스트 조회
    function fcMemo_detail_bak(orderCode,memo){

    	commonDim(true);
    	document.recoveryDetailForm.orderCode.value=orderCode;
    	document.recoveryDetailForm.memo.value=memo;

        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/order/memomanage",
                    data:$("#recoveryDetailForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#memoManage").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
function totalCheck(){
	
	    //if('${recoveryVO.receiveCnt==recoveryVO.totalCnt && strAuth!= "03"}'){
	    if('${strAuth!= "03"}'){

	    	var frm=document.recoveryDetailListForm;
	    	var amtCnt = frm.productCode.length;
	    	
	    	if(amtCnt==undefined){
	    		amtCnt=1;
	    	}
	
	    	var chkCnt=0;
	    	
	    	if(amtCnt > 1){
				for(i=0;i<amtCnt;i++){
		    		
		    		if(frm.recoveryCheck[i].checked==true){
		    			frm.recoveryResultCnt[i].disabled=true;
		    			chkCnt++;
		    		}else{
		    			frm.recoveryResultCnt[i].disabled=false;
		    		}
		    	}
	    	}else{
	
	    		if(frm.recoveryCheck.checked==true){
	    			frm.recoveryResultCnt.disabled=true;
	    			chkCnt++;
		   		}else{
		   			frm.recoveryResultCnt.disabled=false;
		   		}
		  	}
	
	    	if(amtCnt==chkCnt){//검수버튼 활성화
	
	    		frm.recoveryCheckAll.checked=true;
	    		document.all('checkbtn').disabled=false;
	    		
	    	}else{
	    		frm.recoveryCheckAll.checked=false;
	    		document.all('checkbtn').disabled=true;
	    	}
	    }

    }
//품목 상세 페이지 리스트 Layup
function  fcEtc_detail(orderCode,productCode,productName,etc,idx,groupId) {
	

	if(document.recoveryDetailListForm.seqs.length==undefined){
		idx=0;
	}
	//$('#targetEtcView').attr('title',productName);
	//var url='<%= request.getContextPath() %>/order/etcmanage';
	var url='<%= request.getContextPath() %>/order/etcmanage?orderCode='+orderCode+'&category=06'+'&idx='+idx+'&companyCode='+groupId+'&productCode='+productCode+'&productName='+encodeURIComponent(productName)+'&etc='+encodeURIComponent(etc);
	
	var h=430;
	var s=780;

	tmt_winLaunch(url, 'etcObj', 'etcObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=no,scrollbars=yes');
	
/*
	$('#etcManage').dialog({g
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 800,
        height : 500,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
          //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
            $(this).load(url+'?orderCode='+orderCode+'&category=06'+'&idx='+idx+'&companyCode='+groupId+'&productCode='+productCode+'&productName='+encodeURIComponent(productName)+'&etc='+encodeURIComponent(etc));
           
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#etcManage").dialog('close');

                });
        }
        ,close:function(){
        	$('#etcList').empty();
            $('#etcManage').empty();
        }
    });
*/    
};

function fcDelivery_method(){
	
	if(document.recoveryDetailForm.deliveryRadio[0].checked==true){
		document.recoveryDetailForm.transportCode.disabled=false;
		document.recoveryDetailForm.transportNo.disabled=false;
		document.recoveryDetailForm.quickCharge.disabled=true;
		document.recoveryDetailForm.quickTel.disabled=true;
		document.recoveryDetailForm.quickCharge.value='';
		document.recoveryDetailForm.quickTel.value='';
		document.recoveryDetailForm.deliveryMethod.value='01';
	}else{
		document.recoveryDetailForm.transportCode.disabled=true;
		document.recoveryDetailForm.transportNo.disabled=true;
		document.recoveryDetailForm.transportCode.value='';
		document.recoveryDetailForm.transportNo.value='';
		document.recoveryDetailForm.quickCharge.disabled=false;
		document.recoveryDetailForm.quickTel.disabled=false;
		document.recoveryDetailForm.deliveryMethod.value='02';
	}
}

function fcResult_cal(){

	var frm=document.recoveryDetailListForm;
	var amtCnt = frm.productPrice.length;

	if(amtCnt==undefined){
		amtCnt=1;
	}

	var totalcnt=0;
	var totalresultcnt=0;
	var totalamt=0;
	var totalresultamt=0;
	
	var totalprodcnt=0;

	if(amtCnt>1){
    	for(i=0;i<amtCnt;i++){
    		
    		frm.recoveryCnt[i].value=isnullStr(frm.recoveryCnt[i].value);
    		frm.recoveryResultCnt[i].value=isnullStr(frm.recoveryResultCnt[i].value);
    		
    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice[i].value))));
    		var recoveryCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCnt[i].value))));
    		var recoveryResultCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCnt[i].value))));
    		var sum_supplyAmt=productPrice*recoveryCnt;
    		var sum_supplyresultAmt=productPrice*recoveryResultCnt;

    		totalamt=totalamt+sum_supplyAmt;
    		totalcnt=totalcnt+recoveryCnt;
    		totalresultamt=totalresultamt+sum_supplyresultAmt;
    		totalresultcnt=totalresultcnt+recoveryResultCnt;
    		
    		if(recoveryResultCnt>0){
	    		totalprodcnt++;
    		}

    	}
	}else{

		frm.recoveryCnt.value=isnullStr(frm.recoveryCnt.value);
		frm.recoveryResultCnt.value=isnullStr(frm.recoveryResultCnt.value);
		
		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.productPrice.value))));
		var recoveryCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryCnt.value))));
		var recoveryResultCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCnt.value))));

		var sum_supplyAmt=productPrice*recoveryCnt;
		var sum_supplyresultAmt=productPrice*recoveryResultCnt;

		totalamt=totalamt+sum_supplyAmt;
		totalcnt=totalcnt+recoveryCnt;
		totalresultamt=totalresultamt+sum_supplyresultAmt;
		totalresultcnt=totalresultcnt+recoveryResultCnt;
		
		if(recoveryResultCnt>0){
    		totalprodcnt++;
		}
		
	}
	  document.all('totalProdCnt').innerText=addCommaStr(''+totalprodcnt)+' 건  ';
	  document.all('totalRecoveryCnt').innerText=addCommaStr(''+totalcnt)+' 건  ';
	  document.all('totalRecoveryResultCnt').innerText=addCommaStr(''+totalresultcnt)+' 건  ';
	  document.all('totalRecoveryAmt').innerText=addCommaStr(''+totalamt)+' 원  ';
	  document.all('totalRecoveryResultAmt').innerText=addCommaStr(''+totalresultamt)+' 원  ';
		

}

	function fcRecovery_transpath(url,transno){
		
		var theURL=url+transno;
		
		var h=700;
		var s=800;

	    tmt_winLaunch(theURL, 'transObj', 'transObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
	
	}
	
	function tmt_winLaunch(theURL,winName,targetName,features) {
		
		var targetRandom=Math.random();
		eval(winName+"=window.open('"+theURL+"','"+targetRandom+"','"+features+"')");

	}
    /*
     * 화면 POPUP
     */
    function barcode_winLaunch(theURL,winName,targetName,features) {
    	//alert(winName);
    	//alert('opener');
    	//var targetRandom=Math.random();
    	eval(winName+"=window.open('"+theURL+"','"+targetName+"','"+features+"')");

    }
    var CheckInit=0;
    
	function fcBarCode_recovery(recoveryCode){
    	
    	if (confirm('바코드 스캐너를 통해 회수수량을 자동입력 하시겠습니까?\n자동회수 처리시 스캐너 연동 및 환경이 정상적으로 설정 되어 있어야 합니다.')){ 
    	
    		var recoveryCnt=document.all('totalRecoveryCnt').innerText;
    	
	    	var url='<%= request.getContextPath() %>/recovery/barcoderecovery?recoveryCode='+recoveryCode+'&recoveryCnt='+encodeURIComponent(recoveryCnt);

			var h=510;
			var s=280;

			barcode_winLaunch(url, 'barcodeObj', 'barcodeObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=no,scrollbars=yes');
	
			if(CheckInit==0){ //회수대기상태최초에만 초기화
				var frm=document.recoveryDetailListForm;
				var amtCnt = frm.productCode.length;
				
				if(amtCnt==undefined){
					amtCnt=1;
				}
				
				if(amtCnt > 1){
					
			    	for(i=0;i<amtCnt;i++){
			    		frm.recoveryCnt[i].value=0;
			    	}
			    	
				}else{
					
					frm.recoveryCnt.value=0;
				}
				
				 fcResult_cal(); 	
			}
		
    	}
    };
    
	function fcBarCode_check(recoveryCode){
    	
    	if (confirm('바코드 스캐너를 통해 검수수량을 자동입력 하시겠습니까?\n자동검수 처리시 스캐너 연동 및 환경이 정상적으로 설정 되어 있어야 합니다.')){ 
    	
    		var recoveryResultCnt=document.all('totalRecoveryResultCnt').innerText;
    	
	    	var url='<%= request.getContextPath() %>/recovery/barcodecheck?recoveryCode='+recoveryCode+'&recoveryResultCnt='+encodeURIComponent(recoveryResultCnt);

			var h=510;
			var s=280;

			barcode_winLaunch(url, 'barcodeObj', 'barcodeObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=no,scrollbars=yes');
	
			if(CheckInit==0){ //회수대기상태최초에만 초기화
			
				var frm=document.recoveryDetailListForm;
				var amtCnt = frm.productCode.length;
				
				if(amtCnt==undefined){
					amtCnt=1;
				}
				
				if(amtCnt > 1){
					
			    	for(i=0;i<amtCnt;i++){
			    		frm.recoveryResultCnt[i].value=0;
			    	}
			    	
				}else{
					
					frm.recoveryResultCnt.value=0;
				}
				
				 fcResult_cal(); 	
			}
	
    	}
    };

</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="recoveryVO" id="recoveryDetailForm"  name="recoveryDetailForm" method="post" action="" >
	   <input type="hidden" name="recoveryCode"               id="recoveryCode"            value="${recoveryConVO.recoveryCode}" />
	   <input type="hidden" name="collectCode"               id="collectCode"            value="${recoveryConVO.collectCode}" />
	   <input type="hidden" name="groupId"               id="groupId"            value="${recoveryConVO.groupId}" />
	   <input type="hidden" name="orderCode"               id="orderCode"            value="" />
	   <input type="hidden" name="memo"               id="memo"            value="" />
	   <input type="hidden" name="category"               id="category"            value="05" />
	   <input type="hidden" name="deliveryMethod"               id="deliveryMethod"            value="01" />
	   
	      <tr>
          <div style="position:absolute; left:30px" > 
	       <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span>(${recoveryConVO.groupName}) 회수 대상품목  </font></strong></h4>
          </div >
          <div style="position:absolute; right:30px" > 
          <c:choose>
    		<c:when test="${recoveryConVO.recoveryState=='03' && strAuth!= '03'}">
				<button type="button" id="checkbtn"  name="checkbtn" disabled class="btn btn-primary" onClick="fcRecovery_complete()">검수완료</button>
			</c:when>
			<c:otherwise>
				<c:if test="${recoveryConVO.recoveryState=='01'}"><button type="button" class="btn btn-primary" onClick="fcRecovery_process()">발신</button></c:if>
			</c:otherwise>
		  </c:choose>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >회수번호</th>
          <th class='text-center' colspan="2" ><c:out value="${recoveryConVO.recoveryCode}"></c:out>&nbsp;&nbsp;
          <c:if test="${recoveryConVO.recoveryState=='01'}"> 
          <button id="downbtn" type="button" class="btn btn-xs btn-success" onClick="fcRecovery_print('${recoveryConVO.recoveryCode}')" >인쇄</button>
          </c:if>
          </th>
          <th class='text-center' style="background-color:#E6F3FF">회수요청일</th>
          <th class='text-center'><c:out value="${recoveryConVO.collectDateTime}"></c:out></th>
      	  <th class='text-center' style="background-color:#E6F3FF">회수마감일</th>
          <th class='text-center'><c:out value="${recoveryConVO.recoveryClosingDate}"></c:out></th>	
      	</tr>
      	 <c:choose>
    		<c:when test="${recoveryConVO.recoveryState=='01'}">
				<tr>
		          <th class='text-center' rowspan="2"  style="background-color:#E6F3FF" >운송방법<br>선택</th>
		          <th class='text-left' >
		          <input type="radio" name="deliveryRadio" id="deliveryRadio" value="01" checked onChange="fcDelivery_method()")/>&nbsp;택배
		          </th>
		          <th class='text-center' style="background-color:#E6F3FF">운송회사
		          <!-- >button id="downbtn" type="button" class="btn btn-xs btn-success" onClick="" >직접입력</button --></th>
		          <th class='text-center' colspan="2" >
				  <select class="form-control" title="운송업체" id="transportCode" name="transportCode" value="">
                	<option value="">선택</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		 </select>
		   		  <input type="hidden" id="transport" name="transport" >
		          </th>
		      	  <th class='text-center'  style="background-color:#E6F3FF">운송장번호</th>
		          <th class='text-center'><input type="text" class="form-control" id="transportNo" name="transportNo" maxlength="30"   value="" placeholder="운송장번호"  /></th>	
		      	</tr>
		      	<tr>
		      	  <th class='text-left' >
		          <input type="radio" name="deliveryRadio" id="deliveryRadio" value="02"  onChange="fcDelivery_method()")/>&nbsp;퀵
		          </th>
		      	  <th class='text-center' style="background-color:#E6F3FF">담당자</th>
		          <th class='text-center' colspan="2" >
		          <input type="text" class="form-control" id="quickCharge" name="quickCharge" disabled  maxlength="10"  value="" placeholder="담당자"  />
		          </th>
		      	  <th class='text-center' style="background-color:#E6F3FF">연락처</th>
		          <th class='text-center'>
		          <input type="text" class="form-control" id="quicktel" name="quickTel" disabled value=""  maxlength="14" placeholder="연락처"  />
		          </th>
		      	</tr>
			</c:when>
			<c:otherwise>
				<tr>
		          <th class='text-center' style="background-color:#E6F3FF" >운송방법</th>
		          <c:choose>
    				<c:when test="${recoveryConVO.deliveryMethod=='01'}">
		         	  <th class='text-center' >&nbsp;택배 </th>
		              <th class='text-center' style="background-color:#E6F3FF">운송회사</th>
		              <th class='text-center' colspan="2" >${recoveryConVO.transport}</th>
                      <th class='text-center'  style="background-color:#E6F3FF">운송장번호</th>
		               <c:choose>
    						 <c:when test="${recoveryConVO.transurl!='N'}">
    						  	<th class='text-center'><a href="javascript:fcRecovery_transpath('${recoveryConVO.transurl}','${recoveryConVO.transportNo}');">${recoveryConVO.transportNo}</a></th>
		             	 	 </c:when>
							 <c:otherwise>
							  	<th class='text-center'>${recoveryConVO.transportNo}</th>
							 </c:otherwise>
		 			  </c:choose>
		            </c:when>
			        <c:otherwise>	
			       	  <th class='text-center' >&nbsp;퀵 </th>
		              <th class='text-center' style="background-color:#E6F3FF">담당자</th>
		              <th class='text-center' colspan="2" >${recoveryConVO.quickCharge}</th>
                      <th class='text-center'  style="background-color:#E6F3FF">연락처</th>
		              <th class='text-center'>${recoveryConVO.quickTel}</th>
		            </c:otherwise>
		 		   </c:choose>
		      	</tr>
			</c:otherwise>
		  </c:choose>
      	</sapn>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">메모&nbsp;(<span id="memoCnt" style="color:blue">${recoveryConVO.memoCnt}</span>)
          <button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcMemo_detail('${recoveryConVO.recoveryCode}','${recoveryConVO.memo}','${recoveryConVO.groupId}')" >관리</button></th>
          <th colspan='6' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${recoveryConVO.memo}" placeholder="메모" disabled /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="recoveryListVO" id="recoveryDetailListForm" name="recoveryDetailListForm" method="post" action="" >
       <table style="width:460px" class="table table-bordered tbl_type" >
	     <colgroup>
	      <col width="80px" >
	      <col width="50px" >
	      <col width="80px" >
	      <col width="50px" >
	      <col width="100px">
	      <col width="100px">
	      <col width="100px">
	     </colgroup>
	     <tr>
	     	<td style="background-color:#E6F3FF">회수 건수</td>
	     	<td class='text-right'><span style="color:gray">
	          <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryDetailList.size()}" /> 건  
	          </span></td>
	        <td style="background-color:#E6F3FF">회수 수량</td>
	     	<td class='text-right'><span id="totalRecoveryCnt" style="color:gray"></span></td>  
	     	<td style="background-color:#E6F3FF">회수 합계금액</td>
	     	<td class='text-right'><span id="totalRecoveryAmt" style="color:gray"></span></td>
	     </tr>
	     <tr>
	        <td style="background-color:#E6F3FF">검수 건수</td>
	     	<td class='text-right'><span id="totalProdCnt" style="color:red"></span></td>
	     	<td style="background-color:#E6F3FF">검수 수량</td>
	     	<td class='text-right'><span id="totalRecoveryResultCnt" style="color:red"></span></td>
	     	<td style="background-color:#E6F3FF">검수 합계금액</td>
	     	<td class='text-right'><span id="totalRecoveryResultAmt" style="color:red"></span></td>
	        <c:if test="${recoveryConVO.recoveryState=='01'}"><td>&nbsp;<button type="button" id="barcodebtn" onClick="fcBarCode_recovery('${recoveryConVO.recoveryCode}')" class="btn btn-xs btn-info">바코드 회수</button></td></c:if>
	        <c:if test="${recoveryConVO.recoveryState=='03'  && strAuth!= '03'}"><td>&nbsp;<button type="button" id="barcodebtncheck" class="btn btn-xs btn-info" onClick="fcBarCode_check('${recoveryConVO.recoveryCode}')" >바코드 검수</button></td></c:if>
	     </tr>
     </table>
       <div class="thead">
	   <table cellspacing="0" border="0" summary="발주대상리스트" class="table table-bordered tbl_type" style="table-layout: fixed">
	    <caption>발주대상리스트</caption>
 		<colgroup>
	      <col width="60px" >
	      <col width="75px" >
	      <col width="105px" >
	      <col width="280px">
	      <col width="70px">
	      <col width="70px">
	      <col width="70px">
	      <col width="70px">
	      <col width="*">
	      </colgroup>
	    <thead>
		<tr style="background-color:#E6F3FF">
     	  <c:choose>
    		<c:when test="${recoveryConVO.recoveryState=='03'  && strAuth!= '03'}">
				<th class='text-center' >검수<input type="checkbox"  id="recoveryCheckAll"  name="recoveryCheckAll" onchange="fcRecovery_checkAll()" title="전체선택" /></th>
			</c:when>
			<c:otherwise>
				<th class='text-center' >검수<input type="checkbox"  id="recoveryCheckAll"  name="recoveryCheckAll" onchange="fcRecovery_checkAll()" title="전체선택" disabled /></th>
			</c:otherwise>
		  </c:choose>
		  <th class='text-center'>품목코드</th>
		  <th class='text-center'>바코드</th>
          <th class='text-center'>상품명</th>
          <th class='text-center'>기준단가</th>
          <th class='text-center'>재고수량</th>
          <th class='text-center'>회수수량</th>
          <th class='text-center'>검수수량</th>
          <th class='text-center'>비고</th>
      	</tr>
	    </thead>
	  </table>
	  </div>
	  <div class="tbody">
	    <table cellspacing="0" border="0" summary="발주대상리스트" class="table table-bordered tbl_type" style="table-layout: fixed"> 
	      <caption>발주대상리스트</caption>
	      <colgroup>
	      <col width="60px" >
	      <col width="75px" >
	      <col width="105px" >
	      <col width="280px">
	      <col width="70px">
	      <col width="70px">
	      <col width="70px">
	      <col width="70px">
	      <col width="*">
	      </colgroup>
	       <!-- :: loop :: -->
	                <!--리스트---------------->
	      <tbody>
	        <c:if test="${!empty recoveryDetailList}">
             <c:forEach items="${recoveryDetailList}" var="recoveryVO" varStatus="status">
             	 <input type="hidden" id="seqs" name="seqs" >
	             <tr id="barCodeCheckColor" >
                 <c:choose>
		    		<c:when test="${recoveryConVO.recoveryState=='03'  && strAuth!= '03'}"> 
						<td class='text-center'>${status.count}<br><input type="checkbox" id="recoveryCheck" name="recoveryCheck" value="${recoveryVO.productCode}" title="선택"  onChange="totalCheck()" /></td>
		    		</c:when>
					<c:otherwise>
						<td class='text-center'>${status.count}<br><input type="checkbox" id="recoveryCheck" name="recoveryCheck" value="${recoveryVO.productCode}" title="선택" disabled  /></td>
					</c:otherwise>
				</c:choose>
			  	 <td class='text-center'><c:out value="${recoveryVO.productCode}"></c:out></td>
                 <td class='text-center'><c:out value="${recoveryVO.barCode}"></c:out>
                  <br>
                 <span id="barCodeView" style="color:red"></span></td>
                 <td class='text-left'><c:out value="${recoveryVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.productPrice}" /></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.stockCnt}" /></td>
                 <input type="hidden" id="productCode" name="productCode" value="${recoveryVO.productCode}" >
                 <input type="hidden" id="barCode" name="barCode" value="${recoveryVO.barCode}" >
                 <input type="hidden" id="productPrice" name="productPrice" value="${recoveryVO.productPrice}" >
                 <input type="hidden" id="stockDate" name="stockDate" value="${recoveryVO.stockDate}" >
                 <input type="hidden" id="stockCnt" name="stockCnt" value="${recoveryVO.stockCnt}" >
                 <input type="hidden" id="addCnt" name="addCnt" value="${recoveryVO.addCnt}" >
                 <input type="hidden" id="lossCnt" name="lossCnt" value="${recoveryVO.lossCnt}" >
                 <c:choose>
		    		<c:when test="${recoveryVO.recoveryState!='01'}"> 
					   <td class='text-right' id='recoveryCntView' name='recoveryCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryCnt}"  /></td>
					   <input type="hidden" id="recoveryCnt" name="recoveryCnt" value="${recoveryVO.recoveryCnt}" >
                       </c:when>
					<c:otherwise>
					   <td class='text-center'>
					   <input style="width:55px;" type="text" class="form-control" id="recoveryCnt" name="recoveryCnt"  maxlength="3" numberOnly onKeyup="fcResult_cal()" value="${recoveryVO.recoveryCnt}">
					   </td>
                    </c:otherwise>
				</c:choose>
                 <c:choose>
		    		<c:when test="${recoveryConVO.recoveryState=='03'  && strAuth!= '03'}"> 
					    <td class='text-center'>
					    <input style="width:55px;" class="form-control" type="text" id="recoveryResultCnt" name="recoveryResultCnt" maxlength="3" numberOnly onKeyup="fcResult_cal()" value="${recoveryVO.recoveryResultCnt}">
					    </td>
                    </c:when>
					<c:otherwise>
					    <input type="hidden" id="recoveryResultCnt" name="recoveryResultCnt" value="${recoveryVO.recoveryResultCnt}" >
						<td class='text-right' id='recoveryResultCntView' name='recoveryResultCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultCnt}"  /></td>
					</c:otherwise>
				</c:choose>
				
				
				<c:choose>
		    		<c:when test="${recoveryVO.etcCnt>0}">
						<td class='text-center' id="etcAdd" name="etcAdd" style="background-color:#FEE2B4;color:blue"><c:if test="${recoveryConVO.recoveryState!='01'}"><img id="etcbtn" onClick="fcEtc_detail('${recoveryVO.recoveryCode}','${recoveryVO.productCode}','${recoveryVO.productName}','${recoveryVO.etc}','${status.count}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고">(<span id="etcCnt">${recoveryVO.etcCnt}</span>)</c:if></td>
                    </c:when>
					<c:otherwise>
						<td class='text-center' id="etcAdd" name="etcAdd" ><c:if test="${recoveryConVO.recoveryState!='01'}"><img id="etcbtn" onClick="fcEtc_detail('${recoveryVO.recoveryCode}','${recoveryVO.productCode}','${recoveryVO.productName}','${recoveryVO.etc}','${status.count}','${recoveryVO.groupId}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고">(<span id="etcCnt">${recoveryVO.etcCnt}</span>)</c:if></td>
                	</c:otherwise>
				</c:choose>
				
                  <tr>
                 <c:choose>
		    		<c:when test="${recoveryConVO.recoveryState!='01'}"> 
						 <td colspan='9' class='text-center'><input type="text" class="form-control" id="etc" name="etc"  value="${recoveryVO.etc}" placeholder="비고" disabled /></td>
					</c:when>
					<c:otherwise>
						 <td colspan='9' class='text-center'><input type="text" class="form-control" id="etc" name="etc"  value="${recoveryVO.etc}" placeholder="비고" /></td>
					</c:otherwise>
				</c:choose>
	             </tr>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty recoveryDetailList}">
           <tr>
           	<td colspan='9' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	   </table>
	  </div>
      
	 </form:form>
	</div>
	<script type="text/javascript">
    //alert('${recoveryConVO.receiveCnt}');
    //alert('${recoveryConVO.totalCnt}');
    //totalRecoveryAmt();
    //totalRecoveryResultAmt();
    fcResult_cal();
    totalCheck();
    

</script>