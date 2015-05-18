<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
 
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
    
    function totalRecoveryAmt(){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;

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

    	  document.all('totalRecoveryAmt').innerText='[합계] : '+addCommaStr(''+totalamt)+' 원  ';
    }
    
    function fcAdd_Cnt(index){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	if(amtCnt > 1){
    		
    		frm.lossCnt[index-1].value=0;
    	    
    		var recoveryResultCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCntRaw[index-1].value))));
			var addCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.addCnt[index-1].value))));

			var recoveryResultCnt=(recoveryResultCntRaw+addCnt);
	
			frm.recoveryResultCnt[index-1].value=recoveryResultCnt;
			document.all('recoveryResultCntView')[index-1].innerText=recoveryResultCnt;
			

    	}else{
    		
			frm.lossCnt.value=0;
    	    
			var recoveryResultCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCntRaw.value))));
			var addCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.addCnt.value))));

			var recoveryResultCnt=(recoveryResultCntRaw+addCnt);
	
			frm.recoveryResultCnt.value=recoveryResultCnt;
			document.all('recoveryResultCntView').innerText=recoveryResultCnt;
			
    	}

    	totalRecoveryAmt();
    }
 
	function fcLoss_Cnt(index){
    	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productPrice.length;
    	
    	
    	if(amtCnt > 1){
    		
    		frm.addCnt[index-1].value=0;
    	    
    		var recoveryResultCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCntRaw[index-1].value))));
			var lossCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.lossCnt[index-1].value))));

			var recoveryResultCnt=(recoveryResultCntRaw-lossCnt);
	
			frm.recoveryResultCnt[index-1].value=recoveryResultCnt;
			document.all('recoveryResultCntView')[index-1].innerText=recoveryResultCnt;

    	}else{
    		
			frm.addCnt.value=0;
    	    
    		var recoveryResultCntRaw=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.recoveryResultCntRaw.value))));
			var lossCnt=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.lossCnt.value))));

			var recoveryResultCnt=(recoveryResultCntRaw-lossCnt);
	
			frm.recoveryResultCnt.value=recoveryResultCnt;
			document.all('recoveryResultCntView').innerText=recoveryResultCnt;
			
    	}
		
    	totalRecoveryAmt();
    }
    
	//체크박스 전체선택
    function fcRecovery_checkAll(){
		
    	$("input:checkbox[id='recoveryCheck']").prop("checked", $("#recoveryCheckAll").is(":checked"));
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
                $(this).load(url+'?orderCode='+orderCode+'&category=05'+'&memo='+encodeURIComponent(memo));
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#memoManage").dialog('close');

                    });
            }
            ,close:function(){
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
</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="recoveryVO" id="recoveryDetailForm"  name="recoveryDetailForm" method="post" action="" >
	   <input type="hidden" name="recoverCode"               id="recoveryCode"            value="${recoveryVO.recoveryCode}" />
	   <input type="hidden" name="groupId"               id="groupId"            value="${recoveryVO.groupId}" />
	   <input type="hidden" name="orderCode"               id="orderCode"            value="" />
	   <input type="hidden" name="memo"               id="memo"            value="" />
	   <input type="hidden" name="category"               id="category"            value="05" />
	      <tr>
          <div style="position:absolute; left:30px" > 
	       <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span>(${recoveryVO.groupName}) 회수 대상품목  </font></strong></h4>
          </div >
          <div style="position:absolute; right:30px" > 
          <c:choose>
    		<c:when test="${recoveryVO.recoveryState!='01' && strAuth!= '03'}">
				<button type="button" class="btn btn-primary" onClick="fcOrder_process()">검수</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-primary" onClick="fcOrder_process()">회수</button>
			</c:otherwise>
		  </c:choose>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >회수번호</th>
          <th class='text-center'><c:out value="${recoveryVO.recoveryCode}"></c:out>&nbsp;&nbsp;<button id="downbtn" type="button" class="btn btn-xs btn-success" onClick="fcRecovery_print('${recoveryVO.recoveryCode}')" >출력</button></th>
          <th class='text-center' style="background-color:#E6F3FF">회수요청일</th>
          <th class='text-center'><c:out value="${recoveryVO.regDateTime}"></c:out></th>
      	  <th class='text-center' style="background-color:#E6F3FF">회수마감일</th>
          <th class='text-center'><c:out value="${recoveryVO.recoveryClosingDate}"></c:out></th>	
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">메모&nbsp;<img id="etcbtn" onClick="fcMemo_detail('${recoveryVO.recoveryCode}','${recoveryVO.memo}')" src="<%= request.getContextPath()%>/images/common/icon_memo.gif" width="16" height="16" align="absmiddle" title="메모">
          <button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcMemo_detail('${recoveryVO.recoveryCode}','${recoveryVO.memo}')" >관리(${recoveryVO.memoCnt})</button></th>
          <th colspan='5' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${recoveryVO.memo}" placeholder="메모" disabled /></th>
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
     	  <c:choose>
    		<c:when test="${recoveryVO.recoveryState!='01' && strAuth!= '03'}">
				<th class='text-center' >검수<br><input type="checkbox"  id="recoveryCheckAll"  name="recoveryCheckAll" onchange="fcRecovery_checkAll()" title="전체선택" /></th>
			</c:when>
			<c:otherwise>
				<th class='text-center' >검수<br><input type="checkbox"  id="recoveryCheckAll"  name="recoveryCheckAll" onchange="fcRecovery_checkAll()" title="전체선택" disabled /></th>
			</c:otherwise>
		  </c:choose>
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
		    		<c:when test="${recoveryVO.recoveryState!='01' && strAuth!= '03'}"> 
						<td class='text-center'><input type="checkbox" id="recoveryCheck" name="recoveryCheck" value="${recoveryVO.productCode}" title="선택"  /></td>
					</c:when>
					<c:otherwise>
						<td class='text-center'><input type="checkbox" id="recoveryCheck" name="recoveryCheck" value="${recoveryVO.productCode}" title="선택" disabled /></td>
					</c:otherwise>
				</c:choose>
                 <td class='text-left'><c:out value="${recoveryVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.productPrice}" /></td>
                 <input type="hidden" id="productPrice" name="productPrice" value="${recoveryVO.productPrice}" >
                 <input type="hidden" id="recoveryResultCntRaw" name="recoveryResultCntRaw" value="${recoveryVO.recoveryResultCnt}" >
                 <input type="hidden" id="recoveryResultCnt" name="recoveryResultCnt" value="${recoveryVO.recoveryResultCnt}" >
                 <td class='text-right' id='stockCnt' name='stockCnt'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.stockCnt}"/></td>
                 <td class='text-right' id='recoveryResultCntView' name='recoveryResultCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultCnt}"/></td>
                 <td class='text-right'><input style="width:35px" type="text" class="form-control" id="addCnt" name="addCnt" onKeyup="fcAdd_Cnt('${status.count}')" value="${recoveryVO.addCnt}"></td>
                 <td class='text-right'><input style="width:35px" type="text" class="form-control" id="lossCnt" name="lossCnt" onKeyup="fcLoss_Cnt('${status.count}')" value="${recoveryVO.lossCnt}"></td>
                 <td class='text-center'><c:if test="${recoveryVO.recoveryState!='01'}"><img id="etcbtn" onClick="fcEtc_detail('${recoveryVO.recoveryCode}','${recoveryVO.productCode}','${recoveryVO.productName}','${recoveryVO.recoveryMemo}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고"></c:if></td>
                 <tr>
                 <c:choose>
		    		<c:when test="${recoveryVO.recoveryState!='01'}"> 
						 <td colspan='8' class='text-center'><input type="text" class="form-control" id="etc" name="etc"  value="${recoveryVO.recoveryMemo}" placeholder="비고" disabled /></td>
					</c:when>
					<c:otherwise>
						 <td colspan='8' class='text-center'><input type="text" class="form-control" id="etc" name="etc"  value="${recoveryVO.recoveryMemo}" placeholder="비고" /></td>
					</c:otherwise>
				</c:choose>
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
	<div id="memoManage"  title="메모관리"></div>
    <!-- //메모 상세화면 -->
    <div id="etcManage"  title="비고"></div>
    <!-- //비고 상세화면 -->
	<script type="text/javascript">

    totalRecoveryAmt();
</script>