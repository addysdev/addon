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
   			'|'+fillSpace(frm.holdStock[i].value)+'|'+fillSpace(frm.stockCnt[i].value)+'|'+fillSpace(frm.stockDate[i].value)+'|'+fillSpace(frm.vatRate[i].value)+'|'+fillSpace(frm.etc[i].value);

   		}
   	}else{
   		
			frm.seqs.value=fillSpace(frm.productCode.value)+
			'|'+fillSpace(frm.productName.value)+'|'+fillSpace(frm.productPrice.value)+'|'+fillSpace(frm.orderCnt.value)+
			'|'+fillSpace(frm.addCnt.value)+'|'+fillSpace(frm.lossCnt.value)+'|'+fillSpace(frm.safeStock.value)+
			'|'+fillSpace(frm.holdStock.value)+'|'+fillSpace(frm.stockCnt.value)+'|'+fillSpace(frm.stockDate.value)+'|'+fillSpace(frm.vatRate.value)+'|'+fillSpace(frm.etc.value);


   	}
	
	
    $.ajax({
        type: "POST",
        async:false,
           url:  "<%= request.getContextPath() %>/order/orderprocess",
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
	
    	if($("#defer_modify_reason_div").val()==''){
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
           			'|'+fillSpace(frm.holdStock[i].value)+'|'+fillSpace(frm.stockCnt[i].value)+'|'+fillSpace(frm.stockDate[i].value)+'|'+fillSpace(frm.vatRate[i].value)+'|'+fillSpace(frm.etc[i].value);
     
           		}
           	}else{
           		
   				frm.seqs.value=fillSpace(frm.productCode.value)+
       			'|'+fillSpace(frm.productName.value)+'|'+fillSpace(frm.productPrice.value)+'|'+fillSpace(frm.orderCnt.value)+
       			'|'+fillSpace(frm.addCnt.value)+'|'+fillSpace(frm.lossCnt.value)+'|'+fillSpace(frm.safeStock.value)+
       			'|'+fillSpace(frm.holdStock.value)+'|'+fillSpace(frm.stockCnt.value)+'|'+fillSpace(frm.stockDate.value)+'|'+fillSpace(frm.vatRate.value)+'|'+fillSpace(frm.etc.value);


           	}

            if (confirm('보류내용을 수정 하시겠습니까?')){ 
            	
                document.deferDetailForm.deferReason.value=$("#defer_modify_reason_div").val();
                document.deferDetailForm.deferType.value='M';
                
                var paramString = $("#deferDetailForm").serialize()+ "&arrDeferProductId="+arrDeferProductId+'&'+$("#deferDetailListForm").serialize();
     	
		  		$.ajax({
			       type: "POST",
			       async:false,
			          url:  "<%= request.getContextPath() %>/order/deferprocess",
			          data:paramString,
			          success: function(result) {
		
			        	resultMsg(result);
	
						$('#defermodifydialog').dialog('close');
						$('#targetDetailView').dialog('close');
						fcTarget_listSearch();
							
			          },
			          error:function(){
	
			          alert('호출오류!');
					  $('#targetDetailView').dialog('close');
				     
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
   		 
   		 document.deferDetailForm.deferReason.value=$("#defer_cancel_reason_div").val();
   		 document.deferDetailForm.deferType.value='M';
		 var paramString = $("#deferDetailForm").serialize();

	 		$.ajax({
	       type: "POST",
	       async:false,
	          url:  "<%= request.getContextPath() %>/order/defercancel",
	          data:paramString,
	          success: function(result) {
	
	        	resultMsg(result);
				
	        	$('#defercanceldialog').dialog('close');
				$('#targetDetailView').dialog('close');
				fcTarget_listSearch();
					
	          },
	          error:function(){
	          
	          alert('보류 처리 호출오류!');
	          $('#defercanceldialog').dialog('close');
			  $('#targetDetailView').dialog('close');
			  fcTarget_listSearch();
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
                $(this).load(url+'?orderCode='+orderCode+'&category=01');
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#deferReasonList").dialog('close');

                    });
            }
            ,close:function(){
                $('#deferReasonList').empty();
            }
        });
    };
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
	 <form:form commandName="recoveryConVO" id="recoveryDetailForm"  name="recoveryDetailForm" method="post" action="" >
	   <input type="hidden" name="recoverCode"               id="recoveryCode"            value="${recoveryConVO.recoveryCode}" />
	   <input type="hidden" name="groupId"               id="groupId"            value="${recoveryConVO.groupId}" />
	      <tr>
          <div style="position:absolute; right:30px" > 
          <button type="button" class="btn btn-primary" onClick="fcOrder_process()">회수</button>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >회수번호</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryCharge" name="deliveryCharge"  value="${recoveryConVO.recoveryCode}" placeholder="" /></th>
          <th class='text-center' style="background-color:#E6F3FF">회수마감일</th>
          <th class='text-center'><input type="text" class="form-control" id="orderCharge" name="orderCharge"  value="${recoveryConVO.recoveryClosingDate}" placeholder=""/></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="recoveryListVO" id="recoveryDetailListForm" name="recoveryDetailListForm" method="post" action="" >
      <p> <span class="glyphicon glyphicon-asterisk"></span> 
          <span id="totalRecoveryAmt" style="color:red">
        </span>
      </p>       
	  <table class="table table-bordered" >
      	<tr style="background-color:#E6F3FF">
          <th class='text-center' >회수<br><input type="checkbox"  id="recoveryCheckAll"  name="recoveryCheckAll" onchange="fcDefer_checkAll();" title="전체선택" /></th>
          <th class='text-center'>상품명</th>
          <th class='text-center'>기준단가</th>
          <th class='text-center'>재고현황</th>
          <th class='text-center'>회수수량</th>
          <th class='text-center'>+</th>
          <th class='text-center'>-</th>
          <th class='text-center'>비고</th>
      	</tr>
	    	<c:if test="${!empty recoveryDetailList}">
             <c:forEach items="${recoveryDetailList}" var="recoveryVO" varStatus="status">
             	 <input type="hidden" id="seqs" name="seqs" >
	             <tr id="select_tr_${recoveryVO.productCode}">
                 <c:choose>
		    		<c:when test="${recoveryVO.recoveryState=='01'}">
						<td class='text-center'><input type="checkbox" id="recoveryCheck" name="recoveryCheck" value="${recoveryVO.productCode}" title="선택" disabled/></td>
					</c:when>
					<c:otherwise>
						<td class='text-center'><input type="checkbox" id="recoveryCheck" name="recoveryCheck" value="${recoveryVO.productCode}" title="선택" /></td>
					</c:otherwise>
				</c:choose>
                 <td class='text-left'><c:out value="${recoveryVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.productPrice}" /></td>
                 <input type="hidden" id="productPrice" name="productPrice" value="${recoveryVO.productPrice}" >
                 <td class='text-right' id='recoveryCntView' name='recoveryCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.stockCnt}"/></td>
                 <td class='text-right' id='recoveryCntView' name='recoveryCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultCnt}"/></td>
                 <td class='text-right'><input style="width:35px" type="text" class="form-control" id="addCnt" name="addCnt" onKeyup="fcAdd_Cnt('${status.count}')" value="${recoveryVO.addCnt}"></td>
                 <td class='text-right'><input style="width:35px" type="text" class="form-control" id="lossCnt" name="lossCnt" onKeyup="fcLoss_Cnt('${status.count}')" value="${recoveryVO.lossCnt}"></td>
                 <td class='text-center'><img id="etcbtn" onClick="fcEtc_detail('${recoveryVO.recoveryCode}','${recoveryVO.productCode}','${recoveryVO.productName}','${recoveryVO.recoveryMemo}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고"></td>
                 <tr>
	             	<td colspan='8' class='text-center'><input type="text" class="form-control" id="etc" name="etc"  value="${recoveryVO.recoveryMemo}" placeholder="비고" /></td>
	             </tr>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty recoveryDetailList}">
           <tr>
           	<td colspan='8' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	  </table>
	 </form:form>
	</div>
	<script type="text/javascript">

   // totalOrderAmt();
</script>