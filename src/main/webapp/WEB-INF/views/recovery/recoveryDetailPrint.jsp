<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<script>
window.print();
</script>
<html>
<head>

 	<title>회수목록</title>
    <meta http-equiv='Content-Type' content='text/html; charset=euc-kr' />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">
	<style>
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
	
	
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
	<script>
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
		  document.all('totalRecoveryCnt').innerText=addCommaStr(''+totalcnt)+' 건  ';
		  document.all('totalRecoveryAmt').innerText=addCommaStr(''+totalamt)+' 원  ';
			

	}
	
	function totalCheck(){
		
	    //if('${recoveryVO.receiveCnt==recoveryVO.totalCnt && strAuth!= "03"}'){
	    if('${strAuth!= "03" || strAuthId== "AD001" }'){

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
	</script>
	</head>
	<body>
	<div class="container" style="width:950">
	 <div class="form-group" >
	 <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span>회수목록</font></strong> </h4>
	 </div>
	 <form:form commandName="orderListVO" id="orderDetailListForm" name="orderDetailListForm" method="post" action="" >
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
     </table>
      <div class="thead">
	   <table cellspacing="0" border="0" summary="회수리스트" class="table table-bordered tbl_type" style="table-layout: fixed">
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
    		<c:when test="${recoveryConVO.recoveryState=='03'  && (strAuth!='03' || strAuthId=='AD001')}">
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
		    		<c:when test="${recoveryConVO.recoveryState=='03'  && (strAuth!='03' || strAuthId=='AD001')}"> 
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
		    		<c:when test="${recoveryConVO.recoveryState=='03'  && (strAuth!='03' || strAuthId=='AD001')}"> 
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
	</body>
</html>
<script>
fcResult_cal();
totalCheck();
</script>
