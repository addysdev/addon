<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
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
	
	var h=800;
	var s=950;
	var frm = document.recoveryDetailListForm;
	var url="<%= request.getContextPath() %>/recovery/recoverycodeprint?recoveryCode="+recoveryCode;

   // tmt_winLaunch(url, 'printObj', 'printObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
	 frm.action =url; 
	 frm.method="post";
	 frm.target='printObj';
	 frm.submit();
}
//회수처리
function fcRecovery_process(){

        var frm = document.recoveryDetailListForm;
 
    	if(frm.seqs.length>1){
       		for(i=0;i<frm.seqs.length;i++){
				frm.seqs[i].value=fillSpace(frm.productCode[i].value)+'|'+fillSpace(frm.stockDate[i].value)+'|'+fillSpace(frm.stockCnt[i].value)+'|'+fillSpace(frm.recoveryCnt[i].value)+
       			'|'+fillSpace(frm.addCnt[i].value)+'|'+fillSpace(frm.lossCnt[i].value)+'|'+fillSpace(frm.etc[i].value);
 
       		}
       	}else{
       		
			frm.seqs.value=fillSpace(frm.productCode.value)+'|'+fillSpace(frm.stockDate.value)+'|'+fillSpace(frm.stockCnt.value)+'|'+fillSpace(frm.recoveryCnt.value)+
   			'|'+fillSpace(frm.addCnt.value)+'|'+fillSpace(frm.lossCnt.value)+'|'+fillSpace(frm.etc.value);

       	}

        if (confirm('회수 요청건을 처리 하시겠습니까?')){ 
        	
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
	 
 	if(frm.seqs.length>1){
    		for(i=0;i<frm.seqs.length;i++){
				frm.seqs[i].value=fillSpace(frm.productCode[i].value)+'|'+fillSpace(frm.recoveryResultCnt[i].value);

    		}
    	}else{
    		
    		frm.seqs.value=fillSpace(frm.productCode.value)+'|'+fillSpace(frm.recoveryResultCnt.value);

    	}

     if (confirm('회수뭎품을 검수 완료처리 하시겠습니까?')){ 
     	
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

	    if('${recoveryVO.recoveryState}'=='01'){
	    	return;
	    }
	
    	var frm=document.recoveryDetailListForm;
    	var amtCnt = frm.productCode.length;
    	
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}

    	var chkCnt=0;
    	
    	if(amtCnt > 1){
			for(i=0;i<amtCnt;i++){
	    		
	    		if(frm.recoveryCheck[i].checked==true){
	    			frm.recoveryResultCntView[i].disabled=true;
	    			chkCnt++;
	    		}else{
	    			frm.recoveryResultCntView[i].disabled=false;
	    		}
	    	}
    	}else{

    		if(frm.recoveryCheck.checked==true){
    			frm.recoveryResultCntView.disabled=true;
    			chkCnt++;
	   		}else{
	   			frm.recoveryResultCntView.disabled=false;
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
//품목 상세 페이지 리스트 Layup
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
            $(this).load(url+'?orderCode='+orderCode+'&category=06'+'&productCode='+productCode+'&productName='+encodeURIComponent(productName)+'&etc='+encodeURIComponent(etc));
           
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#etcManage").dialog('close');

                });
        }
        ,close:function(){
        	$('#etcList').empty();
            $('#etcManage').empty();
        }
    });
};

$(window).unload(function() {
	 // alert('Handler for .unload() called.');
	});
