<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<style>

 .thead { height:67px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:640px; .height:650px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
<SCRIPT>
 

function tmt_winLaunch(theURL,winName,targetName,features) {
	
	var targetRandom=Math.random();
	eval(winName+"=window.open('"+theURL+"','"+targetRandom+"','"+features+"')");

}
/*
 * print 화면 POPUP
 */
function fcOrderDetail_print(orderCode){
	
	var frm = document.orderDetailListForm;
	var url="<%= request.getContextPath() %>/order/orderdetailprint?orderCode="+orderCode;

    frm.action =url; 
	frm.method="post";
 	frm.target='printObj';
 	frm.submit();
}

	   function fcDefer_reason(reason){

	    	if(reason==''){
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
	    
	           	if(frm.seqs.length!=undefined){
	           		for(i=0;i<frm.seqs.length;i++){
	   					frm.seqs[i].value=fillSpace(frm.productCode[i].value)+
	           			'|'+fillSpace(frm.barCode[i].value)+'|'+fillSpace(deleteCommaStr(frm.orderResultPrice[i].value))+'|'+fillSpace(frm.orderResultCnt[i].value)+
	           			'|'+fillSpace(frm.orderVatRate[i].value)+'|'+fillSpace(frm.etc[i].value);
	     
	           		}
	           	}else{
	           		
   					frm.seqs.value=fillSpace(frm.productCode.value)+
           			'|'+fillSpace(frm.barCode.value)+'|'+fillSpace(deleteCommaStr(frm.orderResultPrice.value))+'|'+fillSpace(frm.orderResultCnt.value)+
           			'|'+fillSpace(frm.orderVatRate.value)+'|'+fillSpace(frm.etc.value);

	           	}
	
	            if (confirm('검수내용을 보류처리 하시겠습니까?')){ 
	            	
	            document.orderDetailForm.deferReason.value=reason;
	            document.orderDetailForm.deferType.value='R';
	            var paramString = $("#orderDetailForm").serialize()+ "&arrCheckProductId="+arrCheckProductId+'&'+$("#orderDetailListForm").serialize();

			  		$.ajax({
				       type: "POST",
				       async:false,
				          url:  "<%= request.getContextPath() %>/order/orderdeferprocess",
				          data:paramString,
				          success: function(result) {
			
				        	resultMsg(result);
							
				        	$('#deferDialog').dialog('close');
							$('#orderDetailView').dialog('close');
							fcOrder_listSearch();
								
				          },
				          error:function(){
				          
				          alert('보류 처리 호출오류!');
				          $('#deferDialog').dialog('close');
						  $('#orderDetailView').dialog('close');
						  fcOrder_listSearch();
				          }
				    });
            	
	            }
	    	}	
		}
	
    function fcDefer_cancel(reason){
    	
    	if(reason==''){
    		alert('보류폐기 사유를 입력하세요!');
    		return;
    	}

    	 if (confirm('보류내용을 폐기 하시겠습니까?\n폐기 하실 경우 검수대기 상태로 변경 됩니다.\n폐기 사유는 검수대기 상태에서 확인 가능합니다.')){ 
        	 
    		 document.orderDetailForm.deferReason.value=reason;
    		 document.orderDetailForm.deferType.value='D';
        	 var paramString = $("#orderDetailForm").serialize();
        	 
	 		$.ajax({
	       type: "POST",
	       async:false,
	          url:  "<%= request.getContextPath() %>/order/orderdefercancel",
	          data:paramString,
	          success: function(result) {
	
	        	resultMsg(result);
				
	        	$('#deferDialog').dialog('close');
				$('#orderDetailView').dialog('close');
				fcOrder_listSearch();
					
	          },
	          error:function(){
	          
	          alert('보류 처리 호출오류!');
	          $('#deferDialog').dialog('close');
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
    
    function fcOrder_cancel(reason){
    	
    	if(reason==''){
    		alert('발주 취소사유를 입력하세요!');
    		return;
    	}

    	 if (confirm('발주 내용을 취소 하시겠습니까?\n취소하 실 경우 모든 발주내용은 발주대기 상태에 포함됩니다.')){ 
        	 
    	   document.orderDetailForm.deferReason.value=reason;
    		 
           var paramString = $("#orderDetailForm").serialize();
        	 
	 		$.ajax({
	       type: "POST",
	       async:false,
	          url:  "<%= request.getContextPath() %>/order/ordercancel",
	          data:paramString,
	          success: function(result) {
	
	        	resultMsg(result);
	        	$('#deferDialog').dialog('close');
				$('#orderDetailView').dialog('close');
				fcOrder_listSearch();
					
	          },
	          error:function(){
	          
	          alert('취소 처리 호출오류!');
	          $('#deferDialog').dialog('close');
			  $('#orderDetailView').dialog('close');
			  fcOrder_listSearch();
	          }
	    	
	 		});
	 		
    	 } 

    }
    function fcOrder_buy(){
    	
   	 if (confirm('eccount 매입처리를 하셨습니까?\n본 과정은 수정되지 않습니다')){ 
       	 
   		 if (confirm('등록 완료 하시겠습니까?\n처리시 본 매입건은 종료 됩니다.')){ 
   	       	 
          var paramString = $("#orderDetailForm").serialize();
       	 
	 		$.ajax({
	       type: "POST",
	       async:false,
	          url:  "<%= request.getContextPath() %>/order/orderbuy",
	          data:paramString,
	          success: function(result) {
	
	        	resultMsg(result);
				
				$('#orderDetailView').dialog('close');
				fcOrder_listSearch();
					
	          },
	          error:function(){
	          
	          alert('등록 처리 호출오류!');
			  $('#orderDetailView').dialog('close');
			  fcOrder_listSearch();
	          }
	    	
	 		});
   		 }	
   	 } 

   }
function totalTargetAmt(){
    	
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
	    		
	    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderPrice[i].value))));
	    		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCnt[i].value))));
	    		var vatAmt=frm.orderVatRate[i].value;
	    		
	    		var sum_supplyAmt=productPrice*orderCnt;
	    		supplyamt=supplyamt+sum_supplyAmt;
	    		
	    		var sum_vatAmt=Math.floor(+vatAmt)*orderCnt;
	    		vatamt=vatamt+sum_vatAmt;
	    		totalcnt=totalcnt+orderCnt;
	    	}
	    	
    	}else{
    		
    		var productPrice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderPrice.value))));
    		var orderCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderCnt.value))));
    		var vatAmt=frm.orderVatRate.value;
    		var sum_supplyAmt=productPrice*orderCnt;

    		var sum_supplyAmt=productPrice*orderCnt;
    		supplyamt=supplyamt+sum_supplyAmt;
    		
    		var sum_vatAmt=Math.floor(+vatAmt)*orderCnt;
    		vatamt=vatamt+sum_vatAmt;
    		totalcnt=totalcnt+orderCnt;
    	}

    	  totalamt=supplyamt+vatamt;
    	  
    	  document.all('totalTargetCnt').innerText=' '+addCommaStr(''+totalcnt)+' 건';
    	  document.all('totalTargetAmt').innerText=' '+addCommaStr(''+totalamt)+' 원 ';// [공급가] : '+addCommaStr(''+supplyamt)+' 원  [부가세] : '+addCommaStr(''+vatamt)+' 원';
    }
    function totalOrderAmt(){
    	
    	var frm=document.orderDetailListForm;
    	var amtCnt = frm.productCode.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}
    	
    	var supplyamt=0;
    	var vatamt=0;
    	var totalamt=0;
    	var totalcnt=0;
    	var totalprodcnt=0;
    	
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
	    		
	    		//if(orderCnt>0){
		    		totalprodcnt++;
	    		//}

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
    		
    		//if(orderCnt>0){
	    		totalprodcnt++;
    		//}

    		document.all('orderTotalPriceView').innerText=addCommaStr(''+(productPrice*orderCnt));
    	}

    	  totalamt=supplyamt+vatamt;
    	
    	  document.all('totalProdCnt').innerText=' '+addCommaStr(''+totalprodcnt)+' 건';
    	  document.all('totalOrderCnt').innerText=' '+addCommaStr(''+totalcnt)+' 건';
    	  document.all('totalOrderAmt').innerText=' '+addCommaStr(''+totalamt)+' 원';//  [공급가] : '+addCommaStr(''+supplyamt)+' 원  [부가세] : '+addCommaStr(''+vatamt)+' 원';
    }
    
    function totalCheck(){
    	
    	var frm=document.orderDetailListForm;
    	var amtCnt = frm.productCode.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}
    	
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
    		frm.orderCheckAll.checked=true;
    		document.all('checkbtn').disabled=false;
    		
    	}else{
    		frm.orderCheckAll.checked=false;
    		document.all('checkbtn').disabled=true;
    	}

    }
   
	//체크박스 전체선택
    function fcOrder_checkAll(){
		
    	$("input:checkbox[id='orderCheck']").prop("checked", $("#orderCheckAll").is(":checked"));
    	totalCheck();
    }
	
 // 보류 상세 페이지 리스트 Layup
    function fcMemo_detail(orderCode,memo,companyCode) {
    	
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
                $(this).load(url+'?orderCode='+orderCode+'&category=03'+'&companyCode='+companyCode+'&memo='+encodeURIComponent(memo));
               
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
    function  fcEtc_detail(orderCode,productCode,productName,etc,idx,companyCode) {

    	//$('#targetEtcView').attr('title',productName);
    	var url='<%= request.getContextPath() %>/order/etcmanage';
  
    	if(document.orderDetailListForm.seqs.length==undefined){
    		idx=0;
    	}

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
                $(this).load(url+'?orderCode='+orderCode+'&category=04'+'&idx='+idx+'&productCode='+productCode+'&companyCode='+companyCode+'&productName='+encodeURIComponent(productName)+'&etc='+encodeURIComponent(etc));
               
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
 function fcOrder_complete(transDate){
	 
	 if(transDate==''){	 
		 alert('거래 명세일자 를 선택 하시기 바랍니다.');
		 return;
	 }else{
		 document.orderDetailForm.transDate.value=transDate;
		 $("#completeDialog").dialog('close');
	 }

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
  
         	if(frm.seqs.length!=undefined){
         		for(i=0;i<frm.seqs.length;i++){
 					frm.seqs[i].value=fillSpace(frm.productCode[i].value)+
         			'|'+fillSpace(frm.barCode[i].value)+'|'+fillSpace(deleteCommaStr(frm.orderResultPrice[i].value))+'|'+fillSpace(frm.orderResultCnt[i].value)+
         			'|'+fillSpace(frm.orderVatRate[i].value)+'|'+fillSpace(frm.etc[i].value);
   
         		}
         	}else{
         		
					frm.seqs.value=fillSpace(frm.productCode.value)+
     			'|'+fillSpace(frm.barCode.value)+'|'+fillSpace(deleteCommaStr(frm.orderResultPrice.value))+'|'+fillSpace(frm.orderResultCnt.value)+
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
    function fcOrder_reMail(reason){
    	
    	if(reason==''){
    		alert('재송부사유를 입력하세요!');
    		return;
    	}

	            if (confirm('발주서를 재송부 하시겠습니까?')){ 
	            
	            document.orderDetailForm.deferReason.value=reason;	
	            var paramString = $("#orderDetailForm").serialize();

			  		$.ajax({
				       type: "POST",
				       async:false,
				          url:  "<%= request.getContextPath() %>/order/orderremail",
				          data:paramString,
				          success: function(result) {
			
				        	resultMsg(result);
				        	 $('#deferDialog').dialog('close');
	
				          },
				          error:function(){
				          
				          alert('재송부 처리 호출오류!');
				          $('#deferDialog').dialog('close');
				       
				          }
				    });
          	
	            }

    }
    function goOrderExcel(){

    	
    	  var frm = document.orderDetailListForm;
    	  var ofrm = document.orderDetailForm;
  
    	  frm.excelTransDate.value = ofrm.transDate.value;
    	  
         	if(frm.seqs.length!=undefined){
         		for(i=0;i<frm.seqs.length;i++){
 					frm.seqs[i].value=fillSpace(frm.productCode[i].value)+
         			'|'+fillSpace(frm.barCode[i].value)+'|'+fillSpace(frm.orderResultPrice[i].value)+'|'+fillSpace(frm.orderResultCnt[i].value)+
         			'|'+fillSpace(frm.orderVatRate[i].value)+'|'+fillSpace(frm.etc[i].value)+'|'+fillSpace(ofrm.companyCode.value)+'|'+fillSpace(ofrm.groupId.value);
   
         		}
         	}else{
         		
					frm.seqs.value=fillSpace(frm.productCode.value)+
     			'|'+fillSpace(frm.barCode.value)+'|'+fillSpace(frm.orderResultPrice.value)+'|'+fillSpace(frm.orderResultCnt.value)+
     			'|'+fillSpace(frm.orderVatRate.value)+'|'+fillSpace(frm.etc.value)+'|'+fillSpace(ofrm.companyCode.value)+'|'+fillSpace(ofrm.groupId.value);

         	}

        	frm.action = "<%=request.getContextPath()%>/order/orderexcellist";	
        	frm.method = "POST";
        	frm.submit();
        	
        	
        	/*
          var paramString = $("#orderDetailForm").serialize()+ '&'+$("#orderDetailListForm").serialize();

		  		$.ajax({
			       type: "POST",
			       async:false,
			          url:  "<%= request.getContextPath() %>/order/orderexcellist",
			          data:paramString,
			          success: function(result) {
		
			        	resultMsg(result);
						
							
			          },
			          error:function(){
			          
			          alert('excel 처리 호출오류!');
			        
			          }
			    });
      	*/
    }
    function fcDefer_reasonpop(deferType){
    	//$('#targetEtcView').attr('title',productName);
    	var url='<%= request.getContextPath() %>/order/deferreason?deferType='+deferType;

    	$('#deferDialog').dialog({
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
                    $("#deferDialog").dialog('close');

                    });
            }
            ,close:function(){
            	$('#deferDialog').empty();
            }
        });
    };
    function fcOrder_complete_input(){
    	//$('#targetEtcView').attr('title',productName);
    	var url='<%= request.getContextPath() %>/order/ordercompleteinput';

    	$('#completeDialog').dialog({
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
                    $("#completeDialog").dialog('close');

                    });
            }
            ,close:function(){
            	$('#completeDialog').empty();
            }
        });
    };
    
    /*
     * 화면 POPUP
     */
    function tmt_winLaunch(theURL,winName,targetName,features) {
    	
    	//var targetRandom=Math.random();
    	eval(winName+"=window.open('"+theURL+"','"+targetName+"','"+features+"')");

    }
    function fcBarCode_check(orderstate){
    	
    	if (confirm('바코드 스캐너를 통해 검수수량을 자동입력 하시겠습니까?\n자동검수 처리시 스캐너 연동 및 환경이 정상적으로 설정 되어 있어야 합니다.')){ 
    	
    		var orderCnt=document.all('totalTargetCnt').innerText;
	    	var url='<%= request.getContextPath() %>/order/barcodecheck?orderCnt='+encodeURIComponent(orderCnt);

			var h=510;
			var s=280;

		    tmt_winLaunch(url, 'barcodeObj', 'barcodeObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=no,scrollbars=yes');
	
		    /*
	    	$('#barCodeDialog').dialog({
	            resizable : false, //사이즈 변경 불가능
	            draggable : true, //드래그 불가능
	            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
	
	            width : 300,
	            height : 490,
	            modal : true, //주위를 어둡게
	
	            open:function(){
	                //팝업 가져올 url
	              //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
	                $(this).load(url);
	               
	                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                    $("#barCodeDialog").dialog('close');
	
	                    });
	            }
	            ,close:function(){
	            	$('#barCodeDialog').empty();
	            }
	        });
	    	*/
	    	if(orderstate=='03'){
		    	var frm=document.orderDetailListForm;
				var amtCnt = frm.productCode.length;
				
				if(amtCnt==undefined){
					amtCnt=1;
				}
				
				if(amtCnt > 1){
					
			    	for(i=0;i<amtCnt;i++){
			    		frm.orderResultCnt[i].value=0;
			    	}
			    	
				}else{
					
					frm.orderResultCnt.value=0;
				}
				
				totalOrderAmt(); 	
	    	}
	
    	}
    };
    
    function EnterKey(e)
    { 
      if(e.keyCode == 13)    // KeyCode  가 아니고 keyCode 입니다 .. 소문자 k
      {
    	  try{
    		 // alert(barcodeObj);
        	  if(barcodeObj==undefined){
          		
          	  }else{
          		alert('[바코드 인식 실패!!]\n바코드 검수 상태에서는 바코드 검수창에 커서키가 놓여있어야 \n바코드 정상 인식 가능합니다.\n확인 후 다시한번 바코드 인식 부탁드립니다.');
          		barcodeObj.document.barCodeForm.barcode_list.focus(1);
          		//return;
          	  }
    		  
    	  }catch(e){
    		 return;
    	  }
    	 
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
	    <input type="hidden" name="groupName"               id="groupName"            value="${orderVO.groupName}" />
	   <input type="hidden" name="companyCode"               id="companyCode"            value="${orderVO.companyCode}" />
	   <input type="hidden" name="orderCode"               id="orderCode"            value="${orderVO.orderCode}" />
	   <input type="hidden" name="email"               id="email"            value="${orderVO.email}" />
	   <input type="hidden" name="telNumber"               id="telNumber"            value="${orderVO.telNumber}" />
	   <input type="hidden" name="mobilePhone"               id="mobilePhone"            value="${orderVO.mobilePhone}" />
	   <input type="hidden" name="faxNumber"               id="faxNumber"            value="${orderVO.faxNumber}" />
	   <input type="hidden" name="orderDate"               id="orderDate"            value="${orderVO.orderDate}" />
	   <input type="hidden" name="orderEmail"               id="orderEmail"            value="${orderVO.orderEmail}" />
	   <input type="hidden" name="orderTelNumber"               id="orderTelNumber"            value="${orderVO.orderTelNumber}" />
	   <input type="hidden" name="orderFaxNumber"               id="orderFaxNumber"            value="${orderVO.orderFaxNumber}" />
	   <input type="hidden" name="orderMobilePhone"               id="orderMobilePhone"            value="${orderVO.orderMobilePhone}" />
	   <input type="hidden" name="orderCharge"               id="orderCharge"            value="${orderVO.orderCharge}" />
	   <input type="hidden" name="deliveryCharge"               id="deliveryCharge"            value="${orderVO.deliveryCharge}" />
	   <input type="hidden" name="orderName"               id="orderName"            value="${orderVO.orderName}" />
	   <input type="hidden" name="deliveryDate"               id="deliveryDate"            value="${orderVO.deliveryDate}" />
	   <input type="hidden" name="transDate"               id="transDate"            value="${orderVO.transDate}" />
	      <div style="position:absolute; left:30px" >
	      <c:if test="${orderVO.orderState=='03' || orderVO.orderState=='06'}"><button id="deferbtn" type="button" class="btn btn-primary" onClick="fcDefer_reasonpop('R')" >보류</button></c:if>
	      <!--  >button id="defermodifybtn"  type="button" class="btn btn-primary">보류수정</button-->
	      <c:if test="${orderVO.orderState=='04'}"><button id="defercancelbtn"  type="button" class="btn btn-danger" onClick="fcDefer_reasonpop('D')" >보류폐기</button></c:if>
	      <c:if test="${orderVO.orderState=='03' || orderVO.orderState=='04'}"><button type="button" class="btn btn-info" onClick="fcDefer_list('${orderVO.orderCode}')">보류사유</button></c:if>
	      <c:if test="${orderVO.orderState=='03' || orderVO.orderState=='04'}"><button type="button" id="checkbtn"  name="checkbtn" disabled class="btn btn-primary" onClick="fcOrder_complete_input()">검수완료</button></c:if>
	      <c:if test="${(orderVO.orderState=='04' || orderVO.orderState=='06' ) && (strAuth!= '03' || strAuthId=='AD001')}"><button type="button" class="btn btn-default" onClick="goOrderExcel()">엑셀변환(구매입력)</button></c:if>
	      <c:if test="${orderVO.orderState=='03' || orderVO.orderState=='04'}"><button type="button" class="btn btn-success" onClick="fcOrderDetail_print('${orderVO.orderCode}')">인쇄</button></c:if>
          </div>
          <div style="position:absolute; right:30px" >
          <c:if test="${orderVO.orderState!='04' && orderVO.orderState!='06' && orderVO.orderState!='07'}"><button type="button" class="btn btn-warning" onClick="fcDefer_reasonpop('C')">취소</button></c:if>
          <c:if test="${orderVO.orderState!='04' && orderVO.orderState!='06' && orderVO.orderState!='07'}"><button type="button" class="btn btn-primary" onClick="fcDefer_reasonpop('S')">재송부</button></c:if>
          <c:if test="${orderVO.orderState=='06' && (strAuth!= '03' || strAuthId=='AD001')}"><button type="button" class="btn btn-primary" onClick="fcOrder_buy()">등록완료</button></c:if>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th rowspan='9' class='text-center' style="background-color:#E6F3FF">수신</th>
          <th class='text-center'  style="background-color:#E6F3FF" >수신</th>
          <th class='text-center'><input disabled type="text" class="form-control"  value="${orderVO.deliveryName}" placeholder="수신" /></th>
          <th rowspan='9' class='text-center'  style="background-color:#E6F3FF">발신</th>
          <th class='text-center' style="background-color:#E6F3FF">발신</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.orderName}" placeholder="발신"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >담당자</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.deliveryCharge}" placeholder="참조" /></th>
          <th class='text-center' style="background-color:#E6F3FF" >담당자</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.orderCharge}" placeholder="참조" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.mobilePhone}"  placeholder="핸드폰"/></th>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.orderMobilePhone}"  placeholder="핸드폰"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input  disabled type="text" class="form-control" value="${orderVO.email}" placeholder="e-mail" /></th>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.orderEmail}" placeholder="e-mail" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.telNumber}" placeholder="tel" /></th>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.orderTelNumber}" placeholder="tel" /></th>
      	</tr>
      	<th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.faxNumber}" placeholder="fax" /></th>
          <th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.orderFaxNumber}" placeholder="fax" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">발주일자</th>
          <th class='text-center'>
          <input  disabled type="text" class="form-control"   value="${orderVO.orderDate}" placeholder="SMS" />
          </th>
          <th rowspan='2' class='text-center' style="background-color:#E6F3FF">배송주소</th>
          <th rowspan='2' class='text-center'><textarea disabled style='height:82px'  class="form-control" row="2" id="orderAddress" name="orderAddress" >${orderVO.orderAddress}</textarea></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품일자</th>
          <th class='text-center'>
          <input  disabled type="text" class="form-control"  value="${orderVO.deliveryDate}" placeholder="" />
          </th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품방법</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="deliveryMethod" name="deliveryMethod"  value="${orderVO.deliveryMethod}" placeholder="납품방버" /></th>
          <th class='text-center' style="background-color:#E6F3FF">결제방법</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="payMethod" name="payMethod"  value="${orderVO.payMethod}" placeholder="결재방법" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">SMS내용</th>
          <th colspan='4' class='text-center'><input  disabled type="text" class="form-control" id="sms" name="sms"  value="${orderVO.sms}" placeholder="SMS" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">메모&nbsp;(<span id="memoCnt" style="color:blue">${orderVO.memoCnt}</span>)
          <button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcMemo_detail('${orderVO.orderCode}','${orderVO.memo}','${orderVO.companyCode}')" >관리</button></th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${orderVO.memo}" placeholder="메모" disabled /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="orderListVO" id="orderDetailListForm" name="orderDetailListForm" method="post" action="" >
     <!-- <input type="hidden" name="orderCode" value="${orderVO.orderCode}" >
     <input type="hidden" name="companyCode" value="${orderVO.companyCode}" > -->
     <input type="hidden" name="excelTransDate" value="" >
     <table style="width:460px" class="table table-bordered tbl_type" >
	     <colgroup>
	      <col width="80px" >
	      <col width="70px" >
	      <col width="80px" >
	      <col width="70px">
	      <col width="100px">
	      <col width="100px">
	      <col width="100px">
	     </colgroup>
	     <tr>
	     	<td style="background-color:#E6F3FF">발주 건수</td>
	     	<td class='text-right'><span style="color:gray">
	          <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderDetailList.size()}" /> 건  
	          </span></td>
	     	<td style="background-color:#E6F3FF">발주 수량</td>
	     	<td class='text-right'><span id="totalTargetCnt" style="color:gray"></span></td>
	     	<td style="background-color:#E6F3FF">발주 합계금액</td>
	     	<td class='text-right'><span id="totalTargetAmt" style="color:gray"></span></td>
	     </tr>
	     <tr>
	        <td style="background-color:#E6F3FF">검수 건수</td>
	     	<td class='text-right'><span id="totalProdCnt" style="color:red"></span></td>
	     	<td style="background-color:#E6F3FF">검수 수량</td>
	     	<td class='text-right'><span id="totalOrderCnt" style="color:red"></span></td>
	     	<td style="background-color:#E6F3FF">검수 합계금액</td>
	     	<td class='text-right'><span id="totalOrderAmt" style="color:red"></span></td>	
	     	<c:if test="${orderVO.orderState=='03' || orderVO.orderState=='04'}"><td>&nbsp;<button type="button" class="btn btn-xs btn-info" onClick="fcBarCode_check('${orderVO.orderState}')" >바코드 검수</button></td></c:if>
	     </tr>
     </table>
       <div class="thead">
	   <table cellspacing="0" border="0" summary="발주대상리스트" class="table table-bordered tbl_type" style="table-layout: fixed">
	    <caption>발주대상리스트</caption>
 		<colgroup>
	      <col width="50px" >
	      <col width="80px" >
	      <col width="110px" >
	      <col width="230px">
	      <col width="50px">
	      <col width="65px">
	      <col width="70px">
	      <col width="90px">
	      <col width="70px">
	      <col width="*">
	      </colgroup>
	    <thead>
			<tr style="background-color:#E6F3FF">
	          <th rowspan='2' class='text-center' >검수<br>
	          <c:if test="${orderVO.orderState!='06' && orderVO.orderState!='07'}">
	          <input type="checkbox"  id="orderCheckAll"  name="orderCheckAll" onchange="fcOrder_checkAll();" title="전체선택" />
	          </c:if>
	          </th>
	          <th rowspan='2' class='text-center'>품목코드</th>
	          <th rowspan='2' class='text-center'>바코드</th>
	          <th rowspan='2' class='text-center'>상품명</th>
	          <th colspan='2' class='text-center'>수량</th>
	          <th colspan='3' class='text-center'>금액(VAT포함)</th>
	          <th rowspan='2' class='text-center'>비고</th>
	      	</tr>
	      	<tr style="background-color:#E6F3FF">
	          <th style="width:50px" class='text-center'>발주</th>
	          <th class='text-center'>구매</th>
	          <th class='text-center'>기준</th>
	          <th class='text-center'>구매</th>
	          <th class='text-center'>합계</th>
	      	</tr>
	    </thead>
	  </table>
	  </div>
	  <div class="tbody">
	    <table cellspacing="0" border="0" summary="발주대상리스트" class="table table-bordered tbl_type" style="table-layout: fixed"> 
	      <caption>발주대상리스트</caption>
	      <colgroup>
	      <col width="50px" >
	      <col width="80px" >
	      <col width="110px" >
	      <col width="230px">
	      <col width="50px">
	      <col width="65px">
	      <col width="70px">
	      <col width="90px">
	      <col width="70px">
	      <col width="*">
	      </colgroup>
	       <!-- :: loop :: -->
	                <!--리스트---------------->
	      <tbody>
	        <c:if test="${!empty orderDetailList}">
             <c:forEach items="${orderDetailList}" var="orderVO" varStatus="status">
             	 <input type="hidden" id="seqs" name="seqs" >
             	  <c:choose>
	                <c:when test="${orderVO.priceOrder==0 || orderVO.cntOrder==0  }">
						<tr id="barCodeCheckColor" style="background-color:#FEE2B4;color:red" >
					</c:when>
					<c:otherwise>
						<tr id="barCodeCheckColor" >
					</c:otherwise>
				</c:choose>
				 <input type="hidden" name="productCode" value="${orderVO.productCode}">
				 <input type="hidden" name="barCode" value="${orderVO.barCode}">
				 <input type="hidden" name="productName" value="${orderVO.productName}">
				 <input type="hidden" name="stockDate" value="${orderVO.stockDate}">
				 <c:choose>
		    		<c:when test="${orderVO.orderState!='06' && orderVO.orderState!='07'}">
	                 <c:choose>
			    		<c:when test="${orderVO.orderCheck=='Y'}">
							<td class='text-center'>${status.count}<br><input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" checked onChange="totalCheck()" /></td>
						</c:when>
						<c:otherwise>
							<td class='text-center'>${status.count}<br><input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" onChange="totalCheck()" /></td>
						</c:otherwise>
					</c:choose>
					</c:when>
					<c:otherwise>
						<td class='text-center'>${status.count}<br><input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" checked disabled  /></td>
					</c:otherwise>
				</c:choose>
                 <td class='text-center'><c:out value="${orderVO.productCode}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.barCode}"></c:out>
                 <br>
                 <span id="barCodeView" style="color:red"></span></td>
                 <td class='text-left'><c:out value="${orderVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.orderCnt}"/></td>
                 <input type="hidden" name="orderCnt" value="${orderVO.orderCnt}">
                 <td class='text-right'><input type="text" class="form-control" id="orderResultCnt" name="orderResultCnt" maxlength="2" numberOnly  onKeyup="totalOrderAmt()" value="${orderVO.orderResultCnt}"></td>
                 
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.orderPrice+orderVO.orderVatRate}" /></td>
                 <input type="hidden" name="orderPrice" value="${orderVO.orderPrice+orderVO.orderVatRate}">
                 <input style="width:80px" type="hidden" class="form-control" id="orderVatRate" name="orderVatRate" onKeyup="totalOrderAmt()" value="0">
                 
                <c:choose>
		    		<c:when test="${orderVO.orderCnt=='0'}">
                		 <td class='text-right'><input  type="text" class="form-control" id="orderResultPrice" maxlength="9" numberOnly name="orderResultPrice" onKeyup="totalOrderAmt()" value="0"></td>
                	</c:when>
					<c:otherwise>
					     <td class='text-right'><input  type="text" class="form-control" id="orderResultPrice" maxlength="9" numberOnly name="orderResultPrice" onKeyup="totalOrderAmt()" value="${orderVO.orderResultPriceView}"></td>
                   </c:otherwise>
				</c:choose>
                 <td class='text-right' id='orderTotalPriceView' name='orderTotalPriceView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="0"/></td>
                 <c:choose>
		    		<c:when test="${orderVO.etcCnt>0}">
						<td class='text-right' id="etcAdd" name="etcAdd" style="background-color:#FEE2B4;color:blue"><img id="etcbtn" onClick="fcEtc_detail('${orderVO.orderCode}','${orderVO.productCode}','${orderVO.productName}','${orderVO.etc}','${status.count}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고">(<span id="etcCnt">${orderVO.etcCnt}</span>)</td>
					</c:when>
					<c:otherwise>
						<td class='text-right' id="etcAdd" name="etcAdd"><img id="etcbtn" onClick="fcEtc_detail('${orderVO.orderCode}','${orderVO.productCode}','${orderVO.productName}','${orderVO.etc}','${status.count}','${orderVO.companyCode}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고">(<span id="etcCnt">${orderVO.etcCnt}</span>)</td>
					</c:otherwise>
				</c:choose>
                 
                  <tr>
	             	<td colspan='10' class='text-center'><input type="text" class="form-control" id="etc" name="etc"  value="${orderVO.etc}" placeholder="비고" disabled /></td>
	             </tr>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty orderDetailList}">
           <tr>
           	<td colspan='10' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	   </table>
	  </div>
      
	 </form:form>
	</div>
    <!-- //보류 상세화면 -->

    <!-- //보류 상세화면 -->
	<script type="text/javascript">

    totalTargetAmt();
    totalOrderAmt();
    totalCheck();
    
</script>