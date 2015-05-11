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
	
	var frm=document.targetDetailForm;
	var emailCheckCnt = $('input:checkbox[ name="emailCheck"]:checked').length;
	var smsCheckCnt = $('input:checkbox[ name="smsCheck"]:checked').length;

	if(emailCheckCnt > 0){
		
		frm.emailKey.value='Y';
		
		if(frm.deliveryEmail.value==''){
			
			alert('발주 대상 이메일 주소가 없습니다.');
			return;
		}
		
		if(smsCheckCnt > 0){
			
			if(frm.deliveryMobile.value==''){
				
				alert('발주 대상 sms 번호가 없습니다.');
				return;
			}
			
			if(!confirm("발주 처리 하시겠습니까?\n상품 주문서는 이메일["+frm.deliveryEmail.value+"] 과\nSMS ["+frm.deliveryMobile.value+"] 로 전송됩니다."))
				return;
			
		}else{
			
			frm.smsKey.value='N';
			
			if(!confirm("발주 처리 하시겠습니까?\n상품 주문서는 이메일["+frm.deliveryEmail.value+"]로 전송됩니다."))
				return;
		}

    }
	
    $.ajax({
        type: "POST",
        async:false,
           url:  "<%= request.getContextPath() %>/order/orderProcess",
           data:$("#targetDetailForm").serialize(),
           success: function(result) {

				if(result=='true'){
					 alert('발주 처리를 성공했습니다.');
				} else{
					 alert('발주 처리를 실패했습니다.');
				}
				
				$('#targetDetailView').dialog('close');
				fcTarget_listSearch();
				
           },
           error:function(){
        	   
        	   alert('발주 처리를 실패했습니다.');
        	   $('#targetDetailView').dialog('close');
           }
    });
}
</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="targetVO" name="targetDetailForm" method="post" action="" >
	   <input type="hidden" name="emailKey"             id="emailKey"            value="1" />
	   <input type="hidden" name="smsKey"               id="smsKey"            value="0" />
	      <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span> 발주방법 : </font></strong>
	          <input type="checkbox" id="emailCheck" name="emailCheck" value="" title="선택" checked disabled />e-mail
	          <input type="checkbox" id="smsCheck" name="smsCheck" value="" title="선택" disabled />sms
	      </h4>
	      <button type="button" class="btn btn-primary" onClick="fcUserManage_excelForm()">보류</button>
	      <button type="button" class="btn btn-primary" onClick="fcUserManage_regForm()">보류수정</button>
	      <button type="button" class="btn btn-danger" onClick="fcUserManage_delete()">보류폐기</button>
	      <button type="button" class="btn btn-primary" onClick="fcUserManage_regForm()">보류사유</button>
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
          <button type="button" class="btn btn-primary" onClick="fcTargetDetail_print()">출력</button>
          <button type="button" class="btn btn-primary" onClick="fcOrder_process()">발주</button>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th rowspan='9' class='text-center' style="background-color:#E6F3FF">수신</th>
          <th class='text-center'  style="background-color:#E6F3FF" >수신</th>
          <th class='text-center'><input type="text" class="form-control" id="companyName" name="companyName"  value="${targetVO.companyName}(${targetVO.deliveryCharge})" placeholder="수신" /></th>
          <th rowspan='9' class='text-center'  style="background-color:#E6F3FF">발신</th>
          <th class='text-center' style="background-color:#E6F3FF">발신</th>
          <th class='text-center'><input type="text" class="form-control" id="groupName" name="groupName"  value="ADDYS ${targetVO.groupName}" placeholder="발신"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="deleveryEtc" name=""deleveryEtc""  value="물류팀" placeholder="참조" /></th>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="orderUserName" name="orderUserName"  value="${targetVO.orderUserName}" placeholder="참조" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryMobile" name="deliveryMobile"  value="${targetVO.deliveryMobile}"  placeholder="핸드폰"/></th>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="mobilePhone" name="mobilePhone"  value="${targetVO.mobilePhone}"  placeholder="핸드폰"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryEmail" name="deliveryEmail"  value="${targetVO.deliveryEmail}" placeholder="e-mail" /></th>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="email" name="email"  value="${targetVO.email}" placeholder="e-mail" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="phone" name="phone"  value="${targetVO.phone}" placeholder="tel" /></th>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryTel" name="deliveryTel"  value="${targetVO.deliveryTel}" placeholder="tel" /></th>
      	</tr>
      	<th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryFax" name="deliveryFax"  value="${targetVO.deliveryFax}" placeholder="fax" /></th>
          <th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="faxNumber" name="faxNumber"  value="${targetVO.faxNumber}" placeholder="fax" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">발주일자</th>
          <th class='text-center'>
          	
          		<div style='width:150px' class='input-group date ' id='datetimepicker1' data-link-field="orderDateTime" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${targetVO.orderDateTime}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="orderDateTime" name="orderDateTime" value="${targetVO.orderDateTime}" />
	            </div>
	         
          </th>
          <th rowspan='2' class='text-center' style="background-color:#E6F3FF">배송주소</th>
          <th rowspan='2' class='text-center'><textarea style='height:82px'  class="form-control" row="2" id="address" name="address" >서울특별시 영등포구 여의도동 54-6 영창빌딩 6층 물류팀</textarea></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품일자</th>
          <th class='text-center'>
          		
          		<div  style='width:150px' class='input-group date ' id='datetimepicker2' data-link-field="deliveryDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${targetVO.deliveryDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="deliveryDate" name="deliveryDate" value="${targetVO.deliveryDate}" />
	            </div>
             
          </th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품방법</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryMethod" name="deliveryMethod"  value="택배배송" placeholder="납품방버" /></th>
          <th class='text-center' style="background-color:#E6F3FF">결재방법</th>
          <th class='text-center'><input type="text" class="form-control" id="approvedMethod" name="approvedMethod"  value="입금지정일.현금" placeholder="결재방법" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">SMS내용</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="sms" name="sms"  value="${targetVO.deliveryCharge}님 ADDYS ${targetVO.groupName}에서 발주서를 보냈습니다.당일처리 부탁드립니다." placeholder="SMS" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">메모</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="" placeholder="메모" /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="targetVO" name="targetDetailListForm" method="post" action="" >
      <p><span style="color:#FF9900">
        <span class="glyphicon glyphicon-asterisk"></span> 
                    합계 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="" />
                    공급가 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="" />
                    부가세 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="" />
        </span>
      </p>       
	  <table class="table table-bordered" >
      	<tr style="background-color:#E6F3FF">
          <th rowspan='2' class='text-center' >보류</th>
          <th rowspan='2' class='text-center'>품목코드</th>
          <th rowspan='2' class='text-center'>상품명</th>
          <th colspan='3' class='text-center'>발주</th>
          <th colspan='3' class='text-center'>재고</th>
          <th rowspan='2' class='text-center'>비고</th>
      	</tr>
      	<tr style="background-color:#E6F3FF">
          <th class='text-center'>기준단가</th>
          <th class='text-center'>수량</th>
          <th class='text-center'>증감</th>
          <th class='text-center'>안전</th>
          <th class='text-center'>보유</th>
          <th class='text-center'>전산</th>
      	</tr>
	    	<c:if test="${!empty targetDetailList}">
             <c:forEach items="${targetDetailList}" var="targetVO" varStatus="status">
             <tr id="select_tr_${targetVO.productCode}">
                 <td><input type="checkbox" id="userCheck" name="userCheck" value="${targetVO.productCode}" title="선택" /></td>
                 <td class='text-center'><c:out value="${targetVO.productCode}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.productName}"></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.safeStock}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.holdStock}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.stockCnt}"></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty targetDetailList}">
           <tr>
           	<td colspan='10' class='text-center'>조회된 데이터가 없습니다.</td>
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
</script>