</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="recoveryVO" id="recoveryDetailForm"  name="recoveryDetailForm" method="post" action="" >
	   <input type="hidden" name="recoveryCode"               id="recoveryCode"            value="${recoveryVO.recoveryCode}" />
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
    		<c:when test="${recoveryVO.recoveryState=='02' && strAuth!= '03'}">
				<button type="button" id="checkbtn"  name="checkbtn" disabled class="btn btn-primary" onClick="fcRecovery_complete()">검수완료</button>
			</c:when>
			<c:otherwise>
				<c:if test="${recoveryVO.recoveryState=='01'}"><button type="button" class="btn btn-primary" onClick="fcRecovery_process()">회수</button></c:if>
			</c:otherwise>
		  </c:choose>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >회수번호</th>
          <th class='text-center'><c:out value="${recoveryVO.recoveryCode}"></c:out>&nbsp;&nbsp;<button id="downbtn" type="button" class="btn btn-xs btn-success" onClick="fcRecovery_print('${recoveryVO.recoveryCode}')" >인쇄</button></th>
          <th class='text-center' style="background-color:#E6F3FF">회수요청일</th>
          <th class='text-center'><c:out value="${recoveryVO.regDateTime}"></c:out></th>
      	  <th class='text-center' style="background-color:#E6F3FF">회수마감일</th>
          <th class='text-center'><c:out value="${recoveryVO.recoveryClosingDate}"></c:out></th>	
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">메모&nbsp;<span id="memoCnt" style="color:blue">(${recoveryVO.memoCnt})</span>
          <button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcMemo_detail('${recoveryVO.recoveryCode}','${recoveryVO.memo}')" >관리</button></th>
          <th colspan='5' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${recoveryVO.memo}" placeholder="메모" disabled /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="recoveryListVO" id="recoveryDetailListForm" name="recoveryDetailListForm" method="post" action="" >
      <p> <span class="glyphicon glyphicon-asterisk"></span> 
        <span style="color:blue"> [품목건수] : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryDetailList.size()}" /> 건  [회수합계 금액] :</span>
        <span id="totalRecoveryAmt" style="color:gray">
        </span>
      </p>  
      <p><span class="glyphicon glyphicon-asterisk"></span> 
          <span style="color:blue"> [검수합계 금액] :</span>
          <span id="totalRecoveryResultAmt" style="color:red">
        </span>
      </p>       
	  <table class="table table-bordered" >
      	<tr style="background-color:#E6F3FF">
     	  <c:choose>
    		<c:when test="${recoveryVO.recoveryState=='02' && strAuth!= '03'}">
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
          <th class='text-center'>검수수량</th>
          <th class='text-center'>비고</th>
      	</tr>
	    	<c:if test="${!empty recoveryDetailList}">
             <c:forEach items="${recoveryDetailList}" var="recoveryVO" varStatus="status">
             	 <input type="hidden" id="seqs" name="seqs" >
	             <tr id="select_tr_${recoveryVO.productCode}">
                 <c:choose>
		    		<c:when test="${recoveryVO.recoveryState=='01' || recoveryVO.recoveryState=='03' || strAuth== '03'}"> 
		    		   <c:choose>
				    		<c:when test="${recoveryVO.recoveryYn=='Y' && recoveryVO.recoveryState=='03'}">
								<td class='text-center'>${status.count}.<input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" disabled checked onChange="totalCheck()" /></td>
							</c:when>
							<c:otherwise>
								<td class='text-center'>${status.count}.<input type="checkbox" id="orderCheck" name="orderCheck" value="${orderVO.productCode}" title="선택" disabled onChange="totalCheck()" /></td>
							</c:otherwise>
						</c:choose>
		    		</c:when>
					<c:otherwise>
						<td class='text-center'>${status.count}.<input type="checkbox" id="recoveryCheck" name="recoveryCheck" value="${recoveryVO.productCode}" title="선택"  onChange="totalCheck()" /></td>
					</c:otherwise>
				</c:choose>
                 <td class='text-left'><c:out value="${recoveryVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.productPrice}" /></td>
                 <input type="hidden" id="productCode" name="productCode" value="${recoveryVO.productCode}" >
                 <input type="hidden" id="productPrice" name="productPrice" value="${recoveryVO.productPrice}" >
                 <input type="hidden" id="stockDate" name="stockDate" value="${recoveryVO.stockDate}" >
                 <input type="hidden" id="stockCnt" name="stockCnt" value="${recoveryVO.stockCnt}" >
                 <input type="hidden" id="recoveryCntRaw" name="recoveryCntRaw" value="${recoveryVO.recoveryCnt}" >
                 <input type="hidden" id="recoveryCnt" name="recoveryCnt" value="${recoveryVO.recoveryCnt}" >
                 <input type="hidden" id="recoveryResultCnt" name="recoveryResultCnt" value="${recoveryVO.recoveryResultCnt}" >
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.stockCnt}"/></td>
                 <td class='text-right' id='recoveryCntView' name='recoveryCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryCnt}"/></td>
                 <c:choose>
		    		<c:when test="${recoveryVO.recoveryState!='01'}"> 
					   <td class='text-right' id='addCnt' name='addCnt'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.addCnt}"  /></td>
					   <td class='text-right' id='lossCnt' name='lossCnt'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.lossCnt}"  /></td>
                    </c:when>
					<c:otherwise>
					   <td class='text-right'><input style="width:35px" type="text" class="form-control" id="addCnt" name="addCnt" onKeyup="fcAdd_Cnt('${status.count}')" value="${recoveryVO.addCnt}"></td>
                 	<td class='text-right'><input style="width:35px" type="text" class="form-control" id="lossCnt" name="lossCnt" onKeyup="fcLoss_Cnt('${status.count}')" value="${recoveryVO.lossCnt}"></td>
                </c:otherwise>
				</c:choose>
                 <c:choose>
		    		<c:when test="${recoveryVO.recoveryState=='02' && strAuth!= '03'}"> 
					    <td class='text-right'><input style="width:35px" type="text" class="form-control" id="recoveryResultCntView" name="recoveryResultCntView" onKeyup="fcResult_Cnt('${status.count}')" value="${recoveryVO.recoveryResultCnt}"></td>
                    </c:when>
					<c:otherwise>
						<td class='text-right' id='recoveryResultCntView' name='recoveryResultCntView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultCnt}"  /></td>
					</c:otherwise>
				</c:choose>
                 <td class='text-center'><c:if test="${recoveryVO.recoveryState!='01'}"><img id="etcbtn" onClick="fcEtc_detail('${recoveryVO.recoveryCode}','${recoveryVO.productCode}','${recoveryVO.productName}','${recoveryVO.etc}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고">(${recoveryVO.etcCnt})</c:if></td>
                 <tr>
                 <c:choose>
		    		<c:when test="${recoveryVO.recoveryState!='01'}"> 
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
	  </table>
	 </form:form>
	</div>
	<script type="text/javascript">

    totalRecoveryAmt();
    totalRecoveryResultAmt();
    totalCheck();
</script>