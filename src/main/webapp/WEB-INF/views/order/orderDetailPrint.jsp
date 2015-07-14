<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<script>
window.print();
</script>
<html>
<head>

 	<title>검수확인서</title>
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
	</script>
	</head>
	<body>
	<div class="container" style="width:950">
	 <div class="form-group" >
	 <form:form commandName="orderVO" id="orderDetailForm"  name="orderDetailForm" method="post" action="" >
	 <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span>검수확인서</font></strong> </h4>
		<table class="table table-bordered" >
	 	<tr>
          <th rowspan='9' class='text-center' style="background-color:#E6F3FF">수신</th>
          <th class='text-center'  style="background-color:#E6F3FF" >수신</th>
          <th class='text-center'><input disabled type="text" class="form-control" id="deliveryName" name="deliveryName"  value="${orderVO.deliveryName}" placeholder="수신" /></th>
          <th rowspan='9' class='text-center'  style="background-color:#E6F3FF">발신</th>
          <th class='text-center' style="background-color:#E6F3FF">발신</th>
          <th class='text-center'><input  disabled type="text" class="form-control"  value="${orderVO.orderName}" placeholder="발신"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >담당자</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="deliveryCharge" name="deliveryCharge"  value="${orderVO.deliveryCharge}" placeholder="참조" /></th>
          <th class='text-center' style="background-color:#E6F3FF" >담당자</th>
          <th class='text-center'><input  disabled type="text" class="form-control" id="orderCharge" name="orderCharge"  value="${orderVO.orderCharge}" placeholder="참조" /></th>
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
          <input  disabled type="text" class="form-control" id="deliveryDate" name="deliveryDate"  value="${orderVO.deliveryDate}" placeholder="SMS" />
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
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">메모&nbsp;<span id="memoCnt" style="color:blue">(${orderVO.memoCnt})</span></th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${orderVO.memo}" placeholder="메모" disabled /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 <form:form commandName="orderListVO" id="orderDetailListForm" name="orderDetailListForm" method="post" action="" >
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
	     	<c:if test="${orderVO.orderState=='03'}"><td>&nbsp;<button type="button" class="btn btn-xs btn-info" onClick="fcBarCode_check()" >바코드 검수</button></td></c:if>
	     </tr>
     </table> 
	  <table class="table table-bordered" >
      	<tr style="background-color:#E6F3FF">
          <th rowspan='2' class='text-center' >검수<br>
          <c:if test="${orderVO.orderState!='06' && orderVO.orderState!='07'}">
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
							<td class='text-center'>${status.count}.</td>
						</c:when>
						<c:otherwise>
							<td class='text-center'>${status.count}.</td>
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
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.orderCnt}"/></td>
                 <input type="hidden" name="orderCnt" value="${orderVO.orderCnt}">
                 <td class='text-right'><input disabled style="width:35px" type="text" class="form-control" id="orderResultCnt" name="orderResultCnt" onKeyup="totalOrderAmt()" value="${orderVO.orderResultCnt}"></td>
                 
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.orderPrice+orderVO.orderVatRate}" /></td>
                 <input type="hidden" name="orderPrice" value="${orderVO.orderPrice+orderVO.orderVatRate}">
                 <input style="width:80px" type="hidden" class="form-control" id="orderVatRate" name="orderVatRate" onKeyup="totalOrderAmt()" value="0">
                 
                 <td class='text-right'><input style="width:90px;" disabled type="text" class="form-control" id="orderResultPrice" name="orderResultPrice" onKeyup="totalOrderAmt()" value="${orderVO.orderResultPriceView}"></td>
                 <td class='text-right' id='orderTotalPriceView' name='orderTotalPriceView'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="0"/></td>
                 <td class='text-right'>(${orderVO.etcCnt})</td>
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
	</div>
	</form:form>
	</div>
	</body>
</html>
<script>
totalTargetAmt();
totalOrderAmt();
</script>
